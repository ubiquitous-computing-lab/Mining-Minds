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
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sl.RecommendationFeedback;

/**
 * This is RecommendationFeedbackAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class RecommendationFeedbackAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(RecommendationFeedbackAdapter.class);
    public RecommendationFeedbackAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */
    @Override
    public List<String> Save(Object objRecommendationFeedback) {
        RecommendationFeedback objInnerRecommendationFeedback = new RecommendationFeedback();
        objInnerRecommendationFeedback =  (RecommendationFeedback) objRecommendationFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_RecommendationFeedback(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationFeedback.getRecommendationID());
            objCallableStatement.setLong("UserID", objInnerRecommendationFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerRecommendationFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerRecommendationFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerRecommendationFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
            
                        
            objCallableStatement.registerOutParameter("RecommendationFeedbackID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intRecommendationFeedbackID = objCallableStatement.getLong("RecommendationFeedbackID");
            objDbResponse.add(String.valueOf(intRecommendationFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Recommendation Feedback saved successfully, Recommendation Feedback Details="+objRecommendationFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Recommendation Feedback");
        	objDbResponse.add("Error in adding Recommendation Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */
    @Override
    public List<String> Update(Object objRecommendationFeedback) {
        RecommendationFeedback objInnerRecommendationFeedback = new RecommendationFeedback();
        objInnerRecommendationFeedback =  (RecommendationFeedback) objRecommendationFeedback;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_RecommendationFeedback(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationFeedbackID", objInnerRecommendationFeedback.getRecommendationFeedbackID());
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationFeedback.getRecommendationID());
            objCallableStatement.setLong("UserID", objInnerRecommendationFeedback.getUserID());
            objCallableStatement.setInt("Rate", objInnerRecommendationFeedback.getRate());
            objCallableStatement.setString("Reason", objInnerRecommendationFeedback.getReason());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerRecommendationFeedback.getFeedbackDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("FeedbackDate", tsDate);
            
            objCallableStatement.execute();
            
            Long intRecommendationFeedbackID = objInnerRecommendationFeedback.getRecommendationFeedbackID();
            objDbResponse.add(String.valueOf(intRecommendationFeedbackID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Recommendation Feedback updated successfully, Recommendation Feedback Details="+objRecommendationFeedback);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Recommendation Feedback");
        	objDbResponse.add("Error in updating Recommendation Feedback");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of RecommendationFeedback
     */
    @Override
    public List<RecommendationFeedback> RetriveData(Object objRecommendationFeedback) {
        RecommendationFeedback objOuterRecommendationFeedback = new RecommendationFeedback();
        List<RecommendationFeedback> objListInnerRecommendationFeedback = new ArrayList<RecommendationFeedback>();
        objOuterRecommendationFeedback =  (RecommendationFeedback) objRecommendationFeedback;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterRecommendationFeedback.getRequestType() == "ByRecommendationID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationFeedbackByRecommendationID(?)}");
                objCallableStatement.setLong("RecommendationID", objOuterRecommendationFeedback.getRecommendationID());
            }
            else if(objOuterRecommendationFeedback.getRequestType() == "ByUserID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationFeedbackByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterRecommendationFeedback.getUserID());
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                RecommendationFeedback objInnerRecommendationFeedback = new RecommendationFeedback();
                
                objInnerRecommendationFeedback.setRecommendationFeedbackID(objResultSet.getLong("RecommendationFeedbackID"));
                objInnerRecommendationFeedback.setRecommendationID(objResultSet.getLong("RecommendationID"));
                objInnerRecommendationFeedback.setUserID(objResultSet.getLong("UserID"));
                objInnerRecommendationFeedback.setRate(objResultSet.getInt("Rate"));
                objInnerRecommendationFeedback.setReason(objResultSet.getString("Reason"));
                
                if(objResultSet.getTimestamp("FeedbackDate") != null)
                {
                    Timestamp tsFeedbackDate = objResultSet.getTimestamp("FeedbackDate");
                    objInnerRecommendationFeedback.setFeedbackDate(tsFeedbackDate.toString());
                }
                
                objInnerRecommendationFeedback.setSituationCategoryID(objResultSet.getInt("SituationCategoryID"));
                objInnerRecommendationFeedback.setSituationCategoryDescription(objResultSet.getString("SituationCategoryDescription"));
                
                 
                objListInnerRecommendationFeedback.add(objInnerRecommendationFeedback);
            }
            objConn.close();
            logger.info("Recommendation Feedback loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Recommendation Feedback");
        }   
        return objListInnerRecommendationFeedback;
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
