<!--
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

-->	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/pages/dashboard.css" rel="stylesheet">
    <title>Rules Dashboard</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<!--  Start of header -->
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="index.aspx"> <img src="<c:url value="resources/images/MMlogo.png" />" style="height:55px; float:left; margin-right:10px;" /> &nbsp;Expert-Driven Intelligent Knowledge Authoring Tool</a>
      <div class="nav-collapse">
        <ul class="nav pull-right"> 
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-cog"></i> Account <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">Settings</a></li>
              <li><a href="#">Help</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-user"></i> Dr. John <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">Profile</a></li>
              <li><a href="login.aspx">Logout</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <!--/.nav-collapse --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /navbar-inner --> 
</div>
<!-- /navbar -->
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">


        <li class="active"><a href="rules"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
        <li><a href="addNewRule"><i class="icon-list-alt"></i><span>Rule Editor</span> </a> </li>
        <li><a href="#"><i class="icon-eye-open "></i><span>Show Created Rule</span> </a></li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->
<div class="main">
  <div class="main-inner">
    <div class="container">
      <div class="row">
<!--  End of header -->
<br>
<h3>Rules List</h3>
<a href="<c:url value='/addNewRule' />" class="btn btn-primary mBottom pull-right"  ><i class="icon-plus"></i> Add new rule</a>
<c:if test="${!empty listRules}">
    <table class="table table-striped table-hover table-bordered">
    <tr>
        <th width="80">Rule Title</th>
        <th width="120">Specialist Name</th>
        <th width="120">Institution</th>
        <th width="120">Created Date</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listRules}" var="Rules">
        <tr>
            <td>${Rules.getRuleTitle()}</td>
            <td>${Rules.getSpecialistName()}</td>
            <td>${Rules.getInstitution()}</td>
            <td>${Rules.getRuleCreatedDate()}</td>
            <td><a href="<c:url value='/edit/${Rules.getRuleID()}' />"  class="btn btn-default"  ><i class="icon-pencil"></i> Edit</a></td>
            <td><a href="<c:url value='/remove/${Rules.getRuleID()}' />" class="btn btn-default"  > <i class="icon-trash"></i> Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>

<!-- Start of Footer -->

 </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
</div>
<!-- /main --> 

<div class="extra">

	<div class="extra-inner">

		<div class="container">

			<div class="row">
                    <div class="span3">
                        
                      <img src="<c:url value="resources/images/uclab_logo.png" />"  />
                   
                      
                    </div>
                   
      <div class="span9">
                       
                          <p>The UCLab. at the <a href="http://www.kyunghee.edu" target="_blank">Kyung Hee University</a> is consisted of more than 30 Post-doc, Ph.D and Master students,   working on research projects under the supervision of Prof. Sungyoung   Lee, who studied in the field of ubiquitous systems.</p>
                    </div>
         
          </div> <!-- /row -->

		</div> <!-- /container -->

	</div> <!-- /extra-inner -->

</div>
<!-- extra -->
<div class="footer">
  <div class="footer-inner">
    <div class="container">
      <div class="row">
        <div class="span12"> &copy; 2015. Intelligent Knowledge Authoring Tool.</div>
        <!-- /span12 --> 
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /footer-inner --> 
</div>
<!-- /footer --> 
<!-- Le javascript
================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 

<!-- end of footer --> 
</body>
</html>