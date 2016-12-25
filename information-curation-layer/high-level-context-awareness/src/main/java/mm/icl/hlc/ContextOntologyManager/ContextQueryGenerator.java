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
import mm.icl.hlc.OntologyTools.LowLevelContext;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
/**
 * Context Query Generator: Subcomponent of the Context Ontology Manager which
 * generates the SPARQL queries required by the Context Handler to find the
 * matching Context instances stored in the Context Ontology Storage.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextQueryGenerator {
	private static ContextQueryGenerator contextQueryGenerator = null;
	
	/**
	 * Method to generate a SPARQL query for the previous valid and unfinished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            PhysicalActivity Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and unfinished
	 *         PhysicalActivity Context instance.
	 */
	public Query generateQueryForPreviousValidAndUnfinishedHlc(PhysicalActivityContext hlc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForHlcUserStartTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		ElementFilter filterNeg = createFilterNotExistsEndTimeInHlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));
		return createHlcQuery(union, filterNeg, filterLessThanOrEqual);
	}
	/**
	 * Method to generate a SPARQL query for the previous valid and unfinished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            Nutrition Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and unfinished
	 *         Nutrition Context instance.
	 */
	public  Query generateQueryForPreviousValidAndUnfinishedHlc(NutritionContext hlc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForNutHlcUserStartTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		ElementFilter filterNeg = createFilterNotExistsEndTimeInHlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));
		return createHlcQuery(union, filterNeg, filterLessThanOrEqual);
	}
	/**
	 * Method to generate a SPARQL query for the previous valid and finished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            PhysicalActivity Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and finished PhysicalActivity
	 *         Context instance.
	 */
	public  Query generateQueryForPreviousValidAndFinishedHlc(PhysicalActivityContext hlc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForHlcUserStartAndEndTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		Literal time = hlc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		return createHlcQuery(union, filterLessThanOrEqual, filterGreaterThan);
	}
	/**
	 * Method to generate a SPARQL query for the previous valid and finished
	 * High Level Context instance.
	 * 
	 * @param hlc
	 *            Nutrition Context instance for which the previous context
	 *            should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the previous valid and finished Nutrition
	 *          Context instance.
	 */
	public  Query generateQueryForPreviousValidAndFinishedHlc(NutritionContext hlc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForHlcUserStartAndEndTime(
				hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		Literal time = hlc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		return createHlcQuery(union, filterLessThanOrEqual, filterGreaterThan);
	}
	/**
	 * Method to generate a SPARQL query for the start time of the next High
	 * Level Context instance.
	 * 
	 * @param hlc
	 *            PhysicalActivity Context instance for which the start time of the
	 *            next context should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the start time of the next PhysicalActivity
	 *          Context instance.
	 */
	public  Query generateQueryForStartTimeOfNextHlc(PhysicalActivityContext hlc, ContextOntology ont) {
		ElementUnion union= createTriplesUnionForHlcUserStartTime(hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);  
		ElementFilter filterGreaterThan = createFilterStartTimeGreaterThan(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));
		return createMinStartTimeQuery(union, filterGreaterThan);
	}
	/**
	 * Method to generate a SPARQL query for the start time of the next High
	 * Level Context instance.
	 * 
	 * @param hlc
	 *            Nutrition Context instance for which the start time of the
	 *            next context should be matched.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return The SPARQL Query to retrieve the start time of the next Nutrition
	 *         Context instance.
	 */
	public  Query generateQueryForStartTimeOfNextHlc(NutritionContext hlc, ContextOntology ont) {
		ElementUnion union= createTriplesUnionForHlcUserStartTime(hlc.getObjectPropertyValue(ont.getContextOfProp()), ont);  
		ElementFilter filterGreaterThan = createFilterStartTimeGreaterThan(
				hlc.getDataPropertyValue(ont.getStartTimeProp()));
		return createMinStartTimeQuery(union, filterGreaterThan);
	}
	/**
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
	 */
	public  Query generateQueryForPreviousValidAndUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union= createTriplesUnionForLlcCategoryUserStartTime (ont.getLlcTypeClass(llc.getLlcCategoryName()), 
																			llc.getObjectPropertyValue(ont.getContextOfProp()), 	ont); 
		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(llc.getDataPropertyValue(ont.getStartTimeProp()));
		return createLlcQuery(union, filterNeg, filterLessThanOrEqual);
	}
	/**
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
	 */
	public  Query generateQueryForPreviousValidAndUnfinishedLlc(OntClass category, Resource user, Literal time,
			ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcCategoryUserStartTime(category, user, ont);  
		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		return createLlcQuery(union, filterNeg, filterLessThanOrEqual);
	}
	/**
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
	 */
	public  Query generateQueryForPreviousValidAndFinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcCategoryUserStartAndEndTime(  
				ont.getLlcTypeClass(llc.getLlcCategoryName()), llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		Literal time = llc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan);
	}
	/**
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
	 */
	public  Query generateQueryForPreviousValidAndFinishedLlc(OntClass category, Resource user, Literal time,
			ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcCategoryUserStartAndEndTime(category, user, ont);  
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan);
	}
	/**
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
	 */
	public  Query generateQueryForStartTimeOfNextLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcCategoryUserStartTime(  
				ont.getLlcTypeClass(llc.getLlcCategoryName()), llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		ElementFilter filterGreaterThan = createFilterStartTimeGreaterThan(
				llc.getDataPropertyValue(ont.getStartTimeProp()));
		return createMinStartTimeQuery(union, filterGreaterThan);
	}
	/**
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
	 */
	public  Query generateQueryForLlcWithStartTimeInWindow(Literal windowStart, Literal windowEnd,
			ContextOntology ont) {
		
		ElementUnion union= createTriplesUnionForLlcStartTime(ont);
		ElementFilter filterGreaterThanOrEqual = createFilterStartTimeGreaterThanOrEqual(windowStart);
		ElementFilter filterLessThan = createFilterStartTimeLessThan(windowEnd);
		Query q = createLlcQuery(union, filterGreaterThanOrEqual, filterLessThan);
		q.addOrderBy(new ExprVar(HLCA.startTimeSparqlVar), Query.ORDER_ASCENDING);
		return q;
	}
	/**
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
	 */
	public  Query generateQueryForLlcWithEndTimeInWindowAndNoContiguousLlc(Literal windowStart, Literal windowEnd, ContextOntology ont) {  
		ElementUnion union = createTriplesUnionForLlcUserAndEndTimeVar(ont);
		ElementFilter filterGreaterThanOrEqual = createFilterEndTimeGreaterThanOrEqual(windowStart);
		ElementFilter filterLessThan = createFilterEndTimeLessThan(windowEnd);
		ElementFilter filterNoAdj = createFilterNotExistsAdjacentLlc(ont);
		Query q = createLlcQuery(union, filterGreaterThanOrEqual, filterLessThan, filterNoAdj);
		q.addOrderBy(new ExprVar(HLCA.endTimeSparqlVar), Query.ORDER_ASCENDING);
		return q;
	}
	/**
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
	 */
	public  Query generateQueryForConcurrentAtStartTimeUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcUserStartTime(       
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				llc.getDataPropertyValue(ont.getStartTimeProp()));
		ElementFilter filterNotEquals = createFilterLlcNotEquals(llc.getCtxIndiv());
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));
		return createLlcQuery(union, filterNeg, filterLessThanOrEqual, filterNotEquals, filterNotEqualsCategory);
	}
	/**
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
	 */
	public  Query generateQueryForConcurrentAtStartTimeFinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcUserStartAndEndTime(        
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		Literal time = llc.getDataPropertyValue(ont.getStartTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		ElementFilter filterNotEquals = createFilterLlcNotEquals(llc.getCtxIndiv());
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));
		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan, filterNotEquals,	filterNotEqualsCategory);
	}
	/**
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
	 */
	public  Query generateQueryForConcurrentAtEndTimeUnfinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcUserStartTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		ElementFilter filterNeg = createFilterNotExistsEndTimeInLlc(ont);
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(
				llc.getDataPropertyValue(ont.getEndTimeProp()));
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));
		return createLlcQuery(union, filterNeg, filterLessThanOrEqual, filterNotEqualsCategory);
	}
	/**
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
	 */
	public  Query generateQueryForConcurrentAtEndTimeFinishedLlc(LowLevelContext llc, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcUserStartAndEndTime(
				llc.getObjectPropertyValue(ont.getContextOfProp()), ont);
		Literal time = llc.getDataPropertyValue(ont.getEndTimeProp());
		ElementFilter filterLessThanOrEqual = createFilterStartTimeLessThanOrEqual(time);
		ElementFilter filterGreaterThan = createFilterEndTimeGreaterThan(time);
		ElementFilter filterNotEqualsCategory = createFilterLlcCategoryNotEquals(
				ont.getLlcCategoryClass(llc.getLlcCategoryName()));
		return createLlcQuery(union, filterLessThanOrEqual, filterGreaterThan, filterNotEqualsCategory);
	}
	/**
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
	 */
		private  ElementUnion createTriplesUnionForHlcUserStartTime(Resource user, ContextOntology ont) { //Asif
		Triple pattern1 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), Nodes.type, ont.getHlcClass().asNode());
		Triple pattern2 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		Triple pattern3 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getStartTimeProp().asNode(),
				Var.alloc(HLCA.startTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		ElementUnion union= new ElementUnion();
		blocka.addTriple(pattern1);
		blocka.addTriple(pattern2);
		blocka.addTriple(pattern3);
		union.addElement(blocka);
		return union;
	}
		/**
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
		 */
			private  ElementUnion createTriplesUnionForNutHlcUserStartTime(Resource user, ContextOntology ont) { 
			Triple pattern4 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), Nodes.type, ont.getNutHlcClass().asNode());  
			Triple pattern5 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
			Triple pattern6 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getStartTimeProp().asNode(),
					Var.alloc(HLCA.startTimeSparqlVar));
			ElementTriplesBlock blockb = new ElementTriplesBlock();
			ElementUnion union= new ElementUnion();
			blockb.addTriple(pattern4);
			blockb.addTriple(pattern5);
			blockb.addTriple(pattern6);
			union.addElement(blockb);
			return union;
		}
	/**
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
	 */
		private  ElementUnion createTriplesUnionForHlcUserStartAndEndTime(Resource user, ContextOntology ont) { 
		ElementUnion union = createTriplesUnionForHlcUserStartTime(user, ont);
		Triple pattern1 = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getEndTimeProp().asNode(),Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		blocka.addTriple(pattern1);
		union.addElement(blocka);
		return union;
	}
	/**
	 * Method to create a block of triples which include the Low Level Context,
	 * the category, the user and the start time.
	 * 
	 * @param category
	 *            OntClass representing the category of the Low Level Context,
	 *            i.e., activity, emotion, food or location, for which the the triple
	 *            should be created.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the category, the user
	 *         and the start time.
	 */
	private  ElementUnion createTriplesUnionForLlcCategoryUserStartTime(OntClass category, Resource user,   
			ContextOntology ont) {
		OntClass SuperClassCategory  = ont.getLlcSuperClassCategory(category);		
				ElementTriplesBlock blocka = new ElementTriplesBlock();
				ElementTriplesBlock blockb = new ElementTriplesBlock();
				Triple pattern1=null;
				Triple pattern2=null;
				Triple pattern3=null;
				Triple pattern4=null;
				Triple pattern5=null;
				Triple pattern6=null;
				Triple pattern7=null;
				Triple pattern8=null;
				Triple pattern9=null;
				ElementUnion union = new ElementUnion();
				if (SuperClassCategory.getLocalName().equals("Food") )
				{
				pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
				pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, SuperClassCategory.asNode());  
				pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
				pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));
				pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
				pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar));
				pattern7 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, SuperClassCategory.asNode()); 
				pattern8 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
				pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));
				}
				else {
				pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
				pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode());   
				pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
				pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));
				pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
				pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar));
				pattern7 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar),com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, category.asNode()); 
				pattern8 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
				pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getStartTimeProp().asNode(),Var.alloc(HLCA.startTimeSparqlVar));
				}
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
		return union;
	}
	/**
	 * Method to create a block of triples which include the Low Level Context,
	 * the category, the user, the start time and the end time.
	 * 
	 * @param category
	 *            OntClass representing the category of the Low Level Context,
	 *            i.e., activity, emotion, food or location, for which the the triple
	 *            should be created.
	 * @param user
	 *            Resource representing the instance of the user for which the
	 *            triple should be created.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the category, the user,
	 *         the start time and the end time.
	 */
	private  ElementUnion createTriplesUnionForLlcCategoryUserStartAndEndTime(OntClass category, 
			Resource user, ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcCategoryUserStartTime(category, user, ont);  
		Triple patterna = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(), Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		blocka.addTriple(patterna);
		union.addElement(blocka);
		return union;
	}
	/**
	 * Method to create a block of triples which include the Low Level Context
	 * and the start time.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context and the start time.
	 */
	private  ElementUnion createTriplesUnionForLlcStartTime(ContextOntology ont) {  
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         Nodes.type,                                       Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),    com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         ont.getStartTimeProp().asNode(),                  Var.alloc(HLCA.startTimeSparqlVar));
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         Nodes.type,                                       Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar),     com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar));
		Triple pattern7 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar),    com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern8 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar),         ont.getStartTimeProp().asNode(),                  Var.alloc(HLCA.startTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		ElementTriplesBlock blockb = new ElementTriplesBlock();
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
	return union;
	}
	/**
	 * Method to create a block of triples which include the Low Level Context,
	 * the start time and the end time.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, the start time and the
	 *         end time.
	 */
	private  ElementUnion createTriplesUnionForLlcStartAndEndTime(ContextOntology ont) {
		ElementUnion union = createTriplesUnionForLlcStartTime(ont);
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),	Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		blocka.addTriple(pattern1);
		union.addElement(blocka);
		return union;
	}
	/**
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
	 */
	private  ElementUnion createTriplesUnionForLlcUserStartTime(Resource user, ContextOntology ont) {  
		ElementUnion union = createTriplesUnionForLlcStartTime(ont);
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		blocka.addTriple(pattern1);
		union.addElement(blocka);
		return union ;
	}
	/**
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
	 */
	private  ElementUnion createTriplesUnionForLlcUserStartAndEndTime(Resource user, ContextOntology ont) { 
		ElementUnion union = createTriplesUnionForLlcStartAndEndTime(ont);  
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(), user.asNode());
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		blocka.addTriple(pattern1);
		union.addElement(blocka);
		return union;
	}
	/**
	 * Method to create a block of triples which include the Low Level Context,
	 * and the user and the end time as variables.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Triples block for the Low Level Context, and the user and the end
	 *         time as variables.
	 */
	private  ElementUnion createTriplesUnionForLlcUserAndEndTimeVar(ContextOntology ont) {  
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar),	com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(),				Var.alloc(HLCA.userSparqlVar));
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),				Var.alloc(HLCA.endTimeSparqlVar));
		Triple pattern6 = Triple.create(Var.alloc(HLCA.llcSparqlVar), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar));
		Triple pattern7 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar));
		Triple pattern8 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern9 = Triple.create(Var.alloc(HLCA.llcCategorySparqlVar),	com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, ont.getLlcClass().asNode());
		Triple pattern10 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getContextOfProp().asNode(),				Var.alloc(HLCA.userSparqlVar));
		Triple pattern11 = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),				Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock blocka = new ElementTriplesBlock();
		ElementTriplesBlock blockb = new ElementTriplesBlock();
		ElementUnion union = new ElementUnion();
		blocka.addTriple(pattern1);
		blocka.addTriple(pattern2);
		blocka.addTriple(pattern3);
		blocka.addTriple(pattern4);
		blocka.addTriple(pattern5);
		blockb.addTriple(pattern6);
		blockb.addTriple(pattern7);
		blockb.addTriple(pattern8);
		blockb.addTriple(pattern9);
		blockb.addTriple(pattern10);
		blockb.addTriple(pattern11);
		union.addElement(blocka);
		union.addElement(blockb);
	return union;
	}
	/**
	 * Method to create a filter for start time less than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time less than the value of time.
	 */
	private  ElementFilter createFilterStartTimeLessThan(Literal time) {
		Expr exprLessThan = new E_LessThan(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprLessThan);
	}
	/**
	 * Method to create a filter for start time less than or equal to a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time less than or equal to the value of time.
	 */
	private  ElementFilter createFilterStartTimeLessThanOrEqual(Literal time) {
		Expr exprLessThanOrEqual = new E_LessThanOrEqual(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprLessThanOrEqual);
	}
	/**
	 * Method to create a filter for start time greater than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time greater than the value of time.
	 */
	private  ElementFilter createFilterStartTimeGreaterThan(Literal time) {
		Expr exprGreaterThan = new E_GreaterThan(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprGreaterThan);
	}
	/**
	 * Method to create a filter for start time greater than or equal to a
	 * value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for start time greater than or equal to the value of time.
	 */
	private  ElementFilter createFilterStartTimeGreaterThanOrEqual(Literal time) {
		Expr exprGreaterThanOrEqual = new E_GreaterThanOrEqual(new ExprVar(HLCA.startTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprGreaterThanOrEqual);
	}
	/**
	 * Method to create a filter for end time less than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time less than the value of time.
	 */
	private  ElementFilter createFilterEndTimeLessThan(Literal time) {
		Expr exprLessThan = new E_LessThan(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprLessThan);
	}
	/**
	 * Method to create a filter for end time greater than a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time greater than the value of time.
	 */
	private  ElementFilter createFilterEndTimeGreaterThan(Literal time) {
		Expr exprGreaterThan = new E_GreaterThan(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprGreaterThan);
	}
	/**
	 * Method to create a filter for end time greater than or equal to a value.
	 * 
	 * @param xsd:dateTime
	 *            typed Literal representing the time.
	 * @return Filter for end time greater than or equal to the value of time.
	 */
	private  ElementFilter createFilterEndTimeGreaterThanOrEqual(Literal time) {
		Expr exprGreaterThanOrEqual = new E_GreaterThanOrEqual(new ExprVar(HLCA.endTimeSparqlVar),
				new NodeValueDT(time.getValue().toString(), time.asNode()));
		return new ElementFilter(exprGreaterThanOrEqual);
	}
	/**
	 * Method to create a filter for "not exists" end time in High Level
	 * Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end time in High Level Context.
	 */
	private  ElementFilter createFilterNotExistsEndTimeInHlc(ContextOntology ont) {
		Triple pattern = Triple.create(Var.alloc(HLCA.hlcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock negation = new ElementTriplesBlock();
		negation.addTriple(pattern);
		Expr exprNeg = new E_NotExists(negation);
		return new ElementFilter(exprNeg);
	}
	/**
	 * Method to create a filter for "not exists" end time in Low Level Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end time in Low Level Context.
	 */
	private  ElementFilter createFilterNotExistsEndTimeInLlc(ContextOntology ont) {
		Triple pattern = Triple.create(Var.alloc(HLCA.llcSparqlVar), ont.getEndTimeProp().asNode(),
				Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock negation = new ElementTriplesBlock();
		negation.addTriple(pattern);
		Expr exprNeg = new E_NotExists(negation);
		return new ElementFilter(exprNeg);
	}
	/**
	 * Method to create a filter for "not exists" end adjacent posterior Low
	 * Level Context.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @return Filter for "not exists" end adjacent posterior Low Level Context.
	 */
	private  ElementFilter createFilterNotExistsAdjacentLlc(ContextOntology ont) {
		String adj = "Adjacent";
		Triple pattern1 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar + adj));
		Triple pattern2 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar + adj), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern3 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getContextOfProp().asNode(), Var.alloc(HLCA.userSparqlVar));
		Triple pattern4 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getStartTimeProp().asNode(), Var.alloc(HLCA.endTimeSparqlVar));
		Triple pattern5 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), Nodes.type, Var.alloc(HLCA.llcTypeSparqlVar + adj));
		Triple pattern6 = Triple.create(Var.alloc(HLCA.llcTypeSparqlVar + adj), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcType1SparqlVar + adj));
		Triple pattern7 = Triple.create(Var.alloc(HLCA.llcType1SparqlVar + adj), com.hp.hpl.jena.vocabulary.RDFS.Nodes.subClassOf, Var.alloc(HLCA.llcCategorySparqlVar));
		Triple pattern8 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getContextOfProp().asNode(), Var.alloc(HLCA.userSparqlVar));
		Triple pattern9 = Triple.create(Var.alloc(HLCA.llcSparqlVar + adj), ont.getStartTimeProp().asNode(), Var.alloc(HLCA.endTimeSparqlVar));
		ElementTriplesBlock negationa = new ElementTriplesBlock();
		ElementTriplesBlock negationb = new ElementTriplesBlock();
		negationa.addTriple(pattern1);
		negationa.addTriple(pattern2);
		negationa.addTriple(pattern3);
		negationa.addTriple(pattern4);
		negationb.addTriple(pattern5);
		negationb.addTriple(pattern6);
		negationb.addTriple(pattern7);
		negationb.addTriple(pattern8);
		negationb.addTriple(pattern9);
		ElementUnion union = new ElementUnion();
		union.addElement(negationa);
		union.addElement(negationb);
		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcSparqlVar), new ExprVar(HLCA.llcSparqlVar + adj));
		ElementFilter notEqual = new ElementFilter(exprNotEquals);
		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(notEqual);
		Expr exprNeg = new E_NotExists(body);
		return new ElementFilter(exprNeg);
	}
	/**
	 * Method to create a filter for "not equals" to a given Low Level Context.
	 * 
	 * @param llc
	 *            Low Level Context for which the matching one has to be not
	 *            equal.
	 * @return Filter for "not equals" to llc.
	 */
	private  ElementFilter createFilterLlcNotEquals(Individual llc) {
		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcSparqlVar), NodeValue.makeNode(llc.asNode()));
		return new ElementFilter(exprNotEquals);
	}
	/**
	 * Method to create a filter for "not equals" to a Low Level Context
	 * category, e.g. Activity, Emotion or Location.
	 * 
	 * @param llcCategory
	 *            OntClass representing the category of Low Level Context in the
	 *            Context Ontolog, which does not have to match.
	 * @return Filter for "not equals" to the llcCategory.
	 */
	private  ElementFilter createFilterLlcCategoryNotEquals(OntClass llcCategory) {
		Expr exprNotEquals = new E_NotEquals(new ExprVar(HLCA.llcCategorySparqlVar),
				NodeValue.makeNode(llcCategory.asNode()));
		return new ElementFilter(exprNotEquals);
	}
	/**
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
	 */
	private  Query createHlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2) {  
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
	/**
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
	 */
		private  Query createLlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2) {
		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter1);
		body.addElement(filter2);
		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.addResultVar(HLCA.llcSparqlVar);
		return q;
	}
	/**
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
	 */
	private  Query createLlcQuery(ElementUnion union, ElementFilter filter1, ElementFilter filter2,
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
	/**
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
	 */
	private  Query createLlcQuery(ElementUnion union , ElementFilter filter1, ElementFilter filter2, 
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
	/**
	 * Method to create a select SPARQL query for the minimum start time of a
	 * context.
	 * 
	 * @param block
	 *            Triples block to be part of the SPARQL query.
	 * @param filter
	 *            Filter to be part of the SPARQL query.
	 * @return A select SPARQL query for the minimum start time of a context.
	 */
	private  Query createMinStartTimeQuery(ElementUnion union , ElementFilter filter) {  
		ElementGroup body = new ElementGroup();
		body.addElement(union);
		body.addElement(filter);
		Query q = QueryFactory.make();
		q.setQueryPattern(body);
		q.setQuerySelectType();
		q.allocAggregate(AggregatorFactory.createMin(false, new ExprVar(HLCA.startTimeSparqlVar)));
		return q;
	}
	private ContextQueryGenerator(){
		
	}
	public static ContextQueryGenerator getContextQueryGenerator(){
		if(contextQueryGenerator == null){
			contextQueryGenerator = new ContextQueryGenerator();
		}
		return contextQueryGenerator;
	}
}
