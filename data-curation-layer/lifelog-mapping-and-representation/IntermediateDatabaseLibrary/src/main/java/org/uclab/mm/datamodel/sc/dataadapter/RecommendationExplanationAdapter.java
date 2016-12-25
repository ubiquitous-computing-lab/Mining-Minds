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
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.RecommendationExplanation;

/**
 * This is RecommendationExplanationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class RecommendationExplanationAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(RecommendationExplanationAdapter.class);
    public RecommendationExplanationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    @Override
    public List<String> Save(Object objRecommendationExplanation) {
        RecommendationExplanation objInnerRecommendationExplanation = new RecommendationExplanation();
        objInnerRecommendationExplanation =  (RecommendationExplanation) objRecommendationExplanation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_RecommendationExplanation(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationExplanation.getRecommendationID());
            objCallableStatement.setString("FactExplanation", objInnerRecommendationExplanation.getFactExplanation());
            objCallableStatement.setInt("FactCategoryID", objInnerRecommendationExplanation.getFactCategoryID());
            
            objCallableStatement.registerOutParameter("RecommendationExplanationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intRecommendationExplanationID = objCallableStatement.getLong("RecommendationExplanationID");
            objDbResponse.add(String.valueOf(intRecommendationExplanationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("RecommendationExplanation saved successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding RecommendationExplanation");
        	objDbResponse.add("Error in adding RecommendationExplanation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    @Override
    public List<String> Update(Object objRecommendationExplanation) {
        RecommendationExplanation objInnerRecommendationExplanation = new RecommendationExplanation();
        objInnerRecommendationExplanation =  (RecommendationExplanation) objRecommendationExplanation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_RecommendationExplanation(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationExplanationID", objInnerRecommendationExplanation.getRecommendationExplanationID());
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationExplanation.getRecommendationID());
            objCallableStatement.setString("FactExplanation", objInnerRecommendationExplanation.getFactExplanation());
            objCallableStatement.setInt("FactCategoryID", objInnerRecommendationExplanation.getFactCategoryID());
            
            objCallableStatement.execute();
            
            Long intRecommendationExplanationID = objInnerRecommendationExplanation.getRecommendationExplanationID();
            objDbResponse.add(String.valueOf(intRecommendationExplanationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("RecommendationExplanation updated successfully, RecommendationExplanation Details="+objRecommendationExplanation);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating RecommendationExplanation");
        	objDbResponse.add("Error in updating RecommendationExplanation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of RecommendationExplanation
     */
    @Override
    public List<RecommendationExplanation> RetriveData(Object objRecommendationExplanation) {
        
        RecommendationExplanation objOuterRecommendationExplanation = new RecommendationExplanation();
        List<RecommendationExplanation> objListInnerRecommendationExplanation = new ArrayList<RecommendationExplanation>();
        objOuterRecommendationExplanation =  (RecommendationExplanation) objRecommendationExplanation;
        
        try
        {
            CallableStatement objCallableStatement = null;
            objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationExplanationByRecommendationID(?)}");
                
            objCallableStatement.setLong("RecommendationID", objOuterRecommendationExplanation.getRecommendationID());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                RecommendationExplanation objInnerRecommendationExplanation = new RecommendationExplanation();
                
                objInnerRecommendationExplanation.setRecommendationExplanationID(objResultSet.getLong("RecommendationExplanationID"));
                objInnerRecommendationExplanation.setRecommendationID(objResultSet.getLong("RecommendationID"));
                objInnerRecommendationExplanation.setFactExplanation(objResultSet.getString("FactExplanation"));
                objInnerRecommendationExplanation.setFactCategoryID(objResultSet.getInt("FactCategoryID"));
                objInnerRecommendationExplanation.setFactCategoryDescription(objResultSet.getString("FactCategoryDescription"));
                
                objListInnerRecommendationExplanation.add(objInnerRecommendationExplanation);
            }
            objConn.close();
            logger.info("Recommendation Explanation loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Recommendation Explanation");
        }   
        return objListInnerRecommendationExplanation;
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
