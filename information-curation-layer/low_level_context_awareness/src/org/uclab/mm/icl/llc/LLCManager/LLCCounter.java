package org.uclab.mm.icl.llc.LLCManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.llc.config.ContextType;

/*
 Copyright [2016] [Dong Uk, Kang]

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

/**
 * This class is counter class which is required in the recognition process of low level context.
 * With this runnable class, each recognized context are unified in predefined window period.
 * @author Nailbrainz
 *
 */
public class LLCCounter implements Runnable{
	String startTime = null;
	ExecutorService es;
	int windowTime;
	LLCUnifier unifier;
	ContextType ct;
	LLCNotifier notifier;
	long userID;
	final static Logger logger = Logger.getLogger(LLCCounter.class);
	AtomicBoolean flag;	
	
	/**
	 * Unique constructor of the LLCCounter instance
	 * @param userID user ID to set on counter
	 * @param startTime startTime of the counting
	 * @param es executorservice to run the thread
	 * @param unifier unifier instance, which stores all the recognized low level context
	 * @param notifier notifier instance, which will be used in notifying unified labels into DCL
	 * @param ct contextType of the counter instance
	 * @param flag Flag variable, indicating whether the counter is running or not.
	 */
	public LLCCounter(long userID, String startTime, ExecutorService es, LLCUnifier unifier, LLCNotifier notifier, ContextType ct, AtomicBoolean flag){
		this.startTime = startTime;
		this.es = es;
		this.windowTime = ct.getWindowSize();
		this.unifier = unifier;
		this.ct = ct;
		this.userID = userID;
		this.notifier = notifier;
		this.flag = flag;
	}
	
	/**
	 * Set the running indicator of context counter instance
	 * @param bool boolean value to set on the indicator of context counter instance
	 */
	public void setRunning(boolean bool){
		flag.set(bool);
	}
	
	/**
	 * Returns whether the context counter instance is running
	 * @return boolean variable, indicating whether the context counter is running
	 */
	public boolean isRunning(){
		return flag.get();
	}
	
	
	@Override
	public void run() throws NullPointerException{
		// TODO Auto-generated method stub
		try {
			synchronized(this){
				wait(1000*ct.getWindowSize());
			}
				//unifier.mVoting(curTime); //majority voting and sending
			es.execute(new Runnable(){
				@Override
				public void run() {
					ContextLabel llc = unifier.unify(startTime, ct);
					if(llc != null){
						notifier.Notify(llc, ct);
					}else{
						notifier.Notify(ct.getBasicLabel(userID, startTime), ct);
					}
					if(llc != null)logger.info("Out of llc = " + llc.getLabel());
					else logger.info("loc null");
					flag.set(false);
				}
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
}