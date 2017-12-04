# DDKAT - Data-Driven Knowledge Acquisition Tool
1. Introduction
2. Core Implementation
3. Getting started
    1. Requirements
    2. Installation
4. Usage
5. Author
6. License

# 1. Introduction

Knowledge is a key to compete and success in every field of life, which can be discovered from heterogeneous sources by various knowledge discovery approaches. In current arena, almost every organization need decision support system that use discovery knowledge techniques for making better decisions. Normally knowledge is acquired either by expert-driven, where the expert heuristics are used or data-driven, where state-of-the-art data mining methods are applied. Due to rapidly increase of data growth rate, it is almost impossible to extract hidden knowledge with manual approach. To extract knowledge from structured data and to realize the data miming end-to-end process, a tool, called *Data-Driven Knowledge Acquisition Tool* (DDKAT), is developed.  

DDKAT is a web-based application that curates the knowledge using data-driven approach. This tool is designed for the Mining Minds (MM) platform and covers the data-driven part for the Knowledge Curation Layer (KCL).

DDKAT acquires the data, preprocesses it, selects an appropriate decision trees algorithm, generates the classification model, and finally, produces the production rules. The main features include Data Selection, Basic Preprocessing, Appropriate Algorithm Selection, Generation of Classification Model, and Creation of Shareable Production Rules.

# 2. Core Implementation
The DDKAT core implementation is mainly include five core modules; The *Data Selector*, *Data Preprocessor*, *Algorithm Selector*, *Model Learner*, and the *Model Translator*. It is developed according to Spring MVC pattern as a maven project.

The first step in any decision support system is to understand the application domain and then to identify application goal, objectives, causative factors, and their associations. All these factors help data understanding phase. The *Data Selector* modules assists the domain expert to select suitable parameters from available features list. Once a selected feature-set is obtained from a dataset, then the *Data Preprocessor* module improves the quality of data. For building a classification model, a suitable machine learning algorithm/method is required. In this study, the *Algorithm Selector* recommends an appropriate decision tree algorithm. The aims of the *Model Learner* module is to learn and build the classification models, called decision trees (DTs). Finally, the *Model Translator* converts the decision trees into executable format i.e. production rules. 

Following are details of DDKAT's implementation.
- **DDKAT Controllers**: 
The controller provides bridge between the data models and views of the project. There are two controllers, called "DDKAController" and "RuleSharingController" that are providing bridge functionalities and handles the communication of DDKAT. These controllers are put under the package of "org.uclab.mm.kcl.ddkat.controllers". 
	-	DDKA Controller: The *DDKA* controller controls the tasks of data selection, data preprocessing, algorithm selection, model learning, and model translation.
    -	Rule Sharing Controller: The *Rule Sharing* controller controls the translation tasks of the classification model. 
- **DDKAT Models**: 
Models are responsible for performing of following tasks such as : (1) data selection, (2) basic preprocessing, (3) appropriate algorithm selection, (4) generation of classification models, and (5) creation of shareable production rules. There are five packages of data model, where each package represents each above mentioned task.  
	-	Data Selector Model: This model is put under the package "org.uclab.mm.kcl.ddkat.dataselector" and is responsible for persisting as well as fetching the features, computes the (1) features score, (2) features weights, and (3) features priorities. 
	-	Data Preprocessor Models: There are five data models, which (1) convert the data into CSV format, (2) identify and replace the missing values, (3) detect and replace the outlier values, (4) discretize the data, and (5) filter the data. These data models are put under the package "org.uclab.mm.kcl.ddkat.datapreprocessor".
	-	Algorithm Selector Model: This model is put under the package "org.uclab.mm.kcl.ddkat.algoselectordataselector" and is responsible for computing and recommending an appropriate decision tree algorithm.
	-	Model Learner Model: This model is put under the package "org.uclab.mm.kcl.ddkat.modellearner" and is responsible for model learning based on algorithm and computes the classification accuracies of original as well as processed data.*/
	-	Model Translator Models: There are two data models, which translate the decision tree into rules by text processing, XML conversion, and extract model parsing for the DDKAT application as well as services purposes. These data models are put under the package "org.uclab.mm.kcl.ddkat.modeltranslator".
