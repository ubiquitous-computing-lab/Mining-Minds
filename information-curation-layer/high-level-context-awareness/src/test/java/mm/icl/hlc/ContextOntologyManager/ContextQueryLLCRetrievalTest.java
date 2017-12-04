package mm.icl.hlc.ContextOntologyManager;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.OntologyTools.LowLevelContext;


import org.junit.Test;

public class ContextQueryLLCRetrievalTest {
	static final String directory ="E:\\ICL_LOG\\TDB";  
	

		
		static final String sparqlQueryLlc = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX icl2: <http://www.miningminds.re.kr/icl/context/context-v2-5.owl#> "
				+ "SELECT ?llc "
				+ "WHERE { "

				+ "{?llc rdf:type ?c. "
				+ "?c rdfs:subClassOf ?type . "                              
				+ "?type rdfs:subClassOf icl2:LowLevelContext.} "

				+ "UNION "
							
				+ "{?llc rdf:type ?c. "
				+ "?c rdfs:subClassOf ?type . "                              
				+ "?type rdfs:subClassOf ?type1 . "                          			
				+ "?type1 rdfs:subClassOf icl2:LowLevelContext.} "
				

			
			+ "}";
				
		
	
		public static void main(String[] args) {

	}
	@Test
	public final void test() {
		 long llc_retr_start = System.currentTimeMillis();	
		ContextOntologyManager mng = new ContextOntologyManager(directory);
		
		if(mng.correctInitialization()) {
			
			List<LowLevelContext> llcList = (List<LowLevelContext>)(List<?>) ContextHandler.retrieveContextInstancesMatchingSparqlQuery(sparqlQueryLlc);
			
			Iterator<LowLevelContext> it = llcList.iterator();
			
			System.out.println("I m here in Testing");
			
			System.out.println(it.toString());
			
			
			while(it.hasNext()) {
				
				System.out.println("------------------------");
				
				LowLevelContext inst = it.next();

				inst.getCtxModel().write(System.out, "Turtle");
			
		}
			
			long elapsed = System.currentTimeMillis() - llc_retr_start;
			DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds', SSS  'milliseconds'");
			df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
			System.out.println("Time taken to retrieve LLC" + df.format(new Date(elapsed)));
		
	}
	else  {
		System.out.println("No LLC Exists First Add LLC by Running _________");
	}
}

}
