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
import org.uclab.mm.datamodel.ic.UserRecognizedActivityAccumulate;

/** 
 * This is UserRecognizedActivityAccumulateAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class UserRecognizedActivityAccumulateAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRecognizedActivityAccumulateAdapter.class);
    public UserRecognizedActivityAccumulateAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRecognizedActivityAccumulate) {
        
        UserRecognizedActivityAccumulate objInnerUserRecognizedActivityAccumulate = new UserRecognizedActivityAccumulate();
        objInnerUserRecognizedActivityAccumulate =  (UserRecognizedActivityAccumulate) objUserRecognizedActivityAccumulate;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

             CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRecognizedActivityAccumulate(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserRecognizedActivityAccumulate.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserRecognizedActivityAccumulate.getActivityId());
            if(objInnerUserRecognizedActivityAccumulate.getActivityDate() != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                Date dtActivityDate = sdf.parse(objInnerUserRecognizedActivityAccumulate.getActivityDate());
                Timestamp tsActivityDate = new Timestamp(dtActivityDate.getYear(),dtActivityDate.getMonth(), dtActivityDate.getDate(), dtActivityDate.getHours(), dtActivityDate.getMinutes(),dtActivityDate.getSeconds(),0);
                objCallableStatement.setTimestamp("ActivityDate", tsActivityDate);
            }
            
            objCallableStatement.setLong("Duration", objInnerUserRecognizedActivityAccumulate.getDuration());
                                               
            objCallableStatement.registerOutParameter("UserRecognizedActivityAccumulateID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserRecognizedActivityAccumulateID = objCallableStatement.getLong("UserRecognizedActivityAccumulateID");
            objDbResponse.add(String.valueOf(intUserRecognizedActivityAccumulateID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Recognized Activity Accumulate saved successfully, User Details="+objUserRecognizedActivityAccumulate);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Recognized Activity Accumulate");
        	objDbResponse.add("Error in adding User Recognized Activity Accumulate");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserRecognizedActivityAccumulate.
     * @param objUserRecognizedActivityAccumulate
     * @return List of String 
     */
    @Override
    public List<String> Update(Object objUserRecognizedActivityAccumulate) {
        
        UserRecognizedActivityAccumulate objInnerUserRecognizedActivityAccumulate = new UserRecognizedActivityAccumulate();
        objInnerUserRecognizedActivityAccumulate =  (UserRecognizedActivityAccumulate) objUserRecognizedActivityAccumulate;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

             CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserRecognizedActivityAccumulate(?, ?, ?, ?, ?)}");
            objCallableStatement.setLong("UserRecognizedActivityAccumulateID", objInnerUserRecognizedActivityAccumulate.getUserRecognizedActivityAccumulateID());
            objCallableStatement.setLong("UserID", objInnerUserRecognizedActivityAccumulate.getUserId());
            objCallableStatement.setInt("ActivityID", objInnerUserRecognizedActivityAccumulate.getActivityId());
            if(objInnerUserRecognizedActivityAccumulate.getActivityDate() != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                Date dtActivityDate = sdf.parse(objInnerUserRecognizedActivityAccumulate.getActivityDate());
                Timestamp tsActivityDate = new Timestamp(dtActivityDate.getYear(),dtActivityDate.getMonth(), dtActivityDate.getDate(), dtActivityDate.getHours(), dtActivityDate.getMinutes(),dtActivityDate.getSeconds(),0);
                objCallableStatement.setTimestamp("ActivityDate", tsActivityDate);
            }
            
            objCallableStatement.setLong("Duration", objInnerUserRecognizedActivityAccumulate.getDuration());
            
            objCallableStatement.execute();
            
            Long intUserRecognizedActivityAccumulateID = objInnerUserRecognizedActivityAccumulate.getUserRecognizedActivityAccumulateID();
            objDbResponse.add(String.valueOf(intUserRecognizedActivityAccumulateID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Recognized Activity Accumulate updated successfully, User Details="+objUserRecognizedActivityAccumulate);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Recognized Activity Accumulate");
        	objDbResponse.add("Error in updating Recognized Activity Accumulate");
        } 
        return objDbResponse;
        
    }

    /**
     * This is implementation function for retrieving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of RetriveUserRecognizedActivityAccumulate
     */
    @Override
    public List<UserRecognizedActivityAccumulate> RetriveData(Object objUserRecognizedActivityAccumulate) {
        UserRecognizedActivityAccumulate objOuterUserRecognizedActivityAccumulate = new UserRecognizedActivityAccumulate();
        List<UserRecognizedActivityAccumulate> objListInnerUserRecognizedActivityAccumulate = new ArrayList<UserRecognizedActivityAccumulate>();
        objOuterUserRecognizedActivityAccumulate =  (UserRecognizedActivityAccumulate) objUserRecognizedActivityAccumulate;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterUserRecognizedActivityAccumulate.getRequestType() == "UserDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityAccumulateByUserIDDateRange(?, ?, ?)}");
                objCallableStatement.setLong("UserID", objOuterUserRecognizedActivityAccumulate.getUserId());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                if(objOuterUserRecognizedActivityAccumulate.getStartTime()!= null)
                {
                    Date dtStartDate = sdf.parse(objOuterUserRecognizedActivityAccumulate.getStartTime());
                    Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                    objCallableStatement.setTimestamp("StartDate", tsStartDate);
                }
                if(objOuterUserRecognizedActivityAccumulate.getEndTime()!= null)
                {
                    Date dtEndDate = sdf.parse(objOuterUserRecognizedActivityAccumulate.getEndTime());
                    Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                    objCallableStatement.setTimestamp("EndDate", tsEndDate);
                }
            }
            else if(objOuterUserRecognizedActivityAccumulate.getRequestType() == "UserOneDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityAccumulateByUserIDOneDate(?, ?)}");
                objCallableStatement.setLong("UserID", objOuterUserRecognizedActivityAccumulate.getUserId());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                if(objOuterUserRecognizedActivityAccumulate.getActivityDate()!= null)
                {
                    Date dtActivityDate = sdf.parse(objOuterUserRecognizedActivityAccumulate.getActivityDate());
                    Timestamp tsActivityDate = new Timestamp(dtActivityDate.getYear(),dtActivityDate.getMonth(), dtActivityDate.getDate(), dtActivityDate.getHours(), dtActivityDate.getMinutes(),dtActivityDate.getSeconds(),0);
                    objCallableStatement.setTimestamp("ActivityDate", tsActivityDate);
                }
            }
            else if(objOuterUserRecognizedActivityAccumulate.getRequestType() == "ActivitiesDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedActivityAccumulateByActivityIDs(?, ?, ?)}");
                objCallableStatement.setString("ActivityIDs", objOuterUserRecognizedActivityAccumulate.getActivityIDs());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                if(objOuterUserRecognizedActivityAccumulate.getStartTime()!= null)
                {
                    Date dtStartDate = sdf.parse(objOuterUserRecognizedActivityAccumulate.getStartTime());
                    Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                    objCallableStatement.setTimestamp("StartDate", tsStartDate);
                }
                if(objOuterUserRecognizedActivityAccumulate.getEndTime()!= null)
                {
                    Date dtEndDate = sdf.parse(objOuterUserRecognizedActivityAccumulate.getEndTime());
                    Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                    objCallableStatement.setTimestamp("EndDate", tsEndDate);
                }
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();
            while(objResultSet.next())
            {
                UserRecognizedActivityAccumulate objInnerUserRecognizedActivityAccumulate = new UserRecognizedActivityAccumulate();
                objInnerUserRecognizedActivityAccumulate.setUserRecognizedActivityAccumulateID(objResultSet.getLong("UserRecognizedActivityAccumulateID"));
                objInnerUserRecognizedActivityAccumulate.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRecognizedActivityAccumulate.setActivityId(objResultSet.getInt("ActivityID"));
                
                if(objResultSet.getTimestamp("ActivityDate") != null)
                {
                    Timestamp tsActivityDate = objResultSet.getTimestamp("ActivityDate");
                    objInnerUserRecognizedActivityAccumulate.setActivityDate(tsActivityDate.toString());
                }
                objInnerUserRecognizedActivityAccumulate.setDuration(objResultSet.getInt("Duration"));
                objInnerUserRecognizedActivityAccumulate.setActivityDescription(objResultSet.getString("ActivityDescription"));
               
                objListInnerUserRecognizedActivityAccumulate.add(objInnerUserRecognizedActivityAccumulate);
            }
            objConn.close();
            logger.info("Recognized Activity Accumulate are loaded successfully.");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Recognized Activity Accumulate");
        }   
        return objListInnerUserRecognizedActivityAccumulate;
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
