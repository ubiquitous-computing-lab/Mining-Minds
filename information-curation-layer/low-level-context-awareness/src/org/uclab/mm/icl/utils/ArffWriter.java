package org.uclab.mm.icl.utils;

/*
Copyright [2016] [Vui]

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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ArffWriter {
	static String PATH = "";
	File file;
	FileWriter fw;

	public ArffWriter(String path, String filename ,String relation, String[] labels, int featureNum,
			String[] featureName) {
		PATH = path;
		file = new File(PATH);
		file.mkdirs();
		file = new File(PATH + "/"+filename+".arff");
	
		try {
			file.createNewFile();
			fw = new FileWriter(file);
			initWrite(relation, labels, featureNum, featureName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initWrite(String relation, String[] labels, int featureNum, String[] featureName) {
		String init = "@relation " + relation+"\r\n";
		try {
			fw.write(init);
			if (featureName == null) {
				for (int i = 0; i < featureNum; i++) {
					fw.write("@attribute " + "f" + i + " real\r\n");
				}
			}else{
				for( int i=0; i<featureName.length; i++){
					fw.write("@attribute " + featureName[i] + " real\r\n");
				}
			}
			fw.write("@attribute class {");
			for(int i = 0;i< labels.length; i++){
				
				if(i == labels.length-1){
					fw.write(labels[i]+"}\r\n");
				}else{
					fw.write(labels[i]+", ");
				}
			}
			fw.write("@data\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public WriteFile() { try { file = convertIsToFile(is); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */

	

	public void write(double[] features, String label) {
		try {
			FileWriter fw;
			fw = new FileWriter(file, true);

			for (int i = 0; i < features.length; i++) {
				fw.write(features[i] + "\t ");
			}
			fw.write("'" + label + "'\r\n");

			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
