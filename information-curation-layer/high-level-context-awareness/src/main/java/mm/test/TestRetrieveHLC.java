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
package mm.test;

import java.util.Iterator;

import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.OntologyTools.Context;

public class TestRetrieveHLC {
	
	static final String directory ="E:\\ICL_LOG\\TDB";         
	
	static final String sparqlQueryHlc = 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
			+ "PREFIX icl2: <http://www.miningminds.re.kr/icl/context/context-v2-5.owl#> "
			+ "SELECT ?hlc "
			+ "WHERE { "
			+ "?hlc rdf:type icl2:PhysicalActivityContext. "
			+ "}";
		
		
	public static void main(String[] args) {
	

		ContextOntologyManager mng = new ContextOntologyManager(directory);
		
		if(mng.correctInitialization()) {
		
			Iterator<Context> it = ContextHandler.retrieveContextInstancesMatchingSparqlQuery(sparqlQueryHlc).iterator();
	
			
			while(it.hasNext()) {
				
				System.out.println("------------------------");
				
				Context inst = it.next();
//				inst.getCtxModel().write(System.out, "RDF/XML");
				inst.getCtxModel().write(System.out, "Turtle");
			}
		}
		else  {
			System.out.println("No HLC because there are no LLC _________");
		}
	}
}