package org.uclab.mm.icl.llc.MachineLearningTools;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.MachineLearningTools.classification.Classification;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This Class build the predictive model by all of classifier from weka library
 * @author Bang Gae
 *
 */

public class ExtClassification extends Classification {

	int FEATURES_NUM;
	int RECOGNIZE_IND;
	FastVector allAttributes;
	Instances learningDataset;
	Classifier predictiveModel;
	Attribute[] m;
	String[] label;
	/**
	 * classify class
	 * @param data
	 * @return int
	 */

	public int classify(double[] data) {
		int result = 0;
		try {
			result = getClassifiedInformation(learningDataset, predictiveModel, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * set training data path, features information, labels and classifier
	 * @param filename
	 * @param FEATURES_NUM
	 * @param label
	 * @param classifier
	 */

	public ExtClassification(String filename, int FEATURES_NUM, String[] label,
			Classifier classifier) {
		try {
			this.FEATURES_NUM = FEATURES_NUM;
			this.RECOGNIZE_IND = FEATURES_NUM + 1;
			this.m = new Attribute[FEATURES_NUM];
			this.label = label;
			allAttributes = createAttributes();
			learningDataset = createLearningDataSet(allAttributes, filename);
			predictiveModel = learnPredictiveModel(learningDataset, classifier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * create attribute by weka
	 * @return FastVector
	 */

	private FastVector createAttributes() {

		for (int i = 0; i < FEATURES_NUM; i++) {
			m[i] = new Attribute("m" + i);
		}

		FastVector labelValues = new FastVector(label.length);
		for (int i = 0; i < label.length; i++) {
			labelValues.addElement(label[i]);
		}

		// emotionValues.addElement("fear");
		Attribute classlabel = new Attribute("CLASS", labelValues);
		FastVector allAttributes = new FastVector(FEATURES_NUM);
		for (int i = 0; i < FEATURES_NUM; i++) {
			allAttributes.addElement(m[i]);
		}
		allAttributes.addElement(classlabel);
		return allAttributes;
	}
	
	/**
	 * Create Learning Data Set, It process based on some format
	 * @param allAttributes
	 * @param filename
	 * @return
	 */

	private Instances createLearningDataSet(FastVector allAttributes,
			String filename) {

		Instances trainingDataSet = new Instances("wekaClassifier",
				allAttributes, 10000);
		trainingDataSet.setClassIndex(FEATURES_NUM);
		File file = new File(filename);
		int count = 0;
		String record = "";
		String[] dataset = new String[FEATURES_NUM + 1];
		double[] mfccmean = new double[FEATURES_NUM];
		String emotion = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file),
					(int) file.length());

			while ((record = in.readLine()) != null) {

				dataset = record.split("\t");
				for (int i = 0; i < FEATURES_NUM; i++) {
					mfccmean[i] = Double.valueOf(dataset[i]);
				}
				String[] predictEmotion = dataset[FEATURES_NUM].split("'");
				emotion = predictEmotion[1];
				count++;
				addInstance(trainingDataSet, mfccmean, emotion);
			}

			in.close();

		} catch (Exception e) {

		}
		return trainingDataSet;
	}
	
	/**
	 * Add Instance by weka
	 * @param trainingDataSet
	 * @param mfccmean
	 * @param emotion
	 */

	private void addInstance(Instances trainingDataSet, double[] mfccmean,
			String emotion) {
		Instance instance = createInstance(trainingDataSet, mfccmean, emotion);
		trainingDataSet.add(instance);
	}
	/**
	 * Create Instance by weka
	 * @param associatedDataSet
	 * @param mfccmean
	 * @param Emotion
	 * @return
	 */

	private Instance createInstance(Instances associatedDataSet,
			double[] mfccmean, String Emotion) {
		Instance instance = new Instance(FEATURES_NUM + 1);
		instance.setDataset(associatedDataSet);
		for (int i = 0; i < FEATURES_NUM; i++) {
			instance.setValue(i, mfccmean[i]);
		}
		instance.setValue(FEATURES_NUM, Emotion);
		return instance;
	}

	/**
	 * Learn Predictive Model by Weka
	 * @param learningDataSet
	 * @param classifier
	 * @return Classifier
	 * @throws Exception
	 */
	private Classifier learnPredictiveModel(Instances learningDataSet,
			Classifier classifier) throws Exception {
		classifier.buildClassifier(learningDataSet);
		return classifier;
	}
	
	/**
	 * Make Predictive Mdoel for Evaluation by Weka
	 * @param classifier
	 * @param learningDataset
	 * @return Evaluation
	 * @throws Exception
	 */

	public Evaluation evaluatePredictiveModel(Classifier classifier,
			Instances learningDataset) throws Exception {
		Evaluation learningSetEvaluation = new Evaluation(learningDataset);
		learningSetEvaluation.evaluateModel(classifier, learningDataset);
		return learningSetEvaluation;
	}

	/**
	 * get Classification Information
	 * @param learningDataset
	 * @param predictiveModel
	 * @param features
	 * @return int
	 * @throws Exception
	 */
	
	private int getClassifiedInformation(Instances learningDataset,
			Classifier predictiveModel, double[] features) throws Exception {
		Instance predictemotion = createInstance(learningDataset, features,
				label[0]);

		int emotionPrediction = (int) predictiveModel
				.classifyInstance(predictemotion);
		return emotionPrediction;
	}
	
	/**
	 * No Define
	 */

	@Override
	public String Classify(Object data) {
		// TODO Auto-generated method stub
		return null;
	}
}
