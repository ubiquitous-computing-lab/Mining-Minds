/*
Copyright [2016] [Sang Beom, Park]

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
package org.uclab.mm.icl;

import java.util.Vector;

public class FoodCategorizer {
	String[] foodCate = { "Grain", "Meat", "SeaFood", "Eggs", "MilkyAndDairyProducts", "Legumes", "Nuts", "Fruits",
			"Vegetable", "Snacks" };
	String Grain[] = { "Rice", "Noodle", "Oats", "Barley", "Quinoa", "Brown", "Rye", "Bread" };
	String Meat[] = { "Beef", "Pork", "Chicken" };
	String SeaFood[] = { "Flounder", "PacificSaury", "Daegu", "Shrimp", "Salmon", "Early", "Tuna", "Mackerel", "Head",
			"FishEggs", "SeaWeed" };
	String Eggs[] = { "Egg", "QuailEggs" };
	String MilkyAndDairyProducts[] = { "Milk", "Yogurt", "LowFatCheese", "LowFatMilk" };
	String Legumes[] = { "Beans", "Tofu", "SoyMilk" };
	String Nuts[] = { "Peanut" };
	String Fruits[] = { "Apple", "Kiwi", "Peach", "Apricot", "Melon", "GrapeFruit", "Lemon", "Avocado", "Orange",
			"Banana", "WaterMelon", "Grape" };
	String Vegeable[] = { "Spinach", "Carrot", "Pumpkin", "Cucumber" };
	String Snack[] = { "FriedFood", "Ham", "Snack", "Pizza", "Ramen", "CarbonatedDrink", "Hamburger", "ChickenSnack" };
	Vector<String[]> foodList = new Vector<String[]>();

	public FoodCategorizer() {
		foodList.add(Grain);
		foodList.add(Meat);
		foodList.add(SeaFood);
		foodList.add(Eggs);
		foodList.add(MilkyAndDairyProducts);
		foodList.add(Legumes);
		foodList.add(Nuts);
		foodList.add(Fruits);
		foodList.add(Vegeable);
		foodList.add(Snack);
	}

	public String recognizeFoodItem(String food) {
		String result = "";
		int saveIndex = 0;
		for (int i = 0; i < foodList.size(); i++) {
			for (int k = 0; k < foodList.get(i).length; k++) {
				if (foodList.get(i)[k].equals(food)) {
					saveIndex = i;
				}
			}
		}

		return foodMapping(saveIndex);
	}

	private String foodMapping(int index) {
		return foodCate[index];
	}

}
