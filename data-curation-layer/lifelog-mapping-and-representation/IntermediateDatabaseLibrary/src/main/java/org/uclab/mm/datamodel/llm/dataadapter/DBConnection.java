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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rizvi
 */
public class DBConnection {
    private static Connection conn = null;
    
    private DBConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Taqdir DB
           conn = DriverManager.getConnection("jdbc:sqlserver://163.180.116.194:1433;instance=SQLEXPRESS;databaseName=DBMiningMindsV1_5;user=sa;password=adminsa");
            // end of Taqdir DB
            
            //Local Bilal DB
           //conn = DriverManager.getConnection("jdbc:sqlserver://163.180.173.135:1433;databaseName=MMV2;user=sa;password=123");
             //end of Local DB
              //Local Usman DB
          //conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DBMiningMindsV1_5;user=usman;password=usman;");
              //end of Local DB
        }catch(SQLException | ClassNotFoundException exp)
        { 
            StringWriter errors = new StringWriter();
            exp.printStackTrace(new PrintWriter(errors));
            
        }
    }
    
    public Connection getConnection()
    {
        return conn;
    }
    
    public static DBConnection getDBConnection() {
        return DBConnectionHolder.INSTANCE;
    }
    
    private static class DBConnectionHolder {

        private static final DBConnection INSTANCE = new DBConnection();
    }
}
