package org.uclab.mm.icl.llc.AER.jh;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.uclab.mm.icl.llc.MachineLearningTools.FeatureExtraction;
import org.uclab.mm.icl.llc.MachineLearningTools.FilterBankPreprocessing;
import org.uclab.mm.icl.llc.MachineLearningTools.TimeDomainFeatureExtraction;

/**
 * This Class extract MiningMind v2.5 Audio based ER Feature Extraction
 * 
 * @author Bang Gae
 *
 */
public class AERFeatureExtraction extends
		FeatureExtraction<AudioInputStream, double[]> {

	
	/**
	 * Extract the Audio features
	 */
	@Override
	public double[] extractFeature(AudioInputStream input) {
		// TODO Auto-generated method stub
		FilterBankPreprocessing fbp = new FilterBankPreprocessing();
		TimeDomainFeatureExtraction tdfe = new TimeDomainFeatureExtraction();
		double[][] mfccMatrix = null;
		double[] features = null;
		try {
			mfccMatrix = fbp.getMfcc(input);
			features = tdfe.getTimeDomainFeatures(mfccMatrix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return features;
	}

}
