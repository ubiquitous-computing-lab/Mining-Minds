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
import mm.icl.hlc.OntologyTools.Context;
import mm.icl.hlc.OntologyTools.LowLevelContext;




import org.junit.Test;

public class ContextQueryHLCRetrievalTest {
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

	}
	@Test
	public final void test() {

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
