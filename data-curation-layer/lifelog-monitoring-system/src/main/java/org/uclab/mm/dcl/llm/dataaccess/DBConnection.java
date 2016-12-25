/**
 * 
 * Copyright [2016] [Bilal Ali]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *  ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */
package org.uclab.mm.dcl.llm.dataaccess;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.uclab.mm.dcl.llm.exception.DatabaseConnectionProblem;

/**
 * This class is used to define the connection with database. JDBC driver is used to connect.
 * @author Rizvi
 */
public class DBConnection{

  private static Connection conn = null;

  /**
   * Constructor to establish the connection with database.
   */
  private DBConnection(){ 
    try{
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     
      conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MMLLM;user=sa;password=123");
      
    } catch (  SQLException | ClassNotFoundException exp){
      
     StringWriter errors = new StringWriter();
      exp.printStackTrace(new PrintWriter(errors));
      System.out.println("conn exception "+ exp);
      }
  }

  /**
   * This method is used to access the connection
   * @return 
   */
  public Connection getConnection(){
    return conn;
  }
/**
 * This method is used to get the established connection with database.
 * @return 
 */
  public static DBConnection getDBConnection(){
    return DBConnectionHolder.INSTANCE;
  }

  /**
   * This method is defined to access same database connection.
   */
  private static class DBConnectionHolder {
    
    private static final DBConnection INSTANCE = new DBConnection();
  
  }
}
