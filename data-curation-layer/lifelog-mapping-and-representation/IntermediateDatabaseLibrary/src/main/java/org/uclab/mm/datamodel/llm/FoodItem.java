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
public class FoodItem {
    
    private String FoodID;
    private String FoodCategory;
    private String FoodNutrientName;
    private String FoodName;

    /**
     * @return the FoodID
     */
    public String getFoodID() {
        return FoodID;
    }

    /**
     * @param FoodID the FoodID to set
     */
    public void setFoodID(String FoodID) {
        this.FoodID = FoodID;
    }

    /**
     * @return the FoodCategory
     */
    public String getFoodCategory() {
        return FoodCategory;
    }

    /**
     * @param FoodCategory the FoodCategory to set
     */
    public void setFoodCategory(String FoodCategory) {
        this.FoodCategory = FoodCategory;
    }

    /**
     * @return the FoodNutrientName
     */
    public String getFoodNutrientName() {
        return FoodNutrientName;
    }

    /**
     * @param FoodNutrientName the FoodNutrientName to set
     */
    public void setFoodNutrientName(String FoodNutrientName) {
        this.FoodNutrientName = FoodNutrientName;
    }

    /**
     * @return the FoodName
     */
    public String getFoodName() {
        return FoodName;
    }

    /**
     * @param FoodName the FoodName to set
     */
    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }
    
    
}
