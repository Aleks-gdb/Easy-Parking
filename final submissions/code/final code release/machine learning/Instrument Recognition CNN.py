#Runs the training of the Machine Learning model. This is done once on a host computer and
#the .sav file generated is then uploaded and referenced by the server to predict all future graphs.

#Imports the necessary libraries
import tensorflow as tf
from keras.models import Sequential
from keras.layers import Dense, Dropout, Activation, Flatten,\
    Conv2D, MaxPooling2D
from keras.utils import to_categorical
import pickle

#Loads in testing and training data
X_train = pickle.load(open("X_train.pickle","rb"))
y_train = pickle.load(open("y_train.pickle","rb"))
X_test = pickle.load(open("X_test.pickle","rb"))
y_test = pickle.load(open("y_test.pickle","rb"))

#outputs verification data to the user
print(len(X))
print(len(y))

#converts the data to model-friendly formats
X_train = np.array(X_train)
X_test = np.array(X_test)
y_train_bin = to_categorical(y_train)
y_test_bin = to_categorical(y_test)

model = Sequential()  #initializes the model
#runs first convolution with 32 filters, 3x3 kernal, and (360,1440,1) input from our converted images
model.add(Conv2D(32, (3,3), input_shape =(360,1440,1)))
model.add(Activation("relu")) #normalizes the data
model.add(Conv2D(32, (3,3)))
model.add(Activation("relu"))
model.add(MaxPooling2D(pool_size=(2,2))) #pools data to reduce input size and boost feature relevance

#repeats the layers with added filters
model.add(Conv2D(64, (3,3)))
model.add(Activation("relu"))
model.add(Conv2D(64, (3,3)))
model.add(Activation("relu"))
model.add(MaxPooling2D(pool_size=(2,2)))

model.add(Flatten())  #squashes data to prepare for labeling
model.add(Dense(64))  #condenses to prepare for softmax
model.add(Activation("relu")) #normalization

model.add(Dropout(0.25))  #runs dropout to reduce overfitting

model.add(Dense(3, activation='softmax')) #condenses to the number of lables(3) and selects the highest

#uses categorical crossentropy to prepare for multiple value single lable processing
model.compile(loss="categorical_crossentropy", optimizer="adam", metrics=['accuracy'])

#trains the model
history = model.fit(X_train, y_train_bin, epochs=4, batch_size=32, validation_split=0.1)

#tests the model and prints results
test_results = model.evaluate(X_test, y_test_bin, verbose=1)
print(f'Test results - Loss: {test_results[0]} - Accuracy: {test_results[1]*100}%')
