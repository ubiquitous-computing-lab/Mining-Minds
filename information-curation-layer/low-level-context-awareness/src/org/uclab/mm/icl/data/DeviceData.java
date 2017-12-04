package org.uclab.mm.icl.data;

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
 * This interface defines the structure of the Sensordata classes, which represents the raw data sent from each devices.
 * @author Nailbrainz
 *
 */
public interface DeviceData {
	
	/**
	 * Getter for the userID of DataType
	 * @return user Id
	 */
	public abstract Long getUserID();
	
	/**
	 * Getter for the userID of DataType 
	 * @param userID will be set into the DataType instance
	 */
	public abstract void setUserID(long userID);
	
	/**
	 * Getter for the getTimeStamp of DataType
	 * @return timeStamp encoded in the data instance
	 */
	public abstract String getTimeStamp();
	
	/**
	 * Getter for the timeStamp of DataType 
	 * @param timeStamp will be set into the DataType instance
	 */
	public abstract void setTimeStamp(String timeStamp);
	
	/**
	 * This function transforms the data sent from each devices, into ICL usable format.
	 * The method of decoding is dependent on each child class
	 * @param input Encoded data sent from device
	 */
	public abstract void setData(Object input);
}
