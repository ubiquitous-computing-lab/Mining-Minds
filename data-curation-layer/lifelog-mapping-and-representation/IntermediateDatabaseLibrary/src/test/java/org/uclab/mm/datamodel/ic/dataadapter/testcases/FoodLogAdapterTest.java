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
package org.uclab.mm.datamodel.ic.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.dataadapter.FoodLogAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of FoodLogAdapter
 * @author Taqdir Ali
 *
 */
public class FoodLogAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of FoodLogAdapter 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		FoodLog objOuterFoodLog = new FoodLog();
		FoodLog objInnerFoodLog = new FoodLog();
		List<FoodLog> objListFoodLog = new  ArrayList<FoodLog>();
		
		objOuterFoodLog.setUserId(2L);
		objOuterFoodLog.setFoodName("Haleem");
		objOuterFoodLog.setEatingTime("2016 12 12 02:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new FoodLogAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveFoodLog(objOuterFoodLog);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerFoodLog.setUserId(2L);
        	 objInnerFoodLog.setRequestType("LatestByUser");
        	 DataAccessInterface objDAInterfaceRetrieval = new FoodLogAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListFoodLog = objADBridgeRetrieval.RetriveFoodLog(objInnerFoodLog);
        	 objInnerFoodLog = objListFoodLog.get(0);
         }
         
         assertEquals(objOuterFoodLog.getFoodName(), objInnerFoodLog.getFoodName());
	}

}
