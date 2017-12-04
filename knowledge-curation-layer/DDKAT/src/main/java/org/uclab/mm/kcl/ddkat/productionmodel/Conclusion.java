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
* This class defines the model/structure of the conclusions.
*/
public class Conclusion implements Serializable{

	private static final long serialVersionUID = 1L;

	/** The conclusion id. */
	private int ConclusionID;
	
	/** The conclusion key. */
	private String conclusionKey;
	
	/** The conclusion value. */
	private String conclusionValue;
	
	/** The conclusion operator. */
	private String conclusionOperator;
	
	public Conclusion()  
	 {  
	 }    

	public int getConclusionID() {
		return ConclusionID;
	}

	public void setConclusionID(int conclusionID) {
		ConclusionID = conclusionID;
	}	

	public String getConclusionKey() {
		return conclusionKey;
	}

	public void setConclusionKey(String conclusionKey) {
		this.conclusionKey = conclusionKey;
	}

	public String getConclusionValue() {
		return conclusionValue;
	}

	public void setConclusionValue(String conclusionValue) {
		this.conclusionValue = conclusionValue;
	}

	public String getConclusionOperator() {
		return conclusionOperator;
	}

	public void setConclusionOperator(String conclusionOperator) {
		this.conclusionOperator = conclusionOperator;
	}
	
}

