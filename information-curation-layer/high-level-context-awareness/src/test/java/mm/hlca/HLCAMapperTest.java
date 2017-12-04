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
package mm.hlca;

import static org.junit.Assert.*;
import java.util.Calendar;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.HLC.TimeUtil;
import mm.icl.hlc.HLCBuilder.ContextMapper; 
import org.apache.log4j.Logger;
import org.junit.Test;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class HLCAMapperTest {

	@Test
	public final void testMain() {
		// input parameter for all 3 variables
		final  Logger logger = Logger.getLogger(TestHLCAMapper.class);

		 final String directory ="E:\\ICL_LOG\\TDB"; 		                  	             
		 final String ontologyFile = "file:////E:/ICL_LOG/TDB/context-v2-5.owl";             
		 final String llcLabel = "Cake";     // Give the Low Level Contexts here as per Ontology
		 final String userId = "9735";

		 	OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			ontModel.read(ontologyFile);
			ContextOntologyManager mng = new ContextOntologyManager(directory, ontModel);
			TimeUtil util = new TimeUtil();
			
			try{
			if (mng.correctInitialization()) {
				ContextMapper mapper = new ContextMapper(mng.getContextOntology());

				  Calendar cal = util.parseString("2016 04 18 10:11:25"); 
				  mapper.mapLLC(llcLabel, userId, cal);                   
	}
			} catch (Exception e) {
				logger.error("[Context Mapper] LLC could not be mapped. " + llcLabel + " is not a valid label.");
				e.printStackTrace();
				fail("[Context Mapper] LLC could not be mapped. " + llcLabel + " is not a valid label."); // TODO
			}
	}

}
