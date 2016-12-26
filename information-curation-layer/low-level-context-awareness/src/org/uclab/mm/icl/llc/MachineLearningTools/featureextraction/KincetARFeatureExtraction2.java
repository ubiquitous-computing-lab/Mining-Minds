package org.uclab.mm.icl.llc.MachineLearningTools.featureextraction;


public class KincetARFeatureExtraction2 extends FeatureExtraction {
	double [] currentData;
	double [] previousData;
	double [] x2,y2,z2;
	double [] x1,y1,z1;
	
	// current frame index
	int[] siDinter = {2,2,2,2,2,2,3,3,3,3,3,3,3,7,7,10,11,11,20,21,21,21,22,23,23,24,24,0,0,1,1,2,2,2,2,2,3,3,4,4,4,6,6,6,7,8,8,8,8,9,10,10,10,11,11,13,13,16,16,17,17,17,17,17,17,17,20,20,20,20,20,20,20,20,20,20,22,22,23,23,23,24,24,0,0,0,0,0,0,1,1,1,1,1,2,2,2,2,2,2};
	// previous frame index
	int[] sjDinter = {7,11,16,21,23,24,7,10,11,21,22,23,24,2,3,3,2,3,21,2,3,20,3,2,3,2,3,2,20,10,17,0,6,10,17,22,6,17,10,13,23,2,3,20,20,11,17,23,24,17,1,2,20,8,20,4,17,2,20,1,2,3,8,9,13,20,0,6,7,10,11,16,17,22,23,24,2,20,4,8,20,8,20,1,3,4,5,6,21,0,13,14,18,19,4,8,12,13,14,15};
	// current frame index
	int[] siAinter = {2,2,2,3,3,3,3,5,5,5,8,14,14,14,15,15,15,15,17,17,18,18,18,19,19,19,19,19,20,20,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,3,3,4,5,5,6,6,6,6,7,10,10,11,11,11,11,12,12,12,12,12,12,12,13,13,13,14,14,14,14,14,15,15,15,15,16,16,16,16,16,16,17,17,17};
	// previous frame index
	int[] sjAinter = {12,14,15,0,5,12,15,3,17,18,18,2,3,4,2,3,4,20,2,8,1,4,8,1,2,8,9,20,12,15,1,2,3,8,13,20,23,0,2,11,14,20,23,24,0,1,17,18,19,4,6,11,14,16,17,18,19,24,1,11,13,3,17,18,23,3,0,12,0,3,12,16,1,2,3,8,13,20,23,2,8,9,1,8,9,13,20,5,8,9,13,2,3,9,13,20,23,1,3,9};
	
	
	public void setInputData(double[] previousData, double[] currentData){
		this.previousData = previousData;
		this.currentData = currentData;
		separateData();
	}
	private void separateData(){
		x1 = new double[25];
		y1 = new double[25];
		z1 = new double[25];
		
		x2 = new double[25];
		y2 = new double[25];
		z2 = new double[25];
		
		for (int i = 0;  i < 25; i++){
			x1[i]=previousData[i*3];
			y1[i]=previousData[i*3+1];
			z1[i]=previousData[i*3+2];
			
			x2[i]=currentData[i*3];
			y2[i]=currentData[i*3+1];
			z2[i]=currentData[i*3+2];
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
	private double distance3dintra(int i, int j){
		double d= 0;
		try{
			d=Math.sqrt(Math.pow(x2[i]-x2[j],2)+Math.pow(y2[i]-y2[j],2)+Math.pow(z2[i]-z2[j],2));
			
		}catch(Exception e){
			//System.out.println("i = "+i + ", j = " +j);
		}
		return d;
	}
	
	private double distance3dinter(int i, int j){
		double d= 0;
		try{
			d=Math.sqrt(Math.pow(x2[i]-x1[j],2)+Math.pow(y2[i]-y1[j],2)+Math.pow(z2[i]-z1[j],2));
			
		}catch(Exception e){
			//System.out.println("i = "+i + ", j = " +j);
		}
		return d;
	}
	
	private double angle3dintra(int i,int j){
		double deltax=x2[j]-x2[i];
        double deltay=y2[j]-y2[i];
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
	
	private double angle3dinter(int i,int j){
		double deltax=x2[j]-x1[i];
        double deltay=y2[j]-y1[i];
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
	
	 public double[] extractDistanceFeatureInter(){
		 int numFeatures = siDinter.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=distance3dinter(siDinter[i],sjDinter[i]);
		 }
		 return features;
	 }
	 
	 public double[] extractAngleFeatureInter(){
		 int numFeatures = siAinter.length;
		 double[] features = new double[numFeatures];
		 for (int i = 0; i < numFeatures; i++){
			 features[i]=angle3dinter(siAinter[i],sjAinter[i]);
		 }
		 return features;
	 }
	 
	@Override
	public Object extractFeature(Object input) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
