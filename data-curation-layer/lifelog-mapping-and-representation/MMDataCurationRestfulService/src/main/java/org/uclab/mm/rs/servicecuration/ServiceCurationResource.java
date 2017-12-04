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
package org.uclab.mm.rs.servicecuration;

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
import org.uclab.mm.datamodel.dc.dataadapter.UserDataAdapter;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;
import org.uclab.mm.datamodel.ic.dataadapter.FoodLogAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserDetectedLocationAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedActivityAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedEmotionAdapter;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedHLCAdapter;
import org.uclab.mm.datamodel.sc.Achievements;
import org.uclab.mm.datamodel.sc.ActivityPlan;
import org.uclab.mm.datamodel.sc.ActivityRecommendation;
import org.uclab.mm.datamodel.sc.ActivityRecommendationLog;
import org.uclab.mm.datamodel.sc.CurrentContextPacket;
import org.uclab.mm.datamodel.sc.Facts;
import org.uclab.mm.datamodel.sc.ProfileData;
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.RecommendationException;
import org.uclab.mm.datamodel.sc.RecommendationExplanation;
import org.uclab.mm.datamodel.sc.Situation;
import org.uclab.mm.datamodel.sc.UserGoal;
import org.uclab.mm.datamodel.sc.UserPreferredActivities;
import org.uclab.mm.datamodel.sc.UserRewards;
import org.uclab.mm.datamodel.sc.dataadapter.AchievementsAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.ActivityPlanAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.ActivityRecommendationAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.ActivityRecommendationLogAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.FactsAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.ProfileDataAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.RecommendationAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.RecommendationExceptionAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.RecommendationExplanationAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.SituationAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.UserGoalAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.UserPreferredActivitiesAdapter;
import org.uclab.mm.datamodel.sc.dataadapter.UserRewardsAdapter;
import org.uclab.mm.rs.datacuration.DataCurationResource;

/**
 * REST Web Service
 *
 * @author Taqdir Ali
 */
