# High Level Context Awareness: (HLCA)
<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/MM__ICL__HLCA-V2.5-ff69b4.svg)](http://www.miningminds.re.kr/english/)
[![Version](https://img.shields.io/badge/Mining%20Minds-Version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![License](https://img.shields.io/badge/Pellet--core-2.3.2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![License](https://img.shields.io/badge/owlapi--api-3.4.10-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![JavaDoc-Version](https://img.shields.io/badge/JavaDoc-Version%202.5-green.svg)](https://usmanakhtar.github.io/MiningMinds/ICL/High-Level-Context-Awareness/doc/index.html)

--------------------------
<!-- Update the list and the main body. -->

- [1. Introduction](#1-introduction)
    - [1.1 Core Implementation](#11-core-implementation)
 
- [2. Getting started](#2-getting-Started)
    - [2.1 General Requirements](#21-general-requirements)
    - [2.2 Installation](#22-installation)
    - [2.3 Usage](#23-usage)
	
- [3. Features](#3-features)
   
- [4. Contributions](#4-contributions)
   
- [5. Authors](#5-authors)

- [6. License](#6-license)

<!-- Main Body of the Document -->


# 1. Introduction

In Mining Minds the sensory data is modelled ontologically in the form of RDF Triples which are fed to HLCA for reasoning purpose.  The underlying Mining Minds Context Ontology (MMCO) models context for human behavior identification in order to enable the provision of personalized health and wellness services in Mining Minds. In Mining Minds, human context is understood as any information characterizing the physical, mental and social situation of a person which enables the identification of human behavior.  Furthermore, human context is here categorized into two different levels of abstraction: low-level context (LLC) and high-level context (HLC).  HLC has further two main classes, Physical Activity high level context (PA-HLC) and nutrition high level context (N-HLC).  PA-HLC comprises of activities, locations and emotions include daily contexts like office work, commuting, house work, gardening, amusement, exercising, sleeping, and inactivity whereas N-HLC consists of carbohydrates, protein and fats determined as per rules from activity eating, location home or restaurant, with several emotions and having any of the food items from the list of 57 identified food under 10 broader categories.   
	-	**Mining Mind Context Ontology v2.5** (MMCOv2.5):  You can have a look at [:link:MMCO V2.5](http://www.miningminds.re.kr/lifelog/context/context-v2.5.owl) how it is designed in [:link:Protégé ](http://protege.stanford.edu/) .  
	-	Also It can be visualized in the Screenshot as under 
		![](./MMCO2-5.PNG)

This  MMCO  ontologies with [owlapi](http://owlapi.sourceforge.net/) and ingests ontology formats available to owlapi (OWL, RDF, etc).  MMCO addresses following goals.

Goals:
* OWL 2 Support
* Provide a simple, usable, MMCO representation for multi-model contexts
* Efficient, Extendable MMCO 
* Provide basic "vocabulary" support
* Reasoning support


## 1.1 Core Implementation

HLCA is java based project developed by exploiting structural Bridge and creational Singleton Design Patterns in Maven project.  This made HLCA system independent and can be plugged played with Mining Minds platform. 

## :small_red_triangle_down: Intent & Applicability for Bridge Design Pattern
In the Bridge design pattern we decouple an abstraction from its implementation so that the two can vary independently.  
* We avoided permanent binding between an abstraction and its implementation.
* Both the abstractions and their implementations are extensible by subclassing. This implementation of Bridge pattern allows to  combine the different abstractions and implementations and extend them independently
* changes in the implementation of an abstraction has no impact on clients
* Shares an implementation among multiple objects and fact remains hidden from the client. 
* We implemented Bridge design pattern for **Context** Class as an abstraction in the **mm.icl.hlc.OntologyTools** package.  **NutritionContext** and **PhysicalActivityContext** Classes work independently and are extended from **Context** Class.  
* Similarly **ContextHandler** class is extended from **AbstractHandler** class in the **mm.icl.hlc.ContextOntologyManager**, which can be extended for future use.  
* The features of Bridge design patterns were also used in **ContextOntology** class which was extended from **AbstractOntology** implemented in  **mm.icl.hlc.OntologyTools** package.
* Lastly High level context reasoner i.e. **HLCReasoner** class is extended from **AbstractReasoner** class in the **mm.icl.hlc.HLCReasoner** package.  
These were the core areas of High Level Context Awareness in which Bridge Design Patterns were used.

## :small_red_triangle_down: Intent & Applicability for Singleton Design Pattern
Ensured that a class only has one instance, and provide a global point of access to it.  We also used singleton design pattern so that 
* the instance is only extensible by subclassing without modifying their code.
* there must be exactly one instance of a class, and it must be accessible and enforced to clients through authorized access point
* Singleton Design pattern in this implementation was applied for **ContextQueryGenerator** and **ContextHandler** classes so that they can be used further without modifying codes.
			
# 2. Getting Started

Following subsections will explain how to use HLCA independently starting from Installation perspective to implementation.  Requirements for installation and installation procedures are discussed briefly. 

## 2.1 Requirements

The requirements for executing HLCA are as under:
- Java version: JDK 1.8 
- Maven: Apache-maven-3.2.2

## 2.2 Installation
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
- Jena TDB
	*	HLCA using Semantic technologies, also requires using triple stores. In scope of Mining Minds project, HLCA requires  triple store as its back-end. We opted Jena TDB which is native file based triple store of Jena, highly scalable and requires no extra tool other than Jena Framework.
	
- Build Project
	*	Once you have Maven installed, verify with:
		mvn -version
  		Then run (once) this to download all the necessary dependencies:
	*	Download src and pom file into the appropriate project folder
	*	Start Command Prompt
	*	Change the directory to your project directory and folder
	*	Run “mvn clean install” command
	*	-------------------------------------------------------
	*	You can import the project in Eclipse via File > Import... > Existing Projects into Workspace
	*	You can run mvn eclipse:eclipse to re-generate Eclipse .project and .classpath files automatically from your pom.xml file.
	
## 2.3 Usage
Once environment has been setup, MMCO v2.5 placed in the folder, the user can use HLCA.  **HLCAMapperTest** can be executed for adding new LLC of any form w.r.t. MMCO v2.5.  This will add User context to *Jena TDB* and for instance if **Sitting** LLC is added which will look like 
![](./SittingLLC.PNG)

After creating enough LLC, User can test **TestHLCA** in order to infer PA-HLC and N-HLC based on LLC like Activity, Location, Emotin and Food.  These inferred PA-HLC and N-HLC would be according to the Constraint based Rules defined in MMCO v2.5.

# 3. Features

####OWLAPI
HLCA uses the features of OWL API which is a Java based API for creating, manipulating and serialising OWL Ontologies.  In this implementation we have used OWLAPI-v3.4.10.  It is available under Open Source licenses (LGPL and Apache).  
####JenaTDB
In order to manipulate and persistance of triplese Jena-tdb-v1.0.2 was used.  
####Reasoning
Finally reasoning was performed used pellet reasoner i.e. pellet-core-2.3.2 reasoner.

# 4. Contributions

-	This work has presented an ontology-based method for deriving PA-HLC and N-HLC out of the combination of cross-domain low-level context primitives, namely activities, locations, emotions and food. 
-	The modeling of the low-level, PA-HLC and N-HLC are defined through the so-called Mining Minds Context Ontology. 
-	The processing and inference of contexts is performed by the Mining Minds High-Level Context Architecture implementation.
-	The unprecedented incorporation of emotions and food items in the context definition enables the representation of new PA-HLC and N-HLC that can only be identified whenever a specific emotion takes place and user take food in meals as LLC. 
-	The Mining Minds Context Ontology has also been designed to procure the identification PA-HLC and N-HLC even in the absence of emotion and Food information. 
-	This implementation of Mining Minds HLCA built on the MMCO enable the inference of PA-HLC and N-HLC from low-level context primitives in real time. 

# 5. Authors
<!--  Name : Claudia Villalonga and Muhammad Asif Razzaq-->
<!---  email : cvillalonga@oslab.khu.ac.kr, asif.razzaq@oslab.khu.ac.kr-->
:large_blue_diamond: Claudia Villalonga*: :e-mail: cvillalonga@oslab.khu.ac.kr

:large_blue_diamond: Muhammad Asif Razzaq*: :e-mail: asif.razzaq@oslab.khu.ac.kr

:large_blue_diamond: Wajahat Ali Khan*: :e-mail: wajahat.alikhan@oslab.khu.ac.kr


# 6. License

Copyright :copyright: [2016] [Claudia Villalonga and Muhammad Asif Razzaq]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<br>
