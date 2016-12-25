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
package mm.icl.hlc.OntologyTools;
import java.util.Iterator;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.syntax.ElementUnion;
/**
 * ContextOntology is the class encapsulating the OntModel associated to the
 * Context Ontology which represents the Mining Minds Context Model. This class
 * provides supporting methods to facilitate the access to the Context Ontology.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextOntology extends AbstractOntology{
	/**
	 * OntModel associated to the Context Ontology which represents the Mining
	 * Minds Context Model.
	 */
	protected OntModel ctxModel;
	/**
	 * Constructor of a new Context Ontology
	 * 
	 * @param ctxModel
	 *            OntModel associated to the Context Ontology.
	 */
	public ContextOntology(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}
	/**
	 * Method to get the OntModel associated to the Context Ontology which
	 * represents the Mining Minds Context Model.
	 * 
	 * @return OntModel representation of the Context Ontology.
	 */
	public OntModel getCtxModel() {
		return ctxModel;
	}
	/**
	 * Method to set the OntModel associated to the Context Ontology which
	 * represents the Mining Minds Context Model.
	 * 
	 * @param ctxModel
	 *            OntModel representation of the Context Ontology.
	 */
	public void setCtxModel(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}
	/**
	 * Method to get the User class in the Context Ontology.
	 * 
	 * @return OntClass representing the User in the Context Ontology.
	 */
	public OntClass getUserClass() {
		return ctxModel.getOntClass(HLCA.userClassName);
	}
	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to the User it belongs to.
	 * 
	 * @return ObjectProperty linking a Context to the User it belongs to.
	 */
	public ObjectProperty getContextOfProp() {
		return ctxModel.getObjectProperty(HLCA.isCtxOfPropName);
	}
	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to its start time.
	 * 
	 * @return DatatypeProperty linking a Context to its start time.
	 */
	public DatatypeProperty getStartTimeProp() {
		return ctxModel.getDatatypeProperty(HLCA.hasStartTimePropName);
	}
	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to its end time.
	 * 
	 * @return DatatypeProperty linking a Context to its end time.
	 */
	public DatatypeProperty getEndTimeProp() {
		return ctxModel.getDatatypeProperty(HLCA.hasEndTimePropName);
	}
	/**
	 * Method to get the PhysicalActivity Context class in the Context Ontology.
	 * 
	 * @return OntClass representing PhysicalActivity Context in the Context Ontology.
	 */
	public OntClass getHlcClass() {

		return ctxModel.getOntClass(HLCA.pacClassName);
	}
	/**
	 * Method to get the Nutrition Context class in the Context Ontology.
	 * 
	 * @return OntClass representing Nutrition Context in the Context Ontology.
	 */
	public OntClass getNutHlcClass() {
		return ctxModel.getOntClass(HLCA.nutrClassName); 
	}
	/**
	 * Method to get the Low Level Context class in the Context Ontology.
	 * 
	 * @return OntClass representing Low Level Context in the Context Ontology.
	 */
	public OntClass getLlcClass() {
		return ctxModel.getOntClass(HLCA.llcClassName);    
	}
	/**
	 * Method to get the property in the Context Ontology which links a 
	 * PhysicalActivity and Nutrition Context to the Activity which is part of it.
	 * 
	 * @return ObjectProperty linking a PhysicalActivity Context to the Activity which
	 *         is part of it.
	 */
	public ObjectProperty getActivityProp() {
		return ctxModel.getObjectProperty(HLCA.hasActivityPropName);
	}
	/**
	 * Method to get the property in the Context Ontology which links a 
	 * PhysicalActivity and Nutrition Context to the Location which is part of it.
	 * 
	 * @return ObjectProperty linking a PhysicalActivity Context to the Location which
	 *         is part of it.
	 */
	public ObjectProperty getLocationProp() {
		return ctxModel.getObjectProperty(HLCA.hasLocationPropName);
	}
	/**
	 * Method to get the property in the Context Ontology which links a 
	 * PhysicalActivity and Nutrition to the Emotion which is part of it.
	 * 
	 * @return ObjectProperty linking a PhysicalActivity Context to the Emotion which
	 *         is part of it.
	 */
	public ObjectProperty getEmotionProp() {
		return ctxModel.getObjectProperty(HLCA.hasEmotionPropName);
	}
	/**   Asif
	 * Method to get the property in the Context Ontology which links a 
	 * PhysicalActivity and Nutrition Context to the Food which is part of it.
	 * 
	 * @return ObjectProperty linking a Nutrition Context to the Food which
	 *         is part of it.
	 */
	public ObjectProperty getFoodProp() {
		return ctxModel.getObjectProperty(HLCA.hasFoodPropName);
	}
	/**
	 * Method to get the subclass of Low Level Context of a given type, e.g.
	 * Sitting or Standing for the category Activity.
	 * 
	 * @param llcTypeName
	 *            Name of the type of Low Level Context.
	 * @return OntClass representing the type llcTypeName in the Context
	 *         Ontology.
	 */
	public OntClass getLlcTypeClass(String llcTypeName) {
		return ctxModel.getOntClass(llcTypeName);
	}
	/**
	 * Method to get the subclass of Low Level Context of a given category, e.g.
	 * Activity, Emotion or Location.
	 * 
	 * @param llcCategoryName
	 *            Name of the category of Low Level Context.
	 * @return OntClass representing the category llcCategoryName in the Context
	 *         Ontology.
	 */
	public OntClass getLlcCategoryClass(String llcCategoryName) {
		return ctxModel.getOntClass(llcCategoryName);
	}
	/**
	 * Method to get the superclass of Low Level Context of a given category, e.g.
	 * Activity, Emotion, food or Location.
	 * 
	 * @param LlcSuperClassCategory
	 *            Name of the category of Low Level Context.
	 * @return OntClass representing the category LlcSuperClassCategory in the Context
	 *         Ontology.
	 */
	public OntClass getLlcSuperClassCategory (OntClass category) {
		OntClass LlcSuperClassCategory = null;
		for (Iterator i = category.listSuperClasses(true); i.hasNext();)  
		{
			LlcSuperClassCategory  = (OntClass) i.next();
				};	
		return LlcSuperClassCategory;
	}
}