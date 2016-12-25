# Lifelog Monitoring
<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)



--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)
    - [1.1 Core concepts](#11-core-concepts)
   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
      
- [4. Contributing](#4-contributing)
    - [4.1 Optional](#41-optional)
   
- [5. Author](#5-author)

- [6. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

Monitoring does not only mean to inform users but may be used to intimate at the nick of time. It is always better to
catch the things before happening at the time of occurrence instead of after delay. Monitoring of life log can help in
2 ways either providing information to the user or intimating the user on the basis of experts’ knowledge for current 
unhealthier activity. The developed technique monitores under the experience of experts against in wellness 
domain for unhealthy activities to support user by intimating when activity become unhealthier to user. The intimation 
with proper suggestion for avoiding the impact of that unhealthy experience act as a check as well remedy of it.

We propose a solution which monitors the different activities of users with multiple situations which become unhealthy for users. The 
situations are provided by experts on the basis of knowledge and experience to avoid cumulative bad impact of the prolonged
activity. The proposed system is able to monitor the activities on the basis of dynamically added situation from the experts. 
These situation are added dynamically at real time and all the futuristic activities are monitored on the basis of available 
situations. The proposed model is robust and comprehensive enough to handle all the possible scenario of multiple activities 
of multiple users for different dynamic situations.

## 1.1 Core concepts

There are four basic module
- Communication:
	This module is responsible to provide all those end points which are necessary to access and provide the information for 
	processing the activities.
- Data Access
	This module is responsible to access the database and manages the single access connection to avoid unnecessary burdan 
	on the connection creation for every data request.
- Monitoring
	This module is responsible to handle the monitoring process of the physical activities and nutrition. The notification generation for the 
	monitored activities and nutrition is also its responsibility.
-Objectmodel
	This module is responsible to define the data model that is according to the common communication format. It is defined in a way that it can handle 
	complex communication object data model.


Followings are the services which are provided to the client to access data and define monitoring situation

- AddFoodLog: Accept the json of the food intake by the user which constitutes of  
	 - user information,
	 -  food information , 
	 -  and log time
- Violations: Retrieves the violation from the log
- TotalViolations: Retrieves the total count of the violation from the log.
- UserViolations: Retrieves total violation from the log against the user information.
- LogViolations: Retrieves violation details from the log against a log information.
- SLViolations: Retrieves violation with sequence grater then the provided id from the log.
- SituationAdd: Accept the json of the constraints and monitoring conditions required for monitoring and assessing the conditions.
- PhysicalMonitoring: Start monitoring of the physical activities which monitor after every 3 seconds.


# 2. Getting Started

This consist of the following sub-headings. 


## 2.1 Requirements

Example:
- Database: Microsoft SQL Sever 2008 R2
- Java version: JDK 1.7 
- Maven: Apache-maven-3.2.2
- Tomcat: Apache-tomcat-8.0.{xx}

## 2.2 Installation
- Database Intallation:
	*	Microsoft SQL Sever 2008 R2
	*	Install the database and set its password “123” and login “sa”
	*	Where Server name is (local)
	*	Authentication isset to “SQL Server Authentication”
	*	Create a database with Name “MMLLM”
	*	Select the database “MMLLM” and right click and select new query
	*	Download the “MMLLMscript.sql” 
	*	Open the MMLLMscript.sql file.
	*	Copy all the queries and paste into  new query window.
	*	Then Execute the query 
	*	Database is ready for the working
- Java and JDK setup
	JDK and JAVA_HOME
	*	Make sure JDK is installed, and “JAVA_HOME” variable is added as Windows environment variable
- Apache Maven Installation
	*	Download Apache Maven and install it
	*	Visit Maven official website, download the Maven zip file,
		for example : apache-maven-3.2.2-bin.zip. Unzip it to the folder you want to install Maven.
		(Assume you unzip to this folder – C:\Program Files\Apache\maven)
	*	Add MAVEN_HOME
	*	Add  MAVEN_HOME variables in the Windows environment, and point it to your Maven folder.
	*	Add to PATH
	*	Update PATH variable, append Maven bin folder, so that you can run the Maven’s command everywhere.
	*	Verification by running  mvn –version in the command prompt.
-Apache Tomcat Installation
	*	Goto http://tomcat.apache.org ⇒ Downloads ⇒ Tomcat 8.0 ⇒ "8.0.{xx}" (where {xx} is the latest upgrade number) ⇒ Binary -Distributions ⇒ Core ⇒ "ZIP" package (e.g., "apache-tomcat-8.0.{xx}.zip", about 8 MB).
	*	Create your project directory, say "drive:\myProject" or "drive:\myProject". UNZIP the downloaded file into your project directory. Tomcat will be unzipped into directory "drive:\myProject\apache-tomcat-8.0.{xx}".
	*	For ease of use, we shall shorten and rename this directory to "drive:\myProject\tomcat".
-Build Project
	*	Download src and pom file into the appropriate project folder
	*	Start Command Prompt
	*	Change the directory to your project directory and folder
	*	Run “mvn clear install” command
	*	After succesful project build  
	*	Go to Project folder and access the target folder to copy LLMS.war file
	*	Go to apache-tomcat\webapps folder and paste war file there.
	*	Go to apache-tomcat\bin and start the tomcat server
	*	Open browser to test apache-tomcat
	*	Give url : http://localhost:8080/LLMS/sc/servicecommunicator/TotalViolations/
	*	Now run service: http://localhost:8080/LLMS/sc/servicecommunicator/PhysicalMonitoring/  it will start monitoring service.
	*	Enter the data now in database with following query
			insert into tblUserRecognizedActivity
			 values(1, 35, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,3)
			insert into tblUserRecognizedActivity 
			values(6, 36, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,3)
	*	 The services are available to return data in JSON format. That can be used further



## 2.3 Usage

Once environment has been setup the user can use the monitoring service to monitor the activities. User can define own methods to absorb the output of the monitoring activity. 
In the monitoring class the databse stored proceduers are called, so while customising those stored procedure the extention of the monitoring process is possible very easily. The notification services
are provided to define own way of communication and enhance the functionality of the working product. New implementation for enhancement of other monitoring process can be extended easily other than 
physical and nutrition based monitoring.

# 3. Features

Write the main features 

- Real Time Monitoring 1: This module help to monitor the unhealthy situation at near real time. The maximum delay is less than the time set for monitoring cycle. 
- Multiple Activities Handling 2: This module help to monitor all multiple activities with multiple situation for multiple users at a time.
- Accomodate runtime situation 3: Expert can add run time situation for intimation and monitoring.

# 4. Contributing

In future monitoring of other complex activities can be added. This feature is too much required to handle the real time scenario of complex activities. Which may be mixture of different parallal on going 
activities. 


# 5. Author

-  Name : Hafiz Syed Muhammad Bilal
-  email : bilalrizvi@oslab.khu.ac.kr.


# 6. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
Copyright [2016] [Bilal Ali]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.
<br>
 


