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

package org.uclab.mm.icl.llc.restservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.uclab.mm.icl.llc.LLCManager.LLCNotifier;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.ICLConfig;
import org.uclab.mm.icl.utils.TimeUtil;

public class RestClients {
	private final static Logger logger = Logger.getLogger(RestClients.class);
	
	public static String[] labels = {"Running","Walking","Sitting","Subway","Bus","Cycling","LyingDown","Standing", "Jogging", "Hiking" ,"ClimbingStairs", "Descending","Stretching", "Dancing", "Sweeping", "Eating" ,"Escalator", "Elevator", "Resting", "UnidentifiedActivity", "NoActivity"};
	
	/**
	 * 
	 * @param str String which will be converted into predefined integer, which is used through Mining Minds project
	 * @return integer corresponding to the String Activity
	 */
	public static synchronized int getIndex(String str)  {
		int ret = 21;
		for(int i = 0; i<labels.length; i++){
			if(labels[i].equals(str)){
				return i+1;
			}
		}
		return ret;
	}
	
	
	
	
	
	/**
	 * This function calls the rest service of DCL to send user detected location
	 * @param loc Location which will be transfered into DCL
	 * @return State string describing the operation
	 * @throws Exception exception caused during the transfer, mainly HTTP exception
	 */
	public static synchronized String addUserDetectedLoc(ContextLabel loc) throws Exception{
		URL url = new URL(ICLConfig.DCLAddress+"AddUserDetectedLocation");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		
		JSONObject json = new JSONObject();
		//json.put("userDetectedLocationId", "1");
		json.put("userId", Long.toString(loc.getUserID()));
		json.put("locationLabel", loc.getLabel());
		json.put("startTime", loc.getTimeStamp());
		out.write(json.toString());
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		return str;
	}
	
	/**
	 * This function calls the rest service of DCL to send user detected emotion
	 * @param emo loc Emotion which will be transfered into DCL
	 * @return State string describing the operation
	 * @throws Exception  exception caused during the transfer, mainly HTTP exception
	 */ 
	public static synchronized String addUserRecognizedEmotion(ContextLabel emo) throws Exception{
		URL url = new URL(ICLConfig.DCLAddress+"AddUserRecognizedEmotion");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		
		JSONObject json = new JSONObject();
		
		//json.put("userDetectedLocationId", "1");
		json.put("userId", Long.toString(emo.getUserID()));
		json.put("emotionLabel", emo.getLabel());
		json.put("startTime", emo.getTimeStamp());
		out.write(json.toString());
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		return str;
	}
	
	/**
	 * 
	 * @param userID user ID which will be transfered into DCL
	 * @param hlcdesc description of high level context
	 * @param StartTime start time of high level context
	 * @return State string describing the operation
	 * @throws Exception exception caused during the transfer, mainly HTTP exception
	 */
	public static synchronized String addUserRecognizedHLC(long userID, String hlcdesc, String StartTime) throws Exception{
		
		
		URL url = new URL(ICLConfig.DCLAddress+"AddUserRecognizedHLC");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		
		JSONObject json = new JSONObject();
		
		//json.put("userDetectedLocationId", "1");
		json.put("userId", Long.toString(userID));
		json.put("hLCLabel", hlcdesc);
		json.put("startTime", StartTime);
		out.write(json.toString());
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String str=in.readLine();
		/**/
		
		return str;
	}
	
	/**
	 * 
	 * @param act low level activity which will be transfered into DCL
	 * @return State string describing the operation
	 * @throws Exception exception caused during the transfer, mainly HTTP exception
	 */
	public static synchronized String addUserRecognizedAct(ContextLabel act) throws Exception{
		URL url = new URL(ICLConfig.DCLAddress+"AddUserRecognizedActivity");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		
		JSONObject json = new JSONObject();
		
		json.put("userId", Long.toString(act.getUserID()));
		json.put("activityId", getIndex(act.getLabel()));
		json.put("startTime", act.getTimeStamp());
		out.write(json.toString());
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		return str;
	}
	
	
	
