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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.uclab.mm.icl.utils.FileUtil;

public class AR_makeARFF {

	String device_header = "@relation sm_acc_gyro\n" + "@attribute accX_average REAL\n"
			+ "@attribute accY_average REAL\n" + "@attribute accZ_average REAL\n" + "@attribute accX_max REAL\n"
			+ "@attribute accY_max REAL\n" + "@attribute accZ_max REAL\n" + "@attribute accX_min REAL\n"
			+ "@attribute accY_min REAL\n" + "@attribute accZ_min REAL\n" + "@attribute accX_deviation REAL\n"
			+ "@attribute accY_deviation REAL\n" + "@attribute accZ_deviation REAL\n"
			+ "@attribute accX_crossing REAL\n" + "@attribute accY_crossing REAL\n"
			+ "@attribute accZ_crossing REAL\n" + "@attribute accX_quartile REAL\n"
			+ "@attribute accY_quartile REAL\n" + "@attribute accZ_quartile REAL\n"
			+ "@attribute accX_meanabs REAL\n" + "@attribute accY_meanabs REAL\n" + "@attribute accZ_meanabs REAL\n"
			+ "@attribute gyroX_average REAL\n" + "@attribute gyroY_average REAL\n"
			+ "@attribute gyroZ_average REAL\n" + "@attribute gyroX_max REAL\n" + "@attribute gyroY_max REAL\n"
			+ "@attribute gyroZ_max REAL\n" + "@attribute gyroX_min REAL\n" + "@attribute gyroY_min REAL\n"
			+ "@attribute gyroZ_min REAL\n" + "@attribute gyroX_deviation REAL\n"
			+ "@attribute gyroY_deviation REAL\n" + "@attribute gyroZ_deviation REAL\n"
			+ "@attribute gyroX_crossing REAL\n" + "@attribute gyroY_crossing REAL\n"
			+ "@attribute gyroZ_crossing REAL\n" + "@attribute gyroX_quartile REAL\n"
			+ "@attribute gyroY_quartile REAL\n" + "@attribute gyroZ_quartile REAL\n"
			+ "@attribute gyroX_meanabs REAL\n" + "@attribute gyroY_meanabs REAL\n"
			+ "@attribute gyroZ_meanabs REAL\n"
			+ "@attribute action {Eating,Running,Sitting,Standing,Walking,Stretching,Sweeping,LyingDown}\n"
			+ "@data\n";
	String integrated_header = "@relation integrated30_w3\n" + "@attribute range_wgyroz REAL\n"
			+ "@attribute max_saccy REAL\n" + "@attribute range_saccz REAL\n" + "@attribute std_saccy REAL\n"
			+ "@attribute std_saccz REAL\n" + "@attribute quartile_saccy REAL\n"
			+ "@attribute quartile_saccz REAL\n" + "@attribute meanabs_saccy REAL\n"
			+ "@attribute meanabs_saccz REAL\n" + "@attribute medianabs_saccy REAL\n"
			+ "@attribute medianabs_saccz REAL\n" + "@attribute std_sgyrox REAL\n"
			+ "@attribute meanabs_sgyrox REAL\n" + "@attribute max_waccx REAL\n" + "@attribute std_wgyroz REAL\n"
			+ "@attribute meanabs_wgyroz REAL\n" + "@attribute average_saccz REAL\n"
			+ "@attribute median_saccz REAL\n" + "@attribute range_saccy REAL\n"
			+ "@attribute trimmean_saccz REAL\n" + "@attribute std_saccx REAL\n" + "@attribute meanabs_saccx REAL\n"
			+ "@attribute medianabs_saccx REAL\n" + "@attribute min_sgyrox REAL\n" + "@attribute min_sgyroy REAL\n"
			+ "@attribute range_sgyrox REAL\n" + "@attribute range_sgyroy REAL\n"
			+ "@attribute geomean_sgyrox REAL\n" + "@attribute quartile)sgyrox REAL\n"
			+ "@attribute medianabs_sgyrox REAL\n" + "@attribute a1 REAL\n" + "@attribute a2 REAL\n"
			+ "@attribute a3 REAL\n" + "@attribute a4 REAL\n" + "@attribute a5 REAL\n" + "@attribute a6 REAL\n"
			+ "@attribute a7 REAL\n" + "@attribute a8 REAL\n" + "@attribute a9 REAL\n" + "@attribute a10 REAL\n"
			+ "@attribute a11 REAL\n" + "@attribute a12 REAL\n" + "@attribute a13 REAL\n" + "@attribute a14 REAL\n"
			+ "@attribute a15 REAL\n" + "@attribute a16 REAL\n" + "@attribute a17 REAL\n" + "@attribute a18 REAL\n"
			+ "@attribute a19 REAL\n"
			+ "@attribute action {Eating,Running,Sitting,Standing,Walking,Stretching,Sweeping,LyingDown}\n"
			+ "@data\n";
	
	public AR_makeARFF(	double[][] acc_wm, double[][] gy_wm, double[][] acc_sm, double[][] gy_sm) throws IOException {
		double[] sm_result = AR_FeatureExtractor.MiningMindsV2_150923_basic42(acc_sm[0], acc_sm[1], acc_sm[2],
				gy_sm[0], gy_sm[1], gy_sm[2]);
		double[] wm_result = AR_FeatureExtractor.MiningMindsV2_150923_basic42(acc_sm[0], acc_sm[1], acc_sm[2],
				gy_sm[0], gy_sm[1], gy_sm[2]);
		double[] in_result = AR_FeatureExtractor.MiningMindsV2_151005_integrated50(acc_sm[0], acc_sm[1], acc_sm[2],
				acc_wm[0], acc_wm[1], acc_wm[2], gy_sm[0], gy_sm[1], gy_sm[2], gy_wm[0], gy_wm[1], gy_wm[2]);

		// Make smartphone feature list ARFF file
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FileUtil.getRootPath() + "ar/" + "/sm.txt")));
		bw.write(device_header);
		for (int j = 0; j < sm_result.length; j++) {
			bw.write(sm_result[j] + ",");
		}
		bw.write("Sweeping"); // Temporarily saving arbitrary label to comply the ARFF form
		bw.close();

		// Make wearable feature list ARFF file
		bw = new BufferedWriter(new FileWriter(new File(FileUtil.getRootPath() + "/ar/" + "/wm.txt")));
		bw.write(device_header);
		for (int j = 0; j < wm_result.length; j++) {
			bw.write(wm_result[j] + ",");
		}
		bw.write("Sweeping"); // Temporarily saving arbitrary label to comply the ARFF form
		bw.close();

		// Make integrated feature list ARFF file
		bw = new BufferedWriter(new FileWriter(new File(FileUtil.getRootPath() + "/ar/" + "/integrated.txt")));
		bw.write(integrated_header);
		for (int j = 0; j < in_result.length; j++) {
			bw.write(in_result[j] + ",");
		}
		bw.write("Sweeping"); // Temporarily saving arbitrary label to comply the ARFF form
		bw.close();
	}
}
