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
import org.uclab.mm.datamodel.dc.UserRiskFactors;
import org.uclab.mm.datamodel.dc.Users;

/**
 * This is UserRiskFactorsAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserRiskFactorsAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRiskFactorsAdapter.class);
    public UserRiskFactorsAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRiskFactors) {
        UserRiskFactors objInnerUserRiskFactors = new UserRiskFactors();
        objInnerUserRiskFactors =  (UserRiskFactors) objUserRiskFactors;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRiskFactors(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserRiskFactors.getUserId());
            objCallableStatement.setInt("RiskFactorID", objInnerUserRiskFactors.getRiskFactorId());
            objCallableStatement.setInt("StatusID", objInnerUserRiskFactors.getStatusId());
            
            objCallableStatement.registerOutParameter("UserRiskFactorID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intUserRiskFactorID = objCallableStatement.getInt("UserRiskFactorID");
            objDbResponse.add(String.valueOf(intUserRiskFactorID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Risk factor saved successfully, User Details=" + objUserRiskFactors);
        }
        catch (Exception e)
        {
        	logger.info("Error in saving risk factor");
        	objDbResponse.add("Error in saving risk factor");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    @Override
    public List<String> Update(Object objUserRiskFactors) {
        UserRiskFactors objInnerUserRiskFactors = new UserRiskFactors();
        objInnerUserRiskFactors =  (UserRiskFactors) objUserRiskFactors;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserRiskFactors(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserRiskFactorID", objInnerUserRiskFactors.getUserRiskFactorId());
            objCallableStatement.setLong("UserID", objInnerUserRiskFactors.getUserId());
            objCallableStatement.setInt("RiskFactorID", objInnerUserRiskFactors.getRiskFactorId());
            objCallableStatement.setInt("StatusID", objInnerUserRiskFactors.getStatusId());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUserRiskFactors.getUserRiskFactorId()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Risk factor saved successfully, User Details="+objUserRiskFactors);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating risk factor");
        	objDbResponse.add("Error in updating risk factor");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving UserRiskFactors.
     * @param objUserRiskFactors
     * @return List of UserRiskFactors 
     */
    @Override
    public List<UserRiskFactors> RetriveData(Object objUserRiskFactors) {
        UserRiskFactors objOuterUserRiskFactors = new UserRiskFactors();
        List<UserRiskFactors> objListInnerUserRiskFactors = new ArrayList<UserRiskFactors>();
        objOuterUserRiskFactors =  (UserRiskFactors) objUserRiskFactors;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRiskFactorsByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterUserRiskFactors.getUserId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRiskFactors objInnerUserRiskFactors = new UserRiskFactors();
                objInnerUserRiskFactors.setUserRiskFactorId(objResultSet.getLong("UserRiskFactorID"));
                objInnerUserRiskFactors.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRiskFactors.setRiskFactorId(objResultSet.getInt("RiskFactorID"));
                objInnerUserRiskFactors.setStatusId(objResultSet.getInt("StatusID"));
                
                objListInnerUserRiskFactors.add(objInnerUserRiskFactors);
            }
            objConn.close();
            logger.info("Risk factor loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in getting risk factor");
        }   
        return objListInnerUserRiskFactors;
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
