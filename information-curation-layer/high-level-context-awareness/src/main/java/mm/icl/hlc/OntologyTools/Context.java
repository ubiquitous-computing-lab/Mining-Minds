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
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
/**
 * Context is the class encapsulating the OntModel associated to a context
 * instance. This is an instance of the Context class in the Context Ontology
 * which represents the Mining Minds Context Model. This class provides
 * supporting methods to facilitate the access to the Context. .
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public abstract class Context implements IContext{
	/**
	 * OntModel associated to a context instance in Context Ontology.
	 */
	protected OntModel ctxModel;
	/**
	 * Name of the Context Instance.
	 */
	protected String ctxInstanceName;
	/**
	 * Name of the type of context to which the instance belongs to as described
	 * in the Context Ontology. E.g., Sitting or Standing for the category
	 * Activity of Low Level Context, and OfficeWork or Amusement for the 
	 * PhysicalActivity and Nutrition Context.
	 */
	protected String ctxTypeName;
	/**
	 * Constructor of a new Context instance.
	 * 
	 * @param ctxModel
	 *            OntModel associated to the Context instance.
	 */
	public Context(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}
	/**
	 * Method to get the OntModel associated to the Context Instance.
	 * 
	 * @return OntModel representation of the Context Instance.
	 */
	public OntModel getCtxModel() {
		return ctxModel;
	}
	/**
	 * Method to set the OntModel associated to the Context Instance.
	 * 
	 * @param ctxModel
	 *            OntModel representation of the Context Instance.
	 */
	public void setCtxModel(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}
	/**
	 * Method to get the name of the Context Instance.
	 * 
	 * @return String representing the name of the Context Instance.
	 */
	public String getCtxInstanceName() {
		return ctxInstanceName;
	}
	/**
	 * Method to set the name of the Context Instance.
	 * 
	 * @param ctxInstaceName
	 *            String representing the name of the Context Instance.
	 */
	protected void setCtxInstanceName(String ctxInstaceName) {
		this.ctxInstanceName = ctxInstaceName;
	}
	/**
	 * Method to get the type of the Context Instance, e.g., Sitting or Standing
	 * for the category Activity of Low Level Context, and OfficeWork or
	 * Amusement for the PhysicalActivity and Nutrition Context.
	 * 
	 * @return String representing the type of the Context Instance.
	 */
	public String getCtxTypeName() {
		return ctxTypeName;
	}
	/**
	 * Method to set the type of the Context Instance, e.g., Sitting or Standing
	 * for the category Activity of Low Level Context, and OfficeWork or
	 * Amusement for the PhysicalActivity and Nutrition Context.
	 * 
	 * @param ctxType
	 *            String representing the type of the Context Instance.
	 */
	protected void setCtxTypeName(String ctxType) {
		this.ctxTypeName = ctxType;
	}
	/**
	 * Method to get the local name of the Context Instance. The local name
	 * refers to the name without the ontology namespace.
	 * 
	 * @return String representing the local name of the Context Instance.
	 */
	public String getCtxInstanceLocalName() {
		String str = getCtxInstanceName();
		return str.substring(str.indexOf("#") + 1);
	}
	/**
	 * Method to get the local name of the type of the Context Instance, e.g.,
	 * Sitting or Standing for the category Activity of Low Level Context, and
	 * OfficeWork or Amusement for the PhysicalActivity and Nutrition Context. The local name refers
	 * to the name without the ontology namespace.
	 * 
	 * @return String representing the local name of the type of the Context
	 *         Instance.
	 */
	public String getCtxTypeLocalName() {
		String str = getCtxTypeName();
		return str.substring(str.indexOf("#") + 1);
	}
	/**
	 * Method to get the Individual representing the Context Instance.
	 * 
	 * @return Individual representing the Context Instance.
	 */
	public Individual getCtxIndiv() {
		return getCtxModel().getIndividual(getCtxInstanceName());
	}
	/**
	 * Method to assert the value of a data property for the Context Instance.
	 * 
	 * @param p
	 *            The data property for which the value should be asserted.
	 * @param value
	 *            Literal value of the property to be asserted.
	 */
	public void setDataPropertyValue(Property p, Literal value) {
		getCtxIndiv().setPropertyValue(p, value);
	}
	/**
	 * Method to get the value of a data property for the Context Instance.
	 * 
	 * @param p
	 *            The data property for which the value is retrieved.
	 * @return Literal value of the property.
	 */
	public Literal getDataPropertyValue(Property p) {
		RDFNode n = getCtxIndiv().getPropertyValue(p);
		if (n == null)
			return null;
		return n.asLiteral();
	}
	/**
	 * Method to get the value of an object property for the Context Instance.
	 * 
	 * @param p
	 *            The object property for which the value is retrieved.
	 * @return Resource representation of the value of the property.
	 */
	public Resource getObjectPropertyValue(Property p) {
		RDFNode n = getCtxIndiv().getPropertyValue(p);
		if (n == null)
			return null;
		return n.asResource();
	}
}
