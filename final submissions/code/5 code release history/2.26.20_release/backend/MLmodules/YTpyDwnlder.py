import pafy
import time
from playsound import playsound as ps

link = input("Insert youtube link here: ")
video = pafy.new(link)

# Simple metadata retrieval:
# Printing the title, rating, viewcount, author,
# video length, duration, likes, dislikes, &
# description
print("Metadata: ")
print(video.title)
minutes, seconds = divmod(video.length, 60)
print(video.author, minutes, "minutes", seconds, "seconds")
# Deprecated
#print(video.duration, video.likes, video.dislikes, video.description)

# Deprecated
# Gets all the stream types (1280, 640, etc.)
#print("Stream types:")
#streams = video.streams
#for s in streams:
    #print(s)


# Downloading audio
audiostream = video.getbestaudio()
# audiostream.download()

def replaceInvalidWinChars(string):
    string = string.replace('"', '#')
    string = string.replace('?', '#')
    string = string.replace('|', '#')
    string = string.replace('>', '#')
    string = string.replace('<', '#')
    string = string.replace(':', '#')
    string = string.replace('*', '#')
    return string

from tkinter import filedialog as fd

# Asking where to save it
print("Select the directory...")
dir_name = fd.askdirectory()
print(dir_name)
title = replaceInvalidWinChars(video.title)
path = dir_name + "/" + video.title + ".wav"
print(path)
audiofile = audiostream.download(filepath=path)

#import IPython.display as ipd

# Playing back the audio
#print("Playing back audio...")
#ipd.Audio(filename=path)

import librosa
import librosa.display as ld
import matplotlib.pyplot as plt

# Visualization
print('Loading data...')
x, sr = librosa.load(path)
plt.figure(figsize=(14, 5))
ld.waveplot(x, sr=sr)

print('Displaying data...')
X = librosa.stft(x)
Xdb = librosa.amplitude_to_db(abs(X))
plt.figure(figsize=(14, 5))
librosa.display.specshow(Xdb, sr=sr, x_axis='time', y_axis='hz')
plt.colorbar()
