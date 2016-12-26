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

import java.io.IOException;
import java.io.Serializable;
import java.util.zip.DataFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.uclab.mm.icl.utils.ByteCompression;

import com.sun.jersey.core.util.Base64;



/**
 * This class represents the data sent from the smartphone + wearable devices, including inertial, voice and image data.
 * @author Nailbrainz
 */
public class SmartphoneData implements DeviceData, Serializable {
    private static final long serialVersionUID = 1L;
    private boolean isFromDemo = false;
    Long userID;

    byte[] emoImage;
    byte[] voiceData;
    byte[] image;
    double[] samples;  //you can neglect. It's for VUI's module only

    double[][] acc_sm = new double[3][150];
    double[][] gy_sm = new double[3][150];
    double[][] mag_sm = new double[3][150];

    double[][] acc_wm = new double[3][150];
    double[][] gy_wm = new double[3][150];
    double[][] mag_wm = new double[3][150];
    double[] ecg_wm = new double[150];


    public double longi, lat, spd;

    public String timeStamp;


    String foodTag;


    public void setIsDemo(boolean isDemo){
        this.isFromDemo = isDemo;
    }

    public boolean getIsDemo(){
        return isFromDemo;
    }



    public byte[] getImage() {
        return image;
    }

    public String getFoodTag() {
        return foodTag;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }


    /**
     *
     * @return user ID who generated this sensor data
     */
    public Long getUserID() {
        return userID;
    }


    /**
     *
     * @param userID user ID who generated this sensor data
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }


    public SmartphoneData(){
        //testing dummy
    }




    /**
     * Set audiosensory data
     * @param data audiosensory data to set
     */
    public void setAudioSensoryData(byte[] data){
        this.voiceData = data;
    }
    
    /**
     * set samples
     * @param samples samples data to set
     */
    public void setSamples(double[] samples){
        this.samples = samples;
    }

    public byte[] getAudioSensoryData(){
        return voiceData;
    }
    public double[] getSamples(){
        return samples;
    }




    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getSpd() {
        return spd;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double[][] getAcc_sm() {
        return acc_sm;
    }

    public void setAcc_sm(double[][] acc_sm) {
        this.acc_sm = acc_sm;
    }

    public double[][] getGy_sm() {
        return gy_sm;
    }

    public void setGy_sm(double[][] gy_sm) {
        this.gy_sm = gy_sm;
    }

    public double[][] getMag_sm() {
        return mag_sm;
    }

    public void setMag_sm(double[][] mag_sm) {
        this.mag_sm = mag_sm;
    }

    public double[][] getAcc_wm() {
        return acc_wm;
    }

    public void setAcc_wm(double[][] acc_wm) {
        this.acc_wm = acc_wm;
    }

    public double[][] getGy_wm() {
        return gy_wm;
    }

    public void setGy_wm(double[][] gy_wm) {
        this.gy_wm = gy_wm;
    }

    public double[][] getMag_wm() {
        return mag_wm;
    }

    public void setMag_wm(double[][] mag_wm) {
        this.mag_wm = mag_wm;
    }

    public double[] getEcg_wm() {
        return ecg_wm;
    }

    public void setEcg_wm(double[] ecg_wm) {
        this.ecg_wm = ecg_wm;
    }

    public byte[] getEmoImage(){return emoImage;}

    public void setEmoImage(byte[] image){
        this.emoImage = image;
    }

	@Override
	public void setData(Object input) {
		// TODO Auto-generated method stub
		JSONObject json1 = (new JSONObject((String)input)).getJSONObject("data");
		JSONObject obj = null;
		 byte[] bm = Base64.decode((new JSONObject((String)input)).getString("byte"));
         try {
			bm = ByteCompression.decompress(bm);
			String rett =  new String(bm);
			//System.out.println(rett);
			obj = new JSONObject(rett);
			//System.out.println(json1);
			if(rett.equals(json1.toString()))System.err.println("Sry Saqib!!!");
		} catch (IOException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		//json1 = json at smartphone
		this.setUserID(json1.getLong("user_id"));
		this.setTimeStamp(json1.getString("timestamp_android"));
		if(json1.has("array")){
			JSONArray sary = json1.getJSONArray("array");
			JSONArray comp = obj.getJSONArray("array");
			for(int i = 0; i<150; i++){
				
                JSONObject sitem = sary.getJSONObject(i);
                JSONObject itemC = comp.getJSONObject(i);
                acc_sm[0][i] = sitem.getDouble("accX");acc_sm[1][i] = sitem.getDouble("accY");acc_sm[2][i] = sitem.getDouble("accZ");
                gy_sm[0][i] = sitem.getDouble("gyroX");gy_sm[1][i] = sitem.getDouble("gyroY");gy_sm[2][i] = sitem.getDouble("gyroZ");
                
            }
		}
		if(json1.has("arrayW")){
			JSONArray wary = json1.getJSONArray("arrayW");

			
			for(int i = 0; i<150; i++){
                JSONObject witem = wary.getJSONObject(i);
                
                acc_wm[0][i] = witem.getDouble("accX");acc_wm[1][i] = witem.getDouble("accY");acc_wm[2][i] = witem.getDouble("accZ");
                gy_wm[0][i] = witem.getDouble("gyroX");gy_wm[1][i] = witem.getDouble("gyroY");gy_wm[2][i] = witem.getDouble("gyroZ");
            }
		}
		if(json1.has("longitude")){
			this.setLat(json1.getDouble("longitude"));
			this.setLongi(json1.getDouble("latitude"));
		}
		if(json1.has("voice")){
			this.setAudioSensoryData(Base64.decode(json1.getString("voice")));
		}
	}

	@Override
	public void setUserID(long userID) {
		this.userID = userID;
		// TODO Auto-generated method stub
		
	}

}
