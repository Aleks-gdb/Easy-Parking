#Imports the necessary libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import tensorflow as tf
import os, random, cv2, pickle
from PIL import Image

#List of all labels used for recognition
CATEGORIES = ["Flute", "Guitar", "Saxophone", "Trumpet", "Tuba", "Violin"]

#Initializes the array of official data
training_data = []

#Accessing My Google Drive
from google.colab import drive
drive.mount("drive")
base_image_path = "drive/My Drive/musical data/"

#Creates a list of all files from the specified folder
def loadImages(path):
    image_files = sorted([os.path.join(path, file)
                          for file in os.listdir(path)])
    return image_files

#Creates a dataset from the linked google drive based on labels in CATEGORIES
def create_training_data():
    for category in CATEGORIES:
        class_num = CATEGORIES.index(category)
        image_path = base_image_path + CATEGORIES[class_num]
        dataset = loadImages(image_path)
        #Subsets of total data can be generated by limiting the iterations of 'dataset'
        for img in dataset[30:200]:
            try:
                pil_im = Image.open(img)                              #opens image
                gray_im = pil_im.convert('LA')                        #converts to grayscale
                std_size = (360, 1440)                                #sets a standard size for images
                gray_im = gray_im.resize(std_size)                    #applies standard size
                img_arr = np.asarray(gray_im)                         #converts image to numerical array
                img_arr = np.reshape(img_arr, [360,1440,2])
                img_arr = np.true_divide(img_arr, 255.0)              #converts values to [0-1] scale for better model evaluation
                training_data.append([img_arr[:,:,[0]], class_num])   #appends to official dataset with shape(360,1440,1) and adds label
            except Exception as e:      #produces exception if the image cannot be read
              print('Fail: ' + e)
              pass

#builds the official dataset and shuffles it for model integrity
create_training_data()
random.shuffle(training_data)

#outputs data to the user to verify incoming information
print(len(training_data))
for sample in training_data[:10]:
    print(sample[1])

#creates final lists of features and labels from dataset
X = []
y = []
for features, label in training_data:
    X.append(features)
    y.append(label)

#changes directory for easy colab storage access of datasets
%cd '/content/'

#Saves all data to corresponding pickle files
pickle_out = open("X_train.pickle","wb") #<--easy pivotting between generating test and train
#pickle_out = open("X_test.pickle","wb")
pickle.dump(X, pickle_out)
pickle_out.close()

pickle_out = open("y_train.pickle","wb")
#pickle_out = open("y_test.pickle","wb")
pickle.dump(y, pickle_out)
pickle_out.close()

pickle_in = open("X_train.pickle", "rb")
#pickle_in = open("X_test.pickle", "rb")
X = pickle.load(pickle_in)

print('Done')