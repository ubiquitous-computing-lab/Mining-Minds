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
