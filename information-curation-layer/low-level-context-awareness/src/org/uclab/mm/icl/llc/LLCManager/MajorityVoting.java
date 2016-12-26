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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.utils.TimeUtil;


/**
 * Low level context unifier which runs the unifying process
 * @author Nailbrainz
 */
public class MajorityVoting extends LLCUnifier{
	private final static Logger logger = Logger.getLogger(MajorityVoting.class);
	AtomicInteger index = new AtomicInteger(0);
	final int maxIndex = 100;
	
	TimeUtil util = new TimeUtil();
	
	ArrayList< ArrayList<ContextLabel> > Tables = new ArrayList< ArrayList<ContextLabel> >();
	ArrayList<byte[]> images = new ArrayList<byte[]>();
    
    
	/**
	 * Unique constructor of MajorityVoting class
	 * @param userID corresponding user ID
	 */
	public MajorityVoting(long userID, ArrayList<AtomicReference<String> > curLabels){
		super(userID, curLabels);
		
		for(int i =  0; i<RecognizerType.values().length; i++){
			Tables.add(new ArrayList<ContextLabel>());
		}
	}
	
	
    /**
     * This function clear the image data
     */
    public void clear(){
		images.clear();
    }
    
    
    /**
     * Push the llc context into unifier
     * @param obj object(llcContext) to push
     * @param rec corresponding recognizer type
     */
	public void push(Object obj, RecognizerType rec){
		Tables.get(rec.getValue()).add((ContextLabel) obj);
		
		if(rec.equals(RecognizerType.SER) || rec.equals(RecognizerType.ER)){
			System.err.println(rec.toString());
			System.err.println(((ContextLabel)obj).getLabel());
			System.err.println(((ContextLabel)obj).getTimeStamp());
			
			logger.info("****** " + rec.toString() + " At unifier : " + ((ContextLabel)obj).getLabel() + "  " + ((ContextLabel)obj).getTimeStamp() + "   ******");
		}
	}
	
	
	/**
	 * boolean function for printing console
	 * @param rec recognizer type
	 * @return 
	 */
	private boolean cond(ContextType ct){
		//return false;
		return (ct.equals(ContextType.Activity));
	}
	
	/**
	 * 
	 * @param rec Target recognizer to get minimum time of the labels
	 * @return get minimum time from the data of target recognizer
	 * @throws NullPointerException The 
	 */
	public Calendar getMinTime(RecognizerType rec) throws NullPointerException{
		ArrayList<ContextLabel> values = Tables.get(rec.getValue());
		if(values.size() == 0)return null;
		
		Calendar cal = Calendar.getInstance();
		for(int i = 0; i<values.size(); i++){
			Calendar comp = util.parseString((values.get(i).getTimeStamp()));
			if(cal.compareTo(comp) > 0){
				cal.setTime(comp.getTime());
			}
		}
		return cal;
	}
	
	



	/**
	 * This function implements unification process with majority voting
	 */
	@Override
	ContextLabel unify(String startTime, ContextType ct) {
		// TODO Auto-generated method stub

		HashMap<String, Double> map = new HashMap<String, Double>();
		String[] labels = ct.getLabels();
		String def = labels[labels.length-1];
		for(String label : labels){
			map.put(label, (double) 0);
		}
		
		//if(cond(rec))System.err.println("Labels of " + rec.toString() + "-------------------- until time " + startTime + " with " + map.size());
		
		ArrayList<ContextLabel> values = new ArrayList<ContextLabel>();
		for(RecognizerType rec: RecognizerType.values()){
			if(rec.getContext().equals(ct)){ //if the recognizer recognizes the parameter context, we collect the data for majority vote
				ArrayList<ContextLabel> prev = Tables.get(rec.getValue());
				for(int i = 0; i<prev.size(); i++){
					ContextLabel item = prev.get(i);
					values.add(item);
				}
				ArrayList<ContextLabel> left = new ArrayList<ContextLabel>();
				Tables.set(rec.getValue(), left);
			}
		}
		
		double sz = values.size();
		for(int i = 0; i<values.size(); i++){
			ContextLabel item = values.get(i);
			if(cond(ct))System.err.println(item.getLabel() + " " + item.getTimeStamp());
			try{
				double val = map.get(item.getLabel());
				map.put(item.getLabel(), val+1/(sz));
			}catch(Exception e){
				if(item == null){
					System.err.println("item null");
				}else{
					if(item.getLabel() == null){
						System.err.println("label null");
					}
				}
				logger.error("Label err is " + item.getLabel());
				for(int j = 0; j<labels.length; j++){
					logger.error("with " + labels[j]);
				}
				logger.error(e);
			}
		}
		
		String maxI = null;
		Double maxV = (double) -1;
		
		boolean found = false;
		for(int i = 0; i<values.size(); i++){
			Calendar cal = util.parseString((values.get(i).getTimeStamp()));
			ContextLabel item = values.get(i); 
			if(map.containsKey(item.getLabel())){
				double val = map.get(item.getLabel());
				if(val != 0)found = true;
				map.put(item.getLabel(), val+1/sz);
			}
		}
		String emptyL = null;
		ContextLabel ret = null;
		/*need to be removed to return null*/
		ret = ct.getBasicLabel(userID, startTime);
		
		if(cond(ct))logger.info("Output-------------");
		
		for( String key : ct.getLabels() ){
			if(cond(ct))
				logger.info("act = " + key + " val = " + map.get(key));
			if(!key.equals(def) && map.get(key) != 0 && map.get(key) > maxV){
            	maxV = map.get(key);
            	maxI = key;
            }
        }
		if(maxI != null){
			ret = ct.getContext(userID, maxI, startTime);
		}
		
		if(cond(ct))logger.info("Final result = " + ret.getUserID() + " "+ ret.getLabel() + "  " + ret.getTimeStamp());
		if(cond(ct)){
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream(new File("D:/KinectFace/activity.txt"),true));
				pw.append(ret.getLabel() + "\t" + ret.getTimeStamp() + "\r\n");
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		curLabels.set(ct.getValue(), new AtomicReference<String>(ret.getLabel()));
		return ret;
	}



}
