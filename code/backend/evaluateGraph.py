import joblib
import numpy as np
from PIL import Image
import os
CATEGORIES = ["Guitar", "Trumpet", "Violin"]

def preprocess(img):
  pil_im = Image.open(img)
  gray_im = pil_im.convert('LA')
  std_size = (360, 1440)
  gray_im = gray_im.resize(std_size)
  img_arr = np.array(gray_im)
  img_arr = np.true_divide(img_arr, 255.0)
  return img_arr[:,:,[0]]

def evaluateGraph(img):
  #prepares the image to be used by the model
  img_arr = preprocess(img)
  img_arr = np.array(img_arr).reshape((1,360,1440,1))
  #load the trained model
  #loaded_model = joblib.load(os.path.abspath('finalized_model.sav'))
  loaded_model = joblib.load('C:\\Users\\aleks\\Documents\\GitHub\\Sound-Recognizer\\sprints\\sprint8\\backend\\finalized_model.sav')

  #tests the model against an individual graph
  #Image.fromarray(np.uint8(X_test[0]*255))
  scores = loaded_model.predict(img_arr)
  max = 0

  print(scores)

  for i in range(len(scores[0])):
    if(scores[0][i]>max):
      max = scores[0][i]
      label = CATEGORIES[i]

  #outputs the determined label
  return label
