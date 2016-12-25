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

package org.uclab.mm.icl;
import java.io.File;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.RecognizerType;

public class ConfigJsonGenerator {
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject configJson = new JSONObject();
		JSONObject recogWindow = new JSONObject();
		recogWindow.put(ContextType.Activity.name(), 6);
		recogWindow.put(ContextType.Emotion.name(), 9);
		recogWindow.put(ContextType.Location.name(), 12);
		configJson.put("WindowConfig", recogWindow);
		
		configJson.put("DCLAddress", "http://163.180.116.194:8080/MMDataCurationRestfulService/webresources/InformationCuration/");
		//configJson.put("LLCAddress", "http://163.180.116.243:8080/icl_whole/icl/llc/");
		//configJson.put("HLCAddress", "http://163.180.116.243:8080/icl_whole/icl/llc/");
		configJson.put("LLCAddress", "http://localhost:8080/icl_whole/icl/llc/");
		configJson.put("HLCAddress", "http://localhost:8080/icl_whole/icl/llc/");
		
		JSONObject activeRecogs = new JSONObject();
		activeRecogs.put(RecognizerType.IAR.name(), true);
		activeRecogs.put(RecognizerType.VAR.name(), true);
		activeRecogs.put(RecognizerType.ER.name(), true);
		activeRecogs.put(RecognizerType.SER.name(), true);
		activeRecogs.put(RecognizerType.LR.name(), true);
		//activeRecogs.put("Food", true);
		configJson.put("ActiveRecognizers", activeRecogs);
		
		configJson.put("HLCDelay", 20);
		
		
		PrintWriter pw = new PrintWriter(new File("D:/mm2.0/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/icl_whole/WEB-INF/classes/iclconfig.json"));
		pw.write(configJson.toString());
		pw.close();
		pw = new PrintWriter(new File("D:/GIT_ICL/glassfish-4.1.1.zip/icl_whole/GIT_ICL_WHOLE/FILES/iclconfig.json"));
		pw.write(configJson.toString());
		pw.close();
		
		/*
		long userID = 35;
		
		//ExtClassification ext = new ExtClassification(null, 0, args, null);
		
		VideoActivityRecognizer vr = new VideoActivityRecognizer(userID);
		GPSLocationRecognizer gr = new GPSLocationRecognizer(userID);
		AudioEmotionRecognizer emr = new AudioEmotionRecognizer(ext, null, userID);
		
		vr.recognize(input, userID, timeStamp);
		gr.recognize(input, userID, timeStamp)
		emr.recognize(segmented, userID, timeStamp)
		
		
		
		
		
		
		
		
		for (int i=0;i<10;i++) {
			// 각각의 레벨에 맞춰 로그를 남김
			LLogger.logger.debug("debug 로그");
			LLogger.logger.info("info 로그");
			LLogger.logger.warn("warn 로그");
			LLogger.logger.error("error 로그");
			LLogger.logger.fatal("fatal 로그");
		}
		/*
		Scanner scan = new Scanner(new File("D:/EmoJHLog.txt"));
		String str = scan.nextLine();
		
		long summ = 0;
		long lines = 0;
		while(scan.hasNext()){
			Long time = scan.nextLong();
			summ += time;
			lines++;
		}
		
		 scan = new Scanner(new File("D:/EmoVuiLog.txt"));
		 str = scan.nextLine();
		
		while(scan.hasNext()){
			Long time = scan.nextLong();
			summ += time;
			lines++;
		}
		
		System.out.println("Time = " + summ/lines);
		/*System.out.println(str);
		JSONArray ary = new JSONArray(str);
		JSONObject obj = ary.getJSONObject(0);
		System.out.println(obj.getString("locationLabel"));
		
		FoodItem df = new FoodItem(35, "Chicken", "2016 05 12 00:00:00", null);
		System.out.println(RestClients.AddFoodLog(df));*/
		/*
		KincetARFeatureExtraction4 fr = new KincetARFeatureExtraction4();
		double[][] ary = new double[30][75];
		Scanner scan = new Scanner(new File("D:\\eating_testing.txt"));
		scan.useDelimiter(",|\r\n");
		
		for(int i = 0; i<30; i++){
			for(int j = 0; j<75; j++)ary[i][j] = scan.nextDouble();
			System.out.println(scan.nextLine());
			for(int j = 0; j<75; j++)scan.nextDouble();
			scan.nextLine();
			for(int j = 0; j<75; j++)scan.nextDouble();
			scan.nextLine();
		}
		
		for(int j = 0; j<75; j++)System.out.print(ary[29][j] + " ");
		System.out.println();
		fr.setInputData(ary);
		fr.calcFeatures();
		double[] rlt = fr.extractFeatures();
		for(int i = 0; i<rlt.length; i++)System.out.print(rlt[i] + " ");
		KinectARClassification ar = new KinectARClassification();
		System.out.println(ar.Classify(rlt));
		*/
	}

}
