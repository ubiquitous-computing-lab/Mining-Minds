/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.LLCRecognizer;

/*
Copyright [2016] [Dong Uk, Kang]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.KinectData;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.LLCManager.MajorityVoting;
import org.uclab.mm.icl.llc.MachineLearningTools.classification.KinectARClassification;
import org.uclab.mm.icl.llc.MachineLearningTools.featureextraction.KincetARFeatureExtraction4;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;


/**
 *	This class recognizes user activity from Kinect skeleton data (in JSON format)
 * 	@author Nailbrainz
 */
public class VideoActivityRecognizer extends LLCRecognizer<KinectData>{
	public VideoActivityRecognizer(long userID) {
		super(userID);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(VideoActivityRecognizer.class);
	KincetARFeatureExtraction4 featureExtractor = new KincetARFeatureExtraction4();
	KinectARClassification ar = new KinectARClassification();
	
	
	@Override
	public RecognizerType getType() {
		return RecognizerType.VAR;
	}
	
	
	@Override
	public ContextLabel recognize(KinectData input, String timeStamp) {
		// TODO Auto-generated method stub
		double inputD[][] = new double[30][75];
		for(int i = 0; i<input.getAry().size(); i++){
			inputD[i] = input.getAry().get(i);
		}
		
		/*
		for(double[] ad1 : inputD){
			for(double ad2 : ad1){
				System.out.print(ad2 + " ");
			}
			System.out.println();
		}*/
		featureExtractor.setInputData(inputD);
		double[] inputFeatures = featureExtractor.extractFeatures();
		// siD and sjD are read from txt file. I do not know how to input this file. Help me!
		
		String rlt = ar.Classify(inputFeatures);
		if(verbose)logger.info("Output of Var Thien " + userID + "\t" + rlt + "\t" + timeStamp);
		return new ContextLabel(userID, rlt, timeStamp, ContextType.Activity);
	}/**/
	

	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		if(it.equals(DeviceType.Kinect)){
			setData((KinectData)obj);
		}
	}
	
	

	/*
	@Override
	public Activity recognize(double[] input, long userID, String timeStamp) {
		// TODO Auto-generated method stub
		

		
		featureExtractor.setInputData(input);
		double[] distanceFeatures = featureExtractor.extractDistanceFeature();
		// siD and sjD are read from txt file. I do not know how to input this file. Help me!
		
		double[] angleFeatures = featureExtractor.extractAngleFeature();
		// siA and sjA are read from txt file
		
		
		double[] features = new double[distanceFeatures.length + angleFeatures.length];
		System.arraycopy(distanceFeatures, 0, features, 0, distanceFeatures.length);
		System.arraycopy(angleFeatures, 0, features, distanceFeatures.length, angleFeatures.length);
		
		String rlt = ar.Classify(features);
		return new Activity(userID, rlt, timeStamp);
	}*/
	
	
	/*
	 * @Override
	public Runnable getRunnable(String timeStamp,  LLCUnifier unifier) {
		// TODO Auto-generated method stub
		return new VARRunnable(unifier);
	}
	
	
	class VARRunnable implements Runnable{
		LLCUnifier unifier;
		KinectARClassification cla = new KinectARClassification();
		public VARRunnable(LLCUnifier unifier){
			this.unifier = unifier;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//이 밑 부분은 consumeData에서 해도 되지 않나
			ArrayList<KinectData> kdAry = kAry;
			kAry = new ArrayList<KinectData>();
			
			for(int i = 0; i<kdAry.size(); i++){
				
				try{
					KinectData fe = kdAry.get(i);
					System.err.println("what the Thien! " + fe.getAry().size());
					if(fe.getAry().size() != 0)//
						unifier.push(recognize(fe, fe.getTimeStamp()), RecognizerType.VAR);
				}catch(Exception e){
					logger.error("error occured at parsing json at varThien. Invalid structured of JSON from Kinect suspected. Error and JSON is as follows");
					logger.error(e);
					logger.error(kdAry.get(i));
				}
			}
		}
	}*/
}
