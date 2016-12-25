# Data Acquisition and Synchronization

<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)


--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)

   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
    - [3.1 Use bullets](#31-use-bullets)
     
- [4. Author](#5-author)

- [5. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

Sensory Data Acquisition and Synchronization subcomponent obtains the raw sensory data from multi-modal data sources, both in a real-time (active) and off-line (passive) manner. This data is synchronized based upon the user identification and the time stamp of the data generation, and subsequently, it is queued for the context
determination.
Implementation of Raw Sensory Data Acquisition and Synchronization (DAS) consists of a REST service that collects raw sensory data from multi-modal data sources. The key in this acquisition is the association of accumulated data with their time of origination. All data sources subsist independently along with independent clocks; therefore, a logical clock is required for identifying the data origination at the same time from multiple sources. Consequently, DAS implements the time frame-based synchronization methods called Complete- and Incomplete-sync. Complete-sync is the sunny day scenario in which all the data pertaining to an instance of time is accumulated within the specified time frame. It waits for the data accumulation from all the data sources at the DCF server within the duration of a three second time frame. As soon as all the data is received, complete-sync encapsulates it as one message. Subsequently, the message is timestamped and enqueued in Sensory Data Queue for context determination by a health and wellness platform. In contrast to Complete-sync, Incomplete-sync executes when data from any of the sources is not received in the three-second time frame.

# 2. Getting Started

This consist of the following sub-headings. 


## 2.1 Requirements

+ 'node js' (minimum version: 5.8)
	+ 'express' (minimum version: 4.14)
	+ 'lokijs' (minimum version: 1.4.1)
	+ 'body-parser' (minimum version: 1.15.2)
	+ 'fs' (minimum version: 0.0.1)
	+ 'util' (minimum version: 0.10.3)
	+ 'process' (minimum version: 0.11.9)
	+ 'jsonfile' (minimum version: 2.3.1)
	+ ‘request’ (minimum version: 2.76.0)
	+ ‘multer’ (minimum version: 1.2.0)



## 2.2 Installation

	+ Install Node JS (minimum version: 5.8.0)
	+ Install following packages using npm command (npm install ):
		-'express' (minimum version: 4.14)
		-'lokijs' (minimum version: 1.4.1)
		-'body-parser' (minimum version: 1.15.2)
		-'requestify' (minimum version: 0.2.3)
		-'fs' (minimum version: 0.0.1)
		-'util' (minimum version: 0.10.3)
		-'process' (minimum version: 0.11.9)
		-'jsonfile' (minimum version: 2.3.1)
		-‘request’ (minimum version: 2.76.0)
		-‘multer’ (minimum version: 1.2.0)
	+ Put the source files all together in the same folder.
	+ Start synchronization server by using this command: node sync_code.js


## 2.3 Usage

There are two kinds of tests for demonstration. The first one is the normal test for raw sensors data. The procedure for this test is described as follows:
	+ For testing purpose, start two more sink services, namely ‘rev_server.js’ and ‘rev_server2.js’. These services are used to receive the synced data from synchronization services. The received data would be print out for confirmation purpose.
	+ Start the ‘simulation.html’ from your browser. This html file sends a number of simulated sensors data to the synchronization service (sync_code.js). 
	+ If everything goes right, you can see the synced data coming to sink services (‘rev_server.js’ and ‘rev_server2.js’).

The second test is multimedia test. This test is used to check whether the synchronization services can well handle the image/audio/video resources or not. The procedure for this test is described as follows:
	+ Create ‘uploads’ folder in the same level with the file ‘sync_code.js’. This step can be skipped if the aforementioned folder is existed.
	+ Start ‘fileUpload.html’ from your browser.
	+ In ‘fileUpload.html’ page, choose an arbitrary image/audio/video to upload.
	+ If everything goes right, you can see the synced data coming to sink services (‘rev_server.js’ and ‘rev_server2.js’).

# 3. Features

	+ Feature 1: Support huge number of sensors (up to 1280 sensors).
	+ Feature 2: Scalable data structure.


# 4. Author

Name: Dinh-Mao Bui.
Email: mao.bui@khu.ac.kr


# 5. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
<br>
 

