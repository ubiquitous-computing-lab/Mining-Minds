/*
Copyright [2016] [Claudia]

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
package org.uclab.mm.icl;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextMapper;
import org.uclab.mm.icl.hlc.HLCBuilder.HLCBuilder;
import org.uclab.mm.icl.utils.FileUtil;
import org.uclab.mm.icl.utils.TimeUtil;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * ICL HCL main server
 * 
 * @author Dong Uk, Kang
 *
 */
public class HLCServer {

	private final static Logger logger = Logger.getLogger(HLCServer.class);
	OntModel ontModel; // ontology model
	ServerSocket server;
	Socket socket;
	ContextMapper mapper;
	ContextOntologyManager contextMng;
	HLCBuilder builder;
	TimeUtil util = new TimeUtil();

	/*
	private static HLCServer singleton = new HLCServer();
	public static HLCServer getHLCServer(){
		return singleton;
	}*/
	static ExecutorService es = Executors.newCachedThreadPool();
	/*
	 * static final String directory = "C:\\HLCA" ; static final String
	 * ontologyFile = "file:context-v2.owl";
	 */
	static String directory = FileUtil.getRootPath() + "/TDB"; // ./ICL_LOG\\TDB
	// static final String ontologyFile =
	// "file:////D:/ICL_LOG/TDB/context-v2-5.owl";
	// static final String ontologyFile = "file:context-v2-5.owl";
	static final String ontologyFile = "file:" + FileUtil.getRootPath() + "/context-v2-5.owl";
	public boolean isWithDCL = false;

	/**
	 * This function reads the ontology TDB file, and creates the
	 * ContextOntologyManager
	 */
	public HLCServer() {
		File f = new File(FileUtil.getRootPath() + "/TDB"); // relative path on server
		System.err.println("Current Server path = " + f.getPath());
		System.out.println(FileUtil.getRootPath());
		System.out.println(FileUtil.getWebPath());
		
		if (!f.exists()) {
			f.mkdir();
		}

		// READ THE CONTEXT ONTOLOGY FROM FILE
		ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		ontModel.read(ontologyFile);

		// CREATE THE CONTEXT ONTOLOGY MANAGER
		contextMng = new ContextOntologyManager(directory, ontModel);
		if (!contextMng.correctInitialization()) {
			logger.fatal("Failed to initialize ContextOntologyManager.");
			logger.fatal("Check whether valid URI is provided for the ContextOntologyManager.");
			throw new IllegalStateException(); // this exception will be handled at above level
		}

		mapper = new ContextMapper(contextMng.getContextOntology());
		builder = new HLCBuilder(contextMng, es);
		es.execute(builder.getCounterRunnable());

	}

	/**
	 * maps llc to the database
	 * 
	 * @param llcLabel
	 *            label of the low level context
	 * @param userID
	 *            corresponding user ID
	 * @param startTime
	 *            start time
	 */
	public void mapLLC(String llcLabel, String userID, Calendar startTime) {
		//logger.info("mapLLC " + llcLabel + " and " + userID + " and " + startTime);
		mapper.mapLLC(llcLabel, userID, startTime);
	}

}
