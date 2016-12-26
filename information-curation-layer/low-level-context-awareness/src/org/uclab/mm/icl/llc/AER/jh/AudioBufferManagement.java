package org.uclab.mm.icl.llc.AER.jh;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;


public class AudioBufferManagement {


	AudioInputStream result;
	AudioFormat af;
	ConvertAudioData cad = new ConvertAudioData();

	public AudioBufferManagement(AudioFormat af) {
		this.af =af;
	}

	public AudioInputStream accumulateData(AudioInputStream input1,
			AudioInputStream input2) {
		byte[] data1 = null;
		byte[] data2 = null;
		byte[] merge =null;
		if(input1 != null){
			data1 = cad.convertByteBuffer(input1);
		}
		if(input2!=null){
			data2 = cad.convertByteBuffer(input2);
		}
		if(input1 !=null && input2 != null){
			merge = mergeBuffer(data1, data2);
		}
		if(input1 != null && input2 ==null){
			merge = data1;
		}
		if(input1 == null && input2 != null){
			merge = data2;
		}
		result = cad.convertAudioInputStream(merge,af);
		return result;
	}

	private byte[] mergeBuffer(byte[] data1, byte[] data2) {
		byte[] result = null;
		int cnt = 0;
		if (data1.length + data2.length > 0) {
			result = new byte[data1.length + data2.length];
			for (int i = 0; i < data1.length; i++) {
				result[cnt] = data1[i];
				cnt++;
			}
			for (int i = 0; i < data2.length; i++) {
				result[cnt] = data2[i];
				cnt++;
			}
		}
		return result;
	}
}
