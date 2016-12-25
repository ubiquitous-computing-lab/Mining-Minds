package org.uclab.mm.icl.llc.MachineLearningTools;

import java.util.Vector;

import javax.sound.sampled.UnsupportedAudioFileException;

import mm.icl.llc.MachineLearningTools.FeatureExtraction;


/**
 * This Class extract time-domain features based on seconds
 * 
 * @author Bang Gae
 *
 */
public class TimeDomainFeatureExtraction extends FeatureExtraction {
	
	/**
	 * Extract Time Domain Feature based on MFCC Filterbank (unit of second)
	 * 
	 * @param mfcc_matrix
	 * @param sec
	 * @return double[][]
	 * @throws UnsupportedAudioFileException
	 */

	public double[][] getTimeDomainMFCC(double[][] mfcc_matrix, int sec)
			throws UnsupportedAudioFileException {

		double[][] bind_mfcc_matrix = new double[sec][(mfcc_matrix[0].length - 1) * 2];
		int size = mfcc_matrix.length / sec;
		double[] sum = new double[mfcc_matrix[0].length];

		double diff = 0;
		double temp_sum = 0;
		double mean = 0;
		int cnt = 0;
		double result = 0;
		for (int i = 0; i < mfcc_matrix.length; i++) {
			for (int j = 0; j < mfcc_matrix[0].length - 1; j++) {
				sum[j] = sum[j] + mfcc_matrix[i][j + 1];

				if ((i + 1) % size == 0) {
					mean = sum[j] / (double) size;
					for (int k = 0; k < i; k++) {
						diff = mfcc_matrix[k][j + 1] - mean;
						temp_sum += diff * diff;
					}
					result = Math.sqrt(temp_sum / (double) size);
					bind_mfcc_matrix[cnt][j] = mean;
					bind_mfcc_matrix[cnt][j + mfcc_matrix[i].length - 1] = result;
					sum[j] = 0;
					temp_sum = 0;
				}

			}

			if ((i + 1) % size == 0) {

				cnt++;
			}

		}

		return bind_mfcc_matrix;
	}
	public double[] getTimeDomainFeatures(double[][] data){
		double[][] reverseData = reverse2ArraysData(data);
		StatisticalFeatureExtraction sfe = new StatisticalFeatureExtraction();
		double[] mfccMeans = new double[data[0].length-1];
		double[] mfccStdDev = new double[data[0].length-1];
		double[] mfccMax = new double[data[0].length-1];
		double[] mfccMin = new double[data[0].length-1];
		//double[] mfccMedian = new double[data[0].length-1];
		double[] featurevt = new double[mfccMeans.length*4];
	
		for(int i =0; i< reverseData.length-1; i++){
			mfccMeans[i] = sfe.getMean(reverseData[i+1]);
			mfccStdDev[i] = sfe.getStdDev(reverseData[i+1]);
			mfccMax[i] = sfe.getMax(reverseData[i+1]);
			mfccMin[i] = sfe.getMin(reverseData[i+1]);
			//mfccMedian[i] = sfe.getMedian(reverseData[i+1]);
			featurevt[i] = mfccMeans[i];
			featurevt[mfccMeans.length + i] = mfccStdDev[i];
			featurevt[mfccMeans.length*2 + i] = mfccMax[i];
			featurevt[mfccMeans.length*3 + i] = mfccMin[i];	
			//featurevt[mfccMeans.length*4 + i] = mfccMedian[i];		
		}
		
		
		return featurevt;
	}
	public double[][] reverse2ArraysData(double[][] data){
		double[][] result = new double[data[0].length][data.length];
		for(int i =0; i<data.length; i++){
			for(int j=0; j<data[i].length; j++){
				result[j][i] = data[i][j];
			}
		}
		return result;
	}

	/**
	 * Convert Single Array
	 * 
	 * @param data
	 * @return double[][]
	 */

	public double[] getSigleArray(double[][] data) {
		double[] result = new double[data.length * data[0].length];
		int cnt = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				result[cnt] = data[i][j];
				cnt++;
			}
		}
		return result;
	}

	/**
	 * No Define
	 */
	@Override
	public Object extractFeature(Object input) {
		// TODO Auto-generated method stub
		return null;
	}
	public double[] convertVector(Vector<double[]> input){
		int size =0;
		for(int i=0; i<input.size(); i++){
			for(int j=0;j<input.get(i).length; j++){
				size++;
			}
		}
		
		double[] result = new double[size];
		int cnt =0;
		for(int i =0; i<input.size(); i++){
			for(int j=0; j<input.get(i).length;j++){
				result[cnt] = input.get(i)[j];
				cnt++;
			}
		}
		return result;
	}
	
	

}