- **DDKAT Views**: 
According to spring mvc pattern, views are responsible to view the user interfaces by rendering the corresponding html. The rendered html is merged with data retrieved by DDKA controller using data models. In this project, there are two views.
    -	Index View: The index view provides an interface to domain expert to open the data selector view.
    -	Data Selector View: The data selector view is the main interface for obtaining the contents of interest from the data persisted in external data source. It loads a schema (i.e. features' information) as input from the external data source and displays it to the domain expert for selecting the features based on his/her own experience and writing the conditions for each selected feature. After features selection, the data selector view provides a features' scorecard to assist the domain expert in selecting informative features for decision making.
	-	Preprocessor View: The preprocessor view is the main interface for (1) loading selected data, and (2) performing data preprocessing tasks such as missing value handling, outliers handling, data discretization, and features selection. 
	-	Model Learner View: The model learner view provides an interface for appropriate algorithm selection, viewing classification accuracies, producing shareable production rules. 
# 3. Getting Started
This consist of the following sub-headings.

## 3.1 Requirements
- Example:
    -	Java version: JDK 1.8
    -	Maven: Apache-maven-3.2.2
    -	Tomcat: Apache-tomcat-8.0.{xx}
    -	WEKA: Weka-3.7.1-beta.jar
	-	Algorithm Selector: algoselector-1.0.jar, jcolibri-2.1.jar, AlgorithmsSelectionCaseBase.csv 
	-	XML Converter: WekatextToXml.jar
    
## 3.2 Installation
- JDK setup, JAVA_HOME, and WEKA as well as DDKAT configuration
    -	Make sure JDK is installed, and "JAVA_HOME" variable is added as Windows environment variable
    -	Add weka-3.7.1-beta.jar, algoselector-1.0.jar, jcolibri-2.1.jar, and WekatextToXml.jar at C:\Program Files (x86)\Java\jdk1.8\jre\lib\ext\
	-	Create three folders "DDKAT", "DDKAT/Resources", and "DDKAT/Resources/output" at user home directory in your machine. Add "AlgorithmsSelectionCaseBase.csv" into "DDKAT/Resources/" folder. 
- Apache Maven Installation
    - Download Apache Maven and install it
    - Visit Maven official website, download the Maven zip file, for example: apache-maven-3.3.9-bin.zip. Unzip it to the folder you want to install Maven. (Assume you unzip to this folder – C:\Program Files\Apache\maven)
    - Add MAVEN_HOME
    - Add MAVEN_HOME variables in the Windows environment, and point it to your Maven folder.
    - Add to PATH
    - Update PATH variable, append Maven bin folder, so that you can run the Maven’s command everywhere.
    - Verification by running mvn –version in the command prompt. -Apache Tomcat Installation
    - Goto http://tomcat.apache.org ⇒ Downloads ⇒ Tomcat 8.0 ⇒ "8.0.{xx}" (where {xx} is the latest upgrade number) ⇒ Binary Distributions ⇒ Core ⇒ "ZIP" package (e.g., "apache-tomcat-8.0.{xx}.zip", about 8 MB).
    - Create your project directory, say "drive:\myProject" or "drive:\myProject". UNZIP the downloaded file into your project directory. Tomcat will be unzipped into directory "drive:\myProject\apache-tomcat-8.0.{xx}".
    - For ease of use, we shall shorten and rename this directory to "drive:\myProject\tomcat". -Build Project
    - Download src and pom file into the appropriate project folder
    - Start Command Prompt
    - Change the directory to your project directory and folder
    - Run "mvn clear install" command
    - After succesful project build
    - Go to Project folder and access the target folder to copy SAFeS.war file
    - Go to apache-tomcat\webapps folder and paste war file there.
    - Go to apache-tomcat\bin and start the tomcat server by click on startup.bat file.
    - Open browser to test apache-tomcat
    
# 4. Usage
- Give url : http://localhost:8080/DDKAT/index
- It will render the index view to the domain expert to open the data selector view.
- The data selector view will appear with the *Load Schema* button to send the domain schema request to an external web service that provides the domain schema object (JSON format). For demo purposes, Apiary mock service has been utilized. A schema object has been created, which can be accessed by http://private-b0977-schema5.apiary-mock.com/schema. 
- The data selector view parses the received object to retrieve the schema name such as *Diabetes Schema*, tables such as *USERINFO*, and their attributes along-with data types such as *Age (int)* and then converts these into tree structure using jQuery plugin
- Domain expert selects the features/parameters based on his/her own experience and knowledge
- For applying some constraints, the domain expert loads the query panel and writes the conditions for each selected feature.
- Domain expert clicks the *Visualize Features Scorecard* button that first creates the query object based on selected features and their conditions (JSON format), then forwards the query object to an external web service that provides the domain data object (JSON format). For demo purposes, Apiary mock service has been utilized. A data object has been created, which can be accessed by http://private-673d5-data11.apiary-mock.com/data?kcldataquery=query.  
- The data selector view parses the received object to retrieve the data name, attributes' name and their values and after computing features score, . 
- Finally, features scorecard methodology is executed and displays the Features Scorecard to the domain expert.
- For moving to *Preprocessor View*, the domain expert clicks the *Data Preprocessor* button.
- Domain expert clicks the *Load Data* and *Save External Data* buttons to load and save the selected data into local machine.
- For data preprocessing, the domain expert clicks the (1) *Fill Missing Values* button for filling the missing values, (2) *Remove Outliers* button for detecting and replacing outliers, (3) *Invoke Data Transformation* button for discretizing the data, and (4) *Invoke Features Selection* button for filtering the features.
- After preprocessing phase, for moving to *Model Learner View*, the domain expert clicks the *Model Learner* button.
- For selection of an appropriate decision tree algorithm, the domain expert clicks the *Automatic Algorithm Selector* button.
- After algorithm recommendation, the domain expert clicks the *Choose Decision Tree Algorithm* listbox to select the algorithm for model learning.
- Domain expert clicks the *Learn Model* button to view the original and processed dataset accuracies.  
- Finally, the domain expert clicks the *Translate Model* button to generate and view the shareable production rules.
- The shareable production rules can be accessible by typing http://localhost:8080/DDKAT/shareRules into any browser. 

# 5. Author
- Maqbool Ali maqbool.ali@oslab.khu.ac.kr, maqbool.ali@utas.edu.au 

# 6. License
Copyright [2017] [Maqbool Ali]
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

