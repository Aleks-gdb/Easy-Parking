import ServerConnection
#import MachineLearning
run = True
while(run):
    num = ServerConnection.openConnection() #Create a connection with the frontend
    if (num == None):
        run = False
    else:
        print("You entered " + num + " iterations.")
        #MachineLearning.mLearning(num) #Start the machine learning model and pass the number of iterations
