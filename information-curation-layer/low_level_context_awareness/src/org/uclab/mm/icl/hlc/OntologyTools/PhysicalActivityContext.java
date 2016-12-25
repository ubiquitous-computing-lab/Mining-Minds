/*package mm.icl.hlc.OntologyTools;

public class PhysicalActivityContext {

}
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.OntologyTools;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL2;

/**
 * High Level Context is the class encapsulating the OntModel associated to a
 * High Level Context Instance. This is an instance of the High Level Context
 * class in the Context Ontology which represents the Mining Minds Context
 * Model. This class provides supporting methods to facilitate the access to
 * High Level Context.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */


public class PhysicalActivityContext extends Context {       ///Asif MM2.5 instead of Context I used HighLevelContext

	/**
	 * Constructor of a new High Level Context instance.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the High Level Context instance.
	 */
	public PhysicalActivityContext(OntModel hlcModel) {

		super(hlcModel);

	}

	/**
	 * Constructor of a new High Level Context instance.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the High Level Context instance.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public PhysicalActivityContext(OntModel hlcModel, ContextOntology ont) {

		super(hlcModel);

		OntModel hlcInst = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, hlcModel);
		hlcInst.addSubModel(ont.getCtxModel());

//		ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.hlcClassName).listInstances(false);
		ExtendedIterator<? extends OntResource> it = hlcInst.getOntClass(HLCA.pacClassName).listInstances(false); ///Asif MMV2.5  pacClassName

		if (it.hasNext()) {

			Individual inst = (Individual) it.next();

			if (!it.hasNext()) {

				super.ctxInstanceName = inst.getURI();

				ExtendedIterator<Resource> iter = inst.listRDFTypes(true);

				boolean classified = false;

				while (iter.hasNext()) {

					String uri = iter.next().getURI();

					if (uri != null && !uri.equals(OWL2.NamedIndividual.getURI())) {

//						if (!uri.equals(HLCA.hlcClassName)) {
						if (!uri.equals(HLCA.pacClassName)) {   //Asif MMV2.5
							super.ctxTypeName = uri;
							classified = true;
						}

					}

				}

				if (!classified)
					setUnidentifiedHlc();

			}

		}

	}

	/**
	 * Method to retrieve whether a High Level Context instance is valid or not.
	 * 
	 * @return true if the High Level Context Instance is valid, and false
	 *         otherwise.
	 */
	public boolean isValidInstanceOfHlc() {

		return (super.ctxInstanceName != null);

	}

	/**
	 * Method to retrieve whether a High Level Context instance has been
	 * classified or not.
	 * 
	 * @return true if the High Level Context Instance is classified, and false
	 *         otherwise.
	 */
	public boolean isClassified() {

		return (super.ctxTypeName != null && !super.ctxTypeName.equals(HLCA.unidentifiedHlc));

	}

	/**
	 * Method to set the High Level Context Instance.
	 * 
	 * @param inst
	 *            OntResource representing the High Level Context Instance.
	 */
	public void setHlcInstance(OntResource inst) {

		setCtxInstanceName(inst.getURI());

	}

	/**
	 * Method to set the class to which a the High Level Context Instance
	 * belongs to.
	 * 
	 * @param c
	 *            OntClass to which the High Level Context Instance belongs and
	 *            which has to be added.
	 */
	public void setHlcClass(OntClass c) {

		getCtxIndiv().addOntClass(c);
		setCtxTypeName(c.getURI());
//		System.out.println("I am here in PhysicalActivityContext.java");  //Asif V2.5

	}

	/**
	 * Method to set that the High Level Context Instance does not belongs to
	 * any of the defined classes in the Context Ontology. This process does not
	 * involve any changes in the OntModel associated to the High Level Context
	 * Instance, but only setting the type of the Context Instance. This is done
	 * this way in order to avoid conflicts in the future and to ensure the
	 * Context Ontology extensibility.
	 * 
	 */
	public void setUnidentifiedHlc() {

		setCtxTypeName(HLCA.unidentifiedHlc);

	}

}
