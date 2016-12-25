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
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sl.FactsFeedback;

/**
 * This is FactsFeedbackAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class FactsFeedbackAdapter implements DataAccessInterface {

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(FactsFeedbackAdapter.class);
    public FactsFeedbackAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    @Override
    public List<String> Save(Object objFactsFeedback) {
        FactsFeedback objInnerFactsFeedback = new FactsFeedback();
        objInnerFactsFeedback =  (FactsFeedback) objFactsFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_FactsFeedback(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("FactID", objInnerFactsFeedback.getFactID());
            objCallableStatement.setLong("UserID", objInnerFactsFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerFactsFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerFactsFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerFactsFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
            
                        
            objCallableStatement.registerOutParameter("FactsFeedbackID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intFactsFeedbackID = objCallableStatement.getLong("FactsFeedbackID");
            objDbResponse.add(String.valueOf(intFactsFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objFactsFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Facts Feedback");
        	objDbResponse.add("Error in adding Facts Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    @Override
    public List<String> Update(Object objFactsFeedback) {
        FactsFeedback objInnerFactsFeedback = new FactsFeedback();
        objInnerFactsFeedback =  (FactsFeedback) objFactsFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_FactsFeedback(?, ?, ?, ?, ?, ?)}");
            objCallableStatement.setLong("FactsFeedbackID", objInnerFactsFeedback.getFactsFeedbackID());
            objCallableStatement.setLong("FactID", objInnerFactsFeedback.getFactID());
            objCallableStatement.setLong("UserID", objInnerFactsFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerFactsFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerFactsFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerFactsFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
                        
            objCallableStatement.execute();
            
            Long intFactsFeedbackID = objInnerFactsFeedback.getFactsFeedbackID();
            objDbResponse.add(String.valueOf(intFactsFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Facts Feedback saved successfully, Facts Feedback Details="+objFactsFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Facts Feedback");
        	objDbResponse.add("Error in adding Facts Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving FactsFeedback.
     * @param objFactsFeedback
     * @return List of FactsFeedback 
     */
    @Override
    public List<FactsFeedback> RetriveData(Object objFactsFeedback) {
        FactsFeedback objOuterFactsFeedback = new FactsFeedback();
        List<FactsFeedback> objListInnerFactsFeedback = new ArrayList<FactsFeedback>();
        objOuterFactsFeedback =  (FactsFeedback) objFactsFeedback;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterFactsFeedback.getRequestType() == "ByFactID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FactsFeedbackByFactID(?)}");
                objCallableStatement.setLong("FactID", objOuterFactsFeedback.getFactID());
            }
            else if(objOuterFactsFeedback.getRequestType() == "ByUserID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FactsFeedbackByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterFactsFeedback.getUserID());
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                FactsFeedback objInnerFactsFeedback = new FactsFeedback();
                
                objInnerFactsFeedback.setFactsFeedbackID(objResultSet.getLong("FactsFeedbackID"));
                objInnerFactsFeedback.setFactID(objResultSet.getLong("FactID"));
                objInnerFactsFeedback.setUserID(objResultSet.getLong("UserID"));
                objInnerFactsFeedback.setRate(objResultSet.getInt("Rate"));
                objInnerFactsFeedback.setReason(objResultSet.getString("Reason"));
                
                 if(objResultSet.getTimestamp("FeedbackDate") != null)
                {
                    Timestamp tsFeedbackDate = objResultSet.getTimestamp("FeedbackDate");
                    objInnerFactsFeedback.setFeedbackDate(tsFeedbackDate.toString());
                }
                 
                objInnerFactsFeedback.setSituationCategoryID(objResultSet.getInt("SituationCategoryID"));
                objInnerFactsFeedback.setSituationCategoryDescription(objResultSet.getString("SituationCategoryDescription"));
                 
                objListInnerFactsFeedback.add(objInnerFactsFeedback);
            }
            objConn.close();
            logger.info("Facts Feedback loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Facts Feedback");
        }   
        return objListInnerFactsFeedback;
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
