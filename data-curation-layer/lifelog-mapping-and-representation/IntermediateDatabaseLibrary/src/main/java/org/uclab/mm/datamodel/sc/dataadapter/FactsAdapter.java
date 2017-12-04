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
import org.uclab.mm.datamodel.sc.Facts;
import org.uclab.mm.datamodel.sc.Situation;

/**
 * This is FactsAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class FactsAdapter implements DataAccessInterface {
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(FactsAdapter.class);
    public FactsAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving Facts. 
     * @param objFacts
     * @return List of String
     */
    @Override
    public List<String> Save(Object objFacts) {
        Facts objInnerFacts = new Facts();
        objInnerFacts =  (Facts) objFacts;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_Facts(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("SituationID", objInnerFacts.getSituationID());
            objCallableStatement.setString("FactDescription", objInnerFacts.getFactDescription());
            objCallableStatement.setString("SupportingLinks", objInnerFacts.getSupportingLinks());
            
            if(objInnerFacts.getFactDate() != null)
            {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtDate = sdf.parse(objInnerFacts.getFactDate());

               Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
               objCallableStatement.setTimestamp("FactDate", tsDate);
            }
            objCallableStatement.setInt("FactStatusID", objInnerFacts.getFactStatusID());
            
            
            objCallableStatement.registerOutParameter("FactID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intFactID = objCallableStatement.getLong("FactID");
            objDbResponse.add(String.valueOf(intFactID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Facts saved successfully, Facts Feedback Details="+objFacts);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Facts");
        	objDbResponse.add("Error in adding Facts");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating Facts. 
     * @param objFacts
     * @return List of String
     */
    @Override
    public List<String> Update(Object objFacts) {
        Facts objInnerFacts = new Facts();
        objInnerFacts =  (Facts) objFacts;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_Facts(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("FactID", objInnerFacts.getFactID());
            objCallableStatement.setLong("SituationID", objInnerFacts.getSituationID());
            objCallableStatement.setString("FactDescription", objInnerFacts.getFactDescription());
            objCallableStatement.setString("SupportingLinks", objInnerFacts.getSupportingLinks());
            
            if(objInnerFacts.getFactDate() != null)
            {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtDate = sdf.parse(objInnerFacts.getFactDate());

               Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
               objCallableStatement.setTimestamp("FactDate", tsDate);
            }
            objCallableStatement.setInt("FactStatusID", objInnerFacts.getFactStatusID());
            
            objCallableStatement.execute();
            
            Long intFactID = objInnerFacts.getFactID();
            objDbResponse.add(String.valueOf(intFactID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Facts updated successfully, Facts Details="+objFacts);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Facts");
        	objDbResponse.add("Error in updating Facts");
        } 
        return objDbResponse;
    }
    
    /**
     * This is implementation function for retrieving Facts. 
     * @param objFacts
     * @return List of Facts
     */
    @Override
    public List<Facts> RetriveData(Object objFacts) {
        Facts objOuterFacts = new Facts();
        List<Facts> objListInnerFacts = new ArrayList<Facts>();
        objOuterFacts =  (Facts) objFacts;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterFacts.getRequestType() == "ByUserOnly")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FactsByUser(?)}");
                objCallableStatement.setLong("UserID", objOuterFacts.getUserID());
            }
            else if(objOuterFacts.getRequestType() == "ByUserandDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_FactsByUserIDDate(?, ?, ?)}");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterFacts.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterFacts.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setLong("UserID", objOuterFacts.getUserID());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Facts objInnerFacts = new Facts();
                objInnerFacts.setFactID(objResultSet.getLong("FactID"));
                objInnerFacts.setSituationID(objResultSet.getLong("SituationID"));
                objInnerFacts.setFactDescription(objResultSet.getString("FactDescription"));
                objInnerFacts.setSupportingLinks(objResultSet.getString("SupportingLinks"));
                
                if(objResultSet.getTimestamp("FactDate") != null)
                {
                    Timestamp tsFactDate = objResultSet.getTimestamp("FactDate");
                    objInnerFacts.setFactDate(tsFactDate.toString());
                }
                   
               objInnerFacts.setFactStatusID(objResultSet.getInt("FactStatusID"));
               objInnerFacts.setFactStatusDescription(objResultSet.getString("RecommendationStatusDescription"));
               
               objListInnerFacts.add(objInnerFacts);
            }
            objConn.close();
            logger.info("Facts loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Facts");
        }   
        return objListInnerFacts;
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
