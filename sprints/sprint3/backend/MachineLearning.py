import keras
from keras.datasets import mnist
from keras import backend as k
from keras import layers
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense, Conv2D, MaxPooling2D, Dropout, Flatten
import os

def mLearning(num):
   (X_train, y_train), (X_test, y_test) = mnist.load_data()
   fig = plt.figure()
   for i in range(9):
      plt.subplot(3,3,i+1)
      plt.tight_layout()
      plt.imshow(X_train[i], cmap='gray', interpolation='none')
      plt.title("Digit: {}".format(y_train[i]))
      plt.xticks([])
      plt.yticks([])
      fig.show()

   img_rows, img_cols = 28, 28

   if k.image_data_format() == 'channels_first':
      X_train = X_train.reshape(X_train.shape[0], 1, img_rows, img_cols)
      X_test = X_test.reshape(X_test.shape[0], 1, img_rows, img_cols)
      input_shape = (1, img_rows, img_cols)
   else:
      X_train = X_train.reshape(X_train.shape[0], img_rows, img_cols, 1)
      X_test = X_test.reshape(X_test.shape[0], img_rows, img_cols, 1)
      input_shape = (img_rows, img_cols, 1)

   X_train = X_train.astype('float32')
   X_test = X_test.astype('float32')
   X_train /= 255
   X_test /= 255
   print('X_train shape:', X_train.shape)

   num_category = 10

   y_train = keras.utils.to_categorical(y_train, num_category)
   y_test = keras.utils.to_categorical(y_test, num_category)

   model = Sequential()

   model.add(Conv2D(32, kernel_size=(3, 3),
                  activation='relu',
                  input_shape=input_shape))

   model.add(Conv2D(64, (3, 3), activation='relu'))
   model.add(MaxPooling2D(pool_size=(2, 2)))
   model.add(Dropout(0.25))
   model.add(Flatten())
   model.add(Dense(128, activation='relu'))
   model.add(Dropout(0.5))
   model.add(Dense(num_category, activation='softmax'))

   model.compile(loss=keras.losses.categorical_crossentropy,
               optimizer=keras.optimizers.Adadelta(),
               metrics=['accuracy'])

   batch_size = 128
   num_epoch = num #number of iterations over the dataset
   model_log = model.fit(X_train, y_train,
            batch_size=batch_size,
            epochs=num_epoch,
            verbose=1,
            validation_data=(X_test, y_test))

   score = model.evaluate(X_test, y_test, verbose=0)
   print('Test loss:', score[0])
   print('Test accuracy:', score[1])

   fig = plt.figure()
   plt.subplot(2,1,1)
   plt.plot(model_log.history['accuracy'])
   plt.plot(model_log.history['val_accuracy'])
   plt.title('model accuracy')
   plt.ylabel('accuracy')
   plt.xlabel('epoch')
   plt.legend(['train', 'test'], loc='lower right')
   plt.subplot(2,1,2)
   plt.plot(model_log.history['loss'])
   plt.plot(model_log.history['val_loss'])
   plt.title('model loss')
   plt.ylabel('loss')
   plt.xlabel('epoch')
   plt.legend(['train', 'test'], loc='upper right')
   plt.tight_layout()
   fig.show()
