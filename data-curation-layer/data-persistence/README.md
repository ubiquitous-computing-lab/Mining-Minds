# Data Persistence

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

Data persistence is received data(JSON format) from synchronization server, and storing data in remote HDFS server.

## 1.1 Core concepts

Received JSON data to remote client, and store data in local until 3000kb, then send to remote HDFS server. Data communication is implemented by POST request.

# 2. Getting Started

Data persistence can be received data as Restful API(Post request). Data format is JSON.

## 2.1 Requirements

- Ubuntu 14.04 or Window 7 OS
- Java versions: Java 1.7(recommandation)  
- Hadoop version: 2.5.2(recommandation)
- Node.JS version: 6.9.2LTS(recommandation)
- Network enable

## 2.2 Installation

This guide skip how to install ubuntu, how to install hadoop. 

For using the web-hdfs module, you need to add below line in /etc/hadoop/hdfs-site.xml in your hadoop folder.

<property>
        <name>dfs.webhdfs.enabled</name>
        <value>true</value>
</property>

For starting the Data Persistence, you need some Node.js library. After install Node.js, enter below command(on root permission).
 
npm install express
npm install body-parser
npm install requestify
npm install util
npm install process
npm install fs
npm install webhdfs


## 2.3 Usage

Data Persistence is consist of 4 files : DataManufacture.js, LocalFileManagement.js, sendHDFS.js, webhdfs-client.js and rev_server.js.
you can start the File writer using by below command.
 	
node DPServer

If you see the message ¡°server is running¡±, server is enable to use. Server is received data and stored data in local until file size is bigger than 3000kb. If file size is over 3000kb, this local file is sending the HDFS server, and local file is initialized. 

Also, You can modify the configuration of Data Persistence such as unit of stored data(in HDFS), server port and read/write file path. File writer`s configuration is declared in ¡®config.json¡¯. 
Config.JSON is consist of below data.

{fileSizeUnit":3000, "port":8083, "filePath_hdfs":"/kjh", "filePath_local":"./"}

¡¯fileSizeUnit¡¯ means limitation of file size of local file. If local file size is going to over the value of ¡®fileSizeUnit¡¯, then local file is sent to the HDFS Server.
¡®port¡¯ means the port number of Data persistence server.
¡®filePath_hdfs¡¯ means the file path which data is stored in HDFS server. 
¡®filePath_local¡¯ means the file path which data is stored in local(Defult local file name is ¡®temp.txt¡¯).

Also, you can modify the configuration of connection of HDFS server. ¡°webhdfs-client.js¡± file has the configuration data for HDFS server. Configuration data is consist of below 3 type of data.

user: 'hadoop', //Hadoop user
host: 'hadoop-VirtualBox', //Namenode host
port: 50070 //Namenode port

You can modify this data for your Hadoop server.

# 3. Features

DataManufacture : Sorting a data object and extract data in object using by index 
LocalFileManagement
sendHDFS : Send the data to HDFS using by HTTP.
DPServer : Run all of modules.

# 4. Contributing

**

# 5. Author

-  JinHyuk Gong
-  kjh@oslab.khu.ac.kr


# 6. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
<br>
 


