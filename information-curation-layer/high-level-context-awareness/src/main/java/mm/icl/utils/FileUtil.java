/**
* 
** Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.icl.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void WriteHLCLog(String str){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("E:/ICL_LOG/hlcLog.txt"), true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void WriteLLCLog(String str){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("E:/ICL_LOG/hlcLog.txt"), true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
