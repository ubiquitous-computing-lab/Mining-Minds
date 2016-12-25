package org.uclab.mm.icl.hlca;

import java.util.Calendar;

import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import org.uclab.mm.icl.hlc.HLC.TimeUtil;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextMapper;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestHLCAMapper {

	static final String directory ="D:\\TDB"; 		                  	             
	static final String ontologyFile = "file:////D:/TDB/context-v2-5.owl";             
	static final String llcLabel = "NoEmotion";                               //"Sitting";"Office" Boredom Office Happiness Mall
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
			
			  Calendar cal = util.parseString("2016 04 18 10:08:31");                       //10:06:50  Calendar.getInstance();  //Asif (from DongUK)
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
	}

}
