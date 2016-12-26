package org.uclab.mm.icl.hlc.HLC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class TimeUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.KOREA);//"yyyy:MM:dd_HH:mm:ss.SSS"
	static SimpleDateFormat demoSdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);//"yyyy:MM:dd_HH:mm:ss.SSS"
	
	
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
	
	public String getTime(){
		synchronized(this){
			Calendar cal = Calendar.getInstance();
		    return sdf.format(cal.getTime());
		}
	}
	
}
