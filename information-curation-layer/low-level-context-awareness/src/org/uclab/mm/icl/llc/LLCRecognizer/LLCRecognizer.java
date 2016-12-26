/**
Copyright [2016] [Dong Uk Kang]

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

import java.util.ArrayList;

import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.MachineLearningTools.MachineLearning;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;

/**
 *	Basic interface of the recognizers used in ICL
 * @author Nailbrainz
 */
public abstract class LLCRecognizer<InputDataType>{
    
	public static final boolean verbose = true;
	long userID;
    private LLCRecognizer(){}; //prohibited.
    
    ArrayList<InputDataType> inputDatas = new ArrayList<InputDataType>();
    
    public LLCRecognizer(long userID){
    	this.userID = userID;
    }
    
    /**
     * With this method, each child recognizer can receive Data sent from each sensor. It depends on each child class to whether consume the data or do nothing.
     * @param it Type of sensor sending the data
     * @param obj Data that sensor sends
     */
    public abstract void ConsumeData(DeviceType it, DeviceData obj);
    
    /**
     * Returns the RecognizerType of the current instance
     * @return RecognizerType of the current instance
     */
    public abstract RecognizerType getType();
    
    /**
     * Recognizes the low level context. Recognition methodology is dependent on each child class.
     * @param input Input data
     * @param timeStamp time of the llc
     * @return recognized low level context, in ContextLabel instance
     */
    public abstract ContextLabel recognize(final InputDataType input, String timeStamp);
    
    
    public Runnable getRunnable(String timeStamp, LLCUnifier unifier){
    	return new Runnable() { // new Handler and Runnable
            @Override
            public void run() {
            	while(isThresholdReached()){
            		InputDataType idd = inputDatas.get(0);
            		inputDatas.remove(0);
            		unifier.push(recognize(idd, timeStamp), getType());
            	}
            }
        };
    }
    protected void setData(InputDataType inputData){
    	inputDatas.add(inputData);
    }
    public boolean isThresholdReached(){
    	return inputDatas.size() != 0;
    }
}
