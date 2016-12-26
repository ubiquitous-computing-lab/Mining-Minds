# Data Curation Layer (SCL)
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)

--------------------------

# Table Of Contents
- [1. DCL Overview](#1-dcl-overview)
- [2. Main Architecture](#2-main-architecture)
- [3. Technical Aspects](#3-technical-aspects)
- [4. Pulication](#4-publication)
- [5. License](#5-license)
  
# 1. DCL Overview

Data Curation Layer (DCL) is the foundation layer of Mining Minds platform. It is device independent and accumulates a user’s sensory data from multimodal data sources in real time. DCL curates the context of this accumulated data over the user’s lifelog. DCL provides rule-based anomaly detection over this context-rich lifelog in real time. To provide computation and persistence over the large volume of sensory data, DCL utilizes the distributed and ubiquitous environment of the cloud platform. 

The contribution of DCL is aligned with the definition of lifelog, meaning it is a black box of user life events. Multidimensional insights into user activities and behaviors require a context-rich lifelog that can be developed by the accumulation of data from a larger set of multimodal data sources. Keeping this perspective as the primary motivation, DCL implements five core concepts in its design philosophy (Illustrated in the following figure):

![RB](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/dcl/Data-curation-framework.jpg)

-(i) The ability to continuously sense for raw sensory data from multimodal data sources in real time; 

-(ii) The device-independent acquisition of raw sensory data. This concept contributes to the compatibility of DCL for IoT-based environments. This property increases the ability of DCL to integrate a larger set of sensory devices; 

-(iii) The induction of a larger set of sensory devices results in a richer context. DCL provides comprehensive curation of this context over a user lifelog. This context-rich lifelog can be used in multidimensional ways, e.g., data accumulated from a smartphone, a smartwatch, and a depth camera can accurately identify the context of a user posture in an environment. Therefore, a health and wellness platform using DCL can make recommendations not only pertaining to his activity but also the later on effect of that activity on his muscular health;

-(iv) DCL is equipped with a lifelog monitoring tool called LLM. In comparison with device-based activity recognition, lifelog monitoring looks for anomalies over richer context occurring over time. For reliability, expert-driven rules provide intelligence to this monitoring; 

-(v) DCL provides persistence to support the large volume of heterogeneous and multimodal raw sensory data associated with the lifelog. This property enables DCL to support the forthcoming concepts of data-driven knowledge generation, descriptive and predictive analytics, and visualization. 

# 2. Main Architecture

The architecture of DCL is illustrated below. DCL consists of two primary components, i.e., Sensory Data Processing and Curation, and Big Data Storage. Within the former, the Sensory Data Acquisition and Synchronization subcomponent obtains the raw sensory data from multimodal data sources, both in a real-time (active) and offline (passive) manner. This data is synchronized based upon the user identification and the time stamp of the data generation, and subsequently, it is queued for the context determination. The definition and methodology of the identification of context vary in health and wellness platforms; therefore, an interface to the synchronized sensory data residing in the queue is provided by DCL. This feature facilitates health and wellness platforms to plug-n-play their context identification engine without worrying about the real-time data acquisition from multimodal sources in a distributed environment. In response, these engines can feedback the DCL with identified context. The subcomponent of Lifelog Mapping and Representation receives the identified context and curates it by mapping the context instances to a time-based log registering the detected human activities and behaviors. The lifelog persists in the Intermediate Database for reuse. The stream of lifelog instances is analyzed by monitoring the subcomponent known as the Lifelog Monitor (LLM); it is responsible for performing customized unit-based (e.g., time-based, physical activity-based, nutrition-based) monitoring of user context available in the lifelog, cross-linked with the user profiles. LLM draws the association between the context available in the lifelog and implements anomaly detection based on expert-driven rules. Anomalies detected by LLM represent risky or unhealthy behavior and are described by various constraints (e.g., age, gender, medical conditions) and monitor-able variables (e.g., the intensity of a particular activity and its duration). LLM is equipped to provide a notification-based response to its client (a health and wellness platform or a user) with the help of a publish-subscribe mechanism.

![RI](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/dcl/data-curation-layer.jpg)

# 3. Technical Aspects

The Big Data Storage component is responsible for providing permanent and distributed big data persistence to the raw sensory data. It is termed non-volatile, as no update or delete operations are performed on the raw sensory data storage. The subcomponent of Data Persistence provides a data contract that is implemented by the clients of Physical Data Storage for permanent persistence of the data. Big Data Storage also provides mechanisms to access this persisting data as a response to health and wellness platforms. This response can be of an online or offline type. For an online response, the Active Data Reader subcomponent is used. It provides a continuous stream of sensory data for extended data operations, including visualization and predictive analytics. For an offline response, the Passive Data Reader subcomponent is used. It provides a batch response that can be effectively used for training machine learning-based models and can provide data insights to experts for rule generation. To create periodic backups of lifelog data, the subcomponents of Data Persistence are again utilized. This method provides permanent storage of the lifelog data, which can be utilized in the future for user behavior analysis. 

# 4. Publication

The technical details of DCL's implementation can be found in the following publication:

> Muhammad Bilal Amin, Oresti Banos, Wajahat Ali Khan, Hafiz Syed Muhammad Bilal, Jinhyuk Gong, Dinh-Mao Bui, Soung Ho Cho, Shujaat Hussain, Taqdir Ali, Usman Akhtar, Tae Choong Chung and Sungyoung Lee, <b>"On Curating Multimodal Sensory Data for Health and Wellness Platforms"</b>, Sensors (SCIE, IF: 2.033), vol. 16,no. 7, doi:10.3390/s16070980 , 2016
> -- <cite>[Open Access - MPDI][1]</cite>
[1]:http://www.mdpi.com/1424-8220/16/7/980



# 5. License
The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
