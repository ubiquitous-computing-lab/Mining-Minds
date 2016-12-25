package org.uclab.mm.icl.llc.MachineLearningTools.classification;

import org.uclab.mm.icl.utils.FileUtil;
import weka.classifiers.functions.SMO;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


/**
 * 
 * @author Nailbrainz
 * THis class classfies user context based on the 25 skeleton coordinates data 
 * inputtype - double[15][76]
 */
public class KinectARClassification extends Classification<double[]>{
	String[] labelString = new String[] {"Stretching", "LyingDown", "Sweeping","Sitting","Eating","Standing"};
	
	private SMO smo;
	Instances dummyDataset;
	/**
	 * 
	 */
	public KinectARClassification() {
		smo = null;
		loadModel(FileUtil.getRootPath()+"/SMOVAR.model");
	}
	/**
	 * This function loads the var model(SMO weka algorihtm) from local file
	 * @param filename file name to load the model from
	 * @return returns true when loading model was successful, false otherwise
	 */
	public boolean loadModel(String filename) {
		try {
			smo = (SMO) weka.core.SerializationHelper.read(filename);
			dummyDataset = createDummyDataset(281);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This function saves the weka smo model into local file system
	 * @param filename file path to save the model
	 * @return true when saving model was successful, false otherwise
	 */
	public boolean saveModel(String filename) {
		try {
			weka.core.SerializationHelper.write(filename, smo);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This function creates Weka instance
	 * @param features
	 * @return created instances
	 */
	protected Instance createInstance(double[] features) {
		int numAttributes = features.length;
		Instance instance = new Instance(numAttributes + 1);
		for (int i = 0; i < numAttributes; i++)
			instance.setValue(i, features[i]);
		return instance;
	}
	
	/**
	 * This function creates Weka instance
	 * @param features
	 * @return created instances
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

	
	
	@Override
	public String Classify(double[] data) {
		// TODO Auto-generated method stub
		try {
			// Convert features to an instance
			Instance instance = createInstance(data);
			instance.setDataset(dummyDataset);
			
			// Classify
			int labelIndex =  Double.valueOf(smo.classifyInstance(instance)).intValue();
			
			return labelString[labelIndex];
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
