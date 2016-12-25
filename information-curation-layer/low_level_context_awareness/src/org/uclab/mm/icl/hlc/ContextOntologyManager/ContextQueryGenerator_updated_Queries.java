package org.uclab.mm.icl.hlc.ContextOntologyManager;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package mm.icl.hlc.ContextOntologyManager;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.expr.E_GreaterThan;
import com.hp.hpl.jena.sparql.expr.E_GreaterThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_LessThan;
import com.hp.hpl.jena.sparql.expr.E_LessThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_NotEquals;
import com.hp.hpl.jena.sparql.expr.E_NotExists;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.ExprVar;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.expr.aggregate.AggregatorFactory;
import com.hp.hpl.jena.sparql.expr.nodevalue.NodeValueDT;
import com.hp.hpl.jena.sparql.syntax.ElementFilter;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import com.hp.hpl.jena.sparql.syntax.ElementTriplesBlock;
import com.hp.hpl.jena.sparql.syntax.ElementUnion;
import com.hp.hpl.jena.vocabulary.RDF.Nodes;

import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.HLCA;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import mm.icl.hlc.OntologyTools.LowLevelContext;

*//**
 * Context Query Generator: Subcomponent of the Context Ontology Manager which
 * generates the SPARQL queries required by the Context Handler to find the
 * matching Context instances stored in the Context Ontology Storage.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 *//*
public class ContextQueryGenerator_updated_Queries {

