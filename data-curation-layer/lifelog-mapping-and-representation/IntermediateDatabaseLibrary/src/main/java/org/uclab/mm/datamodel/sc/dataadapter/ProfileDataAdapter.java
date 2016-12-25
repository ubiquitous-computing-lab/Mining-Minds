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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.ProfileData;

/**
 * This is ProfileDataAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class ProfileDataAdapter implements DataAccessInterface {

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(ProfileDataAdapter.class);
    public ProfileDataAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ProfileData. 
     * Not Implemented
     */
    @Override
    public <T> List<String> Save(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for update ProfileData. 
     * Not Implemented
     */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This is implementation function for retrieving ProfileData. 
     * @param objProfileData
     * @return List of ProfileData
     */
     @Override
    public List<ProfileData> RetriveData(Object objProfileData) {
        ProfileData objOuterProfileData = new ProfileData();
        List<ProfileData> objListInnerProfileData = new ArrayList<ProfileData>();
        objOuterProfileData =  (ProfileData) objProfileData;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ProfileDataByUserID(?)}");
            objCallableStatement.setLong("UserID", objOuterProfileData.getUserID());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ProfileData objInnerProfileData = new ProfileData();
                objInnerProfileData.setGenderId(objResultSet.getInt("GenderID"));
                objInnerProfileData.setGenderDescription(objResultSet.getString("GenderDescription"));
                
                 Timestamp tsDateOfBirth = objResultSet.getTimestamp("DateOfBirth"); //updated
                objInnerProfileData.setDateOfBirth(tsDateOfBirth.toString());
                
                objInnerProfileData.setActivityLevelId(objResultSet.getInt("ActivityLevelID"));
                objInnerProfileData.setActivityLevelDescription(objResultSet.getString("ActivityLevelDescription"));
                objInnerProfileData.setHeight(objResultSet.getFloat("Height"));
                objInnerProfileData.setWeight(objResultSet.getFloat("Weight"));
                objListInnerProfileData.add(objInnerProfileData);
            }
            objConn.close();
            logger.info("Profile Data loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Profile Data");
        }   
        return objListInnerProfileData;
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
