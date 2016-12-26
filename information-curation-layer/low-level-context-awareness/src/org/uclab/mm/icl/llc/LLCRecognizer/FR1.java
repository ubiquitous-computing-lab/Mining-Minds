package org.uclab.mm.icl.llc.LLCRecognizer;

public class FR1{
	
}
/*
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.Data;
import org.uclab.mm.icl.data.SensoryData;
import org.uclab.mm.icl.llc.LLC.FoodItem;
import org.uclab.mm.icl.llc.LLCManager.LLCNotifier;
import org.uclab.mm.icl.llc.LLCManager.MajorityVoting;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.config.DataType;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.llc.restservices.RestClients;
import org.uclab.mm.icl.utils.FileUtil;

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


/**
 * Recognizer for the food context. Consumes the byte image and tag string from datatype ¡°SmartPhone¡±, and recognizers the corresponding food context. 
 * @author Nailbrainz
 *
 *//*

public class FoodRecognizer extends LLCRecognizer<String>{
	
	/**
	 * Unique constructor for FoodRecognizer
	 * @param userID user ID which the recognizer belongs
	 
	public FoodRecognizer(long userID) {
		super(userID);
		// TODO Auto-generated constructor stub
	}



	private final static Logger logger = Logger.getLogger(FoodRecognizer.class);
	AtomicBoolean ifEatingIsExisting = new AtomicBoolean(false);
	byte[] imageData;
	String foodTag;
	String timeStamp;
	long userIDMain;
	
	
	*//**
	 * Set the eating status. The food label will be recognized only when the activity context is eating.
	 * @param val
	 *//*
	public void setEating(AtomicBoolean val){
		ifEatingIsExisting = val;
	}
	
	
	*//**
	 * Returns the ENUM Recognizer type of foodRecognizer
	 *//*
	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	*//**
	 * Based on the tag information, recognize the food information
	 *//*
	@Override
	public ContextLabel recognize(String input, long userID, String timeStamp) {
		// TODO Auto-generated method stub
		userIDMain = userID;
		foodTag = input;
		FoodItem fi = new FoodItem(userID, foodTag, timeStamp, imageData);
		
		FileOutputStream out;
		try {
			try {
				RestClients.AddFoodLog(fi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fi.getImage() != null){
				out = new FileOutputStream(FileUtil.getRootPath()+"/Pic.jpg");
				out.write(fi.getImage());
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fi;
	}

	
	*//**
	 * Returns the runnable which will be running on the thread
	 * Recognize function will be working on that thread
	 *//*
	@Override
	public Runnable getRunnable(Long userID, String timeStamp, MajorityVoting unifier) {
		// TODO Auto-generated method stub
		return new FoodRunnable(unifier, this);
	}
	
	public Runnable getDemoRunnable(long userID, String timeStamp, byte[] image, String tag) {
		// TODO Auto-generated method stub
		return new FoodDemoRunnable(this, userID, timeStamp, image, tag);
	}
	
	public class FoodDemoRunnable implements Runnable{
		String tag;
		byte[] image ;
		FoodRecognizer rec;
		long userID;
		String timeStamp;
		public FoodDemoRunnable(FoodRecognizer rec, long userID, String timeStamp, byte[] image, String tag){
			this.image = image;
			this.tag = tag;
			this.rec = rec;
			this.userID = userID;
			this.timeStamp = timeStamp;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int cnt = 0;
			synchronized(this){ //this might be problematic?
				while(!ifEatingIsExisting.get()){
					logger.info("-------------------waiting");
					try {
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cnt++;
					if(cnt == 120)break;
				}
				if(ifEatingIsExisting.get()){
					
					logger.info("-------FROMDENO-------FOOD eating get and sending");
					ContextLabel fii =  rec.recognize(tag, userID, timeStamp);
					ContextLabel fi = new ContextLabel(fii.getUserID(), fii.getLabel(), fii.getTimeStamp(), image);
					
					logger.info("Food called! 1");
					if(fi.getImage() != null){
						logger.info("Food called! image not null");
					}
					FileOutputStream out;
					try {
						if(fi.getImage() != null){
							logger.info("Food called! 3");
							out = new FileOutputStream(FileUtil.getRootPath()+"/Pic.jpg");
							out.write(fi.getImage());
							out.close();
						}
						try {
							//LLCNotifier.Notify(fi);
							System.out.println(RestClients.AddFoodLog(fi));
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					logger.info("-------------------No Eating, so no FOOD ");
					//unifier.push(new Activity(input.getUserID(), "NoActivity", input.getTimeStamp()), RecognizerType.ARSang);
				}
			}
			
			
		}
		
	}
	
	class FoodRunnable implements Runnable{
		MajorityVoting unifier;
		FoodRecognizer rec;
		public FoodRunnable(MajorityVoting unifier, FoodRecognizer rec){
			this.unifier = unifier;
			this.rec = rec;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int cnt = 0;
			if(foodTag != null){
				//testing tag
				synchronized(this){ //this might be problematic?
					while(!ifEatingIsExisting.get()){
						System.err.println("-------------------waiting");
						try {
							wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cnt++;
						if(cnt == 120)break;
					}
					if(ifEatingIsExisting.get()){
						System.err.println("-------------------FOOD eating get and sending");
						//unifier.push(rec.recognize(foodTag, userIDMain, timeStamp), RecognizerType.Food);
					}else{
						System.err.println("-------------------No Eating, so no FOOD ");
						//unifier.push(new Activity(input.getUserID(), "NoActivity", input.getTimeStamp()), RecognizerType.ARSang);
					}
				}
				foodTag = null;
			}
			
			
		}
		
	}

	
	/**
	 * Returns the data type that food recognizer consumes
	 
	@Override
	public ArrayList<DataType> getDataTypes() {
		// TODO Auto-generated method stub
		ArrayList<DataType> data = new ArrayList<DataType>();
		data.add(DataType.SmartPhone);
		return data;
	}

	*//**
	 * Returns whether the recozniger has enough raw data to recognize the food context
	 *//*
	@Override
	public boolean isThreasholdReached() {
		// TODO Auto-generated method stub
		return false;
	}

	

	*//**
	 * From the each datatype, consumes the raw data to recognize the food context
	 *//*
	@Override
	public void ConsumeData(DataType it, Data obj) {
		// TODO Auto-generated method stub
		if(it.equals(DataType.SmartPhone)){
			SensoryData sd = (SensoryData) obj;
			imageData = sd.getImage();
			foodTag = sd.getFoodTag();
			timeStamp = sd.getTimeStamp();
		}
	}
	
}*/
