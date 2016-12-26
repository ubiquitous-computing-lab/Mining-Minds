/* Copyright [2016] [Syed Imran Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.uclab.scl.framework.RecInterpreter.FoodItemRecommender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.communication.CommunicationManager;
import org.uclab.scl.interpreterdatamodel.SNSTrends;

public class SNSTrendIdentifier{
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapNutrientToCategory = new HashMap<>();
  static HashMap<String, String> mapCatToFooditems = new HashMap<>();
  static HashMap<String, String> mapFooditemsToNutrient = new HashMap<>();
  static HashMap<String, String> mapUserChoices = new HashMap<>();
  
  /**
   * The main purpose of this class is to process food related recommendations. This function receives the recommended nutrient label and userid
   * Current trends against the food category are received from [SO] which in turn calls SNS services provided by [SL]
   * Received food items are filtered based on the major nutrients and user's dislike list
   * Final list of food items is provided to getFoodRecommendation() function
   * 
   * @param nutrientLabel
   * @param userid
   * @return
   * @throws Exception
   */

  @SuppressWarnings("unchecked")
  public String processFoodRec(String nutrientLabel, String userid) throws Exception {  
	  
    NutrientToCategoryMapper nutritionToCat = new NutrientToCategoryMapper();
    CategoryToFooditemsMapper catToFood = new CategoryToFooditemsMapper();
    mapNutrientToCategory = nutritionToCat.NutrientToCategoryMapper();
    mapCatToFooditems = catToFood.CatToFooditemsMapper();
    String foodCategory = mapNutrientToCategory.containsKey(nutrientLabel) ? mapNutrientToCategory.get(nutrientLabel) : mapNutrientToCategory.get(null);
    if (foodCategory == null){
      LOG.debug("FoodCategory not found in the low repository");
      foodCategory = "Protein";
    }
    LOG.debug("Nutrient to Category: " + nutrientLabel + ": " + foodCategory);
    CommunicationManager commManager = CommunicationManager.getCommunicationManager();
    
    SNSTrends snsTrends = commManager.getSNSTrendsFromSLforFoods(foodCategory);
    
    String rawFoodItems = "";
    for (String fooditem : snsTrends.getName()) {
      rawFoodItems = rawFoodItems + fooditem + ",";
    }
    rawFoodItems = rawFoodItems.substring(0, rawFoodItems.length() - 1);
    LOG.debug("RawFooditems for Category: " + rawFoodItems);
    FooditemsToNutrientMapper foodItemsToNut = new FooditemsToNutrientMapper();
    mapFooditemsToNutrient = foodItemsToNut.FooditemtoNutrientMapper();
    String processedList = "";
    String rawFoodArr[] = rawFoodItems.split(",");
    String item = "";
    for (String fooditem : rawFoodArr) {
      item = mapFooditemsToNutrient.containsKey(fooditem) ? mapFooditemsToNutrient.get(fooditem) : mapFooditemsToNutrient.get(null);
      if (item != null)
        if (item.equals(nutrientLabel))
          processedList = processedList + fooditem + ",";
      }
    processedList = processedList.substring(0, processedList.length() - 1);
    LOG.debug("Processedlist for " + nutrientLabel + ": " + processedList);
    // Fifth filter out those fooditems which are on the user's dislike list
    String finalList = "";
    String processedfood_Arr[] = processedList.split(",");
    UserChoiceMapper userCh = new UserChoiceMapper();
    mapUserChoices = userCh.UserChoicesMapper();    
    String userDislikes = mapUserChoices.containsKey(userid) ? mapUserChoices.get(userid) : mapUserChoices.get("default");
    if (userDislikes.equals("default")) {
      LOG.debug("Preference NOT FOUND in the repository");
      userDislikes = mapUserChoices.get("default");    //?
    }
    String foodDislike[] = userDislikes.split(",");
    LOG.debug("User dislike list : " + userDislikes);
    for (String fooditem : processedfood_Arr) {
      if (!userDislikes.contains(fooditem))
        finalList = finalList + fooditem + ",";
    }
    finalList = finalList.substring(0, finalList.length() - 1);
    String processedFoodItems[] = finalList.split(",");
    finalList = "";
    int index = 0;
    String trendValues = "";
    for (String fooditem : processedFoodItems) {
      if (snsTrends.getName().contains(fooditem)) {
        index = snsTrends.getName().indexOf(fooditem);
        trendValues = snsTrends.getTotal().get((index));
        LOG.debug("Fooditem =" + fooditem + " TrendValue ="+ trendValues);
        finalList = finalList + fooditem + ","+ trendValues + ",";
      }
    }
    finalList = finalList.substring(0, finalList.length() - 1);
    LOG.debug("final list" + finalList);
    return (finalList);
  }
}
