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
import org.uclab.mm.datamodel.sc.UserPreferredActivities;

/**
 * This is UserPreferredActivitiesAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserPreferredActivitiesAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserPreferredActivitiesAdapter.class);
    public UserPreferredActivitiesAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserPreferredActivities.
     * @param objUserPreferredActivities
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserPreferredActivities) {
        UserPreferredActivities objInnerUserPreferredActivities = new UserPreferredActivities();
        objInnerUserPreferredActivities =  (UserPreferredActivities) objUserPreferredActivities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserPreferredActivities(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserPreferredActivities.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserPreferredActivities.getActivityId());
            objCallableStatement.setInt("PreferenceLevelID", objInnerUserPreferredActivities.getPreferenceLevelId());
            
            objCallableStatement.registerOutParameter("UserPreferredActivityID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserPreferredActivityID = objCallableStatement.getLong("UserPreferredActivityID");
            objDbResponse.add(String.valueOf(intUserPreferredActivityID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Preferred Activities saved successfully, User Preferred Activities Details="+objUserPreferredActivities);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Preferred Activities");
        	objDbResponse.add("Error in adding User Preferred Activities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserPreferredActivities. 
     * @param objUserPreferredActivities
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserPreferredActivities) {
        UserPreferredActivities objInnerUserPreferredActivities = new UserPreferredActivities();
        objInnerUserPreferredActivities =  (UserPreferredActivities) objUserPreferredActivities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserPreferredActivities(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserPreferredActivityID", objInnerUserPreferredActivities.getUserPreferredActivityId());
            objCallableStatement.setLong("UserID", objInnerUserPreferredActivities.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserPreferredActivities.getActivityId());
            objCallableStatement.setInt("PreferenceLevelID", objInnerUserPreferredActivities.getPreferenceLevelId());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUserPreferredActivities.getUserPreferredActivityId()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Preferred Activities updated successfully, User Preferred Activities Details="+objUserPreferredActivities);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating User Preferred Activities");
        	objDbResponse.add("Error in updating User Preferred Activities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserPreferredActivities. 
     * @param objUserPreferredActivities
     * @return List of UserPreferredActivities
     */
    @Override
    public List<UserPreferredActivities> RetriveData(Object objUserPreferredActivities) {
        UserPreferredActivities objOuterUserPreferredActivities = new UserPreferredActivities();
        List<UserPreferredActivities> objListInnerUserPreferredActivities = new ArrayList<UserPreferredActivities>();
        objOuterUserPreferredActivities =  (UserPreferredActivities) objUserPreferredActivities;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserPreferredActivitiesByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterUserPreferredActivities.getUserId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserPreferredActivities objInnerUserPreferredActivities = new UserPreferredActivities();
                objInnerUserPreferredActivities.setUserPreferredActivityId(objResultSet.getLong("UserPreferredActivityID"));
                objInnerUserPreferredActivities.setUserId(objResultSet.getLong("UserID"));
                objInnerUserPreferredActivities.setActivityId(objResultSet.getInt("ActivityID"));
                objInnerUserPreferredActivities.setPreferenceLevelId(objResultSet.getInt("PreferenceLevelID"));
                
                objListInnerUserPreferredActivities.add(objInnerUserPreferredActivities);
            }
            objConn.close();
            logger.info("User Preferred Activities Data loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Preferred Activities");
        }   
        return objListInnerUserPreferredActivities;
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
