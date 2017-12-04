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

import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.MachineLearningTools.Preprocessing;
import org.uclab.mm.icl.llc.MachineLearningTools.SilentRemoverPreprocessing;


public class AERPreprocessing extends
		Preprocessing<SmartphoneData, AudioInputStream> {
	SilentRemoverPreprocessing srp;

	public AERPreprocessing(AudioFormat.Encoding encoding, float sampleRate,
			int sampleSizeInBits, int channels, int frameSize, float frameRate,
			boolean bigEndian,int thresold) {
		 srp = new SilentRemoverPreprocessing(encoding, sampleRate,
					 sampleSizeInBits,  channels,  frameSize,  frameRate,
					 bigEndian, thresold);
	}
	
	@Override
	public AudioInputStream preprocessing(SmartphoneData input) {
		// TODO Auto-generated method stub
		AudioInputStream ais =srp.preprocessing(input); 
		return ais;
	}

	

}
