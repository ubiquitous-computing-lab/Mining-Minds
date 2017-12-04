/**
* http://stackoverflow.com/questions/37497621/jena-tdb-querying-a-named-model 
* https://github.com/giuseppefutia/lod-hackathon-2015/blob/master/csv-to-rdf/src/main/java/org/dvcama/csvtordf/triplify/Step04.java
* 
Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ext.com.google.common.collect.Lists;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.OntologyTools.Context;

public class HLC_SPARQL {
	
	static final String directory ="E:\\ICL_LOG\\TDB";         
	
/*	if (new File(directory).exists()) {
		for (File file : new File(directory).listFiles()) {
			file.delete();
		}
	} else {
		new File(directory).mkdirs();
	}
	*/
	
	
	static final String sparqlQueryHlc = 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
			+ "PREFIX icl2: <http://www.miningminds.re.kr/icl/context/context-v2-5.owl#> "
			+ "SELECT ?hlc " //count(?hlc) As ?cnt "//?hlc "
			+ "WHERE { "
			+ "?hlc rdf:type icl2:PhysicalActivityContext. " //PhysicalActivityContext NutritionContext
			+ "} ";
//			+ "Group by ?hlc";
		
		
	public static void main(String[] args) {
	
		Dataset dataset = TDBFactory.createDataset(directory);
		dataset.begin(ReadWrite.READ);

		Model model = dataset.getDefaultModel();
		List<String> out = Lists.newArrayList();
		
		QueryExecution qe = QueryExecutionFactory.create(sparqlQueryHlc, dataset)  ;             //("select * {?s ?p ?o}",dataset);
		ResultSet rs = qe.execSelect();
		
		while (rs.hasNext()) {
			System.out.println("in the While");
			QuerySolution qs = rs.next();
			System.out.println(qs);
			String subject = qs.get("s").asNode().toString();
			
			System.out.println(subject);
		}
		System.out.println("End");
		dataset.end();
		dataset.close();
		

		
}
	}		
		
		
		
		
		
		

/*		ContextOntologyManager mng = new ContextOntologyManager(directory);
		
		if(mng.correctInitialization()) {
			Iterator<Context> it = ContextHandler.retrieveContextInstancesMatchingSparqlQuery(sparqlQueryHlc).iterator();
			while(it.hasNext()) {
				//your code that maeks async errors
				    try{
				    	System.out.println("------------------------");
				
				    	Context inst = it.next();
				    	inst.getCtxModel().write(System.out, "RDF/XML");
//						inst.getCtxModel().write(System.out, "Turtle");
				    	Thread.sleep(1);      ///////////////////////////// if want to add delay for visibility 5000
				    
				}
			catch(InterruptedException e){}
			}
		}
		else  {
			System.out.println("No HLC because there are no LLC _________");
		}*/
		
		
		
		
		
		
		
		
		
