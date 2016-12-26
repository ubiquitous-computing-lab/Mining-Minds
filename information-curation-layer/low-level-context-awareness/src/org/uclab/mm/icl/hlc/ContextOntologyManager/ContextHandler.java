/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.ContextOntologyManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.uclab.mm.icl.hlc.OntologyTools.Context;
import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.HLCA;
import org.uclab.mm.icl.hlc.OntologyTools.LowLevelContext;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * Context Handler: Subcomponent of the Context Ontology Manager which deals
 * with the retrieval and storage of Context instances from/in the Context
 * Ontology Storage.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */
public class ContextHandler {

	/**
	 * Collection of Context Instances and Context Ontology stored in the
	 * Context Ontology Storage.
	 */
	private static Dataset dataset;

	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private static ContextOntology ont;

	/**
	 * Method to initialize the Context Handler.
	 * 
	 * @param directory
	 *            Path to the directory where the Context Ontology Storage is
	 *            deployed.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public static void initialize(String directory, ContextOntology ont) {

		ContextHandler.dataset = TDBFactory.createDataset(directory);
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		ContextHandler.ont = ont;

	}

	/**
	 * Method to retrieve the previous instance of High Level Context which is
	 * persisted in the Context Ontology Storage and to save the newly inferred
	 * High Level Context instance.
	 * 
	 * @param newHlc
	 *            Instance of High Level Context which has been newly inferred.
	 * @return High Level Context instance which is previous to newHlc.
	 */
	public static PhysicalActivityContext retrievePreviousHlcAndStoreNew(PhysicalActivityContext newHlc) {

		dataset.begin(ReadWrite.WRITE);

		Query sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndUnfinishedHlc(newHlc, ont);

		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();

		PhysicalActivityContext previousHlc = null;

		if (rs.hasNext()) {
			// There is a HLC which is valid and open (unfinished) at the start
			// time of the newHlc

			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
			OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			previousHlc = new PhysicalActivityContext(previousHlcModel, ont);

			previousHlc.setDataPropertyValue(ont.getEndTimeProp(), newHlc.getDataPropertyValue(ont.getStartTimeProp()));

		}

		else {

			sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndFinishedHlc(newHlc, ont);

			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();

			if (rs.hasNext()) {
				// There is a HLC which was valid at the start time of the
				// newHlc and which finished later

				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
				OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				previousHlc = new PhysicalActivityContext(previousHlcModel, ont);

				newHlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousHlc.getDataPropertyValue(ont.getEndTimeProp()));

				previousHlc.setDataPropertyValue(ont.getEndTimeProp(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

			}

			else {

				// There are no previous instances of HLC in the DB
				// Query to get the StartTime of the next HLC which is posterior
				// to the new Instance StarTime

				sparqlQuery = ContextQueryGenerator.generateQueryForStartTimeOfNextHlc(newHlc, ont);

				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();

				if (rs.hasNext()) {

					QuerySolution soln = rs.next();

					Iterator<String> it = soln.varNames();

					if (it.hasNext())
						// There is future instance of HLC in the DB with the
						// retrieved StartTime

						newHlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));

				}

			}

		}

		// store new HLC Instance
		dataset.addNamedModel(newHlc.getCtxInstanceName(), newHlc.getCtxModel());

		dataset.commit();

		dataset.end();

