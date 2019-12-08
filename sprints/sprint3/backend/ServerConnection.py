#ServerConnection.py used to establish a connection with frontend
import pymongo
import socket
import pprint

#Taken out: real database connection string
client = pymongo.MongoClient("mongodb+srv://<databaseinfo>/test?retryWrites=true&w=majority")
db = client.test_database
collection = db.test_collection

def openConnection():
    HOST = "localhost"
    PORT = 9999
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        s.bind((HOST, PORT))
    except socket.error as err:
        print('Bind failed. Error Code : ' .format(err))
    s.listen(10)
    open = True
    try:
        while(open):
            conn, addr = s.accept()
            message = conn.recv(1024)
            msg = message.decode(encoding='UTF-8') 
            if(msg.isnumeric()):
                return msg
            elif(msg != "close"):
                credentials = msg.split()
                username = credentials[0]
                password = credentials[1]
                event = credentials[2]
                if(event == "login"):
                    if(collection.count_documents({'username': username, 'password': password}) == 1):
                        conn.send(bytes("ok"+"\r\n",'UTF-8'))
                    else:
                        conn.send(bytes("notok"+"\r\n",'UTF-8'))
                if(event == "register"):
                    if(collection.count_documents({'username': username}) >= 1):
                        conn.send(bytes("taken"+"\r\n",'UTF-8'))
                    else:
                        conn.send(bytes("ok" + "\r\n", 'UTF-8'))
                        collection.insert_one({'username' : username, 'password' : password})

            if(message.decode(encoding='UTF-8') == "close"):
                print("Connection gracefully closed")
                open = False
    except ConnectionResetError:
        s.close()
        print("Connection terminated")
    s.close()