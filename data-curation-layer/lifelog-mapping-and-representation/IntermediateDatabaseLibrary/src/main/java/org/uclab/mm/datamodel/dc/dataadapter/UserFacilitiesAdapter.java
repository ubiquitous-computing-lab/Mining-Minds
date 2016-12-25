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
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.dc.UserFacilities;

/**
 * This is UserFacilitiesAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserFacilitiesAdapter implements DataAccessInterface {

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(UserFacilitiesAdapter.class);
    
    public UserFacilitiesAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserFacilities) {
        UserFacilities objInnerUserFacilities = new UserFacilities();
        objInnerUserFacilities =  (UserFacilities) objUserFacilities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserFacilities(?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserFacilities.getUserId());
            objCallableStatement.setInt("FacitlityID", objInnerUserFacilities.getFacilityId());
           
            
            objCallableStatement.registerOutParameter("UserFacilityID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserFacilityID = objCallableStatement.getLong("UserFacilityID");
            objDbResponse.add(String.valueOf(intUserFacilityID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("user Facilities saved successfully, User Details="+objUserFacilities);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding user Facilities");
        	objDbResponse.add("Error in adding user Facilities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserFacilities) {
        UserFacilities objInnerUserFacilities = new UserFacilities();
        objInnerUserFacilities =  (UserFacilities) objUserFacilities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserFacilities(?, ?, ?)}");
            
            objCallableStatement.setLong("UserFacilityID", objInnerUserFacilities.getUserFacilityId());
            objCallableStatement.setLong("UserID", objInnerUserFacilities.getUserId());
            objCallableStatement.setInt("FacitlityID", objInnerUserFacilities.getFacilityId());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUserFacilities.getUserFacilityId()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("user Facilities saved successfully, UserFacilities Details="+objUserFacilities);
        }
        catch (Exception e)
        {
        	logger.info("Error in updated user Facilities");
        	objDbResponse.add("Error in updated user Facilities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserFacilities.
     * @param objUserFacilities
     * @return List of UserFacilities
     */
    @Override
    public List<UserFacilities> RetriveData(Object objUserFacilities) {
        UserFacilities objOuterUserFacilities = new UserFacilities();
        List<UserFacilities> objListInnerUserFacilities = new ArrayList<UserFacilities>();
        objOuterUserFacilities =  (UserFacilities) objUserFacilities;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserFacilitiesByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterUserFacilities.getUserId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserFacilities objInnerUserFacilities = new UserFacilities();
                objInnerUserFacilities.setUserFacilityId(objResultSet.getLong("UserFacilityID"));
                objInnerUserFacilities.setUserId(objResultSet.getLong("UserID"));
                objInnerUserFacilities.setFacilityId(objResultSet.getInt("FacitlityID"));
                
                objListInnerUserFacilities.add(objInnerUserFacilities);
            }
            objConn.close();
            logger.info("user Facilities loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading user Facilities");
        }   
        return objListInnerUserFacilities;
    }

    @Override
    public <T> List<T> Delete(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
