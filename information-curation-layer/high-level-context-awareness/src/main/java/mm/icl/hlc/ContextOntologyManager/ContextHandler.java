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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import mm.icl.hlc.OntologyTools.Context;
import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.HLCA;
import mm.icl.hlc.OntologyTools.LowLevelContext;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;

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
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class ContextHandler extends AbstractHandler{
	
	private static Logger logger = Logger.getLogger(ContextHandler.class);
	
	
	/**
	 * Collection of Context Instances and Context Ontology stored in the
	 * Context Ontology Storage.
	 */
	private static ContextHandler contextHandler = null;
	private ContextHandler(){};
	protected ContextQueryGenerator contextQueryGenerator = ContextQueryGenerator.getContextQueryGenerator();   
	
	private static Dataset dataset;
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	
	
	private static ContextOntology ont;
	
	public ContextQueryGenerator getContextQueryGenerator() {
		return contextQueryGenerator;
	}
	public void setContextQueryGenerator(ContextQueryGenerator contextQueryGenerator) {
		this.contextQueryGenerator = contextQueryGenerator;
	}
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
	public  void initialize(String directory, ContextOntology ont) {
	try {
		ContextHandler.dataset = TDBFactory.createDataset(directory);
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		ContextHandler.ont = ont;}
	catch (Exception e)
		{
			logger.error("Error while Initializing ContextHandler.  Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Method to retrieve the previous instance of PhysicalActivity Context which is
	 * persisted in the Context Ontology Storage and to save the newly inferred
	 * PhysicalActivity Context instance.
	 * 
	 * @param newHlc
	 *            Instance of PhysicalActivity Context which has been newly inferred.
	 * @return PhysicalActivity Context instance which is previous to newHlc.
	 */
	public PhysicalActivityContext retrievePreviousHlcAndStoreNew(PhysicalActivityContext newHlc) { 
		
		
		dataset.begin(ReadWrite.WRITE);
		Query sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndUnfinishedHlc(newHlc, ont); 
		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();
		PhysicalActivityContext previousHlc = null;
		
	try {
		if (rs.hasNext()) {
			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
			OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			previousHlc = new PhysicalActivityContext(previousHlcModel, ont);
			previousHlc.setDataPropertyValue(ont.getEndTimeProp(), newHlc.getDataPropertyValue(ont.getStartTimeProp()));
		}
		else {
			sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndFinishedHlc(newHlc, ont);
			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();
			if (rs.hasNext()) {
				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
				OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				previousHlc = new PhysicalActivityContext(previousHlcModel, ont);
				newHlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousHlc.getDataPropertyValue(ont.getEndTimeProp()));
				previousHlc.setDataPropertyValue(ont.getEndTimeProp(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
			}
			else {
				sparqlQuery = contextQueryGenerator.generateQueryForStartTimeOfNextHlc(newHlc, ont); 
				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();
				if (rs.hasNext()) {
					QuerySolution soln = rs.next();
					Iterator<String> it = soln.varNames();
					if (it.hasNext())
						newHlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));
				}
			}
		}
		}

		catch (Exception e) {
								logger.error("Error while Processing PhysicalActivityHLCContext.  Message: " + e.getMessage());
								e.printStackTrace();
							}
		dataset.addNamedModel(newHlc.getCtxInstanceName(), newHlc.getCtxModel());
		dataset.commit();
		dataset.end();
		return previousHlc;
	}
	/**
	 * Method to retrieve the previous instance of Nutrition Context which is
	 * persisted in the Context Ontology Storage and to save the newly inferred
	 * Nutrition Context instance.
	 * 
	 * @param newHlc
	 *            Instance of Nutrition Context which has been newly inferred.
	 * @return Nutrition Context instance which is previous to newHlc.
	 */
	public NutritionContext retrievePreviousHlcAndStoreNew(NutritionContext newHlc) {     
		dataset.begin(ReadWrite.WRITE);
		Query sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndUnfinishedHlc(newHlc, ont);
		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();
		NutritionContext previousHlc = null;
	try{	
		if (rs.hasNext()) {
			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
			OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			previousHlc = new NutritionContext(previousHlcModel, ont);
			previousHlc.setDataPropertyValue(ont.getEndTimeProp(), newHlc.getDataPropertyValue(ont.getStartTimeProp()));
		}
		else {
			sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndFinishedHlc(newHlc, ont);
			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();
			if (rs.hasNext()) {
				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.hlcSparqlVar).getURI());
				OntModel previousHlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				previousHlc = new NutritionContext(previousHlcModel, ont);
				newHlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousHlc.getDataPropertyValue(ont.getEndTimeProp()));
				previousHlc.setDataPropertyValue(ont.getEndTimeProp(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
			}
			else {
				sparqlQuery = contextQueryGenerator.generateQueryForStartTimeOfNextHlc(newHlc, ont);
				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();
				if (rs.hasNext()) {
					QuerySolution soln = rs.next();
					Iterator<String> it = soln.varNames();
					if (it.hasNext())
						newHlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));
				}
			}
		}
	}
	
	catch (Exception e) {
		logger.error("Error while Processing retrievePreviousHlcAndStoreNew Nutrition Context.  Message: " + e.getMessage());
		e.printStackTrace();
	}
		dataset.addNamedModel(newHlc.getCtxInstanceName(), newHlc.getCtxModel());
		dataset.commit();
		dataset.end();
		return previousHlc;
	}
	/**
	 * Method to store the newly mapped Low Level Context instance into the
	 * Context Ontology Storage.
	 * 
	 * @param newLlc
	 *            Instance of Low Level Context which has been newly mapped and
	 *            has to be stored.
	 */
	public  void storeNewLlc(LowLevelContext newLlc) {
		dataset.begin(ReadWrite.WRITE);
		Query sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndUnfinishedLlc(newLlc, ont);
		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();
	try{	
		if (rs.hasNext()) {
			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
			OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);
			previousLlc.setDataPropertyValue(ont.getEndTimeProp(), newLlc.getDataPropertyValue(ont.getStartTimeProp()));
		}
		else {
			sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndFinishedLlc(newLlc, ont);
			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();
			if (rs.hasNext()) {
				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
				OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);
				newLlc.setDataPropertyValue(ont.getEndTimeProp(),
						previousLlc.getDataPropertyValue(ont.getEndTimeProp()));
				previousLlc.setDataPropertyValue(ont.getEndTimeProp(),
						newLlc.getDataPropertyValue(ont.getStartTimeProp()));
			}
			else {
				sparqlQuery = contextQueryGenerator.generateQueryForStartTimeOfNextLlc(newLlc, ont);
				qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
				rs = qExec.execSelect();
				if (rs.hasNext()) {
					QuerySolution soln = rs.next();
					Iterator<String> it = soln.varNames();
					if (it.hasNext())
						newLlc.setDataPropertyValue(ont.getEndTimeProp(), soln.getLiteral(it.next()));
				}
			}
		}
	}
	catch (Exception e) {
		logger.error("Error while Storing New LLC.  Message: " + e.getMessage());
		e.printStackTrace();
	}
		dataset.addNamedModel(newLlc.getCtxInstanceName(), newLlc.getCtxModel());
		dataset.commit();
		dataset.end();
	}
	/**
	 * Method to finalize, i.e., to set the end time of, the previous Low Level
	 * Context instance stored in the Context Ontology Storage.
	 * 
	 * @param category
	 *            Ontology class defining the category (e.g., location, emotion,
	 *            food or location) of the LLC which should be finalized.
	 * @param user
	 *            Ontology individual representing the user for which the LLC
	 *            should be finalized.
	 * @param time
	 *            Literal describing the end time that should be set to the
	 *            stored LLC.
	 */
	public void finalizePreviousLlc(OntClass category, Resource user, Literal time) {
		dataset.begin(ReadWrite.WRITE);
		Query sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndUnfinishedLlc(category, user, time,
				ont);
		QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
		ResultSet rs = qExec.execSelect();
	try {	
		if (rs.hasNext()) {
			Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
			OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
			LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);
			previousLlc.setDataPropertyValue(ont.getEndTimeProp(), time);
		}
		else {
			sparqlQuery = contextQueryGenerator.generateQueryForPreviousValidAndFinishedLlc(category, user, time, ont);
			qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
			rs = qExec.execSelect();
			if (rs.hasNext()) {
				Model inst = dataset.getNamedModel(rs.next().getResource(HLCA.llcSparqlVar).getURI());
				OntModel previousLlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				LowLevelContext previousLlc = new LowLevelContext(previousLlcModel, ont);
				previousLlc.setDataPropertyValue(ont.getEndTimeProp(), time);
			}
		}
		
	}
	catch (Exception e) {
		logger.error("Error while finalizing Previous LLC.  Message: " + e.getMessage());
		e.printStackTrace();
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
	public  List<LowLevelContext> retrieveConcurrentLlcAtStartTime(LowLevelContext llc) {
		Query sparqlQuery = contextQueryGenerator.generateQueryForConcurrentAtStartTimeUnfinishedLlc(llc, ont);
		ResultSet results = executeSparqlQuery(sparqlQuery);
		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);
		sparqlQuery = contextQueryGenerator.generateQueryForConcurrentAtStartTimeFinishedLlc(llc, ont);
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
	public List<LowLevelContext> retrieveConcurrentLlcAtEndTime(LowLevelContext llc) {
		Query sparqlQuery = contextQueryGenerator.generateQueryForConcurrentAtEndTimeUnfinishedLlc(llc, ont);
		ResultSet results = executeSparqlQuery(sparqlQuery);
		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);
		sparqlQuery = contextQueryGenerator.generateQueryForConcurrentAtEndTimeFinishedLlc(llc, ont);
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
	public List<LowLevelContext> retrieveLlcStartingWithinWindow(Literal windowStart, Literal windowEnd) {
		Query sparqlQuery = contextQueryGenerator.generateQueryForLlcWithStartTimeInWindow(windowStart, windowEnd, ont);
		ResultSet results = executeSparqlQuery(sparqlQuery);
		List<LowLevelContext> llcList = (List<LowLevelContext>) (List<?>) getContextForSparqlResults(results);
		return llcList;
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
	public List<LowLevelContext> retrieveLlcEndingWithinWindow(Literal windowStart, Literal windowEnd) {
		Query sparqlQuery = contextQueryGenerator.generateQueryForLlcWithEndTimeInWindowAndNoContiguousLlc(windowStart,
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
		addContextOntModel(modelName, model);
	}
	
	public static ContextHandler getContextHandler(){
		if(contextHandler == null){
			contextHandler = new ContextHandler();
		}
		return contextHandler;
	}
}
