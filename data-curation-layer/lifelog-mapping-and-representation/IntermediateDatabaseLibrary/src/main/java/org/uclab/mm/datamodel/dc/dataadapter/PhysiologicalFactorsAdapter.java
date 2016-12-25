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
import org.uclab.mm.datamodel.dc.PhysiologicalFactors;

/**
 * This is PhysiologicalFactorsAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class PhysiologicalFactorsAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(PhysiologicalFactorsAdapter.class);
    public PhysiologicalFactorsAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving PhysiologicalFactors. 
     * @param objPhysiologicalFactors
     * @return List of String
     */
    
   @Override
    public List<String> Save(Object objPhysiologicalFactors) {
        PhysiologicalFactors objInnerPhysiologicalFactors = new PhysiologicalFactors();
        objInnerPhysiologicalFactors =  (PhysiologicalFactors) objPhysiologicalFactors;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_PhysiologicalFactors(?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerPhysiologicalFactors.getUserId());
            objCallableStatement.setFloat("Weight", objInnerPhysiologicalFactors.getWeight());
            objCallableStatement.setFloat("height", objInnerPhysiologicalFactors.getHeight());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtDate = sdf.parse(objInnerPhysiologicalFactors.getDate());
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("Date", tsDate);
            objCallableStatement.setFloat("IdealWeight", objInnerPhysiologicalFactors.getIdealWeight());
            objCallableStatement.setFloat("TargetWeight", objInnerPhysiologicalFactors.getTargetWeight());
            objCallableStatement.registerOutParameter("PhysiologicalFactorID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intPhysiologicalFactorID = objCallableStatement.getInt("PhysiologicalFactorID");
            objDbResponse.add(String.valueOf(intPhysiologicalFactorID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Physiological factor saved successfully, User Details="+objPhysiologicalFactors);
        }
        catch (Exception e)
        {
             e.printStackTrace();
             objDbResponse.add("rror in adding Physiological factors");
             logger.info("Error in adding Physiological factors");
        } 
        return objDbResponse;
    }

   /**
    * This is implementation function for updating PhysiologicalFactors. 
    * @param objPhysiologicalFactors
    * @return List of String
    */
    @Override
    public List<String> Update(Object objPhysiologicalFactors) {
        PhysiologicalFactors objInnerPhysiologicalFactors = new PhysiologicalFactors();
        objInnerPhysiologicalFactors =  (PhysiologicalFactors) objPhysiologicalFactors;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_PhysiologicalFactors(?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("PhysiologicalFactorID", objInnerPhysiologicalFactors.getPhysiologicalFactorId());
            objCallableStatement.setLong("UserID", objInnerPhysiologicalFactors.getUserId());
            objCallableStatement.setFloat("Weight", objInnerPhysiologicalFactors.getWeight());
            objCallableStatement.setFloat("height", objInnerPhysiologicalFactors.getHeight());

             SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtDate =sdf.parse( objInnerPhysiologicalFactors.getDate());
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("Date", tsDate);
            objCallableStatement.setFloat("IdealWeight", objInnerPhysiologicalFactors.getIdealWeight());
            objCallableStatement.setFloat("TargetWeight", objInnerPhysiologicalFactors.getTargetWeight());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerPhysiologicalFactors.getPhysiologicalFactorId()));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Physiological factor updated successfully, User Details="+objPhysiologicalFactors);
        }
        catch (Exception e)
        {
        	logger.info("Error in updated Physiological factors");
        	objDbResponse.add("Error in updated Physiological factors");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving PhysiologicalFactors.
     * @param objPhysiologicalFactors
     * @return List of PhysiologicalFactors 
     */
    @Override
    public List<PhysiologicalFactors> RetriveData(Object objPhysiologicalFactors) {
        PhysiologicalFactors objOuterPhysiologicalFactors = new PhysiologicalFactors();
        List<PhysiologicalFactors> objListInnerPhysiologicalFactors = new ArrayList<PhysiologicalFactors>();
        objOuterPhysiologicalFactors =  (PhysiologicalFactors) objPhysiologicalFactors;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_PhysiologicalFactorsByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterPhysiologicalFactors.getUserId());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                PhysiologicalFactors objInnerPhysiologicalFactors = new PhysiologicalFactors();
                objInnerPhysiologicalFactors.setPhysiologicalFactorId(objResultSet.getLong("PhysiologicalFactorID"));
                objInnerPhysiologicalFactors.setUserId(objResultSet.getLong("UserID"));
                objInnerPhysiologicalFactors.setWeight(objResultSet.getFloat("Weight"));
                objInnerPhysiologicalFactors.setHeight(objResultSet.getFloat("height"));
                
                Timestamp tsDate = objResultSet.getTimestamp("Date");
                objInnerPhysiologicalFactors.setDate(tsDate.toString());
                
                objInnerPhysiologicalFactors.setIdealWeight(objResultSet.getFloat("IdealWeight"));
                objInnerPhysiologicalFactors.setTargetWeight(objResultSet.getFloat("TargetWeight"));
                
                objListInnerPhysiologicalFactors.add(objInnerPhysiologicalFactors);
            }
            objConn.close();
            logger.info("Physiological is loaded successfully");
            
        }
        catch (Exception e)
        {
             logger.info("Error in Loading Physiological factors");
        }   
        return objListInnerPhysiologicalFactors;
    }

    /**
     * This is implementation function for retrieving PhysiologicalFactors.
     * Not implemented
     */
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
