package org.uclab.mm.icl.llc.MachineLearningTools;

import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.AER.jh.ConvertAudioData;
import org.uclab.mm.icl.llc.AER.jh.TransMFCC;
import org.uclab.mm.icl.llc.AER.jh.jAudioPreProcessing;

import mm.icl.llc.MachineLearningTools.Preprocessing;

/**
 * This Class remove silent from audio data
 * @author Bang Gae
 *
 */
public class SilentRemoverPreprocessing extends Preprocessing<SmartphoneData, AudioInputStream>  {

	AudioFormat audioFM;
	ConvertAudioData cad = new ConvertAudioData();
	TransMFCC tmfcc = new TransMFCC();
	int thresold = 0;
	int nonSpeechTime = 0;
	int speechTime = 0;
	boolean speechFlag = false;
	Vector<Integer> speechIndex = new Vector<Integer>();
	
	/**
	 * Set Audio Format with threshold
	 * @param encoding
	 * @param sampleRate
	 * @param sampleSizeInBits
	 * @param channels
	 * @param frameSize
	 * @param frameRate
	 * @param bigEndian
	 * @param thresold
	 */

	public SilentRemoverPreprocessing(AudioFormat.Encoding encoding, float sampleRate,
			int sampleSizeInBits, int channels, int frameSize, float frameRate,
			boolean bigEndian, int thresold) {
		audioFM = new AudioFormat(encoding, sampleRate, sampleSizeInBits,
				channels, frameSize, frameRate, bigEndian);
		this.thresold = thresold;
	}
	

	/**
	 * 
	 */
	@Override
	public AudioInputStream preprocessing(SmartphoneData input) {
		AudioInputStream preprocessedData = null;
		// TODO Auto-generated method stub
		if (input.getAudioSensoryData().length > 0) {
			AudioInputStream ais = cad.convertAudioInputStream(input
					.getAudioSensoryData(),audioFM);
			try {
				preprocessedData = audioFiltering(ais, 1024, thresold);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return preprocessedData;
	}

	
	/**
	 * Filter Audio without silent
	 * @param ais
	 * @param windowSize
	 * @param treshold
	 * @return AudioInputStream
	 * @throws IOException
	 */

	private AudioInputStream audioFiltering(AudioInputStream ais,
			int windowSize, int treshold) throws IOException {
		AudioInputStream preprocessedData = null;
		Vector<double[]> filter = tmfcc.getMFCC(ais, windowSize, 13, true);
		Vector<byte[]> buffer = new Vector<byte[]>();
		
		ais.reset();
		for (int i = 0; i < filter.size(); i++) {
			byte[] WINDOWSIZE = new byte[windowSize];
			ais.read(WINDOWSIZE);
			if (filter.get(i)[0] > treshold) {
				buffer.add(WINDOWSIZE);
				speechIndex.add(1);	
				
			}else{
				speechIndex.add(0);
			}
		}
		if (buffer.size() > 0) {
			byte[] accumulatedBuffer = convertByteBuffer(buffer);
			preprocessedData = cad.convertAudioInputStream(accumulatedBuffer, audioFM);
		}
		return preprocessedData;
	}
	
	/**
	 * Convert vector data to Byte 
	 * @param buffer
	 * @return byte[]
	 */

	private byte[] convertByteBuffer(Vector<byte[]> buffer) {
		byte[] data = new byte[buffer.size() * buffer.get(0).length];
		int cnt = 0;
		for (int i = 0; i < buffer.size(); i++) {
			for (int j = 0; j < buffer.get(0).length; j++) {
				data[cnt] = buffer.get(i)[j];
				cnt++;
			}
		}
		return data;
	}
	public Vector<Integer> getSpeechIndex(){
		Vector<Integer> copySpeechIndex = speechIndex;
		speechIndex.removeAllElements();
		return copySpeechIndex;
	}

}
