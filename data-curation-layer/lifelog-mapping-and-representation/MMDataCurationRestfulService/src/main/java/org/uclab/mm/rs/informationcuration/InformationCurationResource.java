/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.rs.informationcuration;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.ic.Device;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserDevice;
import org.uclab.mm.datamodel.ic.UserPreferredLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityAccumulate;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityLog;
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;
import org.uclab.mm.datamodel.ic.dataadapter.DeviceAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.FoodLogAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserDetectedLocationAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserDeviceAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserPreferredLocationAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedActivityAccumulateAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedActivityAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedActivityLogAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedEmotionAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedHLCAdapter;
import org.uclab.mm.rs.datacuration.DataCurationResource;

/**
 * REST Web Service
 *
 * @author Taqdir Ali
 */
@Path("InformationCuration")
public class InformationCurationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InformationCurationResource
     */
    public InformationCurationResource() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DataCurationResource.class);
    
    /**
     * This function is using to Register Device 
     * @param objOuterDevice
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RegisterDevice")
    public List<String> RegisterDevice(Device objOuterDevice) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new DeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveDevice(objOuterDevice);
            logger.info("Device saved successfully, Device Details="+objOuterDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in registring Device");
        	response.add("Error in registering Device");
        }
        return response;
    }
    
    /**
     * This function is using to Update Device 
     * @param objOuterDevice
     * @return aa list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateDevice")
    public List<String> UpdateDevice(Device objOuterDevice) {
        
        List<String> response = new ArrayList<String>();
        String strResponse = "";
        try
        {
            DataAccessInterface objDAInterface = new DeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateDevice(objOuterDevice);
            logger.info("Device updated successfully, Device Details="+objOuterDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated Device");
        	response.add("Error in updated Device");
        }
        return response;
    }
    
     /**
     * This function is using to Retrieves Device by DeviceID 
     * @param DeviceID
     * @return an instance of list of Device
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("Device/{DeviceID}")
    public List<Device> GetDevice(@PathParam ("DeviceID") String DeviceID) {
        Device objOuterDevice = new Device();
        List<Device> objListDevice = new  ArrayList<Device>();
        try
        {
            objOuterDevice.setDeviceId(Long.parseLong(DeviceID));
        
            DataAccessInterface objDAInterface = new DeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListDevice = objADBridge.RetriveDevice(objOuterDevice);
            logger.info("Device loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in Device loadeding");
        }
        return objListDevice;
    }
    

   /**
     * This function is using to Register Device for user.
     * @param objOuterUserDevice
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("SubscribeUserDevice")
    public List<String> SubscribeUserDevice(UserDevice objOuterUserDevice) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserDevice(objOuterUserDevice);
            logger.info("Device registered successfully, User Details="+objOuterUserDevice);

        }
        catch(Exception ex)
        {
        	logger.info("Error in registering Device");
        	response.add("Error in registering Device");
        }
        return response;
    }
    
     /**
     * This function is using to subscribe the sensors of interest.
     * @param objListUserDevice
     * @return an object of string
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("subscribeToSensorsOfInterest")
    public List<String> subscribeToSensorsOfInterest(List<UserDevice> objListUserDevice) {
        List<String> response = new ArrayList<String>();
        try{
                
            UserDevice objUserDevice = new UserDevice();
            for(int i = 0; i < objListUserDevice.size(); i++)
            {
                objUserDevice = objListUserDevice.get(i);
                DataAccessInterface objDAInterface = new UserDeviceAdapter();
                AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
                response = objADBridge.UpdateUserDevice(objUserDevice);
            }
            logger.info("Sensor subscribed successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in subscribing sensor");
        	response.add("Error in subscribing sensor");
        }
        return response;
    }
    
    /**
     * This function is using to Update User Device 
     * @param objOuterUserDevice
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserDevice")
    public List<String> UpdateUserDevice(UserDevice objOuterUserDevice) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserDevice(objOuterUserDevice);
            logger.info("User Device Updated successfully,  User Device Details="+objOuterUserDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating  User Device");
        	response.add("Error in updating  User Device");
        }
        
        return response;
    }
    
    /**
     * This function is using to Retrieves User Device by UserDeviceID 
     * @param UserDeviceID
     * @return an instance of list of UserDevice
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserDevice/{UserDeviceID}")
    public List<UserDevice> GetUserDevice(@PathParam ("UserDeviceID") String UserDeviceID) {
        UserDevice objOuterUserDevice = new UserDevice();
        List<UserDevice> objListUserDevice = new  ArrayList<UserDevice>();
        try
        {
            objOuterUserDevice.setUserDeviceID(Long.parseLong(UserDeviceID));
        
            DataAccessInterface objDAInterface = new UserDeviceAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserDevice = objADBridge.RetriveUserDevice(objOuterUserDevice);
            logger.info("User device loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading user device");
        }
        return objListUserDevice;
    }
    
    /**
     * This function is using to Add user recognized activities 
     * @param objOuterUserRecognizedActivity
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRecognizedActivity")
    public List<String> AddUserRecognizedActivity(UserRecognizedActivity objOuterUserRecognizedActivity) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRecognizedActivity(objOuterUserRecognizedActivity);
            logger.info("user recognized activities saved successfully, user recognized activities Details="+objOuterUserRecognizedActivity);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding  user recognized activities");
        	response.add("Error in adding user recognized activities");
        }
        return response;
    }
    
    /**
     * This function is using to  Retrieves users recognized activities by userid, start time and end time
     * @param objOuterUserRecognizedActivity
     * @return an instance of list of UserRecognizedActivity
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivity")
    public List<UserRecognizedActivity> GetUserRecognizedActivity(UserRecognizedActivity objOuterUserRecognizedActivity) {
        
        List<UserRecognizedActivity> objListUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
        String strResponse = "";
        objOuterUserRecognizedActivity.setRequestType("UserDateRange");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivity = objADBridge.RetriveUserRecognizedActivity(objOuterUserRecognizedActivity);
            logger.info("users recognized activities successfully loaded");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading users recognized activities");
        }
        return objListUserRecognizedActivity;
    }
    
    /**
     * This function is using to Retrieves users accumulative recognized activities by userid, start time and end time
     * @param objOuterUserRecognizedActivity
     * @return an instance of list of UserRecognizedActivity
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivityAccumulate")
    public List<UserRecognizedActivity> GetUserRecognizedActivityAccumulate(UserRecognizedActivity objOuterUserRecognizedActivity) {
        
        List<UserRecognizedActivity> objListUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
        String strResponse = "";
        objOuterUserRecognizedActivity.setRequestType("UserDateRangeAccumulate");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivity = objADBridge.RetriveUserRecognizedActivity(objOuterUserRecognizedActivity);
            logger.info("users recognized accumulative activities successfully loaded");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading users recognized accumulative activities");
        }
        return objListUserRecognizedActivity;
    }
    
    
     /**
     * This function is using to Add User Detected Location
     * @param objOuterUserDetectedLocation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserDetectedLocation")
    public List<String> AddUserDetectedLocation(UserDetectedLocation objOuterUserDetectedLocation) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDetectedLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserDetectedLocation(objOuterUserDetectedLocation);
            logger.info("User Detected Location saved successfully, User Detected Location Details="+objOuterUserDetectedLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Detected Location");
        	response.add("Error in adding User Detected Location");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Detected Location by userid, start time and end time
     * @param objOuterUserDetectedLocation
     * @return an instance of list of UserDetectedLocation
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserDetectedLocation")
    public List<UserDetectedLocation> GetUserDetectedLocation(UserDetectedLocation objOuterUserDetectedLocation) {
        
        List<UserDetectedLocation> objListUserDetectedLocation = new ArrayList<UserDetectedLocation>();
        objOuterUserDetectedLocation.setRequestType("UserDateRange");
        try
        {
            DataAccessInterface objDAInterface = new UserDetectedLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserDetectedLocation = objADBridge.RetriveUserDetectedLocation(objOuterUserDetectedLocation);
            logger.info("User Detected Location laoded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Detected Location");
        }
        
        return objListUserDetectedLocation;
    }
    
    /**
     * This function is using to Add user recognized activities Log
     * @param objOuterUserRecognizedActivityLog
     * @return  a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRecognizedActivityLog")
    public List<String> AddUserRecognizedActivityLog(UserRecognizedActivityLog objOuterUserRecognizedActivityLog) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRecognizedActivityLog(objOuterUserRecognizedActivityLog);
            logger.info("user recognized activities Log saved successfully, User Details="+objOuterUserRecognizedActivityLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding user recognized activities Log");
        	response.add("Error in adding user recognized activities Log");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves users recognized activities log by userid, start time and end time
     * @param objOuterUserRecognizedActivityLog
     * @return an instance of list of UserRecognizedActivityLog
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivityLog")
    public List<UserRecognizedActivityLog> GetUserRecognizedActivityLog(UserRecognizedActivityLog objOuterUserRecognizedActivityLog) {
        List<UserRecognizedActivityLog> objListUserRecognizedActivityLog = new ArrayList<UserRecognizedActivityLog>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivityLog = objADBridge.RetriveUserRecognizedActivityLog(objOuterUserRecognizedActivityLog);
            logger.info("users recognized activities log laoded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading users recognized activities log");
        }
        return objListUserRecognizedActivityLog;
    }
    
    /**
     * This function is using to Add User Recognized Activity Accumulate
     * @param objOuterUserRecognizedActivityAccumulate
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRecognizedActivityAccumulateForNotCalling")
    public List<String> AddUserRecognizedActivityAccumulateForNotCalling(UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAccumulateAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRecognizedActivityAccumulate(objOuterUserRecognizedActivityAccumulate);
            logger.info("User Recognized Activity Accumulate saved successfully, User Details="+objOuterUserRecognizedActivityAccumulate);        
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized Activity Accumulate");
        	response.add("Error in adding User Recognized Activity Accumulate");
        }
        return response;
    }
    
    /**
     * This function is using to Update User Recognized Activity Accumulate
     * @param objOuterUserRecognizedActivityAccumulate
     * @return  a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserRecognizedActivityAccumulate")
    public List<String> UpdateUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAccumulateAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserRecognizedActivityAccumulate(objOuterUserRecognizedActivityAccumulate);
            logger.info("Recognized Activity Accumulate updated successfully, User Details="+objOuterUserRecognizedActivityAccumulate);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recognized Activity Accumulate");
        	response.add("Error in updating Recognized Activity Accumulate");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Recognized Activity Accumulate by userid, start time and end time
     * @param objOuterUserRecognizedActivityAccumulate
     * @return an instance of list of UserRecognizedActivityAccumulate
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivityAccumulateByUserAndDate")
    public List<UserRecognizedActivityAccumulate> GetUserRecognizedActivityAccumulateByUserAndDate(UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate) {
        List<UserRecognizedActivityAccumulate> objListUserRecognizedActivityAccumulate = new ArrayList<UserRecognizedActivityAccumulate>();
        objOuterUserRecognizedActivityAccumulate.setRequestType("UserOneDate");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAccumulateAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivityAccumulate = objADBridge.RetriveUserRecognizedActivityAccumulate(objOuterUserRecognizedActivityAccumulate);
            logger.info("Recognized Activity Accumulate are loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recognized Activity Accumulate");
        }
        return objListUserRecognizedActivityAccumulate;
    }
    
    /**
     * This function is using to Retrieves User Recognized Activity Accumulate by userid, start time and end time
     * @param objOuterUserRecognizedActivityAccumulate
     * @return an instance of list of UserRecognizedActivityAccumulate
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivityAccumulateByUserAndDateRange")
    public List<UserRecognizedActivityAccumulate> GetUserRecognizedActivityAccumulateByUserAndDateRange(UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate) {
        
        List<UserRecognizedActivityAccumulate> objListUserRecognizedActivityAccumulate = new ArrayList<UserRecognizedActivityAccumulate>();
        objOuterUserRecognizedActivityAccumulate.setRequestType("UserDateRange");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAccumulateAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivityAccumulate = objADBridge.RetriveUserRecognizedActivityAccumulate(objOuterUserRecognizedActivityAccumulate);
            logger.info("Recognized Activity Accumulate are loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recognized Activity Accumulate");
        }
        return objListUserRecognizedActivityAccumulate;
    }
    
    /**
     * This function is using to Retrieves User Recognized Activity Accumulate by ActivitiesID, start time and end time
     * @param objOuterUserRecognizedActivityAccumulate
     * @return an instance of list of UserRecognizedActivityAccumulate
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedActivityAccumulateByActivitiesAndDateRange")
    public List<UserRecognizedActivityAccumulate> GetUserRecognizedActivityAccumulateByActivitiesAndDateRange(UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate) {
        List<UserRecognizedActivityAccumulate> objListUserRecognizedActivityAccumulate = new ArrayList<UserRecognizedActivityAccumulate>();
        objOuterUserRecognizedActivityAccumulate.setRequestType("ActivitiesDateRange");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedActivityAccumulateAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivityAccumulate = objADBridge.RetriveUserRecognizedActivityAccumulate(objOuterUserRecognizedActivityAccumulate);
            logger.info("Recognized Activity Accumulate are loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recognized Activity Accumulate");
        }
        return objListUserRecognizedActivityAccumulate;
    }
    
    /**
     *This function is using to  Add User Recognized Emotion
     * @param objOuterUserRecognizedEmotion
     * @return an object of string
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRecognizedEmotion")
    public List<String> AddUserRecognizedEmotion(UserRecognizedEmotion objOuterUserRecognizedEmotion) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedEmotionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRecognizedEmotion(objOuterUserRecognizedEmotion);
            logger.info("User Recognized Emotion saved successfully, User Recognized Emotion Details="+objOuterUserRecognizedEmotion);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized Emotion");
        	response.add("Error in adding User Recognized Emotion");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Recognized emotion by userid, start time and end time
     * @param objOuterUserRecognizedEmotion
     * @return an instance of list of UserRecognizedEmotion
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedEmotionByUserIDDateRange")
    public List<UserRecognizedEmotion> GetUserRecognizedEmotionByUserIDDateRange(UserRecognizedEmotion objOuterUserRecognizedEmotion) {
        
        List<UserRecognizedEmotion> objListUserRecognizedEmotion = new ArrayList<UserRecognizedEmotion>();
        objOuterUserRecognizedEmotion.setRequestType("UserDateRange");
        String strResponse = "";
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedEmotionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedEmotion = objADBridge.RetriveUserRecognizedEmotion(objOuterUserRecognizedEmotion);
            logger.info("User Recognized emotion are loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Recognized emotion");
        }
        return objListUserRecognizedEmotion;
    }
    
    /**
     * This function is using to Add User Recognized HighLC
     * @param objOuterUserRecognizedHLC
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRecognizedHLC")
    public List<String> AddUserRecognizedHLC(UserRecognizedHLC objOuterUserRecognizedHLC) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedHLCAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRecognizedHLC(objOuterUserRecognizedHLC);
            logger.info("User Recognized HighLC saved successfully, User Recognized HighLC Details="+objOuterUserRecognizedHLC);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized HighLC");
        	response.add("Error in adding User Recognized HighLC");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Recognized HLC by userid, start time and end time
     * @param objOuterUserRecognizedHLC
     * @return an instance of list of UserRecognizedHLC
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserRecognizedHLCByUserIDDateRange")
    public List<UserRecognizedHLC> GetUserRecognizedHLCByUserIDDateRange(UserRecognizedHLC objOuterUserRecognizedHLC) {
        List<UserRecognizedHLC> objListUserRecognizedHLC = new ArrayList<UserRecognizedHLC>();
        objOuterUserRecognizedHLC.setRequestType("UserDateRange");
        try
        {
            DataAccessInterface objDAInterface = new UserRecognizedHLCAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedHLC = objADBridge.RetriveUserRecognizedHLC(objOuterUserRecognizedHLC);
            logger.info("User Recognized HLC are loaded successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Recognized HLC");
        }
        return objListUserRecognizedHLC;
    }
    
    /**
     * This function is using to Add User Preferred Location
     * @param objOuterUserPreferredLocation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserPreferredLocation")
    public List<String> AddUserPreferredLocation(UserPreferredLocation objOuterUserPreferredLocation) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserPreferredLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserPreferredLocation(objOuterUserPreferredLocation);
            logger.info("User Preferred Location saved successfully, User Details="+objOuterUserPreferredLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Preferred Location");
        	response.add("Error in adding User Preferred Location");
        }
        return response;
    }
    
    /**
     * This function is using to Update User Preferred Location
     * @param objOuterUserPreferredLocation
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserPreferredLocation")
    public List<String> UpdateUserPreferredLocation(UserPreferredLocation objOuterUserPreferredLocation) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserPreferredLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserPreferredLocation(objOuterUserPreferredLocation);
            logger.info(" User Preferred Location saved successfully,  User Preferred Location Details="+objOuterUserPreferredLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating  User Preferred Location");
        	response.add("Error in updating  User Preferred Location");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves users preferred location by UserID 
     * @param UserID
     * @return an instance of list of UserPreferredLocation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserPreferredLocation/{UserID}")
    public List<UserPreferredLocation> GetUserPreferredLocation(@PathParam ("UserID") String UserID) {
        UserPreferredLocation objOuterUserPreferredLocation = new UserPreferredLocation();
        List<UserPreferredLocation> objListUserPreferredLocation = new  ArrayList<UserPreferredLocation>();
        try
        {
            objOuterUserPreferredLocation.setUserId(Long.parseLong(UserID));
            DataAccessInterface objDAInterface = new UserPreferredLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserPreferredLocation = objADBridge.RetriveUserPreferredLocation(objOuterUserPreferredLocation);
            logger.info("users preferred location loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading  users preferred location");
        }
        return objListUserPreferredLocation;
    }
    
    /**
     * This function is using to Retrieves food log by userid, start time and end time
     * @param objOuterFoodLog
     * @return an instance of list of FoodLog
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetFoodLogByUserIDDateRange")
    public List<FoodLog> GetFoodLogByUserIDDateRange(FoodLog objOuterFoodLog) {
        List<FoodLog> objListFoodLog = new ArrayList<FoodLog>();
        try
        {
            DataAccessInterface objDAInterface = new FoodLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFoodLog = objADBridge.RetriveFoodLog(objOuterFoodLog);
            logger.info("food log loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading  food log");
        }
        return objListFoodLog;
    }
    
     /**
     * This function is using to Add food log
     * @param objOuterFoodLog
     * @return an object of string
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddFoodLog")
    public List<String> AddFoodLog(FoodLog objOuterFoodLog) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new FoodLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveFoodLog(objOuterFoodLog);
            logger.info("food log saved successfully, food log Details="+objOuterFoodLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding food log");
        	response.add("Error in adding food log");
        }
        return response;
    }
}
