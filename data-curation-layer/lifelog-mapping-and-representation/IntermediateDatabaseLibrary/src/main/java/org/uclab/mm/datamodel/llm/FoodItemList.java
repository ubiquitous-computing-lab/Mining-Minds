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
package org.uclab.mm.datamodel.llm;

/**
 *
 * @author Rizvi
 */

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.uclab.mm.datamodel.llm.dataadapter.DBConnection;


public class FoodItemList implements Serializable {
     private List<FoodItem> listFood = new ArrayList<FoodItem>();
     public List<FoodItem> getFoods() {
        Connection conn = null;
        Statement sta = null;
        try {
            conn = DBConnection.getDBConnection().getConnection();
            sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select FoodID, FoodCategory, FoodNutrientName, FoodItem from tblFoodItems");
            if (rs != null) {
                // String[] result = new String[7];
                while (rs.next()) {
                    String FoodID = rs.getString("FoodID");
                    String FoodCategory = rs.getString("FoodCategory");
                    String FoodNutrientName = rs.getString("FoodNutrientName");
                    String FoodName = rs.getString("FoodItem");
                    //String LogID = rs.getString("Log_Id");
                    FoodItem objFoodItem = new FoodItem();
                    objFoodItem.setFoodID(FoodID);
                    objFoodItem.setFoodCategory(FoodCategory);
                    objFoodItem.setFoodNutrientName(FoodNutrientName);
                    objFoodItem.setFoodName(FoodName);
                    //objViolations.setUserID(UserID);
                    listFood.add(objFoodItem);
                }
            }
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
        }
        return listFood;
    }
    
    
}
