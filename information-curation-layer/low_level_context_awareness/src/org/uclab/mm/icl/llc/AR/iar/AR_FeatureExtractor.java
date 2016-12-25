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

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;

/**
 * This class is for extracting features.
 * It contains feature calculation functions and function to return feature list.
 * 
 * @author Tae ho Hur
 *
 */
public class AR_FeatureExtractor {

	/**
	 * get Zero Crossing
	 * @param signal double array(eg:accelerometer data)
	 * @return number of zero crossing
	 */
	static double getCrossing(double[] signal) {
		double crossing = 0;
		double mean = 0;
		mean = getAverage(signal);
		for (int i = 0; i < signal.length - 1; i++) {
			if ((signal[i] - mean) * (signal[i + 1] - mean) < 0)
				crossing += 1;
		}
		crossing /= signal.length;

		return crossing;
	}

	/**
	 * get Standard deviation
	 * @param signal double array(eg:accelerometer data)
	 * @return Standard deviation
	 */
	static double getStandardDeviation(double[] signal) {
		double variance = getVariance(signal);
		return Math.sqrt(variance);
	}

	/**
	 * get Max value
	 * @param signal double array(eg:accelerometer data)
	 * @return max value
	 */
	static double getMax(double[] signal) {
		double max = -9999;
		for (int j = 0; j < signal.length; j++) {
			if (signal[j] > max) {
				max = signal[j];
			}
		}
		return max;
	}

	/**
	 * get minimum value
	 * @param signal double array(eg:accelerometer data)
	 * @return minimum value
	 */
	static double getMin(double[] signal) {
		double min = 9999;
		for (int j = 0; j < signal.length; j++) {
			if (signal[j] < min) {
				min = signal[j];
			}
		}
		return min;
	}

	/**
	 * get sum of array data
	 * @param values double array(eg:accelerometer data)
	 * @return sum of array data
	 */
	public static double getSum(double[] values) {
		double sumData = 0;

		for (int i = 0; i < values.length; i++) {
			sumData += values[i];
		}
		return sumData;
	}

	/**
	 * get average of array
	 * @param values double array(eg:accelerometer data)
	 * @return average
	 */
	public static double getAverage(double[] values) {
		double avgData = 0;

		avgData = getSum(values) / values.length;

		return avgData;
	}

	/**
	 * get variance of array
	 * @param values double array(eg:accelerometer data)
	 * @return variance
	 */
	public static double getVariance(double[] values) {
		double varData = 0;
		double sum_varData = 0;
		double avg = getAverage(values);

		for (int i = 0; i < values.length; i++) {
			sum_varData += Math.pow((values[i] - avg), 2);
		}
		varData = sum_varData / values.length;

		return varData;
	}

	/**
	 * get covariance of two arrays
	 * @param values_A double array(eg:accelerometer data)
	 * @param values_B double array(eg:accelerometer data)
	 * @return covariance of two arrays
	 */
	public static double getCovariance(double[] values_A, double[] values_B) {
		double a_avg = getAverage(values_A);
		double b_avg = getAverage(values_B);
		double covarData = 0;

		double[] ab_avg = new double[values_A.length];

		for (int i = 0; i < values_A.length; i++) {
			ab_avg[i] = (values_A[i] - a_avg) * (values_B[i] - b_avg);
		}

		covarData = getAverage(ab_avg);

		return covarData;
	}

	/**
	 * get quartile value
	 * @param values double array(eg:accelerometer data)
	 * @return quartile
	 */
	public static double getQuartile(double[] values) {
		double[] result = sort(values);
		int quart = values.length / 4;
		return Math.abs((result[quart] + result[quart + 1]) / 2
				- (result[values.length - quart] + result[values.length - quart + 1]) / 2);
	}

	/**
	 * get mean value of abs
	 * @param double array(eg:accelerometer data)
	 * @return meanabs
	 */
	public static double getMeanAbs(double[] values) {
		double average = getAverage(values);
		double[] list = new double[values.length];
		for (int j = 0; j < values.length; j++) {
			list[j] = Math.abs(values[j] - average);
		}

		return getAverage(list);
	}

