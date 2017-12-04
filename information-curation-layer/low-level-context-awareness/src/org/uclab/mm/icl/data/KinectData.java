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
package org.uclab.mm.icl.data;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 * Kinect data class
 * @author Nailbrainz
 * 
 */
public class KinectData implements DeviceData, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String timeStamp;
	ArrayList<double[]> ary = new ArrayList<double[]>();
	
	long userID;
	
	/**
	 * Returns the timestamp included in the Kinect data
	 * @return Time stamp of kinect data
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Set the timestamp to kinect data
	 * @param timeStamp timestamp to set on KinectData
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 
	 * @return returns Kinect data array
	 */
	public ArrayList<double[]> getAry() {
		return ary;
	}
	
	/**
	 * 
	 * @param ary kinect data 
	 */
	public void setAry(ArrayList<double[]> ary) {
		this.ary = ary;
	}
	
	/**
	 * 
	 * @return corresponding user ID of KinectData
	 */
	public Long getUserID() {
		return userID;
	}
	
	
	/**
	 * 
	 * @param userID user ID to be set on the KinectData
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * This function decodes the encoded data sent from kienct, into SensoryData class
	 * @param input Encoded data sent from the kienct
	 */
	@Override
	public void setData(Object input) {
		// TODO Auto-generated method stub
		ArrayList<double[]> ary = parseJsonInput(input.toString());
		
		
		for(int j = 0; j<ary.size(); j++){
			this.getAry().add(ary.get(j));
		}
	}
	
	
	/**
	 * This function parses the json string which is sent from kinect, into array of double[]
	 * @param str Json string, which have 25 Body skeleton data, sent from Kinect
	 * @return double[], skeleton data sent from Kinect
	 */
	ArrayList<double[]> parseJsonInput(String str){
    	JSONArray ary = new JSONArray(str);
    	ArrayList<double[]> ret = new ArrayList<double[]>();
		//System.out.println("~~~~~~~~~~~~~~~~~~");
		for(int i = 0; i<ary.length(); i++){
			JSONObject prev = new JSONObject(ary.get(i).toString());
			
			double[] data = new double[75];
			
			JSONArray prevJAry = prev.getJSONArray("points");
			for(int j = 0; j<prevJAry.length(); j++){
				JSONObject item = prevJAry.getJSONObject(j);
				data[j*3] = item.getDouble("X");
				data[j*3+1] = item.getDouble("Y");
				data[j*3+2] = item.getDouble("Z");
			}
			if(i == 0){
				userID = prev.getLong("user_id");
				timeStamp = prev.getString("timestamp_kinect");
			}
			ret.add(data);
		}
		return ret;
    }

	
}