	public static synchronized void sendDemo(String userID, String act, String emo, String loc, String food, String con, String cate, String timeStamp) throws Exception{
		//http://163.180.116.210/gathering.php?userid=36&time=10:50:09&act=Standing&emo=Happiness&loc=Home&food=Meat&con=Fat
		
		
		String actG = LLCNotifier.noActivity;
		String emoG = LLCNotifier.noEmotion;
		String locG = LLCNotifier.noLocation;
		String fdG = LLCNotifier.noFood;
		String conG = LLCNotifier.noHLC;
		String foodCate = LLCNotifier.noCat;
		
		if(!act.equals(LLCNotifier.noActivity))actG = act;
		if(!emo.equals(LLCNotifier.noEmotion))emoG = emo;
		if(!loc.equals(LLCNotifier.noLocation))locG = loc;
		if(!food.equals(LLCNotifier.noFood))fdG = food;
		if(!con.equals(LLCNotifier.noHLC)){
			conG = con;
		}
		if(!cate.equals(LLCNotifier.noCat)){
			foodCate = cate;
		}
		/*
		Random r = new Random();
		String[] foods = {"Grain", "Meat", "SeaFood", "Eggs", "MilkAndDairyProducts", "Legumes", "Nuts", "Fruits", "Vegetable", "Snacks" };
		fdG = foods[(Math.abs(r.nextInt()))%(foods.length)];
		*/
		
		
		try {
			
			TimeUtil tutil = new TimeUtil();
			String ts = tutil.toDemoStr(Calendar.getInstance());
			String query = "?userid="+userID+"&"+"time="+ts+"&act="+actG+"&emo="+emoG+"&loc="+locG+"&food="+fdG+"&con="+conG+"&foodCate="+foodCate;//"?time="+timeStamp+"&act="+actG+"&emo="+emoG+"&loc="+locG+"&con=NoHighLevel";
			
			 String ENDPOINT ="http://163.180.116.210/gathering.php";
	         String charset = "UTF-8";
	         logger.info("query to Jaehun's visualization module is " + ENDPOINT + query);
	         java.net.URLConnection connection = new URL(ENDPOINT + query).openConnection();
	         connection.setRequestProperty("Accept-Charset", charset);
	         //actG = emoG = locG = conG = "no";
	         if ( connection instanceof HttpURLConnection)
	         {
	            HttpURLConnection httpConnection = (HttpURLConnection) connection;
	            logger.info("result of query : " + httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage());
	         }
	         
		} catch (Exception e) {
			
			logger.error(e);
		}
		
	}
	
	/**
	 * Counter class, for demo
	 * @author Nailbrainz
	 *
	 */
	
	/*
	static class Counter implements Runnable{
		@Override
		public synchronized void run() {
			try {
				wait(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// TODO Auto-generated method stub
				while(true){
					try {
						wait(3000);
						TimeUtil tutil = new TimeUtil();
						String timeStamp = tutil.toDemoStr(Calendar.getInstance());
						String query = "?time="+timeStamp+"&act="+actG+"&emo="+emoG+"&loc="+locG+"&con="+conG;//"?time="+timeStamp+"&act="+actG+"&emo="+emoG+"&loc="+locG+"&con=NoHighLevel";
						actG = "noActivity";
						emoG = "noEmotion";
						locG = "noLocation";
						conG = "NoHighLevel";
						System.out.println("query is " + query);
						 String ENDPOINT ="http://163.180.116.210/gathering.php";
				         String charset = "UTF-8";
				         java.net.URLConnection connection = new URL(ENDPOINT + query).openConnection();
				         connection.setRequestProperty("Accept-Charset", charset);
				         //actG = emoG = locG = conG = "no";
				         if ( connection instanceof HttpURLConnection)
				         {
				            HttpURLConnection httpConnection = (HttpURLConnection) connection;
				            System.out.println(httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage());
				         }
				         else
				         {
				        	 System.out.println("error!");
				         }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		}

	
	/**
	 * 
	 * @param label label to notify DCL
	 * @param userID corresponding user ID
	 * @param timeStamp corresponding TimeStamp
	 * @throws IOException exception related to socket
	 */
    public static void notifyChange(String label, long userID, String timeStamp) throws IOException{
    	
    	logger.info("notifyChange called with "+ label + " " + userID + " " + timeStamp);
    	
   	URL url = null;
		try {
			url = new URL(ICLConfig.LLCAddress+"updatellc");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error at ");
			logger.error(e);
		}
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(connection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		try {
			JSONObject llc = new JSONObject();
			llc.put("label", label);
			llc.put("userID", Long.toString(userID));
			llc.put("timeStamp", timeStamp);
			out.write(llc.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		in.close();
		logger.info("result of notifyChange = " + str);
   }
    
    
    /**
	 * @throws IOException exception related to socket
	 */
    public static String getVarVersion() throws IOException{
    	
    	//if(label.equals("Sitting"))label = "Eating";
    	
   	URL url = null;
		try {
			url = new URL(ICLConfig.LLCAddress+"VARService/version");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		/*
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(connection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		try {
			JSONObject llc = new JSONObject();
			llc.put("label", label);
			llc.put("userID", Long.toString(userID));
			llc.put("timeStamp", timeStamp);
			out.write(llc.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		in.close();
		return str;
   }
    
    public static synchronized String AddFoodLog(ContextLabel food) throws Exception{
		
    	
    	URL url = new URL("http://163.180.173.135:8080/testWeb/webresources/testweb/AddFoodLog");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		//String input = new String("{\"points\": [{\"X\": 33.3333,\"Y\": 33.3333,\"Z\": 33.3333,\"FrameIndex\": 1}],\"timestamp_kinect\": \"its string\",\"user_id\": 123}"); 
		
		JSONObject json = new JSONObject();
		//json.put("userDetectedLocationId", "1");
		json.put("userId", Long.toString(food.getUserID()));
		json.put("foodName", food.getLabel());
		json.put("eatingTime", food.getTimeStamp());
		out.write(json.toString());
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		return str;
	}
	
}
