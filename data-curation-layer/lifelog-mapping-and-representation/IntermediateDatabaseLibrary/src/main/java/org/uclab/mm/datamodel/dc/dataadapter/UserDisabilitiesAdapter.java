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
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.dc.UserDisabilities;
import org.uclab.mm.datamodel.dc.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is UserDisabilitiesAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class UserDisabilitiesAdapter implements DataAccessInterface {

    private Connection objConn;
    
    private static final Logger logger = LoggerFactory.getLogger(UserDisabilitiesAdapter.class);
    
    public UserDisabilitiesAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserDisabilities) {
        UserDisabilities objInnerUserDisabilities = new UserDisabilities();
        objInnerUserDisabilities =  (UserDisabilities) objUserDisabilities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserDisabilities(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserDisabilities.getUserID());
            objCallableStatement.setInt("DisabilityID", objInnerUserDisabilities.getDisabilityID());
            objCallableStatement.setInt("StatusID", objInnerUserDisabilities.getStatusID());
            
            objCallableStatement.registerOutParameter("UserDisabilityID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserDisabilityID = objCallableStatement.getLong("UserDisabilityID");
            objDbResponse.add(String.valueOf(intUserDisabilityID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Disabilities saved successfully, User Disabilities Details="+objUserDisabilities);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Disabilities");
        	objDbResponse.add("Error in adding User Disabilities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserDisabilities) {
        UserDisabilities objInnerUserDisabilities = new UserDisabilities();
        objInnerUserDisabilities =  (UserDisabilities) objUserDisabilities;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserDisabilities(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserDisabilityID", objInnerUserDisabilities.getUserDisabilityID());
            objCallableStatement.setLong("UserID", objInnerUserDisabilities.getUserID());
            objCallableStatement.setInt("DisabilityID", objInnerUserDisabilities.getDisabilityID());
            objCallableStatement.setInt("StatusID", objInnerUserDisabilities.getStatusID());
            
            objCallableStatement.execute();
            
            Long intUserDisabilityID = objInnerUserDisabilities.getUserDisabilityID();
            objDbResponse.add(String.valueOf(intUserDisabilityID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Disabilities updated successfully, User Details="+objUserDisabilities);
        }
        catch (Exception e)
        {
        	logger.info("Error in updated User Disabilities");
        	objDbResponse.add("Error in updated User Disabilities");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of UserDisabilities
     */
    @Override
    public List<UserDisabilities> RetriveData(Object objUserDisabilities) {
        UserDisabilities objOuterUserDisabilities = new UserDisabilities();
        List<UserDisabilities> objListInnerUserDisabilities = new ArrayList<UserDisabilities>();
        objOuterUserDisabilities =  (UserDisabilities) objUserDisabilities;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserDisabilitiesByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterUserDisabilities.getUserID());
            ResultSet objResultSet = objCallableStatement.executeQuery();
            while(objResultSet.next())
            {
                UserDisabilities objInnerUserDisabilities = new UserDisabilities();
                objInnerUserDisabilities.setUserDisabilityID(objResultSet.getLong("UserDisabilityID"));
                objInnerUserDisabilities.setUserID(objResultSet.getLong("UserID"));
                objInnerUserDisabilities.setDisabilityID(objResultSet.getInt("DisabilityID"));
                objInnerUserDisabilities.setStatusID(objResultSet.getInt("StatusID"));
                
                objInnerUserDisabilities.setDisabilityDescription(objResultSet.getString("DisabilityDescription"));
                objInnerUserDisabilities.setStatusDescription(objResultSet.getString("StatusDescription"));
                
                objListInnerUserDisabilities.add(objInnerUserDisabilities);
            }
            objConn.close();
            logger.info("User Disabilities loaded successfully, User Details=");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Disabilities");
        }   
        return objListInnerUserDisabilities;
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
