package org.uclab.mm.icl.llc.MachineLearningTools.featureextraction;


public class KincetARFeatureExtraction {
	double [] inputData;
	double [] x,y,z;
	
	
	int[] siD = {2,3,3,2,2,2,2,3,3,3,3,3,4,8,10,13,17,18,2,2,2,2,2,3,4,4,7,8,8,8,9,11,14,20,20,0,0,0,0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5};
	int[] sjD = {17,10,17,10,13,14,18,11,13,14,18,23,13,17,20,20,20,20,7,11,21,23,24,24,11,17,20,10,18,23,18,20,20,23,24,1,2,3,4,8,18,20,2,3,14,18,20,6,16,19,20,6,7,12,15,16,21,22,6,7,10,14,16,18,20,21,23,24,13,14};
	int[] siA = {16,0,0,1,1,1,5,11,11,12,0,0,0,1,2,3,5,5,5,6,6,6,7,8,9,9,9,10,10,10,11,12,12,12,14,15,16,16,16,19,20,22,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4};
	int[] sjA = {23,11,23,11,23,24,10,12,16,23,13,21,24,10,12,17,11,23,24,11,23,24,23,11,10,11,23,12,16,23,23,13,21,24,16,16,20,21,24,20,23,23,1,2,3,4,7,10,15,20,22,2,14,15,20,5,14,15,18,11,12,13,14,15,16,18,19,11,23,24};
	/*
	int[] siD = {3,2,2,2,3,3,3,3,3,8,17,0,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,5,5,6,6,8,8,8,8,8,8,9,9,9,9,10,11,12,13,13,14,16,18,19,20,20,0,0,0,0,0,0,1,1,2,2};
	int[] sjD = {10,6,7,17,7,11,17,23,24,17,20,1,10,18,10,11,13,14,15,18,19,21,23,24,6,13,14,15,18,19,21,22,10,13,17,13,14,12,20,13,14,18,19,21,23,14,17,18,19,20,20,21,17,20,20,20,20,20,21,23,2,4,7,12,20,22,2,14,4,5};
	int[] siA = {2,0,2,2,3,3,5,5,11,11,12,12,14,17,0,0,0,0,1,1,2,2,2,2,2,3,3,3,3,3,3,3,3,3,5,6,8,9,9,10,10,10,12,12,15,15,16,18,19,19,19,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2};
	int[] sjA = {12,23,14,15,5,15,13,17,12,16,13,20,20,20,1,3,8,13,3,8,4,5,6,18,19,4,6,12,14,16,17,18,19,22,12,22,18,10,23,11,12,23,21,23,20,24,23,20,20,22,24,2,4,7,9,11,20,21,22,2,4,6,7,10,12,14,15,20,21,7};
	*/
	public void setInputData(double[] inputData){
		this.inputData = inputData;
		separateData();
	}
	public int getAngleFeatureSize(){
		return siA.length;
	}
	public int getDistanceFeatureSize(){
		return siD.length;
	}
	private void separateData(){
		x = new double[25];
		y = new double[25];
		z = new double[25];
		for (int i = 0;  i < 25; i++){
			x[i]=inputData[i*3];
			y[i]=inputData[i*3+1];
			z[i]=inputData[i*3+2];
		}
		/*
		System.out.print("X = ");
		for (int i = 0;  i < 25; i++){
			System.out.print(x[i] + "  ");
		}
		System.out.println("");
		
		System.out.print("Y = ");
		for (int i = 0;  i < 25; i++){
			System.out.print(y[i] + "  ");
		}
		System.out.println("");
		
		System.out.print("Z = ");
		for (int i = 0;  i < 25; i++){
			System.out.print(z[i] + "  ");
		}
		System.out.println("");*/
	} 
	private double distance3d(int i, int j){
		double d= 0;
		try{
			d=Math.sqrt(Math.pow(x[i]-x[j],2)+Math.pow(y[i]-y[j],2)+Math.pow(z[i]-z[j],2));
			
		}catch(Exception e){
			//System.out.println("i = "+i + ", j = " +j);
		}
		return d;
	}
	
	private double angle3d(int i,int j){
		double deltax=x[j]-x[i];
        double deltay=y[j]-y[i];
        double a = Math.atan(deltay/deltax);
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
	
	 public double[] extractDistanceFeature(){
		 int numFeatures = siD.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=distance3d(siD[i],sjD[i]);
		 }
		 return features;
	 }
	 
	 public double[] extractAngleFeature(){
		 int numFeatures = siA.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=angle3d(siA[i],sjA[i]);
		 }
		 return features;
	 }

}