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
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;

/**
 * This is UserDetectedLocationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserDetectedLocationAdapter implements DataAccessInterface{

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserDetectedLocationAdapter.class);
    public UserDetectedLocationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserDetectedLocation. 
     * @param objUserDetectedLocation
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserDetectedLocation) {
        UserDetectedLocation objInnerUserDetectedLocation = new UserDetectedLocation();
        objInnerUserDetectedLocation =  (UserDetectedLocation) objUserDetectedLocation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserDetectedLocation(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserDetectedLocation.getUserId());
            objCallableStatement.setString("LocationLabel", objInnerUserDetectedLocation.getLocationLabel());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtStartDate = sdf.parse(objInnerUserDetectedLocation.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
                                               
            objCallableStatement.registerOutParameter("UserDetectedLocationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserDetectedLocationID = objCallableStatement.getLong("UserDetectedLocationID");
            objDbResponse.add(String.valueOf(intUserDetectedLocationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Detected Location saved successfully, User Detected Location Details="+objUserDetectedLocation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Detected Location");
        	objDbResponse.add("Error in adding User Detected Location");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for saving UserDetectedLocation. 
     * Not Implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving UserDetectedLocation.
     * @param objUserDetectedLocation
     * @return List of UserDetectedLocation 
     */
    @Override
    public List<UserDetectedLocation> RetriveData(Object objUserDetectedLocation) {
        UserDetectedLocation objOuterUserDetectedLocation = new UserDetectedLocation();
        List<UserDetectedLocation> objListInnerUserDetectedLocation = new ArrayList<UserDetectedLocation>();
        objOuterUserDetectedLocation =  (UserDetectedLocation) objUserDetectedLocation;
        
        try
        {
            CallableStatement objCallableStatement = null;
            Timestamp tsStartDate = null;
            Timestamp tsEndDate = null;
            if(objOuterUserDetectedLocation.getRequestType() == "LatestByUser" )
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_CurrentUserDetectedLocationByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterUserDetectedLocation.getUserId());
            }
            else if(objOuterUserDetectedLocation.getRequestType() == "UserDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserDetectedLocationByUserID(?, ?, ?)}");
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                 Date dtStartDate = sdf.parse(objOuterUserDetectedLocation.getStartTime());
                tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate = sdf.parse(objOuterUserDetectedLocation.getEndTime());
                tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterUserDetectedLocation.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserDetectedLocation objInnerUserDetectedLocation = new UserDetectedLocation();
                objInnerUserDetectedLocation.setUserDetectedLocationId(objResultSet.getLong("UserDetectedLocationID"));
                objInnerUserDetectedLocation.setUserId(objResultSet.getLong("UserID"));
                objInnerUserDetectedLocation.setLocationLabel(objResultSet.getString("LocationLabel"));
                if(objResultSet.getTimestamp("StartTime") != null)
                {
                    tsStartDate = objResultSet.getTimestamp("StartTime");
                    objInnerUserDetectedLocation.setStartTime( tsStartDate.toString());
                }
                
                if(objResultSet.getTimestamp("EndTime") != null)
                {
                    tsEndDate = objResultSet.getTimestamp("EndTime");
                    objInnerUserDetectedLocation.setEndTime( tsEndDate.toString());
                }
                
                objInnerUserDetectedLocation.setDuration(objResultSet.getLong("Duration"));
                
                objListInnerUserDetectedLocation.add(objInnerUserDetectedLocation);
            }
            objConn.close();
            logger.info("User Detected Location laoded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Detected Location");
        }   
        return objListInnerUserDetectedLocation;
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
