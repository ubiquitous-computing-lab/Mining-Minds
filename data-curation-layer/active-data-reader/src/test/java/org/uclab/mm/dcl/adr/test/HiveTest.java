/*
 * Copyright (C) 2016 Usman Akhtar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License....
 */

package org.uclab.mm.dcl.adr.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveTest {
	
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	 
	  /**
	   * @param args
	   * @throws SQLException
	   */
	  public static void main(String[] args) throws SQLException {
	      try {
	      Class.forName(driverName);
	    } catch (ClassNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      System.exit(1);
	    }
	    //replace "hive" here with the name of the user the queries should run as
	    Connection con = DriverManager.getConnection("jdbc:hive2://163.180.116.94:10000/test1", "hive", "hive");
	    Statement stmt = con.createStatement();
	    String tableName = "testHiveDriverTable";
	   
	    // Testing >> show tables
	    String sql = "show tables '" + tableName + "'";
	    System.out.println("Running: " + sql);
	    ResultSet res = stmt.executeQuery(sql);
	    if (res.next()) {
	      System.out.println(res.getString(1));
	    }
	       // Testing >> describe table
	    sql = "describe " + tableName;
	    System.out.println("Running: " + sql);
	    res = stmt.executeQuery(sql);
	    while (res.next()) {
	      System.out.println(res.getString(1) + "\t" + res.getString(2));
	    }
	 
	  	 
	    // Testing Query  >> select * query
	    sql = "select * from " + tableName;
	    System.out.println("Running: " + sql);
	    res = stmt.executeQuery(sql);
	    while (res.next()) {
	      System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
	    }
	 
	    // Testing Regular Query  >> select * query regular hive query
	    sql = "select count(1) from " + tableName;
	    System.out.println("Running: " + sql);
	    res = stmt.executeQuery(sql);
	    while (res.next()) {
	      System.out.println(res.getString(1));
	    }
	  }
}
