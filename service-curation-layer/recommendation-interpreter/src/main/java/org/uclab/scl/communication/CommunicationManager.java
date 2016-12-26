/**
 * Copyright [2016] [Muhammad Afzal]
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
 * limitations under the License.
 */
package org.uclab.scl.communication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.interpreterdatamodel.CurrentContext;

import org.uclab.scl.interpreterdatamodel.FoodLog;
import org.uclab.scl.interpreterdatamodel.LifeLogData;


import org.uclab.scl.interpreterdatamodel.SNSTrends;


import org.uclab.scl.outputModel.InterpretationModel.PhyActivityExplaination;

/**
 * This class holds all the client methods for external layer communication in order to get, store, and update data from/to the databases.
 * @version MM v2.5
 * @author Afzal
 *
 */
public class CommunicationManager {
  private static Logger LOG = LogManager.getRootLogger();
  
  private String LIFELOG_DCL_SERVER1 = "http://127.0.0.1:8080/MMDataCurationRestfulService/webresources/DataCuration";
  private String LIFELOG_DCL_SERVER2 = "http://127.0.0.1:8081/MMDataCurationRestfulService/webresources/InformationCuration";
  private String LIFELOG_ICL_SERVER = "http://127.0.0.1:8082/MMDataCurationRestfulService/webresources/InformationCuration";
  private String LIFELOG_SCL_SERVER = "http://127.0.0.1:8083/MMDataCurationRestfulService/webresources/ServiceCuration";
  private String LIFELOG_SL_SERVER = "http://127.0.0.1/MMv2.5/snsservice.php?";
  
  private CommunicationManager() {
    if(!loadConfiguration()){
      LOG.error("Cannot load configuration file <Application.properties>");
    }
  }
  private boolean loadConfiguration(){
    try{
      Properties prop = new Properties();
      InputStream propInstream = new FileInputStream(getClass().getClassLoader().getResource("Application.properties").getFile());
      prop.load(propInstream);
      propInstream.close();
      LIFELOG_DCL_SERVER1 = prop.getProperty("LIFELOG_DCL_SERVER1");
      LIFELOG_DCL_SERVER2 = prop.getProperty("LIFELOG_DCL_SERVER2");
      LIFELOG_ICL_SERVER = prop.getProperty("LIFELOG_ICL_SERVER");
      LIFELOG_SCL_SERVER = prop.getProperty("LIFELOG_SCL_SERVER");
      LIFELOG_SL_SERVER = prop.getProperty("LIFELOG_SL_SERVER");
      
    }catch(IOException e){
      return false;
    }
    return true;
  }
  
  /**
   * This method gets food category (carbohydrates, fat, etc.) as input and returns social trends of individual food item in that category in terms of frequency as output. 
   * <p>
   * @param foodCatagory
   * @return trends
   */
  public SNSTrends getSNSTrendsFromSLforFoods(String foodCatagory) {
    AbstractRestClient<SNSTrends, String> snsFoodTrendsWS = new SLSNSTrendsWSClient(LIFELOG_SL_SERVER);
    String resourceUser_GETURI = "type=" + foodCatagory;
    SNSTrends snsFoodTrends = snsFoodTrendsWS.GetData(resourceUser_GETURI);

    return snsFoodTrends;
  }
  /**
   * This method takes user identifier and returns the whole record of that user stored in the database.
   * <p>
   * @param userID
   * @return user
   */
//  public User getUser(long userID) {
//    AbstractRestClient<User, String> userWSClient = new DCUserWSClient(LIFELOG_DCL_SERVER1);
//    String resourceUser_GETURI = "/User/" + String.valueOf(userID);
//    User user = userWSClient.GetData(resourceUser_GETURI);
//    return user;
//
//  }
  /**
   * Takes foodLog object by setting the mandatory parameter (user identifier) and returns the list of foodLog objects
   * <p>
   * @param foodLog
   * @return food list
   */
  public List<FoodLog> getFoodLog(FoodLog foodLog) {

    AbstractRestClient<List<FoodLog>, List<FoodLog>> foodLogClient = new DCFoodLogWSClient(LIFELOG_DCL_SERVER2);
    String resourceFoodLog = "/GetFoodLogByUserIDDateRange";
    List<FoodLog> foodLogList = new ArrayList<>();
    foodLogList.add(foodLog);
    List<FoodLog> userFoodLogDetails = foodLogClient.PostData(resourceFoodLog, foodLogList);
    return userFoodLogDetails;
  }
 
  /**
   * Based on user identifier, the object of Current Context (location, high level context, etc.) is returned
   * <p>
   * @param userID
   * @return user context
   */
  public CurrentContext getCurrentContext(long userID) {
    AbstractRestClient<CurrentContext, String> currentContextWSClient = new DCCurrentContextWSClient(LIFELOG_SCL_SERVER);
    String resourceCurrentContext_GETURI = "/UserCurrentContext/" + String.valueOf(userID);
    CurrentContext currentContexts = currentContextWSClient.GetData(resourceCurrentContext_GETURI);
    return currentContexts;
  }
  

  /**
   * This method is used to save the explanation of recommendation in the Lifelog database.
   * <p>
   * @param pAExplanation
   * @return List<String>
   */
  public List<String> persistPAExplanations(List<PhyActivityExplaination> pAExplanation) {
    AbstractRestClient<List<PhyActivityExplaination>, List<String>> pAExplanationWSClient = new PhysicalActivityExplanationWSClient(LIFELOG_SCL_SERVER);
    String resourcePhyActRecURI = "/AddRecommendationExplanation";
    List<String> explanationResult = pAExplanationWSClient.PostData(resourcePhyActRecURI, pAExplanation);
    return explanationResult;
  }
  
  /**
   * This method returns the list of lifeLogData objects for a particular user set in lifeLogData object.
   * <p>
   * @param lifeLogData
   * @return LifeLogData List
   */
  public List<LifeLogData> getLifeLogData(LifeLogData lifeLogData) {
    AbstractRestClient<List<LifeLogData>, List<LifeLogData>> lifeLogDataClient = new DCLifeLogDataWSClient(LIFELOG_ICL_SERVER);
    String resourceLifelogData_GETURI = "/GetUserRecognizedActivityAccumulateByUserAndDate";
    List<LifeLogData> usrLifeLogDataRequest = new ArrayList<>();
    usrLifeLogDataRequest.add(lifeLogData);
    List<LifeLogData> listLifeLogData = lifeLogDataClient.PostData(resourceLifelogData_GETURI, usrLifeLogDataRequest);
    return listLifeLogData;
  }
  
  /**
   * Singleton pattern: making sure one instance of DataCurationCommunicationManager
   * <p>
   * @return Communication Manager
   */
  public static CommunicationManager getCommunicationManager() {
    return CommunicationManagerHolder.INSTANCE;
  }

  private static class CommunicationManagerHolder {
    private static final CommunicationManager INSTANCE = new CommunicationManager();
  }
}
