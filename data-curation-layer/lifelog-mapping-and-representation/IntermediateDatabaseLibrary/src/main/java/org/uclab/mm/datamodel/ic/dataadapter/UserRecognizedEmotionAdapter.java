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
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;

/**
 * This is UserRecognizedEmotionAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserRecognizedEmotionAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(UserRecognizedEmotionAdapter.class);
    public UserRecognizedEmotionAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving UserRecognizedEmotion. 
     * @param objUserRecognizedEmotion
     * @return List of String
     */
    @Override
    public List<String> Save(Object objUserRecognizedEmotion) {
        UserRecognizedEmotion objInnerUserRecognizedEmotion = new UserRecognizedEmotion();
        objInnerUserRecognizedEmotion =  (UserRecognizedEmotion) objUserRecognizedEmotion;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_UserRecognizedEmotion(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUserRecognizedEmotion.getUserId());
            objCallableStatement.setString("EmotionLabel", objInnerUserRecognizedEmotion.getEmotionLabel());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtStartDate = sdf.parse(objInnerUserRecognizedEmotion.getStartTime());
            Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);
            
            objCallableStatement.setTimestamp("StartTime", tsStartDate);
                                             
            objCallableStatement.registerOutParameter("UserRecognizedEmotionID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intUserRecognizedEmotionID = objCallableStatement.getLong("UserRecognizedEmotionID");
            objDbResponse.add(String.valueOf(intUserRecognizedEmotionID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("User Recognized Emotion saved successfully, User Recognized Emotion Details="+objUserRecognizedEmotion);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding User Recognized Emotion");
        	objDbResponse.add("Error in adding User Recognized Emotion");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating UserRecognizedEmotion. 
     * Not implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This is implementation function for retrieving UserRecognizedEmotion.
     * @param objUserRecognizedEmotion
     * @return List of RetriveUserRecognizedEmotion 
     */
    @Override
    public List<UserRecognizedEmotion> RetriveData(Object objUserRecognizedEmotion) {
         UserRecognizedEmotion objOuterUserRecognizedEmotion = new UserRecognizedEmotion();
        List<UserRecognizedEmotion> objListInnerUserRecognizedEmotion = new ArrayList<UserRecognizedEmotion>();
        objOuterUserRecognizedEmotion =  (UserRecognizedEmotion) objUserRecognizedEmotion;
        
        try
        {
            CallableStatement objCallableStatement = null;
            Timestamp tsStartDate = null;
            Timestamp tsEndDate = null;
            if(objOuterUserRecognizedEmotion.getRequestType() == "LatestByUser")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_CurrentUserRecognizedEmotionByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterUserRecognizedEmotion.getUserId());
            }
            else if(objOuterUserRecognizedEmotion.getRequestType() == "UserDateRange")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserRecognizedEmotionByUserID(?, ?, ?)}");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtStartDate = sdf.parse(objOuterUserRecognizedEmotion.getStartTime());
                tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate = sdf.parse(objOuterUserRecognizedEmotion.getEndTime());
                tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterUserRecognizedEmotion.getUserId());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                UserRecognizedEmotion objInnerUserRecognizedEmotion = new UserRecognizedEmotion();
                objInnerUserRecognizedEmotion.setUserRecognizedEmotionId(objResultSet.getLong("UserRecognizedEmotionID"));
                objInnerUserRecognizedEmotion.setUserId(objResultSet.getLong("UserID"));
                objInnerUserRecognizedEmotion.setEmotionLabel(objResultSet.getString("EmotionLabel"));
                
                if(objResultSet.getTimestamp("StartTime") != null)
                {
                    tsStartDate = objResultSet.getTimestamp("StartTime");// updated
                    objInnerUserRecognizedEmotion.setStartTime( tsStartDate.toString());
                }
                
                if(objResultSet.getTimestamp("EndTime") != null)
                {
                    tsEndDate = objResultSet.getTimestamp("EndTime");// updated
                    objInnerUserRecognizedEmotion.setEndTime( tsEndDate.toString());
                }
                
                objInnerUserRecognizedEmotion.setDuration(objResultSet.getLong("Duration"));
                
                objListInnerUserRecognizedEmotion.add(objInnerUserRecognizedEmotion);
            }
            objConn.close();
            logger.info("User Recognized emotion are loaded successfully.");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading User Recognized emotion");
        }   
        return objListInnerUserRecognizedEmotion;
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
