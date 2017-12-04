/**
Copyright [2016] [Tae ho Hur]

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

package org.uclab.mm.icl.llc.LLCRecognizer;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.AR.iar.AR_DecisionFusion;
import org.uclab.mm.icl.llc.AR.iar.AR_Reasoning;
import org.uclab.mm.icl.llc.AR.iar.AR_makeARFF;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.utils.FileUtil;
import org.uclab.mm.icl.utils.TimeChecker;

/**
 * This class processes the inertial sensor based activity recognition
 * @author Tae ho Hur
 *
 */


class IARData{
	public double[][] getAcc_wm() {
		return acc_wm;
	}

	public double[][] getGy_wm() {
		return gy_wm;
	}

	public double[][] getAcc_sm() {
		return acc_sm;
	}

	public double[][] getGy_sm() {
		return gy_sm;
	}

	public IARData(double[][] acc_wm, double[][] gy_wm, double[][] acc_sm, double[][] gy_sm) {
		super();
		this.acc_wm = acc_wm;
		this.gy_wm = gy_wm;
		this.acc_sm = acc_sm;
		this.gy_sm = gy_sm;
	}

	double[][] acc_wm, gy_wm, acc_sm, gy_sm;
	
}

public class InertialActivityRecognizer extends LLCRecognizer<IARData>{
	
	private final static Logger logger = Logger.getLogger(InertialActivityRecognizer.class);
	private AR_Reasoning ar_integrated = null; // Integrated model instance for reasoning
	private AR_Reasoning ar_smartphone = null; // Smartphone model instance for reasoning
	private AR_Reasoning ar_wearable = null; // Wearable model instance for reasoning
	private AR_makeARFF mkArff = null; // Instance for making ARFF file
	
	AR_DecisionFusion df; // To store decision fusion result

	// Stores the sensor variables
   
	/**
	 * This function loads the integrated, smartphone and smartwatch AR model
	 * @param userID
	 */
	public InertialActivityRecognizer(long userID){
		super(userID);
		
		ar_integrated = new AR_Reasoning(FileUtil.getRootPath()+"/armodel/integrated model_0126.model");
		ar_smartphone = new AR_Reasoning(FileUtil.getRootPath()+"/armodel/sm model_0126.model");
		ar_wearable = new AR_Reasoning(FileUtil.getRootPath()+"/armodel/wm model_0126.model");
		
		df = new AR_DecisionFusion();
				
		if (!new File(FileUtil.getRootPath()+"/arffFile/").exists()) {
			new File(FileUtil.getRootPath()+"/arffFile/").mkdirs();
		}
		
	}
	
	/**
	 * This function proceeds the inertial based activity reasoning 
	 */
	@Override
	public ContextLabel recognize(IARData input, String timeStamp) {
		
		// Execute to make the ARFF file
		try {
			mkArff = new AR_makeARFF(input.getAcc_wm(), input.getGy_wm(), input.getAcc_sm(), input.getGy_sm());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		String in = null; // To store activity result from integrated AR model
		String sm = null; // To store activity result from smartphone AR model
		String wm = null; // To store activity result from wearable AR model
		
		// Return the result of each AR model
		try{
			 in = AR_DecisionFusion.getActivity(Integer.parseInt(ar_integrated.executeWeka(1)));
			 sm = AR_DecisionFusion.getActivity(Integer.parseInt(ar_smartphone.executeWeka(2)));
			 wm = AR_DecisionFusion.getActivity(Integer.parseInt(ar_wearable.executeWeka(3)));
			 
		}catch(Exception e){
			wm = null;
			e.printStackTrace();
		}
		
		// Store all the information with activity label
		ContextLabel act = new ContextLabel(userID, df.getFinalActivity(in, sm, wm), timeStamp, ContextType.Activity);

		// Simple notification for successful reasoning
		if(verbose)logger.info("Output from Inertial recognizer!" + userID + "\t" + act.getLabel() + "\t" + act.getTimeStamp());
	
		long localTime = TimeChecker.time;
		return act;
	}


	/**
	 * This class loads the sensor data
	 */
	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		
		if(it.equals(DeviceType.SmartPhone)){
			SmartphoneData data = (SmartphoneData) obj;
			IARData idd = new IARData(data.getAcc_wm(),data.getGy_wm(),data.getAcc_sm(),data.getGy_sm());
			setData(idd);
		}
	}

	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.IAR;
	}
	
	
	
	/*
	 * 
	public boolean isFull(){
		return acc_wm != null && acc_wm[0] != null;
	}
	
	@Override
	public Runnable getRunnable(String timeStamp, LLCUnifier unifier) {
		// TODO Auto-generated method stub
		return new IARunnable(timeStamp, unifier, this);
	}
	class IARunnable implements Runnable{
		LLCUnifier unifier;
		InertialActivityRecognizer rec;
		String timeStamp;
		
		public IARunnable(String timeStamp, LLCUnifier unifier, InertialActivityRecognizer rec){
			this.unifier = unifier;
			this.rec = rec;
			this.timeStamp = timeStamp;
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(rec.isFull()){
				unifier.push(rec.recognize(new double[5], timeStamp), RecognizerType.IAR);
			}
				
			else{
				unifier.push(new ContextLabel(userID, "NoActivity", timeStamp, ContextType.Activity), RecognizerType.IAR);
			}
				
		}
		
	}
	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.IAR;
	}
	

	@Override
	public boolean isThresholdReached() {
		// TODO Auto-generated method stub
		return acc_sm != null;
	}*/
	 
}

