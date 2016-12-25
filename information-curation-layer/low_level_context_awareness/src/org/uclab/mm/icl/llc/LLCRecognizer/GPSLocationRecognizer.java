/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.LLCRecognizer;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.LLCManager.MajorityVoting;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.utils.TimeChecker;
import org.uclab.mm.icl.llc.config.RecognizerType;

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
 * Data class for GPSLocationRecognizer
 * @author Nailbrainz
 *
 */
class GPSData{
	public double getLongi() {
		return longi;
	}
	public double getLat() {
		return lat;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	
	@SuppressWarnings("unused")
	private GPSData() {}
	
	public GPSData(double longi, double lat, String timeStamp) {
		this.longi = longi;
		this.lat = lat;
		this.timeStamp = timeStamp;
	}
	double longi;
	double lat;
	String timeStamp;
}

/**
 * Recognizer class to recognize the user specific location, from the geoposition GPS data (latitude, longitude)
 * @author Nailbrainz
 */
public class GPSLocationRecognizer extends LLCRecognizer<GPSData>{
	
	private final static Logger logger = Logger.getLogger(GPSLocationRecognizer.class);
	//public static String[] places = {"Office", "Home","Yard","Gym","Mall","Restaurant", "NoLocation"};
	//public static String[] places = {"Office", "Home","Yard","Gym","Mall", "Restaurant"}; 
	
	
    final int longiInd = 0, latInd = 1; 
    double points[][];
    String[] Labels;
    
    /**
     * Unique constructor of Location Recognizer.
     * @param userID
     */
    public GPSLocationRecognizer(long userID){
    	super(userID);
    	
    	double[][] coord = new double[6][2];
    	coord[0][0] = 127.08348;//long
    	coord[0][1] = 37.23979;//lat
    	
    	coord[1][0] = 127.081292;
    	coord[1][1] = 37.242645528;
    	
    	coord[2][0] = 127.08106;
    	coord[2][1] = 37.24080;
    	
    	coord[3][0] = 127.07660;
    	coord[3][1] = 37.24404;
    	
    	coord[4][0] = 127.07703;
    	coord[4][1] = 37.24654;
    	
    	coord[5][0] = 127.07881;
    	coord[5][1] = 37.24196;
    	
    	String locname[] = {"Office", "Home","Yard","Gym","Mall", "Restaurant"};
    	//여기서 유저이름으로 DCL 레스트 서비스에서 통신해서 개인위치 받아야 함
    	
    	
        this.points = coord;
        this.Labels = locname;
    }
    
    
    /**
     * Returns the output context type of the recognizer
     */
	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.LR;//RecognizerType.GPSLocation;
	}

	/**
	 * Recognizes the user location from the given longitude, latitude GPS coordinate.
	 */
	@Override
	public ContextLabel recognize(GPSData input, String timeStamp) {
		// TODO Auto-generated method stub
		double minD = 987654321;
		int minI = -1;
		
		for(int i = 0; i<points.length; i++){
			double val = CalculationByDistance(points[i][0], points[i][1], input.getLat(), input.getLongi());
			if(val < minD){
				minD = val;
				minI = i;
			}
			
		}
	/*	
		try {
		      ////////////////////////////////////////////////////////////////
		      BufferedWriter out = new BufferedWriter(new FileWriter("D:/out.txt", true));

		      out.write(Labels[minI]); out.newLine();

		      out.close();
		      ////////////////////////////////////////////////////////////////
		    } catch (IOException e) {
		        System.err.println(e); // 에러가 있다면 메시지 출력
		        System.exit(1);
		    }
*/
		if(minI == -1){
			if(verbose)logger.info("NoLocation!");
			if(verbose)logger.info(userID + "\t" + "NoLocation" + "\t" + timeStamp + "\t" + input.getLat() + "\t" + input.getLongi());
			 return ContextType.Location.getBasicLabel(userID, timeStamp);
		}
		if(verbose)logger.info("Location recognized! : " + userID + "\t" + Labels[minI] + "\t" + timeStamp + "\t" + input.getLat() + "\t" + input.getLongi());
		
		long localTime = TimeChecker.time;
		return new ContextLabel(userID,  Labels[minI], timeStamp, ContextType.Location);
	}

	
	
	/**
	 * Calculate the distance between 2 different (lat,long) geopositions
	 * @param initialLat first lat coordinate
	 * @param initialLong first long coordinate
	 * @param finalLat second lat coordinate
	 * @param finalLong second long coordinate
	 * @return the distance between two geopositions.
	 */
	public static double CalculationByDistance(double initialLat, double initialLong, double finalLat, double finalLong){
	    /*PRE: All the input values are in radians!*/

	    double latDiff = finalLat - initialLat;
	    double longDiff = finalLong - initialLong;
	    double earthRadius = 6371; //In Km if you want the distance in km

	    double distance = 2*earthRadius*Math.asin(Math.sqrt(Math.pow(Math.sin(latDiff/2.0),2)+Math.cos(initialLat)*Math.cos(finalLat)*Math.pow(Math.sin(longDiff/2),2)));

	    return distance;

	}
	
	/**
	 *  Calculate the distance between 2 different (lat,long) geopositions
	 * @param lat1 first lat coordinate
	 * @param lng1 first long coordinate
	 * @param lat2 second lat coordinate
	 * @param lng2 second long  coordinate
	 * @return
	 */
	 public static double meterFromLL(double lat1, double lng1, double lat2, double lng2) {
		    double earthRadius = 6371000; //meters
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    double dist = (double) (earthRadius * c);

		    return dist;
	}

	/**
	 * From the each datatype, consumes the data required to recognize the location context
	 */
	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		if(it.equals(DeviceType.SmartPhone)){
			SmartphoneData data = (SmartphoneData) obj;
			setData(new GPSData(data.getLongi(), data.getLat(), data.getTimeStamp()));
		}
	}

/*

	@Override
	public Runnable getRunnable(String timeStamp, LLCUnifier unifier) {
		// TODO Auto-generated method stub
		return new GPSRunnable(unifier);
	}

	class GPSRunnable implements Runnable{
		LLCUnifier unifier;
		
		public GPSRunnable(LLCUnifier unifier){
			this.unifier = unifier;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			double[] ary = new double[2];
			ary[0] = lat;
			ary[1] = longi;
			if(ary[0] == -1){
				logger.info("Cannot recognize location!");
				return;
			}
			unifier.push(recognize(ary, timeStamp), RecognizerType.LR);
			lat = 0;
		}
	}
	
 */
}
