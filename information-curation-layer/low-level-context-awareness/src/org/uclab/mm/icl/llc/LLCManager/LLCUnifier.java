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

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.RecognizerType;


/**
 * This abstract class is the parent class of unifiers used in ICL unifying process.
 * @author Nailbrainz
 *
 */
public abstract class LLCUnifier {
	
	long userID;
	ArrayList<AtomicReference<String> > curLabels;
	
	/**
	 * Unique constructor of LLC unifier
	 * @param userID corresponding user ID
	 */
	public LLCUnifier(long userID, ArrayList<AtomicReference<String> > curLabels){
		this.userID = userID;
		this.curLabels = curLabels;
	}
	
	/**
	 * Method to remove all the inner contexts
	 */
	public abstract void clear();
	
	/**
     * Push the llc context into unifier
     * @param obj object(llcContext) to push
     * @param rec corresponding recognizer type
     */
	public abstract void push(Object obj, RecognizerType rec);
	
	
	/**
	 * Unifying function. Exact methodology of unification is dependent on each child class
	 * @param startTime start time of window
	 * @param ct contextType which unification will be happen
	 * @return unified result
	 */
	abstract ContextLabel unify(String startTime, ContextType ct);
	
	
}
