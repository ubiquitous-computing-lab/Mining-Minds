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

import javax.sound.sampled.AudioInputStream;

import org.uclab.mm.icl.llc.MachineLearningTools.Segmentation;
import org.uclab.mm.icl.llc.MachineLearningTools.WindowBasedSegmentation;

public class AERSegmentation extends
		Segmentation<AudioInputStream, AudioInputStream> {
	WindowBasedSegmentation wbs ;

	public AERSegmentation(int sec, int samplerate) {
		wbs = new WindowBasedSegmentation(sec, samplerate);
	}

	@Override
	public AudioInputStream segmentation(AudioInputStream input) {
		// TODO Auto-generated method stub
		AudioInputStream ais = null;
		ais = wbs.segmentation(input);
		return ais;
	}

	
}
