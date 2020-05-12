#ServerConnection.py used to establish a connection with frontend
import pymongo, socket, pprint, hashlib, binascii, os

client = pymongo.MongoClient("mongodb+srv://dbTest:dbTestPass@cluster0-6dhsl.mongodb.net/test?retryWrites=true&w=majority")
db = client.test_database
collection = db.test_collection

def hashPass(password):
    salt = hashlib.sha256(os.urandom(60)).hexdigest().encode('ascii')
    pwdhash = hashlib.pbkdf2_hmac('sha512', password.encode('utf-8'), salt, 100000)
    pwdhash = binascii.hexlify(pwdhash)
    return (salt + pwdhash).decode('ascii')

def verifyPass(spass, password):
    salt = spass[:64]
    spass = spass[64:]
    pwdhash = hashlib.pbkdf2_hmac('sha512', 
                                  password.encode('utf-8'), 
                                  salt.encode('ascii'), 
                                  100000)
    pwdhash = binascii.hexlify(pwdhash).decode('ascii')
    return pwdhash == spass

def openConnection():
    HOST = "localhost"
    PORT = 9999
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        s.bind((HOST, PORT))
    except socket.error as err:
        print('Bind failed. Error Code : ' .format(err))
    s.listen(10)
    #conn, addr = s.accept()
    open = True
    try:
        while(open):
            conn, addr = s.accept()
            #conn.send(bytes("Message1"+"\r\n",'UTF-8'))
            message = conn.recv(1024)
            msg = message.decode(encoding='UTF-8') 
            if(msg.isnumeric()):
                return msg
            elif(msg != "close"):
                credentials = msg.split()
                username = credentials[0]
                #password = hashPass(credentials[1])
                password = credentials[1]
                event = credentials[2]
                if(event == "login"):
                    # p = collection.find_one({'username': username})
                    # print(p['password'])
                    # print("\n\n")
                    # print(password)
                    if(collection.count_documents({'username': username, 'password': password}) == 1):
                    #if(verifyPass(p['password'], password)):
                        conn.send(bytes("ok"+"\r\n",'UTF-8'))
                    else:
                        conn.send(bytes("notok"+"\r\n",'UTF-8'))
                if(event == "register"):
                    if(collection.count_documents({'username': username}) >= 1):
                        conn.send(bytes("taken"+"\r\n",'UTF-8'))
                    else:
                        conn.send(bytes("ok" + "\r\n", 'UTF-8'))
                        collection.insert_one({'username' : username, 'password' : password})
            
            # print(message.decode(encoding='UTF-8'))

            if(message.decode(encoding='UTF-8') == "close"):
                print("Connection gracefully closed")
                open = False
    except ConnectionResetError:
        s.close()
        print("Connection terminated")
    s.close()

# print(db)
# print(collection)
# user = { 'username' : username,
#           'password' : password}
# user_id = collection.insert_one(user)
# for user in collection.find():
#     pprint.pprint(user)
#print(collection.count_documents({'username': username}))
