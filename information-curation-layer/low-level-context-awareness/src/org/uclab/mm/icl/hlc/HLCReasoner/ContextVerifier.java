/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCReasoner;

import java.util.Iterator;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.uclab.mm.icl.hlc.OntologyTools.HLCA;
import org.uclab.mm.icl.hlc.OntologyTools.InferredContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.StandardValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * Context Verifier: Subcomponent of the HLC Reasoner which performs a
 * consistency check (validation and verification) of the unclassified
 * high-level context instance versus the Context Ontology Model.
 * 
 * @author Claudia Villalonga
 * @version 2.0
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
	 * Method to validate and verify an unclassified high-level context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified High Level Context instance.
	 * @return true if the unclassified High Level Context Instance passed the
	 *         consistency check, and false otherwise.
	 */
	public boolean isValid(PhysicalActivityContext unclassifiedHlc) {
		return (validate(unclassifiedHlc).isValid());
	}

	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************

	/**
	 * Method to validate and verify an unclassified high-level context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedNutHlc
	 *            Unclassified High Level Context instance.
	 * @return true if the unclassified High Level Context Instance passed the
	 *         consistency check, and false otherwise.
	 */
	public boolean isValid(NutritionContext unclassifiedNutHlc) {
		return (validate(unclassifiedNutHlc).isValid());
	}

	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	
	
	/**
	 * Method to validate and verify an unclassified high-level context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified High Level Context instance.
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

//				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.hlcClassName)
				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.pacClassName)   //Asif MMV2.5  pacClassName
						.listInstances(false);

				if (it.hasNext()) {

					OntResource inst = it.next();

					if (!it.hasNext()) {

						// valid: logically consistent and instance of High
						// Level Context
						unclassifiedHlc.setHlcInstance(inst);

						((PelletInfGraph) hlcInst.getGraph()).close(false);

						return validity;

					} else {

						StandardValidityReport report = new StandardValidityReport();
//						report.add(true, "PhysicalActivityContext contains several instances of the class", HLCA.hlcClassName);
						report.add(true, "PhysicalActivityContext contains several instances of the class", HLCA.pacClassName); //Asif MMV2.5
						report.add(false, "-", inst.getURI());
						while (it.hasNext())
							report.add(false, "-", it.next().getURI());

						((PelletInfGraph) hlcInst.getGraph()).close(false);

						return report;
					}

				} else {

					((PelletInfGraph) hlcInst.getGraph()).close(false);

					StandardValidityReport report = new StandardValidityReport();
//					report.add(true, "PhysicalActivityContext does not contain an instance of the class", HLCA.hlcClassName);
					report.add(true, "PhysicalActivityContext does not contain an instance of the class", HLCA.pacClassName); //Asif MMV2.5
					return report;
				}

			} else {

				((PelletInfGraph) hlcInst.getGraph()).close(false);

				// there are warnings in the report
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

	
	
	
	
	
	
	
	
	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************
	
	
	/**
	 * Method to validate and verify an unclassified high-level context instance
	 * versus the Context Ontology Model.
	 * 
	 * @param unclassifiedNutHlc
	 *            Unclassified High Level Context instance.
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

//				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.hlcClassName)
				ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.nutrClassName)   //Asif MMV2.5  pacClassName
						.listInstances(false);

				if (it.hasNext()) {

					OntResource inst = it.next();

					if (!it.hasNext()) {

						// valid: logically consistent and instance of High
						// Level Context
						unclassifiedNutHlc.setHlcInstance(inst);

						((PelletInfGraph) hlcInst.getGraph()).close(false);

						return validity;

					} else {

						StandardValidityReport report = new StandardValidityReport();
//						report.add(true, "NutritionContext contains several instances of the class", HLCA.nutrClassName);
						report.add(true, "NutritionContext contains several instances of the class", HLCA.nutrClassName); //Asif MMV2.5
						report.add(false, "-", inst.getURI());
						while (it.hasNext())
							report.add(false, "-", it.next().getURI());

						((PelletInfGraph) hlcInst.getGraph()).close(false);

						return report;
					}

				} else {

					((PelletInfGraph) hlcInst.getGraph()).close(false);

					StandardValidityReport report = new StandardValidityReport();
//					report.add(true, "NutritionContext does not contain an instance of the class", HLCA.nutrClassName);
					report.add(true, "NutritionContext does not contain an instance of the class", HLCA.nutrClassName); //Asif MMV2.5
					return report;
				}

			} else {

				((PelletInfGraph) hlcInst.getGraph()).close(false);

				// there are warnings in the report
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

	
	
	
	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
