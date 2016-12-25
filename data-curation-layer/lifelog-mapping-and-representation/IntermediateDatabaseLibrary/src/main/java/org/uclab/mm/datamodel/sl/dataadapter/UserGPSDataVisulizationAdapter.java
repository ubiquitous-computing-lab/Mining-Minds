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
package org.uclab.mm.datamodel.sl.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.ic.UserGPSData;
import org.uclab.mm.datamodel.sl.UserGPSDataVisulization;

/**
 * This is UserGPSDataVisulizationAdapter class which implements the Data Access Interface for CRUD operations
 * @author dcadmin
 */
public class UserGPSDataVisulizationAdapter implements DataAccessInterface {
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserGPSDataVisulizationAdapter.class);
    public UserGPSDataVisulizationAdapter()
    {
        
    }

    /**
     * This is implementation function for saving UserGPSDataVisulizationAdapter. 
     *Not Implemented
     */
    @Override
    public <T> List<String> Save(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for Update UserGPSDataVisulizationAdapter. 
     *Not Implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This is implementation function for retrieving UserGPSDataVisulization. 
     * @param objUserGPSData
    * @return List of UserGPSDataVisulization
     */
    @Override
    public List<UserGPSDataVisulization> RetriveData(Object objUserGPSData) {
        UserGPSDataVisulization objOuterUserGPSData = new UserGPSDataVisulization();
        List<UserGPSDataVisulization> objListInnerUserGPSData = new ArrayList<UserGPSDataVisulization>();
        objOuterUserGPSData =  (UserGPSDataVisulization) objUserGPSData;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserGPSDataForVisualization(?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            
            Date dtStartTimeStamp = sdf.parse(objOuterUserGPSData.getTimeStampStart());
            Timestamp tsStartTimeStamp = new Timestamp(dtStartTimeStamp.getYear(),dtStartTimeStamp.getMonth(), dtStartTimeStamp.getDate(), dtStartTimeStamp.getHours(), dtStartTimeStamp.getMinutes(), dtStartTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("StartTimeStamp", tsStartTimeStamp);
                
            Date dtEndTimeStamp = sdf.parse(objOuterUserGPSData.getTimeStampEnd());
            Timestamp tsEndTimeStamp = new Timestamp(dtEndTimeStamp.getYear(),dtEndTimeStamp.getMonth(), dtEndTimeStamp.getDate(), dtEndTimeStamp.getHours(), dtEndTimeStamp.getMinutes(), dtEndTimeStamp.getSeconds(), 00);
            objCallableStatement.setTimestamp("EndTimeStamp", tsEndTimeStamp);

            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserGPSDataVisulization objInnerUserGPSData = new UserGPSDataVisulization();
                objInnerUserGPSData.setUserGPSDataId(objResultSet.getLong("UserGPSDataID"));
                objInnerUserGPSData.setUserDeviceId(objResultSet.getLong("UserDeviceID"));
                objInnerUserGPSData.setLatitude(objResultSet.getFloat("Latitude"));
                objInnerUserGPSData.setLongitude(objResultSet.getFloat("Longitude"));
                objInnerUserGPSData.setSpeed(objResultSet.getFloat("Speed"));
                
                Timestamp tstimestamp = objResultSet.getTimestamp("timestamp"); //updated
                objInnerUserGPSData.setTimeStamp(tstimestamp.toString());
                
                objListInnerUserGPSData.add(objInnerUserGPSData);
            }
            objConn.close();
            logger.info("User GPS Data Visulization loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User GPS Data Visulization");
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
