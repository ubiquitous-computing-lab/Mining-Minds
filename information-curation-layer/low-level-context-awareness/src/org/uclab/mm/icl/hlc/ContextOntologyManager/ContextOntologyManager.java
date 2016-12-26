package org.uclab.mm.icl.hlc.ContextOntologyManager;

import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.InferredContextOntology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Context Ontology Manager : Component of HLCA which manages the interactions
 * with the Context Ontology Storage where Context Ontology and Context
 * Instances are persisted.
 *
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */
public class ContextOntologyManager {

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
	public ContextOntologyManager(String directory) {

		OntologyModelManager.initialize(directory);

		// Get Context Ontology stored on DB
		OntModel ontModel = OntologyModelManager.retrieveContextOntology();

		if (ontModel != null) {

			OntModel infOntModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, ontModel);

			this.ont = new ContextOntology(ontModel);
			this.infOnt = new InferredContextOntology(infOntModel);

			ContextHandler.initialize(directory, this.ont);

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

		if (this.ont == null) { // OK. There is no Context Model stored in DB
								// and this will be created

			OntModel infOntModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, ontModel);

			ContextOntology ont = new ContextOntology(ontModel);
			InferredContextOntology infOnt = new InferredContextOntology(infOntModel);

			if (infOnt.isValid()) {
				// Load ontology to DB
				OntologyModelManager.loadContextOntology(ont);

				this.ont = ont;
				this.infOnt = infOnt;

				ContextHandler.initialize(directory, this.ont);

			}

		} else
			// There is a Context Model stored in DB. Ignore the provided
			// ontology
			System.err.println("There is a Context Model stored in DB. Ignoring the provided OntModel and "
					+ "using the available one to initialize the Context Ontology Manager.");

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
