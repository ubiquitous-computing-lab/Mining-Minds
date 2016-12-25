package org.uclab.mm.icl.llc.AR.var;

import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class KinectARClassification {
	String[] labelString = new String[] {"Stretching","LyingDown","Sweeping","Sitting","Eating","Standing"};
	
	private J48 smo;
	Instances dummyDataset;
	
	/**
	 * 
	 */
	public KinectARClassification(String modelFile) {
		smo = null;
		loadModel(modelFile);
	}
	
	/**
	 * @param filename
	 * @return
	 */
	public boolean loadModel(String filename) {
		try {
			smo = (J48) weka.core.SerializationHelper.read(filename);
			dummyDataset = createDummyDataset(281);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @param features
	 * @return
	 */
	protected Instance createInstance(double[] features) {
		int numAttributes = features.length;
		Instance instance = new Instance(numAttributes + 1);
		for (int i = 0; i < numAttributes; i++)
			instance.setValue(i, features[i]);
		return instance;
	}
	
	/**
	 * @param numAttributes
	 * @return
	 */
	protected Instances createDummyDataset(int numAttributes) {
		// Create attribute set
		FastVector fvWekaAttributes = new FastVector(numAttributes);
		
		// Create numeric attributes
		for (int i = 0; i < numAttributes - 1; i++) {
			Attribute attribute = new Attribute("f" + i);
			fvWekaAttributes.addElement(attribute);
		}
		
		// Create class attribute
		FastVector fvClassVal = new FastVector(6);
		fvClassVal.addElement("c1");
		fvClassVal.addElement("c2");
		fvClassVal.addElement("c3");
		fvClassVal.addElement("c4");
		fvClassVal.addElement("c5");
		fvClassVal.addElement("c6");
		Attribute classAttribute = new Attribute("class", fvClassVal);
		fvWekaAttributes.addElement(classAttribute);
		
		// Create an empty training set
		Instances dataset = new Instances("Rel", fvWekaAttributes, 1);
		// Set class index
		dataset.setClassIndex(numAttributes - 1);
		
		return dataset;
	}

	public String Classify(double[] data) {
		try {
			if (smo == null)
				return "MODELISNULL";
			
			// Convert features to an instance
			Instance instance = createInstance(data);
			instance.setDataset(dummyDataset);
			
			// Classify
			int labelIndex = Double.valueOf(smo.classifyInstance(instance)).intValue();
			
			return labelString[labelIndex];
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
