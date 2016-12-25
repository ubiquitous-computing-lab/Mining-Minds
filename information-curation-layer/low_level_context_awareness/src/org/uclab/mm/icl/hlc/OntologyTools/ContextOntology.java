package org.uclab.mm.icl.hlc.OntologyTools;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.syntax.ElementUnion;

/**
 * ContextOntology is the class encapsulating the OntModel associated to the
 * Context Ontology which represents the Mining Minds Context Model. This class
 * provides supporting methods to facilitate the access to the Context Ontology.
 * 
 * @author Claudia Villalonga      Asif
 * @version 2.0
 * @since 2015-10-28
 */
public class ContextOntology {

	/**
	 * OntModel associated to the Context Ontology which represents the Mining
	 * Minds Context Model.
	 */
	protected OntModel ctxModel;

	/**
	 * Constructor of a new Context Ontology
	 * 
	 * @param ctxModel
	 *            OntModel associated to the Context Ontology.
	 */
	public ContextOntology(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}

	/**
	 * Method to get the OntModel associated to the Context Ontology which
	 * represents the Mining Minds Context Model.
	 * 
	 * @return OntModel representation of the Context Ontology.
	 */
	public OntModel getCtxModel() {
		return ctxModel;
	}

	/**
	 * Method to set the OntModel associated to the Context Ontology which
	 * represents the Mining Minds Context Model.
	 * 
	 * @param ctxModel
	 *            OntModel representation of the Context Ontology.
	 */
	public void setCtxModel(OntModel ctxModel) {
		this.ctxModel = ctxModel;
	}

	/**
	 * Method to get the User class in the Context Ontology.
	 * 
	 * @return OntClass representing the User in the Context Ontology.
	 */
	public OntClass getUserClass() {

		return ctxModel.getOntClass(HLCA.userClassName);

	}

	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to the User it belongs to.
	 * 
	 * @return ObjectProperty linking a Context to the User it belongs to.
	 */
	public ObjectProperty getContextOfProp() {

		return ctxModel.getObjectProperty(HLCA.isCtxOfPropName);

	}

	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to its start time.
	 * 
	 * @return DatatypeProperty linking a Context to its start time.
	 */
	public DatatypeProperty getStartTimeProp() {

		return ctxModel.getDatatypeProperty(HLCA.hasStartTimePropName);

	}

	/**
	 * Method to get the property in the Context Ontology which links a Context
	 * to its end time.
	 * 
	 * @return DatatypeProperty linking a Context to its end time.
	 */
	public DatatypeProperty getEndTimeProp() {

		return ctxModel.getDatatypeProperty(HLCA.hasEndTimePropName);

	}

	/**
	 * Method to get the High Level Context class in the Context Ontology.
	 * 
	 * @return OntClass representing High Level Context in the Context Ontology.
	 */
	public OntClass getHlcClass() {

//		return ctxModel.getOntClass(HLCA.hlcClassName);
		return ctxModel.getOntClass(HLCA.pacClassName);  //Asif MMV2.5

	}

	
	
	
	//////********************************   MMV2.5 To handle Nutrition Context and return the URI for NutritionClass  Start *//////////////////////////////
	
	/**
	 * Method to get the High Level Context class in the Context Ontology.
	 * 
	 * @return OntClass representing High Level Context in the Context Ontology.
	 */
	public OntClass getNutHlcClass() {

//		return ctxModel.getOntClass(HLCA.hlcClassName);
		return ctxModel.getOntClass(HLCA.nutrClassName);  //Asif MMV2.5

	}

	//////********************************   MMV2.5 To handle Nutrition Context and return the URI for NutritionClass  End*//////////////////////////////
	
	
	
	
	
	/**
	 * Method to get the Low Level Context class in the Context Ontology.
	 * 
	 * @return OntClass representing Low Level Context in the Context Ontology.
	 */
	public OntClass getLlcClass() {

		return ctxModel.getOntClass(HLCA.llcClassName);    
//		return ctxModel.getOntClass(HLCA.foodClassName);    //MMV2.5 Nutrition Testing

	}

