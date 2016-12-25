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
package org.uclab.mm.datamodel.dc.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.dc.PhysiologicalFactors;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.dc.dataadapter.PhysiologicalFactorsAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserDataAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of PhysiologicalFactorsAdapter 
 * @author Taqdir Ali
 *
 */

public class PhysiologicalFactorsAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of PhysiologicalFactorsAdapter 
	 */
	@Test
	public void testCRUDPhysiologicalFactors() {
				
		/* Preparing input object for insertion*/
		PhysiologicalFactors objOuterPhysiologicalFactors = new PhysiologicalFactors();
		PhysiologicalFactors objInnerPhysiologicalFactors = new PhysiologicalFactors();
		List<PhysiologicalFactors> objListPhysiologicalFactors = new  ArrayList<PhysiologicalFactors>();
		
		objOuterPhysiologicalFactors.setUserId(2L);
		objOuterPhysiologicalFactors.setWeight((float) 65.3);
		objOuterPhysiologicalFactors.setHeight((float) 6.1);
		objOuterPhysiologicalFactors.setDate("2014 05 02 00:00:00");
		objOuterPhysiologicalFactors.setIdealWeight((float) 60.5);
		objOuterPhysiologicalFactors.setTargetWeight((float) 61.9);
		
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new PhysiologicalFactorsAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SavePhysiologicalFactors(objOuterPhysiologicalFactors);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerPhysiologicalFactors.setUserId(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new PhysiologicalFactorsAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListPhysiologicalFactors = objADBridgeRetrieval.RetrivePhysiologicalFactors(objInnerPhysiologicalFactors);
        	 objInnerPhysiologicalFactors = objListPhysiologicalFactors.get(0);
         }
         
         assertEquals(0, Double.compare(objOuterPhysiologicalFactors.getWeight(), objInnerPhysiologicalFactors.getWeight()));
         
         /* Testing of Update function*/
         objInnerPhysiologicalFactors.setWeight((float) 64.8);
         DataAccessInterface objDAInterfaceUpdate = new PhysiologicalFactorsAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdatePhysiologicalFactors(objInnerPhysiologicalFactors);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerPhysiologicalFactors.setUserId(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new PhysiologicalFactorsAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListPhysiologicalFactors = objADBridgeRetrieval.RetrivePhysiologicalFactors(objInnerPhysiologicalFactors);
        	 objInnerPhysiologicalFactors = objListPhysiologicalFactors.get(0);
         }
         
         assertEquals(0, Double.compare(objInnerPhysiologicalFactors.getWeight(), (float) 64.8));
		
	}

}
