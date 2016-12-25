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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.ActivityPlan;

/**
 * This is ActivityPlanAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class ActivityPlanAdapter implements DataAccessInterface{

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(ActivityPlanAdapter.class);
    public ActivityPlanAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ActivityPlan. 
     * @param objActivityPlan
     * @return List of String
     */
    @Override
    public List<String> Save(Object objActivityPlan) {
        ActivityPlan objInnerActivityPlan = new ActivityPlan();
        objInnerActivityPlan =  (ActivityPlan) objActivityPlan;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ActivityPlan(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserGoalID", objInnerActivityPlan.getUserGoalId());
            objCallableStatement.setString("PlanDescription", objInnerActivityPlan.getPlanDescription());
            objCallableStatement.setString("Explanation", objInnerActivityPlan.getExplanation());
                                   
            objCallableStatement.registerOutParameter("ActivityPlanID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intActivityPlanID = objCallableStatement.getLong("ActivityPlanID");
            objDbResponse.add(String.valueOf(intActivityPlanID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Activity Plan saved successfully, Activity Plan Details="+objActivityPlan);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Activity Plan");
        	objDbResponse.add("Error in adding Activity Plan");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Update ActivityPlan. 
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving ActivityPlan. 
     * @param objActivityPlan
     * @return List of ActivityPlan
     * 
     */
    @Override
    public List<ActivityPlan> RetriveData(Object objActivityPlan) {
        ActivityPlan objOuterActivityPlan = new ActivityPlan();
        List<ActivityPlan> objListInnerActivityPlan = new ArrayList<ActivityPlan>();
        objOuterActivityPlan =  (ActivityPlan) objActivityPlan;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ActivityPlanByUserGoalID(?)}");
            objCallableStatement.setLong("UserGoalID", objOuterActivityPlan.getUserGoalId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ActivityPlan objInnerActivityPlan = new ActivityPlan();
                objInnerActivityPlan.setActivityPlanId(objResultSet.getLong("ActivityPlanID"));
                objInnerActivityPlan.setUserGoalId(objResultSet.getLong("UserGoalID"));
                objInnerActivityPlan.setPlanDescription(objResultSet.getString("PlanDescription"));
                objInnerActivityPlan.setExplanation(objResultSet.getString("Explanation"));
                
                
                objListInnerActivityPlan.add(objInnerActivityPlan);
            }
            objConn.close();
            logger.info("User Goal loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Goal");
        }   
        return objListInnerActivityPlan;
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
