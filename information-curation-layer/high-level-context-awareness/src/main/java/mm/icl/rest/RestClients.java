/**
* 
* Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.icl.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * RestClient: 
 * Rest Services to connect MiningMinds DCL Layer.
 * 
 * @author Nailbrainz
 * @version 2.5
 * @since 2015-11-06
 */

import mm.icl.utils.*;

public class RestClients {
	
	RestClients(){
		
	}
	                               
	public static String[] labels = {"Jumping","Boredom","Running","Walking","Sitting","Subway","Bus","Cycling","LyingDown","Standing", "Jogging", "Hiking" ,"Climbing", "Descending"," Stretching", "Dancing", "Sweeping", "Eating" ,"Escalator", "Elevator", "Resting", "NoActivity"};
	
	public static synchronized int getIndex(String str)  {
		int ret = -1;
		for(int i = 0; i<labels.length; i++){
			if(labels[i].equals(str)){
				return i+1;
			}
		}
		return ret;
	}
	
	public static synchronized String addUserRecognizedHLC(long userID, String hlcdesc, String StartTime) throws Exception{
		URL url = new URL("http://163.180.116.194:8080/MMDataCurationRestfulService/webresources/InformationCuration/AddUserRecognizedHLC");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		
		JSONObject json = new JSONObject();
		
		
		JSONArray ary1 = new JSONArray();
		JSONObject item = new JSONObject();
		
		json.put("userId", Long.toString(userID));
		json.put("hLCLabel", hlcdesc);
		json.put("startTime", StartTime);
		out.write(json.toString());
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String str = in.readLine();
		return str;
	}
	
	
	
	public static synchronized String sendDemo(String act, String emo, String loc, String con, String timeStamp) throws Exception{
		String ret = "";
		if(!act.equals("no"))actG = act;
		if(!emo.equals("no"))emoG = emo;
		if(!loc.equals("no"))locG = loc;
		if(!con.equals("no"))conG = con;
		
		
		TimeUtil tutil = new TimeUtil();
		timeStamp = tutil.toDemoStr(tutil.parseString(timeStamp));
		String query = "?time="+timeStamp+"&act="+act+"&emo="+emo+"&loc="+loc+"&con="+con;
		
		 String ENDPOINT ="http://163.180.116.210/gathering.php";
        String url = ENDPOINT;
         String charset = "UTF-8";
         java.net.URLConnection connection = new URL(url + query).openConnection();
         connection.setRequestProperty("Accept-Charset", charset);
         
         if ( connection instanceof HttpURLConnection)
         {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            ret = httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage();
         }
         else
         {
            return ("error!");
         }
		
		return ret;
	}
	
	static String actG = "no";
	static String emoG = "no";
	static String locG = "no";
	static String conG = "no";
	
	static{
		Thread t = new Thread(new Counter());
		t.start();
	}
	
	static class Counter implements Runnable{
		@Override
		public synchronized void run() {
			try {
				wait(1500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				while(true){
					try {
						wait(3000);
						TimeUtil tutil = new TimeUtil();
						String timeStamp = tutil.toDemoStr(Calendar.getInstance());
						String query = "?time="+timeStamp+"&act="+actG+"&emo="+emoG+"&loc="+locG+"&con="+conG;
						System.out.println("query is " + query);
						 String ENDPOINT ="http://163.180.116.210/gathering.php";
				         String charset = "UTF-8";
				         java.net.URLConnection connection = new URL(ENDPOINT + query).openConnection();
				         connection.setRequestProperty("Accept-Charset", charset);
				         if ( connection instanceof HttpURLConnection)   
				         {
				            HttpURLConnection httpConnection = (HttpURLConnection) connection;
				            System.out.println("RestClients.java"+ httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage());
				         }
				         else
				         {
				        	 System.out.println("error!");
				         }
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}
}
