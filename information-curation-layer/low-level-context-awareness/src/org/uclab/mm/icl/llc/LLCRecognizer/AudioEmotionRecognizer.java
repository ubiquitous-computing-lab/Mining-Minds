/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.LLCRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat.Encoding;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.AER.jh.AudioBufferManagement;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.LLCManager.MajorityVoting;
import org.uclab.mm.icl.llc.MachineLearningTools.ExtClassification;
import org.uclab.mm.icl.llc.MachineLearningTools.FilterBankPreprocessing;
import org.uclab.mm.icl.llc.MachineLearningTools.SilentRemoverPreprocessing;
import org.uclab.mm.icl.llc.MachineLearningTools.TimeDomainFeatureExtraction;
import org.uclab.mm.icl.llc.MachineLearningTools.WindowBasedSegmentation;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;

/**
 * This class recognizes filters the audio data based on the frequency, and
 * recognizes user emotion from the filtered audio data.
 * 
 * @author Jae Hun, Bang
 */


class EmotionDataJH{
	
	AudioInputStream ais;
	String timeStamp;
	
	@SuppressWarnings("unused")
	private EmotionDataJH(){}
	
	public EmotionDataJH(AudioInputStream ais, String timeStamp) {
		super();
		this.ais = ais;
		this.timeStamp = timeStamp;
	}
	public AudioInputStream getAIS() {
		return ais;
	}
	public void setAIS(AudioInputStream ss) {
		this.ais = ss;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}

public class AudioEmotionRecognizer extends LLCRecognizer<EmotionDataJH> {
	private final static Logger logger = Logger.getLogger(AudioEmotionRecognizer.class);
	public static String[] emoLabel = { "Anger", "Happiness", "Sadness", "NoEmotion" };
	String timeStamp;

	Long userID;

	AudioInputStream buffer = null; // Audio Buffer
	AudioInputStream inputData = null; // Audio Buffer
	AudioBufferManagement abm = null; // Supporting Class for accumulating Audio
										// Buffer
	TimeDomainFeatureExtraction fe = new TimeDomainFeatureExtraction();
	SilentRemoverPreprocessing silentRemover = new SilentRemoverPreprocessing(Encoding.PCM_SIGNED, 44100, 16, 1, 2,
			44100, false, 5); // set Silent Remover Option
	FilterBankPreprocessing fbp = new FilterBankPreprocessing();
	WindowBasedSegmentation aers = new WindowBasedSegmentation(3, 44100);
	ExtClassification aerc = null;
	String path;

	public AudioEmotionRecognizer(ExtClassification classifier, String filepath, long userID) {
		super(userID);
		aerc = classifier;
		
		this.path = filepath;
	}

	// data from llcrawdatabuffer

	@Override
	public ContextLabel recognize(EmotionDataJH jh, String timeStamp) {
		ContextLabel emotion = ContextType.Emotion.getBasicLabel(userID, timeStamp);
		try {
			double[][] mfccPreprocessing = null;
			AudioInputStream[] devideSegmented = aers.divideInputStream(jh.getAIS(), 3);
			Vector<double[]> featureVector = new Vector<double[]>();

			for (int k = 0; k < devideSegmented.length; k++) {

				try {
					mfccPreprocessing = fbp.getMfcc(devideSegmented[k]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
				double[] featureVT = fe.getTimeDomainFeatures(mfccPreprocessing);
				featureVector.add(featureVT);
			}

			double[] timeDomainFeatureVt = fe.convertVector(featureVector);

			if(emoLabel[aerc.classify(timeDomainFeatureVt)] != null)emotion.setLabel(emoLabel[aerc.classify(timeDomainFeatureVt)]);

			if(verbose)logger.info("EMOJH individual output : " + userID + "\t" + emotion.getLabel() + "\t" + timeStamp);


			return emotion;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public AudioEmotionRecognizer(long userID) {
		super(userID);
	}

	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.SER;
	}

	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		if (it.equals(DeviceType.SmartPhone)) {
			SmartphoneData data = (SmartphoneData) obj;
			timeStamp = data.getTimeStamp();
			userID = data.getUserID();
			// emoJHinput = silentRemover.preprocessing(data); //Audio Input
			// Stream for removing silent
			if (data.getAudioSensoryData() != null) {
				AudioInputStream preprocessed = silentRemover.preprocessing(data); // Audio Input Stream for removing silent

				if (preprocessed != null) {
					abm = new AudioBufferManagement(preprocessed.getFormat()); // AudioBufferManagement for accumulating Audio Buffer

					buffer = abm.accumulateData(buffer, preprocessed);
					if (buffer != null) {
						//inputData = aers.segmentation(buffer);
						if(aers.segmentation(buffer) != null)
							setData(new EmotionDataJH(aers.segmentation(buffer), timeStamp));
						buffer = aers.returnRemainedData(buffer);
					}
				}
			}

		}
	}

	
/*
	@Override
	public Runnable getRunnable(String timeStamp, LLCUnifier unifier) {
		// TODO Auto-generated method stub
		return new EmoJHRunnable(unifier, this);
	}

	class EmoJHRunnable implements Runnable {
		LLCUnifier unifier;
		AudioEmotionRecognizer cla;

		public EmoJHRunnable(LLCUnifier unifier, AudioEmotionRecognizer cla) {
			this.unifier = unifier;
			this.cla = cla;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (cla.isThreasholdReached())
				unifier.push(cla.recognize(inputData, timeStamp), RecognizerType.SER);
			else {
				if(verbose)logger.info(userID + "\t" + "NoEmotion" + "\t" + timeStamp);
				unifier.push(new ContextLabel(userID, "NoEmotion", timeStamp, ContextType.Emotion), RecognizerType.SER);
			}
		}

	}
*/
}
