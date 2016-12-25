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
package org.uclab.mm.datamodel.sl.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sl.ActivityFeedback;

/**
 * This is ActivityFeedbackAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class ActivityFeedbackAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(ActivityFeedbackAdapter.class);
    public ActivityFeedbackAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    @Override
    public List<String> Save(Object objActivityFeedback) {
        ActivityFeedback objInnerActivityFeedback = new ActivityFeedback();
        objInnerActivityFeedback =  (ActivityFeedback) objActivityFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ActivityFeedback(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecognizedActivityID", objInnerActivityFeedback.getRecognizedActivityID());
            objCallableStatement.setLong("UserID", objInnerActivityFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerActivityFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerActivityFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerActivityFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
            
                        
            objCallableStatement.registerOutParameter("ActivityFeedbackID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intActivityFeedbackID = objCallableStatement.getLong("ActivityFeedbackID");
            objDbResponse.add(String.valueOf(intActivityFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Activity Feedback saved successfully, Activity Feedback Details="+objActivityFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Activity Feedback");
        	objDbResponse.add("Error in adding Activity Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    @Override
    public List<String> Update(Object objActivityFeedback) {
        ActivityFeedback objInnerActivityFeedback = new ActivityFeedback();
        objInnerActivityFeedback =  (ActivityFeedback) objActivityFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_ActivityFeedback(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("ActivityFeedbackID", objInnerActivityFeedback.getActivityFeedbackID());
            objCallableStatement.setLong("RecognizedActivityID", objInnerActivityFeedback.getRecognizedActivityID());
            objCallableStatement.setLong("UserID", objInnerActivityFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerActivityFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerActivityFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerActivityFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
                        
            objCallableStatement.execute();
            
            Long intActivityFeedbackID = objInnerActivityFeedback.getActivityFeedbackID();
            objDbResponse.add(String.valueOf(intActivityFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Activity Feedback updated successfully, Activity Feedback Details="+objActivityFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Activity Feedback");
        	objDbResponse.add("Error in updating Activity Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of ActivityFeedback
     */
    @Override
    public List<ActivityFeedback> RetriveData(Object objActivityFeedback) {
        ActivityFeedback objOuterActivityFeedback = new ActivityFeedback();
        List<ActivityFeedback> objListInnerActivityFeedback = new ArrayList<ActivityFeedback>();
        objOuterActivityFeedback =  (ActivityFeedback) objActivityFeedback;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterActivityFeedback.getRequestType() == "ByRecognizedActivityID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityFeedbackByRecognizedActivityID(?)}");
                objCallableStatement.setLong("RecognizedActivityID", objOuterActivityFeedback.getRecognizedActivityID());
            }
            else if(objOuterActivityFeedback.getRequestType() == "ByUserID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityFeedbackByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterActivityFeedback.getUserID());
            }
                
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ActivityFeedback objInnerActivityFeedback = new ActivityFeedback();
                
                objInnerActivityFeedback.setActivityFeedbackID(objResultSet.getLong("ActivityFeedbackID"));
                objInnerActivityFeedback.setRecognizedActivityID(objResultSet.getLong("RecognizedActivityID"));
                objInnerActivityFeedback.setUserID(objResultSet.getLong("UserID"));
                objInnerActivityFeedback.setRate(objResultSet.getInt("Rate"));
                objInnerActivityFeedback.setReason(objResultSet.getString("Reason"));
                
                 if(objResultSet.getTimestamp("FeedbackDate") != null)
                {
                    Timestamp tsFeedbackDate = objResultSet.getTimestamp("FeedbackDate");
                    objInnerActivityFeedback.setFeedbackDate(tsFeedbackDate.toString());
                }
                objInnerActivityFeedback.setSituationCategoryID(objResultSet.getInt("RecognizedActivityID"));
                objInnerActivityFeedback.setSituationCategoryDescription(objResultSet.getString("ActivityDescription"));
                 
                objListInnerActivityFeedback.add(objInnerActivityFeedback);
            }
            objConn.close();
            logger.info("recommendation Feedback loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading recommendation Feedback");
        }   
        return objListInnerActivityFeedback;
    }

    @Override
    public <T> List<T> Delete(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for connection database.
     * @param objConf
     */
    @Override
     public void ConfigureAdapter(Object objConf) {
         try
         {
            objConn = (Connection)objConf;
            logger.info("Database connected successfully");
         }
         catch(Exception ex)
         {
        	 logger.info("Error in connection to Database");
         }
    }
    
}
