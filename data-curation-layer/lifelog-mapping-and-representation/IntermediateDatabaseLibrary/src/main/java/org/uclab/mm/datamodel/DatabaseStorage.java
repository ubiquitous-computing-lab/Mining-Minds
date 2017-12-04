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

package org.uclab.mm.datamodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.dc.PhysiologicalFactors;
import org.uclab.mm.datamodel.dc.UserDisabilities;
import org.uclab.mm.datamodel.dc.UserFacilities;
import org.uclab.mm.datamodel.dc.UserRiskFactors;
import org.uclab.mm.datamodel.dc.UserSchedule;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.ic.Device;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.UserAccelerometerData;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserDevice;
import org.uclab.mm.datamodel.ic.UserGPSData;
import org.uclab.mm.datamodel.ic.UserPreferredLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityAccumulate;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityLog;
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;
import org.uclab.mm.datamodel.llm.LifelogMonitorActivity;
import org.uclab.mm.datamodel.sc.Achievements;
import org.uclab.mm.datamodel.sc.ActivityPlan;
import org.uclab.mm.datamodel.sc.ActivityRecommendation;
import org.uclab.mm.datamodel.sc.ActivityRecommendationLog;
import org.uclab.mm.datamodel.sc.Facts;
import org.uclab.mm.datamodel.sc.ProfileData;
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.RecommendationException;
import org.uclab.mm.datamodel.sc.RecommendationExplanation;
import org.uclab.mm.datamodel.sc.Situation;
import org.uclab.mm.datamodel.sc.UserGoal;
import org.uclab.mm.datamodel.sc.UserPreferredActivities;
import org.uclab.mm.datamodel.sc.UserRewards;
import org.uclab.mm.datamodel.sl.ActiveSession;
import org.uclab.mm.datamodel.sl.ActivityFeedback;
import org.uclab.mm.datamodel.sl.ExpertReview;
import org.uclab.mm.datamodel.sl.FactsFeedback;
import org.uclab.mm.datamodel.sl.RecommendationFeedback;
import org.uclab.mm.datamodel.sl.UserAccelerometerDataVisulization;
import org.uclab.mm.datamodel.sl.UserGPSDataVisulization;



/**
 * This is the DatabaseStorage class, it extends the AbstractDataBridge class
 * @author Taqdir
 */
public class DatabaseStorage extends AbstractDataBridge {
    
    private Connection objConnection;
 
    public DatabaseStorage(DataAccessInterface objDAInterface)
    {
       setDataAdapter(objDAInterface);
    }

    private static final Logger logger = LoggerFactory.getLogger(DatabaseStorage.class);
    
