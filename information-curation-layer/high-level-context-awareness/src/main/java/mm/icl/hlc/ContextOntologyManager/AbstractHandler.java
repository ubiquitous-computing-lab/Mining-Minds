/**
* 
* Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.icl.hlc.ContextOntologyManager;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.LowLevelContext;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
/**
 * Abstract Handler contains all Function required by. Context Handler  
 * for Certain other Context for many Ontologies. It provides 
 * Most Common methods for re-use purpose by implementing Pattern
 * 
 * @author Muhammad Asif Razzaq
 * @version 2.5
 * @since 2016-10-28
 */
public abstract class AbstractHandler {
	public abstract void initialize(String directory, ContextOntology ont);
	public abstract PhysicalActivityContext retrievePreviousHlcAndStoreNew(PhysicalActivityContext newHlc);
	public abstract NutritionContext retrievePreviousHlcAndStoreNew(NutritionContext newHlc);
	public abstract  void storeNewLlc(LowLevelContext newLlc);
	public abstract void finalizePreviousLlc(OntClass category, Resource user, Literal time);
}
