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
import org.uclab.mm.datamodel.ic.Device;
import org.uclab.mm.datamodel.ic.UserDevice;

/**
 * This is UserDeviceAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserDeviceAdapter implements  DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserDeviceAdapter.class);
    public UserDeviceAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserDevice) {
        UserDevice objInnerUserDevice = new UserDevice();
        objInnerUserDevice =  (UserDevice) objUserDevice;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserDevice(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserDevice.getUserID());
            objCallableStatement.setLong("DeviceID", objInnerUserDevice.getDeviceID());
            objCallableStatement.setInt("SubscriptionStatusID", objInnerUserDevice.getSubscriptionStatusID());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtRegisterDate = sdf.parse(objInnerUserDevice.getRegisterDate());
            Timestamp tsRegisterDate = new Timestamp(dtRegisterDate.getYear(),dtRegisterDate.getMonth(), dtRegisterDate.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("RegisterDate", tsRegisterDate);
                       
            objCallableStatement.registerOutParameter("UserDeviceID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intUserDeviceID = objCallableStatement.getInt("UserDeviceID");
            objDbResponse.add(String.valueOf(intUserDeviceID));
            objDbResponse.add("No Error");
            
            objConn.close();
            logger.info("Device loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in registering Device");
        	objDbResponse.add("Error in registering Device");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserDevice. 
     * @param objUserDevice
     * @return List of UserDevice
     */
    @Override
    public List<String> Update(Object objUserDevice) {
        
        UserDevice objInnerUserDevice = new UserDevice();
        objInnerUserDevice =  (UserDevice) objUserDevice;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserDevice(?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserDevice.getUserID());
            objCallableStatement.setLong("DeviceID", objInnerUserDevice.getDeviceID());
            objCallableStatement.setInt("SubscriptionStatusID", objInnerUserDevice.getSubscriptionStatusID());
                       
            objCallableStatement.execute();
            
            objDbResponse.add("No Error");
            
            objConn.close();
            logger.info("Sensor updated successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in subscribing sensor");
        	objDbResponse.add("Error in subscribing sensor");
        }   
        
        return objDbResponse;
        
    }

    /**
     * This is implementation function for retrieving UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    @Override
    public List<UserDevice> RetriveData(Object objUserDevice) {
        UserDevice objOuterUserDevice = new UserDevice();
        List<UserDevice> objListInnerUserDevice = new ArrayList<UserDevice>();
        objOuterUserDevice =  (UserDevice) objUserDevice;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserDeviceByID(?)}");
            objCallableStatement.setLong("UserDeviceID", objOuterUserDevice.getUserDeviceID());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserDevice objInnerUserDevice = new UserDevice();
                objInnerUserDevice.setUserDeviceID(objResultSet.getLong("UserDeviceID"));
                objInnerUserDevice.setUserID(objResultSet.getLong("UserID"));
                objInnerUserDevice.setDeviceID(objResultSet.getLong("DeviceID"));
                objInnerUserDevice.setSubscriptionStatusID(objResultSet.getInt("SubscriptionStatusID"));
                
                if(objResultSet.getTimestamp("RegisterDate") != null)
                {
                     Timestamp tsRegisterDate = objResultSet.getTimestamp("RegisterDate");
                     objInnerUserDevice.setRegisterDate(tsRegisterDate.toString());
                }
                objListInnerUserDevice.add(objInnerUserDevice);
            }
            objConn.close();
            logger.info("Device loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in Device loadeding");
        }   
        return objListInnerUserDevice;
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
