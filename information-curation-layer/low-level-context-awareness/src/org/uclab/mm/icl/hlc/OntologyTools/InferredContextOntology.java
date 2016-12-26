/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.OntologyTools;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.reasoner.StandardValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;

/**
 * InferredContextOntology extends the ContextOntology class in order to provide
 * the mechanisms to validate the Context Ontology which represents the Mining
 * Minds Context Model.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */
public class InferredContextOntology extends ContextOntology {

	/**
	 * Constructor of a new InferredContextOntology
	 * 
	 * @param ctxModel
	 *            OntModel associated to the inferred version of the Context
	 *            Ontology which represents the Mining Minds Context Model.
	 */
	public InferredContextOntology(OntModel ctxModel) {

		super(ctxModel);
	}

	/**
	 * Method to validate the Context Ontology which represents the Mining Minds
	 * Context Model.
	 * 
	 * @return true if the Context Ontology passed the consistency check, and
	 *         false otherwise.
	 */
	public boolean isValid() {
		return (validate().isValid());
	}

	/**
	 * Method to validate the Context Ontology which represents the Mining Minds
	 * Context Model.
	 * 
	 * @return Validity Report which contains the description of the errors in
	 *         the consistency check of the Context Ontology.
	 */
	public ValidityReport validate() {

		ValidityReport validity = ctxModel.validate();

		if (validity.isValid()) {

			if (!validity.isClean()) {

				// there are warnings in the report (e.g., uninstanciable
				// classes)
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology contains errors in the class definition", "");

				Iterator<Report> it = validity.getReports();
				while (it.hasNext())
					report.add(it.next());

				return report;

			}

			if (ctxModel.getNsURIPrefix(HLCA.ns) == null) {

				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology does not contain the namespace", HLCA.ns);
				return report;

			}

//			if (ctxModel.getOntClass(HLCA.hlcClassName) == null) {
			if (ctxModel.getOntClass(HLCA.pacClassName) == null) { //Asif MMV2.5

				StandardValidityReport report = new StandardValidityReport();
//				report.add(true, "Context Ontology does not contain the class", HLCA.hlcClassName);
				report.add(true, "Context Ontology does not contain the class", HLCA.pacClassName); //Asif MMV2.5
				return report;

			}

		}

		return validity;

	}

}
