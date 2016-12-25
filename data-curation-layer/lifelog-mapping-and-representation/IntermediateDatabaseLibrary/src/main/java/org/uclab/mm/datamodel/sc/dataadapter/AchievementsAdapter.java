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
import org.uclab.mm.datamodel.sc.Achievements;

/**
 * This is AchievementsAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class AchievementsAdapter implements DataAccessInterface {
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(AchievementsAdapter.class);
    public AchievementsAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving Achievements. 
     * @param objAchievements
     * @return List of String
     */
    @Override
    public List<String> Save(Object objAchievements) {
        Achievements objInnerAchievements = new Achievements();
        objInnerAchievements =  (Achievements) objAchievements;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_Achievements(?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerAchievements.getUserID());
            objCallableStatement.setString("AchievementValue", objInnerAchievements.getAchievementValue());
            objCallableStatement.setString("AchievementDescription", objInnerAchievements.getAchievementDescription());
            
            if(objInnerAchievements.getAchievementDate()!= null)
            {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtDate = sdf.parse(objInnerAchievements.getAchievementDate());

               Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
               objCallableStatement.setTimestamp("AchievementDate", tsDate);
            }
            objCallableStatement.setString("SupportingLink", objInnerAchievements.getSupportingLink());
            objCallableStatement.setInt("AchievementStatusID", objInnerAchievements.getAchievementStatusID());
            
            objCallableStatement.registerOutParameter("AchievementID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intAchievementID = objCallableStatement.getLong("AchievementID");
            objDbResponse.add(String.valueOf(intAchievementID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Achievements saved successfully, Achievements Details="+objAchievements);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Achievements");
        	objDbResponse.add("Error in adding Achievements");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating Achievements. 
     * @param objAchievements
     * @return List of String
     */
    @Override
    public List<String> Update(Object objAchievements) {
        
        Achievements objInnerAchievements = new Achievements();
        objInnerAchievements =  (Achievements) objAchievements;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_Achievements(?, ?, ?, ?, ?, ?, ?)}");
            objCallableStatement.setLong("AchievementID", objInnerAchievements.getAchievementID());
            objCallableStatement.setLong("UserID", objInnerAchievements.getUserID());
            objCallableStatement.setString("AchievementValue", objInnerAchievements.getAchievementValue());
            objCallableStatement.setString("AchievementDescription", objInnerAchievements.getAchievementDescription());
            
            if(objInnerAchievements.getAchievementDate()!= null)
            {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtDate = sdf.parse(objInnerAchievements.getAchievementDate());

               Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
               objCallableStatement.setTimestamp("AchievementDate", tsDate);
            }
            objCallableStatement.setString("SupportingLink", objInnerAchievements.getSupportingLink());
            objCallableStatement.setInt("AchievementStatusID", objInnerAchievements.getAchievementStatusID());
            
            objCallableStatement.execute();
            
            Long intAchievementID = objInnerAchievements.getAchievementID();
            objDbResponse.add(String.valueOf(intAchievementID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Achievements updated successfully, Achievements Details="+objAchievements); 
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Achievements");
        	objDbResponse.add("Error in updating Achievements");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving Achievements. 
     * @param objAchievements
     * @return List of Achievements
     */
    @Override
    public List<Achievements> RetriveData(Object objAchievements) {
        
        Achievements objOuterAchievements = new Achievements();
        List<Achievements> objListInnerAchievements = new ArrayList<Achievements>();
        objOuterAchievements =  (Achievements) objAchievements;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterAchievements.getRequestType() == "ByUserOnly")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_AchievementsByUser(?)}");
                objCallableStatement.setLong("UserID", objOuterAchievements.getUserID());
            }
            else if(objOuterAchievements.getRequestType() == "ByUserandDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_AchievementsByUserIDDate(?, ?, ?)}");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterAchievements.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterAchievements.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setLong("UserID", objOuterAchievements.getUserID());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Achievements objInnerAchievements = new Achievements();
                objInnerAchievements.setAchievementID(objResultSet.getLong("AchievementID"));
                objInnerAchievements.setUserID(objResultSet.getLong("UserID"));
                objInnerAchievements.setAchievementValue(objResultSet.getString("AchievementValue"));
                objInnerAchievements.setAchievementDescription(objResultSet.getString("AchievementDescription"));
                
                if(objResultSet.getTimestamp("AchievementDate") != null)
                {
                    Timestamp tsAchievementDate = objResultSet.getTimestamp("AchievementDate");
                    objInnerAchievements.setAchievementDate(tsAchievementDate.toString());
                }
                objInnerAchievements.setSupportingLink(objResultSet.getString("SupportingLink"));
                   
               objInnerAchievements.setAchievementStatusID(objResultSet.getInt("AchievementStatusID"));
               objInnerAchievements.setAchievementStatusDescription(objResultSet.getString("RecommendationStatusDescription"));
               
               objListInnerAchievements.add(objInnerAchievements);
            }
            objConn.close();
            logger.info("Achievements loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Achievements");
        }   
        return objListInnerAchievements;
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
