package org.uclab.mm.icl.llc.AER.jh;
import comirva.audio.util.AudioPreProcessor;
import comirva.audio.util.MFCC;
import comirva.audio.util.PointList;
import comirva.audio.util.gmm.GaussianComponent;
import comirva.audio.util.gmm.GaussianMixture;
import comirva.audio.util.kmeans.KMeansClustering;
import comirva.audio.util.math.Matrix;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.IIOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TransMFCC {
	int fileNo=0;
	double[] win_size = new double[157];
	int count = 0;
	private PointList list;
	
	/**
	 * Extract MFCC feature to Vector<double[]>
	 * @param file
	 * @return double[][]
	 */
	

	public Vector<double[]> getMFCC(File file){
		
		Vector<double[]> featurevt = new Vector<double[]>();
		MFCC mf;
		AudioInputStream audioIn;
		AudioPreProcessor audioPre;
		try {
			audioIn = AudioSystem.getAudioInputStream(file);
			audioPre = new AudioPreProcessor(audioIn,audioIn.getFormat().getSampleRate());
			mf = new MFCC(audioPre.getSampleRate(),1024,13,true);
			featurevt = mf.process(audioPre);
			audioIn.close();
			return featurevt;
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return featurevt;
	}
	
	public Vector<double[]> getMFCC(AudioInputStream ais, int windowSize, int cepstral,boolean useFirstCoefficient ) throws IOException{
		Vector<double[]>featurevt = new Vector<double[]>();
		ais.reset();
		AudioPreProcessor audioPre;
		try{
			audioPre = new AudioPreProcessor(ais, ais.getFormat().getSampleRate());
			MFCC mf = new MFCC(audioPre.getSampleRate(),windowSize,cepstral,useFirstCoefficient);
			featurevt = mf.process(audioPre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return featurevt;
		
	}
	/**
	 * Transform MFCC to dobule[][] 
	 * @param doubles
	 * @return double[][]
	 */
	public double[][] getSingleFileFeatures(Vector<double[]> doubles) {
		double[][] result = new double[doubles.size()][doubles.get(0).length];
		for (int i = 0; i < doubles.size(); i++) {
			for (int j = 0; j < doubles.get(0).length; j++) {
					result[i][j] = doubles.get(i)[j];
				}
		}
		
	
		return result;
	}
	
}
