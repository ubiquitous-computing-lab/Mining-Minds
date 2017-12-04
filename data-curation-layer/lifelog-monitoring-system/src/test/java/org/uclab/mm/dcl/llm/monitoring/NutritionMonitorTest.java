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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationNotification;

/**
 * This class is used to define test cases for monitoring the nutrition.
 * @author Rizvi
 */
public class NutritionMonitorTest{
  
  public NutritionMonitorTest(){
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
   * Test of doMonitor method, of class NutritionMonitor for null values.
   * This method is used to  
   */
 @Test
  public void testDoMonitor_Connection_String() throws Exception{
    System.out.println("doMonitor");
    Connection dbConn = null;
    String a = "";
    NutritionMonitor instance = new NutritionMonitor();
    instance.doMonitor(dbConn, a);

  }

  /**
   * Test of doMonitor method, of class NutritionMonitor for initial value of food.
   */
  @Test
  public void testDoMonitor_FoodLog(){
    System.out.println("doMonitor");
    FoodLog ObjFoodLog=new FoodLog();
    Long a=35L;
    String b="Chicken";
     ObjFoodLog.setUserId(a);
      ObjFoodLog.setFoodName(b);
    NutritionMonitor instance = new NutritionMonitor();
    instance.doMonitor(ObjFoodLog);

  }


    /**
   * Test of doMonitor method, of class NutritionMonitor for incremental value of food.
   */
  @Test
  public void testDoMonitor_FoodLog1(){
    System.out.println("doMonitor");
    FoodLog ObjFoodLog=new FoodLog();
    Long a=35L;
    String b="Chicken";
     ObjFoodLog.setUserId(a);
      ObjFoodLog.setFoodName(b);
    NutritionMonitor instance = new NutritionMonitor();
    instance.doMonitor(ObjFoodLog);
    }
  
    /**
   * Test of doMonitor method, of class NutritionMonitor for incremental value of food.
   */
  @Test
  public void testDoMonitor_FoodLog2(){
    System.out.println("doMonitor");
    FoodLog ObjFoodLog=new FoodLog();
    Long a=35L;
    String b="Chicken";
     ObjFoodLog.setUserId(a);
      ObjFoodLog.setFoodName(b);
    NutritionMonitor instance = new NutritionMonitor();
    instance.doMonitor(ObjFoodLog);

  }
  
  /**
   * Test of doMonitor method, of class NutritionMonitor for incremental value of food.
   */
  @Test
  public void testDoMonitor_FoodLog3(){
    System.out.println("doMonitor");
    FoodLog ObjFoodLog=new FoodLog();
    Long a=35L;
    String b="Chicken";
     ObjFoodLog.setUserId(a);
      ObjFoodLog.setFoodName(b);
    NutritionMonitor instance = new NutritionMonitor();
    instance.doMonitor(ObjFoodLog);
    
  }
  
  /**
   * Test of getNotificationMessage method, of class NutritionMonitor for threshold value of nutrition.
   */
  @Test(expected=java.lang.AssertionError.class)
  public void testGetNotificationMessage(){
    System.out.println("getNotificationMessage");
    String MapId = "651";
    String UserId = "35";
    String ConsumedFat = "85";
    NutritionMonitor instance = new NutritionMonitor();
    SituationNotification expResult = null;
    SituationNotification result = instance.getNotificationMessage(MapId, UserId, ConsumedFat);
    assertEquals(expResult, result);
  }
   
}








