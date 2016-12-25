package org.uclab.mm.icl.llc.AER.jh;

import jAudioFeatureExtractor.AudioFeatures.LPC;
import jAudioFeatureExtractor.jAudioTools.AudioSamples;
import jAudioFeatureExtractor.jAudioTools.FFT;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class jAudioPreProcessing {
	private double sampling_rate;
	
	public double[] getPowerSpectrum(AudioInputStream ais) throws Exception {
		double[] samples = preProcessRecording(ais);
		FFT fft = new FFT(samples, null, false, true);
		double[] result = fft.getPowerSpectrum();
		return result;
	}

	public double[] getLPC(AudioInputStream ais) throws Exception {
		double[] samples = preProcessRecording(ais);
		LPC lpc = new LPC();
		double[] result = lpc.extractFeature(samples, sampling_rate, null);
		return result;
	}

	public double[] getMagnitudeSpectrum(AudioInputStream ais) throws Exception {
		double[] samples = preProcessRecording(ais);
		FFT fft = new FFT(samples, null, false, true);
		double[] result = fft.getMagnitudeSpectrum();
		return result;
	}
	public double[] getRealValue(AudioInputStream ais) throws Exception {
		double[] samples = preProcessRecording(ais);
		FFT fft = new FFT(samples, null, false, true);
		double[] result = fft.getRealValues();
		return result;
	}
	private double[] preProcessRecording(AudioInputStream ais) throws Exception {
		ais.reset();
		// Get the original audio and its format
		AudioInputStream original_stream = ais;
		AudioFormat original_format = original_stream.getFormat();
		sampling_rate = original_format.getSampleRate();

		// Set the bit depth
		int bit_depth = original_format.getSampleSizeInBits();
		if (bit_depth != 8 && bit_depth != 16)
			bit_depth = 16;

		// If the audio is not PCM signed big endian, then convert it to PCM
		// signed
		// This is particularly necessary when dealing with MP3s
		AudioInputStream second_stream = original_stream;
		if (original_format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED
				|| original_format.isBigEndian() == false) {
			AudioFormat new_format = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					original_format.getSampleRate(), bit_depth,
					original_format.getChannels(),
					original_format.getChannels() * (bit_depth / 8),
					original_format.getSampleRate(), true);
			second_stream = AudioSystem.getAudioInputStream(new_format,
					original_stream);
		}

		// Convert to the set sampling rate, if it is not already at this
		// sampling rate.
		// Also, convert to an appropriate bit depth if necessary.
		AudioInputStream new_stream = second_stream;
		if (original_format.getSampleRate() != (float) sampling_rate
				|| bit_depth != original_format.getSampleSizeInBits()) {
			AudioFormat new_format = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					original_format.getSampleRate(), bit_depth,
					original_format.getChannels(),
					original_format.getChannels() * (bit_depth / 8),
					original_format.getSampleRate(), true);
			new_stream = AudioSystem.getAudioInputStream(new_format,
					second_stream);
		}

		// Extract data from the AudioInputStream
		AudioSamples audio_data = new AudioSamples(new_stream,
				"unique", false);
		
		
		
		// Normalise samples if this option has been requested
		/*if (normalise)
			audio_data.normalizeMixedDownSamples();*/

		// Return all channels compressed into one
		return audio_data.getSamplesMixedDown();
		//return audio_data.normalizeMixedDownSamples();

	}

}
