package org.uclab.mm.icl.llc.AR.var;

import java.util.ArrayList;

public class KinectARFeatureExtraction {
	double [] inputData;
	double [] x,y,z;
	
	int[] siD = {2,3,3,2,2,2,2,3,3,3,3,3,4,8,10,13,17,18,2,2,2,2,2,3,4,4,7,8,8,8,9,11,14,20,20,0,0,0,0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5};
	int[] sjD = {17,10,17,10,13,14,18,11,13,14,18,23,13,17,20,20,20,20,7,11,21,23,24,24,11,17,20,10,18,23,18,20,20,23,24,1,2,3,4,8,18,20,2,3,14,18,20,6,16,19,20,6,7,12,15,16,21,22,6,7,10,14,16,18,20,21,23,24,13,14};
	int[] siA = {16,0,0,1,1,1,5,11,11,12,0,0,0,1,2,3,5,5,5,6,6,6,7,8,9,9,9,10,10,10,11,12,12,12,14,15,16,16,16,19,20,22,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4};
	int[] sjA = {23,11,23,11,23,24,10,12,16,23,13,21,24,10,12,17,11,23,24,11,23,24,23,11,10,11,23,12,16,23,23,13,21,24,16,16,20,21,24,20,23,23,1,2,3,4,7,10,15,20,22,2,14,15,20,5,14,15,18,11,12,13,14,15,16,18,19,11,23,24};
	
	// 	Call the function of data reconstruction
	public void setInputData(double[] inputData) {
		this.inputData = inputData;
		separateData();
	}
	
	public void setIndex(ArrayList<ArrayList<Integer>> listIndex) {
		for (int i = 0; i < 70; i++) {
			siD[i] = listIndex.get(0).get(i);
			sjD[i] = listIndex.get(1).get(i);
			siA[i] = listIndex.get(2).get(i);
			sjA[i] = listIndex.get(3).get(i);
		}
	}
	
	// Get the size of angle feature
	public int getAngleFeatureSize(){
		return siA.length;
	}
	
	// 	Get the size of angle feature
	public int getDistanceFeatureSize(){
		return siD.length;
	}
	/** Re-construct the data from DCL for processing
	 *	One vector data with 75 values of x,y, and z to three vectors with 25 values
	 *	25 values corresponding to 25 joints of each complete skeleton
	 */
	private void separateData(){
		x = new double[25];
		y = new double[25];
		z = new double[25];
		for (int i = 0;  i < 25; i++){
			x[i]=inputData[i*3];
			y[i]=inputData[i*3+1];
			z[i]=inputData[i*3+2];
		}
	} 
	
	/** Calculating a distance feature between joint i and j
	 *	This is the Euclidean distance
	 */
	private double distance3d(int i, int j){
		double d= 0;
		try{
			d=Math.sqrt(Math.pow(x[i]-x[j],2)+Math.pow(y[i]-y[j],2)+Math.pow(z[i]-z[j],2));			
		}catch(Exception e){
		}
		return d;
	}
	
	/**	Calculating an angle feature between joint i and j
	 *	This is the angle between vector ij and horizontal axis on the plane z=0
	 */
	private double angle3d(int i,int j){
		double deltax=x[j]-x[i];
        double deltay=y[j]-y[i];
        double a = Math.atan(deltay/deltax);
		//	Normalizing angle from degree to gradian
        if (deltay == 0)
                        a=0;
        else if (deltay > 0 && deltax > 0)
                        a=a/(2*Math.PI);
        else if (deltay > 0 && deltax < 0)
                        a=(a+Math.PI)/(2*Math.PI);
        else if (deltay <0 && deltax < 0)
                        a=(a+Math.PI)/(2*Math.PI);
        else if (deltay <0 && deltax > 0)
                        a=(a+2*Math.PI)/(2*Math.PI);
        return a;
	}
	
	/**	Calculating all distance features between 70 pre-identified joints
	 *	These joints are pre-identified based on the feature selection technique to only
	 *	calculate the most important features
	 */
	 public double[] extractDistanceFeature(){
		 int numFeatures = siD.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=distance3d(siD[i],sjD[i]);
		 }
		 return features;
	 }
	/**Calculating all angle features between 70 pre-identified joints
	 *	These joints are pre-identified based on the feature selection technique to only
	 *	calculate the most important features
	 */
	 public double[] extractAngleFeature(){
		 int numFeatures = siA.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=angle3d(siA[i],sjA[i]);
		 }
		 return features;
	 }

}