	*//**
	 * Method to generate a SPARQL query for the previous valid and unfinished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            High Level Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and unfinished
	 *         High Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndUnfinishedHlc(PhysicalActivityContext hlc, ContextOntology ont) {

		ElementUnion union= createTriplesUnionForHlcUserStartTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		ElementFilter filterNeg = createFilterNotExistsEndTimeInHlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));

		return createHlcQuery(union, filterNeg, filterLessThanOrEqual);

	}

	*//**
	 * Method to generate a SPARQL query for the previous valid and finished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            High Level Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and finished High
	 *         Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndFinishedHlc(PhysicalActivityContext hlc, ContextOntology ont) {

		ElementUnion union = createTriplesUnionForHlcUserStartAndEndTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		Literal time = hlc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);

		return createHlcQuery(union, filterLessThanOrEqual, filterGreaterThan);

	}

	*//**
	 * Method to generate a SPARQL query for the start time of the next High
	 * Level Context instance.
	 * 
	 * @param hlc
	 *            High Level Context instance for which the start time of the
	 *            next context should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the start time of the next High
	 *         Level Context instance.
	 *//*
	public static Query generateQueryForStartTimeOfNextHlc(PhysicalActivityContext hlc, ContextOntology ont) {

		ElementUnion union= createTriplesUnionForHlcUserStartTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		ElementFilter filterGreaterThan = createFilterStartTimeGreaterThan(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));

		return createMinStartTimeQuery(union, filterGreaterThan);

	}

	*//**
	 * Method to generate a SPARQL query for the previous valid and unfinished
	 * Low Level Context instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and unfinished
	 *         Low Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcCategoryUserStartTime(
				ont.getLlcTypeClass(llc.getLlcCategoryName()), llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		

		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				llc.getDataPropertyValue(ont.getStartTimeProp()));

		return createLlcQuery(union, filterNeg, filterLessThanOrEqual);

	}

	*//**
	 * Method to generate a SPARQL query for the previous valid and unfinished
	 * Low Level Context instance.
	 * 
	 * @param category
	 *            OntClass representing the category, i.e., activity, emotion or
	 *            location, for which a previous Low Level Context instance
	 *            should be matched.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            previous Low Level Context instance should be matched.
	 * @param time
	 *            xsd:dateTime typed Literal representing the time for which the
	 *            previous Low Level Context instance should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and unfinished
	 *         Low Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndUnfinishedLlc(OntClass category, Resource user, Literal time,
			ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcCategoryUserStartTime(category, user, ont);

		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);

		return createLlcQuery(union, filterNeg, filterLessThanOrEqual);

	}

	*//**
	 * Method to generate a SPARQL query for the previous valid and finished Low
	 * Level Context instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and finished High
	 *         Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndFinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union  = createTriplesUnionForLlcCategoryUserStartAndEndTime(
				ont.getLlcTypeClass(llc.getLlcCategoryName()), llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		Literal time = llc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);

		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan);

	}

	*//**
	 * Method to generate a SPARQL query for the previous valid and finished Low
	 * Level Context instance.
	 * 
	 * @param category
	 *            OntClass representing the category, i.e., activity, emotion or
	 *            location, for which a previous Low Level Context instance
	 *            should be matched.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            previous Low Level Context instance should be matched.
	 * @param time
	 *            xsd:dateTime typed Literal representing the time for which the
	 *            previous Low Level Context instance should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and finished High
	 *         Level Context instance.
	 *//*
	public static Query generateQueryForPreviousValidAndFinishedLlc(OntClass category, Resource user, Literal time,
			ContextOntology ont) {

		ElementUnion union  = createTriplesUnionForLlcCategoryUserStartAndEndTime(category, user, ont);

		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);

		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan);

	}

	*//**
	 * Method to generate a SPARQL query for the start time of the next Low
	 * Level Context instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which the start time of the
	 *            next context should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the start time of the next Low Level
	 *         Context instance.
	 *//*
	public static Query generateQueryForStartTimeOfNextLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union  = createTriplesUnionForLlcCategoryUserStartTime(
				ont.getLlcTypeClass(llc.getLlcCategoryName()), llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		ElementFilter filterGreaterThan = createFilterStartTimeGreaterThan(
				llc.getDataPropertyValue(ont.getStartTimeProp()));

		return createMinStartTimeQuery(union, filterGreaterThan);

	}

	*//**
	 * Method to generate a SPARQL query for Low Level Context instances which
	 * have their start time within a time window.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window.
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the end time of the
	 *            window.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the Low Level Context instances
	 *         which have their start time within a time window.
	 *//*
	public static Query generateQueryForLlcWithStartTimeInWindow(Literal windowStart, Literal windowEnd,
			ContextOntology ont) {
		
	//	System.out.println("1. I am in Context QueryGenerator");
		
		
		
//		ElementTriplesBlock block = createTriplesBlockForLlcStartTime(ont);      Asif MMV2.5
		ElementUnion        union = createTriplesUnionForLlcStartTime(ont);
		ElementFilter filterGreaterThanOrEqual = createFilterStartTimeGreaterThanOrEqual(windowStart);
		ElementFilter filterLessThan = createFilterStartTimeLessThan(windowEnd);

		Query q = createLlcQuery(union, filterGreaterThanOrEqual, filterLessThan);

			
		q.addOrderBy(new ExprVar(HLCA.startTimeSparqlVar), Query.ORDER_ASCENDING);
		
		System.out.println("I am in Context Query Generator");
		System.out.println(q);
		
		return q;

	}



	*//**
	 * Method to generate a SPARQL query for the Low Level Context instances
	 * which have their end time within a time window and which do not have a
	 * contiguous Low Level Context instance.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window.
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the end time of the
	 *            window.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the Low Level Context instances
	 *         which have their end time within a time window and which do not
	 *         have a contiguous Low Level Context instance.
	 *//*
	public static Query generateQueryForLlcWithEndTimeInWindowAndNoContiguousLlc(Literal windowStart, Literal windowEnd,
			ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcUserAndEndTimeVar(ont);

		ElementFilter filterGreaterThanOrEqual = createFilterEndTimeGreaterThanOrEqual(windowStart);
		ElementFilter filterLessThan = createFilterEndTimeLessThan(windowEnd);
		ElementFilter filterNoAdj = createFilterNotExistsAdjacentLlc(ont);

		Query q = createLlcQuery(union, filterGreaterThanOrEqual, filterLessThan, filterNoAdj);

		q.addOrderBy(new ExprVar(HLCA.endTimeSparqlVar), Query.ORDER_ASCENDING);

		return q;

	}

	*//**
	 * Method to generate a SPARQL query for unfinished Low Level Context
	 * instances which are concurrent at the start time of a Low Level Context
	 * instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which concurrent context
	 *            instances should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve unfinished Low Level Context
	 *         instances which are concurrent at the start time of a Low Level
	 *         Context instance.
	 *//*
	public static Query generateQueryForConcurrentAtStartTimeUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union  = createTriplesUnionForLlcUserStartTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				llc.getDataPropertyValue(ont.getStartTimeProp()));
		ElementFilter filterNotEquals = createFilterLlcNotEquals(llc.getCtxIndiv());
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));

		return createLlcQuery(union, filterNeg, filterLessThanOrEqual, filterNotEquals, filterNotEqualsCategory);

	}

	*//**
	 * Method to generate a SPARQL query for finished Low Level Context
	 * instances which are concurrent at the start time of a Low Level Context
	 * instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which concurrent context
	 *            instances should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve finished Low Level Context instances
	 *         which are concurrent at the start time of a Low Level Context
	 *         instance.
	 *//*
	public static Query generateQueryForConcurrentAtStartTimeFinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcUserStartAndEndTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		Literal time = llc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		ElementFilter filterNotEquals = createFilterLlcNotEquals(llc.getCtxIndiv());
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));

		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan, filterNotEquals,
				filterNotEqualsCategory);

	}

	*//**
	 * Method to generate a SPARQL query for unfinished Low Level Context
	 * instances which are concurrent at the end time of a Low Level Context
	 * instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which concurrent context
	 *            instances should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve unfinished Low Level Context
	 *         instances which are concurrent at the end time of a Low Level
	 *         Context instance.
	 *//*
	public static Query generateQueryForConcurrentAtEndTimeUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcUserStartTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				llc.getDataPropertyValue(ont.getEndTimeProp()));
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));

		return createLlcQuery(union, filterNeg, filterLessThanOrEqual, filterNotEqualsCategory);

	}

	*//**
	 * Method to generate a SPARQL query for finished Low Level Context
	 * instances which are concurrent at the end time of a Low Level Context
	 * instance.
	 * 
	 * @param llc
	 *            Low Level Context instance for which concurrent context
	 *            instances should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve finished Low Level Context instances
	 *         which are concurrent at the end time of a Low Level Context
	 *         instance.
	 *//*
	public static Query generateQueryForConcurrentAtEndTimeFinishedLlc(LowLevelContext llc, ContextOntology ont) {

		ElementUnion union = createTriplesUnionForLlcUserStartAndEndTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);

		Literal time = llc.getDataPropertyValue(ont.getEndTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));

		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan, filterNotEqualsCategory);

	}

	*//**
	 * Method to create a block of triples which include the High Level Context,
	 * the user and the start time.
	 * 
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the High Level Context, the user and the start
	 *         time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForHlcUserStartTime(Resource user, ContextOntology ont) {

		Triple pattern1 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), Nodes.type, ont.getHlcClass().asNode());
		Triple pattern2 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern3 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getStartTimeProp().asNode(),
				Var.alloc(HLCA.startTimeSparqlVar));

		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern1);
		block.addTriple(pattern2);
		block.addTriple(pattern3);
