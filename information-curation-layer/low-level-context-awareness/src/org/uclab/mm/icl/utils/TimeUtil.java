package org.uclab.mm.icl.utils;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class TimeUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.KOREA);//"yyyy:MM:dd_HH:mm:ss.SSS"
	static SimpleDateFormat demoSdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);//"yyyy:MM:dd_HH:mm:ss.SSS"
	static SimpleDateFormat fileName = new SimpleDateFormat("HHmmss", Locale.KOREA);//"yyyy:MM:dd_HH:mm:ss.SSS"
	
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cal;
		}
	}
	
	public String getFileName(){
		synchronized(this){
			Calendar cal = Calendar.getInstance();
		    return fileName.format(cal.getTime());
		}
	}
	public String getTime(){
		synchronized(this){
			Calendar cal = Calendar.getInstance();
		    return sdf.format(cal.getTime());
		}
	}
	public static long getCurTime(){
		return System.nanoTime();
	}
}
