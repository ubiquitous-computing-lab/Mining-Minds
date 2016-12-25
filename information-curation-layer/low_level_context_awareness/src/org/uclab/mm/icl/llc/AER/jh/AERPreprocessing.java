package org.uclab.mm.icl.llc.AER.jh;

import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.MachineLearningTools.Preprocessing;
import org.uclab.mm.icl.llc.MachineLearningTools.SilentRemoverPreprocessing;


public class AERPreprocessing extends
		Preprocessing<SmartphoneData, AudioInputStream> {
	SilentRemoverPreprocessing srp;

	public AERPreprocessing(AudioFormat.Encoding encoding, float sampleRate,
			int sampleSizeInBits, int channels, int frameSize, float frameRate,
			boolean bigEndian,int thresold) {
		 srp = new SilentRemoverPreprocessing(encoding, sampleRate,
					 sampleSizeInBits,  channels,  frameSize,  frameRate,
					 bigEndian, thresold);
	}
	
	@Override
	public AudioInputStream preprocessing(SmartphoneData input) {
		// TODO Auto-generated method stub
		AudioInputStream ais =srp.preprocessing(input); 
		return ais;
	}

	

}
