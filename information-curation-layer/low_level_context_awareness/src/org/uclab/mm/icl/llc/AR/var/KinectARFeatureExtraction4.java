package org.uclab.mm.icl.llc.AR.var;

import java.util.ArrayList;

public class KinectARFeatureExtraction4 {
	KinectARFeatureExtraction kf = new KinectARFeatureExtraction();
	
	/**	initial variable 
	 *	input 	: the matrix containing skeleton data within 3 seconds 90x75			
	 *	mat 	: the matrix containing feature data of distance and angle values 90x140  	
	 */
	double inputs[][];
	double mat[][] = new double[30][140];
	
	
	/**
	 * set input data to extract features for kinect activity recognition
	 * @param inputs input data to set
	 */
	public void setInputData(double[][] inputs) {
		this.inputs = inputs;
	}
	
	public void setIndex(ArrayList<ArrayList<Integer>> listIndex) {
		kf.setIndex(listIndex);
	}
	
	/**
	 * calculate features. This function must be called after calling setInputData()
	 * arrange each skeleton data of each body frame into matrix of data
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
	
	/**	Calculating the temporal features 1x280
	 * 	Mean of distance and angle features in the temporal dimension 
	 * 	Standard deviation of distance and angle features in the temporal dimension
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
