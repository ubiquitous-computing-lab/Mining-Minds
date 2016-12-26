package org.uclab.mm.icl.llc.MachineLearningTools;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.llc.AER.jh.ConvertAudioData;

import mm.icl.llc.MachineLearningTools.Segmentation;

/**
 * This Class segment audio data based on seconds
 * 
 * @author Bang Gae
 *
 */
public class WindowBasedSegmentation extends
		Segmentation<AudioInputStream, AudioInputStream> {
	int threshold;
	int size;
	AudioFormat audioFM;
	ConvertAudioData cad = new ConvertAudioData();
	boolean segFlag = false;

	/**
	 * Set Window for Segmentation with sample rate and second
	 * 
	 * @param sec
	 * @param samplerate
	 */
	public WindowBasedSegmentation(int sec, int samplerate) {
		this.threshold = sec * samplerate;
	}

	/**
	 * Segment Audio Data based on setted second
	 * 
	 * @param input
	 * @return AudioInputStream
	 */

	@Override
	public AudioInputStream segmentation(AudioInputStream input) {
		// TODO Auto-generated method stub
		AudioInputStream ais = null;
		size = (int) (threshold * input.getFormat().getFrameSize());
		if (threshold <= input.getFrameLength()+22) {
			audioFM = input.getFormat();
			ais = cad.convertAudioInputStream(segmentData(input), audioFM);
			segFlag = true;
		}
		return ais;
	}

	public AudioInputStream[] divideInputStream(AudioInputStream input,
			int divideNumber) {
		AudioInputStream[] ais = new AudioInputStream[divideNumber];
		for (int i = 0; i < divideNumber; i++) {
			try {
				AudioFormat audioFM = input.getFormat();
				byte[] header = new byte[44];
				byte[] data = new byte[(int) (input.getFrameLength()*input.getFormat().getFrameSize()) / divideNumber];
				input.read(header);
				input.read(data);

				ais[i] = cad.convertAudioInputStream(data, audioFM);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ais;
	}

	/**
	 * Segment data by byte read
	 * 
	 * @param input
	 * @return byte[]
	 */

	public byte[] segmentData(AudioInputStream input) {
		byte[] header = new byte[44];
		byte[] temp = new byte[size];
		try {
			input.read(header);
			input.read(temp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * return remained data
	 * 
	 * @param input
	 * @return AudioInputStream
	 */

	public AudioInputStream returnRemainedData(AudioInputStream input) {
		if (segFlag) {
			byte[] remained = new byte[(int) (input.getFrameLength()
					* input.getFormat().getFrameSize() - size)];
			AudioInputStream ais = null;
			try {
				input.read(remained);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ais = cad.convertAudioInputStream(remained, audioFM);
			segFlag = false;
			return ais;
		}
		return input;
	}
}
