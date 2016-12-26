package org.uclab.mm.icl.llc.AER.vui;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.LLCManager.MajorityVoting;
import org.uclab.mm.icl.llc.LLCRecognizer.LLCRecognizer;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.utils.FileUtil;
import org.uclab.mm.icl.utils.TimeChecker;
import org.uclab.mm.icl.utils.TimeUtil;

import mm.icl.llc.MachineLearningTools.Classifications.WekaClassification;
import mm.icl.llc.MachineLearningTools.FeatureExtractions.TemporalFeatureExtraction;

/**
 *
 * @author lebavui
 */


/**
 * Data class for emotion recognizers
 * @author Nailbrainz
 *
 */
class EmotionData{
	public byte[] getData() {
		return data;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	byte[] data;
	String timeStamp;

	EmotionData(){}; //prohibited
	public EmotionData(byte[] data, String timeStamp){
		this.data = data;
		this.timeStamp = timeStamp;
	}
};

public class AudioEmotionRecognizerV extends LLCRecognizer<EmotionData>{
	public static String[] emoLabel = { "Anger", "Happiness", "Sadness", "NoEmotion" };
	private static final double SILENCE_THRESHOLD = 0.01; 
	
	private static final String DEFAULT_MODEL_FILE = FileUtil.getRootPath()+"//vuimodel/smo3emotions_new.model";
	
	long userID;
	String timeStamp;
	
	private String modelFile;
	
	private final static Logger logger = Logger.getLogger(AudioEmotionRecognizerV.class);
	
	//data from llcrawdatabuffer
	
	public AudioEmotionRecognizerV(long userID) {
		super(userID);
		modelFile = DEFAULT_MODEL_FILE;
	}
	
	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}
	