	/**
	 * get median value of abs
	 * @param double array(eg:accelerometer data)
	 * @return median value of abs
	 */
	public static double getMedianAbs(double[] values) {
		double median = getMedian(values);
		double list[] = new double[values.length];
		for (int j = 0; j < values.length; j++) {
			list[j] = Math.abs(values[j] - median);
		}
		return getMedian(list);
	}

	
	/**
	 * get median value
	 * @param values double array(eg:accelerometer data)
	 * @return median value
	 */
	public static double getMedian(double[] values) {
		double[] result = sort(values);
		if (result.length % 2 == 1) {
			return result[(result.length / 2) + 1];
		} else {
			double a, b;
			a = result[(result.length / 2) + 1];
			b = result[(result.length / 2)];
			return (a + b) / 2.;
		}
	}

	/**
	 * get range of array
	 * @param value double array(eg:accelerometer data)
	 * @return maximum data - minimum data
	 */
	public static double getRange(double[] value) {
		return getMax(value) - getMin(value);
	}

	/**
	 * get geomean of array
	 * @param value double array(eg:accelerometer data)
	 * @return geomean of array
	 */
	public static double getGeomean(double[] value) {
		double result = 1;
		for (int j = 0; j < value.length; j++) {
			result *= value[j];
		}
		return Math.pow(result, (1 / value.length));
	}

	/**
	 * sort array
	 * @param value double array(eg:accelerometer data)
	 * @return sorted array
	 */
	public static double[] sort(double[] value) {
		double[] result = new double[value.length];
		for (int j = 0; j < value.length; j++) {
			result[j] = value[j];
		}

		for (int j = 0; j < result.length; j++) {
			for (int k = 0; k < result.length - 1; k++) {
				if (result[k] > result[k + 1]) {
					double temp = result[k];
					result[k] = result[k + 1];
					result[k + 1] = result[k];
				}
			}
		}

		return result;
	}

	/**
	 * get harmonic value of array
	 * @param double array(eg:accelerometer data)
	 * @return harmonic value of array
	 */
	public static double getHarmonic(double[] values) {
		double sum = 0.0;

		for (int j = 0; j < values.length; j++) {
			sum += 1.0 / values[j];
		}

		return sum;
	}

	/**
	 * 
	 * @param values double array to get kurtosis
	 * @return kurtosis of the input array
	 */
	public static double getKurtosis(double[] values) {
		Kurtosis ks = new Kurtosis();
		return ks.evaluate(values);
	}

	public static double getTrimMean(double[] values) {
		double sum = 0.0;
		for (int j = 1; j < values.length - 1; j++) {
			sum += values[j];
		}
		return sum / (values.length - 2);
	}

	/**
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return 
	 */
	
	/**
	 * get integrated model's featured data(with 50 features)
	 * @param SaccX Smartphone's accelerometer dataX
	 * @param SaccY Smartphone's accelerometer dataY
	 * @param SaccZ Smartphone's accelerometer dataZ
	 * @param WaccX Smart watch's accelerometer dataX
	 * @param WaccY Smart watch's accelerometer dataY
	 * @param WaccZ Smart watch's accelerometer dataZ
	 * @param SgyroX Smartphone's gyroscope dataX
	 * @param SgyroY Smartphone's gyroscope dataY
	 * @param SgyroZ Smartphone's gyroscope dataZ
	 * @param WgyroX Smart watch's gyroscope dataX
	 * @param WgyroY Smart watch's gyroscope dataY
	 * @param WgyroZ Smart watch's gyroscope dataZ
	 * @return feature list.
	 */
	public static double[] MiningMindsV2_151005_integrated50(double[] SaccX, double[] SaccY, double[] SaccZ,
			double[] WaccX, double[] WaccY, double[] WaccZ, double[] SgyroX, double[] SgyroY, double[] SgyroZ,
			double[] WgyroX, double[] WgyroY, double[] WgyroZ) {

		double[] feature = new double[49];

		feature[0] = getRange(WgyroZ);
		feature[1] = getMax(SaccY);
		feature[2] = getRange(SaccZ);
		feature[3] = getStandardDeviation(SaccY);
		feature[4] = getStandardDeviation(SaccZ);
		feature[5] = getQuartile(SaccY);
		feature[6] = getQuartile(SaccZ);
		feature[7] = getMeanAbs(SaccY);
		feature[8] = getMeanAbs(SaccZ);
		feature[9] = getMedianAbs(SaccY);
		feature[10] = getMedianAbs(SaccZ);
		feature[11] = getStandardDeviation(SgyroX);
		feature[12] = getMeanAbs(SgyroX);
		feature[13] = getMax(WaccX);
		feature[14] = getStandardDeviation(WgyroZ);
		feature[15] = getMeanAbs(WgyroZ);
		feature[16] = getAverage(SaccZ);
		feature[17] = getMedian(SaccZ);
		feature[18] = getRange(SaccY);
		feature[19] = getTrimMean(SaccZ);
		feature[20] = getStandardDeviation(SaccX);
		feature[21] = getMeanAbs(SaccX);
		feature[22] = getMedianAbs(SaccX);
		feature[23] = getMin(SgyroX);
		feature[24] = getMin(SgyroY);
		feature[25] = getRange(SgyroX);
		feature[26] = getRange(SgyroY);
		feature[27] = getGeomean(SgyroX);
		feature[28] = getQuartile(SgyroX);
		feature[29] = getMedianAbs(SgyroX);
		feature[30] = getMedianAbs(SgyroZ);
		feature[31] = getStandardDeviation(WaccX);
		feature[32] = getQuartile(WaccX);
		feature[33] = getMeanAbs(WaccX);
		feature[34] = getMedianAbs(WaccX);
		feature[35] = getMin(WgyroZ);
		feature[36] = getStandardDeviation(WgyroY);
		feature[37] = getGeomean(WgyroY);
		feature[38] = getGeomean(WgyroZ);
		feature[39] = getQuartile(WgyroY);
		feature[40] = getQuartile(WgyroZ);
		feature[41] = getMeanAbs(WgyroY);
		feature[42] = getMedianAbs(WgyroY);
		feature[43] = getMedianAbs(WgyroZ);
		feature[44] = getMin(SaccZ);
		feature[45] = getRange(SaccX);
		feature[46] = getVariance(SaccY);
		feature[47] = getGeomean(SaccY);
		feature[48] = getGeomean(SaccZ);

		return feature;

	}
		
