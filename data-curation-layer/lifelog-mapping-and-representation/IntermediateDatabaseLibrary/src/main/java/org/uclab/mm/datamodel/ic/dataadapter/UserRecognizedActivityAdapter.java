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

package org.uclab.mm.datamodel.ic.dataadapter;

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
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;

/**
 * This is UserRecognizedActivityAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserRecognizedActivityAdapter implements DataAccessInterface{

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRecognizedActivityAdapter.class);
    public UserRecognizedActivityAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of String 
     */
    @Override
    public List<String> Save(Object objUserRecognizedActivity) {
        UserRecognizedActivity objInnerUserRecognizedActivity = new UserRecognizedActivity();
        objInnerUserRecognizedActivity =  (UserRecognizedActivity) objUserRecognizedActivity;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

             CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRecognizedActivity(?, ?, ?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtStartDate = sdf.parse(objInnerUserRecognizedActivity.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                       
            objCallableStatement.setLong("UserID", objInnerUserRecognizedActivity.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserRecognizedActivity.getActivityId());
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
                                   
            objCallableStatement.registerOutParameter("UserRecognizedActivityID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserRecognizedActivityID = objCallableStatement.getLong("UserRecognizedActivityID");
            objDbResponse.add(String.valueOf(intUserRecognizedActivityID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("user recognized activities saved successfully, user recognized activities Details="+objUserRecognizedActivity);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding  user recognized activities");
        	objDbResponse.add("Error in adding user recognized activities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Updating UserRecognizedActivity.
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of UserRecognizedActivity 
     */
    @Override
    public List<UserRecognizedActivity> RetriveData(Object objUserRecognizedActivity) {
        UserRecognizedActivity objOuterUserRecognizedActivity = new UserRecognizedActivity();
        List<UserRecognizedActivity> objListInnerUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
        objOuterUserRecognizedActivity =  (UserRecognizedActivity) objUserRecognizedActivity;
        
        try
        {
            CallableStatement objCallableStatement = null;
            Timestamp tsStartDate = null;
            Timestamp tsEndDate = null;
            if(objOuterUserRecognizedActivity.getRequestType() == "LatestByUser")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_CurrentUserRecognizedActivityByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterUserRecognizedActivity.getUserId());
            }
            else if(objOuterUserRecognizedActivity.getRequestType() == "UserDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityByUserID(?, ?, ?)}");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtStartDate = sdf.parse(objOuterUserRecognizedActivity.getStartTime());
                tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate =sdf.parse( objOuterUserRecognizedActivity.getEndTime()); //.getTime();
                tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterUserRecognizedActivity.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            else if(objOuterUserRecognizedActivity.getRequestType() == "UserDateRangeAccumulate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityByUserIDAccumulate(?, ?, ?)}");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtStartDate = sdf.parse(objOuterUserRecognizedActivity.getStartTime());
                tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate =sdf.parse( objOuterUserRecognizedActivity.getEndTime()); //.getTime();
                tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterUserRecognizedActivity.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
                
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRecognizedActivity objInnerUserRecognizedActivity = new UserRecognizedActivity();
                if(objResultSet.getLong("UserRecognizedActivityID") != 0)
                {
                    objInnerUserRecognizedActivity.setUserRecognizedActivityId(objResultSet.getLong("UserRecognizedActivityID"));
                }
                
                objInnerUserRecognizedActivity.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRecognizedActivity.setActivityId(objResultSet.getInt("ActivityID"));
                objInnerUserRecognizedActivity.setActivityDescription(objResultSet.getString("ActivityDescription"));
                
                try
                {
                    if(objResultSet.getObject("StartTime") != null)
                    {
                        tsStartDate = objResultSet.getTimestamp("StartTime"); //updated
                        objInnerUserRecognizedActivity.setStartTime(tsStartDate.toString());
                    }
                }
                catch(Exception ex)
                {}
                
                try
                {
                    if(objResultSet.getObject("EndTime") != null)
                    {
                        tsEndDate = objResultSet.getTimestamp("EndTime"); // updated
                        objInnerUserRecognizedActivity.setEndTime(tsEndDate.toString());
                    }
                }
                catch(Exception ex)
                {
                }
                
                objInnerUserRecognizedActivity.setDuration(objResultSet.getLong("Duration"));
                objListInnerUserRecognizedActivity.add(objInnerUserRecognizedActivity);
            }
            objConn.close();
            logger.info("users recognized activities successfully loaded");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading users recognized activities");
        }   
        return objListInnerUserRecognizedActivity;
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
