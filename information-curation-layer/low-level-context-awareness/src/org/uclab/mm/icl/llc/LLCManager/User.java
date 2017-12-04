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
package org.uclab.mm.icl.llc.LLCManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.llc.LLCRecognizer.LLCRecognizer;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.utils.TimeUtil;


/**
 * Note : this class is intended to be thread-safe
 * User class, which invokes independent recognizer threads for each registered recognizer,
 * and counter thread for each registered context
 * @author Nailbrainz
 */
public class User {
	private final static Logger logger = Logger.getLogger(User.class);
	private  ArrayList<AtomicReference<String> > curLabels = new ArrayList<AtomicReference<String>>();
	Long userID;
    MajorityVoting unifier;
    //AtomicBoolean isEatingFood = new AtomicBoolean(false);
    
    ExecutorService es;
    ArrayList<LLCRecognizer> recognizers = new ArrayList<LLCRecognizer>();
    ArrayList<AtomicBoolean> isContCounterRunning = new ArrayList<AtomicBoolean>();
    TimeUtil util = new TimeUtil();
    LLCNotifier notifier = new LLCNotifier();
    
    
    /**
     * Unique Constructor of user
     * @param userID user ID to generate
     * @param es Global executor service
     * @param bq Blocking queue for sending data to other layers
     */
    public User(Long userID, ExecutorService es/*, BlockingQueue<SendingItem> bq*/){
        this.userID = userID;
        
        //for each context type, initialize the counters and current labels
        for(int i = 0; i<ContextType.values().length; i++){
        	isContCounterRunning.add(new AtomicBoolean(false));
        	String[] labels = ContextType.values()[i].getLabels();
        	curLabels.add(new AtomicReference<String>(labels[labels.length-1])); // set to No(ContextName)
        }
        
        unifier = new MajorityVoting(/*bq,*/ userID, curLabels);
        this.es = es;
        
        //initialize each registered recognizers
        for(RecognizerType rec: RecognizerType.values()){
        	if(rec.isOn()){
        		recognizers.add(rec.getRecognizer(userID));
            	logger.info("recognizer added : " + rec.toString() + " of user " + userID);
        	}
        }
    }
    
    
    
   
    /**
     * With the data sent from Sensor, fill the buffer of each recognizers
     * @param it SensorType which sends the data
     * @param data data which is is sent from the SensorType
     * @param data_timeStamp timestamp of the data, designating the time when the data is sent from the device, in MM format.
     * @throws NullPointerException The data object could be null, if the program in the sensor is not well coded.
     */
	public void fillBuffer(DeviceType it, DeviceData data, String data_timeStamp) throws NullPointerException{ 
		
		//for each context, check whether the counter is running and run the counter if it is not running
		for(ContextType cont: ContextType.values()){
			if(!isContCounterRunning.get(cont.getValue()).get()){
				isContCounterRunning.get(cont.getValue()).set(true);
		    	es.execute(new LLCCounter(userID, data_timeStamp, es, unifier, notifier, cont, isContCounterRunning.get(cont.getValue())));
			}
        }
		
		/*
		for(RecognizerType rec: RecognizerType.values()){ //for each registered recognizers
			if(!rec.isOn())continue;
			LLCRecognizer curRec = recognizers.get(rec.getValue());
			curRec.ConsumeData(it, data); //consume the current sensor data
			if(curRec.isThresholdReached()) //if threashold reached, call the recognizer
			{
				es.execute(curRec.getRunnable(data_timeStamp, unifier));
			}
        }*/
		for(LLCRecognizer rec: recognizers){ //for each registered recognizers
			rec.ConsumeData(it, data); //consume the current sensor data
			if(rec.isThresholdReached()) //if threashold reached, call the recognizer
			{
				es.execute(rec.getRunnable(data_timeStamp, unifier));
			}
        }
    }
	
	
	
	
	
	
	
	
	
	public ArrayList<AtomicReference<String> > getcurLabels(){
		return curLabels;
	}
	
	
	
/**
 * Fillbuffer function for the demo (food) scenario
 * @param timeStamp timestamp of the food context
 * @param image food image
 * @param tag tag string, from manual user input
 */
    public void fillBufferDemo(String timeStamp, byte[] image, String tag){
    	
    	for(RecognizerType rec: RecognizerType.values()){
    		if(rec.isOn()){
    			if(rec.equals(RecognizerType.VAR))continue;
        	}
        }
    }
}
