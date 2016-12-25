# Active Data Reader: For Visualization and Analytics 
<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Spring MVC](https://img.shields.io/badge/Spring%20-MVC-green%20.svg)](https://spring.io/guides/gs/serving-web-content/)
[![Apache Hive](https://img.shields.io/badge/Apache%20Hive-Query-lightgrey.svg)](https://hive.apache.org/)
[![JavaDoc-Version](https://img.shields.io/badge/JavaDoc-Version%202.5-green.svg)](https://usmanakhtar.github.io/MiningMinds/DCL/Active-Data-Reader/doc/index.html)



--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)
    - [1.1 Core Implementation](#11-core-implementation)
   
- [2. Getting started](#2-getting-Started)
    - [2.1 Requirements](#21-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
   
- [4. Contributions](#4-contributions)
   
- [5. Author](#5-author)

- [6. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

Apache Hadoop is mainly used for the batch processing, it is not mainly used for Active data read and writes. If our goal is to perform an SQL like operations 
then we have to connect the Apache Hadoop to the other apache projects that allow the SQL interface, one of the project is Apache Hive. In our component, Apache Hive 
is responsible for providing the read and writes requests from the other layers of miningmind. This component handles the read and the write requests by using the REST based End points. 
This component is easy to build and integrate into the existing projcets. For simplicity and scalability, Spring MVC is used and the JDBC connection is managed by Spring 
Bean internally. All is to change the JDBC URL parameters. The output form of this component is in Json format. 


Our raw sensory data is stored inside the Apache Hive, for analytics and visualization Supporting Layer (SL) send the request to Active Data Reader 
we have provided the datasets along with the project files. We also written the Simple Java Application program to upload the CSV files inside the Apache Hive. 
For proper usuage you need to change the JDBC URL. Moreover, In order to proper use of this component you should have basic understanding of the Apache Hadoop and Apache Hive.
You can use it on single and multi-node cluster, you should enable the Hive server2. When you send the request from the HTTP URL the request will be map by the Spring MVC REST Controller. 
The GET or POST request will handle by the Thrift server which comes along with the Apache Hive. If the Thrift server is not enabled the request will not be handled by the Hive metastore. 
 
## 1.1 Core Implementation

Here we will discuss about the core implemenation more in details. This project is using Spring MVC pattern and used build using maven.
- Controllers:<br>
	The controllers provide bridge between the data models and views of the project. Put Home controller is responsible for providing the mapping request to the methods. 
	When the request comes Spring Request mapping invokes the method and response body. We have configured the JSon API to send the reponse in the json format. The Home Controller 
    is 	under the package of "org.uclab.mm.dcl.adr.Homecontroller". Accoring to the data data we will return the Location counts after calculating from the datanotes. If you want to test the 
	controller you have to upload the data inside the HDFS by using our Uploading Script. 
	
- Models:<br>
	Models is responsible for persisting and fetching the location count from the knowledge bases. We haev used the Data Access object pattern on the data models to access the data from the 
	Apache Hive. For the service of the location the data access object of the services are used to connect with the JDBC string connection. As these connection are handle by the Spring Internally. 
	We have written the customized query to fetch the data from the Apache Hive. 
	
- Views:<br>
	Our view is different then the traditional view of the MVC. As we are using Spring MVC, according to spring mvc pattern, views are responsible to view the user interfaces by rendering the correspinding html. 
	The rendered html is merged with data retrived by controllers using particular DAO and services. In this project, our view is a static HTML file that are used to handle the HTTP request. The view template provide
	the view to the client to send the request for mapping inside the Apache Hive. 
	
- Generate Schema:<br>
	we have provided the Java Application program to generate the tables and load the data inside the HDFS. You need to change the parameter of the program to work correct. You have to adjust the path of the 
	CSV file that will be loaded inside the Apache Hive. 
	
- Spring Beans:<br>
	Inside the web.xml file we have provided the Servelet mapping with the Hive Connect. So, when the application will run on the Server this bean will connect to the Apache Hive automatically. You need to change the parameter 
	of your JDBC connection. The file is located in the src/main/resources/HiveConnect folder. 
	
- Pom.xml File:<br>
To hnadle the dependencies we have added the pom.xml file. This file will handle all the Spring, Hadoop and the Hive dependencies. If you are using the Hadoop on the single node you can import the jars from the Lib directory of
Hadoop and also the Hive. But we recommend you to use our pom.xml and change the version of your hadoop and  hive. 

# 2. Getting Started

Now, its time to get started and configure the core requirements. 


## 2.1 Requirements

Example:
- Database: Apache Hive 1.2.1
- Hadoop: version 2.5.2
- Java version: JDK 1.8 
- Maven: Apache-maven-3.2.2
- Tomcat: Apache-tomcat-8.0.{xx}

## 2.2 Installation
- Hive Intallation and Data Import:
	You need to configure the Apache Hive as well as enable the Hive Server2 thrift port. 
	*	Go to the http://doctuts.readthedocs.io/en/latest/hive.html
	*	You need to configure it with the Hadoop 2.5.2 as well. 
	*	After configure go to the bin folder and run the hive. 
	*	Go to the bin folder and run the Hiveserver2. 
	*	Upload the data by using our provided script. 
	*	Our data is inside the csv you need to import in your Hive warehouse.
	*	You need to change the database and TableName according to your setting. 
	
- Java and JDK setup
	JDK and JAVA_HOME
	*	Make sure JDK is installed, and “JAVA_HOME” variable is added as Linux environment variable. We would recommend you to used the latest version of the 
- Apache Maven Installation
	*	Download Apache Maven and install it
	*	Visit Maven official website, download the Maven zip file or,
	```
	$ sudo apt-get install maven
	```
	*	Add MAVEN_HOME in .bashrc and point it to your Maven folder
	*	Add to PATH
	*	Update PATH variable, append Maven bin folder, so that you can run the Maven’s command everywhere.
	*	Verification by running  mvn –version in the command prompt.
-Apache Tomcat Installation
	*	Goto http://tomcat.apache.org ⇒ Downloads ⇒ Tomcat 8.0 ⇒ "8.0.{xx}" (where {xx} is the latest upgrade number) ⇒ Binary Distributions ⇒ Core ⇒ "ZIP" package (e.g., "apache-tomcat-8.0.{xx}.zip", about 8 MB).
	*	Create your project directory, say "drive:\myProject" or "drive:\myProject". UNZIP the downloaded file into your project directory. Tomcat will be unzipped into directory "drive:\myProject\apache-tomcat-8.0.{xx}".
	*	For ease of use, we shall shorten and rename this directory to "drive:\myProject\tomcat".
-Build Project
	*	Download src and pom file into the appropriate project folder
	```
	$ Git Clone project name
	```
	*	cd /Project Folder Name: Change the directory to your project directory and folder
	*	Run ```mvn compile``` command to compile the source code of the project and then ```mvn clean install``` to install the package into the local repository, for use as a dependency in other projects locally
	*	After succesful project build  
	*	Go to Project folder and access the target folder to copy ActiveDataReader.war file
	*	Go to apache-tomcat\webapps folder and paste war file there.
	*	Go to apache-tomcat\bin and start the tomcat server by click on startup.bat file. 
	*	Open browser to test apache-tomcat
	*	Give url : http://localhost:8080/analytics/ and then http://localhost:8080/analytics/location?userid=0 
	*	This will display the result in the json format. 
	*	If you want to run the code on your own datasets then simply change the setting of the Jdbc url.


## 2.3 Usage

After configuring the environment you can custom made the own services. All you need is to follow the Spring MVC pattern. But you should consider these steps before furthur updating the project.   
*	You can create drop and import the tables by using the generate schema script.
*	For new services you can update the Home Controller which is written in Spring MVC
*	For new tables inside the Hive, you need to add the table in the model package
*	Add new or update existing DAO object under the mode package
*	Add new or update existing service with interface and implementation under the package "org.uclab.mm.dcl.adr"
*	Add the required objects as bean in servlet-context.xml file under foler hierarchy src/main/webapp/WEB-INF/spring/appServlet according to followed pattern.
*	Add new or update the existing views according to the requirements under the folder hierarchy src/main/webapp/WEB-INF/views according to followed pattern.

# 3. Features

Following are the main features of Active Data Reader. 

- Providing Read and write request on the top of the Hadoop. 
- Hadles the Get request by using the Spring MVC framework.
- provide a script to add the data inside the Apache Hive.

# 4. Contributions

The main contributions are as follows:
-	Provide the read and write request on the top of the Hadoop. 
-	A easy to use script to upload the data inside the Apache Hive.
-	In future, we will provide more services for CRUD operations, if you want to contribute just send the code we will pull your requests.


# 5. Author

-  Name : Usman Akhtar
-  email : usman@oslab.khu.ac.kr.


# 6. License

Copyright [2016] [Usman Akhtar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<br>
