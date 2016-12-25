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
package org.uclab.mm.dcl.llm.monitoring;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationLog;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.uclab.mm.dcl.llm.dataaccess.DBConnection;
import org.uclab.mm.dcl.llm.exception.DatabaseConnectionProblem;

/**
 *This class is used to define test cases for monitoring the physical activities.
 * @author Rizvi
 */
public class PhysicalActivityMonitorTest{
  
  public PhysicalActivityMonitorTest(){
  }
  
  @BeforeClass
  public static void setUpClass(){
  }
  
  @AfterClass
  public static void tearDownClass(){
  }
  
  @Before
  public void setUp(){
  }
  
  @After
  public void tearDown(){
  }

  /**
   * Test of doMonitor method, of class PhysicalActivityMonitor.
   */
  @Test//(expected=RuntimeException.class)
  public void testDoMonitor_Connection_String()throws Exception
  {
    
    try{
   
    System.out.println("doMonitor");
    Connection conn = DBConnection.getDBConnection().getConnection();
    String a = "";
    PhysicalActivityMonitor instance = new PhysicalActivityMonitor();
    instance.doMonitor(conn, a);
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

    /**
   * Test of doMonitor method, of class PhysicalActivityMonitor.
   */
  @Test
  public void testDoMonitor_Connection_String2()throws Exception
  {
    
    try{
   
    System.out.println("doMonitor");
    Connection conn = DBConnection.getDBConnection().getConnection();
    String a = "";
    PhysicalActivityMonitor instance = new PhysicalActivityMonitor();
    instance.doMonitor(conn, a);
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
    
  
}

 

 
