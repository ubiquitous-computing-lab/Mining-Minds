package org.uclab.mm.icl.llc.MachineLearningTools;

import java.util.Arrays;

import mm.icl.llc.MachineLearningTools.FeatureExtraction;

/**
 * This Class extract statistical feature such as mean, stddev, etc.
 * @author Bang Gae
 *
 */

public class StatisticalFeatureExtraction extends FeatureExtraction {
	
	/**
	 * No Define
	 */
	@Override
	public Object extractFeature(Object input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***
	 * Extract Average
	 * @param doubles
	 * @return double
	 */
	
	public double getMean(double[] doubles) {
		double result = 0;
		for (int i = 0; i < doubles.length; i++) {
			result = result + doubles[i];
		}
		result = result / doubles.length;
		return result;
	}
	/**
	 * Normalize Data
	 * @param doubles
	 * @return double[]
	 */

	public double[] normalization(double[] doubles) {
		double[] result = new double[doubles.length];
		double mean = getMean(doubles);
		double diff = 0;
		double sum = 0;
		double Var = 0;
		for (int i = 0; i < doubles.length; i++) {
			diff = doubles[i] - mean;
			sum += (diff * diff);
		}
		Var = sum / doubles.length;

		for (int i = 0; i < doubles.length; i++) {
			result[i] = (doubles[i] - mean) / Var;
		}
		return result;
	}
	/**
	 * Extract Standard Deviation
	 * @param doubles
	 * @return double
	 */

	public double getStdDev(double[] doubles) {
		double result = 0;
		double mean = getMean(doubles);
		double diff = 0;
		double sum = 0;
		for (int i = 0; i < doubles.length; i++) {
			diff = doubles[i] - mean;
			sum += (diff * diff);
		}
		result = Math.sqrt(sum / doubles.length - 0);
		return result;
	}
	
	/**
	 * Extract Minimum
	 * @param doubles
	 * @return double
	 */

	public double getMin(double[] doubles) {
		Arrays.sort(doubles);
		return doubles[0];
	}
	/**
	 * Extract Maximum
	 * @param doubles
	 * @return double
	 */

	public double getMax(double[] doubles) {
		Arrays.sort(doubles);
		return doubles[doubles.length - 1];
	}
	
	/**
	 * Extract Energy
	 * @param doubles
	 * @return double[]
	 */

	private double[] getEnergy(double[] doubles) {
		double[] result = new double[doubles.length];
		for (int i = 0; i < doubles.length; i++) {
			result[i] = (doubles[i] * doubles[i]);
		}
		return result;
	}
	/**
	 * Extract Energy Average
	 * @param doubles
	 * @return double
	 */

	public double getEnergyMean(double[] doubles) {
		double[] energy = getEnergy(doubles);
		return getMean(energy);
	}
	/**
	 * Extract Energy Standard Deviation
	 * @param doubles
	 * @return double
	 */

	public double getEnergyStdDev(double[] doubles) {
		double[] energy = getEnergy(doubles);
		return getStdDev(energy);
	}
	/**
	 * Extract Energy Maximum
	 * @param doubles
	 * @return double
	 */

	public double getEnergyMax(double[] doubles) {
		double[] energy = getEnergy(doubles);
		Arrays.sort(energy);
		return energy[energy.length - 1];
	}

	/**
	 * Extract Energy Median
	 * @param doubles
	 * @return double
	 */
	public double getEnergyMedian(double[] doubles) {
		double[] energy = getEnergy(doubles);
		return getMedian(energy);

	}
	/**
	 * Extract Median
	 * @param doubles
	 * @return double
	 */

	public double getMedian(double[] doubles) {
		if (doubles.length == 0)
			return Double.NaN;
		int center = doubles.length / 2;

		if (doubles.length % 2 == 1) {
			return doubles[center];
		} else {
			return (doubles[center - 1] + doubles[center]) / 2.0;
		}
	}

	

}