    /**
     * This is implementation function for saving User.
     * @param objUser
     * @return List of String 
     */
    @Override
    public List<String> SaveUser(Users objUser) {
        
        List<String> objResponse = new ArrayList<>();
        try
        {
            objDAInterface.ConfigureAdapter(objConnection);
            objResponse = objDAInterface.Save(objUser);
            logger.info("User saved successfully, User Details="+objUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in saving user, User Details=");
        	objResponse.add("Error in saving user");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating User.
     * @param objUser
     * @return List of string
     *  
     */
    @Override
    public List<String> UpdateUser(Users objUser) {
        List<String> objResponse = new ArrayList<>();
        try{
        objDAInterface.ConfigureAdapter(objConnection);
        objResponse = objDAInterface.Update(objUser);     
        logger.info("User udpated successfully, User Details="+objUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating user, User Details=");
        	objResponse.add("Error in saving user");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving User. 
     * @param objUser
     * @return List of Users
     */
    @Override
    public List<Users> RetriveUser(Users objUser) {
    	List<Users> objListUsers = new  ArrayList<Users>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUsers =  objDAInterface.RetriveData(objUser);
            logger.info("User loaded successfully.");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in getting user");
    	}
        return objListUsers;
    }

    /**
     * This is implementation function for deleting User.
     * @param objUser
     * @return List of String 
     */
    @Override
    public List<String> DeleteUser(Users objUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This is implementation function for saving Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
     public List<String> SaveDevice(Device objDevice) {
        
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objDevice);   
        	logger.info("Device saved successfully, Device Details="+objDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in registring Device");
        	objResponse.add("Error in registering Device");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
    public List<String> UpdateDevice(Device objDevice) {
        List<String> objResponse = new ArrayList<>();
        try
        {
	        objDAInterface.ConfigureAdapter(objConnection);
	        objResponse = objDAInterface.Update(objDevice); 
	        logger.info("Device updated successfully, Device Details="+objDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated Device");
        	objResponse.add("Error in updated Device");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
    public List<Device> RetriveDevice(Device objDevice) {
    	List<Device> objListDevice = new  ArrayList<Device>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListDevice =  objDAInterface.RetriveData(objDevice);
            logger.info("Device loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in Device loadeding");
    	}
    	return objListDevice;
    }

    /**
     * This is implementation function for retrieving UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    @Override
    public List<UserDevice> RetriveUserDevice(UserDevice objUserDevice) {
    	List<UserDevice> objListUserDevice = new  ArrayList<UserDevice>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserDevice =  objDAInterface.RetriveData(objUserDevice);
            logger.info("Device loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in Device loadeding");
    	}
       
        return objListUserDevice;
    }

    /**
     * This is implementation function for adding UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    @Override
    public List<String> SaveUserDevice(UserDevice objUserDevice) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserDevice);   
        	logger.info("Device registered successfully, User Details="+objUserDevice);
        }
        catch(Exception ex)
        {
        	logger.info("Error in registering Device");
        	objResponse.add("Error in registering Device");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserDevice. 
     * @param objUserDevice
     * @return List of UserDevice
     */
    @Override
    public List<String> UpdateUserDevice(UserDevice objUserDevice) {
        List<String> objResponse = new ArrayList<>();
        try
        {
	        objDAInterface.ConfigureAdapter(objConnection);
	        objResponse = objDAInterface.Update(objUserDevice); 
	        logger.info("Sensor updated successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in subscribing sensor");
        	objResponse.add("Error in subscribing sensor");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    @Override
    public List<String> SaveUserRiskFactors(UserRiskFactors objUserRiskFactors) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserRiskFactors);  
        	logger.info("Risk factor saved successfully, User Details=" + objUserRiskFactors);
        }
        catch(Exception ex)
        {

        	logger.info("Error in saving risk factor");
        	objResponse.add("Error in saving risk factor");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRiskFactors.
     * @param objUserRiskFactors
     * @return List of UserRiskFactors 
     */
    @Override
    public List<UserRiskFactors> RetriveUserRiskFactors(UserRiskFactors objUserRiskFactors) {
    	List<UserRiskFactors> objListUserRiskFactors = new  ArrayList<UserRiskFactors>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListUserRiskFactors =  objDAInterface.RetriveData(objUserRiskFactors);
    	     logger.info("Risk factor loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in getting risk factor");
    	}
        
        return objListUserRiskFactors;
    }
        
    
    /**
     * This is implementation function for updating UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    @Override
    public List<String> UpdateUserRiskFactors(UserRiskFactors objUserRiskFactors) {
         List<String> objResponse = new ArrayList<>();
         try
         {
        	 objDAInterface.ConfigureAdapter(objConnection);
        	 objResponse = objDAInterface.Update(objUserRiskFactors);
        	 logger.info("Risk factor saved successfully, User Details="+objUserRiskFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating risk factor");
        	objResponse.add("Error in updating risk factor");
        }
        return objResponse;
    }
    
    /**
     * This is implementation function for saving PhysiologicalFactors. 
     * @param objPhysiologicalFactors
     * @return List of String
     */

    @Override
    public List<String> SavePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objPhysiologicalFactors);  
        	logger.info("Physiological factor saved successfully, User Details="+objPhysiologicalFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Physiological factors");
        	objResponse.add("Error in adding Physiological factors");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating PhysiologicalFactors. 
     * @param objPhysiologicalFactors
     * @return List of String
     */
    @Override
    public List<String> UpdatePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors) {
         List<String> objResponse = new ArrayList<>();
         try
         {
        	 objDAInterface.ConfigureAdapter(objConnection);
        	 objResponse = objDAInterface.Update(objPhysiologicalFactors);   
        	 logger.info("Physiological factor updated successfully, User Details="+objPhysiologicalFactors);
         }
        catch(Exception ex)
        {
        	logger.info("Error in updated Physiological factors");
        	objResponse.add("Error in updated Physiological factors");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving PhysiologicalFactors.
     * @param objPhysiologicalFactors
     * @return List of PhysiologicalFactors 
     */
    @Override
    public List<PhysiologicalFactors> RetrivePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors) {
    	List<PhysiologicalFactors> objListPhysiologicalFactors = new  ArrayList<PhysiologicalFactors>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListPhysiologicalFactors =  objDAInterface.RetriveData(objPhysiologicalFactors);
            logger.info("Physiological is loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in Loading Physiological factors");
    	}
        
        return objListPhysiologicalFactors;
    }

    /**
     * This is implementation function for saving UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    @Override
    public List<String> SaveUserFacilities(UserFacilities objUserFacilities) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserFacilities); 
        	logger.info("user Facilities saved successfully, User Details="+objUserFacilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding user Facilities");
        	objResponse.add("Error in adding user Facilities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    @Override
    public List<String> UpdateUserFacilities(UserFacilities objUserFacilities) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objUserFacilities);
        	logger.info("user Facilities saved successfully, UserFacilities Details="+objUserFacilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated user Facilities");
        	objResponse.add("Error in updated user Facilities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserFacilities.
     * @param objUserFacilities
     * @return List of UserFacilities
     */
    @Override
    public List<UserFacilities> RetriveUserFacilities(UserFacilities objUserFacilities) {
    	List<UserFacilities> objListUserFacilities = new  ArrayList<UserFacilities>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserFacilities =  objDAInterface.RetriveData(objUserFacilities);
            logger.info("user Facilities loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading user Facilities");
    	}
        return objListUserFacilities;
    }

    /**
     * This is implementation function for saving UserPreferredActivities.
     * @param objUserPreferredActivities
     * @return List of String
     */
    @Override
    public List<String> SaveUserPreferredActivities(UserPreferredActivities objUserPreferredActivities) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserPreferredActivities);
        	 logger.info("User Preferred Activities saved successfully, User Preferred Activities Details="+objUserPreferredActivities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Preferred Activities");
        	objResponse.add("Error in adding User Preferred Activities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserPreferredActivities. 
     * @param objUserPreferredActivities
     * @return List of String
     */
    @Override
    public List<String> UpdateUserPreferredActivities(UserPreferredActivities objUserPreferredActivities) {
         List<String> objResponse = new ArrayList<>();
         try
         {
        	 objDAInterface.ConfigureAdapter(objConnection);
        	 objResponse = objDAInterface.Update(objUserPreferredActivities); 
        	 logger.info("User Preferred Activities updated successfully, User Preferred Activities Details="+objUserPreferredActivities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating User Preferred Activities");
        	objResponse.add("Error in updating User Preferred Activities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserPreferredActivities. 
     * @param objUserPreferredActivities
     * @return List of UserPreferredActivities
     */
    @Override
    public List<UserPreferredActivities> RetriveUserPreferredActivities(UserPreferredActivities objUserPreferredActivities) {
    	List<UserPreferredActivities> objListUserPreferredActivities = new  ArrayList<UserPreferredActivities>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserPreferredActivities =  objDAInterface.RetriveData(objUserPreferredActivities);
            logger.info("User Preferred Activities Data loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Preferred Activities");
    	}
        return objListUserPreferredActivities;
    }

    /**
     * This is implementation function for saving UserGPSData. 
     * @param objUserGPSData
     * @return List of String
     */
    @Override
    public List<String> SaveUserGPSData(UserGPSData objUserGPSData) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserGPSData);   
        	logger.info("User GPS Data added successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User GPS Data");
        	objResponse.add("Error in adding User GPS Data");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserGPSData. 
     * @param objUserGPSData
     * @return List of UserGPSData
     */
    @Override
    public List<UserGPSData> RetriveUserGPSData(UserGPSData objUserGPSData) {
    	List<UserGPSData> objListUserGPSData = new  ArrayList<UserGPSData>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListUserGPSData =  objDAInterface.RetriveData(objUserGPSData);
    	     logger.info("User GPS Data loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User GPS Data");
    	}
        return objListUserGPSData;
    }

    /**
     * This is implementation function for saving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of String
     */
    @Override
    public List<String> SaveUserAccelerometerData(UserAccelerometerData objUserAccelerometerData) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserAccelerometerData);  
        	logger.info("Use rAccelerometer Data added successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Accelerometer Data");
        	objResponse.add("Error in adding User Accelerometer Data");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of UserAccelerometerData
     */
    @Override
    public List<UserAccelerometerData> RetriveUserAccelerometerData(UserAccelerometerData objUserAccelerometerData) {
    	List<UserAccelerometerData> objListUserAccelerometerData = new  ArrayList<UserAccelerometerData>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserAccelerometerData =  objDAInterface.RetriveData(objUserAccelerometerData);
            logger.info("Use rAccelerometer Data loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Accelerometer Data");
    	}
        
        return objListUserAccelerometerData;
    }

    /**
     * This is implementation function for saving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of String 
     */
    @Override
    public List<String> SaveUserRecognizedActivity(UserRecognizedActivity objUserRecognizedActivity) {
         List<String> objResponse = new ArrayList<>();
         try
         {
        	 objDAInterface.ConfigureAdapter(objConnection);
        	 objResponse = objDAInterface.Save(objUserRecognizedActivity);  
        	 logger.info("user recognized activities saved successfully, user recognized activities Details="+objUserRecognizedActivity);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding  user recognized activities");
        	objResponse.add("Error in adding user recognized activities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of UserRecognizedActivity 
     */
    @Override
    public List<UserRecognizedActivity> RetriveUserRecognizedActivity(UserRecognizedActivity objUserRecognizedActivity) {
    	List<UserRecognizedActivity> objListUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRecognizedActivity =  objDAInterface.RetriveData(objUserRecognizedActivity);
            logger.info("users recognized activities successfully loaded");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading users recognized activities");
    	}
        return objListUserRecognizedActivity;
    }

    /**
     * This is implementation function for saving UserDetectedLocation. 
     * @param objUserDetectedLocation
     * @return List of String
     */
    @Override
    public List<String> SaveUserDetectedLocation(UserDetectedLocation objUserDetectedLocation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserDetectedLocation);   
        	logger.info("User Detected Location saved successfully, User Detected Location Details="+objUserDetectedLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Detected Location");
        	objResponse.add("Error in adding User Detected Location");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserDetectedLocation.
     * @param objUserDetectedLocation
     * @return List of UserDetectedLocation 
     */
    @Override
    public List<UserDetectedLocation> RetriveUserDetectedLocation(UserDetectedLocation objUserDetectedLocation) {
    	List<UserDetectedLocation> objListUserDetectedLocation = new ArrayList<UserDetectedLocation>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListUserDetectedLocation =  objDAInterface.RetriveData(objUserDetectedLocation);
    	     logger.info("User Detected Location laoded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Detected Location");
    	}
        return objListUserDetectedLocation;
    }

    /**
     * This is implementation function for saving UserGoal. 
     * @param objUserGoal
     * @return List of String
     */
    @Override
    public List<String> SaveUserGoal(UserGoal objUserGoal) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserGoal); 
        	logger.info("User Goal saved successfully, User Goal Details="+objUserGoal);
        }
        catch(Exception ex)
        {
        	logger.info("Error in Adding User Goal");
        	objResponse.add("Error in Adding User Goal");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserGoal.
     * @param objUserGoal
     * @return List of UserGoal 
     */
    @Override
    public List<UserGoal> RetriveUserGoal(UserGoal objUserGoal) {
    	 List<UserGoal> objListUserGoal = new  ArrayList<UserGoal>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserGoal =  objDAInterface.RetriveData(objUserGoal);
            logger.info(" User Goal loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading  User Goal");
    	}
        return objListUserGoal;
    }

    /**
     * This is implementation function for saving ActivityPlan. 
     * @param objActivityPlan
     * @return List of String
     */
    @Override
    public List<String> SaveActivityPlan(ActivityPlan objActivityPlan) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objActivityPlan);   
        	logger.info("Activity Plan saved successfully, Activity Plan Details="+objActivityPlan);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Plan");
        	objResponse.add("Error in adding Activity Plan");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving ActivityPlan. 
     * @param objActivityPlan
     * @return List of ActivityPlan
     * 
     */
    @Override
    public List<ActivityPlan> RetriveActivityPlan(ActivityPlan objActivityPlan) {
    	List<ActivityPlan> objListActivityPlan = new  ArrayList<ActivityPlan>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListActivityPlan =  objDAInterface.RetriveData(objActivityPlan);
            logger.info("User Goal loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Goal");
    	}
        return objListActivityPlan;
    }

    /**
     * This is implementation function for saving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of String
     */
    @Override
    public List<String> SaveActivityRecommendation(ActivityRecommendation objActivityRecommendation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objActivityRecommendation);    
        	logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objActivityRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Recommendation");
        	objResponse.add("Error in adding Activity Recommendation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of ActivityRecommendation
     */
    @Override
    public List<ActivityRecommendation> RetriveActivityRecommendation(ActivityRecommendation objActivityRecommendation) {
    	List<ActivityRecommendation> objListActivityRecommendation = new  ArrayList<ActivityRecommendation>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListActivityRecommendation =  objDAInterface.RetriveData(objActivityRecommendation);
            logger.info("Activity Recommendation loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Activity Recommendation");
    	}
        return objListActivityRecommendation;
    }

    /**
     * This is implementation function for validating Users. 
     * @param objUsers
     * @return List of String
     */
    @Override
    public List<String> IsUserValid(Users objUsers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This is implementation function for saving ActiveSession. 
     * @param objActiveSession
     * @return List of String
     */
    @Override
    public List<String> SaveActiveSession(ActiveSession objActiveSession) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objActiveSession);    
        	logger.info("Active Session saved successfully.");
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Active Session");
        }
        return objResponse;
    }

    /**
     * This is implementation function setter of DataAccessInterface. 
     * @param DAInterface
     */
    @Override
    public void setDataAdapter(DataAccessInterface DAInterface) {
        super.objDAInterface = DAInterface;
        
        try
        {
            if(objConnection == null || objConnection.isClosed())
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                objConnection = DriverManager.getConnection("jdbc:sqlserver://BILAL-OFFICE-PC\\SQLEXPRESS;database=DBMiningMindsV1_5;user=sa;password=adminsa;");
                logger.info("Database connected successfully");
            }
        }
        catch (Exception e)
        {
        	logger.info("Error in connecting database");
        }  
    }

    /**
     * This is implementation function for retrieving ProfileData. 
     * @param objProfileData
     * @return List of ProfileData
     */
    @Override
    public List<ProfileData> RetriveProfileData(ProfileData objProfileData) {
    	List<ProfileData> objListProfileData = new  ArrayList<>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListProfileData =  objDAInterface.RetriveData(objProfileData);
            logger.info("Profile Data loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Profile Data");
    	}
        return objListProfileData;
    }
    /**
     * This is implementation function for retrieving UserGPSDataVisulization. 
     * @param objUserGPSData
    * @return List of UserGPSDataVisulization
     */

    @Override
    public List<UserGPSDataVisulization> RetriveUserGPSDataVisulization(UserGPSDataVisulization objUserGPSData) {
    	List<UserGPSDataVisulization> objListUserGPSData = new  ArrayList<UserGPSDataVisulization>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserGPSData =  objDAInterface.RetriveData(objUserGPSData);
            logger.info("User GPS Data Visulization loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User GPS Data Visulization");
    	}
        return objListUserGPSData;
    }
    
