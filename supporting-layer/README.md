# Supporting Layer 

## Note: follow this template and commit for changes.. 
<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/ICL-2.5-ff69b4.svg)](http://www.miningminds.re.kr/english/)
![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)

--------------------------

<!-- Update the list and the main body. -->




- [1. Introduction](#1-introduction)
	
- [2. Low Level Context Awareness](#2-low-level-context-awareness)
   
- [3. High Level Context Awareness](#3-high-level-context-awareness)

- [4. License](#4-license)

<!-- Main Body of the Document -->


# 1. Introduction

Current domain-specific solutions are seen to be certainly insufficient to deal with the magnitude of the behaviour analysis problem, thus making it necessary to rather use more holistic approaches to infer and analyse people¡¯s conduct. In this context, it is devised Mining Minds, a novel digital health and wellness platform designed to seamlessly investigate and support people¡¯s lifestyles
by intelligently mining human¡¯s daily living data generated through heterogeneous resources. The multimodal context mining framework presented in this paper plays a core role in Mining Minds for the transformation of heterogeneous sensory data into interpretable and actionable information from which behavioural patterns can be derived. Although this framework has originally been devised to operate in conjunction with other layers of the Mining Minds platform stack, it can nevertheless operate as an independent and decoupled engine for the inference and modelling of people¡¯s context. The multimodal context mining framework is composed of two main modules, namely Low Level Context Awareness (LLCA) and High Level Context Awareness (HLCA). LLCA is in charge of converting the wide-spectrum of data obtained from the user interaction with the real and cyber world into abstract concepts or categories, namely physical activities, emotional states and locations. These categories are intelligently combined and processed at HLCA in order to determine and track
more meaningful semantic representations of the user context.
<br><br>
![alt tag](https://nailbrainz.github.io/ICLLIB_reop/ICL.jpg)
<br>
# 2. Low Level Context Awareness

LLCA consists of the Sensory Data Router, Low-Level Context Recognizers (i.e., Activity
Recognizer, Emotion Recognizer, Location Recognizer), Low-Level Context Unifiers (i.e., Activity
Unifier, Emotion Unifier, Location Unifier) and Low-Level Context Notifiercomponents. The operation
of the LLCA module is as follows. The influx of sensory data coming from the monitoring
devices is first processed by the Sensory Data Router component to identify the Low-Level Context
Recognizers to which each datum must be distributed. This identification is performed based on
the nature of the incoming data and the requirements of each recognizer. Upon receiving new data,
the Activity Recognizer, Emotion Recognizer and/or Location Recognizer take action. Each of these
recognizers includes various subcomponents operating on different sensor data modalities. The
decisions delivered by each individual subcomponent are fused or unified into a single context
for every category through the Activity Unifier, Emotion Unifier and Location Unifier, respectively.
Finally, once a new low-level context is identified (either activity, emotion or location), the Low-Level
Context Notifier makes it available to HLCA for further analysis and also to any potential third party
application, which might be registered for this type of information
<br><br>
![alt tag](https://nailbrainz.github.io/ICLLIB_reop/LLCA.jpg)
<br>
# 3. High Level Context Awareness
HLCA consists of four main components: High-Level Context Builder, High-Level Context Reasoner,
High-Level Context Notifier and Context Manager. The operation of HLCA is as follows.
The High-Level Context Builder receives unstructured low-level information, namely activities,
emotions and locations, yielded by the Low-Level Context Architecture. Then, based on the received
low-level context information, the High-Level Context Builder generates the ontological concepts
representing the user high-level context. The unclassified high-level context is served to the High-Level
Context Reasoner for its verification and classification into one of the different high-level context
categories by applying ontological inference. Once a newly classified high-level context has been
identified, the High-Level Context Notifier makes it available to any third party application that
registered for this type of information. During the context identification process, several components
interact with the Context Manager, which provides ontological persistence, also supporting the easy
access to low-level context and high-level context information. For all of the aforementioned processes,
HLCA uses the Mining Minds Context Ontology.
<br><br>
![alt tag](https://nailbrainz.github.io/ICLLIB_reop/HLCA.jpg)
<br>
# 4. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
<br>
 


