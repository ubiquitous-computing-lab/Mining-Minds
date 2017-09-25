/*
 * 
Copyright [2016] [name of copyright owner <-Put your name]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package mm.hlca;
import java.util.Calendar;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.HLC.TimeUtil;
import mm.icl.hlc.HLCBuilder.ContextMapper;
import mm.icl.hlc.HLCNotifier.HLCNotifier;

import org.apache.log4j.Logger;

public class TestHLCAMapper {
	final static Logger logger = Logger.getLogger(TestHLCAMapper.class);

	static final String directory ="E:\\ICL_LOG\\TDB"; 		                  	             
	static final String ontologyFile = "file:////E:/ICL_LOG/TDB/context-v2-5.owl";             
	static final String llcLabel = "Sitting";     
	static final String userId = "9735";

	public static void main(String[] args) {
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
			logger.error("Error while Notifying HLC.  Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}




















/*
 * package mm.hlca;
import java.util.Calendar;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.HLC.TimeUtil;
import mm.icl.hlc.HLCBuilder.ContextMapper;
public class TestHLCAMapper {
	static final String directory ="E:\\ICL_LOG\\TDB"; 		                  	             
	static final String ontologyFile = "file:////E:/ICL_LOG/TDB/context-v2-5.owl";             
	static final String llcLabel = "Salmon";                               //"Sitting";"Office" Boredom Office Happiness Mall
	static final String userId = "9735";
	//static final String startTime = "2015-08-10T11:25:50+09:00";
	public static void main(String[] args) {
		// READ THE CONTEXT ONTOLOGY FROM FILE
		OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		ontModel.read(ontologyFile);
		// CREATE THE CONTEXT ONTOLOGY MANAGER
		ContextOntologyManager mng = new ContextOntologyManager(directory, ontModel);
		TimeUtil util = new TimeUtil();
		if (mng.correctInitialization()) {
			// CREATE THE MAPPER
			ContextMapper mapper = new ContextMapper(mng.getContextOntology());
			// MAP LLC
			  Calendar cal = util.parseString("2016 04 18 10:11:25");                       //10:06:50  Calendar.getInstance();  //Asif (from DongUK)
			  mapper.mapLLC(llcLabel, userId, cal);                                         //Asif (from DongUK)
			//mapper.mapLLC(llcLabel, userId, startTime.);
		}
/*            ORignal
 * 
 *       	// CREATE THE MAPPER
			ContextMapper mapper = new ContextMapper(mng.getContextOntology());
			// MAP LLC
			//mapper.mapLLC(llcLabel, userId, startTime);
 * */
	