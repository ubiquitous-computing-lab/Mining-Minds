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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import org.apache.log4j.Logger;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.AER.vui.WavFile;
import org.uclab.mm.icl.llc.LLCManager.User;
import org.uclab.mm.icl.llc.config.DeviceType;
import org.uclab.mm.icl.utils.FileUtil;
import org.uclab.mm.icl.utils.TimeChecker;
import org.uclab.mm.icl.utils.TimeUtil;


/**
 * This class is socket version of ICL. This class is multithreaded, and it generates new thread for each new socket connection.
 * @author Nailbrainz
 *
 */
public class ICLServerThread extends Thread {
	private static List<ICLServerThread> threads = new ArrayList<ICLServerThread>();
	Socket clientSocket = null;
	static String answer = "";
	private int payloadSize = 0;
	private RandomAccessFile randomAccessWriter;
	AudioFormat af = new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);
	LLCServer icl;
	ServerSocket ss;
	private final static Logger logger = Logger.getLogger(ICLServerThread.class);
	

	
	/**
	 * Unique constructor of ICL socket version
	 * @param ss server socket
	 * @param socket client socket
	 * @param icl icl server instance
	 */
	public ICLServerThread(ServerSocket ss, Socket socket, LLCServer icl) {
		super();
		this.ss = ss;
		this.clientSocket = socket;
		threads.add(this);
		this.icl = icl;
		for (int i = 0; i < threads.size() - 1; i++) {
			if (socket.getInetAddress().equals(threads.get(i).clientSocket.getInetAddress())) {
				System.out.println(threads.get(i).clientSocket.getInetAddress() + "Duplicated Access");
				System.out.println(threads.get(i).getName() + "Delete Thread");
				threads.remove(i);
			} else {
				System.out.println(threads.get(i).clientSocket.getInetAddress() + "Access");
			}
		}
		for (int i = 0; i < threads.size(); i++) {
			System.out.println(threads.get(i).getName() + "Activated Thread");
		}
	}

	//running part of ICLServerThread. Waits for the socket message and convey the sensor data to user objects
	public void run(){
		boolean running = true;
		InputStream objectInBytes = null;
		ObjectInputStream readingObjects = null;
		SmartphoneData sd = null;
		try {
			//System.out.println("waiting for objects");
			objectInBytes = clientSocket.getInputStream();
			readingObjects = new ObjectInputStream(objectInBytes);
			sd = new SmartphoneData();
		} catch (IOException ex) {
			System.out.println("err at objectinputstream on server " + ex.toString());
			ex.printStackTrace();
		}
		while (running) {
			try {
				sd = (SmartphoneData) readingObjects.readObject();
				if (!sd.getIsDemo()) {
					System.out.println("Data of user  " + sd.getUserID() + " rvcd at " + sd.getTimeStamp());
					try {
						System.out.println("smartphone Accelerometer data = " + sd.getAcc_sm()[0][130]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("gps =  " + sd.getLat() + "," + sd.getLongi());
					if (sd.getAudioSensoryData() == null)
						System.out.println("voice null");
					else
						System.out.println("voice size =  " + sd.getAudioSensoryData().length);
					// ObjectSizer of = new ObjectSizer();
					// System.out.println("Object size = " + of.getObjectSize(sd));
					/*
					 * FileOutputStream fout = new FileOutputStream("D:\\packet.dat");
					 * ObjectOutputStream oos = new ObjectOutputStream(fout); oos.writeObject(sd);
					 * oos.close();
					 */
					logger.info(sd.getLat() + "," + sd.getLongi());
					TimeChecker.time = System.nanoTime();
					/*
					 * if(sd.getEmoImage() != null){ 
					 * 	System.err.print("!!!!!!!!!!!!!!!!!!!!!!!!!!! emo image not null!!");
					 * 	FileOutputStream out; TimeUtil ti = new TimeUtil();
					 * 	out = new FileOutputStream("D:/emoPic/"+ti.getFileName()+".jpg"); 
					 * 	out.write(sd.getEmoImage()); out.close(); }else{
					 * 	System.err.print("!!!!!!!!!!!!!!!!!!!!!!!!!!! emo image  null!!!!!!!!!!!!!!!!!!!!!!!!");
					 * }
					 */
					icl.RouteData(DeviceType.SmartPhone, sd);

				} else {
					User usr = icl.getUserDemo(sd.getUserID());
					if (usr != null) {
						usr.fillBufferDemo(sd.getTimeStamp(), sd.getImage(), sd.getFoodTag());
					}
					break;
				}

			} catch (IOException e) {
				logger.error("io related error at reading data class (smartphone) from socket inputstream");
				logger.error(e);
				try {
					clientSocket.close();
				} catch (IOException e1) {
					logger.error("error at closing socket from smartphone");
					logger.error(e);
					// TODO Auto-generated catch block
				}
				threads.remove(this);
				return;
			} catch (ClassNotFoundException e) {
				logger.error("error at reading data class (smartphone) from socket inputstream");
				logger.error("serializable data class does not matched. Make sure to use right class and "
						+ "right sererialization number");
				logger.error(e);
			} 
		}
	}

	/**
	 * Sends the socket message
	 * 
	 * @param talker ICLServerThread to send message
	 * @param message message to be send
	 */
	public void sendMessage(ICLServerThread talker, String message) {
		try {
			OutputStream out = clientSocket.getOutputStream();
			byte[] buffer = message.getBytes("UTF-8");
			out.write(buffer);
			out.flush();
		} catch (IOException ioe) {
		}
	}

	/**
	 * Initialize the class required for the file writing of Vui's module
	 * 
	 * @param path path to create file
	 * @throws IOException IOException of the file exception
	 */
	public void prepare(String path) throws IOException {

		randomAccessWriter = new RandomAccessFile(path, "rw");
		randomAccessWriter.setLength(0);
		randomAccessWriter.writeBytes("RIFF");
		// Final file size not known yet, write 0
		randomAccessWriter.writeInt(0);
		randomAccessWriter.writeBytes("WAVE");
		randomAccessWriter.writeBytes("fmt ");
		// Sub-chunk size, 16 for PCM
		randomAccessWriter.writeInt(Integer.reverseBytes(16));
		// AudioFormat, 1 for PCM
		randomAccessWriter.writeShort(Short.reverseBytes((short) 1));
		// Number of channels, 1 for mono, 2 for stereo
		randomAccessWriter.writeShort(Short.reverseBytes((short) af.getChannels()));
		// Sample rate
		randomAccessWriter.writeInt(Integer.reverseBytes((int) af.getSampleRate()));
		// Byte rate,
		// SampleRate*NumberOfChannels*BitsPerSample/8
		randomAccessWriter.writeInt(
				Integer.reverseBytes((int) (af.getSampleRate() * af.getSampleSizeInBits() * af.getChannels() / 8)));
		// Block align, NumberOfChannels*BitsPerSample/8
		randomAccessWriter.writeShort(Short.reverseBytes((short) (af.getChannels() * af.getSampleSizeInBits() / 8)));
		// Bits per sample
		randomAccessWriter.writeShort(Short.reverseBytes((short) af.getSampleSizeInBits()));
		randomAccessWriter.writeBytes("data");
		// Data chunk size not known yet, write 0
		randomAccessWriter.writeInt(0);
		// buffer = new byte[framePeriod * bSamples / 8 * channel];

	}

	
	/**
	 *  This function writes voice wav data into uri which is set in randomAccessWriter instance
	 * @throws IOException when randomaccesswriter fails, this exception could occur
	 */
	public void stop_writing() throws IOException {
		// randomAccessWriter.write(buffer);
		randomAccessWriter.seek(4); // Write size to RIFF header
		randomAccessWriter.writeInt(Integer.reverseBytes(36 + payloadSize));

		randomAccessWriter.seek(40); // Write size to Subchunk2Size
										// field
		randomAccessWriter.writeInt(Integer.reverseBytes(payloadSize));
		randomAccessWriter.close();

	}
}
