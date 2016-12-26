package org.uclab.mm.icl.hlca;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import org.uclab.mm.icl.hlc.HLC.TimeUtil;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextInstantiator;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextSynchronizer;
import org.uclab.mm.icl.hlc.HLCNotifier.HLCNotifier;
import org.uclab.mm.icl.hlc.HLCReasoner.HLCReasoner;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestHLCA {
	
	static final String directory ="D:\\TDB"; 		                  	             
	static final String ontologyFile = "file:////D:/context-v2-5.owl";                //"file:context-v2.owl";
	
	static final int windowSizeInSec = 15;  //15 orignal
	
	static final int simulationTimeInSec = 240;  //240 orignal

	// Simulation start time
	static final int year = 2016; 
	static final int month = 4;  
	static final int day = 18;
	static final int hour = 10; 
	static final int minute = 6; 
	static final int second = 50;    //2016 04 18 10:08:50 
	
	
	public static void main(String[] args) {
		 long hlc_start = System.currentTimeMillis();
		// READ THE CONTEXT ONTOLOGY FROM FILE
		 OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_DL_MEM );   
		 ontModel.read(ontologyFile);
				
		// CREATE THE CONTEXT ONTOLOGY MANAGER
		 ContextOntologyManager mng = new ContextOntologyManager(directory, ontModel);
				
		// IF THE CONTEXT ONTOLOGY HAS ALREADY BEEN DEPLOYED TO TDB, THERE IS NO NEED TO LOAD IT FROM FILE
		// ContextOntologyManager mng = new ContextOntologyManager(directory);
		
		if(mng.correctInitialization()) {
			
			// CREATE THE HLC BUILDER (Context Synchronizer and Context Instantiator)
			
			// CREATE THE HLC REASONER
			HLCReasoner r = new HLCReasoner(mng.getInferredContextOntology());
			
			// CREATE THE HLC NOTIFIER
			HLCNotifier n = new HLCNotifier(mng.getContextOntology());
			ContextInstantiator inst = new ContextInstantiator(mng.getContextOntology());
			ContextSynchronizer sync = new ContextSynchronizer(mng.getContextOntology(), inst, r, n, null);
			
	
			// SIMULATE HLC BUILDER
			TimeUtil util= new TimeUtil();
			Calendar windowStart = util.parseString("2016 04 18 10:06:50");/*GregorianCalendar.getInstance();  //Asif ("2015 12 24 12:59:55")
			windowStart.set(year, month, day, hour, minute, second);  */
			
			Calendar simulationEnd = GregorianCalendar.getInstance();
			simulationEnd.setTime(windowStart.getTime());;
			simulationEnd.add(Calendar.SECOND, simulationTimeInSec);  
			
			while(windowStart.before(simulationEnd)){
				
				Calendar windowEnd = GregorianCalendar.getInstance();
				windowEnd.setTime(windowStart.getTime());;
				windowEnd.add(Calendar.SECOND, windowSizeInSec);   
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------------------------------------");
				System.out.println("Synchronizing for window (" + windowStart.getTime() + ", " + windowEnd.getTime() + "]");
				
				// SYNCHRONIZE
				sync.synchronizeTest(windowStart, windowEnd, r, n);
				// sync.synchronizeLlcInWindow(windowStart, windowEnd);
				
				windowStart.setTime(windowEnd.getTime());
				
			}
			
			
		}
		long elapsed = System.currentTimeMillis() - hlc_start;
		DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds', SSS  'milliseconds'");
		df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		System.out.println("Time taken to Reason HLC: " + df.format(new Date(elapsed)));
	
	}

}
