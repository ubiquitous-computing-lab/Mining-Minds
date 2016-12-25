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
package mm.icl.hlc.HLCReasoner;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mm.icl.hlc.OntologyTools.HLCA;
import mm.icl.hlc.OntologyTools.InferredContextOntology;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
/**
 * Context Classifier: Subcomponent of the HLC Reasoner which performs the
 * classification or identification of the context type to which the
 * unclassified PhysicalActivity and Nutrition Context instance belongs.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextClassifier {
	/**
	 * Inferred version of the Context Ontology which represents the Mining
	 * Minds Context Model.
	 */
	private InferredContextOntology ont;
	/**
	 * Constructor of a new Context Classifier.
	 * 
	 * @param ont
	 *            Inferred version of the Context Ontology which represents the
	 *            Mining Minds Context Model.
	 */
	public ContextClassifier(InferredContextOntology ont) {
		this.ont = ont;
	}
	/**
	 * Method to classify a PhysicalActivity Context instance, i.e., to identify the
	 * context type to which the unclassified PhysicalActivity Context instance
	 * belongs.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified PhysicalActivity Context instance.
	 * @return Classified PhysicalActivity Context instance.
	 */
	public PhysicalActivityContext classify(PhysicalActivityContext unclassifiedHlc) {
		PhysicalActivityContext classifiedHlc = new PhysicalActivityContext(unclassifiedHlc.getCtxModel(), ont);
		if (!classifiedHlc.isClassified()) {
			OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
					unclassifiedHlc.getCtxModel());
			hlcInst.addSubModel(ont.getCtxModel());
			((PelletInfGraph) hlcInst.getGraph()).classify();
			OntClass c = hlcInst.getIndividual(unclassifiedHlc.getCtxInstanceName()).listOntClasses(true).next();
			if (!c.getURI().equals(HLCA.pacClassName))    
				classifiedHlc.setHlcClass(c);
			((PelletInfGraph) hlcInst.getGraph()).close(false);
		}
		return classifiedHlc;
	}
	/**
	 * Method to classify a Nutrition Context instance, i.e., to identify the
	 * context type to which the unclassified Nutrition Context instance
	 * belongs.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified Nutrition Context instance.
	 * @return Classified Nutrition Context instance.
	 */
	public NutritionContext classify (NutritionContext unclassifiedNutHlc) {
		NutritionContext classifiedNutHlc = new NutritionContext (unclassifiedNutHlc.getCtxModel(), ont);
		if (!classifiedNutHlc.isClassified()) {
			OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
					unclassifiedNutHlc.getCtxModel());
			hlcInst.addSubModel(ont.getCtxModel());
			((PelletInfGraph) hlcInst.getGraph()).classify();
			OntClass c = hlcInst.getIndividual(unclassifiedNutHlc.getCtxInstanceName()).listOntClasses(true).next();
			if (!c.getURI().equals(HLCA.nutrClassName))     
				classifiedNutHlc.setHlcClass(c);
			((PelletInfGraph) hlcInst.getGraph()).close(false);
		}
		return classifiedNutHlc;
	}
}
