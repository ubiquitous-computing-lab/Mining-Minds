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
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.ic.Device;
/**
 * This is DeviceAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class DeviceAdapter implements  DataAccessInterface{

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(DeviceAdapter.class);
    public DeviceAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
    public List<String> Save(Object objDevice) {
        Device objInnerDevice = new Device();
        objInnerDevice =  (Device) objDevice;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_Device(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setString("DeviceName", objInnerDevice.getDeviceName());
            objCallableStatement.setInt("DeviceTypeID", objInnerDevice.getDeviceTypeId());
            objCallableStatement.setString("DeviceModel", objInnerDevice.getDeviceModel());
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtRegistrationDate = sdf.parse(objInnerDevice.getRegistrationDate());
            
            
            
            Timestamp tsRegistrationDate = new Timestamp(dtRegistrationDate.getYear(),dtRegistrationDate.getMonth(), dtRegistrationDate.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("RegistrationDate", tsRegistrationDate);
            
            objCallableStatement.registerOutParameter("DeviceID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intDeviceID = objCallableStatement.getInt("DeviceID");
            objDbResponse.add(String.valueOf(intDeviceID));
            objDbResponse.add("No Error");
            
            objConn.close();
            logger.info("Device saved successfully, Device Details="+objDevice);
        }
        catch (Exception e)
        {
        	logger.info("Error in registring Device");
        	objDbResponse.add("Error in registering Device");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for updating Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
    public List<String> Update(Object objDevice) {
        Device objInnerDevice = new Device();
        objInnerDevice =  (Device) objDevice;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_Device(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("DeviceID", objInnerDevice.getDeviceId());
            objCallableStatement.setString("DeviceName", objInnerDevice.getDeviceName());
            objCallableStatement.setInt("DeviceTypeID", objInnerDevice.getDeviceTypeId());
            objCallableStatement.setString("DeviceModel", objInnerDevice.getDeviceModel());
            
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
             Date dtRegistrationDate = sdf.parse(objInnerDevice.getRegistrationDate());
            
            Timestamp tsRegistrationDate = new Timestamp(dtRegistrationDate.getYear(),dtRegistrationDate.getMonth(), dtRegistrationDate.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("RegistrationDate", tsRegistrationDate);
           
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerDevice.getDeviceId()));
            objDbResponse.add("No Error");
            
            objConn.close();
            logger.info("Device updated successfully, Device Details="+objDevice);
        }
        catch (Exception e)
        {
        	logger.info("Error in updated Device");
        	objDbResponse.add("Error in updated Device");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving Device. 
     * @param objDevice
     * @return List of String
     */
    @Override
    public List<Device> RetriveData(Object objDevice) {
        
        Device objOuterDevice = new Device();
        List<Device> objListInnerDevice = new ArrayList<Device>();
        objOuterDevice =  (Device) objDevice;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_DeviceByID(?)}");
            objCallableStatement.setLong("DeviceID", objOuterDevice.getDeviceId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Device objInnerDevice = new Device();
                objInnerDevice.setDeviceId(objResultSet.getLong("DeviceID"));
                objInnerDevice.setDeviceName(objResultSet.getString("DeviceName"));
                objInnerDevice.setDeviceTypeId(objResultSet.getInt("DeviceTypeID"));
                objInnerDevice.setDeviceModel(objResultSet.getString("DeviceModel"));
                
                if(objResultSet.getTimestamp("RegistrationDate") != null)
                {
                    Timestamp tsRegistrationDate = objResultSet.getTimestamp("RegistrationDate");// updated
                    objInnerDevice.setRegistrationDate(tsRegistrationDate .toString()); // updated
                }
                else
                {
                    objInnerDevice.setRegistrationDate(null);
                }
                objListInnerDevice.add(objInnerDevice);
            }
            objConn.close();
            logger.info("Device loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in Device loadeding");
        }   
        return objListInnerDevice;
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
