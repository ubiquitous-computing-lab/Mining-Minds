# Service Curation Layer (SCL)
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)

--------------------------

# Table Of Contents
- [1. Introduction](#1-introduction)
- [2. Setup](#2-setup)
  - [2.1 Prerequisites](#2.1-prerequisites)
  - [2.2 Clone or Download zip](#2.2-clone-or-download-zip)
  - [2.3 Build with Maven](#2.3-build-with-maven)
  - [2.4 How to run](#2.4-how-to-run)
    - [2.4.1 Run in Eclipse](#2.4.1-run-in-eclipse)
    - [2.4.2 Deploy inside tomcat](#2.4.2-deploy-inside-tomcat)
- [3. Components of SCL](#3-components-of-scl)
  - [3.1 Recommendation Builder](#3.1-recommendation-builder)
  - [3.2 Recommendation Interpreter](#3.2-recommendation-interpreter)
  - [3.3 Orchestrator](#3.3-orchestrator)
- [4. Authors](#4-authors)
- [5. License](#5-license)
  
# 1. Introduction

**Service Curation Layer** `(SCL)` provides recommendations based on user's physiological and contextual information. Physiological recommendation is based on production rules from the wellness domain. Whereas, contextual matrix, based on data collected from different users, provides the basis for providing cross-context contextual recommendations. It is important to note that the contextual considerations supplement the phyisological factors of the recommendations. 

# 2. Setup
## 2.1 Prerequisites
#### Download & install the following prerequisites
- Download & Install [Maven]([https://www.apache.org/dyn/closer.cgi)
- Downlaod & Install [Tomcat server](http://tomcat.apache.org/)

## 2.2 Clone or Download zip
#### clone scl-miningmind-2.0 OR download its zip file
* `git clone  https://eccentric-bit@bitbucket.org/bit-whacker/scl-miningmind-2.5.git`
* [Download zip](https://eccentric-bit@bitbucket.org/bit-whacker/scl-miningmind-2.5.git)

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
* Copy `target/scl-miningmind.war` to Tomcat's webapps directory e.g. 
* `C:\apache\apache-tomcat-7.0.70\webapps` for example
* Run tomcat e.g. move to `C:\apache\apache-tomcat-7.0.70\bin` and run one of the following depending on the `OS`
* window: `startup.bat`, linux: `startup.sh`

Navigate to browser and the service curation framework will be listening on `port: 8080`
e.g. `localhost:8080/scl-miningmind-2.5/rest/`


# 3. Components of SCL
## 3.1 Recommendation Builder
\description
## 3.2 Recommendation Interpreter
\description
## 3.3 Orchestrator
\description

# 4. Authors

- Muhamamd Afzal  `muhammad.afzal@oslab.khu.ac.kr`
- Muhammad Sadiq  `sadiq@oslab.khu.ac.kr`
- Syed Imran Ali  `imran.ali@oslab.khu.ac.kr`

# 5. License
The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
