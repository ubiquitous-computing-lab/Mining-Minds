package org.uclab.mm.icl.llc.AER.vui;

import java.util.Arrays;

import org.uclab.mm.icl.llc.MachineLearningTools.FeatureExtraction;

import mm.icl.llc.MachineLearningTools.FeatureExtractions.AudioFeatureExtraction;
import mm.icl.llc.MachineLearningTools.FeatureExtractions.StatisticalFunctions;
import mm.icl.llc.MachineLearningTools.FeatureExtractions.TemporalFeatureExtraction;
import mm.icl.llc.MachineLearningTools.Utilities.UtilityFunctions;

public class AERFeatureExtraction extends FeatureExtraction<double[], double[]> {
	private double samplingRate;
	
	private boolean hasIndex;
	private int labelIndex;
	
	/**
	 * 
	 */
	public AERFeatureExtraction() {
		this.hasIndex = false;
		this.labelIndex = -1;
		this.samplingRate = 8000;
	}
	
	/**
	 * 
	 * @param labelIndex
	 */
	public AERFeatureExtraction(int labelIndex) {
		if (labelIndex > -1)
			this.hasIndex = true;
		else
			this.hasIndex = false;
		
		this.labelIndex = labelIndex;
		this.samplingRate = 8000;
	}
	
	/**
	 * 
	 */
	public double[] extractFeature(double[] samples) {
		// 1. Split frames
		double[][] frames = splitFrames(samples);
		
		int numFrames = frames.length;
		int numFeatures = 14;
		
		double[][] matrix = new double[numFrames][numFeatures];
		
		// 2. Extract features
		// a. MFCC
		// b. Zero Crossing
		// c. Power Spectrum 
		// d. Concatenate into 1 vector
		
		for (int i = 0; i < numFrames; i++) {
			double[] frame = frames[i];
			double[] mfcc = AudioFeatureExtraction.extractMFCC(frame, samplingRate);
			double[] zeroCrossing = TemporalFeatureExtraction.extractZeroCrossings(samples, samplingRate);
			
			System.arraycopy(mfcc, 0, matrix[i], 0, mfcc.length);
			System.arraycopy(zeroCrossing, 0, matrix[i], mfcc.length, zeroCrossing.length);
		}
		
		// 3. Compute Delta
		double[][] matrixDelta = computeDelta(matrix); // 14 * 2 = 28 features
		
		// 4. Compute Mean, Standard Deviation, Skewness, Kurtosis
		double[] mean = StatisticalFunctions.computeMean2D(matrixDelta);
		double[] std = StatisticalFunctions.computeStd2D(matrixDelta, mean);
		// double[] skewness = StatisticalFunctions.computeSkewness2D(matrix, mean, std);
		// double[] kurtosis = StatisticalFunctions.computeKurtosis2D(matrix, mean, std);
		
		// Total features 28 * 2 = 54 features
		
		// Concatenate all features to a vector
		double[] features = UtilityFunctions.concat(mean, std);
		
		if (hasIndex) {
			features  = Arrays.copyOf(features, features.length + 1);
			features[features.length - 1] = labelIndex;
		}
		
		return features;
	}
	
	/**
	 * 
	 * @param samplingRate
	 */
	public void setSamplingRate(double samplingRate) {
		this.samplingRate = samplingRate;
	}
	
	/**
	 * @param samples
	 * @return
	 */
	protected double[][] splitFrames(double[] samples) {
		// Window Size: 100 ms
		// Overlap 50ms
		int windowSizeInMs = 200;
		int overlapInMs = 50;
		
		int windowSize = (int)(windowSizeInMs * samplingRate / 1000);
		int overlap = (int)(overlapInMs * samplingRate / 1000);
		
		int numFrames = (int)Math.ceil(samples.length / (windowSize - overlap));
		
		double[][] frames = new double[numFrames][windowSize];
		
		for (int i = 0; i < numFrames; i++)
			for (int j = 0; j < windowSize; j++)
				if (i * (windowSize - overlap) + j < samples.length)
					frames[i][j] = samples[i * (windowSize - overlap) + j];
				else
					frames[i][j] = 0;
		
		return frames;
	}
	
	/**
	 * @param matrix
	 * @return
	 */
	protected double[][] computeDelta(double[][] matrix) {
		int numFeatures = matrix[0].length;
		int numFrames = matrix.length;
		
		double[][] newMatrix = new double[numFrames][numFeatures * 2];
		
		for (int i = 0; i < numFeatures * 2; i++)
			for (int j = 0; j < numFrames; j++)
				newMatrix[j][i] = 0;
		
		for (int i = 0; i < numFeatures; i++)
			for (int j = 0; j < numFrames; j++)
				newMatrix[j][i] = matrix[j][i];
		
		for (int i = 0; i < numFeatures; i++)
			for (int j = 0; j < numFrames - 1; j++)
					newMatrix[j][numFeatures + i] = matrix[j+1][i] - matrix[j][i];
		
		return newMatrix;
	}
	
}
