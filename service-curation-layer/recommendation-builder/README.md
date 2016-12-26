# Recommendation Builder (RB)
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![JavaDoc-Version](https://img.shields.io/badge/JavaDoc-Version%202.5-green.svg)](https://ubiquitous-computing-lab.github.io/mining-minds/doc/scl-doc/rb/doc/index.html)

--------------------------

# Table Of Contents
- [1. Introduction](#1-introduction)
- [2. Core Implementation](#2-core-implementation)
- [3. Setup](#2-setup)
  - [3.1 Prerequisites](#2.1-prerequisites)
  - [3.2 Clone or Download zip](#2.2-clone-or-download-zip)
  - [3.3 Build with Maven](#2.3-build-with-maven)
  - [3.4 How to run](#2.4-how-to-run)
    - [3.4.1 Run in Eclipse](#2.4.1-run-in-eclipse)
    - [3.4.2 Deploy inside tomcat](#2.4.2-deploy-inside-tomcat)
- [4. Authors](#4-authors)
- [5. License](#5-license)
  
# 1. Introduction
Recommendation Builder, generates recommendations through reasoning on the user proﬁle and life-log data and the knowledge rules developed in a specific format. RB provided recommendations are considered as initial recommendation because of the fact that the recommendations are yet to be interpreted from the user’s contextual perspective. The initial recommendation may be forwarded as-is or transforming it to a more applicable form.

# 2. Core Implementation

### Pattern Matcher
Enables developers to provide rule selection strategy for a particular situation or condition. PatternMatcher uses forward chaining strategy to select all applicable rules for the particular situation.

### Conflict Resolver
Enables developers to provide conflict resolution strategy when org.uclab.scl.framework.recbuilder.AbstractPatternMatcher matches or fires more than one rule for a particular situation or condition. AbstractConflictResolver provides a way through which the user can provide the implementation for  conflict resolution strategy strategy. Default implementation uses maximum specificity strategy to resolve conflict among multiple rules
     
# 3. Setup
## 3.1 Prerequisites
#### Download & install the following prerequisites
- Download & Install [Maven]([https://www.apache.org/dyn/closer.cgi)
- Downlaod & Install [Tomcat server](http://tomcat.apache.org/)

## 3.2 Clone or Download zip
#### clone scl-miningmind-2.0 OR download its zip file
* `git clone  https://eccentric-bit@bitbucket.org/bit-whacker/scl-miningmind-2.5.git`
* [Download zip](https://eccentric-bit@bitbucket.org/bit-whacker/scl-miningmind-2.5.git)

## 3.3 Build with Maven
#### run the following command from project's root directory
$ `mvn clean package`

## 3.4 How to run
### 3.4.1 Run in Eclipse
#### To run in eclipse do the following steps
##### Import project in Eclipse:
* File > Import 
* Expand `Maven` and select `Existing Maven Projects` 
* Select root directory of the project and click `Finish`

Once the project is loaded now Right Click on the project, 
* Run as > Run on Server
Note: you have to attach runtime with the project before running on server

### 3.4.2 Deploy inside tomcat
#### To deploy inside tomcat do the following steps
* Copy `target/scl-miningmind.war` to Tomcat's webapps directory e.g. 
* `C:\apache\apache-tomcat-7.0.70\webapps` for example
* Run tomcat e.g. move to `C:\apache\apache-tomcat-7.0.70\bin` and run one of the following depending on the `OS`
* window: `startup.bat`, linux: `startup.sh`

Navigate to browser and the service curation framework will be listening on `port: 8080`
e.g. `localhost:8080/scl-miningmind-2.5/rest/`

# 4. Authors

- Muhammad Sadiq  `sadiq@oslab.khu.ac.kr`
- Muhamamd Afzal  `muhammad.afzal@oslab.khu.ac.kr`
- Rahman Ali `rahmanali@oslab.khu.ac.kr`

# 5. License
The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
