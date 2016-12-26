# Service Curation Layer (SCL)
[![Version](https://img.shields.io/badge/mining%20minds-version%202.5-green.svg)](http://www.miningminds.re.kr/english/)
[![License](https://img.shields.io/badge/Apache%20License%20-Version%202.0-yellowgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)

--------------------------

# Table Of Contents
- [1. SCL Overview](#1-scl-overview)
- [2. Recommendation Builder](#2-recommendation-builder)
- [3. Recommendation Interpreter](#3-recommendation-interpreter)
- [4. Execution Guidlenes](#4-execution-guidelines)
- [5. License](#5-license)
  
# 1. SCL Overview

Service Curation Layer (SCL) is designed to curating recommendation services for health and wellness. It composed of two main components: recommendation builder (RB) and recommendation interpreter (RI). These two component are supported with a third component called service orchestrator (SO) in terms of communication with the outer world. SO works as a communication wrapper for RB and RI. 

![SCL](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/scl/scl_arch.png)

# 2. Recommendation Builder

Recommendation Builder, generates recommendations through reasoning on the user proﬁle and life-log data and the knowledge rules developed in a specific format. RB provided recommendations are considered as initial recommendation because of the fact that the recommendations are yet to be interpreted from the user’s contextual perspective. The initial recommendation may be forwarded as-is or transforming it to a more applicable form.
Navigate to browser and the service curation framework will be listening on `port: 8080`
e.g. `localhost:8080/recommendation-interpreter/rest/`

![RB](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/scl/rb_arch.png)


# 3. Recommendation Interpreter

Recommendation Interpreter (RI) is envisioned as a contextual information processing unit of a larger recommendation generating system. In the current, concept-of-proof implementation, RI deals with two kinds of recommendations i.e. physical activity based recommendation and food-items recommendation. The main purpose of RI is to complement the recommendation generated based on user’s physiological requirements or conditions. RI takes into account contextual information of the user in terms of location of the user, user’s current activity, weather conditions, and emotional state of the user. Physical activity recommendations are based on a novel concept called “Contextual Matrix”. Contextual Matrix processes users’ preference data and in turn produces cross-contextual global patterns. This contextual matrix is populated using users’ surveyed data.

![RI](https://github.com/ubiquitous-computing-lab/mining-minds/blob/gh-pages/figures/scl/ri_arch.png)

# 5. Execution Guidlenes
There are three sub-projects in SCL (i) Integrated SCL, (ii) Recommendation Builder (RB), and (iii) Recommendation Interpreter (RI). Integrated SCL holds the capabilities of both RB and Ri and by running it, the user get overall functionally of SCL. However, RB and RI can work independently as well thus they are release as separate projects. Depends on user need and requirement, these projects can be utilized accordingly.

# 5. License
The code is licensed under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
