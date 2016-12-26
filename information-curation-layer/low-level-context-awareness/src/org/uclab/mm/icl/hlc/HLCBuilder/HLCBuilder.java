/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import org.uclab.mm.icl.hlc.HLCNotifier.HLCNotifier;
import org.uclab.mm.icl.hlc.HLCReasoner.HLCReasoner;
import org.uclab.mm.icl.llc.config.ICLConfig;
import org.uclab.mm.icl.utils.FileUtil;

/**
 *	HLCBuilder class includes synchronizing process of high level context.
 *	it periodically synchronized low level contexts (within fixed window of time)
 *	and generates appropriate HLC with ContextSynchronizer class.
 * @author Nailbrainz
 */
public class HLCBuilder {
	private final static Logger logger = Logger.getLogger(HLCBuilder.class);
    ContextInstantiator inst;
    ContextSynchronizer sync;
    HLCReasoner r;
	HLCNotifier n;
	CxtSyncCounter counter;
	
    public HLCBuilder(ContextOntologyManager contextMng, ExecutorService es){
    	inst = new ContextInstantiator(contextMng.getContextOntology());
		r = new HLCReasoner(contextMng.getInferredContextOntology());
		n = new HLCNotifier(contextMng.getContextOntology());
		
		sync = new ContextSynchronizer(contextMng.getContextOntology(), inst, r, n, es);
		counter = new CxtSyncCounter(3);
    }
    
    
    public HLCNotifier getNotifier(){
    	return n;
    }
    
    public CxtSyncCounter getCounterRunnable(){
    	return counter;
    }
    
    class CxtSyncCounter implements Runnable {
		int windowSize;
		
		public CxtSyncCounter(int windowSize){
			this.windowSize = windowSize;
		}
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			
			
			synchronized(this){
				Calendar windowStart, windowEnd;
				windowStart = Calendar.getInstance();
				int delay = ICLConfig.HLCDelay; //17이었음 받고 48
				windowStart.add(Calendar.SECOND, -(delay));
				while(true){
					
					
					try {
						 
						// SYNCHRONIZE
						//sync.synchronizeLlcInWindow(windowStart, windowEnd);
						
						wait(windowSize*1000);
						
						windowEnd = Calendar.getInstance();
						windowEnd.add(Calendar.SECOND, -(delay));
						logger.info("Synchronizing for window (" + windowStart.get(Calendar.MINUTE) + ":" +windowStart.get(Calendar.SECOND) + ":" +windowStart.get(Calendar.MILLISECOND) + ", "  + windowEnd.get(Calendar.MINUTE) + ":"+ windowEnd.get(Calendar.SECOND) + ":" +windowEnd.get(Calendar.MILLISECOND) + ")");
						//System.out.println(FileUtil.getPath());
						/*
						System.out.println(FileUtil.getPath());
						System.out.println(FileUtil.getPathAno());
						
						FileUtil.WriteHLCLog("Synchronizing for window (" + windowStart.getTime() + ", " + windowEnd.getTime() + ")");
						*/		
						sync.synchronizeTest(windowStart, windowEnd, r, n);
						windowStart = windowEnd;
						
					} catch (InterruptedException e) {
						logger.error("InterruptedException exception called at hlc syncing (3sec period)");
						logger.error(e);
						// TODO Auto-generated catch block
					}
				}
			}
			
		}

    }
}
