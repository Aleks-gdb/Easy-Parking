import ServerConnection

run = True
while(run):
    num = ServerConnection.openConnection() #Create a connection with the frontend
    if (num == None):
        run = False
