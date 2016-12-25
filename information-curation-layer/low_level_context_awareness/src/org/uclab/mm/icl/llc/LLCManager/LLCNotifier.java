/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.LLCManager;

import java.util.ArrayList;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.restservices.RestClients;
/*
Copyright [2016] [Dong Uk, Kang]

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
/**
 * Notifier class to DCL
 * @author Nailbrainz
 */
public class LLCNotifier {
	
	
	public static final String noActivity = "NoActivity";
	public static final String noEmotion = "NoEmotion";
	public static final String noLocation = "NoLocation";
	public static final String noFood = "NoFood";
	public static final String noHLC = "NoHLC";
	public static final String noCat = "NoFoodCate";
	
	ArrayList<String> prevContext = new ArrayList<String>();
	
	public LLCNotifier(){
		for(int i = 0; i<ContextType.values().length; i++){
			prevContext.add(" ");
		}
	}
	
	
	
	/**
	 * Notify activity context to DCL
	 * @param act activity to notify
	 */
	public void Notify(ContextLabel llc, ContextType ct){
		String prevLabel = prevContext.get(ct.getValue());
		System.err.println(prevLabel + " VS " + llc.getLabel());
		if(!prevLabel.equals(llc.getLabel())){
			try {
				//RestClients.sendDemo(""+act.getUserID(), act.getLabel(), noEmotion, noLocation, noFood, noHLC, noCat, act.getTimeStamp());
				RestClients.notifyChange(llc.getLabel(), llc.getUserID(), llc.getTimeStamp());
				prevContext.set(ct.getValue(), llc.getLabel());
				//ct.notifyLLCToDCL(llc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
