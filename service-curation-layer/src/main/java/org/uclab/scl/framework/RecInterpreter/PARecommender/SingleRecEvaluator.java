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

package org.uclab.scl.framework.RecInterpreter.PARecommender;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.datamodel.contextMapper.RecommendationMapper;
import org.uclab.scl.framework.RecInterpreter.AbstractRecEvaluator;

public class SingleRecEvaluator extends AbstractRecEvaluator {
  private static Logger LOG = LogManager.getRootLogger();
  
	static HashMap<String, String> mapWeatherRec = new HashMap<>();
	static HashMap<String, String> mapDisableRec = new HashMap<>();
	static HashMap<String, String> mapRec = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static int[] evalRecommendations(String rec, String userid, String context) throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	
	public int[] evaluate(String rec, String userid, String context) {
		RecommendationMapper recommendationMapper = new RecommendationMapper();
		// String context_string = "Office,OfficeWork,Rainy,Neutral,No";
		LOG.debug("Context String: " + context);
		try {
			mapRec = recommendationMapper.recMapper();
		} catch (FileNotFoundException e) {
		//	LOG.debug(e.printStackTrace);
		}
		int[] matResultVector = null;
		try {
			matResultVector = ContextEvaluator.generateContextMatrix(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Reading data from HashMap
		String temp = mapRec.containsKey(rec) ? mapRec.get(rec)	: mapRec.get(null);
		int index = 0;
		if (temp != null)
			index = Integer.parseInt(temp);
		else {
		  LOG.debug("Recommendation NOT FOUND in the repository");
		  LOG.debug("Default case applied");
			index = Integer.parseInt(mapRec.get("D"));
		}
		// Data Retrieval complete
		int sumArrVec = 0;
		int sumPrefVec = 0;
		for (int i = 0; i < matResultVector.length; i++) {
			sumArrVec += matResultVector[i];
		}

		int[] tempFinalRec = new int[matResultVector.length];
		java.util.Arrays.fill(tempFinalRec, 0);
		if (matResultVector[index] == 0) {
			LOG.debug("Alternative Rec needed for " + rec);
			if (sumArrVec > 1) { // PREFERENCE CHKING
				//LOG.debug("okay now we have multiple alternative rec; now Preference check needed");
				int[] ARPref = null;
				try {
					ARPref = PreferenceTesting.mainPrefTest(matResultVector, userid, context);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print("ARP_List ");
				for (int i = 0; i < ARPref.length; i++) {
					LOG.debug(ARPref[i] + " ");
					sumPrefVec += ARPref[i];
				}

				if (sumPrefVec == 0) {
				//	LOG.debug("Well ARP is 0 because Preference chk made it so, GO BACK TO AR !!!");
					System.arraycopy(matResultVector, 0, tempFinalRec, 0, matResultVector.length); // forward aggregate vector
				} else if ((sumPrefVec > 1)) {
				//	LOG.debug("now we got multiple preference based recommendations, forwarding");
					System.arraycopy(ARPref, 0, tempFinalRec, 0, ARPref.length);
					//for enforcing some other constraints
				} else {
				//	LOG.debug("after Preference chk left with only 1 recommendation, forwarding");
					System.arraycopy(ARPref, 0, tempFinalRec, 0, ARPref.length);
				}
			} else
				LOG.debug("we have only one alternative rec, so forward that to Explanation Generator");
		}
		// Alternative Rec code here
		else {
			LOG.debug("Recommendation is Fine as per the current context; Forward to Explanation Generator");
			// Forward code to explanation generator
			java.util.Arrays.fill(tempFinalRec, 0);
			tempFinalRec[index] = 1;
			//System.arraycopy(matResultVector, 0, tempFinalRec, 0,	matResultVector.length);
		}
		// Finalize Rec Processing
		int[] finalRec = new int[mapRec.size() - 1];
		boolean flag = true;
		for (int i = 0; i < tempFinalRec.length; i++) {
			if ((tempFinalRec[i] == 1) && (flag == true)) {
				finalRec[i] = 1;
				flag = false;
			} else
				finalRec[i] = 0;
		}
		return finalRec;
	}
}
