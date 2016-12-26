package org.uclab.mm.icl.llc.MachineLearningTools;

import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.llc.AER.jh.ConvertAudioData;

import mm.icl.llc.MachineLearningTools.Segmentation;


/**
 * This class segments the audio data, based on the event (when there is no voice, this class filters that void period)
 * @author Jae Hun, Bang
 *
 */
public class EventBasedSegmentation extends
		Segmentation<AudioInputStream, AudioInputStream[]> {

	AudioFormat audioFM;
	ConvertAudioData cad = new ConvertAudioData();
	int nonSpeechTime = 0;
	int speechTime = 0;
	boolean segFlag = false;
	Vector<Integer> speechIndex = new Vector<Integer>();
	int segmentNumber = 0;
	double segmentSec = 0.4;
	Vector<Vector<byte[]>> segment;
	int remainedNumber =0;

	@Override
	public AudioInputStream[] segmentation(AudioInputStream origin_input) {
		// TODO Auto-generated method stub

		audioFM = origin_input.getFormat();
		segment = segmentData(origin_input);
		AudioInputStream[] ais = new AudioInputStream[segment.size()];
		if (segment.size() > 0) {
			for (int i = 0; i < segment.size(); i++) {
				byte[] buffer = cad.convertByteBuffer(segment.get(i));
				ais[i] = cad.convertAudioInputStream(buffer, audioFM);
			}
			segFlag = true;
		}
		segFlag =false;

		return ais;
	}

	public void setSpeechIndex(Vector<Integer> speechIndex) {
		this.speechIndex = speechIndex;
	}

	public void setSegmentOption(double sec) {
		segmentSec = sec;
	}
	
	
/**
 * Segmenting the data
 * @param input input audio stream data to segment
 * @return segmented audio data
 */
	public Vector<Vector<byte[]>> segmentData(AudioInputStream input) {
		Vector<byte[]> buffer = new Vector<byte[]>();
		Vector<Vector<byte[]>> returnValue = new Vector<Vector<byte[]>>();
		byte[] temp = new byte[1024];

		try {
			for (int i = 0; i < speechIndex.size(); i++) {
				if (speechIndex.get(i) == 0) {
					input.read(temp);
					nonSpeechTime++;
				} else {
					input.read(temp);
					buffer.add(temp);
					speechTime++;
					nonSpeechTime = 0;
				}
				if (speechTime > 34 && nonSpeechTime == 43) {
					Vector<byte[]> temp_vector = new Vector<byte[]>();
					for (int l = 0; l < buffer.size(); l++) {
						temp_vector.add(buffer.get(l));
					}
					buffer.removeAllElements();
					returnValue.add(temp_vector);
					speechTime = 0;
					nonSpeechTime = 0;
					remainedNumber = i;
										
				} else if (nonSpeechTime > 43) {
					buffer.removeAllElements();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	

	/**
	 * return remained data
	 * 
	 * @param input
	 * @return AudioInputStream
	 */

	public AudioInputStream returnRemainedData(AudioInputStream input) {
		if (segFlag) {
			byte[] remained = new byte[(speechIndex.size()-remainedNumber)*1024];
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
