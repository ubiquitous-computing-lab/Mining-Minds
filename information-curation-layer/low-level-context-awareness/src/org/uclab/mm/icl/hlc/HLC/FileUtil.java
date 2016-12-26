package org.uclab.mm.icl.hlc.HLC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.uclab.mm.icl.HLCServer;

public class FileUtil {
	
	String path = "./log";
	/*
	public static String getPath(){
		String curPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"";
		
		return System.getProperty("user.dir")+"";
	}*/
	public static String getPath(){
		String curPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"";
		return curPath;
	}
	
	private static synchronized void createLogDir(){
		
		File f = new File("log");
		if(!f.exists()){
			f.mkdir();
		}
	}
	public synchronized static void WriteTimeLog(String rec, String str){
		createLogDir();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./log/"+rec+"Log.txt"), true));
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static void WriteHLCLog(String str){
		createLogDir();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./log/hlcLog.txt"), true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized static void WriteLLCLog(String str){
		createLogDir();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./log/llcLog.txt"), true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized static void WriteLoCLog(String str){
		createLogDir();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./log/locLog.txt"), true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
