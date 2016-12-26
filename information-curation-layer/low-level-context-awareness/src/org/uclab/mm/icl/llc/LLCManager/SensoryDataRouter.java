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

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.uclab.mm.icl.LLCServer;
import org.uclab.mm.icl.MainServlet;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.KinectData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.LLCRecognizer.AudioEmotionRecognizer;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.llc.restservices.RestServices;

import com.sun.jersey.core.util.Base64;


/**
 * Sensory Data router class. Parses and routes the raw data to corresponding user instance, 
 * ultimately to the right recognizer inside the user instance
 * @author Nailbrainz
 *
 */
public class SensoryDataRouter implements Runnable{
	private final static Logger logger = Logger.getLogger(SensoryDataRouter.class);
	
	DeviceType it;
	Object data;
	LLCServer server;
	
	/**
	 * Unique constructor of SensoryDataRouter
	 * @param it sensor type the current router is dealing with
	 * @param data Data to route to user
	 * @param server LLCServer contains the user classes, which the data will be routed
	 */
	public SensoryDataRouter(DeviceType it, Object data, LLCServer server){
		this.it = it;
		this.data = data;
		this.server = server;
	}

	/**
	 * THis function routes the data sent from server, with the method defined in each SensorType Enum. 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DeviceData d = it.parseDeviceData(data);
		User user = server.getUser(d.getUserID());
		user.fillBuffer(it, d, d.getTimeStamp());
	}
	
}
