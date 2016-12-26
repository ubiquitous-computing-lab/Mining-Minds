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

package org.uclab.mm.icl.llc.config;

import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.KinectData;
import org.uclab.mm.icl.data.SmartphoneData;


/**
 * This enum class represents the data sent from a device
 * @author Nailbrainz
 *
 */
public enum DeviceType {
	SmartPhone(1), Kinect(2);
    private int value;
    
    /**
     * Unique constructor of the data type.
     * @param _value
     */
    private DeviceType(int _value){
        value = _value;
    }
    
    /**
     * This function parses JSON input string into corresponding data type, which inherits org.uclab.mm.icl.data.Data class
     * @param obj JSON string to parse
     * @return returns the corresponding parsed data
     */
    public DeviceData parseDeviceData(Object obj){
    	switch(value){
    	case 0: //this should not be happen
    		return null;
    	case 1: //smartphone
    		SmartphoneData sd = new SmartphoneData();
    		sd.setData(obj);
    		return sd;
    	case 2: //Kinect
    		KinectData kd = new KinectData();
    		kd.setData(obj);
    		return kd;
    	}
    	return null;
    }
    
    public int getValue(){
    	return value;
    }
}