@Path("ServiceCuration")
public class ServiceCurationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceCurationResource
     */
    public ServiceCurationResource() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DataCurationResource.class);
    
    /**
     * This function is using to Retrieves Profile Data by UserID 
     * @param UserID
     * @return an instance of List of ProfileData
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ProfileData/{UserID}")
    public List<ProfileData> GetProfileData(@PathParam ("UserID") String UserID) {
        ProfileData objOuterProfileData = new ProfileData();
        List<ProfileData> objListProfileData = new  ArrayList<>();
        try
        {
            objOuterProfileData.setUserID(Long.parseLong(UserID));
            DataAccessInterface objDAInterface = new ProfileDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListProfileData = objADBridge.RetriveProfileData(objOuterProfileData);
            logger.info("Profile Data loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Profile Data");
        }
        return objListProfileData;
    }
    
    /**
     * This function is using to Add User Preferred Activities
     * @param objOuterUserPreferredActivities
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserPreferredActivities")
    public List<String> AddUserPreferredActivities(UserPreferredActivities objOuterUserPreferredActivities) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserPreferredActivitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserPreferredActivities(objOuterUserPreferredActivities);
            logger.info("User Preferred Activities saved successfully, User Preferred Activities Details="+objOuterUserPreferredActivities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Preferred Activities");
        	response.add("Error in adding User Preferred Activities");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves users preferred activities by UserID 
     * @param UserID
     * @return an instance of list of UserPreferredActivities
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserPreferredActivities/{UserID}")
    public List<UserPreferredActivities> GetUserPreferredActivities(@PathParam ("UserID") String UserID) {
        UserPreferredActivities objOuterUserPreferredActivities = new UserPreferredActivities();
        List<UserPreferredActivities> objListUserPreferredActivities = new  ArrayList<UserPreferredActivities>();
        try
        {
            objOuterUserPreferredActivities.setUserId(Long.parseLong(UserID));
            DataAccessInterface objDAInterface = new UserPreferredActivitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserPreferredActivities = objADBridge.RetriveUserPreferredActivities(objOuterUserPreferredActivities);
            logger.info("User Preferred Activities Data loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Profile Data");
        }
        return objListUserPreferredActivities;
    }
    
    
    /**
     * This function is using to Update User Preferred Activities
     * @param objOuterUserPreferredActivities
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserPreferredActivities")
    public List<String> UpdateUserPreferredActivities(UserPreferredActivities objOuterUserPreferredActivities) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserPreferredActivitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserPreferredActivities(objOuterUserPreferredActivities);
            logger.info("User Preferred Activities updated successfully, User Preferred Activities Details="+objOuterUserPreferredActivities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating User Preferred Activities");
        	response.add("Error in updating User Preferred Activities");
        }
        return response;
    }
    
    /**
     * This function is using to Add User Goal
     * @param objOuterUserGoal
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserGoal")
    public List<String> AddUserGoal(UserGoal objOuterUserGoal) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserGoalAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserGoal(objOuterUserGoal);
            logger.info("User Goal saved successfully, User Goal Details="+objOuterUserGoal);
        }
        catch(Exception ex)
        {
        	logger.info("Error in Adding User Goal");
        	response.add("Error in Adding User Goal");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Goal by UserID 
     * @param UserID
     * @return an instance of list of UserGoal
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserGoal/{UserID}")
    public List<UserGoal> GetUserGoal(@PathParam ("UserID") String UserID) {
        UserGoal objOuterUserGoal = new UserGoal();
        List<UserGoal> objListUserGoal = new  ArrayList<UserGoal>();
        try
        {
            objOuterUserGoal.setUserId(Long.parseLong(UserID));
            objOuterUserGoal.setRequestType("ByUserOnly");
            DataAccessInterface objDAInterface = new UserGoalAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserGoal = objADBridge.RetriveUserGoal(objOuterUserGoal);
            logger.info(" User Goal loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading  User Goal");
        }
        return objListUserGoal;
    }
    
    /**
     * This function is using to Retrieves User Goal by UserID, start time and end time
     * @param objOuterUserGoal
     * @return an instance of list of UserGoal
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveUserBurnedCaloriesByDate")
    public List<UserGoal> RetrieveUserBurnedCaloriesByDate(UserGoal objOuterUserGoal) {
        List<UserGoal> objListUserGoal = new  ArrayList<UserGoal>();
        try
        {
            objOuterUserGoal.setRequestType("ByUserandDate");
            DataAccessInterface objDAInterface = new UserGoalAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserGoal = objADBridge.RetriveUserGoal(objOuterUserGoal);
            logger.info("User Goal loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Goal");
        }
        return objListUserGoal;
    }
    
    /**
     * This function is using to add Activity Plan
     * @param objOuterActivityPlan
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddActivityPlan")
    public List<String> AddActivityPlan(ActivityPlan objOuterActivityPlan) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ActivityPlanAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveActivityPlan(objOuterActivityPlan);
            logger.info("Activity Plan saved successfully, Activity Plan Details="+objOuterActivityPlan);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Plan");
        	response.add("Error in adding Activity Plan");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Goal by UserGoalID 
     * @param UserGoalID
     * @return an instance of list of ActivityPlan
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ActivityPlan/{UserGoalID}")
    public List<ActivityPlan> GetActivityPlan(@PathParam ("UserGoalID") String UserGoalID) {
        ActivityPlan objOuterActivityPlan = new ActivityPlan();
        List<ActivityPlan> objListActivityPlan = new  ArrayList<ActivityPlan>();
        try
        {
            objOuterActivityPlan.setUserGoalId(Long.parseLong(UserGoalID));
            DataAccessInterface objDAInterface = new ActivityPlanAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityPlan = objADBridge.RetriveActivityPlan(objOuterActivityPlan);
            logger.info("User Goal loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Goal");
        }
        return objListActivityPlan;
    }
    
     /**
     * This function is using to add Activity Recommendation
     * @param objOuterActivityRecommendation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddActivityRecommendation")
    public List<String> AddActivityRecommendation(ActivityRecommendation objOuterActivityRecommendation) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ActivityRecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveActivityRecommendation(objOuterActivityRecommendation);
            logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objOuterActivityRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Recommendation");
        	response.add("Error in adding Activity Recommendation");
        }
        return response;
    }
    
    /**
     * This function is using to add Activity Recommendation for log
     * @param objOuterActivityRecommendationLog
     * @return  a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddActivityRecommendationLog")
    public List<String> AddActivityRecommendationLog(ActivityRecommendationLog objOuterActivityRecommendationLog) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ActivityRecommendationLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveActivityRecommendationLog(objOuterActivityRecommendationLog);
            logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objOuterActivityRecommendationLog);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Recommendation");
        	response.add("Error in adding Activity Recommendation");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieve Activity Recommendation By ActivityPlanID 
     * @param ActivityPlanID
     * @return an instance of list of ActivityRecommendation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ActivityRecommendation/{ActivityPlanID}")
    public List<ActivityRecommendation> RetrieveActivityRecommendationByActivityPlanID(@PathParam ("ActivityPlanID") String ActivityPlanID) {
        ActivityRecommendation objOuterActivityRecommendation = new ActivityRecommendation();
        List<ActivityRecommendation> objListActivityRecommendation = new  ArrayList<ActivityRecommendation>();
        try
        {
            objOuterActivityRecommendation.setActivityPlanId(Long.parseLong(ActivityPlanID));
            objOuterActivityRecommendation.setRequestType("ByPlan");
            
            DataAccessInterface objDAInterface = new ActivityRecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityRecommendation = objADBridge.RetriveActivityRecommendation(objOuterActivityRecommendation);
            logger.info("Activity Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Activity Recommendation");
        }
        return objListActivityRecommendation;
    }
    
    /**
     * This function is using to Retrieve Activity Recommendation Log By ActivityPlanID 
     * @param ActivityPlanID
     * @return an instance of list of ActivityRecommendationLog
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ActivityRecommendationLog/{ActivityPlanID}")
    public List<ActivityRecommendationLog> RetrieveActivityRecommendationLogByActivityPlanID(@PathParam ("ActivityPlanID") String ActivityPlanID) {
        ActivityRecommendationLog objOuterActivityRecommendationLog = new ActivityRecommendationLog();
        List<ActivityRecommendationLog> objListActivityRecommendationLog = new  ArrayList<ActivityRecommendationLog>();
        try
        {
            objOuterActivityRecommendationLog.setActivityPlanId(Long.parseLong(ActivityPlanID));
            objOuterActivityRecommendationLog.setRequestType("ByPlan");
            
            DataAccessInterface objDAInterface = new ActivityRecommendationLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityRecommendationLog = objADBridge.RetriveActivityRecommendationLog(objOuterActivityRecommendationLog);
            logger.info("Activity Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Activity Recommendation");
        }
        return objListActivityRecommendationLog;
    }
    
    /**
     * This function is using to Retrieves User Activity Recommendation by UserID and date range
     * @param objOuterActivityRecommendation
     * @return an instance of list of ActivityRecommendation
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveActivityRecommendationByUserAndDate")
    public List<ActivityRecommendation> RetrieveActivityRecommendationByUserAndDate(ActivityRecommendation objOuterActivityRecommendation) {
        List<ActivityRecommendation> objListActivityRecommendation = new  ArrayList<ActivityRecommendation>();
        try
        {
            objOuterActivityRecommendation.setRequestType("ByUserDate");
            DataAccessInterface objDAInterface = new ActivityRecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityRecommendation = objADBridge.RetriveActivityRecommendation(objOuterActivityRecommendation);
            logger.info("Activity Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Activity Recommendation");
        }
        return objListActivityRecommendation;
    }
    
    /**
     * This function is using to Retrieves User Activity Recommendation Log by UserID and date range
     * @param objOuterActivityRecommendationLog
     * @return an instance of list of ActivityRecommendationLog 
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveActivityRecommendationLogByUserAndDate")
    public List<ActivityRecommendationLog> RetrieveActivityRecommendationLogByUserAndDate(ActivityRecommendationLog objOuterActivityRecommendationLog) {
        List<ActivityRecommendationLog> objListActivityRecommendationLog = new  ArrayList<ActivityRecommendationLog>();
        try
        {
            objOuterActivityRecommendationLog.setRequestType("ByUserDate");
            DataAccessInterface objDAInterface = new ActivityRecommendationLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityRecommendationLog = objADBridge.RetriveActivityRecommendationLog(objOuterActivityRecommendationLog);
            logger.info("Activity Recommendation log loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Activity Recommendation log");
        }
        return objListActivityRecommendationLog;
    }
    
    /**
     * This function is using to  Retrieves User Rewards by UserID 
     * @param UserID
     * @return an instance of list of UserRewards
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserRewards/{UserID}")
    public List<UserRewards> GetUserRewards(@PathParam ("UserID") String UserID) {
        UserRewards objOuterUserRewards = new UserRewards();
        List<UserRewards> objListUserRewards = new  ArrayList<UserRewards>();
        try
        {
            objOuterUserRewards.setUserID(Long.parseLong(UserID));
                       
            DataAccessInterface objDAInterface = new UserRewardsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRewards = objADBridge.RetriveUserRewards(objOuterUserRewards);
            logger.info(" User Rewards loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading  User Rewards");
        }
        return objListUserRewards;
    }
    
    /**
     * This function is using to update User Rewards
     * @param objOuterUserRewards
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserRewards")
    public List<String> UpdateUserRewards(UserRewards objOuterUserRewards) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRewardsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserRewards(objOuterUserRewards);
            logger.info("User Rewards updated successfully, User Rewards Details="+objOuterUserRewards);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating User Rewards");
        	response.add("Error in updating User Rewards");
        }
        return response;
    }
    
    /**
     * This function is using to add User Rewards
     * @param objOuterUserRewards
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRewards")
    public List<String> AddUserRewards(UserRewards objOuterUserRewards) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRewardsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRewards(objOuterUserRewards);
            logger.info("User Rewards saved successfully, User Rewards Details="+objOuterUserRewards);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Rewards");
        	response.add("Error in adding User Rewards");
        }
        return response;
    }
    
    /**
     * This function is using to Add situation
     * @param objOuterSituation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddSituation")
    public List<String> AddSituation(Situation objOuterSituation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new SituationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveSituation(objOuterSituation);
            logger.info("situation saved successfully, User Details="+objOuterSituation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding situation");
        	response.add("Error in adding situation");
        }
        return response;
    }
    
    /**
     * This function is using to Update situation
     * @param objOuterSituation
     * @return  a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateSituation")
    public List<String> UpdateSituation(Situation objOuterSituation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new SituationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateSituation(objOuterSituation);
            logger.info("situation saved successfully, situation Details="+objOuterSituation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating situation");
        	response.add("Error in updating situation");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves situation by UserID 
     * @param UserID
     * @return an instance of list of Situation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("Situation/{UserID}")
    public List<Situation> RetrieveSituationByUserID(@PathParam ("UserID") String UserID) {
        Situation objOuterSituation = new Situation();
        List<Situation> objListSituation = new  ArrayList<Situation>();
        try
        {
            objOuterSituation.setUserID(Long.parseLong(UserID));
            objOuterSituation.setRequestType("ByUserOnly");
            
            DataAccessInterface objDAInterface = new SituationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListSituation = objADBridge.RetriveSituation(objOuterSituation);
            logger.info("situation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading situation");
        }
        return objListSituation;
    }
    
    /**
     * This function is using to Retrieves Situation by UserID and date range
     * @param objOuterSituation
     * @return an instance of list of Situation
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveSituationByUserIDDate")
    public List<Situation> RetrieveSituationByUserIDDate(Situation objOuterSituation) {
        
        List<Situation> objListSituation = new  ArrayList<Situation>();
        try
        {
            objOuterSituation.setRequestType("ByUserandDate");
            
            DataAccessInterface objDAInterface = new SituationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListSituation = objADBridge.RetriveSituation(objOuterSituation);
            logger.info("situation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading situation");
        }
        return objListSituation;
    }
    
    /**
     *  This function is using to Retrieves situation by SituationID 
     * @param SituationID
     * @return an instance of list of Situation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("SituationBySituationID/{SituationID}")
    public List<Situation> RetrieveSituationBySituationID(@PathParam ("SituationID") String SituationID) {
        Situation objOuterSituation = new Situation();
        List<Situation> objListSituation = new  ArrayList<Situation>();
        try
        {
            objOuterSituation.setSituationID(Long.parseLong(SituationID));
            objOuterSituation.setRequestType("BySituationID");
            
            DataAccessInterface objDAInterface = new SituationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListSituation = objADBridge.RetriveSituation(objOuterSituation);
            logger.info("situation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading situation");
        }
        return objListSituation;
    }
    
    
    /**
     *  This function is using to Add Recommendation
     * @param objRecommendation
     * @return  a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddRecommendation")
    public List<String> AddRecommendation(Recommendation objRecommendation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveRecommendation(objRecommendation);
            logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Recommendation");
        	response.add("Error in adding Recommendation");
        }
        return response;
    }
    
    /**
     * This function is using to Update Recommendation
     * @param objRecommendation
     * @return list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateRecommendation")
    public List<String> UpdateRecommendation(Recommendation objRecommendation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateRecommendation(objRecommendation);
            logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recommendation");
        	response.add("Error in updating Recommendation");
        }
       return response;
    }
    
    /**
     * This function is using to Retrieves Recommendation by UserID 
     * @param UserID
     * @return an instance of list of Recommendation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("Recommendation/{UserID}")
    public List<Recommendation> RetrieveRecommendationByUserID(@PathParam ("UserID") String UserID) {
        Recommendation objOuterRecommendation = new Recommendation();
        List<Recommendation> objListRecommendation = new  ArrayList<Recommendation>();
        try
        {
            objOuterRecommendation.setUserID(Long.parseLong(UserID));
            objOuterRecommendation.setRequestType("ByUserOnly");
            
            DataAccessInterface objDAInterface = new RecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendation = objADBridge.RetriveRecommendation(objOuterRecommendation);
            logger.info("Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation");
        }
        return objListRecommendation;
    }
    
    /**
     * This function is using to Retrieves Recommendation by UserID and date range
     * @param objOuterRecommendation
     * @return an instance of list of Recommendation
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveRecommendationByUserIDDate")
    public List<Recommendation> RetrieveRecommendationByUserIDDate(Recommendation objOuterRecommendation) {
        
        List<Recommendation> objListRecommendation = new  ArrayList<Recommendation>();
        try
        {
            objOuterRecommendation.setRequestType("ByUserandDate");
            
            DataAccessInterface objDAInterface = new RecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendation = objADBridge.RetriveRecommendation(objOuterRecommendation);
            logger.info("Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation");
        }
        
        return objListRecommendation;
    }
    
    /**
     * This function is using to Retrieves Recommendation by Situation Category ID and date range
     * @param objOuterRecommendation
     * @return an instance of list of Recommendation
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveRecommendationByDateRangeActivityIDs")
    public List<Recommendation> RetrieveRecommendationByDateRangeActivityIDs(Recommendation objOuterRecommendation) {
        
        List<Recommendation> objListRecommendation = new  ArrayList<Recommendation>();
        try
        {
            objOuterRecommendation.setRequestType("ByActivityIDsAndDate");
            
            DataAccessInterface objDAInterface = new RecommendationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendation = objADBridge.RetriveRecommendation(objOuterRecommendation);
            logger.info("Recommendation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation");
        }
        
        return objListRecommendation;
    }
    
    /**
     * This function is using to Add RecommendationException
     * @param objRecommendationException
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddRecommendationException")
    public List<String> AddRecommendationException(RecommendationException objRecommendationException) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationExceptionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveRecommendationException(objRecommendationException);
            logger.info("RecommendationException saved successfully, RecommendationException Details="+objRecommendationException);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding RecommendationException");
        	response.add("Error in adding RecommendationException");
        }
       return response;
    }
    
    /**
     * This function is using to Update RecommendationException
     * @param objRecommendationException
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateRecommendationException")
    public List<String> UpdateRecommendationException(RecommendationException objRecommendationException) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationExceptionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateRecommendationException(objRecommendationException);
            logger.info("RecommendationException updated successfully, RecommendationException Details="+objRecommendationException);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating RecommendationException");
        	response.add("Error in updating RecommendationException");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Recommendation Exception by RecommendationID 
     * @param RecommendationID
     * @return an instance of list of RecommendationException
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RecommendationException/{RecommendationID}")
    public List<RecommendationException> RetrieveRecommendationExceptionByRecommendationID(@PathParam ("RecommendationID") String RecommendationID) {
        RecommendationException objOuterRecommendationException = new RecommendationException();
        List<RecommendationException> objListRecommendationException = new  ArrayList<RecommendationException>();
        try
        {
            objOuterRecommendationException.setRecommendationID(Long.parseLong(RecommendationID));
            
            DataAccessInterface objDAInterface = new RecommendationExceptionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendationException = objADBridge.RetriveRecommendationException(objOuterRecommendationException);
            logger.info("Recommendation Exception loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation Exception");
        }
        return objListRecommendationException;
    }
    
    /**
     * This function is using to Add RecommendationExplanation
     * @param objRecommendationExplanation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddRecommendationExplanation")
    public List<String> AddRecommendationExplanation(RecommendationExplanation objRecommendationExplanation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationExplanationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveRecommendationExplanation(objRecommendationExplanation);
            logger.info("RecommendationExplanation saved successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding RecommendationExplanation");
        	response.add("Error in adding RecommendationExplanation");
        }
        return response;
    }
    
    /**
     * This function is using to Update RecommendationExplanation
     * @param objRecommendationExplanation
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateRecommendationExplanation")
    public List<String> UpdateRecommendationExplanation(RecommendationExplanation objRecommendationExplanation) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationExplanationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateRecommendationExplanation(objRecommendationExplanation);
            logger.info("RecommendationExplanation updated successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating RecommendationExplanation");
        	response.add("Error in updating RecommendationExplanation");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Recommendation Explanation by RecommendationID 
     * @param RecommendationID
     * @return an instance of list of RecommendationExplanation
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RecommendationExplanation/{RecommendationID}")
    public List<RecommendationExplanation> RetrieveRecommendationExplanationByRecommendationID(@PathParam ("RecommendationID") String RecommendationID) {
        RecommendationExplanation objOuterRecommendationExplanation = new RecommendationExplanation();
        List<RecommendationExplanation> objListRecommendationExplanation = new  ArrayList<RecommendationExplanation>();
        try
        {
            objOuterRecommendationExplanation.setRecommendationID(Long.parseLong(RecommendationID));
            
            DataAccessInterface objDAInterface = new RecommendationExplanationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendationExplanation = objADBridge.RetriveRecommendationExplanation(objOuterRecommendationExplanation);
            logger.info("Recommendation Explanation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation Explanation");
        }
        return objListRecommendationExplanation;
    }
    
    /**
     * This function is using to Add Facts
     * @param objFacts
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddFacts")
    public List<String> AddFacts(Facts objFacts) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new FactsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveFacts(objFacts);
            logger.info("Facts saved successfully, Facts Details="+objFacts);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Facts");
        	response.add("Error in adding Facts");
        }
        return response;
    }
    
    /**
     * This function is using to Update Facts
     * @param objFacts
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateFacts")
    public List<String> UpdateFacts(Facts objFacts) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new FactsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateFacts(objFacts);
            logger.info("Facts updated successfully, Facts Details="+objFacts);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Facts");
        	response.add("Error in updating Facts");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Facts by UserID 
     * @param UserID
     * @return an instance of list of Facts
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("Facts/{UserID}")
    public List<Facts> RetrieveFactsByUserID(@PathParam ("UserID") String UserID) {
        Facts objOuterFacts = new Facts();
        List<Facts> objListFacts = new  ArrayList<Facts>();
        try
        {
            objOuterFacts.setUserID(Long.parseLong(UserID));
            objOuterFacts.setRequestType("ByUserOnly");
            
            DataAccessInterface objDAInterface = new FactsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFacts = objADBridge.RetriveFacts(objOuterFacts);
            logger.info("Facts loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Facts");
        }
        
        return objListFacts;
    }
    
    /**
     * This function is using to Retrieves Facts by UserID and date range
     * @param objOuterFacts
     * @return an instance of list of Facts
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveFactsByUserIDDate")
    public List<Facts> RetrieveFactsByUserIDDate(Facts objOuterFacts) {
        
        List<Facts> objListFacts = new  ArrayList<Facts>();
        try
        {
            objOuterFacts.setRequestType("ByUserandDate");
            
            DataAccessInterface objDAInterface = new FactsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFacts = objADBridge.RetriveFacts(objOuterFacts);
            logger.info("Facts loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Facts");
        }
        
        return objListFacts;
    }
    
    /**
     * This function is using to Add Achievements
     * @param objAchievements
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddAchievements")
    public List<String> AddAchievements(Achievements objAchievements) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new AchievementsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveAchievements(objAchievements);
            logger.info("Achievements saved successfully, Achievements Details="+objAchievements);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Achievements");
        	response.add("Error in adding Achievements");
        }
        return response;
    }
    
    /**
     * This function is using to  Update Achievements
     * @param objAchievements
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateAchievements")
    public List<String> UpdateAchievements(Achievements objAchievements) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new AchievementsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateAchievements(objAchievements);
            logger.info("Achievements updated successfully, Achievements Details="+objAchievements);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Achievements");
        	response.add("Error in updating Achievements");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Achievements by UserID 
     * @param UserID
     * @return an instance of list of Achievements
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("Achievements/{UserID}")
    public List<Achievements> RetrieveAchievementsByUserID(@PathParam ("UserID") String UserID) {
        Achievements objOuterAchievements = new Achievements();
        List<Achievements> objListAchievements = new  ArrayList<Achievements>();
        try
        {
            objOuterAchievements.setUserID(Long.parseLong(UserID));
            objOuterAchievements.setRequestType("ByUserOnly");
            
            DataAccessInterface objDAInterface = new AchievementsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListAchievements = objADBridge.RetriveAchievements(objOuterAchievements);
            logger.info("Achievements loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Achievements");
        }
        
        return objListAchievements;
    }
    
    /**
     * This function is using to Retrieves Achievements by UserID and date range
     * @param objOuterAchievements
     * @return an instance of list of Achievements
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RetrieveAchievementsByUserIDDate")
    public List<Achievements> RetrieveAchievementsByUserIDDate(Achievements objOuterAchievements) {
        
        List<Achievements> objListAchievements = new  ArrayList<Achievements>();
        try
        {
            objOuterAchievements.setRequestType("ByUserandDate");
            
            DataAccessInterface objDAInterface = new AchievementsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListAchievements = objADBridge.RetriveAchievements(objOuterAchievements);
            logger.info("Achievements loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Achievements");
        }
        
        return objListAchievements;
    }
    
     /**
     * This function is using to Retrieves users current context, Activity, location, HLC, and Emotion by UserID 
     * @param UserID
     * @return an instance of CurrentContextPacket
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserCurrentContext/{UserID}")
    public CurrentContextPacket UserCurrentContext(@PathParam ("UserID") String UserID) {
        
        CurrentContextPacket objCurrentContextPacket = new CurrentContextPacket();
        
        try
        {
            List<UserRecognizedActivity> objListUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
            UserRecognizedActivity objOuterUserRecognizedActivity = new UserRecognizedActivity();
            objOuterUserRecognizedActivity.setRequestType("LatestByUser");
            objOuterUserRecognizedActivity.setUserId(Long.parseLong(UserID));
        
            DataAccessInterface objDAInterface = new UserRecognizedActivityAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedActivity = objADBridge.RetriveUserRecognizedActivity(objOuterUserRecognizedActivity);
            objCurrentContextPacket.setObjListUserRecognizedActivity(objListUserRecognizedActivity);
            logger.info("UserRecognizedActivity loaded successfully");
        }
        catch(Exception ex)
        { 
        	logger.info("Error in loading UserRecognizedActivity"); 
        }
        try
        {
            List<UserDetectedLocation> objListUserDetectedLocation = new ArrayList<UserDetectedLocation>();
            UserDetectedLocation objOuterUserDetectedLocation = new UserDetectedLocation();
            objOuterUserDetectedLocation.setRequestType("LatestByUser");
            objOuterUserDetectedLocation.setUserId(Long.parseLong(UserID));
        
            DataAccessInterface objDAInterface = new UserDetectedLocationAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserDetectedLocation = objADBridge.RetriveUserDetectedLocation(objOuterUserDetectedLocation);
            objCurrentContextPacket.setObjListUserDetectedLocation(objListUserDetectedLocation);
            logger.info("UserDetectedLocation loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading UserDetectedLocation");
        }
        
        try
        {
            List<UserRecognizedEmotion> objListUserRecognizedEmotion = new ArrayList<UserRecognizedEmotion>();
            UserRecognizedEmotion objOuterUserRecognizedEmotion = new UserRecognizedEmotion();
            objOuterUserRecognizedEmotion.setRequestType("LatestByUser");
            objOuterUserRecognizedEmotion.setUserId(Long.parseLong(UserID));
        
            DataAccessInterface objDAInterface = new UserRecognizedEmotionAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedEmotion = objADBridge.RetriveUserRecognizedEmotion(objOuterUserRecognizedEmotion);
            objCurrentContextPacket.setObjListUserRecognizedEmotion(objListUserRecognizedEmotion);
            logger.info("UserRecognizedEmotion loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading UserRecognizedEmotion");
        }
        
        try
        {
            List<UserRecognizedHLC> objListUserRecognizedHLC = new ArrayList<UserRecognizedHLC>();
            UserRecognizedHLC objOuterUserRecognizedHLC = new UserRecognizedHLC();
            objOuterUserRecognizedHLC.setRequestType("LatestByUser");
            objOuterUserRecognizedHLC.setUserId(Long.parseLong(UserID));
        
            DataAccessInterface objDAInterface = new UserRecognizedHLCAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRecognizedHLC = objADBridge.RetriveUserRecognizedHLC(objOuterUserRecognizedHLC);
            objCurrentContextPacket.setObjListUserRecognizedHLC(objListUserRecognizedHLC);
            logger.info("UserRecognizedHLC loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading UserRecognizedHLC");
        }
        
         try
        {
            List<FoodLog> objListFoodLog = new ArrayList<FoodLog>();
            FoodLog objOuterFoodLog = new FoodLog();
            objOuterFoodLog.setRequestType("LatestByUser");
            objOuterFoodLog.setUserId(Long.parseLong(UserID));
        
            DataAccessInterface objDAInterface = new FoodLogAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFoodLog = objADBridge.RetriveFoodLog(objOuterFoodLog);
            objCurrentContextPacket.setObjListFoodLog(objListFoodLog);
            logger.info("FoodLog loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading FoodLog");
        }
         
        return objCurrentContextPacket;
    }
    
}
