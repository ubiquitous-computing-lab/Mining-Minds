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
import org.uclab.mm.datamodel.ic.FoodLog;


/**
 * This is FoodLogAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class FoodLogAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(FoodLogAdapter.class);
    public FoodLogAdapter()
    {
        
    }
   
    /**
     * This is implementation function for saving FoodLog. 
     * @param objFoodLog
     * @return List of String
     */
    @Override
    public List<String> Save(Object objFoodLog) {
        FoodLog objInnerFoodLog = new FoodLog();
        objInnerFoodLog =  (FoodLog) objFoodLog;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {
                  
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_FoodLog(?, ?, ?, ?, ?)}");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtEatingTime = sdf.parse(objInnerFoodLog.getEatingTime());
            Timestamp tsEatingTime = new Timestamp(dtEatingTime.getYear(),dtEatingTime.getMonth(), dtEatingTime.getDate(), dtEatingTime.getHours(), dtEatingTime.getMinutes(),dtEatingTime.getSeconds(),0);
                       
            objCallableStatement.setLong("UserID", objInnerFoodLog.getUserId());
            objCallableStatement.setString("FoodName", objInnerFoodLog.getFoodName());
            objCallableStatement.setTimestamp("EatingTime", tsEatingTime);
            
            if(objInnerFoodLog.getByteImage() != null)
            {
                objCallableStatement.setBytes("FoodImage", objInnerFoodLog.getByteImage());
            }
            else
            {
                objCallableStatement.setBytes("FoodImage", null);
            }
                                   
            objCallableStatement.registerOutParameter("FoodLogID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intFoodLogID = objCallableStatement.getLong("FoodLogID");
            objDbResponse.add(String.valueOf(intFoodLogID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("food log saved successfully, food log Details="+objFoodLog);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding food log");
        	objDbResponse.add("Error in adding food log");
        } 
        return objDbResponse;
    }

    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This is implementation function for retrieving FoodLog.
     * @param objFoodLog
     * @return List of FoodLog 
     */
    @Override
    public List<FoodLog> RetriveData(Object objFoodLog) {
        FoodLog objOuterFoodLog = new FoodLog();
        List<FoodLog> objListInnerFoodLog = new ArrayList<FoodLog>();
        objOuterFoodLog =  (FoodLog) objFoodLog;
        
        try
        {
            CallableStatement objCallableStatement = null;
            Timestamp tsStartDate = null;
            Timestamp tsEndDate = null;
                       
            if(objOuterFoodLog.getRequestType() == "LatestByUser")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FoodLogByUserLatest(?)}");
                objCallableStatement.setLong("UserID", objOuterFoodLog.getUserId());
                ResultSet objResultSet = objCallableStatement.executeQuery();
                while(objResultSet.next())
                {
                    FoodLog objInnerFoodLog = new FoodLog();
                    objInnerFoodLog.setUserId(objResultSet.getLong("UserID"));
                    objInnerFoodLog.setFoodName(objResultSet.getString("FoodName"));
                    Timestamp tsEatingTime = null;
                    if(objResultSet.getTimestamp("EatingTime") != null)
                    {
                        tsEatingTime = objResultSet.getTimestamp("EatingTime");// updated
                        objInnerFoodLog.setStartTime( tsEatingTime.toString());
                    }
                    else
                    {
                        objInnerFoodLog.setStartTime(null);
                    }
                    objListInnerFoodLog.add(objInnerFoodLog);
                }
                objConn.close();
                logger.info("food log loaded successfully");
            }
            else
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FoodLogByUserIDandDateRange(?, ?, ?)}");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               if(objOuterFoodLog.getStartTime() != null)
                {
                    Date dtStartDate = sdf.parse(objOuterFoodLog.getStartTime());
                    tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
                }
                else
                {
                    tsStartDate = null;
                }
                if(objOuterFoodLog.getStartTime() != null)
                {
                    Date dtEndDate = sdf.parse(objOuterFoodLog.getEndTime());
                    tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
                }
                else
                {
                    tsEndDate = null;
                }
                objCallableStatement.setLong("UserID", objOuterFoodLog.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
                
                ResultSet objResultSet = objCallableStatement.executeQuery();
                while(objResultSet.next())
                {
                    FoodLog objInnerFoodLog = new FoodLog();
                    objInnerFoodLog.setUserId(objResultSet.getLong("UserID"));
                    objInnerFoodLog.setFoodName(objResultSet.getString("FoodName"));
                    
                    objInnerFoodLog.setTotalFoodItem(objResultSet.getLong("TotalFoodItem"));
                    objInnerFoodLog.setEatingTime(objResultSet.getString("EatingTime"));
                    objInnerFoodLog.setTotalFat(objResultSet.getLong("TotalFat")); 
                    objInnerFoodLog.setTotalProtein(objResultSet.getLong("TotalProtein"));
                    objInnerFoodLog.setTotalCarbohydrate(objResultSet.getLong("TotalCarbohydrate"));
                     
                    objListInnerFoodLog.add(objInnerFoodLog);
                }
                objConn.close();
                logger.info("food log loaded successfully");
            }
        }
        catch (Exception e)
        {
        	logger.info("Error in loading  food log");
        }   
        return objListInnerFoodLog;
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