		return previousHlc;

	}

	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made Start **********/////////////////////////////////////
	
	public static NutritionContext retrievePreviousHlcAndStoreNew(NutritionContext newHlc) {

		dataset.begin(ReadWrite.WRITE);

		Query sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndUnfinishedHlc(newHlc, ont);

		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();

		NutritionContext previousHlc = null;

		if (rs.hasNext()) {
			// There is a HLC which is valid and open (unfinished) at the start
			// time of the newHlc

			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
			OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			previousHlc = new NutritionContext(previousHlcModel, ont);

			previousHlc.setDataPropertyValue(ont.getEndTimeProp(), newHlc.getDataPropertyValue(ont.getStartTimeProp()));

		}

		else {

			sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndFinishedHlc(newHlc, ont);

			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();

			if (rs.hasNext()) {
				// There is a HLC which was valid at the start time of the
				// newHlc and which finished later

				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
				OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				previousHlc = new NutritionContext(previousHlcModel, ont);

				newHlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousHlc.getDataPropertyValue(ont.getEndTimeProp()));

				previousHlc.setDataPropertyValue(ont.getEndTimeProp(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

			}

			else {

				// There are no previous instances of HLC in the DB
				// Query to get the StartTime of the next HLC which is posterior
				// to the new Instance StarTime

				sparqlQuery = ContextQueryGenerator.generateQueryForStartTimeOfNextHlc(newHlc, ont);

				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();

				if (rs.hasNext()) {

					QuerySolution soln = rs.next();

					Iterator<String> it = soln.varNames();

					if (it.hasNext())
						// There is future instance of HLC in the DB with the
						// retrieved StartTime

						newHlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));

				}

			}

		}

		// store new HLC Instance
		dataset.addNamedModel(newHlc.getCtxInstanceName(), newHlc.getCtxModel());

		dataset.commit();

		dataset.end();

		return previousHlc;

	}

	

	
	
	
	
	
	
	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made End   **********/////////////////////////////////////	
	/**
	 * Method to store the newly mapped Low Level Context instance into the
	 * Context Ontology Storage.
	 * 
	 * @param newLlc
	 *            Instance of Low Level Context which has been newly mapped and
	 *            has to be stored.
	 */
	public static void storeNewLlc(LowLevelContext newLlc) {

		dataset.begin(ReadWrite.WRITE);

		Query sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndUnfinishedLlc(newLlc, ont);

		
		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();
		
		if (rs.hasNext()) {
			// There is a LLC which is valid and open (unfinished) at the start
			// time of the newLlc

			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
			
			OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);

			previousLlc.setDataPropertyValue(ont.getEndTimeProp(), newLlc.getDataPropertyValue(ont.getStartTimeProp()));
		}

		else {

			sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndFinishedLlc(newLlc, ont);

			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();

			if (rs.hasNext()) {
				// There is a LLC which was valid at the start time of the
				// newLlc and which finished later

				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
				OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);

				newLlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousLlc.getDataPropertyValue(ont.getEndTimeProp()));

				previousLlc.setDataPropertyValue(ont.getEndTimeProp(),
						newLlc.getDataPropertyValue(ont.getStartTimeProp()));

			}

			else {

				// There are no previous instances of LLC in the DB
				// Query to get the StartTime of the next HLC which is posterior
				// to the new Instance StarTime

				sparqlQuery = ContextQueryGenerator.generateQueryForStartTimeOfNextLlc(newLlc, ont);

				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();

				if (rs.hasNext()) {

					QuerySolution soln = rs.next();

					Iterator<String> it = soln.varNames();

					if (it.hasNext())
						// There is future instance of LLC in the DB with the
						// retrieved StartTime

						newLlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));

				}

			}

		}

		// store new HLC Instance
		dataset.addNamedModel(newLlc.getCtxInstanceName(), newLlc.getCtxModel());
		dataset.commit();
		dataset.end();

	}

	/**
	 * Method to finalize, i.e., to set the end time of, the previous Low Level
	 * Context instance stored in the Context Ontology Storage.
	 * 
	 * @param category
	 *            Ontology class defining the category (e.g., location, emotion
	 *            or location) of the LLC which should be finalized.
	 * @param user
	 *            Ontology individual representing the user for which the LLC
	 *            should be finalized.
	 * @param time
	 *            Literal describing the end time that should be set to the
	 *            stored LLC.
	 */
	public static void finalizePreviousLlc(OntClass category, Resource user, Literal time) {

		dataset.begin(ReadWrite.WRITE);

		Query sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndUnfinishedLlc(category, user, time,
				ont);

		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();

		if (rs.hasNext()) {
			// There is a LLC which is valid and open (unfinished) at the start
			// time of the newLlc

			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
			OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);

			previousLlc.setDataPropertyValue(ont.getEndTimeProp(), time);

		}

		else {

			sparqlQuery = ContextQueryGenerator.generateQueryForPreviousValidAndFinishedLlc(category, user, time, ont);

			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();

			if (rs.hasNext()) {
				// There is a LLC which was valid at the start time of the
				// newLlc and which finished later

				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
				OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);

				previousLlc.setDataPropertyValue(ont.getEndTimeProp(), time);

			}

		}

		dataset.commit();

		dataset.end();

	}

	/**
	 * Method to retrieve from the Context Ontology Storage the Low Level
	 * Context instances which are concurrent at the start time of a Low Level
	 * Context instance.
	 * 
	 * @param llc
	 *            Instance of Low Level Context.
	 * @return List of Low Level Context Instances which are concurrent at the
	 *         start time of llc.
	 */
	public static List<LowLevelContext> retrieveConcurrentLlcAtStartTime(LowLevelContext llc) {

		Query sparqlQuery = ContextQueryGenerator.generateQueryForConcurrentAtStartTimeUnfinishedLlc(llc, ont);
		ResultSet results = executeSparqlQuery(sparqlQuery);

		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);

		sparqlQuery = ContextQueryGenerator.generateQueryForConcurrentAtStartTimeFinishedLlc(llc, ont);
		results = executeSparqlQuery(sparqlQuery);

		
		llcList.addAll((List<LowLevelContext>) (List<?>) getContextForSparqlResults(results));

		return llcList;
	}

	/**
	 * Method to retrieve from the Context Ontology Storage the Low Level
	 * Context instances which are concurrent at the end time of a Low Level
	 * Context instance.
	 * 
	 * @param llc
	 *            Instance of Low Level Context.
	 * @return List of Low Level Context Instances which are concurrent at the
	 *         end time of llc.
	 */
	public static List<LowLevelContext> retrieveConcurrentLlcAtEndTime(LowLevelContext llc) {

		Query sparqlQuery = ContextQueryGenerator.generateQueryForConcurrentAtEndTimeUnfinishedLlc(llc, ont);

		ResultSet results = executeSparqlQuery(sparqlQuery);

		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);

		sparqlQuery = ContextQueryGenerator.generateQueryForConcurrentAtEndTimeFinishedLlc(llc, ont);

		results = executeSparqlQuery(sparqlQuery);

		llcList.addAll((List<LowLevelContext>) (List<?>) getContextForSparqlResults(results));

		return llcList;

	}

	/**
	 * Method to retrieve from the Context Ontology Storage all the Low Level
	 * Context instances which have their start time within a time window.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window.
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the end time of the
	 *            window.
	 * @return List of Low Level Context instances which have their start time
	 *         within the time window
	 */
	
	// Asif MMV2.5 for Additional Low Level Context
	public static List<LowLevelContext> retrieveLlcStartingWithinWindow(Literal windowStart, Literal windowEnd) {

		//     HERE IS THE WORKING TESTCASE TESTING
		
		
		Query sparqlQuery = ContextQueryGenerator.generateQueryForLlcWithStartTimeInWindow(windowStart, windowEnd, ont);
		ResultSet results = executeSparqlQuery(sparqlQuery);
		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);
		
