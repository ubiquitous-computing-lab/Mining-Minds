# Supporting Layer 

<!-- make your own badges from here: http://shields.io/ -->
[![Version](https://img.shields.io/badge/SL-2.5-lightgrey.svg)](http://www.miningminds.re.kr/approach/)
![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)

--------------------------

<!-- Update the list and the main body. -->


- [1. Introduction](#1-introduction)
	
- [2. Descriptive Analytics](#2-descriptive-analytics)
   
- [3. UI/UX Authoring Tool](#3-ux-ui-authoring-tool)

- [4. License](#4-license)

<!-- Main Body of the Document -->


# 1. Introduction

The supporting layer is in charge of user interface, visualization, analytics and security. The supporting layer create a unique interface which has the adaptive and personalized approach towards building and managing the user interfaces. The analytics gives user and experts different insights into the habits, activities and different classification of the application. 

# 2. Descriptive Analytics

Big data is creating many opportunities for different and diverse fields to achieve deeper and faster insights that can enhance the decision making. 
Descriptive analytics mines data to provide trending information on past or current events that can give the context needed for future actions. It is basically a quantitative summary that describes the main characteristics of a data collection. 
Descriptive analytics, such as reporting, dashboards, and data visualization is being widely used now with emergence of big data.
These field are increasingly turning to visualization based tools has 30 percent growth rate in 2015. We can provide interactive and easily understandable visual formats to improve the overall user experience. The trend analyzer takes data from Data Store Interface and output trends and summaries to Visualization Enabler.  In Trend Analyzer, Textual Classification are performed. In Numerical Analysis, tuning will be done by considering capacity, counter, size and threshold parameters. In association clustering statistical features like Minimum, Maximum, and Average features that are used to tune the parameters. This approach finds the ranks of features as high ranked features produces more accurate models.
Query Creation Interface takes query from Visualization Enabler and the output is mapped Query with parameters. It maps the query from visualization enabler. The data query manager takes the query from query library. If the query is not in the library or needs to be tuned/changed, the query selection and tuning module is triggered to change the query. 

Visualization Enabler takes the request from UI/UX and trends and graph data from trend analyzer. It outputs a query for query interface.  and summaries and graphs for UI/UX curation . The visualization enabler has a request manager which forwards the query from UI/UX module. This query is then passed to query creation interface. b) The graph evaluation modules takes graph data from the trend analyzer and maps it on a graph template. The visualization context takes into account the data and profile of the user

<br>
![alt tag](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/sl/analytics.png)
<br>
# 3. UX/ UI Authoring Tool	
The main reason of less adaptation of expert systems is because of the user interfaces. The two main areas that are recently under thorough observation is related to user interface (UI) and user experience (UX).  Most of the researchers considers UI and UX as same entities but there exists huge amount of difference between them, although they are related to one another. The main objectives that we are dealing with in this module is the adaptive and personalized approach towards building and managing the user interfaces.

Adaptation Manager component behaves as a coordinator between different internal components. It takes initial input from the Personalized Patterns layer and forwards it to the Interactive KB. It then takes input from the Interactive KB and provides it to UI Elements Selection. It finally takes output of the UI Elements Selection and builds Graphical User Interface accordingly.

User Satisfaction Model takes feedback and outputs ersonalized Information Categories. The feedback obtained from the different measurement indicators are then processed for finding out the user satisfaction. A threshold value is set for comparison and degree of adaptation to be carried out in the user interface.

Personalized Information Evolution takes personalized Information Categories and outputs evolution of Personalized Repositories 
This modules takes as input the categories form the user satisfaction model and forwards the information to the Adaptive User Interface for evolution of the personalized repositories.

<br>
![alt tag](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/sl/uiux.png)
<br>
# 4. License

The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
<br>
 


