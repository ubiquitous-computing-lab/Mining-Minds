/*
Copyright [2016] [Jae Hun, Bang]

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
package org.uclab.mm.icl.llc.AER.jh;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

/**
 * Convert the audio data between oInputStream to Byte
 * @author Bang Gae
 *
 */
public class ConvertAudioData {

	/**
	 * convert byte to audioinputstream
	 * @param data
	 * @param audioFM
	 * @return
	 */
	public AudioInputStream convertAudioInputStream(byte[] data,
			AudioFormat audioFM) {
		AudioInputStream ais = null;
		if (data != null && audioFM != null) {
			InputStream is = convertInputStream(data);
			ais = new AudioInputStream(is, audioFM,
					(long) ((data.length) / audioFM.getFrameSize()));
		}
		return ais;
	}

	private InputStream convertInputStream(byte[] data) {
		InputStream is = null;
		if (data != null) {
			is = new ByteArrayInputStream(data);
		}
		return is;
	}

	/***
	 * convert audioinputstream to byte
	 * @param input
	 * @return
	 */
	public byte[] convertByteBuffer(AudioInputStream input) {
		byte[] temp = null;
		if (input != null) {
			temp = new byte[(int) (input.getFrameLength() * input
					.getFormat().getFrameSize())];
			try {
				input.read(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	/**
	 * convert array byte to byte
	 * @param input
	 * @return
	 */
	public byte[] convertByteBuffer(Vector<byte[]> input){
		byte[] temp = new byte[input.size()*input.get(0).length];
		int cnt = 0;
		for(int i=0; i<input.size(); i++){
			for(int j=0; j<input.get(i).length; j++){
				temp[cnt++] = input.get(i)[j];
			}
		}
		return temp;
	}
}
