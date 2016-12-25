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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityLog;

/**
 * This is UserRecognizedActivityLogAdapter class which implements the Data Access Interface for CRUD operations
 * @author dcadmin
 */
public class UserRecognizedActivityLogAdapter implements DataAccessInterface{
    
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRecognizedActivityLogAdapter.class);
    public UserRecognizedActivityLogAdapter()
    {
        
    }

    /**
     * This is implementation function for saving UserRecognizedActivityLog. 
     * @param objUserRecognizedActivityLog
   * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRecognizedActivityLog) {
        UserRecognizedActivityLog objInnerUserRecognizedActivityLog = new UserRecognizedActivityLog();
        objInnerUserRecognizedActivityLog =  (UserRecognizedActivityLog) objUserRecognizedActivityLog;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRecognizedActivityLog(?, ?, ?, ?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtStartDate = sdf.parse(objInnerUserRecognizedActivityLog.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            Date dtEndDate = sdf.parse(objInnerUserRecognizedActivityLog.getEndTime());
            Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
            objCallableStatement.setLong("UserID", objInnerUserRecognizedActivityLog.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserRecognizedActivityLog.getActivityId());
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
            objCallableStatement.setTimestamp("EndTime", tsEndDate);
                                   
            objCallableStatement.registerOutParameter("UserRecognizedActivityLogID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserRecognizedActivityLogID = objCallableStatement.getLong("UserRecognizedActivityLogID");
            objDbResponse.add(String.valueOf(intUserRecognizedActivityLogID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("user recognized activities Log saved successfully, User Details="+objUserRecognizedActivityLog);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding user recognized activities Log");
        	objDbResponse.add("Error in adding user recognized activities Log");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Updating UserRecognizedActivityLog. 
     * Not Implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivityLog. 
     * @param objUserRecognizedActivityLog
     * @return List of UserRecognizedActivityLog
     */
    @Override
    public List<UserRecognizedActivityLog> RetriveData(Object objUserRecognizedActivityLog) {
        UserRecognizedActivityLog objOuterUserRecognizedActivityLog = new UserRecognizedActivityLog();
        List<UserRecognizedActivityLog> objListInnerUserRecognizedActivityLog = new ArrayList<UserRecognizedActivityLog>();
        objOuterUserRecognizedActivityLog =  (UserRecognizedActivityLog) objUserRecognizedActivityLog;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityLogByUserID(?, ?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtStartDate = sdf.parse(objOuterUserRecognizedActivityLog.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            Date dtEndDate =sdf.parse( objOuterUserRecognizedActivityLog.getEndTime()); //.getTime();
            Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                        
            objCallableStatement.setLong("UserID", objOuterUserRecognizedActivityLog.getUserId());
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
            objCallableStatement.setTimestamp("EndTime", tsEndDate);
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRecognizedActivityLog objInnerUserRecognizedActivityLog = new UserRecognizedActivityLog();
                objInnerUserRecognizedActivityLog.setUserRecognizedActivityLogId(objResultSet.getLong("UserRecognizedActivityLogID"));
                objInnerUserRecognizedActivityLog.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRecognizedActivityLog.setActivityId(objResultSet.getInt("ActivityID"));
                objInnerUserRecognizedActivityLog.setActivityDescription(objResultSet.getString("ActivityDescription"));
                
                tsStartDate = objResultSet.getTimestamp("StartTime"); //updated
                objInnerUserRecognizedActivityLog.setStartTime(tsStartDate.toString());
                
                tsEndDate = objResultSet.getTimestamp("EndTime"); // updated
                objInnerUserRecognizedActivityLog.setEndTime(tsEndDate.toString());
                
                objListInnerUserRecognizedActivityLog.add(objInnerUserRecognizedActivityLog);
            }
            objConn.close();
            logger.info("users recognized activities log laoded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading users recognized activities log");
        }   
        return objListInnerUserRecognizedActivityLog;
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
