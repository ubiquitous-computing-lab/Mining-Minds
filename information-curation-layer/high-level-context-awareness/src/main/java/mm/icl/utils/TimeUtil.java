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
package mm.icl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
/**
 * TimeUtil: 
 * Provides time date format utility.
 * 
 * @author Nailbrainz
 * @version 2.5
 * @since 2015-11-06
 */
public class TimeUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.KOREA);
	static SimpleDateFormat demoSdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
	
	public String toDemoStr(Calendar cal){
		synchronized(this){
			String formatted = demoSdf.format(cal.getTime());
			return formatted;
		}
	}
	
	public String parseCal(Calendar cal){
		synchronized(this){
			String formatted = sdf.format(cal.getTime());
			return formatted;
		}
	}
	
	public Calendar parseString(String str){
		synchronized(this){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(str));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return cal;
		}
	}
	
	public String getTime(){
		synchronized(this){
			Calendar cal = Calendar.getInstance();
		    return sdf.format(cal.getTime());
		}
	}
	
}