//		System.out.println("block 1  " + block);
		return block;
	}


	private static ElementUnion createTriplesUnionForHlcUserStartTime(Resource user, ContextOntology ont) {

		Triple pattern1 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), Nodes.type, ont.getHlcClass().asNode());
		Triple pattern2 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern3 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getStartTimeProp().asNode(), Var.alloc(HLCA.startTimeSparqlVar));

		
		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern1);
		block.addTriple(pattern2);
		block.addTriple(pattern3);
//		System.out.println("block 1  " + block);
		
		
		ElementUnion union = new ElementUnion();
		union.addElement(block);
		
				
		return union;
	}
	
	
	*//**
	 * Method to create a block of triples which include the High Level Context,
	 * the user, the start time and the end time.
	 * 
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the High Level Context, the user, the start
	 *         time and the end time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForHlcUserStartAndEndTime(Resource user, ContextOntology ont) {

		ElementTriplesBlock block = createTriplesBlockForHlcUserStartTime(user, ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		block.addTriple(pattern);
//		System.out.println("block 2  " + block);
		return block;
	}

	
	private static ElementUnion createTriplesUnionForHlcUserStartAndEndTime(Resource user, ContextOntology ont) {

		ElementUnion union= createTriplesUnionForHlcUserStartTime(user, ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
	
		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern);
//		System.out.println("block 2  " + block);
		
	//	ElementUnion union = new ElementUnion();
		union.addElement(block);
		
		return union;
	}

	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * the category, the user and the start time.
	 * 
	 * @param category
	 *            OntClass representing the category of the Low Level Context,
	 *            i.e., activity, emotion or location, for which the the triple
	 *            should be created.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the category, the user
	 *         and the start time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcCategoryUserStartTime(OntClass category, Resource user,
			ContextOntology ont) {

		
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),
				com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode());
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),
				Var.alloc(HLCA.startTimeSparqlVar));

		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern1);
		block.addTriple(pattern2);
		block.addTriple(pattern3);
		block.addTriple(pattern4);
