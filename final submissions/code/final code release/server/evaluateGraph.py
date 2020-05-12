#Creates the prediction for a PSD graph using a pretrained model that has been uploaded to the server

import joblib
import numpy as np
from PIL import Image
import os

#Holds string values of all potential labels
CATEGORIES = ["Flute", "Guitar", "Saxophone", "Trumpet", "Tuba", "Violin"]

#Preprocesses the incomming graph to be in an acceptable format for evaluation by the trained model
#Preprocessing consists of grayscaling, resizing, and reducing input values
def preprocess(img):
  pil_im = Image.open(img)              #opens the image
  gray_im = pil_im.convert('LA')        #converts to grayscale
  std_size = (360, 1440)                #specifies a standard image size
  gray_im = gray_im.resize(std_size)    #resizes the image
  img_arr = np.array(gray_im)           #converts the image to an array
  img_arr = np.reshape(img_arr, [360,1440,2])   #retains the intended dimensions for the image
  img_arr = np.true_divide(img_arr, 255.0)      #reduces all values to a [0-1] representation
  return img_arr[:,:,[0]]

def evaluateGraph(img):
  #prepares the image to be used by the model
  img_arr = preprocess(img)
  img_arr = np.array(img_arr).reshape((1,360,1440,1))

  #load the trained model
  loaded_model = joblib.load(os.path.abspath('finalized_model.sav'))

  #tests the model against an individual graph
  scores = loaded_model.predict(img_arr)
  max = 0

  #prints all label scores
  print(scores)

  #determines the best label to use
  for i in range(len(scores[0])):
    if(scores[0][i]>max):
      max = scores[0][i]
      label = CATEGORIES[i]

  #outputs the determined label
  return label
