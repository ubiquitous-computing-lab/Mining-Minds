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
package org.uclab.mm.kcl.ddkat.productionmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//TODO: Auto-generated Javadoc
/**
* This class defines the model/structure of the rules.
*/
public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** The rule id. */
	private int RuleID;
	
	/** The conditions list. */	
    private List<Condition> conditionList = new ArrayList<Condition>();
	
    /** The conclusion list. */
    private List<Conclusion> conclusionList = new ArrayList<Conclusion>();

	public Rule(){}
	
	public int getRuleID(){
    	return RuleID;
    }
	
	public void setRuleID(int ruleID) {
		RuleID = ruleID;
	} 

	public List<Condition> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}
	
	public List<Conclusion> getConclusionList() {
		return conclusionList;
	}

	public void setConclusionList(List<Conclusion> conclusionList) {
		this.conclusionList = conclusionList;
	}
		
}
