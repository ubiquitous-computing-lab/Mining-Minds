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
import java.util.Iterator;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.StandardValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import mm.icl.hlc.OntologyTools.HLCA;
import mm.icl.hlc.OntologyTools.InferredContextOntology;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
/**
 * Context Verifier: Subcomponent of the HLC Reasoner which performs a
 * consistency check (validation and verification) of the unclassified
 * PhysicalActivity and Nutrition context instance versus the Context Ontology Model.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextVerifier {
	/**
	 * Inferred version of the Context Ontology which represents the Mining
	 * Minds Context Model.
	 */
	private InferredContextOntology ont;
	/**
	 * Constructor of a new Context Verifier.
	 * 
	 * @param ont
	 *            Inferred version of the Context Ontology which represents the
	 *            Mining Minds Context Model.
	 */
	public ContextVerifier(InferredContextOntology ont) {
		this.ont = ont;
	}
	/**
	 * Method to validate and verify an unclassified PhysicalActivity and Nutrition context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified PhysicalActivity Context instance.
	 * @return true if the unclassified PhysicalActivity Context Instance passed the
	 *         consistency check, and false otherwise.
	 */
	public boolean isValid(PhysicalActivityContext unclassifiedHlc) {
		return (validate(unclassifiedHlc).isValid());
	}
	/**
	 * Method to validate and verify an unclassified Nutrition context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified Nutrition Context instance.
	 * @return true if the unclassified Nutrition Context Instance passed the
	 *         consistency check, and false otherwise.
	 */
	public boolean isValid(NutritionContext unclassifiedNutHlc) {
		return (validate(unclassifiedNutHlc).isValid());
	}
	/**
	 * Method to validate and verify an unclassified PhysicalActivity context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified PhysicalActivity Context instance.
	 * @return Validity Report which contains the description of the errors in
	 *         the consistency check of unclassifiedHlc.
	 */
	public ValidityReport validate(PhysicalActivityContext unclassifiedHlc) {
		OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
				unclassifiedHlc.getCtxModel());
		hlcInst.addSubModel(ont.getCtxModel());
		ValidityReport validity = hlcInst.validate();
		if (validity.isValid()) {
			if (validity.isClean()) {
				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.pacClassName)   
						.listInstances(false);
				if (it.hasNext()) {
					OntResource inst = it.next();
					if (!it.hasNext()) {
						unclassifiedHlc.setHlcInstance(inst);
						((PelletInfGraph) hlcInst.getGraph()).close(false);
						return validity;
					} else {
						StandardValidityReport report = new StandardValidityReport();
						report.add(true, "PhysicalActivityContext contains several instances of the class", HLCA.pacClassName); 
						report.add(false, "-", inst.getURI());
						while (it.hasNext())
							report.add(false, "-", it.next().getURI());
						((PelletInfGraph) hlcInst.getGraph()).close(false);
						return report;
					}
				} else {
					((PelletInfGraph) hlcInst.getGraph()).close(false);
					StandardValidityReport report = new StandardValidityReport();
					report.add(true, "PhysicalActivityContext does not contain an instance of the class", HLCA.pacClassName); 
					return report;
				}
			} else {
				((PelletInfGraph) hlcInst.getGraph()).close(false);
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "PhysicalActivityContext contains logical errors", "");
				Iterator<Report> it = validity.getReports();
				while (it.hasNext())
					report.add(it.next());
				return report;
			}
		}
		((PelletInfGraph) hlcInst.getGraph()).close(false);
		return validity;
	}
	/**
	 * Method to validate and verify an unclassified Nutrition context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified Nutrition Context instance.
	 * @return Validity Report which contains the description of the errors in
	 *         the consistency check of unclassifiedHlc.
	 */
	public ValidityReport validate(NutritionContext unclassifiedNutHlc) {
		OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
				unclassifiedNutHlc.getCtxModel());
		hlcInst.addSubModel(ont.getCtxModel());
		ValidityReport validity = hlcInst.validate();
		if (validity.isValid()) {
			if (validity.isClean()) {
				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.nutrClassName)   
						.listInstances(false);
				if (it.hasNext()) {
					OntResource inst = it.next();
					if (!it.hasNext()) {
						unclassifiedNutHlc.setHlcInstance(inst);
						((PelletInfGraph) hlcInst.getGraph()).close(false);
						return validity;
					} else {
						StandardValidityReport report = new StandardValidityReport();
						report.add(true, "NutritionContext contains several instances of the class", HLCA.nutrClassName);
						report.add(false, "-", inst.getURI());
						while (it.hasNext())
							report.add(false, "-", it.next().getURI());
						((PelletInfGraph) hlcInst.getGraph()).close(false);
						return report;
					}
				} else {
					((PelletInfGraph) hlcInst.getGraph()).close(false);
					StandardValidityReport report = new StandardValidityReport();
					report.add(true, "NutritionContext does not contain an instance of the class", HLCA.nutrClassName); 
					return report;
				}
			} else {
				((PelletInfGraph) hlcInst.getGraph()).close(false);
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "NutritionContext contains logical errors", "");
				Iterator<Report> it = validity.getReports();
				while (it.hasNext())
					report.add(it.next());
				return report;
			}
		}
		((PelletInfGraph) hlcInst.getGraph()).close(false);
		return validity;
	}
}