//		System.out.println("block 3  " + block);
		return block;
	}


	private static ElementUnion createTriplesUnionForLlcCategoryUserStartTime(OntClass category, Resource user,
			ContextOntology ont) {

		
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode());
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));

	
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode());
		Triple pattern7 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode());
		Triple pattern8 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));

		
		
		
		
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		ElementTriplesBlock blockb = new ElementTriplesBlock();
		blocka.addTriple(pattern1);
		blocka.addTriple(pattern2);
		blockb.addTriple(pattern3);
		blockb.addTriple(pattern4);
//		System.out.println("block 3  " + block);
		
		ElementUnion union = new ElementUnion();
		union.addElement(blocka);
		union.addElement(blockb);
		
		
		
		return union;
	}
	
	
	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * the category, the user, the start time and the end time.
	 * 
	 * @param category
	 *            OntClass representing the category of the Low Level Context,
	 *            i.e., activity, emotion or location, for which the the triple
	 *            should be created.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the category, the user,
	 *         the start time and the end time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcCategoryUserStartAndEndTime(OntClass category,
			Resource user, ContextOntology ont) {

		ElementTriplesBlock block = createTriplesBlockForLlcCategoryUserStartTime(category, user, ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		block.addTriple(pattern);
	//	System.out.println("block 4  " + block);
		return block;
	}


	
	private static ElementUnion createTriplesUnionForLlcCategoryUserStartAndEndTime(OntClass category,
			Resource user, ContextOntology ont) {

		ElementUnion union= createTriplesUnionForLlcCategoryUserStartTime(category, user, ont);

		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		
		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern);
	//	System.out.println("block 4  " + block);
		
				

		union.addElement(block);
		
		
		return union;
	}

	
	*//**
	 * Method to create a block of triples which include the Low Level Context
	 * and the start time.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context and the start time.
	 *//*
	
	private static ElementUnion createTriplesUnionForLlcStartTime(ContextOntology ont) {
//	private static ElementTriplesBlock createTriplesBlockForLlcStartTime(ContextOntology ont) {
		
//	System.out.println("1. I am in Context QueryGenerator");
	
	

		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         Nodes.type,                                       Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),    com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         ont.getStartTimeProp().asNode(),                  Var.alloc(HLCA.startTimeSparqlVar));


		
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         Nodes.type,                                       Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),     com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar));
		Triple pattern7 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar),    com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern8 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         ont.getStartTimeProp().asNode(),                  Var.alloc(HLCA.startTimeSparqlVar));
		
		

		
