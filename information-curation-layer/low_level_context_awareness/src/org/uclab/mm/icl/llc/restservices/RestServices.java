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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.uclab.mm.icl.MainServlet;
import org.uclab.mm.icl.llc.AR.var.KinectARClassification;
import org.uclab.mm.icl.llc.AR.var.KinectARFeatureExtraction4;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.utils.TimeUtil;
import java.lang.reflect.Type;

/**
 * Rest services of LLCA and HLCA. Need to be seperated when modularized
 * @author Nailbrainz
 *
 */
@Path("/llc")
public class RestServices {
	private final static Logger logger = Logger.getLogger(RestClients.class);
	
	
	
	/**
	 * Rest endpoint for testing.
	 * 
	 * @return alive message
	 * @throws JSONException
	 */
	@Path("/start")
	@GET
	public Response convertFtoCfromInput() throws JSONException {
		logger.info("ICL Running!!");
		return null;
	}

	/**
	 * receives the data from Kinect
	 * @param incomingData kinect skeleton data (Json formated string)
	 * @return status of the update
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateKinect(InputStream incomingData) {
		logger.info("update endpoint called");
		StringBuilder crunchifyBuilder = null;
		synchronized (this) {
			crunchifyBuilder = new StringBuilder();

			String line = null;
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
				while ((line = in.readLine()) != null) {
					crunchifyBuilder.append(line);
				}
			} catch (Exception e) {
				logger.error("Error at rcvd string stream from Kinect!");
				logger.error(e);
			}
		}
		if (MainServlet.server == null) {
			logger.error("Main server null");
		} else {
			try {
				//MainServlet.server.getUser(userID).fillBuffer(DataType.Kinect, crunchifyBuilder.toString());
				JSONObject obj = new JSONObject(crunchifyBuilder.toString());
				MainServlet.server.RouteData(DeviceType.Kinect, obj.getString("data"));
			} catch (Exception e) {
				logger.error(e);
			}
		}

		JSONObject ret = new JSONObject();
		ret.put("response", "success");
		return Response.status(200).entity(ret.toString()).type(MediaType.APPLICATION_JSON).build();
	}

	String timeStamp = "";

	

	static AtomicBoolean lock = new AtomicBoolean(true);

	
	/**
	 * This function update the low level context label into HLC tdb.
	 * @param incomingData JSON data, which contains low level labels
	 * @return status of the update
	 */
	@POST
	@Path("/updatellc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLLC(InputStream incomingData) {
		//
		//
		//
		while (!lock.get()) {
			lock.set(false);
		}
		StringBuilder crunchifyBuilder = new StringBuilder();
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));

			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			logger.error("Error at parsing llc from LLCA, in HLCA");
			logger.error(e);
		}
		lock.set(true);
		JSONObject input = new JSONObject(crunchifyBuilder.toString());
		String label = input.getString("label");
		String userID = input.getString("userID");
		String timeStamp = input.getString("timeStamp");

		logger.info("------------HLCA server received change in the llc----------");
		logger.info(label + "   " + userID + "    " + timeStamp);
		logger.info("---------------------------------------------------------------");
		
		
		
		
		TimeUtil util = new TimeUtil();
		//MainServlet.sserver.mapLLC(label, userID, util.parseString(timeStamp));
		JSONObject retJson = new JSONObject();
		retJson.put("response", "success");
		
		return Response.status(200).entity(retJson.toString()).type(MediaType.APPLICATION_JSON).build();
		// return Response.ok(retJson, MediaType.APPLICATION_JSON).build();
		// return
		// return Response.status(200).entity("Mapping LLC Successful").build();
	}

	
	/**
	 * This function update the HLC label into TDB
	 * @param incomingData JSON data, which contains the HLC labels
	 * @return status of the update
	 */
	@POST
	@Path("/updatehlc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateHLC(InputStream incomingData) {
		
		
		StringBuilder crunchifyBuilder = new StringBuilder();
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));

			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		/*
		 * Scanner scan = null; scan = new
		 * Scanner(crunchifyBuilder.toString()).useDelimiter(",|\n"); String
		 * label = scan.next(); String userID = scan.next(); String timeStamp =
		 * scan.next();
		 */
		JSONObject input = new JSONObject(crunchifyBuilder.toString());
		String label = input.getString("label");
		String userID = input.getString("userID");
		String timeStamp = input.getString("timeStamp");

		logger.info("------------LLCA received change in the hlc----------");
		logger.info(label + "   " + userID + "    " + timeStamp);
		logger.info("---------------------------------------------------------------");
		/*
		 * FileUtil.WriteHLCLog(
		 * "------------Restful server received change in the llc----------");
		 * FileUtil.WriteHLCLog(label + "   " + userID + "    " + timeStamp);
		 * FileUtil.WriteHLCLog(
		 * "---------------------------------------------------------------");
		 */
		TimeUtil util = new TimeUtil();
		System.out.println("Null ? " + label + " and " + userID + " and " + timeStamp);
		//MainServlet.sserver.mapLLC(label, userID, util.parseString(timeStamp));
		JSONObject retJson = new JSONObject();
		retJson.put("response", "success");
		return Response.status(200).entity(retJson.toString()).type(MediaType.APPLICATION_JSON).build();
		// return Response.ok(retJson, MediaType.APPLICATION_JSON).build();
		// return
		// return Response.status(200).entity("Mapping LLC Successful").build();
	}

	
	static final public boolean isFromMao = false;
	
	@POST
	@Path("/updatesensordata")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSensorData(InputStream incomingData) {
		
		StringBuilder data = new StringBuilder();
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));

			while ((line = in.readLine()) != null) {
				data.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		
		if(isFromMao){
			
			
			JSONObject wrapper = new JSONObject(data);
			for(DeviceType datatype : DeviceType.values()){ //for each datatype
				String contentsLabel = "contentBody_"+datatype.getValue(); //the value should match with the number defined in DCL
				if(wrapper.getString(contentsLabel) != null){ //check whether the data exists for each datatype
					MainServlet.server.RouteData(datatype, wrapper.getString(contentsLabel)); //byte encoded string
				}
				
			}
			
			
			JSONObject retJson = new JSONObject();
			retJson.put("response", "success");
			return Response.status(200).entity(retJson.toString()).type(MediaType.APPLICATION_JSON).build();
		}else{
			
			MainServlet.server.RouteData(DeviceType.SmartPhone, data.toString());
			JSONObject retJson = new JSONObject();
			retJson.put("response", "success");
			return Response.status(200).entity(retJson.toString()).type(MediaType.APPLICATION_JSON).build();
		}
		//
		//
		//
		
		// return Response.ok(retJson, MediaType.APPLICATION_JSON).build();
		// return
		// return Response.status(200).entity("Mapping LLC Successful").build();
	}
