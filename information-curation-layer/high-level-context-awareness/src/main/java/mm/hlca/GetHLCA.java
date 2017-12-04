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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.HLC.TimeUtil;
import mm.icl.hlc.HLCBuilder.ContextInstantiator;
import mm.icl.hlc.HLCBuilder.ContextSynchronizer;
import mm.icl.hlc.HLCNotifier.HLCNotifier;
import mm.icl.hlc.HLCReasoner.HLCReasoner;

import org.apache.log4j.Logger;

public class TestHLCA {
	
	final static Logger logger = Logger.getLogger(TestHLCA.class);
	
	
	static final String directory = "E:\\ICL_LOG\\TDB";                                
	static final String ontologyFile = "file:////E:/ICL_LOG/TDB/context-v2-5.owl";     
	static final int windowSizeInSec = 15;  
	static final int simulationTimeInSec = 240; 
	static final int year = 2016; 
	static final int month = 4;  
	static final int day = 18;
	static final int hour = 10; 
	static final int minute = 8; 
	static final int second = 50;    
	public static void main(String[] args) {
		 long hlc_start = System.currentTimeMillis();
		 OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_DL_MEM );   
		 ontModel.read(ontologyFile);

		 ContextOntologyManager mng = new ContextOntologyManager(directory, ontModel);
		 if(mng.correctInitialization()) {
			HLCReasoner r = new HLCReasoner(mng.getInferredContextOntology());
			HLCNotifier n = new HLCNotifier(mng.getContextOntology());
			ContextInstantiator inst = new ContextInstantiator(mng.getContextOntology());
			ContextSynchronizer sync = new ContextSynchronizer(mng.getContextOntology(), inst, r, n, null);
			TimeUtil util= new TimeUtil();
			Calendar windowStart = util.parseString("2016 04 18 10:08:50");
			Calendar simulationEnd = GregorianCalendar.getInstance();
			simulationEnd.setTime(windowStart.getTime());;
			simulationEnd.add(Calendar.SECOND, simulationTimeInSec);  
			while(windowStart.before(simulationEnd)){
				Calendar windowEnd = GregorianCalendar.getInstance();
				windowEnd.setTime(windowStart.getTime());;
				windowEnd.add(Calendar.SECOND, windowSizeInSec);   
				
				logger.info(" ");
				logger.info("-----------------------------------------------------------------------------------------------");
				logger.info("Synchronizing for window (" + windowStart.getTime() + ", " + windowEnd.getTime() + "]");
				sync.synchronizeTest(windowStart, windowEnd, r, n);
				windowStart.setTime(windowEnd.getTime());
			}
		}
		long elapsed = System.currentTimeMillis() - hlc_start;
		DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds', SSS  'milliseconds'");
		df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		System.out.println("Time taken to Reason HLC: " + df.format(new Date(elapsed)));
	}
}
