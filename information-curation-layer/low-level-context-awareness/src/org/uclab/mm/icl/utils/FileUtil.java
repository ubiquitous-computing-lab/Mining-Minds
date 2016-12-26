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
package org.uclab.mm.icl.utils;

public class FileUtil {
	
	String path = "./log";
	/*
	public static String getPath(){
		String curPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"";
		
		return System.getProperty("user.dir")+"";
	}*/
	
	final static String webPath;
	final static String rootPath;
	
	public static String getWebPath(){
		return webPath;
	}
	public static String getRootPath(){
		return rootPath;
	}
	static{
		webPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"";
		
		int cnt = 0;
		String temp = "";
		for(int i = webPath.length()-1; i>=0; i--){
			System.out.print(webPath.charAt(i));
			if(webPath.charAt(i) == '/')cnt++;
			
			if(cnt == 3){
				temp = webPath.substring(0, i+1);
				break;
			}
		}
		rootPath = temp;
	}
}
