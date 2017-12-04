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
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.HLCA;
/**
 * Ontology Model Manager: Subcomponent of the Context Ontology Manager which
 * manages the Context Ontology persisted in the Context Ontology Storage.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class OntologyModelManager {
	/**
	 * Collection of Context Instances and Context Ontology stored in the
	 * Context Ontology Storage.
	 */
	private static Dataset dataset;
	/**
	 * Method to initialize the Ontology Model Manager.
	 * 
	 * @param directory
	 *            Path to the directory where the Context Ontology Storage is
	 *            deployed.
	 */
	public static void initialize(String directory) {
		dataset = TDBFactory.createDataset(directory);
	}
	/**
	 * Method to load a Context Ontology, which represents the Mining Minds
	 * Context Model, to the Context Ontology Storage.
	 * 
	 * @param uri
	 *            Namespace of the Context Ontology.
	 * @param model
	 *            OntModel representation of the Context Ontology.
	 */
	private static void loadContextOntology(String uri, OntModel model) {
		dataset.begin(ReadWrite.WRITE);
		// Store context Ontology model to DB
		dataset.addNamedModel(uri, model);
		dataset.commit();
		dataset.end();
	}
	/**
	 * Method to load a Context Ontology to the Context Ontology Storage.
	 *
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public static void loadContextOntology(ContextOntology ont) {
		loadContextOntology(HLCA.ns, ont.getCtxModel());
	}
	/**
	 * Method to retrieve from the Context Ontology Storage the Context Ontology
	 * which represents the Mining Minds Context Model.
	 *
	 * @return OntModel representation of the Context Ontology.
	 */
	public static OntModel retrieveContextOntology() {
		dataset.begin(ReadWrite.READ);
		Model inst = dataset.getNamedModel(HLCA.ns);
		dataset.end();
		if (inst.isEmpty())
			return null;
		return ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
	}
}
