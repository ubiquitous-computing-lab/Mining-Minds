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
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.UserGoal;

/**
 * This is UserGoalAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserGoalAdapter implements DataAccessInterface{

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserGoalAdapter.class);
    public UserGoalAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserGoal. 
     * @param objUserGoal
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserGoal) {
        UserGoal objInnerUserGoal = new UserGoal();
        objInnerUserGoal =  (UserGoal) objUserGoal;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserGoal(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserGoal.getUserId());
            objCallableStatement.setInt("WeightStatusID", objInnerUserGoal.getWeightStatusId());
            objCallableStatement.setLong("DailyCaloriesIntake", objInnerUserGoal.getDailyCaloriesInTake());
            objCallableStatement.setFloat("IdealWeight", objInnerUserGoal.getIdealWeight());
            objCallableStatement.setString("GoalDescription", objInnerUserGoal.getGoalDescription());
            objCallableStatement.setLong("TotalCaloriesToBurn", objInnerUserGoal.getTotalCaloriesToBurn());
            objCallableStatement.setInt("BurnedCalories", objInnerUserGoal.getBurnedCalories());
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerUserGoal.getDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("Date", tsDate);
            
            objCallableStatement.setInt("DailyBurnedCal", objInnerUserGoal.getDailyBurnedCal());
            objCallableStatement.setInt("WeeklyBurnedCal", objInnerUserGoal.getWeklyBurnedCal());
            objCallableStatement.setInt("MonthlyBurnedCal", objInnerUserGoal.getMonthlyBurnedCal());
            objCallableStatement.setInt("QuarterlyBurnedCal", objInnerUserGoal.getQuarterlyBurnedCal());
            objCallableStatement.setFloat("BMI", objInnerUserGoal.getbMI());
            
            objCallableStatement.registerOutParameter("UserGoalID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserGoalID = objCallableStatement.getLong("UserGoalID");
            objDbResponse.add(String.valueOf(intUserGoalID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Goal saved successfully, User Goal Details="+objUserGoal);
        }
        catch (Exception e)
        {
        	logger.info("Error in Adding User Goal");
        	objDbResponse.add("Error in Adding User Goal");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for Update UserGoal. 
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieving UserGoal.
     * @param objUserGoal
     * @return List of UserGoal 
     */
    @Override
    public List<UserGoal> RetriveData(Object objUserGoal) {
        UserGoal objOuterUserGoal = new UserGoal();
        List<UserGoal> objListInnerUserGoal = new ArrayList<UserGoal>();
        objOuterUserGoal =  (UserGoal) objUserGoal;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterUserGoal.getRequestType() == "ByUserOnly")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_SituationByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterUserGoal.getUserId());
            }
            else if(objOuterUserGoal.getRequestType() == "ByUserandDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_BurnedCaloriesByUserIDDate(?, ?, ?)}");
                
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterUserGoal.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterUserGoal.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setLong("UserID", objOuterUserGoal.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserGoal objInnerUserGoal = new UserGoal();
                objInnerUserGoal.setUserGoalId(objResultSet.getLong("UserGoalID"));
                objInnerUserGoal.setUserId(objResultSet.getLong("UserID"));
                objInnerUserGoal.setWeightStatusId(objResultSet.getInt("WeightStatusID"));
                objInnerUserGoal.setDailyCaloriesInTake(objResultSet.getLong("DailyCaloriesIntake"));
                objInnerUserGoal.setIdealWeight(objResultSet.getFloat("IdealWeight"));
                objInnerUserGoal.setGoalDescription(objResultSet.getString("GoalDescription"));
                objInnerUserGoal.setTotalCaloriesToBurn(objResultSet.getLong("TotalCaloriesToBurn"));
                objInnerUserGoal.setBurnedCalories(objResultSet.getInt("BurnedCalories"));
                objInnerUserGoal.setDate(objResultSet.getString("Date"));
               try
               {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
                    Date tsDate = sdf.parse(objResultSet.getTimestamp("Date").toString());
                    objInnerUserGoal.setDate( tsDate.toString());
               }
               catch(Exception ex)
               {}
                

                objInnerUserGoal.setDailyBurnedCal(objResultSet.getInt("DailyBurnedCal"));
                objInnerUserGoal.setWeklyBurnedCal(objResultSet.getInt("WeeklyBurnedCal"));
                objInnerUserGoal.setMonthlyBurnedCal(objResultSet.getInt("MonthlyBurnedCal"));
                objInnerUserGoal.setQuarterlyBurnedCal(objResultSet.getInt("QuarterlyBurnedCal"));
                objInnerUserGoal.setbMI(objResultSet.getFloat("BMI"));
                
                objListInnerUserGoal.add(objInnerUserGoal);
            }
            objConn.close();
            logger.info(" User Goal loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading  User Goal");
        }   
        return objListInnerUserGoal;
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
