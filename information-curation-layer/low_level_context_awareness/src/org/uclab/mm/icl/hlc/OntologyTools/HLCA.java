package org.uclab.mm.icl.hlc.OntologyTools;

/**
 * Vocabulary definitions for HLCA in Mining Minds v2.0. Concepts of the Context
 * Ontology which represents the Mining Minds Context Model.
 * 
 * @author Claudia Villalonga & Asif
 * @version 2.0
 * @since 2015-10-28
 */

public class HLCA {

	/**
	 * Namespace of the Context Ontology.
	 */
	public static final String ns = "http://www.miningminds.re.kr/icl/context/context-v2-5.owl#";

	/**
	 * Prefix for the namespace of the Context Ontology.
	 */
	public static final String nsPrefix = "icl2";

	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String hlcClassName = ns + "HighLevelContext";

	/**
	 * Name of the User class in the Context Ontology.
	 */
	public static final String userClassName = ns + "User";

	/**
	 * Name of the property in the Context Ontology which links a Context to the
	 * User it belongs to.
	 */
	public static final String isCtxOfPropName = ns + "isContextOf";

	/**
	 * Name of the property in the Context Ontology which links a Context to its
	 * start time.
	 */
	public static final String hasStartTimePropName = ns + "hasStartTime";

	/**
	 * Name of the property in the Context Ontology which links a Context to its
	 * end time.
	 */
	public static final String hasEndTimePropName = ns + "hasEndTime";

	/**
	 * Name of the Low Level Context class in the Context Ontology.
	 */
	public static final String llcClassName = ns + "LowLevelContext";

	/**
	 * Name of the property in the Context Ontology which links a High Level
	 * Context to the Activity which is part of it.
	 */
	public static final String hasActivityPropName = ns + "hasActivity";

	/**
	 * Name of the property in the Context Ontology which links a High Level
	 * Context to the Emotion which is part of it.
	 */
	public static final String hasEmotionPropName = ns + "hasEmotion";

	/**
	 * Name of the property in the Context Ontology which links a High Level
	 * Context to the Location which is part of it.
	 */
	public static final String hasLocationPropName = ns + "hasLocation";
	
	/**                    Asif
	 * Name of the property in the Context Ontology which links a High Level
	 * Context to the Location which is part of it.
	 */
	public static final String hasFoodPropName = ns + "hasFood";                  //Temporary change Later on If feasible give a separate property every Food Item with Food Category
	
	
	/**
	 * Name of the Activity class in the Context Ontology.
	 */
	public static final String activityClassName = ns + "Activity";

	/**
	 * Name of the Emotion class in the Context Ontology.
	 */
	public static final String emotionClassName = ns + "Emotion";

	/**
	 * Name of the Location class in the Context Ontology.
	 */
	public static final String locationClassName = ns + "Location";
	
	/**
	 * Name of the Food class in the Context Ontology.        Asif
	 */
	public static final String foodClassName = ns + "Food";
	
	

	/**
	 * Name used to represent the Unidentified High Level Context. Please note
	 * that this concept is only used by the HLC Notifier but it is not part of
	 * the Context Ontology. This is done in this way in order to ensure the
	 * Context Ontology extensibility.
	 */
	public static final String unidentifiedHlc = ns + "UnidentifiedHLC";

	/**
	 * Name used to represent the lack of information for the Low Level Context
	 * type Activity. Please note that this concept is only used by the Context
	 * Mapper but it is not part of the Context Ontology.
	 */
	public static final String noActivity = "NoActivity";

	/**
	 * Name used to represent the lack of information for the Low Level Context
	 * type Emotion. Please note that this concept is only used by the Context
	 * Mapper but it is not part of the Context Ontology.
	 */
	public static final String noEmotion = "NoEmotion";

	/**
	 * Name used to represent the lack of information for the Low Level Context
	 * type Location. Please note that this concept is only used by the Context
	 * Mapper but it is not part of the Context Ontology.
	 */
	public static final String noLocation = "NoLocation";
	
	/**  Asif
	 * Name used to represent the lack of information for the Low Level Context
	 * type Food. Please note that this concept is only used by the Context
	 * Mapper but it is not part of the Context Ontology.
	 */
	public static final String noFood= "NoFood";

	/**
	 * Name of the variable used in the SPARQL queries to refer to a High Level
	 * Context.
	 */
	public static final String hlcSparqlVar = "hlc";

	/**
	 * Name of the variable used in the SPARQL queries to refer to a User.
	 */
	public static final String userSparqlVar = "user";

	/**
	 * Name of the variable used in the SPARQL queries to refer to a start time
	 * value.
	 */
	public static final String startTimeSparqlVar = "starttime";

	/**
	 * Name of the variable used in the SPARQL queries to refer to an end time
	 * value
	 */
	public static final String endTimeSparqlVar = "endtime";

	/**
	 * Name of the variable used in the SPARQL queries to refer to a Low Level
	 * Context.
	 */
	public static final String llcSparqlVar = "llc";

	/**
	 * Name of the variable used in the SPARQL queries to refer to a Low Level
	 * Context type, e.g. sitting or standing for the category activity.
	 */
	public static final String llcTypeSparqlVar = "type";

	
	//Asif MMV2.5
	public static final String llcType1SparqlVar = "type1";
	//Asif MMV2.5
	
	//Asif MMV2.5
	public static final String llcType2SparqlVar = "type2";
	public static final String llcType3SparqlVar = "type3";
	//Asif MMV2.5

	/**
	 * Name of the variable used in the SPARQL queries to refer to a Low Level
	 * Context category, e.g., activity, location or emotion.
	 */
	public static final String llcCategorySparqlVar = "category";




	/**
	 * Name of the Nutrition Context in the Context Ontology.
	 */
	public static final String nutrClassName = ns + "NutritionContext";

	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String pacClassName = ns + "PhysicalActivityContext";
	
	
	
	
	
	
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String eggClassName = ns + "Eggs";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String fruitClassName = ns + "Fruits";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String grainClassName = ns + "Grain";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String legumesClassName = ns + "Legumes";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String meatClassName = ns + "Meat";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String milkdairyClassName = ns + "MilkAndDairyProducts";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String nutsClassName = ns + "Nuts";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String seafoodClassName = ns + "SeaFood";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String snacksClassName = ns + "Snacks";
	/**
	 * Name of the High Level Context class in the Context Ontology.
	 */
	public static final String vegClassName = ns + "Vegetable";









}
