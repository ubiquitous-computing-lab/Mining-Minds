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
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;

/**
 * This is UserRecognizedHLCAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserRecognizedHLCAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRecognizedHLCAdapter.class);
    public UserRecognizedHLCAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRecognizedHLC. 
     * @param objUserRecognizedHLC
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRecognizedHLC) {
        UserRecognizedHLC objInnerUserRecognizedHLC = new UserRecognizedHLC();
        objInnerUserRecognizedHLC =  (UserRecognizedHLC) objUserRecognizedHLC;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRecognizedHLC(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserRecognizedHLC.getUserId());
            objCallableStatement.setString("HLCLabel", objInnerUserRecognizedHLC.gethLCLabel());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtStartDate = sdf.parse(objInnerUserRecognizedHLC.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
            objCallableStatement.registerOutParameter("UserRecognizedHLCID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserRecognizedHLCID = objCallableStatement.getLong("UserRecognizedHLCID");
            objDbResponse.add(String.valueOf(intUserRecognizedHLCID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Recognized HighLC saved successfully, User Recognized HighLC Details="+objUserRecognizedHLC);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Recognized HighLC");
        	objDbResponse.add("Error in adding User Recognized HighLC");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserRecognizedHLC. 
     *Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving UserRecognizedHLC.
     * @param objUserRecognizedHLC
     * @return List of UserRecognizedHLC 
     */
    @Override
    public List<UserRecognizedHLC> RetriveData(Object objUserRecognizedHLC) {
        
        UserRecognizedHLC objOuterUserRecognizedHLC = new UserRecognizedHLC();
        List<UserRecognizedHLC> objListInnerUserRecognizedHLC = new ArrayList<UserRecognizedHLC>();
        objOuterUserRecognizedHLC =  (UserRecognizedHLC) objUserRecognizedHLC;
        
        try
        {
            CallableStatement objCallableStatement = null;
            Timestamp tsStartDate = null;
            Timestamp tsEndDate = null;
            
            if(objOuterUserRecognizedHLC.getRequestType() == "LatestByUser")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_CurrentUserRecognizedHLCByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterUserRecognizedHLC.getUserId());
            }
            else if(objOuterUserRecognizedHLC.getRequestType() == "UserDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedHLCByUserID(?, ?, ?)}");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtStartDate = sdf.parse(objOuterUserRecognizedHLC.getStartTime());
                tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate = sdf.parse(objOuterUserRecognizedHLC.getEndTime());
                tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterUserRecognizedHLC.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRecognizedHLC objInnerUserRecognizedHLC = new UserRecognizedHLC();
                objInnerUserRecognizedHLC.setUserRecognizedHLCId(objResultSet.getLong("UserRecognizedHLCID"));
                objInnerUserRecognizedHLC.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRecognizedHLC.sethLCLabel(objResultSet.getString("HLCLabel"));
                
                if(objResultSet.getTimestamp("StartTime") != null)
                {
                    tsStartDate = objResultSet.getTimestamp("StartTime");// updated
                    objInnerUserRecognizedHLC.setStartTime( tsStartDate.toString());
                }
                else
                {
                    objInnerUserRecognizedHLC.setStartTime(null);
                }
                if(objResultSet.getTimestamp("EndTime") != null)
                {
                    tsEndDate = objResultSet.getTimestamp("EndTime");// updated
                    objInnerUserRecognizedHLC.setEndTime( tsEndDate.toString());
                }
                else
                {
                    objInnerUserRecognizedHLC.setEndTime( null );
                }
                        
                objInnerUserRecognizedHLC.setDuration(objResultSet.getLong("Duration"));
                
                objListInnerUserRecognizedHLC.add(objInnerUserRecognizedHLC);
            }
            objConn.close();
            logger.info("User Recognized HLC are loaded successfully.");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Recognized HLC");
        }   
        return objListInnerUserRecognizedHLC;
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