//		ElementTriplesBlock block = new ElementTriplesBlock();
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		ElementTriplesBlock blockb = new ElementTriplesBlock();
		
		System.out.println("PatternValues block 5");
		System.out.println(pattern1);
		System.out.println(pattern2);
		System.out.println(pattern3);
		System.out.println(pattern4);
		
		
		ElementUnion union = new ElementUnion();
		
		
		blocka.addTriple(pattern1);
		blocka.addTriple(pattern2);
		blocka.addTriple(pattern3);
		blocka.addTriple(pattern4);
		

		blockb.addTriple(pattern5);
		blockb.addTriple(pattern6);
		blockb.addTriple(pattern7);
		blockb.addTriple(pattern8);
		blockb.addTriple(pattern9);
		
		
		union.addElement(blocka);
		union.addElement(blockb);

		System.out.println("I m in CQG printing Union");
		System.out.println(union);
		
			System.out.println(" Printing block 5 ") ;
			System.out.println( block);

			
	return union;
	}

	
	
	
	//Dummy
	private static ElementTriplesBlock createTriplesBlockForLlcStartTime(ContextOntology ont) {
	

		ElementTriplesBlock block = new ElementTriplesBlock();
		
		
		
		return block;
	}
	
	
	
	
	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * the start time and the end time.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the start time and the
	 *         end time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcStartAndEndTime(ContextOntology ont) {

		ElementTriplesBlock block = createTriplesBlockForLlcStartTime(ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		block.addTriple(pattern);
//		System.out.println("block 6  " + block);
		return block;
	}
	
	
	private static ElementUnion createTriplesUnionForLlcStartAndEndTime(ContextOntology ont) { //Asif MMV 2.5

//		ElementTriplesBlock block = createTriplesBlockForLlcStartTime(ont);
		ElementUnion union = createTriplesUnionForLlcStartTime(ont);   //Asif MM V2.5
		
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),	Var.alloc(HLCA.endTimeSparqlVar));
		
		ElementTriplesBlock block = new ElementTriplesBlock();    //Asif MMV 2.5
		block.addTriple(pattern);   //Asif MMV 2.5
		union.addElement(block);   //Asif MMV 2.5
		
//		System.out.println("block 6  " + block);
		
				
		return union;   //Asif MMV 2.5
	}
	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * the user and the start time.
	 * 
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the user and the start
	 *         time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcUserStartTime(Resource user, ContextOntology ont) {

		ElementTriplesBlock block = createTriplesBlockForLlcStartTime(ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		block.addTriple(pattern);
//		System.out.println("block 7  " + block);
		return block;

	}

	private static ElementUnion createTriplesUnionForLlcUserStartTime(Resource user, ContextOntology ont) {

		
		
		ElementUnion union = createTriplesUnionForLlcStartTime(ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		
		ElementTriplesBlock block = new ElementTriplesBlock();    //Asif MMV 2.5
		block.addTriple(pattern);
		union.addElement(block);   //Asif MMV 2.5
		
//		System.out.println("block 7  " + block);
		return union;

	}
	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * the user, the start time and the end time.
	 * 
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the user, the start time
	 *         and the end time.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcUserStartAndEndTime(Resource user, ContextOntology ont) {

		ElementTriplesBlock block = createTriplesBlockForLlcStartAndEndTime(ont);
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		block.addTriple(pattern);
//		System.out.println("block 8  " + block);
		return block;

	}

	private static ElementUnion createTriplesUnionForLlcUserStartAndEndTime(Resource user, ContextOntology ont) {

		ElementUnion union= createTriplesUnionForLlcStartAndEndTime(ont);

		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());

//		System.out.println("block 8  " + block);

		ElementTriplesBlock block = new ElementTriplesBlock();    //Asif MMV 2.5
		block.addTriple(pattern);
		union.addElement(block);   //Asif MMV 2.5
		
		return union;

	}
	*//**
	 * Method to create a block of triples which include the Low Level Context,
	 * and the user and the end time as variables.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, and the user and the end
	 *         time as variables.
	 *//*
	private static ElementTriplesBlock createTriplesBlockForLlcUserAndEndTimeVar(ContextOntology ont) {

		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), 	Var.alloc(HLCA.userSparqlVar));
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(), Var.alloc(HLCA.endTimeSparqlVar));

		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern1);
		block.addTriple(pattern2);
		block.addTriple(pattern3);
		block.addTriple(pattern4);
		block.addTriple(pattern5);

//		System.out.println("block 9  " + block);
		return block;
	}

	private static ElementUnion createTriplesUnionForLlcUserAndEndTimeVar(ContextOntology ont) {

		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), 	Var.alloc(HLCA.userSparqlVar));
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(), Var.alloc(HLCA.endTimeSparqlVar));

		ElementTriplesBlock block = new ElementTriplesBlock();
		block.addTriple(pattern1);
		block.addTriple(pattern2);
		block.addTriple(pattern3);
		block.addTriple(pattern4);
		block.addTriple(pattern5);

