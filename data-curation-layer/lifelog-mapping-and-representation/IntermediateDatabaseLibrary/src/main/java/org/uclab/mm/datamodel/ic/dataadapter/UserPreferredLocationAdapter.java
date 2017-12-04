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
import org.uclab.mm.datamodel.ic.UserPreferredLocation;

/**
 * This is UserPreferredLocationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserPreferredLocationAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserPreferredLocationAdapter.class);
    public UserPreferredLocationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    @Override
    public  List<String> Save(Object objUserPreferredLocation) {
        UserPreferredLocation objInnerUserPreferredLocation = new UserPreferredLocation();
        objInnerUserPreferredLocation =  (UserPreferredLocation) objUserPreferredLocation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserPreferredLocation(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserPreferredLocation.getUserId());
            objCallableStatement.setFloat("Latitude", objInnerUserPreferredLocation.getLatitude());
            objCallableStatement.setFloat("Longitude", objInnerUserPreferredLocation.getLongitude());
            objCallableStatement.setString("LocationLabel", objInnerUserPreferredLocation.getLocationLabel());
            objCallableStatement.setString("LocationDescription", objInnerUserPreferredLocation.getLocationDescription());
                                   
            objCallableStatement.registerOutParameter("UserPreferredLocationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserPreferredLocationID = objCallableStatement.getLong("UserPreferredLocationID");
            objDbResponse.add(String.valueOf(intUserPreferredLocationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Preferred Location saved successfully, User Details="+objUserPreferredLocation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Preferred Location");
        	objDbResponse.add("Error in adding User Preferred Location");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserPreferredLocation) {
        
        UserPreferredLocation objInnerUserPreferredLocation = new UserPreferredLocation();
        objInnerUserPreferredLocation =  (UserPreferredLocation) objUserPreferredLocation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserPreferredLocation(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserPreferredLocationID", objInnerUserPreferredLocation.getUserPreferredLocationId());
            objCallableStatement.setLong("UserID", objInnerUserPreferredLocation.getUserId());
            objCallableStatement.setFloat("Latitude", objInnerUserPreferredLocation.getLatitude());
            objCallableStatement.setFloat("Longitude", objInnerUserPreferredLocation.getLongitude());
            objCallableStatement.setString("LocationLabel", objInnerUserPreferredLocation.getLocationLabel());
            objCallableStatement.setString("LocationDescription", objInnerUserPreferredLocation.getLocationDescription());
                        
            objCallableStatement.execute();
            
            Long intUserPreferredLocationID = objInnerUserPreferredLocation.getUserPreferredLocationId();
            objDbResponse.add(String.valueOf(intUserPreferredLocationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info(" User Preferred Location saved successfully,  User Preferred Location Details="+objUserPreferredLocation);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating  User Preferred Location");
        	objDbResponse.add("Error in updating  User Preferred Location");
        } 
        return objDbResponse;
        
    }

    /**
     * This is implementation function for retrieving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of UserPreferredLocation
     */
    @Override
    public List<UserPreferredLocation> RetriveData(Object objUserPreferredLocation) {
        UserPreferredLocation objOuterUserPreferredLocation = new UserPreferredLocation();
        List<UserPreferredLocation> objListUserPreferredLocation = new ArrayList<UserPreferredLocation>();
        objOuterUserPreferredLocation =  (UserPreferredLocation) objUserPreferredLocation;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserPreferredLocationByUserID(?)}");
            
            objCallableStatement.setLong("UserID", objOuterUserPreferredLocation.getUserId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserPreferredLocation objInnerUserPreferredLocation = new UserPreferredLocation();
                objInnerUserPreferredLocation.setUserPreferredLocationId(objResultSet.getLong("UserPreferredLocationID"));
                objInnerUserPreferredLocation.setUserId(objResultSet.getLong("UserID"));
                objInnerUserPreferredLocation.setLatitude(objResultSet.getFloat("Latitude"));
                objInnerUserPreferredLocation.setLongitude(objResultSet.getFloat("Longitude"));
                objInnerUserPreferredLocation.setLocationLabel(objResultSet.getString("LocationLabel"));
                objInnerUserPreferredLocation.setLocationDescription(objResultSet.getString("LocationDescription"));
                
                objListUserPreferredLocation.add(objInnerUserPreferredLocation);
            }
            objConn.close();
            logger.info("users preferred location loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading  users preferred location");
        }   
        return objListUserPreferredLocation;
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