	/**
	 * Method to get the property in the Context Ontology which links a High
	 * Level Context to the Activity which is part of it.
	 * 
	 * @return ObjectProperty linking a High Level Context to the Activity which
	 *         is part of it.
	 */
	public ObjectProperty getActivityProp() {

		return ctxModel.getObjectProperty(HLCA.hasActivityPropName);

	}

	/**
	 * Method to get the property in the Context Ontology which links a High
	 * Level Context to the Location which is part of it.
	 * 
	 * @return ObjectProperty linking a High Level Context to the Location which
	 *         is part of it.
	 */
	public ObjectProperty getLocationProp() {

		return ctxModel.getObjectProperty(HLCA.hasLocationPropName);

	}

	/**
	 * Method to get the property in the Context Ontology which links a High
	 * Level Context to the Emotion which is part of it.
	 * 
	 * @return ObjectProperty linking a High Level Context to the Emotion which
	 *         is part of it.
	 */
	public ObjectProperty getEmotionProp() {

		return ctxModel.getObjectProperty(HLCA.hasEmotionPropName);

	}
	
	/**   Asif
	 * Method to get the property in the Context Ontology which links a High
	 * Level Context to the Emotion which is part of it.
	 * 
	 * @return ObjectProperty linking a High Level Context to the Emotion which
	 *         is part of it.
	 */
	public ObjectProperty getFoodProp() {

		return ctxModel.getObjectProperty(HLCA.hasFoodPropName);           //Temporary change Later on If feasible give a separate property every Food Item with Food Category

	}
	
	

	
	/**
	 * Method to get the subclass of Low Level Context of a given type, e.g.
	 * Sitting or Standing for the category Activity.
	 * 
	 * @param llcTypeName
	 *            Name of the type of Low Level Context.
	 * @return OntClass representing the type llcTypeName in the Context
	 *         Ontology.
	 */
	public OntClass getLlcTypeClass(String llcTypeName) {

		return ctxModel.getOntClass(llcTypeName);

	}

	/**
	 * Method to get the subclass of Low Level Context of a given category, e.g.
	 * Activity, Emotion or Location.
	 * 
	 * @param llcCategoryName
	 *            Name of the category of Low Level Context.
	 * @return OntClass representing the category llcCategoryName in the Context
	 *         Ontology.
	 */
	public OntClass getLlcCategoryClass(String llcCategoryName) {

		return ctxModel.getOntClass(llcCategoryName);

	}
	

	
	// Asif MMV2.5

	public OntClass getLlcSuperClassCategory (OntClass category) {
	
		
		OntClass LlcSuperClassCategory = null;
		
		for (Iterator i = category.listSuperClasses(true); i.hasNext();)    //   Lists the Super Classes
			
		{
			LlcSuperClassCategory  = (OntClass) i.next();
		
//             System.out.print("I m in Context Ontology for SuperCategory.getLocalName:   " + LlcSuperClassCategory.getLocalName() + "\n");
//             System.out.println(SuperCategory.getURI());
             
				};	
		
		
		
		return LlcSuperClassCategory;

	}

	
	
	
	
/*	
	private static ElementUnion createTriplesUnionForLlcCategoryUserStartTime(OntClass category, Resource user,  //Asif MMV2.5
			ContextOntology ont) {

		
		OntClass SuperClassCategory = null;
		
		for (Iterator i = category.listSuperClasses(true); i.hasNext();)    //   Lists the Super Classes
			
		{
			SuperClassCategory  = (OntClass) i.next();
		
             System.out.print("SuperCategory.getLocalName:   " + SuperClassCategory.getLocalName() + "\n");
//             System.out.println(SuperCategory.getURI());
             
				};		
	*/
	
	
	
	
	
	
	
	

}
