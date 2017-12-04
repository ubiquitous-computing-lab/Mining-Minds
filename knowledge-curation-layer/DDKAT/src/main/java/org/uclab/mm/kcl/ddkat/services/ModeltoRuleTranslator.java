/**
 * Copyright [2017] [Maqbool Ali, Maqbool Hussain]
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
package org.uclab.mm.kcl.ddkat.services;

import java.util.ArrayList;
import java.util.List;

import org.uclab.mm.kcl.ddkat.modeltranslator.DTtoRuleTranslator;
import org.uclab.mm.kcl.ddkat.productionmodel.Rule;

//TODO: Auto-generated Javadoc
/**
* This class defines the service functionalities of model translation.
*/
public class ModeltoRuleTranslator implements IClassificationModelTranslator {
	
	private DTtoRuleTranslator ruleTranslator = new DTtoRuleTranslator();
	
	public void setRuleTranslator(DTtoRuleTranslator ruleTranslator) {
		this.ruleTranslator = ruleTranslator;
	}
	
	/**
	 * Method to translate the classification model.
	 *
	 * @return listRule the list of rules
	 */
	public List<Rule> translateModel() {
		
		List<Rule> listRule = new ArrayList<Rule>();
		try{
			listRule = ruleTranslator.translateModel();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return listRule;
	}
	

}