// INTRODUCE LIST HERE
		
/*		sparqlQuery = ContextQueryGenerator.generateQueryForAddlLlcWithStartTimeInWindow(windowStart, windowEnd, ont);
		results = executeSparqlQuery(sparqlQuery);
		llcList.addAll((List<LowLevelContext>) (List<?>) getContextForSparqlResults(results));
*/		
//		System.out.println("3. I am in Context Handler");
//		System.out.println(results);
		

//		return (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results); //Asif MMV2.5
		return llcList; //Asif MMV2.5

	}

	/**
	 * Method to retrieve from the Context Ontology Storage all the Low Level
	 * Context instances which have their end time within a time window and
	 * which do not have a contiguous Low Level Context instance.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window.
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the end time of the
	 *            window.
	 * @return List of Low Level Context instances which have their end time
	 *         within the time window
	 */
	public static List<LowLevelContext> retrieveLlcEndingWithinWindow(Literal windowStart, Literal windowEnd) {

		Query sparqlQuery = ContextQueryGenerator.generateQueryForLlcWithEndTimeInWindowAndNoContiguousLlc(windowStart,
				windowEnd, ont);

		ResultSet results = executeSparqlQuery(sparqlQuery);

		return (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);

	}

	/**
	 * Method to retrieve the Context instances stored in the Context Ontology
	 * Storage which match a SPARQL query.
	 * 
	 * @param sparqlQuery
	 *            SPARQL query which defines the constraints that the Context
	 *            needs to meet.
	 * @return List of Context instances that match the sparqlQuery.
	 */
	public static List<Context> retrieveContextInstancesMatchingSparqlQuery(Query sparqlQuery) {

		ResultSet rs = executeSparqlQuery(sparqlQuery);

		return getContextForSparqlResults(rs);
	}

	/**
	 * Method to retrieve the Context instances stored in the Context Ontology
	 * Storage which match a SPARQL query.
	 * 
	 * @param sparqlQueryString
	 *            String encoding of the SPARQL query which defines the
	 *            constraints that the Context needs to meet.
	 * @return List of Context instances that match the sparqlQueryString.
	 */
	public static List<Context> retrieveContextInstancesMatchingSparqlQuery(String sparqlQueryString) {

		ResultSet rs = executeSparqlQuery(sparqlQueryString);

		return getContextForSparqlResults(rs);
	}

	/**
	 * Method to execute a SPARQL query on the Context instances stored in the
	 * Context Ontology Storage.
	 * 
	 * @param sparqlQuery
	 *            SPARQL query which defines the constraints that the Context
	 *            needs to meet.
	 * @return Set of results that match the sparqlQuery, presented in a
	 *         table-like manner.
	 */
	
	//static AtomicBoolean lock = new AtomicBoolean(true);
	//while(!lock.get()){}
			//lock.set(false);
	//lock.set(true);
	private static ResultSet executeSparqlQuery(Query sparqlQuery) {
		
		dataset.begin(ReadWrite.WRITE);

		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();

		dataset.commit();

		dataset.end();
		
		return rs;

	}

	/**
	 * Method to execute a SPARQL query on the Context instances stored in the
	 * Context Ontology Storage.
	 * 
	 * @param sparqlQueryString
	 *            String encoding of the SPARQL query which defines the
	 *            constraints that the Context needs to meet.
	 * @return Set of results that match the sparqlQueryString, presented in a
	 *         table-like manner.
	 */
	private static ResultSet executeSparqlQuery(String sparqlQueryString) {

		dataset.begin(ReadWrite.WRITE);

		QueryExecution qExec = QueryExecutionFactory.create(sparqlQueryString, dataset);
		ResultSet rs = qExec.execSelect();

		dataset.commit();

		dataset.end();

		return rs;

	}

	/**
	 * Method to get from the Context Ontology Storage the list of Context
	 * instances which are part of the result set of a SPARQL query.
	 * 
	 * @param rs
	 *            Set of results of a SPARQL query, presented in a table-like
	 *            manner.
	 * @return List of Context instances that match the sparqlQuery.
	 */
	private static List<Context> getContextForSparqlResults(ResultSet rs) {

		List<Context> results = new ArrayList<Context>();

		if (rs.getResultVars().contains(HLCA.hlcSparqlVar) && !rs.getResultVars().contains(HLCA.llcSparqlVar)) {

			while (rs.hasNext()) {

				OntModel hlcModel = getContextOntModel(rs.next(), HLCA.hlcSparqlVar);

				if (hlcModel != null)

					results.add(new PhysicalActivityContext(hlcModel, ont));

			}

		}

		else if (!rs.getResultVars().contains(HLCA.hlcSparqlVar) && rs.getResultVars().contains(HLCA.llcSparqlVar)) {

			while (rs.hasNext()) {

				OntModel llcModel = getContextOntModel(rs.next(), HLCA.llcSparqlVar);

				if (llcModel != null)

					results.add(new LowLevelContext(llcModel, ont));

			}

		}

		return results;
	}

	/**
	 * Method to get from the Context Ontology Storage the OntModel associated
	 * to the Context instance which is a solution of the SPARQL query.
	 * 
	 * @param sol
	 *            Solution which is part of the set of results of a SPARQL
	 *            query.
	 * @param varName
	 *            Name of the variable which is part of the results of a SPARQL
	 *            query.
	 * @return OntModel associated to the Context which is a solution of the
	 *         SPARQL query.
	 */
	private static OntModel getContextOntModel(QuerySolution sol, String varName) {

		return getContextOntModel(sol.getResource(varName).getURI());

	}

	/**
	 * Method to get from the Context Ontology Storage the OntModel associated
	 * to the Context instance by its name.
	 * 
	 * @param modelName
	 *            Name of the Context instance.
	 * @return OntModel associated to the Context instance which name is
	 *         modelName.
	 */
	private static OntModel getContextOntModel(String modelName) {

		dataset.begin(ReadWrite.READ);

		Model inst = dataset.getNamedModel(modelName);
		dataset.end();

		if (inst.isEmpty())
			return null;

		return ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
	}

	/**
	 * Method to add to the Context Ontology Storage the OntModel associated to
	 * a Context instance.
	 * 
	 * @param modelName
	 *            Name of the Context instance.
	 * @param model
	 *            OntModel representation of the Context instance.
	 */
	private static void addContextOntModel(String modelName, OntModel model) {

		dataset.begin(ReadWrite.WRITE);

		dataset.addNamedModel(modelName, model);

		dataset.commit();
		dataset.end();

	}

	/**
	 * Method to update in the Context Ontology Storage the OntModel associated
	 * to a Context instance.
	 * 
	 * @param modelName
	 *            Name of the Context instance.
	 * @param model
	 *            OntModel representation of the Context instance.
	 */
	private static void updateContextOntModel(String modelName, OntModel model) {
		// Maybe we want to do some verification first...
		addContextOntModel(modelName, model);
	}

}
