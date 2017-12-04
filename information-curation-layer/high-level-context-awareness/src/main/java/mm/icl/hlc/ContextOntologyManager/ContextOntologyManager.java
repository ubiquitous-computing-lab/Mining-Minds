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
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.InferredContextOntology;
/**
 * Context Ontology Manager : Component of HLCA which manages the interactions
 * with the Context Ontology Storage where Context Ontology and Context
 * Instances are persisted.
 *
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextOntologyManager {

	private static Logger logger = Logger.getLogger(ContextOntologyManager.class);
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;
	/**
	 * Inferred version of the Context Ontology which represents the Mining
	 * Minds Context Model.
	 */
	private InferredContextOntology infOnt;
	/**
	 * Constructor of a new Context Ontology Manager.
	 * 
	 * @param directory
	 *            Path to the directory where the Context Ontology Storage is
	 *            deployed.
	 */
	private ContextHandler contextHandler = ContextHandler.getContextHandler();
	public ContextOntologyManager(String directory) {
		OntologyModelManager.initialize(directory);
		OntModel ontModel = OntologyModelManager.retrieveContextOntology();
		try{
		if (ontModel != null) {
			OntModel infOntModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, ontModel);
			this.ont = new ContextOntology(ontModel);
			this.infOnt = new InferredContextOntology(infOntModel);
			contextHandler.initialize(directory, this.ont);
		}
		}
		catch (Exception e) {
			logger.error("Error while initializing Context Ontology.  Message: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	/**
	 * Constructor of a new Context Ontology Manager.
	 * 
	 * @param directory
	 *            Path to the directory where the Context Ontology Storage is
	 *            deployed.
	 * @param ontModel
	 *            OntModel representation of the Context Ontology.
	 */
	public ContextOntologyManager(String directory, OntModel ontModel) {
			this(directory);

	try{		
		if (this.ont == null) { 
			OntModel infOntModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, ontModel);
			ContextOntology ont = new ContextOntology(ontModel);
			InferredContextOntology infOnt = new InferredContextOntology(infOntModel);
			if (infOnt.isValid()) {
				OntologyModelManager.loadContextOntology(ont);
				this.ont = ont;
				this.infOnt = infOnt;
				contextHandler.initialize(directory, this.ont);
			}
		}
	}
	catch (Exception e) {
							logger.error("There is a Context Model stored in DB. Ignoring the provided OntModel and "
									+ "using the available one to initialize the Context Ontology Manager." + e.getMessage());
							e.printStackTrace();
						}
	}
	
	/**
	 * Method to inquire whether the Context Ontology Manager has been correctly
	 * initialized.
	 * 
	 * @return true if the Context Ontology Manager has been correctly
	 *         initialized, and false otherwise.
	 */
	public boolean correctInitialization() {
		return (this.ont != null && this.infOnt != null);
	}
	/**
	 * Method to retrieve the Context Ontology which has been persisted in the
	 * Context Ontology Storage.
	 * 
	 * @return Context Ontology which was persisted in the Context Ontology
	 *         Storage.
	 */
	public ContextOntology getContextOntology() {
		return ont;
	}
	/**
	 * Method to retrieve the inferred version of the Context Ontology which has
	 * been persistedd in the Context Ontology Storage.
	 * 
	 * @return Inferred version of the Context Ontology which was persisted in
	 *         the Context Ontology Storage.
	 */
	public InferredContextOntology getInferredContextOntology() {
		return infOnt;
	}
}