	/**
	 * get each device's featured data(basic feature lists)
	 * @param accX Device's accelerometer dataX
	 * @param accY Device's accelerometer dataY
	 * @param accZ Device's accelerometer dataZ
	 * @param gyroX Device's gyroscope dataX
	 * @param gyroY Device's gyroscope dataY
	 * @param gyroZ Device's gyroscope dataZ
	 * @return
	 */
	public static double[] MiningMindsV2_150923_basic42(double[] accX, double[] accY, double[] accZ, double[] gyroX,
			double[] gyroY, double[] gyroZ) {
		double[] feature = new double[42];

		feature[0] = getAverage(accX);
		feature[1] = getAverage(accY);
		feature[2] = getAverage(accZ);
		feature[3] = getMax(accX);
		feature[4] = getMax(accY);
		feature[5] = getMax(accZ);
		feature[6] = getMin(accX);
		feature[7] = getMin(accY);
		feature[8] = getMin(accZ);
		feature[9] = getStandardDeviation(accX);
		feature[10] = getStandardDeviation(accY);
		feature[11] = getStandardDeviation(accZ);
		feature[12] = getCrossing(accX);
		feature[13] = getCrossing(accY);
		feature[14] = getCrossing(accZ);
		feature[15] = getQuartile(accX);
		feature[16] = getQuartile(accY);
		feature[17] = getQuartile(accZ);
		feature[18] = getMeanAbs(accX);
		feature[19] = getMeanAbs(accY);
		feature[20] = getMeanAbs(accZ);

		feature[21] = getAverage(gyroX);
		feature[22] = getAverage(gyroY);
		feature[23] = getAverage(gyroZ);
		feature[24] = getMax(gyroX);
		feature[25] = getMax(gyroY);
		feature[26] = getMax(gyroZ);
		feature[27] = getMin(gyroX);
		feature[28] = getMin(gyroY);
		feature[29] = getMin(gyroZ);
		feature[30] = getStandardDeviation(gyroX);
		feature[31] = getStandardDeviation(gyroY);
		feature[32] = getStandardDeviation(gyroZ);
		feature[33] = getCrossing(gyroX);
		feature[34] = getCrossing(gyroY);
		feature[35] = getCrossing(gyroZ);
		feature[36] = getQuartile(gyroX);
		feature[37] = getQuartile(gyroY);
		feature[38] = getQuartile(gyroZ);
		feature[39] = getMeanAbs(gyroX);
		feature[40] = getMeanAbs(gyroY);
		feature[41] = getMeanAbs(gyroZ);

		return feature;

	}
	
}
