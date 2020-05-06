import joblib
import numpy as np
from PIL import Image
import _pickle as pickle
from keras.utils import to_categorical

CATEGORIES = ["Guitar","Trumpet", "Violin"]

# X_test = pickle.load(open("X_test.pickle","rb"))
# y_test = pickle.load(open("y_test.pickle","rb"))
# X_test = np.array(X_test)
# y_test_bin = to_categorical(y_test)

def preprocess(img):
  pil_im = Image.open(img)
  gray_im = pil_im.convert('LA')
  std_size = (360, 1440)
  gray_im = gray_im.resize(std_size)
  img_arr = np.array(gray_im)
  img_arr = np.reshape(img_arr, [360,1440,2])
  img_arr = np.true_divide(img_arr, 255.0)
  return img_arr[:,:,[0]]

def evaluateGraph(img):
  #prepares the image to be used by the model
  img_arr = preprocess(img)
  img_arr = np.array(img_arr).reshape((1,360,1440,1))

  #load the trained model
  loaded_model = joblib.load('finalized_model.sav')

  #tests the model against an individual graph
  scores = loaded_model.predict(img_arr)
  max = 0

  print(X_test[0].shape)

  test_results = loaded_model.evaluate(X_test, y_test_bin, verbose=1)
  print(f'Test results - Loss: {test_results[0]} - Accuracy: {test_results[1]*100}%')

  print(scores)

  for i in range(len(scores[0])):
    if(scores[0][i]>max):
      max = scores[0][i]
      label = CATEGORIES[i]

  #outputs the determined label
  return label