//		System.out.println("block 9  " + block);
		
		ElementUnion union = new ElementUnion();	
		union.addElement(block);
		
		
		return union;
	}
	*//**
	 * Method to create a filter for start time less than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time less than the value of time.
	 *//*
	private static ElementFilter createFilterStartTimeLessThan(Literal time) {

		Expr exprLessThan = new E_LessThan(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprLessThan);
	}

	*//**
	 * Method to create a filter for start time less than or equal to a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time less than or equal to the value of time.
	 *//*
	private static ElementFilter createFilterStartTimeLessThanOrEqual(Literal time) {

		Expr exprLessThanOrEqual = new E_LessThanOrEqual(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprLessThanOrEqual);
	}

	*//**
	 * Method to create a filter for start time greater than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time greater than the value of time.
	 *//*
	private static ElementFilter createFilterStartTimeGreaterThan(Literal time) {

		Expr exprGreaterThan = new E_GreaterThan(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprGreaterThan);
	}

	*//**
	 * Method to create a filter for start time greater than or equal to a
	 * value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time greater than or equal to the value of time.
	 *//*
	private static ElementFilter createFilterStartTimeGreaterThanOrEqual(Literal time) {

		Expr exprGreaterThanOrEqual = new E_GreaterThanOrEqual(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprGreaterThanOrEqual);
	}

	*//**
	 * Method to create a filter for end time less than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time less than the value of time.
	 *//*
	private static ElementFilter createFilterEndTimeLessThan(Literal time) {

		Expr exprLessThan = new E_LessThan(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprLessThan);
	}

	*//**
	 * Method to create a filter for end time greater than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time greater than the value of time.
	 *//*
	private static ElementFilter createFilterEndTimeGreaterThan(Literal time) {

		Expr exprGreaterThan = new E_GreaterThan(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprGreaterThan);
	}

	*//**
	 * Method to create a filter for end time greater than or equal to a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time greater than or equal to the value of time.
	 *//*
	private static ElementFilter createFilterEndTimeGreaterThanOrEqual(Literal time) {

		Expr exprGreaterThanOrEqual = new E_GreaterThanOrEqual(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));

		return new ElementFilter(exprGreaterThanOrEqual);
	}

	*//**
	 * Method to create a filter for "not exists" end time in High Level
	 * Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end time in High Level Context.
	 *//*
	private static ElementFilter createFilterNotExistsEndTimeInHlc(ContextOntology ont) {

		Triple pattern = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock negation = new ElementTriplesBlock();
		negation.addTriple(pattern);

		Expr exprNeg = new E_NotExists(negation);

		return new ElementFilter(exprNeg);
	}

	*//**
	 * Method to create a filter for "not exists" end time in Low Level Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end time in Low Level Context.
	 *//*
	private static ElementFilter createFilterNotExistsEndTimeInLlc(ContextOntology ont) {

		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock negation = new ElementTriplesBlock();
		negation.addTriple(pattern);

		Expr exprNeg = new E_NotExists(negation);

		return new ElementFilter(exprNeg);
	}

	*//**
	 * Method to create a filter for "not exists" end adjacent posterior Low
	 * Level Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end adjacent posterior Low Level Context.
	 *//*
	private static ElementFilter createFilterNotExistsAdjacentLlc(ContextOntology ont) {

		String adj = "Adjacent";

		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), Nodes.type,
				Var.alloc(HLCA.llcTypeSparqlVar + adj));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar + adj),
				com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getContextOfProp().asNode(),
				Var.alloc(HLCA.userSparqlVar));
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getStartTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));

		ElementTriplesBlock negation = new ElementTriplesBlock();
		negation.addTriple(pattern1);
		negation.addTriple(pattern2);
		negation.addTriple(pattern3);
		negation.addTriple(pattern4);

		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcSparqlVar), new ExprVar(HLCA.llcSparqlVar + adj));
		ElementFilter notEqual = new ElementFilter(exprNotEquals);

		ElementGroup body = new ElementGroup();
		body.addElement(negation);
		body.addElement(notEqual);

		Expr exprNeg = new E_NotExists(body);

		return new ElementFilter(exprNeg);
	}

	*//**
	 * Method to create a filter for "not equals" to a given Low Level Context.
	 * 
	 * @param llc
	 *            Low Level Context for which the matching one has to be not
	 *            equal.
	 * @return Filter for "not equals" to llc.
	 *//*
	private static ElementFilter createFilterLlcNotEquals(Individual llc) {

		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcSparqlVar), NodeValue.makeNode(llc.asNode()));
		return new ElementFilter(exprNotEquals);

	}

	*//**
	 * Method to create a filter for "not equals" to a Low Level Context
	 * category, e.g. Activity, Emotion or Location.
	 * 
	 * @param llcCategory
	 *            OntClass representing the category of Low Level Context in the
	 *            Context Ontolog, which does not have to match.
	 * @return Filter for "not equals" to the llcCategory.
	 *//*
	private static ElementFilter createFilterLlcCategoryNotEquals(OntClass llcCategory) {

		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcCategorySparqlVar),
				NodeValue.makeNode(llcCategory.asNode()));
		return new ElementFilter(exprNotEquals);

	}

	*//**
	 * Method to create a select SPARQL query for High Level Context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter1
	 *            Filter to be part of the SPARQL query.
	 * @param filter2
	 *            Triples block to be part of the SPARQL query.
	 * @return A select SPARQL query for High Level Context which is composed of
	 *         the block, filter1 and filter2.
	 *//*
	private static Query createHlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2) {

		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter1);
		body.addElement(filter2);

		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.addResultVar(HLCA.hlcSparqlVar);

		return q;
	}

	*//**
	 * Method to create a select SPARQL query for Low Level Context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter1
	 *            First filter to be part of the SPARQL query.
	 * @param filter2
	 *            Second filter to be part of the SPARQL query.
	 * @return A select SPARQL query for Low Level Context which is composed of
	 *         the block, filter1 and filter2.
	 *//*
