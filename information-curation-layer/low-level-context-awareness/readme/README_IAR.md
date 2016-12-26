# Name of the Component

<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)


--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)
    - [1.1 Core concepts](#11-core-concepts)
   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
    - [3.1 Use bullets](#31-use-bullets)
   
- [4. Contributing](#4-contributing)
    - [4.1 Optional](#41-optional)
   
- [5. Author](#5-author)

- [6. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

- The Inertial based Activity Recognizer (IAR) is designed to infer user physical activity such as ¡°walking¡± or ¡°running¡± using inertial sensors which are mainly accelerometer and gyroscope. 
- IAR currently uses two devices which are smartphone and smartwatch. 
- The operation flow of IAR is similar to general activity recognition flow as data input, segmentation, feature extraction and classification. 
- In our work, we use weighted voting decision fusion to conclude the final activity.


## 1.1 Core concepts

- Example:
- Module: Modules are separated by different function, every module can communicate with other one through their own services.
- Service: Services are the interface of the specifically module.


# 2. Getting Started

This consist of the following sub-headings. 


## 2.1 Requirements

-This component requires Java Development Kit and Weka tools to be installed.
- Our setup envirionment was JDK v1.7 and Weka 3.8.


## 2.2 Installation

- No extra installation is required to run this component.
- Only development tool such as eclipse is required.


## 2.3 Usage

- Running the ICL, this component will start in the ICL part through Inertial Activity Recognizer class.


# 3. Features

- Feature extraction: Basic feature extraction functions included are zero crossing, standard deviation, max, min, average, variance, covariance, quartile, meanAbs, median, range, geomean, harmonic and kurtosis
                      The main function receives each sensor data and calculates each feature, and then return the feature vector. 
		      There are two basic functions which are for integrated model with 50 features and for each device with 42 features.
- Make ARFF file: The ARFF file is required to run the WEKA tool. This file includes the list (name) of features and feature values. 
		  We have predefined the feature list to be included in the ARFF file. 
 	          The main function makes the ARFF file. It sends the sensor data to feature extraction module to extract the features and get returned. 
		  As we use three different AR model, it created three separate ARFF files in the predefined path. 
- Classification: This class executes the Weka based on the current sensor data and conclude the activity.
- Decision fusion: This class will gather each AR model¡¯s inferred activity label, and then conclude the final activity based on the weight.
                   The weight is allocated based on our offline experiment result. 
- Activity Recognizer: This class exeuctes the overall AR process from sensor data acqusition to feature extraction, classification, decision fusion and conclude the final activity.


# 4. Contributing

- Feature extraction: You may include any of the features you want to extract by inserting the feature extraction functions.
		      And also change the constitution of the main feature extraction functions based on your preference.
- Make ARFF file: You may insert or delete the features according to what features you want to use.
		  You may also change the path you want to save the file.
- Decision fusion: You may change the according to your own criteria. 


# 5. Author

-  Name: Tae ho Hur
-  email: hth@oslab.khu.ac.kr


# 6. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
<br>
 