	@Override
    public ContextLabel recognize(EmotionData input, String timeStamp) {
    	System.err.println("Emo vui working");
    	try {
    		double[] samples = this.setAudioDataFromByteArray(input.getData());
    		
	    	double rmsEnergy = TemporalFeatureExtraction.computeRMSE(samples);
			if (rmsEnergy < SILENCE_THRESHOLD) {
				ContextLabel classifiedEmotion = new ContextLabel(userID, "NoEmotion", timeStamp, ContextType.Emotion);
				return classifiedEmotion;
			} else {
				AERFeatureExtraction fe = new AERFeatureExtraction();
				fe.setSamplingRate(44100);
				double[] features = fe.extractFeature(samples);
				
				WekaClassification classifier = new WekaClassification();
				classifier.loadModel(modelFile);
				int labelIndex = classifier.classify(features);
				
				ContextLabel classifiedEmotion = new ContextLabel(userID, emoLabel[labelIndex], timeStamp, ContextType.Emotion);
				long localTime = TimeChecker.time;
				if(verbose)logger.info("EmoVui " + (TimeUtil.getCurTime() - localTime));
				return classifiedEmotion;
			}
    	} catch (Exception ex) {
    		System.out.println(ex.toString() + "at emoV");
    		ex.printStackTrace();
    	}
    	return null;
    }

	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.ER;
	}
	
	RandomAccessFile rndF;
	public RandomAccessFile getrndFile(){
		return rndF;
	}
	public void stop_writing(int payloadSize) throws IOException {
		//randomAccessWriter.write(buffer);
		rndF.seek(4); // Write size to RIFF header
		rndF.writeInt(Integer.reverseBytes(36 + payloadSize));

		rndF.seek(40); // Write size to Subchunk2Size
										// field
		rndF.writeInt(Integer.reverseBytes(payloadSize));
		rndF.close();

	}
	public void prepare(String path) throws IOException {
		AudioFormat af =new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);
		rndF = new RandomAccessFile(path, "rw");
		rndF.setLength(0);
		rndF.writeBytes("RIFF");
		// Final file size not known yet, write 0
		rndF.writeInt(0);
		rndF.writeBytes("WAVE");
		rndF.writeBytes("fmt ");
		// Sub-chunk size, 16 for PCM
		rndF.writeInt(Integer.reverseBytes(16));
		// AudioFormat, 1 for PCM
		rndF.writeShort(Short.reverseBytes((short) 1));
		// Number of channels, 1 for mono, 2 for stereo
		rndF.writeShort(Short.reverseBytes((short) af.getChannels()));
		// Sample rate
		rndF.writeInt(Integer.reverseBytes((int) af.getSampleRate()));
		// Byte rate,
		// SampleRate*NumberOfChannels*BitsPerSample/8
		rndF.writeInt(Integer.reverseBytes((int) (af.getSampleRate() * af.getSampleSizeInBits()
				* af.getChannels() / 8)));
		// Block align, NumberOfChannels*BitsPerSample/8
		rndF.writeShort(Short.reverseBytes((short) (af.getChannels()
				* af.getSampleSizeInBits() / 8)));
		// Bits per sample
		rndF.writeShort(Short.reverseBytes((short) af.getSampleSizeInBits()));
		rndF.writeBytes("data");
		// Data chunk size not known yet, write 0
		rndF.writeInt(0);
		//buffer = new byte[framePeriod * bSamples / 8 * channel];
	}
	public double[] setAudioDataFromByteArray(byte[] data) {
		if (data.length <= 44)
    		return new double[0];
    	
    	int length = (data.length - 44) / 2;
    	double[] samples = new double[length];
    	for (int i = 0; i < length; i++)
    		samples[i] = ((data[44 + i * 2] & 0xFF) + data[44 + i * 2 + 1] * 256) / 32768.0;
    	return samples;
	}


	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		if(it.equals(DeviceType.SmartPhone)){
			SmartphoneData data = (SmartphoneData) obj;
			timeStamp = data.getTimeStamp();
			userID = data.getUserID();
			//emoJHinput = silentRemover.preprocessing(data); //Audio Input Stream for removing silent
	    	if(data.getAudioSensoryData() != null){
	    		EmotionData ed = new EmotionData(data.getAudioSensoryData(), data.getTimeStamp());
	    		setData(ed);
	    	}
			
		}
	}
	
	
	/*
	@Override
	public Runnable getRunnable(String timeStamp, LLCUnifier unifier) {
		// TODO Auto-generated method stub
		return new EmoVUIRunnable(unifier, this);
	}
	
	class EmoVUIRunnable implements Runnable{
		LLCUnifier unifier;
		AudioEmotionRecognizerV cla;
		private RandomAccessFile randomAccessWriter;
		
		public RandomAccessFile getRandomAccessWriter(){
			return randomAccessWriter;
		}
		AudioFormat af =new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);
		private int payloadSize = 0;
		
		public EmoVUIRunnable(LLCUnifier unifier, AudioEmotionRecognizerV cla){
			this.unifier = unifier;
			this.cla = cla;
		}
		
		public double[] setAudioDataFromByteArray(byte[] data) {
			if (data.length <= 44)
	    		return new double[0];
	    	
	    	int length = (data.length - 44) / 2;
	    	double[] samples = new double[length];
	    	for (int i = 0; i < length; i++)
	    		samples[i] = ((data[44 + i * 2] & 0xFF) + data[44 + i * 2 + 1] * 256) / 32768.0;
	    	return samples;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(cla.isThreasholdReached()){
				
				File file1 = new File("D:/Audio.wav");
				try {
					file1.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					prepare("D:/Audio.wav");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				payloadSize = input.getAudiodata().length;
				try {
					randomAccessWriter.write(input.getAudiodata());
					stop_writing();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				SmartphoneData sd = new SmartphoneData();
				
				sd.setSamples(setAudioDataFromByteArray(audiodata));
				ContextLabel result = null;
				try{
					result = cla.recognize(sd, timeStamp);
				}catch(Exception e){
					e.printStackTrace();
					System.out.println(e.toString());
				}
				audiodata = null;
				unifier.push(result, RecognizerType.ER);
				if(verbose)logger.info(userID + "\t" + result.getLabel() + "\t" + timeStamp);
			}else{
				if(verbose)logger.info(userID + "\t" + "NoEmotion" + "\t" + timeStamp);
				unifier.push(new ContextLabel(userID, "NoEmotion", timeStamp, ContextType.Emotion), RecognizerType.ER);
				
			}
				
				
			
		}
		public void prepare(String path) throws IOException {

			randomAccessWriter = new RandomAccessFile(path, "rw");
			randomAccessWriter.setLength(0);
			randomAccessWriter.writeBytes("RIFF");
			// Final file size not known yet, write 0
			randomAccessWriter.writeInt(0);
			randomAccessWriter.writeBytes("WAVE");
			randomAccessWriter.writeBytes("fmt ");
			// Sub-chunk size, 16 for PCM
			randomAccessWriter.writeInt(Integer.reverseBytes(16));
			// AudioFormat, 1 for PCM
			randomAccessWriter.writeShort(Short.reverseBytes((short) 1));
			// Number of channels, 1 for mono, 2 for stereo
			randomAccessWriter.writeShort(Short.reverseBytes((short) af.getChannels()));
			// Sample rate
			randomAccessWriter.writeInt(Integer.reverseBytes((int) af.getSampleRate()));
			// Byte rate,
			// SampleRate*NumberOfChannels*BitsPerSample/8
			randomAccessWriter.writeInt(Integer.reverseBytes((int) (af.getSampleRate() * af.getSampleSizeInBits()
					* af.getChannels() / 8)));
			// Block align, NumberOfChannels*BitsPerSample/8
			randomAccessWriter.writeShort(Short.reverseBytes((short) (af.getChannels()
					* af.getSampleSizeInBits() / 8)));
			// Bits per sample
			randomAccessWriter.writeShort(Short.reverseBytes((short) af.getSampleSizeInBits()));
			randomAccessWriter.writeBytes("data");
			// Data chunk size not known yet, write 0
			randomAccessWriter.writeInt(0);
			//buffer = new byte[framePeriod * bSamples / 8 * channel];

		}

		public void stop_writing() throws IOException {
			//randomAccessWriter.write(buffer);
			randomAccessWriter.seek(4); // Write size to RIFF header
			randomAccessWriter.writeInt(Integer.reverseBytes(36 + payloadSize));

			randomAccessWriter.seek(40); // Write size to Subchunk2Size
											// field
			randomAccessWriter.writeInt(Integer.reverseBytes(payloadSize));
			randomAccessWriter.close();

		}
	}*/

}
