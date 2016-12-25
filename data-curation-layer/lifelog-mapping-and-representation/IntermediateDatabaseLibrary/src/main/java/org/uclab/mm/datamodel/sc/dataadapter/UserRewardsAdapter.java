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
package org.uclab.mm.datamodel.sc.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.sc.UserRewards;

/**
 * This is UserRewardsAdapter class which implements the Data Access Interface for CRUD operations
 * @author dcadmin
 */
public class UserRewardsAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRewardsAdapter.class);
    public UserRewardsAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRewards. 
     * @param objUserRewards
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRewards) {
        
        UserRewards objInnerUserRewards = new UserRewards();
        objInnerUserRewards =  (UserRewards) objUserRewards;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRewards(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserRewards.getUserID());
            objCallableStatement.setInt("RewardPoints", objInnerUserRewards.getRewardPoints());
            objCallableStatement.setString("RewardDescription", objInnerUserRewards.getRewardDescription());
                       
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtUserRewards = sdf.parse(objInnerUserRewards.getRewardDate());
            Timestamp tsUserRewards = new Timestamp(dtUserRewards.getYear(),dtUserRewards.getMonth(), dtUserRewards.getDate(), dtUserRewards.getHours(), dtUserRewards.getMinutes(), dtUserRewards.getSeconds(), 00);
            objCallableStatement.setTimestamp("RewardDate", tsUserRewards);
            
            objCallableStatement.setInt("RewardTypeID", objInnerUserRewards.getRewardTypeID());
            
            objCallableStatement.registerOutParameter("UserRewardID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intUserRewardID = objCallableStatement.getInt("UserRewardID");
            objDbResponse.add(String.valueOf(intUserRewardID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Rewards saved successfully, User Rewards Details="+objUserRewards);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Rewards");
        	objDbResponse.add("Error in adding User Rewards");
        }   
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserRewards.
     * @param objUserRewards
     * @return List of String 
     */
    @Override
    public List<String> Update(Object objUserRewards) {
        
        UserRewards objInnerUserRewards = new UserRewards();
        objInnerUserRewards =  (UserRewards) objUserRewards;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_UserRewards(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserRewardID", objInnerUserRewards.getUserRewardID());
            objCallableStatement.setLong("UserID", objInnerUserRewards.getUserID());
            objCallableStatement.setInt("RewardPoints", objInnerUserRewards.getRewardPoints());
            objCallableStatement.setString("RewardDescription", objInnerUserRewards.getRewardDescription());
                       
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtUserRewards = sdf.parse(objInnerUserRewards.getRewardDate());
            Timestamp tsUserRewards = new Timestamp(dtUserRewards.getYear(),dtUserRewards.getMonth(), dtUserRewards.getDate(), dtUserRewards.getHours(), dtUserRewards.getMinutes(), dtUserRewards.getSeconds(), 00);
            objCallableStatement.setTimestamp("RewardDate", tsUserRewards);
            objCallableStatement.setInt("RewardTypeID", objInnerUserRewards.getRewardTypeID());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUserRewards.getUserRewardID()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Rewards updated successfully, User Rewards Details="+objUserRewards);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating User Rewards");
        	objDbResponse.add("Error in updating User Rewards");
        }   
        return objDbResponse;
        
    }

    /**
     * This is implementation function for retrieving UserRewards. 
     * @param objUserRewards
     * @return List of UserRewards
     */
    @Override
    public List<UserRewards> RetriveData(Object objUserRewards) {
        UserRewards objOuterUserRewards = new UserRewards();
        List<UserRewards> objListInnerUserRewards = new ArrayList<UserRewards>();
        objOuterUserRewards =  (UserRewards) objUserRewards;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRewardsByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterUserRewards.getUserID());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRewards objInnerUserRewards = new UserRewards();
                
                objInnerUserRewards.setUserRewardID(objResultSet.getLong("UserRewardID"));
                objInnerUserRewards.setUserID(objResultSet.getLong("UserID"));
                objInnerUserRewards.setRewardPoints(objResultSet.getInt("RewardPoints"));
                objInnerUserRewards.setRewardDescription(objResultSet.getString("RewardDescription"));
                Timestamp tsRewardDate = objResultSet.getTimestamp("RewardDate");
                objInnerUserRewards.setRewardDate(tsRewardDate.toString());
                objInnerUserRewards.setRewardTypeID(objResultSet.getInt("RewardTypeID"));
                objInnerUserRewards.setRewardTypeDescription(objResultSet.getString("RewardTypeDescription"));
                
                objListInnerUserRewards.add(objInnerUserRewards);
            }
            objConn.close();
            logger.info("User Rewards loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Rewards");
        }   
        return objListInnerUserRewards;
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
