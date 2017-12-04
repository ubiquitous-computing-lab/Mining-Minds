/**
 * Copyright [2017] [Maqbool Ali]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.mm.kcl.ddkat.modeltranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.inra.qualscape.wekatexttoxml.WekaTextfileToXMLTextfile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.apache.wink.json4j.OrderedJSONObject;

//TODO: Auto-generated Javadoc
/**
* This class processes the classification model, converts into XML model, and finally parses the XML model to extract the rules.
*/
public class ModelTranslator {
	
	/** The rule conditions. */
	private ArrayList<String> ruleConditions;
	
	/** The rule conclusion. */
	private ArrayList<String> ruleConclusions;
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
	
	/**
	 * Constructor to instantiate a new ModelTranslator object.
	 * @param algo_modelstr that contains algo, decision attribute, classification model, and file path information
	 * @throws Exception the exception
	 */    
    public ModelTranslator(String algo_modelstr) throws Exception {
    	
    	this.ruleConditions = new ArrayList<String>();
    	this.ruleConclusions = new ArrayList<String>();
    
       int rulesCounter = 0;
       int lastTrimIndex = 0;
       String treemodelStr ="";
     
       String[] parts = algo_modelstr.split("#####", 3);
       String algo = parts[0]; 
       String decisionattribute = parts[1]; 
       String modelstr = parts[2];
              
       //process the classification model based on decision tree algorithm
       if (algo.equals("J48")){
    	   	lastTrimIndex = modelstr.indexOf("Number of Leaves  :");
    	   	treemodelStr = modelstr.substring(36, lastTrimIndex-2);
    	   	System.out.println("\n\nDecision Tree:\n\n"+treemodelStr);
       	} else if (algo.equals("J48graft")) {
       		lastTrimIndex = modelstr.indexOf("Number of Leaves  :");
       		treemodelStr = modelstr.substring(41, lastTrimIndex-2);
       		treemodelStr = treemodelStr.replace("| ", "*****");
       		treemodelStr = treemodelStr.replace("|", " OR ");
       		treemodelStr = treemodelStr.replace("*****", "|");           
       	} else if (algo.equals("BFTree")) {
       		lastTrimIndex = modelstr.indexOf("Size of the Tree:");
       		treemodelStr = modelstr.substring(26, lastTrimIndex-2);
    	} else if (algo.equals("FT")) {
    		lastTrimIndex = modelstr.indexOf("Number of Leaves  :");
       		treemodelStr = modelstr.substring(29, lastTrimIndex-2);
       	} else if (algo.equals("LADTree")) {
            lastTrimIndex = modelstr.indexOf("Legend:");
            treemodelStr = modelstr.substring(49, lastTrimIndex-1);
       	} else if (algo.equals("RandomTree")) {
       		lastTrimIndex = modelstr.indexOf("Size of the tree :");
       		treemodelStr = modelstr.substring(24, lastTrimIndex-2);
       	} else if (algo.equals("REPTree")) {
       		lastTrimIndex = modelstr.indexOf("Size of the tree :");
       		treemodelStr = modelstr.substring(23, lastTrimIndex-2);
       	} else if (algo.equals("SimpleCart")) {
       		lastTrimIndex = modelstr.indexOf("Number of Leaf Nodes:");
       		treemodelStr = modelstr.substring(20, lastTrimIndex-2);
       	}
       
       
       String outputTxtFile = BASE_DIR + "treemodelFile.txt";
       PrintStream out = new PrintStream(new FileOutputStream(outputTxtFile));
       out.print(treemodelStr);out.close();
       
       File aTextfile = new File(outputTxtFile);
       String newtext="";
       BufferedReader reader = new BufferedReader(new FileReader(aTextfile));
       String line = "";
       while((line = reader.readLine()) != null)
       {
           String colonKeyword = ":";
           Boolean colonFound = Arrays.asList(line.split("")).contains(colonKeyword);
           if(colonFound){
               String[] lineArr = line.split(":");
               String tempConditionStr = lineArr[0];
               Boolean equalFound = Arrays.asList(tempConditionStr.split("")).contains("=");
               Boolean lessFound = Arrays.asList(tempConditionStr.split("")).contains("<");
               Boolean greaterFound = Arrays.asList(tempConditionStr.split("")).contains(">");
               if(!(equalFound | lessFound | greaterFound))
                   {
                   line = "RuleCondition = None : None";
                   }
               else{
                   String tempConclusionStr = lineArr[1];                     
                   String[] tempStrArr = tempConclusionStr.split("\\(");
                   tempConclusionStr = tempStrArr[0];
                   line = tempConditionStr + ":"+tempConclusionStr;
                   }
                
               }
        
           line = line.replaceAll("\\)\\|\\(", " OR_OPERATOR ");
           line = line.replaceAll("=\\(", " = \\(");
           line = line.replaceAll("\\! =", " !=");
  
           String orKeyword = "OR_OPERATOR";
           Boolean orFound = Arrays.asList(line.split(" ")).contains(orKeyword);
           if(orFound){          
               String condStr = line.substring(line.indexOf('('), line.indexOf(')')+1);
               String[] tempArr = condStr.split("OR_OPERATOR");
               String tempLeft = tempArr[0];
               tempLeft = tempLeft.trim();
               String tempRight = tempArr[1];
               tempRight = tempRight.trim();
               String replacedStr = tempLeft+"_OR_"+tempRight;
               line = line.replaceAll(condStr, replacedStr);
               line = line.replaceAll("\\(", "");
               line = line.replaceAll("\\)", "");
           } else{
               line = line.replaceAll("\\(", "");
               line = line.replaceAll("\\)", "");
           } 
           newtext += line + "\r\n";
       }
       reader.close();
       
       // XML model conversion 
       FileWriter writer = new FileWriter(aTextfile);
       writer.write(newtext);writer.close();
       
       String outputXMLFile = BASE_DIR + "treemodelFile.xml";
       File anXmlfile = new File(outputXMLFile);
       
       WekaTextfileToXMLTextfile xmlconversionObj = new WekaTextfileToXMLTextfile(aTextfile, anXmlfile, true, true);
       xmlconversionObj.writeXmlFromWekaText();
       
       String oldtext="";
       String newtextStr="";
       BufferedReader xmlReader = new BufferedReader(new FileReader(anXmlfile)); 
       String xmlLine = "";
       while((xmlLine = xmlReader.readLine()) != null)
           {
            oldtext += xmlLine + "\r\n";
           }
       xmlReader.close();
       // replace a word in a file
       newtextStr = oldtext.replaceAll("\"<", "\"&lt;");
       newtextStr = newtextStr.replaceAll(">\"", "&gt;\"");

       FileWriter xmlWriter = new FileWriter(outputXMLFile);
       xmlWriter.write(newtextStr);xmlWriter.close();
       
       String algoWriterFile = BASE_DIR + "selectedalgorithmFile.txt";
       FileWriter algoWriter = new FileWriter(algoWriterFile);
       algoWriter.write(algo);algoWriter.close();
      
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document source = builder.parse(new File(outputXMLFile));
       Node rootNode = source.getDocumentElement();
       Node currentNode=rootNode.getFirstChild();
       
       JSONArray jsonArr = new JSONArray();
            
       while (currentNode!=null) {
         // print node information
           if(currentNode.getNodeName().equals("Test")){
        	// print node information  
           }

         if ( currentNode.hasChildNodes()) {
           currentNode = currentNode.getFirstChild();
         }
         else {
             // leaf
             if(currentNode.getNodeName().equals("Output")){
  
            	 rulesCounter++;
                 String ruleConclusion = "";
                 ruleConclusion = decisionattribute+" = "+currentNode.getAttributes().getNamedItem("decision").getNodeValue();
           
                 int pathnodes_counter = 0;
                 String ruleCondition = " ";
                 String conditionAttribute = " ";
                 String conditionOperator = " ";
                 String conditionValue = " ";
                 String ruleConditions = " ";
                 Node pathNode = currentNode;

                 while(pathNode.getParentNode().getNodeName().equals("Test")){
                     pathnodes_counter++;
                     pathNode = pathNode.getParentNode();
                     }

                 pathNode = currentNode;
                 String ruleConditionsArr[] = new String[pathnodes_counter];
                 int arrIndex = pathnodes_counter-1;
                 
                 
                 while(pathNode.getParentNode().getNodeName().equals("Test")){
                     conditionAttribute = pathNode.getParentNode().getAttributes().getNamedItem("attribute").getNodeValue();
                     conditionOperator = pathNode.getParentNode().getAttributes().getNamedItem("operator").getNodeValue();
                     conditionValue = pathNode.getParentNode().getAttributes().getNamedItem("value").getNodeValue();
                     ruleCondition = conditionAttribute + " " + conditionOperator + " " + conditionValue;
                     ruleConditionsArr[arrIndex] = ruleCondition;
                     arrIndex--;
                     pathNode = pathNode.getParentNode();
                                          
                     }
                 
                 for(int i=0; i<pathnodes_counter; i++){
                     ruleConditions = ruleConditions.concat(ruleConditionsArr[i] + " AND ");
                     }

                 ruleConditions = ruleConditions.substring(0,ruleConditions.length()-5);
                 ruleConditions = ruleConditions.trim();

                 System.out.println(" \n RULE = "+Integer.toString(rulesCounter)+", Conditions = "+ruleConditions+", Conclusion = "+ruleConclusion);
                       
                 JSONObject jsonObj = new OrderedJSONObject();
               
                 jsonObj.put("ruleID", Integer.toString(rulesCounter));
                 jsonObj.put("ruleConditions", ruleConditions);
                 jsonObj.put("ruleConclusion", ruleConclusion);
          
                 jsonArr.put(jsonObj);
               
                 this.ruleConditions.add(ruleConditions);
                 this.ruleConclusions.add(ruleConclusion);
             } 
             
             // find the parent level
             while (currentNode.getNextSibling()==null && currentNode != rootNode){
            	 // use child-parent link to get to the parent level
            	 currentNode=currentNode.getParentNode();
             	 }

             currentNode = currentNode.getNextSibling();
         }
       }
                   
       JSONObject mainJsonObj = new JSONObject();
       mainJsonObj.put("decisiontreeRules", jsonArr);
	
    }
    
    
    public ArrayList<String> getRuleConditions() {
		return ruleConditions;
	}

	public void setRuleConditions(ArrayList<String> ruleConditions) {
		this.ruleConditions = ruleConditions;
	}
    
    
	public ArrayList<String> getRuleConclusions() {
		return ruleConclusions;
	}

	public void setRuleConclusions(ArrayList<String> ruleConclusions) {
		this.ruleConclusions = ruleConclusions;
	}
    
	
}
