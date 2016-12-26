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
package org.uclab.mm.icl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.LLCManager.SensoryDataRouter;
import org.uclab.mm.icl.llc.LLCManager.User;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.utils.TimeUtil;

import com.hp.hpl.jena.ontology.OntModel;
import com.sun.jersey.core.util.Base64;

/**
 * LLC server instance
 * @author Dong Uk, Kang
 *
 */
public class LLCServer {
	private final static Logger logger = Logger.getLogger(LLCServer.class);
	OntModel ontModel;
	ServerSocket server;
	Socket socket;
	ICLServerThread thread;
	TimeUtil util = new TimeUtil();
	final static ExecutorService es = Executors.newCachedThreadPool();  //we have to share users among all different LLCServer
	
	
	/**
	 * returns the server thread
	 * 
	 * @return current server thread instance
	 */
	public ICLServerThread getST() {
		return thread;
	}

	/**
	 * returns the instance of corresponding user. When it cannot find any,
	 * instantiate the user and returns the new one
	 * 
	 * @param userID
	 *            user ID to get/generate instance
	 * @return user instance corresponding to user id
	 */
	public User getUser(long userID) {
		if (MainServlet.getUserMap().get(userID) == null) {
			logger.info("User null, creating new user");
			User user =new User(userID, es);
			MainServlet.getUserMap().put(userID, user);
		}
		return MainServlet.getUserMap().get(userID);
	}

	public User getUserDemo(long userID) {
		return MainServlet.getUserMap().get(userID);
	}

	
	
	/**
	 * Routes the sensory data from smartphone to user
	 * 
	 * @param sd
	 *            sensory data to route
	 */
	public void RouteData(DeviceType it, Object data) {
		//call the router with input data
		es.execute(new SensoryDataRouter(it, data, this));
	}

	
	/**
	 * Initiate ICL server with socket
	 * 
	 * @param port port number to assign socket server
	 */
	public LLCServer(int port) {
		final LLCServer thisServ = this;
		es.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					server = new ServerSocket(port);
					logger.info("ICL Server started at " + port);
					while (true) {
						socket = server.accept();
						thread = new ICLServerThread(server, socket, thisServ);
						thread.start();
					}
				} catch (IOException ioe) {
					logger.error("Exception occured at accepting socket from smartphone");
					logger.error(ioe);
				}
			}
		});

	}

}
