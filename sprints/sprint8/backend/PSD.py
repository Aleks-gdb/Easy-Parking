from scipy.io import wavfile # scipy library to read wav files
import numpy as np
import matplotlib.pyplot as plt
import os
from scipy.fftpack import fft # fourier transform

plt.figure(figsize=(20, 5))

def convertAndClassify(path):
    fs, Audiodata = wavfile.read(path)
    #tittle for the graph will be the name of the file.
    plt.title(path,size=16)
    #FFT
    n = len(Audiodata) 
    AudioFreq = fft(Audiodata)
    AudioFreq = AudioFreq[0:int(np.ceil((n+1)/2.0))] #Half of the spectrum
    MagFreq = np.abs(AudioFreq) # Magnitude
    MagFreq = MagFreq / float(n)
    
    # power spectrum
    MagFreq = MagFreq**2
    
    if n % 2 > 0: # ffte odd 
        MagFreq[1:len(MagFreq)] = MagFreq[1:len(MagFreq)] * 2
    else:# fft even
        MagFreq[1:len(MagFreq) -1] = MagFreq[1:len(MagFreq) - 1] * 2 

    freqAxis = np.arange(0,int(np.ceil((n+1)/2.0)), 1.0) * (fs / n)
    
    plt.plot(freqAxis/1000.0, 10*np.log10(MagFreq)) #Power spectrum
    plt.title(path)
    plt.xlabel('Frequency (kHz)'); plt.ylabel('Power spectrum (dB)')
    plt.savefig(path.rstrip(".wav") + '.png')
    plt.clf()
    return 'converted'