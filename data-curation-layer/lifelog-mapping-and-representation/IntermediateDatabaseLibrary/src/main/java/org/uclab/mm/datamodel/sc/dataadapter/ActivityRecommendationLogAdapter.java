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
package org.uclab.mm.datamodel.sc.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.ActivityRecommendation;
import org.uclab.mm.datamodel.sc.ActivityRecommendationLog;

/**
 * This is ActivityRecommendationLogAdapter class which implements the Data Access Interface for CRUD operations
 * @author dcadmin
 */
public class ActivityRecommendationLogAdapter implements DataAccessInterface{
    
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(ActivityRecommendationLogAdapter.class);
    public ActivityRecommendationLogAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of String
     */
    public List<String> Save(Object objActivityRecommendationLog) {
        ActivityRecommendationLog objInnerActivityRecommendationLog = new ActivityRecommendationLog();
        objInnerActivityRecommendationLog =  (ActivityRecommendationLog) objActivityRecommendationLog;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ActivityRecommendationLog(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("ActivityPlanID", objInnerActivityRecommendationLog.getActivityPlanId());
            objCallableStatement.setString("Description", objInnerActivityRecommendationLog.getDescription());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtTimestamp = sdf.parse(objInnerActivityRecommendationLog.getTimestamp());
            Timestamp tsTimestamp = new Timestamp(dtTimestamp.getYear(),dtTimestamp.getMonth(), dtTimestamp.getDate(), dtTimestamp.getHours(), dtTimestamp.getMinutes(), dtTimestamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("Timestamp", tsTimestamp);
                                   
            objCallableStatement.registerOutParameter("ActivityRecommendationLogID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intActivityRecommendationLogID = objCallableStatement.getLong("ActivityRecommendationLogID");
            objDbResponse.add(String.valueOf(intActivityRecommendationLogID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objActivityRecommendationLog);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Activity Recommendation");
        	objDbResponse.add("Error in adding Activity Recommendation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Update ActivityRecommendationLog. 
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of ActivityRecommendationLog
     */
    @Override
    public List<ActivityRecommendationLog> RetriveData(Object objActivityRecommendationLog) {
        ActivityRecommendationLog objOuterActivityRecommendationLog = new ActivityRecommendationLog();
        List<ActivityRecommendationLog> objListInnerActivityRecommendationLog = new ArrayList<ActivityRecommendationLog>();
        objOuterActivityRecommendationLog =  (ActivityRecommendationLog) objActivityRecommendationLog;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterActivityRecommendationLog.getRequestType() == "ByPlan")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityRecommendationLogByActivityPlanID(?)}");
                objCallableStatement.setLong("ActivityPlanID", objOuterActivityRecommendationLog.getActivityPlanId());
            }
            else if(objOuterActivityRecommendationLog.getRequestType() == "ByUserDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityRecommendationLogByUserDate(?, ?, ?)}");
                objCallableStatement.setLong("UserID", objOuterActivityRecommendationLog.getUserID());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            
                Date dtStartTimeStamp = sdf.parse(objOuterActivityRecommendationLog.getStartTimestamp());
                Timestamp tsStartTimeStamp = new Timestamp(dtStartTimeStamp.getYear(),dtStartTimeStamp.getMonth(), dtStartTimeStamp.getDate(), dtStartTimeStamp.getHours(), dtStartTimeStamp.getMinutes(), dtStartTimeStamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("StartTimeStamp", tsStartTimeStamp);

                Date dtEndTimeStamp = sdf.parse(objOuterActivityRecommendationLog.getEndTimestamp());
                Timestamp tsEndTimeStamp = new Timestamp(dtEndTimeStamp.getYear(),dtEndTimeStamp.getMonth(), dtEndTimeStamp.getDate(), dtEndTimeStamp.getHours(), dtEndTimeStamp.getMinutes(), dtEndTimeStamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("EndTimeStamp", tsEndTimeStamp);
                
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ActivityRecommendationLog objInnerActivityRecommendationLog = new ActivityRecommendationLog();
                objInnerActivityRecommendationLog.setActivityRecommendationLogId(objResultSet.getLong("ActivityRecommendationLogID"));
                objInnerActivityRecommendationLog.setDescription(objResultSet.getString("Description"));
                
                Timestamp tsTimestamp = objResultSet.getTimestamp("Timestamp"); //updated
                objInnerActivityRecommendationLog.setTimestamp(tsTimestamp.toString());
                
                objListInnerActivityRecommendationLog.add(objInnerActivityRecommendationLog);
            }
            objConn.close();
            logger.info("Activity Recommendation loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Activity Recommendation");
        }   
        return objListInnerActivityRecommendationLog;
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