//	private static Query createLlcQuery(ElementTriplesBlock block, ElementFilter filter1, ElementFilter filter2) {    
		private static Query createLlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2) { //Asif MM V2.5		

		ElementGroup body = new ElementGroup();
//		body.addElement(block);
		body.addElement(union); //Asif MMV2.5
		body.addElement(filter1);
		body.addElement(filter2);

		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.addResultVar(HLCA.llcSparqlVar);

		return q;
	}

	*//**
	 * Method to create a select SPARQL query for Low Level Context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter1
	 *            First filter to be part of the SPARQL query.
	 * @param filter2
	 *            Second filter to be part of the SPARQL query.
	 * @param filter3
	 *            Third filter to be part of the SPARQL query.
	 * @return A select SPARQL query for Low Level Context which is composed of
	 *         the block, filter1, filter2 and filter3.
	 *//*
	private static Query createLlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2,
			ElementFilter filter3) {

		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter1);
		body.addElement(filter2);
		body.addElement(filter3);

		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.addResultVar(HLCA.llcSparqlVar);

		return q;
	}

	*//**
	 * Method to create a select SPARQL query for Low Level Context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter1
	 *            First filter to be part of the SPARQL query.
	 * @param filter2
	 *            Second filter to be part of the SPARQL query.
	 * @param filter3
	 *            Third filter to be part of the SPARQL query.
	 * @param filter4
	 *            Forth filter to be part of the SPARQL query.
	 * @return A select SPARQL query for Low Level Context which is composed of
	 *         the block, filter1, filter2 and filter3.
	 *//*
	private static Query createLlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2,
			ElementFilter filter3, ElementFilter filter4) {

		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter1);
		body.addElement(filter2);
		body.addElement(filter3);
		body.addElement(filter4);

		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.addResultVar(HLCA.llcSparqlVar);

		return q;
	}

	*//**
	 * Method to create a select SPARQL query for the minimum start time of a
	 * context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter
	 *            Filter to be part of the SPARQL query.
	 * @return A select SPARQL query for the minimum start time of a context.
	 *//*
	private static Query createMinStartTimeQuery(ElementUnion union, ElementFilter filter) {

		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter);

		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.allocAggregate(AggregatorFactory.createMin(false, new ExprVar(HLCA.startTimeSparqlVar)));

		return q;
	}
}
*/