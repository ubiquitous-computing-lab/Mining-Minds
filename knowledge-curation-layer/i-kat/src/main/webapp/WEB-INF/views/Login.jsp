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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/pages/dashboard.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/pages/signin.css" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <title>User Login</title>
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			 <a class="brand" href="#"> <img src="${pageContext.request.contextPath}/resources/images/MMlogo.png" /> &nbsp;Expert-Driven Intelligent Knowledge Authoring Tool</a>
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li class="">						
						<a href="#" class="">
							Don't have an account?
						</a>
					</li>
					<li class="">						
						<a href="#" class="">
							<i class="icon-chevron-left"></i>
							Back to Homepage
						</a>
					</li>
				</ul>
			</div><!--/.nav-collapse -->	
		</div> <!-- /container -->
	</div> <!-- /navbar-inner -->
</div> <!-- /navbar -->
        <div class="account-container">
		<div class="content clearfix">
			<c:url var="addAction" value="/user/validate" ></c:url>
        	<form:form method="post" action="${addAction}" name="login" commandName="user">
			<h1>Member Login</h1>		
			<div class="login-fields">
				<p>Please provide your details</p>
				<div class="field">
					<input type="text" id="loginID" name="loginID" value="" placeholder="login ID" class="login username-field" />
				</div> <!-- /field -->
				<div class="field">
					<input type="password" id="password" name="password" value="" placeholder="Password" class="login password-field" />
				</div> <!-- /password -->
				<div style="color:red">${error}</div>
			</div> <!-- /login-fields -->
			<div class="login-actions">
				<span class="login-checkbox">
					<input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
					<label class="choice" for="Field">Keep me signed in</label>
				</span>
				<input type="submit" value="Login" class='btn btn-lg btn-primary addnewrow pull-right mBottom' />
			</div> <!-- .actions -->
			</form:form>
	</div> <!-- /content -->
</div> <!-- /account-container -->
<div class="login-extra">
	<a href="#">Reset Password</a>
</div> <!-- /login-extra -->
</body>
</html>