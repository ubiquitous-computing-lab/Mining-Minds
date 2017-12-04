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
package org.uclab.mm.datamodel.dc.dataadapter;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.uclab.mm.datamodel.dc.UserSchedule;
/**
 * This is UserScheduleAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class UserScheduleAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserScheduleAdapter.class);
    public UserScheduleAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserSchedule) {
        UserSchedule objInnerUserSchedule = new UserSchedule();
        objInnerUserSchedule =  (UserSchedule) objUserSchedule;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserSchedule(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserSchedule.getUserID());
            objCallableStatement.setString("ScheduledTask", objInnerUserSchedule.getScheduledTask());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            if(objInnerUserSchedule.getStartTime() != null )
            {
                 Date dtStartDate = sdf.parse(objInnerUserSchedule.getStartTime());
                 Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                 objCallableStatement.setTimestamp("StartTime", tsStartDate);
            }
            else
            { objCallableStatement.setTimestamp("StartTime", null); }
            if(objInnerUserSchedule.getEndTime()!= null)
            {
                Date dtEndDate = sdf.parse(objInnerUserSchedule.getEndTime());
                Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            else
            { objCallableStatement.setTimestamp("EndTime", null); }
                
            if(objInnerUserSchedule.getExtra()!= null)
            {
                objCallableStatement.setString("Extra", objInnerUserSchedule.getExtra());
            }
            else
            { objCallableStatement.setString("Extra", objInnerUserSchedule.getExtra()); }
            
            objCallableStatement.registerOutParameter("UserScheduleID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserScheduleID = objCallableStatement.getLong("UserScheduleID");
            objDbResponse.add(String.valueOf(intUserScheduleID));
            objDbResponse.add("No Error");
            objConn.close();
            logger.info(" User Schedule saved successfully, User Details="+objUserSchedule);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Schedule");
        	objDbResponse.add("Error in adding User Schedule");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserSchedule) {
        UserSchedule objInnerUserSchedule = new UserSchedule();
        objInnerUserSchedule =  (UserSchedule) objUserSchedule;
        List<String> objDbResponse = new ArrayList<>();
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserSchedule(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserScheduleID", objInnerUserSchedule.getUserScheduleID());
            objCallableStatement.setLong("UserID", objInnerUserSchedule.getUserID());
            objCallableStatement.setString("ScheduledTask", objInnerUserSchedule.getScheduledTask());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            if(objInnerUserSchedule.getStartTime() != null )
            {
                 Date dtStartDate = sdf.parse(objInnerUserSchedule.getStartTime());
                 Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                 objCallableStatement.setTimestamp("StartTime", tsStartDate);
            }
            else
            { objCallableStatement.setTimestamp("StartTime", null); }
            if(objInnerUserSchedule.getEndTime()!= null)
            {
                Date dtEndDate = sdf.parse(objInnerUserSchedule.getEndTime());
                Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            else
            { objCallableStatement.setTimestamp("EndTime", null); }
                
            if(objInnerUserSchedule.getExtra()!= null)
            {
                objCallableStatement.setString("Extra", objInnerUserSchedule.getExtra());
            }
            else
            { objCallableStatement.setString("Extra", objInnerUserSchedule.getExtra()); }
            
            objCallableStatement.execute();
            
            Long intUserScheduleID = objInnerUserSchedule.getUserScheduleID();
            objDbResponse.add(String.valueOf(intUserScheduleID));
            objDbResponse.add("No Error");
            objConn.close();
            logger.info("User Schedule saved successfully, User Details="+objUserSchedule);
        }
        catch (Exception e)
        {
        	logger.info("Error in updated User Schedule");
        	objDbResponse.add("Error in updated User Schedule");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserSchedule.
     * @param objUserSchedule
     * @return List of UserSchedule 
     */
    @Override
    public List<UserSchedule> RetriveData(Object objUserSchedule) {
        UserSchedule objOuterUserSchedule = new UserSchedule();
        List<UserSchedule> objListInnerUserSchedule = new ArrayList<UserSchedule>();
        objOuterUserSchedule =  (UserSchedule) objUserSchedule;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserScheduleByUserID(?, ?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtStartDate = sdf.parse(objOuterUserSchedule.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            Date dtEndDate =sdf.parse( objOuterUserSchedule.getEndTime()); //.getTime();
            Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                        
            objCallableStatement.setLong("UserID", objOuterUserSchedule.getUserID());
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
            objCallableStatement.setTimestamp("EndTime", tsEndDate);
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserSchedule objInnerUserSchedule = new UserSchedule();
                objInnerUserSchedule.setUserScheduleID(objResultSet.getLong("UserScheduleID"));
                objInnerUserSchedule.setUserID(objResultSet.getLong("UserID"));
                objInnerUserSchedule.setScheduledTask(objResultSet.getString("ScheduledTask"));
               
                
                tsStartDate = objResultSet.getTimestamp("StartTime"); //updated
                objInnerUserSchedule.setStartTime(tsStartDate.toString());
                
                tsEndDate = objResultSet.getTimestamp("EndTime"); // updated
                objInnerUserSchedule.setEndTime(tsEndDate.toString());
                
                if(objResultSet.getString("Extra") != null)
                {
                    objInnerUserSchedule.setExtra(objResultSet.getString("Extra"));
                }
                objListInnerUserSchedule.add(objInnerUserSchedule);
            }
            objConn.close();
            logger.info("User Schedule loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Schedule");
        }   
        return objListInnerUserSchedule;
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
