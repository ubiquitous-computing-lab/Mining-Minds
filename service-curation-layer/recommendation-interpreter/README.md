# Recommendation Interpreter (RI)
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)

--------------------------

# Table Of Contents
- [1. Introduction](#1-introduction)
- [2. Core Implementation](#2-core-implementation)
 - [2.1 Context Interpreter](#2.1-context-interpreter)
  - [2.2 Content Interpreter](#2.2-content-interpreter)
  - [2.3 Explanation Manager](#3.3-explanation-manager)
- [3. Setup](#3-setup)
  - [3.1 Prerequisites](#3.1-prerequisites)
  - [3.2 Clone or Download zip](#3.2-clone-or-download-zip)
  - [3.3 Build with Maven](#3.3-build-with-maven)
  - [3.4 How to run](#3.4-how-to-run)
    - [3.4.1 Run in Eclipse](#3.4.1-run-in-eclipse)
    - [3.4.2 Deploy inside tomcat](#3.4.2-deploy-inside-tomcat)
- [4. Authors](#4-author)
- [5. License](#5-license)
  
# 1. Introduction

**Recommendation Interpreter (RI) is envisioned as a contextual information processing unit of a larger recommendation generating system. In the current, concept-of-proof implementation, RI deals with two kinds of recommendations i.e. physical activity based recommendation and food-items recommendation. The main purpose of RI is to complement the recommendation generated based on user’s physiological requirements or conditions. RI takes into account contextual information of the user in terms of location of the user, user’s current activity, weather conditions, and emotional state of the user. Physical activity recommendations are based on a novel concept called “Contextual Matrix”. Contextual Matrix processes users’ preference data and in turn produces cross-contextual global patterns. This contextual matrix is populated using users’ surveyed data.

# 2. Core Implementation

**Recommendation Interpreter has three core componenents

##2.1 Context Interpreter
#### Content Interpreter evalutes interruptibility of a user. Contextual information consists of current context i.e. current location, activity, weather conditions, and emotional state of the user. Based on this contextual information it is decided whether the use is available to recevie recommendation or not.

##2.2 Content Interpreter
#### Content Interpreter evalautes the viability of the received recommendation against the current context of the user. In case the received recommendation is not deemed suitable in the current content then alternative recommendation is generated.

##2.3 Explanation Manager
#### Explanation manager provides explanatory note and audio/visual aids along with the final generated recommendation.

# 2. Setup
## 2.1 Prerequisites
#### Download & install the following prerequisites
- Download & Install [Maven]([https://www.apache.org/dyn/closer.cgi)
- Downlaod & Install [Tomcat server](http://tomcat.apache.org/)

## 2.2 Clone or Download zip
#### clone scl-miningmind-2.0 OR download its zip file
* `git clone  https://eccentric-bit@bitbucket.org/bit-whacker/recommendation-interpreter.git`
* [Download zip](https://eccentric-bit@bitbucket.org/bit-whacker/recommendation-interpreter.git)

## 2.3 Build with Maven
#### run the following command from project's root directory
$ `mvn clean package`

## 2.4 How to run
### 2.4.1 Run in Eclipse
#### To run in eclipse do the following steps
##### Import project in Eclipse:
* File > Import 
* Expand `Maven` and select `Existing Maven Projects` 
* Select root directory of the project and click `Finish`

Once the project is loaded now Right Click on the project, 
* Run as > Run on Server
Note: you have to attach runtime with the project before running on server

### 2.4.2 Deploy inside tomcat
#### To deploy inside tomcat do the following steps
* Copy `target/recommendation-interpreter.war` to Tomcat's webapps directory e.g. 
* `C:\apache\apache-tomcat-7.0.70\webapps` for example
* Run tomcat e.g. move to `C:\apache\apache-tomcat-7.0.70\bin` and run one of the following depending on the `OS`
* window: `startup.bat`, linux: `startup.sh`

Navigate to browser and the service curation framework will be listening on `port: 8080`
e.g. `localhost:8080/recommendation-interpreter/rest/`

# 3. Authors
- Syed Imran Ali  `imran.ali@oslab.khu.ac.kr`

# 5. License
The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)