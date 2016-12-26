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

/**
 * This class is to fuse the decision among smartphone, smartwatch and integrated AR model
 * 
 * @author Tae ho Hur
 *
 */

public class AR_DecisionFusion {
	int[][] table;  // Contains the list of activity

	/**
	 * This function returns the activity label
	 * @param input activity integer label
	 * @return string activity label
	 */
	public static String getActivity(int input) {
		if (input == 0) {
			return "Eating";
		} else if (input == 1) {
			return "Running";
		} else if (input == 2) {
			return "Sitting";
		} else if (input == 3) {
			return "Standing";
		} else if (input == 4) {
			return "Walking";
		} else if (input == 5) {
			return "Stretching";
		} else if (input == 6) {
			return "Sweeping";
		} else if (input == 7) {
			return "LyingDown";
		} else
			return "UnidentifiedActivity";
	}

/**
 * Converts the string activity into integer
 * @param arg String activity to convert
 * @return converted integer label
 */
	public static int getActivityNum(String arg) {
		arg = arg.toLowerCase();
		if (arg.equals("eating")) {
			return 0;
		} else if (arg.equals("running")) {
			return 1;
		} else if (arg.equals("sitting")) {
			return 2;
		} else if (arg.equals("standing")) {
			return 3;
		} else if (arg.equals("walking")) {
			return 4;
		} else if (arg.equals("stretching")) {
			return 5;
		} else if (arg.equals("sweeping")) {
			return 6;
		} else if (arg.equals("lyingdown")) {
			return 7;
		}
		return 10;
	}

	/**
	 * This function finds the activity with the most weight
	 * @param arg activity to find the biggest weight
	 * @return activity with the biggest weight
	 */
	public int findBiggestActivity(int arg[]) {

		int big_point = -1;
		double big_num = -1;

		for (int j = 0; j < arg.length; j++) {
			if (arg[j] > big_num) {
				big_num = arg[j];
				big_point = j;
			}
		}

		return big_point;
	}

	/**
	 * This function returns the final concluded activity based on the weight
	 * @param in
	 * @param sm
	 * @param wm
	 * @return
	 */
	public String getFinalActivity(String in, String sm, String wm) {

		int[][] result_table = new int[4][8];
		
		for(int j = 0 ; j < 4 ; j++) {
			for(int k = 0 ; k < 8; k++) {
				result_table[j][k] = 0;
			}
		}

		result_table[0][getActivityNum(in)] = table[0][getActivityNum(in)]; // integrated
		result_table[1][getActivityNum(sm)] = table[1][getActivityNum(sm)]; // sm
		result_table[2][getActivityNum(wm)] = table[2][getActivityNum(wm)]; // wm

		for (int l = 0; l < 8; l++) {
			result_table[3][l] = result_table[2][l] + result_table[1][l] + result_table[0][l];
		}

		int act = findBiggestActivity(result_table[3]);
		return getActivity(act);
	}

	/**
	 * This function allocates weight to each AR model's activity
	 */
	public AR_DecisionFusion() {

	      table = new int[3][8];

	      // integrated    
	      table[0][0] = 2;
	      table[0][1] = 1;
	      table[0][2] = 1;
	      table[0][3] = 1;
	      table[0][4] = 2;
	      table[0][5] = 2;
	      table[0][6] = 3;
	      table[0][7] = 2;
	      
	      // smartphone
	      table[1][0] = 0;
	      table[1][1] = 1;
	      table[1][2] = 2;
	      table[1][3] = 2;
	      table[1][4] = 2;
	      table[1][5] = 1;
	      table[1][6] = 0;
	      table[1][7] = 2;
	     
	      // wearable
	      table[2][0] = 0;
	      table[2][1] = 3;
	      table[2][2] = 2;
	      table[2][3] = 2;
	      table[2][4] = 1;
	      table[2][5] = 1;
	      table[2][6] = 1;
	      table[2][7] = 2;
	}
}
