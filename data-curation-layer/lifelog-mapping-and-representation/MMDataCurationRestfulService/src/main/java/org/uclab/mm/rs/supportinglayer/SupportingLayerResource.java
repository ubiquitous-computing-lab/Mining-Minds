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
package org.uclab.mm.rs.supportinglayer;

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
import org.uclab.mm.datamodel.sl.ActivityFeedback;
import org.uclab.mm.datamodel.sl.ExpertReview;
import org.uclab.mm.datamodel.sl.FactsFeedback;
import org.uclab.mm.datamodel.sl.RecommendationFeedback;
import org.uclab.mm.datamodel.sl.dataadapter.ActivityFeedbackAdapter;
import org.uclab.mm.datamodel.sl.dataadapter.ExpertReviewAdapter;
import org.uclab.mm.datamodel.sl.dataadapter.FactsFeedbackAdapter;
import org.uclab.mm.datamodel.sl.dataadapter.RecommendationFeedbackAdapter;
import org.uclab.mm.rs.datacuration.DataCurationResource;

/**
 * REST Web Service
 *
 * @author Taqdir Ali
 */
@Path("SupportingLayer")
public class SupportingLayerResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SupportingLayerResource
     */
    public SupportingLayerResource() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DataCurationResource.class);
    
    /**
     * This function is using to Add Recommendation Feedback
     * @param objOuterRecommendationFeedback
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddRecommendationFeedback")
    public List<String> AddRecommendationFeedback(RecommendationFeedback objOuterRecommendationFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveRecommendationFeedback(objOuterRecommendationFeedback);
            logger.info("Recommendation Feedback saved successfully, Recommendation Feedback Details="+objOuterRecommendationFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Recommendation Feedback");
        	response.add("Error in adding Recommendation Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Update Recommendation Feedback
     * @param objOuterRecommendationFeedback
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateRecommendationFeedback")
    public List<String> UpdateRecommendationFeedback(RecommendationFeedback objOuterRecommendationFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new RecommendationFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateRecommendationFeedback(objOuterRecommendationFeedback);
            logger.info("Recommendation Feedback updated successfully, Recommendation Feedback Details="+objOuterRecommendationFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Recommendation Feedback");
        	response.add("Error in updating Recommendation Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Recommendation Feedback by RecommendationID 
     * @param RecommendationID
     * @return an instance of list of RecommendationFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RecommendationFeedback/{RecommendationID}")
    public List<RecommendationFeedback> RetrieveRecommendationFeedbackByRecommendationID(@PathParam ("RecommendationID") String RecommendationID) {
        RecommendationFeedback objOuterRecommendationFeedback = new RecommendationFeedback();
        List<RecommendationFeedback> objListRecommendationFeedback = new  ArrayList<RecommendationFeedback>();
        try
        {
            objOuterRecommendationFeedback.setRecommendationID(Long.parseLong(RecommendationID));
            objOuterRecommendationFeedback.setRequestType("ByRecommendationID");
            DataAccessInterface objDAInterface = new RecommendationFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendationFeedback = objADBridge.RetriveRecommendationFeedback(objOuterRecommendationFeedback);
            logger.info("Recommendation Feedback loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation Feedback");
        }
        
        return objListRecommendationFeedback;
    }
    
    /**
     * This function is using to Retrieves Recommendation Feedback by UserID 
     * @param UserID
     * @return an instance of list of RecommendationFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RecommendationFeedbackByUserID/{UserID}")
    public List<RecommendationFeedback> RetrieveRecommendationFeedbackByUserID(@PathParam ("UserID") String UserID) {
        RecommendationFeedback objOuterRecommendationFeedback = new RecommendationFeedback();
        List<RecommendationFeedback> objListRecommendationFeedback = new  ArrayList<RecommendationFeedback>();
        try
        {
            objOuterRecommendationFeedback.setUserID(Long.parseLong(UserID));
            objOuterRecommendationFeedback.setRequestType("ByUserID");
            DataAccessInterface objDAInterface = new RecommendationFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListRecommendationFeedback = objADBridge.RetriveRecommendationFeedback(objOuterRecommendationFeedback);
            logger.info("Recommendation Feedback loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Recommendation Feedback");
        }
        
        return objListRecommendationFeedback;
    }
    
    /**
     * This function is using to Add Activity Feedback
     * @param objOuterActivityFeedback
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddActivityFeedback")
    public List<String> AddActivityFeedback(ActivityFeedback objOuterActivityFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ActivityFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveActivityFeedback(objOuterActivityFeedback);
            logger.info("Activity Feedback saved successfully, Activity Feedback Details="+objOuterActivityFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Activity Feedback");
        	response.add("Error in adding Activity Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Update Activity Feedback
     * @param objOuterActivityFeedback
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateActivityFeedback")
    public List<String> UpdateActivityFeedback(ActivityFeedback objOuterActivityFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ActivityFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateActivityFeedback(objOuterActivityFeedback);
            logger.info("Activity Feedback updated successfully, Activity Feedback Details="+objOuterActivityFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Activity Feedback");
        	response.add("Error in updating Activity Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Recommendation Feedback by Recognized Activity ID 
     * @param RecognizedActivityID
     * @return an instance of list of ActivityFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ActivityFeedback/{RecognizedActivityID}")
    public List<ActivityFeedback> RetrieveActivityFeedbackByRecognizedActivityID(@PathParam ("RecognizedActivityID") String RecognizedActivityID) {
        ActivityFeedback objOuterActivityFeedback = new ActivityFeedback();
        List<ActivityFeedback> objListActivityFeedback = new  ArrayList<ActivityFeedback>();
        try
        {
            objOuterActivityFeedback.setRecognizedActivityID(Long.parseLong(RecognizedActivityID));
            objOuterActivityFeedback.setRequestType("ByRecognizedActivityID");
            DataAccessInterface objDAInterface = new ActivityFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityFeedback = objADBridge.RetriveActivityFeedback(objOuterActivityFeedback);
            logger.info("recommendation Feedback loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading recommendation Feedback");
        }
        
        return objListActivityFeedback;
    }
    
    /**
     * This function is using to Retrieves Recommendation Feedback by Recognized Activity ID 
     * @param UserID
     * @return an instance of list of ActivityFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ActivityFeedbackByUserID/{UserID}")
    public List<ActivityFeedback> RetrieveActivityFeedbackByUserID(@PathParam ("UserID") String UserID) {
        ActivityFeedback objOuterActivityFeedback = new ActivityFeedback();
        List<ActivityFeedback> objListActivityFeedback = new  ArrayList<ActivityFeedback>();
        try
        {
            objOuterActivityFeedback.setUserID(Long.parseLong(UserID));
            objOuterActivityFeedback.setRequestType("ByUserID");
            DataAccessInterface objDAInterface = new ActivityFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListActivityFeedback = objADBridge.RetriveActivityFeedback(objOuterActivityFeedback);
            logger.info("recommendation Feedback loaded successfully");
        }
        catch(Exception ex)
        { 
        	logger.info("Error in loading recommendation Feedback");
        }
        
        return objListActivityFeedback;
    }
    
    /**
     * This function is using to Add Expert Review
     * @param objOuterExpertReview
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddExpertReview")
    public List<String> AddExpertReview(ExpertReview objOuterExpertReview) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ExpertReviewAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveExpertReview(objOuterExpertReview);
            logger.info("Expert Review saved successfully, Expert Review Details="+objOuterExpertReview);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Expert Review");
        	response.add("Error in adding Expert Review");
        }
        return response;
    }
    
    /**
     * This function is using to Update Expert Review
     * @param objOuterExpertReview
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateExpertReview")
    public List<String> UpdateExpertReview(ExpertReview objOuterExpertReview) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new ExpertReviewAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateExpertReview(objOuterExpertReview);
            logger.info("Expert Review updated successfully, Expert Review Details="+objOuterExpertReview);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating Expert Review");
        	response.add("Error in updating Expert Review");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Expert Review by UserID 
     * @param UserID
     * @return an instance of list of ExpertReview
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ExpertReview/{UserID}")
    public List<ExpertReview> RetrieveExpertReviewByUserID(@PathParam ("UserID") String UserID) {
        ExpertReview objOuterExpertReview = new ExpertReview();
        List<ExpertReview> objListExpertReview = new  ArrayList<ExpertReview>();
        objOuterExpertReview.setRequestType("ByUserID");
        try
        {
            objOuterExpertReview.setUserID(Long.parseLong(UserID));
            
            DataAccessInterface objDAInterface = new ExpertReviewAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListExpertReview = objADBridge.RetriveExpertReview(objOuterExpertReview);
            logger.info("Expert Review loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Expert Review");
        }
        
        return objListExpertReview;
    }
    
    /**
     * This function is using to Retrieves Expert review by userid, start time and end time
     * @param objOuterExpertReview
     * @return an instance of list of ExpertReview
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetExpertReviewByUserIDDateRange")
    public List<ExpertReview> GetFoodLogByUserIDDateRange(ExpertReview objOuterExpertReview) {
        
        List<ExpertReview> objListExpertReview = new ArrayList<ExpertReview>();
        String strResponse = "";
        objOuterExpertReview.setRequestType("ByUserAndDate");
        try
        {
            DataAccessInterface objDAInterface = new ExpertReviewAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListExpertReview = objADBridge.RetriveExpertReview(objOuterExpertReview);
            logger.info("Expert Review loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Expert Review");
        }
        
        return objListExpertReview;
    }
    
    /**
     * This function is using to Add Facts Feedback
     * @param objOuterFactsFeedback
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddFactsFeedback")
    public List<String> AddFactsFeedback(FactsFeedback objOuterFactsFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new FactsFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveFactsFeedback(objOuterFactsFeedback);
            logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objOuterFactsFeedback);
        }
        catch(Exception ex)
        {

        	logger.info("Error in adding Facts Feedback");
        	response.add("Error in adding Facts Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Update Facts Feedback
     * @param objOuterFactsFeedback
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateFactsFeedback")
    public List<String> UpdateFactsFeedback(FactsFeedback objOuterFactsFeedback) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new FactsFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateFactsFeedback(objOuterFactsFeedback);
            logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objOuterFactsFeedback);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Facts Feedback");
        	response.add("Error in adding Facts Feedback");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves Facts Feedback by FactID 
     * @param FactID
     * @return an instance of list of FactsFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("FactsFeedback/{FactID}")
    public List<FactsFeedback> RetrieveFactsFeedbackByFactID(@PathParam ("FactID") String FactID) {
        FactsFeedback objOuterFactsFeedback = new FactsFeedback();
        List<FactsFeedback> objListFactsFeedback = new  ArrayList<FactsFeedback>();
        try
        {
            objOuterFactsFeedback.setFactID(Long.parseLong(FactID));
            objOuterFactsFeedback.setRequestType("ByFactID");
            DataAccessInterface objDAInterface = new FactsFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFactsFeedback = objADBridge.RetriveFactsFeedback(objOuterFactsFeedback);
            logger.info("Facts Feedback loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Facts Feedback");
        }
        
        return objListFactsFeedback;
    }
    /**
     * This function is using to Retrieves Facts Feedback by FactID 
     * @param UserID
     * @return an instance of list of FactsFeedback
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("FactsFeedbackByUserID/{UserID}")
    public List<FactsFeedback> RetrieveFactsFeedbackByUserID(@PathParam ("UserID") String UserID) {
        FactsFeedback objOuterFactsFeedback = new FactsFeedback();
        List<FactsFeedback> objListFactsFeedback = new  ArrayList<FactsFeedback>();
        try
        {
            objOuterFactsFeedback.setUserID(Long.parseLong(UserID));
            objOuterFactsFeedback.setRequestType("ByUserID");
            DataAccessInterface objDAInterface = new FactsFeedbackAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListFactsFeedback = objADBridge.RetriveFactsFeedback(objOuterFactsFeedback);
            logger.info("Facts Feedback loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading Facts Feedback");
        }
        
        return objListFactsFeedback;
    }
    
}
