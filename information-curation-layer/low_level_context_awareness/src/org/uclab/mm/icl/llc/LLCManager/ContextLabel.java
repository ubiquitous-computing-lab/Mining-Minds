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
package org.uclab.mm.icl.llc.LLCManager;

import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.RecognizerType;


/**
 * Unique instantiable data class for the low level contexts
 * @author Nailbrainz
 * 
 */

public class ContextLabel {
  
   /** 
    * @return representation of your dataType as String
    */
   @Override
    public String toString(){
	   return Label;
   };
    String timeStamp;
    String Label;
    long userID;
    ContextType ct;
    
    /**
     * 
     * @param userID id of user 
     * @param Label label of the low level context
     * @param timeStamp timestamp in MM format
     * @param ct contextType of the ContextLabel
     */
    public ContextLabel(long userID, String Label, String timeStamp, ContextType ct){
    	this.userID = userID;
    	this.Label = Label;
    	this.timeStamp = timeStamp;
    }
    
    
    /**
     * 
     * @return timestamp of corresponding context, in MM format
     */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * forcefully sets the timestamp to target context 
	 * @param timeStamp timestamp to set. must be in MM format
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * This function returns the label stored in the low level context
	 * @return label of the context
	 */
	public String getLabel() {
		return Label;
	}
	
	
	/**
	 * forcefully sets the label to target context 
	 * @param label label to set
	 */
	public void setLabel(String label) {
		Label = label;
	}
	
	/**
	 * This function returns the user id stored in the low level context
	 * @return user id of corresponding context
	 */
	public long getUserID() {
		return userID;
	}
	
	
	/**
	 * forcefully sets the id to target context 
	 * @param userID user id to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

}
