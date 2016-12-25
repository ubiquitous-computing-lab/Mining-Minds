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
import org.uclab.mm.datamodel.ic.UserAccelerometerData;
import org.uclab.mm.datamodel.ic.UserGPSData;

/**
 * This is UserDataAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserAccelerometerDataDataAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserAccelerometerDataDataAdapter.class);
    public UserAccelerometerDataDataAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserAccelerometerData) {
        UserAccelerometerData objInnerUserAccelerometerData = new UserAccelerometerData();
        objInnerUserAccelerometerData =  (UserAccelerometerData) objUserAccelerometerData;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserAccelerometerData(?, ?, ?, ?, ?, ?)}");
            
            List<Float> arrX = objInnerUserAccelerometerData.getxCoordinate();
            List<Float> arrY =  objInnerUserAccelerometerData.getyCoordinate();
            List<Float> arrZ =  objInnerUserAccelerometerData.getzCoordinate();
            for(int i = 0; i < objInnerUserAccelerometerData.getxCoordinate().size(); i++)
            {
                objCallableStatement.setLong("UserDeviceID", objInnerUserAccelerometerData.getUserDeviceId());
                objCallableStatement.setFloat("XCoordinate", arrX.get(i));
                objCallableStatement.setFloat("YCoordinate", arrY.get(i));
                objCallableStatement.setFloat("ZCoordinate", arrZ.get(i));
                
                
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");	// updated
                Date dttimestamp =  sdf.parse(objInnerUserAccelerometerData.getTimeStamp());
                
                Timestamp tstimestamp = new Timestamp(dttimestamp.getYear(),dttimestamp.getMonth(), dttimestamp.getDate(), dttimestamp.getHours(), dttimestamp.getMinutes(), dttimestamp.getSeconds(), 00);
                objCallableStatement.setTimestamp("timestamp", tstimestamp);

                objCallableStatement.registerOutParameter("UserAccelerometerDataID", Types.BIGINT);
                objCallableStatement.execute();
            }
            
            Long intUserAccelerometerDataID = objCallableStatement.getLong("UserAccelerometerDataID");
            objDbResponse.add(String.valueOf(intUserAccelerometerDataID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Use rAccelerometer Data added successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Accelerometer Data");
        	objDbResponse.add("Error in adding User Accelerometer Data");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for saving UserAccelerometerData. 
     * Not Implemented
     * 
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of UserAccelerometerData
     */
     @Override
    public List<UserAccelerometerData> RetriveData(Object objUserAccelerometerData) {
        UserAccelerometerData objOuterUserAccelerometerData = new UserAccelerometerData();
        List<UserAccelerometerData> objListInnerUserAccelerometerData = new ArrayList<UserAccelerometerData>();
        objOuterUserAccelerometerData =  (UserAccelerometerData) objUserAccelerometerData;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserGPSDataByUserID(?, ?, ?)}");
            objCallableStatement.setLong("UserDeviceID", objOuterUserAccelerometerData.getUserDeviceId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtStartTimeStamp = sdf.parse(objOuterUserAccelerometerData.getTimeStamp());
            
            Timestamp tsStartTimeStamp = new Timestamp(dtStartTimeStamp.getYear(),dtStartTimeStamp.getMonth(), dtStartTimeStamp.getDate(), dtStartTimeStamp.getHours(), dtStartTimeStamp.getMinutes(), dtStartTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("StartTimeStamp", tsStartTimeStamp);
                
            Date dtEndTimeStamp = sdf.parse(objOuterUserAccelerometerData.getTimeStamp());//  updated
            Timestamp tsEndTimeStamp = new Timestamp(dtEndTimeStamp.getYear(),dtEndTimeStamp.getMonth(), dtEndTimeStamp.getDate(), dtEndTimeStamp.getHours(), dtEndTimeStamp.getMinutes(), dtEndTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("EndTimeStamp", tsEndTimeStamp);
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserAccelerometerData objInnerUserAccelerometerData = new UserAccelerometerData();
                objInnerUserAccelerometerData.setUserAccelerometerDataId(objResultSet.getLong("UserGPSDataID"));
                objInnerUserAccelerometerData.setUserDeviceId(objResultSet.getLong("UserDeviceID"));
                Timestamp tstimestamp = objResultSet.getTimestamp("timestamp"); //updated
                objInnerUserAccelerometerData.setTimeStamp(tstimestamp.toString()); //updated
                objListInnerUserAccelerometerData.add(objInnerUserAccelerometerData);
            }
            objConn.close();
            logger.info("Use rAccelerometer Data loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Accelerometer Data");
        }   
        return objListInnerUserAccelerometerData;
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
