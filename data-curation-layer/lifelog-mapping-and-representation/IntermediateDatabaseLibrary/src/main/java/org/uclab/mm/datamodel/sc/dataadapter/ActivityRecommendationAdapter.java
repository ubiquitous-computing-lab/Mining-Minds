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
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.ActivityPlan;
import org.uclab.mm.datamodel.sc.ActivityRecommendation;

/**
 * This is ActivityRecommendationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class ActivityRecommendationAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(ActivityRecommendationAdapter.class);
    public ActivityRecommendationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of String
     */
    @Override
    public List<String> Save(Object objActivityRecommendation) {
        ActivityRecommendation objInnerActivityRecommendation = new ActivityRecommendation();
        objInnerActivityRecommendation =  (ActivityRecommendation) objActivityRecommendation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ActivityRecommendation(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("ActivityPlanID", objInnerActivityRecommendation.getActivityPlanId());
            objCallableStatement.setString("Description", objInnerActivityRecommendation.getDescription());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtTimestamp = sdf.parse(objInnerActivityRecommendation.getTimestamp());
            Timestamp tsTimestamp = new Timestamp(dtTimestamp.getYear(),dtTimestamp.getMonth(), dtTimestamp.getDate(), dtTimestamp.getHours(), dtTimestamp.getMinutes(), dtTimestamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("Timestamp", tsTimestamp);
                                   
            objCallableStatement.registerOutParameter("ActivityRecommendationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intActivityRecommendationID = objCallableStatement.getLong("ActivityRecommendationID");
            objDbResponse.add(String.valueOf(intActivityRecommendationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Activity Recommendation saved successfully, Activity Recommendation Details="+objActivityRecommendation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Activity Recommendation");
        	objDbResponse.add("Error in adding Activity Recommendation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Update ActivityRecommendation. 
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This is implementation function for retrieving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of ActivityRecommendation
     */
    @Override
    public List<ActivityRecommendation> RetriveData(Object objActivityRecommendation) {
        ActivityRecommendation objOuterActivityRecommendation = new ActivityRecommendation();
        List<ActivityRecommendation> objListInnerActivityRecommendation = new ArrayList<ActivityRecommendation>();
        objOuterActivityRecommendation =  (ActivityRecommendation) objActivityRecommendation;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterActivityRecommendation.getRequestType() == "ByPlan")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityRecommendationByActivityPlanID(?)}");
                objCallableStatement.setLong("ActivityPlanID", objOuterActivityRecommendation.getActivityPlanId());
            }
            else if(objOuterActivityRecommendation.getRequestType() == "ByUserDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityRecommendationByUserDate(?, ?, ?)}");
                objCallableStatement.setLong("UserID", objOuterActivityRecommendation.getUserID());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            
                Date dtStartTimeStamp = sdf.parse(objOuterActivityRecommendation.getStartTimestamp());
                Timestamp tsStartTimeStamp = new Timestamp(dtStartTimeStamp.getYear(),dtStartTimeStamp.getMonth(), dtStartTimeStamp.getDate(), dtStartTimeStamp.getHours(), dtStartTimeStamp.getMinutes(), dtStartTimeStamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("StartTimeStamp", tsStartTimeStamp);

                Date dtEndTimeStamp = sdf.parse(objOuterActivityRecommendation.getEndTimestamp());
                Timestamp tsEndTimeStamp = new Timestamp(dtEndTimeStamp.getYear(),dtEndTimeStamp.getMonth(), dtEndTimeStamp.getDate(), dtEndTimeStamp.getHours(), dtEndTimeStamp.getMinutes(), dtEndTimeStamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("EndTimeStamp", tsEndTimeStamp);
                
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ActivityRecommendation objInnerActivityRecommendation = new ActivityRecommendation();
                objInnerActivityRecommendation.setActivityRecommendationId(objResultSet.getLong("ActivityRecommendationID"));
                objInnerActivityRecommendation.setActivityPlanId(objResultSet.getLong("ActivityPlanID"));
                objInnerActivityRecommendation.setDescription(objResultSet.getString("Description"));
                
                Timestamp tsTimestamp = objResultSet.getTimestamp("Timestamp"); //updated
                objInnerActivityRecommendation.setTimestamp(tsTimestamp.toString());
                objInnerActivityRecommendation.setPlanDescription(objResultSet.getString("PlanDescription"));
                
                objListInnerActivityRecommendation.add(objInnerActivityRecommendation);
            }
            objConn.close();
            logger.info("Activity Recommendation loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Activity Recommendation");
        }   
        return objListInnerActivityRecommendation;
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
