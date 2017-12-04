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

//TODO: Auto-generated Javadoc
/**
* This class defines the model/structure of the conditions.
*/
public class Condition implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** The condition id. */
	private int ConditionID;
	
	/** The condition key. */
	private String conditionKey;
	
	/** The condition value. */
	private String conditionValue;
	
	/** The condition operator. */
	private String conditionValueOperator;

   	public Condition() {}
	 
	 public int getConditionID() {
			return ConditionID;
		}

		public void setConditionID(int conditionID) {
			ConditionID = conditionID;
		}

		public String getConditionKey() {
			return conditionKey;
		}

		public void setConditionKey(String conditionKey) {
			this.conditionKey = conditionKey;
		}

		public String getConditionValue() {
			return conditionValue;
		}

		public void setConditionValue(String conditionValue) {
			this.conditionValue = conditionValue;
		}

		public String getConditionValueOperator() {
			return conditionValueOperator;
		}

		public void setConditionValueOperator(String conditionValueOperator) {
			this.conditionValueOperator = conditionValueOperator;
		}

}