    /**
     * This is implementation function for retrieving UserAccelerometerDataVisulization. 
     * @param objUserAccelerometerData
    * @return List of UserAccelerometerDataVisulization
     */
    @Override
    public List<UserAccelerometerDataVisulization> RetriveUserAccelerometerDataVisulization(UserAccelerometerDataVisulization objUserAccelerometerData) {
    	
    	List<UserAccelerometerDataVisulization> objListUserAccelerometerData = new  ArrayList<UserAccelerometerDataVisulization>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserAccelerometerData =  objDAInterface.RetriveData(objUserAccelerometerData);
            logger.info("User Accelerometer Data Visulization loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Accelerometer Data Visulization");
    	}
       
        return objListUserAccelerometerData;
    }

    /**
     * This is implementation function for retrieving UserRewards. 
     * @param objUserRewards
    * @return List of UserRewards
     */
    @Override
    public List<UserRewards> RetriveUserRewards(UserRewards objUserRewards) {
    	List<UserRewards> objListUserRewards = new  ArrayList<UserRewards>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRewards =  objDAInterface.RetriveData(objUserRewards);
            logger.info("User Rewards loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Rewards");
    	}
        return objListUserRewards;
        
    }

    /**
     * This is implementation function for saving UserRewards. 
     * @param objUserRewards
   * @return List of String
     */
    @Override
    public List<String> SaveUserRewards(UserRewards objUserRewards) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserRewards);    
        	logger.info("User Rewards saved successfully, User Rewards Details="+objUserRewards);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Rewards");
        	objResponse.add("Error in adding User Rewards");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserRewards.
     * @param objUserRewards
   * @return List of String 
     */
    @Override
    public List<String> UpdateUserRewards(UserRewards objUserRewards) {
        List<String> objResponse = new ArrayList<>();
        try
       	{
    	   objDAInterface.ConfigureAdapter(objConnection);
    	   objResponse = objDAInterface.Update(objUserRewards);  
    	   logger.info("User Rewards updated successfully, User Rewards Details="+objUserRewards);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating User Rewards");
        	objResponse.add("Error in updating User Rewards");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving UserRecognizedActivityLog. 
     * @param objUserRecognizedActivityLog
   * @return List of String
     */
    @Override
    public List<String> SaveUserRecognizedActivityLog(UserRecognizedActivityLog objUserRecognizedActivityLog) {
       List<String> objResponse = new ArrayList<>();
         try
         {
        	 objDAInterface.ConfigureAdapter(objConnection);
        	 objResponse = objDAInterface.Save(objUserRecognizedActivityLog);  
        	 logger.info("user recognized activities Log saved successfully, User Details="+objUserRecognizedActivityLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding user recognized activities Log");
        	objResponse.add("Error in adding user recognized activities Log");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivityLog. 
     * @param objUserRecognizedActivityLog
     * @return List of UserRecognizedActivityLog
     */
    @Override
    public List<UserRecognizedActivityLog> RetriveUserRecognizedActivityLog(UserRecognizedActivityLog objUserRecognizedActivityLog) {
    	List<UserRecognizedActivityLog> objListUserRecognizedActivityLog = new ArrayList<UserRecognizedActivityLog>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRecognizedActivityLog =  objDAInterface.RetriveData(objUserRecognizedActivityLog);
            logger.info("users recognized activities log laoded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading users recognized activities log");
    	}
        return objListUserRecognizedActivityLog;
    }

    /**
     * This is implementation function for saving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of String
     */
    @Override
    public List<String> SaveActivityRecommendationLog(ActivityRecommendationLog objActivityRecommendationLog) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objActivityRecommendationLog); 
        	logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objActivityRecommendationLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Recommendation");
        	objResponse.add("Error in adding Activity Recommendation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of ActivityRecommendationLog
     */
    @Override
    public List<ActivityRecommendationLog> RetriveActivityRecommendationLog(ActivityRecommendationLog objActivityRecommendationLog) {
    	List<ActivityRecommendationLog> objListActivityRecommendationLog = new  ArrayList<ActivityRecommendationLog>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListActivityRecommendationLog =  objDAInterface.RetriveData(objActivityRecommendationLog);
            logger.info("Activity Recommendation loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Activity Recommendation");
    	}
        return objListActivityRecommendationLog;
    }

    /**
     * This is implementation function for saving Situation. 
     * @param objSituation
     * @return List of String
     */
    @Override
    public List<String> SaveSituation(Situation objSituation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objSituation); 
        	logger.info("situation saved successfully, User Details="+objSituation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding situation");
        	objResponse.add("Error in adding situation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving Situation.
     * @param objSituation
     * @return List of Situation 
     */
    @Override
    public List<Situation> RetriveSituation(Situation objSituation) {
    	List<Situation> objListSituation = new  ArrayList<Situation>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListSituation =  objDAInterface.RetriveData(objSituation);
            logger.info("situation loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading situation");
    	}
        return objListSituation;
    }

    /**
     * This is implementation function for updating Situation. 
     * @param objSituation
     * @return List of String
     */
    @Override
    public List<String> UpdateSituation(Situation objSituation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objSituation); 
        	logger.info("situation saved successfully, situation Details="+objSituation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating situation");
        	objResponse.add("Error in updating situation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    @Override
    public List<String> SaveRecommendation(Recommendation objRecommendation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objRecommendation);  
        	logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Recommendation");
        	objResponse.add("Error in adding Recommendation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving Recommendation. 
     * @param objRecommendation
     * @return List of Recommendation
     */
    @Override
    public List<Recommendation> RetriveRecommendation(Recommendation objRecommendation) {
    	List<Recommendation> objListRecommendation = new  ArrayList<Recommendation>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListRecommendation =  objDAInterface.RetriveData(objRecommendation);
            logger.info("Recommendation loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Recommendation");
    	}
        
        return objListRecommendation;
    }

    /**
     * This is implementation function for updating Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    @Override
    public List<String> UpdateRecommendation(Recommendation objRecommendation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objRecommendation);
        	logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recommendation");
        	objResponse.add("Error in updating Recommendation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving RecommendationException.
     * @param objRecommendationException
     * @return List of String 
     */
    @Override
    public List<String> SaveRecommendationException(RecommendationException objRecommendationException) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objRecommendationException);   
        	logger.info("RecommendationException saved successfully, RecommendationException Details="+objRecommendationException);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding RecommendationException");
        	objResponse.add("Error in adding RecommendationException");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    @Override
    public List<RecommendationException> RetriveRecommendationException(RecommendationException objRecommendationException) {
    	List<RecommendationException> objListRecommendationException = new  ArrayList<RecommendationException>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListRecommendationException =  objDAInterface.RetriveData(objRecommendationException);
            logger.info("Recommendation Exception loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Recommendation Exception");
    	}
        return objListRecommendationException;
    }

    /**
     * This is implementation function for updating RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    @Override
    public List<String> UpdateRecommendationException(RecommendationException objRecommendationException) {
         List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objRecommendationException);   
        	logger.info("RecommendationException updated successfully, RecommendationException Details="+objRecommendationException);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating RecommendationException");
        	objResponse.add("Error in updating RecommendationException");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    @Override
    public List<String> SaveRecommendationExplanation(RecommendationExplanation objRecommendationExplanation) {
         List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objRecommendationExplanation);  
        	logger.info("RecommendationExplanation saved successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding RecommendationExplanation");
        	objResponse.add("Error in adding RecommendationExplanation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of RecommendationExplanation
     */
    @Override
    public List<RecommendationExplanation> RetriveRecommendationExplanation(RecommendationExplanation objRecommendationExplanation) {
    	List<RecommendationExplanation> objListRecommendationExplanation = new  ArrayList<RecommendationExplanation>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListRecommendationExplanation =  objDAInterface.RetriveData(objRecommendationExplanation);
            logger.info("Recommendation Explanation loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Recommendation Explanation");
    	}
        return objListRecommendationExplanation;
    }

    /**
     * This is implementation function for updating RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    @Override
    public List<String> UpdateRecommendationExplanation(RecommendationExplanation objRecommendationExplanation) {
         List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objRecommendationExplanation);  
        	logger.info("RecommendationExplanation updated successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating RecommendationExplanation");
        	objResponse.add("Error in updating RecommendationExplanation");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */
    @Override
    public List<String> SaveRecommendationFeedback(RecommendationFeedback objRecommendationFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objRecommendationFeedback);   
        	logger.info("Recommendation Feedback saved successfully, Recommendation Feedback Details="+objRecommendationFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Recommendation Feedback");
        	objResponse.add("Error in adding Recommendation Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of RecommendationFeedback
     */
    @Override
    public List<RecommendationFeedback> RetriveRecommendationFeedback(RecommendationFeedback objRecommendationFeedback) {
    	List<RecommendationFeedback> objListRecommendationFeedback = new  ArrayList<RecommendationFeedback>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListRecommendationFeedback =  objDAInterface.RetriveData(objRecommendationFeedback);
            logger.info("Recommendation Feedback loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Recommendation Feedback");
    	}
        
        return objListRecommendationFeedback;
    }
    
    /**
     * This is implementation function for updating RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */

    @Override
    public List<String> UpdateRecommendationFeedback(RecommendationFeedback objRecommendationFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objRecommendationFeedback);
        	logger.info("Recommendation Feedback updated successfully, Recommendation Feedback Details="+objRecommendationFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recommendation Feedback");
        	objResponse.add("Error in updating Recommendation Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    @Override
    public List<String> SaveActivityFeedback(ActivityFeedback objActivityFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objActivityFeedback);  
        	logger.info("Activity Feedback saved successfully, Activity Feedback Details="+objActivityFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Feedback");
        	objResponse.add("Error in adding Activity Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of ActivityFeedback
     */
    @Override
    public List<ActivityFeedback> RetriveActivityFeedback(ActivityFeedback objActivityFeedback) {
    	List<ActivityFeedback> objListActivityFeedback = new  ArrayList<ActivityFeedback>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListActivityFeedback =  objDAInterface.RetriveData(objActivityFeedback);
            logger.info("recommendation Feedback loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading recommendation Feedback");
    	}
        
        return objListActivityFeedback;
    }

    /**
     * This is implementation function for updating ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    @Override
    public List<String> UpdateActivityFeedback(ActivityFeedback objActivityFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objActivityFeedback);  
        	logger.info("Activity Feedback updated successfully, Activity Feedback Details="+objActivityFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Activity Feedback");
        	objResponse.add("Error in updating Activity Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    @Override
    public List<String> SaveExpertReview(ExpertReview objExpertReview) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objExpertReview); 
        	logger.info("Expert Review saved successfully, Expert Review Details="+objExpertReview);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Expert Review");
        	objResponse.add("Error in adding Expert Review");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving ExpertReview. 
     * @param objExpertReview
     * @return List of ExpertReview
     */
    @Override
    public List<ExpertReview> RetriveExpertReview(ExpertReview objExpertReview) {
    	List<ExpertReview> objListExpertReview = new  ArrayList<ExpertReview>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListExpertReview =  objDAInterface.RetriveData(objExpertReview);
    	     logger.info("Expert Review loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Expert Review");
    	}
        return objListExpertReview;
    }

    /**
     * This is implementation function for updating ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    @Override
    public List<String> UpdateExpertReview(ExpertReview objExpertReview) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objExpertReview);
        	logger.info("Expert Review updated successfully, Expert Review Details="+objExpertReview);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Expert Review");
        	objResponse.add("Error in updating Expert Review");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    @Override
    public List<String> SaveUserDisabilities(UserDisabilities objUserDisabilities) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserDisabilities);  
        	logger.info("User Disabilities saved successfully, User Disabilities Details="+objUserDisabilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Disabilities");
        	objResponse.add("Error in adding User Disabilities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    @Override
    public List<String> UpdateUserDisabilities(UserDisabilities objUserDisabilities) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objUserDisabilities); 
        	logger.info("User Disabilities updated successfully, User Details="+objUserDisabilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated User Disabilities");
        	objResponse.add("Error in updated User Disabilities");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of UserDisabilities
     */
    @Override
    public List<UserDisabilities> RetriveUserDisabilities(UserDisabilities objUserDisabilities) {
    	List<UserDisabilities> objListUserDisabilities = new  ArrayList<UserDisabilities>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserDisabilities =  objDAInterface.RetriveData(objUserDisabilities);
            logger.info("User Disabilities loaded successfully, User Details=");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Disabilities");
    	}
        return objListUserDisabilities;
    }

    /**
     * This is implementation function for saving UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    @Override
    public List<String> SaveUserSchedule(UserSchedule objUserSchedule) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserSchedule);  
        	logger.info(" User Schedule saved successfully, User Details="+objUserSchedule);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Schedule");
        	objResponse.add("Error in adding User Schedule");
        }
        return objResponse;
    }

    /**
     * This is implementation function for updating UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    @Override
    public List<String> UpdateUserSchedule(UserSchedule objUserSchedule) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objUserSchedule);   
        	logger.info("User Schedule saved successfully, User Details="+objUserSchedule);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated User Schedule");
        	objResponse.add("Error in updated User Schedule");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserSchedule.
     * @param objUserSchedule
     * @return List of UserSchedule 
     */
    @Override
    public List<UserSchedule> RetriveUserSchedule(UserSchedule objUserSchedule) {
    	List<UserSchedule> objListUserSchedule = new ArrayList<UserSchedule>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserSchedule =  objDAInterface.RetriveData(objUserSchedule);
            logger.info("User Schedule loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Schedule");
    	}
        
        return objListUserSchedule;
    }

    /**
     * This is implementation function for saving LifelogMonitorActivity. 
     * @param objLifelogMonitorActivity
     * @return List of LifelogMonitorActivity
     */ 
    @Override
    public List<LifelogMonitorActivity> RetriveLifelogMonitorActivity(LifelogMonitorActivity objLifelogMonitorActivity) {
    	List<LifelogMonitorActivity> objListLifelogMonitorActivity = new ArrayList<LifelogMonitorActivity>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListLifelogMonitorActivity =  objDAInterface.RetriveData(objLifelogMonitorActivity);
    	     logger.info("Lifelog Monitor Activity loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Lifelog Monitor Activity");
    	}
        return objListLifelogMonitorActivity;
    }

    /**
     * This is implementation function for saving Facts. 
     * @param objFacts
     * @return List of String
     */
    @Override
    public List<String> SaveFacts(Facts objFacts) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objFacts);  
        	logger.info("Facts saved successfully, Facts Feedback Details="+objFacts);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Facts");
        	objResponse.add("Error in adding Facts");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving Facts. 
     * @param objFacts
     * @return List of Facts
     */
    @Override
    public List<Facts> RetriveFacts(Facts objFacts) {
    	List<Facts> objListFacts = new  ArrayList<Facts>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListFacts =  objDAInterface.RetriveData(objFacts);
            logger.info("Facts loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Facts");
    	}
        
        return objListFacts;
    }

    /**
     * This is implementation function for updating Facts. 
     * @param objFacts
     * @return List of String
     */
    @Override
    public List<String> UpdateFacts(Facts objFacts) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objFacts);
        	logger.info("Facts updated successfully, Facts Details="+objFacts);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Facts");
        	objResponse.add("Error in updating Facts");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    @Override
    public List<String> SaveFactsFeedback(FactsFeedback objFactsFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objFactsFeedback);
        	logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objFactsFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Facts Feedback");
        	objResponse.add("Error in adding Facts Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving FactsFeedback.
     * @param objFactsFeedback
     * @return List of FactsFeedback 
     */
    @Override
    public List<FactsFeedback> RetriveFactsFeedback(FactsFeedback objFactsFeedback) {
    	List<FactsFeedback> objListFactsFeedback = new  ArrayList<FactsFeedback>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListFactsFeedback =  objDAInterface.RetriveData(objFactsFeedback);
            logger.info("Facts Feedback loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Facts Feedback");
    	}
        return objListFactsFeedback;
    }

    /**
     * This is implementation function for updating FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    @Override
    public List<String> UpdateFactsFeedback(FactsFeedback objFactsFeedback) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objFactsFeedback); 
        	logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objFactsFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Facts Feedback");
        	objResponse.add("Error in adding Facts Feedback");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of String
     */
    @Override
    public List<String> SaveUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserRecognizedActivityAccumulate);  
        	logger.info("User Recognized Activity Accumulate saved successfully, User Details="+objUserRecognizedActivityAccumulate);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized Activity Accumulate");
        	objResponse.add("Error in adding User Recognized Activity Accumulate");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of RetriveUserRecognizedActivityAccumulate
     */
    @Override
    public List<UserRecognizedActivityAccumulate> RetriveUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate) {
    	List<UserRecognizedActivityAccumulate> objListUserRecognizedActivityAccumulate = new ArrayList<UserRecognizedActivityAccumulate>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRecognizedActivityAccumulate =  objDAInterface.RetriveData(objUserRecognizedActivityAccumulate);
            logger.info("Recognized Activity Accumulate are loaded successfully.");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Recognized Activity Accumulate");
    	}
        
        return objListUserRecognizedActivityAccumulate;
    }

    /**
     * This is implementation function for updating UserRecognizedActivityAccumulate.
     * @param objUserRecognizedActivityAccumulate
     * @return List of String 
     */
    @Override
    public List<String> UpdateUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objUserRecognizedActivityAccumulate);  
        	logger.info("Recognized Activity Accumulate updated successfully, User Details="+objUserRecognizedActivityAccumulate);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recognized Activity Accumulate");
        	objResponse.add("Error in updating Recognized Activity Accumulate");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving Achievements. 
     * @param objAchievements
     * @return List of String
     */
    @Override
    public List<String> SaveAchievements(Achievements objAchievements) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objAchievements); 
        	logger.info("Achievements saved successfully, Achievements Details="+objAchievements);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Achievements");
        	objResponse.add("Error in adding Achievements");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving Achievements. 
     * @param objAchievements
     * @return List of Achievements
     */
    @Override
    public List<Achievements> RetriveAchievements(Achievements objAchievements) {
    	List<Achievements> objListAchievements = new  ArrayList<Achievements>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListAchievements =  objDAInterface.RetriveData(objAchievements);
    		logger.info("Achievements loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading Achievements");
    	}
        return objListAchievements;
    }

    /**
     * This is implementation function for updating Achievements. 
     * @param objAchievements
     * @return List of String
     */
    @Override
    public List<String> UpdateAchievements(Achievements objAchievements) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objAchievements);  
        	logger.info("Achievements updated successfully, Achievements Details="+objAchievements);    
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Achievements");
        	objResponse.add("Error in updating Achievements");
        }
        return objResponse;
    }

    /**
     * This is implementation function for saving UserRecognizedEmotion. 
     * @param objUserRecognizedEmotion
     * @return List of String
     */
    @Override
    public List<String> SaveUserRecognizedEmotion(UserRecognizedEmotion objUserRecognizedEmotion) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserRecognizedEmotion);
        	logger.info("User Recognized Emotion saved successfully, User Recognized Emotion Details="+objUserRecognizedEmotion);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized Emotion");
        	objResponse.add("Error in adding User Recognized Emotion");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRecognizedEmotion.
     * @param objUserRecognizedEmotion
     * @return List of RetriveUserRecognizedEmotion 
     */
    @Override
    public List<UserRecognizedEmotion> RetriveUserRecognizedEmotion(UserRecognizedEmotion objUserRecognizedEmotion) {
    	List<UserRecognizedEmotion> objListUserRecognizedEmotion = new ArrayList<UserRecognizedEmotion>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRecognizedEmotion =  objDAInterface.RetriveData(objUserRecognizedEmotion);
            logger.info("User Recognized emotion are loaded successfully.");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Recognized emotion");
    	}
        
        return objListUserRecognizedEmotion;
    }

    /**
     * This is implementation function for saving UserRecognizedHLC. 
     * @param objUserRecognizedHLC
     * @return List of String
     */
    @Override
    public List<String> SaveUserRecognizedHLC(UserRecognizedHLC objUserRecognizedHLC) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserRecognizedHLC);
        	logger.info("User Recognized HighLC saved successfully, User Recognized HighLC Details="+objUserRecognizedHLC);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Recognized HighLC");
        	objResponse.add("Error in adding User Recognized HighLC");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserRecognizedHLC.
     * @param objUserRecognizedHLC
     * @return List of UserRecognizedHLC 
     */
    @Override
    public List<UserRecognizedHLC> RetriveUserRecognizedHLC(UserRecognizedHLC objUserRecognizedHLC) {
    	List<UserRecognizedHLC> objListUserRecognizedHLC = new ArrayList<UserRecognizedHLC>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserRecognizedHLC =  objDAInterface.RetriveData(objUserRecognizedHLC);
            logger.info("User Recognized HLC are loaded successfully.");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading User Recognized HLC");
    	}
    	
        return objListUserRecognizedHLC;
    }

    /**
     * This is implementation function for saving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    @Override
    public List<String> SaveUserPreferredLocation(UserPreferredLocation objUserPreferredLocation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objUserPreferredLocation); 
        	logger.info("User Preferred Location saved successfully, User Details="+objUserPreferredLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Preferred Location");
        	objResponse.add("Error in adding User Preferred Location");
        }
        return objResponse;
    }

    /**
     * This is implementation function for retrieving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of UserPreferredLocation
     */
    @Override
    public List<UserPreferredLocation> RetriveUserPreferredLocation(UserPreferredLocation objUserPreferredLocation) {
    	List<UserPreferredLocation> objListUserPreferredLocation = new  ArrayList<UserPreferredLocation>();
    	try
    	{
    		objDAInterface.ConfigureAdapter(objConnection);
            objListUserPreferredLocation =  objDAInterface.RetriveData(objUserPreferredLocation);
            logger.info("users preferred location loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading  users preferred location");
    	}
        
        return objListUserPreferredLocation;
    }

    /**
     * This is implementation function for updating UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    @Override
    public List<String> UpdateUserPreferredLocation(UserPreferredLocation objUserPreferredLocation) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Update(objUserPreferredLocation);   
        	logger.info(" User Preferred Location saved successfully,  User Preferred Location Details="+objUserPreferredLocation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating  User Preferred Location");
        	objResponse.add("Error in updating  User Preferred Location");
        }
        return objResponse;
    }
    
    /**
     * This is implementation function for saving FoodLog. 
     * @param objFoodLog
     * @return List of String
     */
     @Override
    public List<String> SaveFoodLog(FoodLog objFoodLog) {
        List<String> objResponse = new ArrayList<>();
        try
        {
        	objDAInterface.ConfigureAdapter(objConnection);
        	objResponse = objDAInterface.Save(objFoodLog);
        	logger.info("food log saved successfully, food log Details="+objFoodLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding food log");
        	objResponse.add("Error in adding food log");
        }
        return objResponse;
    }

     /**
      * This is implementation function for retrieving FoodLog.
      * @param objFoodLog
      * @return List of FoodLog 
      */
    @Override
    public List<FoodLog> RetriveFoodLog(FoodLog objFoodLog) {
    	List<FoodLog> objListFoodLog = new ArrayList<FoodLog>();
    	try
    	{
    		 objDAInterface.ConfigureAdapter(objConnection);
    	     objListFoodLog =  objDAInterface.RetriveData(objFoodLog);
    	     logger.info("food log loaded successfully");
    	}
    	catch(Exception ex)
    	{
    		logger.info("Error in loading  food log");
    	}
        return objListFoodLog;
    }
}
