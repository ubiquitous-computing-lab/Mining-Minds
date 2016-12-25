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
package org.uclab.mm.datamodel.llm.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.llm.LifelogMonitorActivity;

/**
 *
 * @author Taqdir Ali
 */
public class LifelogMonitorActivityAdapter implements DataAccessInterface {

    private Connection objConn;
    
    public LifelogMonitorActivityAdapter()
    {
        
    }
    
    @Override
    public <T> List<String> Save(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LifelogMonitorActivity> RetriveData(Object objLifelogMonitorActivity) {
        LifelogMonitorActivity objOuterLifelogMonitorActivity = new LifelogMonitorActivity();
        List<LifelogMonitorActivity> objListInnerLifelogMonitorActivity = new ArrayList<LifelogMonitorActivity>();
        objOuterLifelogMonitorActivity =  (LifelogMonitorActivity) objLifelogMonitorActivity;
        
        try
        {
            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_MonitorLifeLogByActivities(?, ?, ?, ?)}");
            objCallableStatement.setLong("UserID", objOuterLifelogMonitorActivity.getUserID());
            
            if(objOuterLifelogMonitorActivity.getEndTime() != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtDate = sdf.parse(objOuterLifelogMonitorActivity.getEndTime());

                Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
                objCallableStatement.setTimestamp("EndTime", tsDate);
            }
            objCallableStatement.setInt("TimeInterval", objOuterLifelogMonitorActivity.getTimeInterval());
            objCallableStatement.setInt("Threshold", objOuterLifelogMonitorActivity.getThrashold());
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            //ResultSet resultSet = statement.executeQuery("SELECT Name FROM ComputerStatus");
            while(objResultSet.next())
            {
                LifelogMonitorActivity objInnerLifelogMonitorActivity = new LifelogMonitorActivity();
                objInnerLifelogMonitorActivity.setUserID(objResultSet.getLong("UserID"));
                objInnerLifelogMonitorActivity.setActivityID(objResultSet.getInt("ActivityID"));
                objInnerLifelogMonitorActivity.setActivityDescription(objResultSet.getString("ActivityDescription"));
                objInnerLifelogMonitorActivity.setTotalTime(objResultSet.getInt("TotalTime"));
                
                objListInnerLifelogMonitorActivity.add(objInnerLifelogMonitorActivity);
            }
            objConn.close();
        }
        catch(SQLException ex)
        {
             ex.printStackTrace();
             System.err.println("Problem with sql server");
        }
        catch (Exception e)
        {
             e.printStackTrace();
             System.err.println("Problem in registration user!");
        }   
        return objListInnerLifelogMonitorActivity;
    }

    @Override
    public <T> List<T> Delete(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConfigureAdapter(Object objConf) {
        try{
        objConn = (Connection)objConf;
        }
        catch(Exception ex)
        {}
    }
    
}
