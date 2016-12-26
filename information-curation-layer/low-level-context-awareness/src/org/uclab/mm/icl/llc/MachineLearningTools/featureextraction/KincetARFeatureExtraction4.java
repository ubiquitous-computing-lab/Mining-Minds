package org.uclab.mm.icl.llc.MachineLearningTools.featureextraction;

import java.util.ArrayList;
import java.util.List;

public class KincetARFeatureExtraction4 {
	KincetARFeatureExtraction kf = new KincetARFeatureExtraction();
	
	double inputs[][];
	double mat[][] = new double[30][140];
	
	
	/**
	 * set input data to extract features for kinect activity recognition
	 * @param inputs input data to set
	 */
	public void setInputData(double[][] inputs) {
		this.inputs = inputs;
	}
	
	
	/**
	 * calculate features. This function must be called after calling setInputData()
	 */
	public void calcFeatures(){
		
		for(int i = 0; i<inputs.length; i++){
			kf.setInputData(inputs[i]);
			double[] dd = kf.extractDistanceFeature();
			double[] aa = kf.extractAngleFeature();
			for(int j = 0; j<dd.length; j++){
				mat[i][j] = dd[j];
			}
			for(int j = 0; j<dd.length; j++){
				mat[i][j+aa.length] = aa[j];
			}
		}
	}
	
	/**
	 * calculate mean of kinect data. This function must be called after setInputData()
	 * @return mean calculated mean of kinect data
	 */
	private double[] calcMean(){
		double[] ret = new double[mat[0].length*2];
		int size = mat.length;
		for(int i = 0; i<mat[0].length; i++){
			double summ = 0;
			for(int j = 0; j<mat.length; j++){
				summ += mat[j][i];
			}
			ret[i] = summ/size;
		}
		
		for(int i = 0; i<mat[0].length; i++){
			double sqSumm = 0;
			for(int j = 0; j<mat.length; j++){
				sqSumm += (mat[j][i]-ret[i])*(mat[j][i]-ret[i])/(size-1);
			}
			
			ret[mat[0].length+i] = Math.sqrt(sqSumm);
		}
		
		return ret;
	}
	
	/**
	 * extract features. this function must be called after calling setInputData() function.
	 * @return features for kinect activity recognition
	 */
	public double[] extractFeatures() {
		// Extract features for each frame
		calcFeatures();
		return calcMean();
	}
	
}
