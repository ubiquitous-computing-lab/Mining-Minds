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
import org.uclab.mm.datamodel.ic.UserGPSData;

/**
 * This is UserGPSDataDataAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserGPSDataDataAdapter implements DataAccessInterface {

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(UserGPSDataDataAdapter.class);
    public UserGPSDataDataAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserGPSData. 
     * @param objUserGPSData
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserGPSData) {
        UserGPSData objInnerUserGPSData = new UserGPSData();
        objInnerUserGPSData =  (UserGPSData) objUserGPSData;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserGPSData(?, ?, ?, ?, ?, ?)}");
            List<Float> arrLatitude = objInnerUserGPSData.getLatitude();
            List<Float> arrLongitude = objInnerUserGPSData.getLongitude();
            List<Float> arrSpeed = objInnerUserGPSData.getSpeed();
            for(int i = 0; i < objInnerUserGPSData.getLatitude().size(); i++)
            {
                objCallableStatement.setLong("UserDeviceID", objInnerUserGPSData.getUserDeviceId());
                objCallableStatement.setFloat("Latitude", arrLatitude.get(i));
                objCallableStatement.setFloat("Longitude", arrLongitude.get(i));
                objCallableStatement.setFloat("Speed", arrSpeed.get(i));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");	
                Date dttimestamp =  sdf.parse(objInnerUserGPSData.getTimeStamp());
                Timestamp tstimestamp = new Timestamp(dttimestamp.getYear(),dttimestamp.getMonth(), dttimestamp.getDate(), dttimestamp.getHours(), dttimestamp.getMinutes(), dttimestamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("timestamp", tstimestamp);

                objCallableStatement.registerOutParameter("UserGPSDataID", Types.BIGINT);
                objCallableStatement.execute();
            }
            
            
            Long intUserGPSDataID = objCallableStatement.getLong("UserGPSDataID");
            objDbResponse.add(String.valueOf(intUserGPSDataID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User GPS Data added successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User GPS Data");
        	objDbResponse.add("Error in adding User GPS Data");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserGPSData. 
     * @param objUserGPSData
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserGPSData) {
        UserGPSData objInnerUserGPSData = new UserGPSData();
        objInnerUserGPSData =  (UserGPSData) objUserGPSData;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserGPSData(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserGPSDataID", objInnerUserGPSData.getUserGPSDataId());
            objCallableStatement.setLong("UserDeviceID", objInnerUserGPSData.getUserDeviceId());
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUserGPSData.getUserGPSDataId()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User GPS Data updated successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in updating User GPS Data");
        	objDbResponse.add("Error in updating User GPS Data");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserGPSData. 
     * @param objUserGPSData
     * @return List of UserGPSData
     */
    @Override
    public List<UserGPSData> RetriveData(Object objUserGPSData) {
        UserGPSData objOuterUserGPSData = new UserGPSData();
        List<UserGPSData> objListInnerUserGPSData = new ArrayList<UserGPSData>();
        objOuterUserGPSData =  (UserGPSData) objUserGPSData;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserGPSDataByUserID(?, ?, ?)}");
            objCallableStatement.setLong("UserDeviceID", objOuterUserGPSData.getUserDeviceId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            
            Date dtStartTimeStamp = sdf.parse(objOuterUserGPSData.getTimeStamp());
            Timestamp tsStartTimeStamp = new Timestamp(dtStartTimeStamp.getYear(),dtStartTimeStamp.getMonth(), dtStartTimeStamp.getDate(), dtStartTimeStamp.getHours(), dtStartTimeStamp.getMinutes(), dtStartTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("StartTimeStamp", tsStartTimeStamp);
                
            Date dtEndTimeStamp = sdf.parse(objOuterUserGPSData.getTimeStamp());
            Timestamp tsEndTimeStamp = new Timestamp(dtEndTimeStamp.getYear(),dtEndTimeStamp.getMonth(), dtEndTimeStamp.getDate(), dtEndTimeStamp.getHours(), dtEndTimeStamp.getMinutes(), dtEndTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("EndTimeStamp", tsEndTimeStamp);

            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserGPSData objInnerUserGPSData = new UserGPSData();
                objInnerUserGPSData.setUserGPSDataId(objResultSet.getLong("UserGPSDataID"));
                objInnerUserGPSData.setUserDeviceId(objResultSet.getLong("UserDeviceID"));
                Timestamp tstimestamp = objResultSet.getTimestamp("timestamp"); //updated
                objInnerUserGPSData.setTimeStamp(tstimestamp.toString());
                
                objListInnerUserGPSData.add(objInnerUserGPSData);
            }
            objConn.close();
            logger.info("User GPS Data loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User GPS Data");
        }   
        return objListInnerUserGPSData;
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
