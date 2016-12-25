# Lifelog Mapping and Representation (LLMR)
<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)


--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)
    - [1.1 Core Implementation](#11-core-implementation)
   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
   
- [4. Contribution](#4-contribution)
   
- [5. Author](#5-author)

- [6. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

The data curation layer of Mining Minds platform collects data from variety of sources such as multi-model sensors, social networking sites,
 wearables, and audio/video streams to acquire knowledge and generate personalized services for the end user. Data from multimodal sensors are
 curated to extract low level context information such as user activities (walking, jogging, running, sleeping etc.) and high level context
 information such as behavior modeling and context recognition. Mining Minds generates recommendations based on the user’s daily activities,
 sedentary behavior, profile information, and user preferences and many other factors. Therefore, according to this flow of data, different 
 nature of data is needed to persist in intermediate storage. This intermediate storage should give access to the different layers of the 
 Mining Minds. The output of all the layers should persist in intermediate database as life-log. Therefore, a sophisticated, flexible and 
 scalable data representation is needed.
 Life-log mapping & representation is emerging and integral part of a system after the data acquisition phase.  After data acquisition the 
 data passes through different phases of the data curation process and information curation process to the life-log mapping and representation 
 module to validate, verify and persist the recognized life activities. Based on the recognized activities and high level context of user the 
 provides recommendations to the end user. Therefore, the Life-log mapping and representation provides a flexible, scalable and relational 
 representation to persist this heterogeneous data from diverse resources in a uniform storage.
 
## 1.1 Core Implementation

There are two main separate projects 
- Data Curation Restful Services:
	This module provides Restful webservices with different end proints, depends on the nature of data. In code directory, it's name is 
	MMDataCurationRestfulService. It provides end points based on the nature of data using in different layers of Mining Minds platform.
	- Data Curation Resource:
		It provides representation and persistence for user’s related data like physiological, disabilities, risk factors, facilitities, user's 
		schuduals. In code directory, it is presented as Restful Service facade, "DataCurationResource".
	- Information Curation Resource:
		It provides representation and persistence for user's low level and high level context data like user's recognized activity, location, emotion, 
		and corresponding high level context. It is presented as Restful Service facade, "InformationCurationResource".
	- Service Curation Resource:
		It provides representation and persistence for user's recommendations, goals, situations, activity plans, and facts based on the  corresponding
		high level context and behavior of the user. It is presented as Restful Service facade, "ServiceCurationResource".
	- Supporting Layer Resource:
		It provides representation, persistence but mainly focuses on analytics and visualization of the persisted all information in other layers. For example, 
		analytics and visualization for performed activities, emotions, locations, high level context. It is presented as Restful Service facade, "SupportingLayerResource".
- Intermediate Database Library:
	This library provides the implementation of object relation model (ORM) to persist heterogeneous data curated in different layers of Mining Minds platform. This library is 
	structured based on data model and adapters with corresponding layer.
	- Data Models:
		The root of data model package "org.uclab.mm.datamodel" contains the general classes, which are using in all layers to communicate with database. The abriviations dc 
		(Data Cruration), ic (Information Curation), sc (Service Curation), and sl (Supporting Layer) is appending with root of data model, and represent the object model of 
		the corresponding layer. (e.g. org.uclab.mm.datamodel.dc, org.uclab.mm.datamodel.ic, org.uclab.mm.datamodel.sc, and org.uclab.mm.datamodel.sl)
	- Data Adapters:
		After the object data model representation, the next level is represented by data adapters. Each data model has its own adapter to persist and retrieve the data into
		database, it contains the fundamental CRUD operations for each entity. The dataadapter packages is extended after the abriviations of each layer, like 
		"org.uclab.mm.datamodel.dc.dataadapter".

Followings are the services which are provided to the client to access and persist data

- DataCurationResources: Accept and return the json of the correspinding data entity  
	 Physiological Factors, User Address, User Disabilities, User Facilities, User Risk Factors, Users, User Schedule
- InformationCurationResource: Accept and return the json of the correspinding data entity  
	 Device, Food Log, User Accelerometer Data, User Detected Location, User Device, User GPS Data, User Prefered Location
	 User Recognized Activity, User Recognized Activity Log, User Recognized Emotion, user recognition high level context.
- ServiceCurationResource: Accept and return the json of the correspinding data entity  
	 Achievments, Activity Plan, Activity Recommendation, Facts, Profile Data, Recommendation, Recommendation Exceptions,
	 Recommendation Explanation, Situation, User Goal, User Prefered Activities, User Rewards.
- SupportingLayerResource: Accept and return the json of the correspinding data entity  
	 Active Session, Activity Feedback, Expert Review, Facts Feedback, Recommendation Feedback, User Accelerometer Data Visualization,
	 User GPS Data Visualization.
- The detail of each end points with complete URLs and input json are given in a separate file (mentioned file).

# 2. Getting Started

This consist of the following sub-headings. 


## 2.1 Requirements

Example:
- Database: Microsoft SQL Sever 2012 R2
- Java version: JDK 1.8 
- Maven: Apache-maven-3.2.2
- Tomcat: Apache-tomcat-8.0.{xx}

## 2.2 Installation
- Database Intallation:
	*	Microsoft SQL Sever 2012
	*	Install the database and set its password and login id
	*	Where Server name is (local)
	*	Authentication isset to “SQL Server Authentication”
	*	Create a database with Name “DBMiningMindsV1_5”
	*	Select the database “DBMiningMindsV1_5” and right click and select new query
	*	Download the “MiningMindsLLMRSchema.sql” and "MiningMindsLLMRData.sql"
	*	Open the “MiningMindsLLMRSchema.sql" file.
	*	Copy all the queries and paste into  new query window.
	*	Then Execute the query, it will create database with whole schema.
	*	Open the MiningMindsLLMRData.sql" file.
	*	Copy all the queries and paste into  new query window.
	*	Then Execute the query, it will import all the data of Mining Minds.
	*	Database is ready for the working
- Java and JDK setup
	JDK and JAVA_HOME
	*	Make sure JDK is installed, and “JAVA_HOME” variable is added as Windows environment variable
- Apache Maven Installation
	*	Download Apache Maven and install it
	*	Visit Maven official website, download the Maven zip file,
		for example : apache-maven-3.3.9-bin.zip. Unzip it to the folder you want to install Maven.
		(Assume you unzip to this folder – C:\Program Files\Apache\maven)
	*	Add MAVEN_HOME
	*	Add  MAVEN_HOME variables in the Windows environment, and point it to your Maven folder.
	*	Add to PATH
	*	Update PATH variable, append Maven bin folder, so that you can run the Maven’s command everywhere.
	*	Verification by running  mvn –version in the command prompt.
-Apache Tomcat Installation
	*	Goto http://tomcat.apache.org ? Downloads ? Tomcat 8.0 ? "8.0.{xx}" (where {xx} is the latest upgrade number) ? Binary Distributions ? Core ? "ZIP" package (e.g., "apache-tomcat-8.0.{xx}.zip", about 8 MB).
	*	Create your project directory, say "drive:\myProject" or "drive:\myProject". UNZIP the downloaded file into your project directory. Tomcat will be unzipped into directory "drive:\myProject\apache-tomcat-8.0.{xx}".
	*	For ease of use, we shall shorten and rename this directory to "drive:\myProject\tomcat".
-Build Project
	*	Download src and pom file into the appropriate project folder
	*	Start Command Prompt
	*	Change the directory to your project directory and folder
	*	Run “mvn clear install” command
	*	After succesful project build  
	*	Go to Project folder and access the target folder to copy MMDataCurationRestfulService.war file
	*	Go to apache-tomcat\webapps folder and paste war file there.
	*	Go to apache-tomcat\bin and start the tomcat server by click on startup.bat file. 
	*	Open browser to test apache-tomcat
	*	Give url : http://localhost:8080/MMDataCurationRestfulService/webresources/DataCuration/User/12
	*	It will fetch the User data of Mining Minds platform. It means the services are ready for persisting and retriving using differet provided methods
	*	DCL provides 23 different CRUD functions
	*	ICL provides 28 different CRUD functions
	*	SCL provides 43 different CRUD functions
	*	SL provides 16 different CRUD functions
	*	The detail URLs for each CRUD operation with input JSON are given in above mentioned document.
	*	 The services are available to return data in JSON format. That can be used further.


## 2.3 Usage

Once environment has been setup the user can use the Life-log mapping and representation service to persist and fetch different data. User can define own methods to persist the required and customized data into the 
lifelog. The users can use the methods without modification and customization. When user want to customize and add more functionality into it then follow these steps in projects "MMDataCurationRestfulService" and 
"IntermediateDatabaseLibrary" source code.
*	Add new required table in database "DBMiningMindsV1_5".
*	Add Entity class for new added table in corresponding datamodel package e.g. "org.uclab.mm.datamodel.dc".
*	Add adapter class for new added table with all required CRUD operations in correspinding adapter package e.g. "org.uclab.mm.datamodel.dc.adapter"
*	Add the abstract methods of above CRUD operations in abstract class "AbstractDataBridge".
*	Add implementation of the abstract methods in "DatabaseStorage" class.
*	call these new created methods by interface in correspinding resource of Restful service "MMDataCurationRestfulService".

# 3. Features

Write the main features 

- Heterogeneous data Processing Streams 1: Providing extensible and understandable structured format to variety of data. 
- Fine-grained Information access 2: Processable and procedural format to all layers and modules of MM Platform.
- Extensible Data Model 3: Integratable new models based on the different input data sources.

# 4. Contribution

In this implementation, we are contributing a flexible and scalable data model to represent heterogeneous data and life-log information to find users behavior and provide recommendations based on life-log information. 


# 5. Author

-  Name : Taqdir Ali
-  email : taqdir.ali@oslab.khu.ac.kr.


# 6. License

Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<br>
 


