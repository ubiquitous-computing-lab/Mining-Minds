/**
Copyright [2016] [Tae ho Hur]

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

package org.uclab.mm.icl.llc.AR.iar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.uclab.mm.icl.utils.FileUtil;

import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * This class loads the smartphone, smartwatch and integrated AR model. It also
 * executes the weka based on the AR model and returns the activity label
 * 
 * @author Tae ho Hur
 *
 */
public class AR_Reasoning {
	Classifier classifier;

	String name;

	/**
	 * This function loads the AR model
	 * 
	 * @param modelpath
	 * 
	 */
	public AR_Reasoning(String modelpath) {
		try {
			classifier = (Classifier) weka.core.SerializationHelper.read(modelpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function executes the Weka to infer the activity and returns the activity label
	 * 
	 * @param modelno
	 * @return arLabel
	 */
	public String executeWeka(int modelno) {
		String arLabel = ""; // Stores the activity label
		Instances unlabeled = null;
		try {
			switch (modelno) {
			// Load integrated arff file which contains feature list
			case 1:
				unlabeled = new Instances(new BufferedReader(
						new FileReader(new File(FileUtil.getRootPath() + "/ar/" + "/integrated.txt"))));
				break;
			// Load smartphone arff file which contains feature list
			case 2:
				unlabeled = new Instances(new BufferedReader(
						new FileReader(new File(FileUtil.getRootPath() + "/ar/" + "/sm.txt"))));
				break;
			// Load wearable arff file which contains feature list
			case 3:
				unlabeled = new Instances(new BufferedReader(
						new FileReader(new File(FileUtil.getRootPath() + "/ar/" + "/wm.txt"))));
				break;
			}

			unlabeled.setClassIndex((unlabeled.numAttributes() - 1));
			Instances labeld = new Instances(unlabeled);

			for (int j = 0; j < unlabeled.numInstances(); j++) {
				arLabel += (int) classifier.classifyInstance(unlabeled.instance(j));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arLabel;
	}
}
