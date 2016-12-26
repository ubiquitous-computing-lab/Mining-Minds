/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCReasoner;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

//copied from here till here
import org.uclab.mm.icl.hlc.OntologyTools.HLCA;
import org.uclab.mm.icl.hlc.OntologyTools.InferredContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Context Classifier: Subcomponent of the HLC Reasoner which performs the
 * classification or identification of the context type to which the
 * unclassified High Level Context instance belongs.
 * 
 * @author Claudia Villalonga
 * @version 2.0
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
	 * Method to classify a High Level Context instance, i.e., to identify the
	 * context type to which the unclassified High Level Context instance
	 * belongs.
	 * 
	 * @param unclassifiedHlc
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public PhysicalActivityContext classify(PhysicalActivityContext unclassifiedHlc) {

		// check whether it is already classified
		PhysicalActivityContext classifiedHlc = new PhysicalActivityContext(unclassifiedHlc.getCtxModel(), ont);

		if (!classifiedHlc.isClassified()) {
//Here
			OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
					unclassifiedHlc.getCtxModel());
			

//			System.out.println("I am here in Classifier");  //Asif V2.5  
//			System.out.println(unclassifiedHlc.getCtxModel());  //Asif V2.5
			
			

			
			hlcInst.addSubModel(ont.getCtxModel());
//Here
//			Reasoner hermit=new Reasoner((OWLOntology) hlcInst.getGraph());  //Asif
//		     System.out.println(hermit.isConsistent())   ;     //Asif

			((PelletInfGraph) hlcInst.getGraph()).classify();

			OntClass c = hlcInst.getIndividual(unclassifiedHlc.getCtxInstanceName()).listOntClasses(true).next();
		
			
/*			System.out.println("I am here in Classifier for URI and classifiedHLC");  //Asif V2.5
	        System.out.println(c.getURI());		  //Asif V2.5     HighLevelContext
	        System.out.println(classifiedHlc);		  //Asif V2.5     HighLevelContext
			*/

			
//			if (!c.getURI().equals(HLCA.hlcClassName))
			if (!c.getURI().equals(HLCA.pacClassName))      //Asif MMV2.5  pacClassName

				
				
				// Classification OK and HLC belongs to one of the classes
				// defined in the MM Context Ontology.
				// Add inferred class to HLC Instance
				classifiedHlc.setHlcClass(c);
//Here
			((PelletInfGraph) hlcInst.getGraph()).close(false);

		}

		return classifiedHlc;
	}

	
	
	
	
	
	
	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************
	
	
	
	
	

	/**
	 * Method to classify a High Level Context instance, i.e., to identify the
	 * context type to which the unclassified High Level Context instance
	 * belongs.
	 * 
	 * @param unclassifiedNutHlc
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public NutritionContext classify (NutritionContext unclassifiedNutHlc) {

		// check whether it is already classified
		NutritionContext classifiedNutHlc = new NutritionContext (unclassifiedNutHlc.getCtxModel(), ont);

		if (!classifiedNutHlc.isClassified()) {
//Here
			OntModel hlcInst = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,
					unclassifiedNutHlc.getCtxModel());
			

//			System.out.println("I am here in Classifier");  //Asif V2.5  
//			System.out.println(unclassifiedHlc.getCtxModel());  //Asif V2.5
			
			

			
			hlcInst.addSubModel(ont.getCtxModel());
//Here
//			Reasoner hermit=new Reasoner((OWLOntology) hlcInst.getGraph());  //Asif
//		     System.out.println(hermit.isConsistent())   ;     //Asif

			((PelletInfGraph) hlcInst.getGraph()).classify();

			OntClass c = hlcInst.getIndividual(unclassifiedNutHlc.getCtxInstanceName()).listOntClasses(true).next();
		
			
//		System.out.println("I am here in ContextClassifier for URI and classifiedHLC Line 173");  //Asif V2.5
//	        System.out.println(c.getURI());		  //Asif V2.5     HighLevelContext
//	        System.out.println(classifiedHlc);		  //Asif V2.5     HighLevelContext


			
//			if (!c.getURI().equals(HLCA.hlcClassName))
			if (!c.getURI().equals(HLCA.nutrClassName))      //Asif MMV2.5  pacClassName

				
				
				// Classification OK and HLC belongs to one of the classes
				// defined in the MM Context Ontology.
				// Add inferred class to HLC Instance
				classifiedNutHlc.setHlcClass(c);
//Here
			((PelletInfGraph) hlcInst.getGraph()).close(false);

		}

		return classifiedNutHlc;
	}
	
	
	
	
	

	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	
	
	
	
	
	
	
}