/*
	@GET
	@Path("/VARService/version")
	@Produces(MediaType.APPLICATION_JSON)
	public Response version() {
		JSONObject ret = new JSONObject();
		ret.put("result", "VARAPI version 1.0!");
		return Response.status(200).entity(ret.toString()).type(MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/VARService/classifyWithDefaultModel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String classifyWithDefaultModel(@Context ServletContext context, @FormParam("input") String sInput)
			throws IOException {
		String modelFile = context.getRealPath("/J48VAR.model");

		KinectARFeatureExtraction4 featureExtractor = new KinectARFeatureExtraction4();
		KinectARClassification classifier = new KinectARClassification(modelFile);

		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<ArrayList<Float>>>() {
		}.getType();
		ArrayList<ArrayList<Float>> listInput = gson.fromJson(sInput, listType);

		if (listInput == null)
			return "{\"result\":\"ERROR\", \"message\":\"Input data is null\"}";

		if (listInput.size() != 30)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";

		if (listInput.get(0).size() != 75)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";

		double inputD[][] = new double[30][75];

		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 75; j++)
				inputD[i][j] = listInput.get(i).get(j);

		featureExtractor.setInputData(inputD);
		double[] inputFeatures = featureExtractor.extractFeatures();
		String label = classifier.Classify(inputFeatures);

		return "{\"result\":\"OK\", \"label\":\"" + label + "\"}";
	}

	@POST
	@Path("/VARService/classifyWithCustomIndex")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String classifyWithCustomIndex(@Context ServletContext context, @FormParam("input") String sInput,
			@FormParam("index") String sIndex) throws IOException {

		String modelFile = context.getRealPath("/J48VAR.model");

		KinectARFeatureExtraction4 featureExtractor = new KinectARFeatureExtraction4();
		KinectARClassification classifier = new KinectARClassification(modelFile);

		Gson gson = new Gson();
		Type listTypeDouble = new TypeToken<ArrayList<ArrayList<Double>>>() {
		}.getType();
		ArrayList<ArrayList<Double>> listInput = gson.fromJson(sInput, listTypeDouble);

		Type listTypeInteger = new TypeToken<ArrayList<ArrayList<Integer>>>() {
		}.getType();
		ArrayList<ArrayList<Integer>> listIndex = gson.fromJson(sIndex, listTypeInteger);

		if (listInput == null)
			return "{\"result\":\"ERROR\", \"message\":\"Input data is null\"}";

		if (listInput.size() != 30)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";

		if (listInput.get(0).size() != 75)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";

		if (listIndex == null)
			return "{\"result\":\"ERROR\", \"message\":\"Index is null\"}";

		if (listIndex.size() != 4)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong index size\"}";

		if (listIndex.get(0).size() != 70)
			return "{\"result\":\"ERROR\", \"message\":\"Wrong index size\"}";

		double inputD[][] = new double[30][75];

		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 75; j++)
				inputD[i][j] = listInput.get(i).get(j);

		featureExtractor.setIndex(listIndex);
		featureExtractor.setInputData(inputD);
		double[] inputFeatures = featureExtractor.extractFeatures();
		String label = classifier.Classify(inputFeatures);

		return "{\"result\":\"OK\", \"label\":\"" + label + "\"}";
	}

		/*
		@POST
		@Path("/VARService/uploadCustomModel")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public String uploadCustomModel(@Context ServletContext context, 
				@FormDataParam("model") InputStream uploadedInputStream,
				@FormDataParam("model") FormDataContentDisposition fileDetail) throws IOException {
			try {  
				FileOutputStream out = new FileOutputStream(new File("customModel.model"));  
				int read = 0;  
				byte[] bytes = new byte[1024];  
				out = new FileOutputStream(new File("customModel.model"));  
				while ((read = uploadedInputStream.read(bytes)) != -1) {  
					out.write(bytes, 0, read);  
				}  
				out.flush();  
				out.close();  
				return "{\"result\":\"OK\"}";
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return "{\"result\":\"Failed\"}";
		}
		
		@POST
		@Path("/VARService/classifyWithCustomModel")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public String classifyWithCustomModel(@Context ServletContext context, 
				@FormParam("input") String sInput,
				@FormParam("index") String sIndex) throws IOException {
			
			String modelFile = context.getRealPath("customModel.model");
			
			KinectARFeatureExtraction4 featureExtractor = new KinectARFeatureExtraction4();
			KinectARClassification classifier = new KinectARClassification(modelFile);
			
			Gson gson = new Gson();
		    Type listTypeDouble = new TypeToken<ArrayList<ArrayList<Double>>>(){}.getType();
		    ArrayList<ArrayList<Double>> listInput = gson.fromJson(sInput, listTypeDouble);
		    
		    Type listTypeInteger = new TypeToken<ArrayList<ArrayList<Integer>>>(){}.getType();
		    ArrayList<ArrayList<Integer>> listIndex = gson.fromJson(sIndex, listTypeInteger);
		    
		    if (listInput == null)
		    	return "{\"result\":\"ERROR\", \"message\":\"Input data is null\"}";
		    
		    if (listInput.size() != 30)
		    	return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";
		    
		    if (listInput.get(0).size() != 75)
		    	return "{\"result\":\"ERROR\", \"message\":\"Wrong data size\"}";
		    
		    if (listIndex == null)
		    	return "{\"result\":\"ERROR\", \"message\":\"Index is null\"}";
		    
		    if (listIndex.size() != 4)
		    	return "{\"result\":\"ERROR\", \"message\":\"Wrong index size\"}";
		    
		    if (listIndex.get(0).size() != 70)
		    	return "{\"result\":\"ERROR\", \"message\":\"Wrong index size\"}";
		    
			double inputD[][] = new double[30][75];
			
			for (int i = 0; i < 30; i++)
				for (int j = 0; j < 75; j++)
					inputD[i][j] = listInput.get(i).get(j);
			
			featureExtractor.setIndex(listIndex);
			featureExtractor.setInputData(inputD);
			double[] inputFeatures = featureExtractor.extractFeatures();
			String label = classifier.Classify(inputFeatures);
			
			return "{\"result\":\"OK\", \"label\":\"" + label + "\"}";
		}*/
}
