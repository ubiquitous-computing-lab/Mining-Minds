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

package org.uclab.mm.icl.llc.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.uclab.mm.icl.utils.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * ICL configuration class. Gets the input from external configuration file, iclconfig.json
 * @author Nailbrainz
 *
 */
public class ICLConfig {
	
	public static final ArrayList<Integer> contextWindowSize = new ArrayList<Integer>();
	public static final ArrayList<Boolean> isRecogOn = new ArrayList<Boolean>();
	
	public static AtomicInteger delay = new AtomicInteger(9);
	public static final String DCLAddress;
	public static final String LLCAddress;
	public static final String HLCAddress;
	
	public static final int HLCDelay;
	
	
	public static void reInitialize(){
		
	}
	
	//The window size will be initialized when 
	static{
		File configFile = new File(FileUtil.getRootPath()+"/iclconfig.json");
		Scanner scan;
		String content = "";
		try {
			scan = new Scanner(configFile);
			scan.useDelimiter("\\Z");  
			 content = scan.next(); 
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		JSONObject configJson = new JSONObject(content);
		if(configJson.has("WindowConfig")){
			JSONObject recogWindow = configJson.getJSONObject("WindowConfig");
			for(ContextType ct : ContextType.values()){
				if(recogWindow.has(ct.name()))contextWindowSize.add(recogWindow.getInt(ct.name()));
				else contextWindowSize.add( ct.getDefaultWindowSize());
			}
		}
		DCLAddress = configJson.getString("DCLAddress");
		LLCAddress = configJson.getString("LLCAddress");
		HLCAddress = configJson.getString("HLCAddress");
		
		if(configJson.has("ActiveRecognizers")){
			JSONObject activeRecogs = configJson.getJSONObject("ActiveRecognizers");
			for(RecognizerType ct : RecognizerType.values()){
				if(activeRecogs.has(ct.name()))isRecogOn.add(activeRecogs.getBoolean(ct.name()));
				else isRecogOn.add(true);
			}
		}
		HLCDelay = configJson.getInt("HLCDelay");
	}
	
	
}
