/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.ContextOntologyManager;

import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.HLCA;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * Ontology Model Manager: Subcomponent of the Context Ontology Manager which
 * manages the Context Ontology persisted in the Context Ontology Storage.
 * 
 * @author Claudia Villalonga
 * @version 2.0
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
