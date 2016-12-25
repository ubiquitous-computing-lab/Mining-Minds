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
package mm.icl.hlc.HLCBuilder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
import java.util.concurrent.ExecutorService;
import mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import mm.icl.hlc.HLCNotifier.HLCNotifier;
import mm.icl.hlc.HLCReasoner.HLCReasoner;
import mm.icl.utils.FileUtil;
/**
 * HLCBuilder: HLC Builder which aligns concurrent
 * low-level contexts of the same user.
 * 
 * @author Nailbrainz
 * @version 2.5
 * @since 2015-11-06
 */
public class HLCBuilder {
	private static Logger logger = Logger.getLogger(HLCBuilder.class);
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
			synchronized(this){
				Calendar windowStart, windowEnd;
				windowStart = Calendar.getInstance();
				int delay = 17;
				windowStart.add(Calendar.SECOND, -(delay));
				while(true){
					try {
						wait(windowSize*1000);
						windowEnd = Calendar.getInstance();
						windowEnd.add(Calendar.SECOND, -(delay));
						logger.info("Synchronizing for window (" + windowStart.get(Calendar.SECOND) + ":" +windowStart.get(Calendar.MILLISECOND) + ", " + windowEnd.get(Calendar.SECOND) + ":" +windowEnd.get(Calendar.MILLISECOND) + ")");
						logger.info("Synchronizing for window (" + windowStart.getTime() + ", " + windowEnd.getTime() + ")");
						sync.synchronizeTest(windowStart, windowEnd, r, n);
						windowStart = windowEnd;
					} catch (InterruptedException e) {
						logger.error("Error while Processing HLCBuilder.  Message: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
    }
}