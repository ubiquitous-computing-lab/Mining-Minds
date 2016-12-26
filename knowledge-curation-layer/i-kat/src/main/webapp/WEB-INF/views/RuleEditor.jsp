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
    <title>Rule Editor</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/pages/dashboard.css" rel="stylesheet">
	
	    
    
    
    
    <link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
    
    <script src="https://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
   <script src="https://code.jquery.com/ui/1.9.2/jquery-ui.min.js" type="text/javascript"></script>
	

    
  	<style>
  		table{width:100%}
		.deleterow{cursor:pointer}
  	</style>    
    <script type="text/javascript">  
    
    function getParameterByName(name) {
    	
    	var strUrl = window.location;
    	var arrUrlParts = strUrl.toString().split('=');
    	if(arrUrlParts.length > 1)
   		{
   			if(arrUrlParts[1] == "yes")
   				{ alert("Rule has been saved successfully"); }
   		}
    	
        /* name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " ")); */
    }
    
    function buildRuleAndSituation()
	{
		var totalConditionRows = 0;
		var totalConclusionRows = 0;
		var strConditionText = "";
		var strConclustionText = "";
		var strSituationText = "";
		
		totalConditionRows = $('#tblCondition tr').length;
		totalConclusionRows = $('#tblConclusion tr').length;
		
		//alert("totalConditionRows : " + totalConditionRows);
		//alert("totalConclusionRows : " + totalConclusionRows);
		
		if(totalConditionRows > 1)
		{
			for(var i = 0; i < totalConditionRows - 1; i++)
   			{
   				var strKeyName = "rule.conditionList" + i + ".conditionKey"; 
   				var strValueName = "rule.conditionList" + i + ".conditionValue";
   				var strOperator = "rule.conditionList" + i + ".conditionValueOperator";
   				var strSituationName = "rule.conditionList" + i + ".isItSituation1";
   				
   				if(document.getElementById(strKeyName).value != "" && document.getElementById(strOperator).value != "" && document.getElementById(strValueName).value != "")
   				{ 
   					if(strConditionText == "")
   					{ 
   						strConditionText = document.getElementById(strKeyName).value + " " + document.getElementById(strOperator).value + " " + document.getElementById(strValueName).value;
   					}
   					else
						{
   						strConditionText = strConditionText + " and " +  document.getElementById(strKeyName).value + " " + document.getElementById(strOperator).value + " " + document.getElementById(strValueName).value;
   						
						}
   					
   					if(document.getElementById(strSituationName).checked == true)
					{
							if(strSituationText == "")
							{ strSituationText = document.getElementById(strKeyName).value + " " + document.getElementById(strOperator).value + " " + document.getElementById(strValueName).value; }
							else
							{ strSituationText = strSituationText + " and " +  document.getElementById(strKeyName).value + " " + document.getElementById(strOperator).value + " " + document.getElementById(strValueName).value; }
					}
   				}
   				
   			}
		}
		
		if(totalConclusionRows > 1)
			{
			for(var j = 0; j < totalConclusionRows - 1; j++)
			{
			
				var strConclusionKeyName = "rule.conclusionList" + j + ".conclusionKey"; 
				var strConclusionValueName = "rule.conclusionList" + j + ".conclusionValue";
				var strConclusionOperator = "rule.conclusionList" + j + ".conclusionOperator";
				
				if(document.getElementById(strConclusionKeyName).value != "" && document.getElementById(strConclusionOperator).value != "" && document.getElementById(strConclusionValueName).value != "")
   				{ 
   					if(strConclustionText == "")
   					{ 
   						strConclustionText = document.getElementById(strConclusionKeyName).value + " " + document.getElementById(strConclusionOperator).value + " " + document.getElementById(strConclusionValueName).value;
   					}
   					else
						{
   						strConclustionText = strConclustionText + " and " +  document.getElementById(strConclusionKeyName).value + " " + document.getElementById(strConclusionOperator).value + " " + document.getElementById(strConclusionValueName).value;
						}
   				}
				
			}
			}
		if(strSituationText != "")
			{ document.getElementById("lblSituationDescription").innerHTML = strSituationText; }
		else
			{ document.getElementById("lblSituationDescription").innerHTML = ""; }
		
			if(strConditionText != "")
				{ document.getElementById("lblRuleConditionView").innerHTML = "IF ( " + strConditionText + ")"; }
			else
				{ document.getElementById("lblRuleConditionView").innerHTML = "IF (Condition....)"; }
			
			if(strConclustionText != "")
			{ document.getElementById("lblRuleConclusionView").innerHTML = "Then ( " + strConclustionText + " )"; }
		else
			{ document.getElementById("lblRuleConclusionView").innerHTML = "Then (Conclusion....)"; }
			
	}
	
    $(document).ready(function (){
    	
    	
    	
        function bindConcepts(){
    	    var availableTags = [
          "ActionScript",
          "AppleScript",
          "Asp",
          "BASIC",
          "C",
          "C++",
          "Clojure",
          "COBOL",
          "ColdFusion",
          "Erlang",
          "Fortran",
          "Groovy",
          "Haskell",
          "Java",
          "JavaScript",
          "Lisp",
          "Perl",
          "PHP",
          "Python",
          "Ruby",
          "Scala",
          "Scheme"
        ];
        $( ".conceptKey" ).addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" ).autocomplete({
                delay: 0,
                minLength: 0,
                scroll: true,
                //source: availableTags,
                
                source: function (request, response) {
                	
                	 //$.getJSON("http://localhost:8081/AuthoringEnvironment/getAllWellnessConcepts", request, function(result) {  
                	 //$.getJSON("http://163.180.116.194:8081/AuthoringEnvironment/getAllWellnessConcepts", request, function(result) { 
                	 $.getJSON("${pageContext.request.contextPath}/getAllWellnessConcepts", request, function(result) {
                         response($.map(result, function(item) {                	
                             return {
                                 // following property gets displayed in drop down
                                 label: item.wellnessConceptDescription,
                                 // following property gets entered in the textbox
                                 value: item.wellnessConceptDescription
                                 
                             }
                         	
                         }));
                 	});
                },
                change: function(event, ui) {
                    try {
                    	console.log('tables: ' , event);
                    	bindConceptValues(event.target.id, ui.item.value);
                    } catch (err) {
                        
                    }
                },
                click: function(event, ui) {
                    try {
                    	alert('event: ' + event);
                    	//bindConceptValues(event.target.id, ui.item.value);
                    	$( ".conceptKey" ).autocomplete( "search", "" );
                    } catch (err) {
                        
                    }
                }
                
        }).focus(function() {
            $(this).autocomplete('search', $(this).val())
        });
    } 
        
        function bindConceptValues(controlId, selectedKey){
        	if(controlId == "NO") // NO means not restricted on control, else based on control id
        	{
        		 var availableTags = [
        		                      "Value1 No",
        		                      "AppleScript Value1 No",
        		                      "Asp Value1 No",
        		                      "BASIC Value1 No",
        		                      "C Value1 No",
        		                      "C++ Value1 No",
        		                      "Haskell No",
        		                      "Java Value1 No",
        		                      "JavaScript Value1 No",
        		                      "Lisp Value1 No",
        		                      "Perl Value1 No",
        		                      "PHP Value1 No",
        		                      "Python No",
        		                      "Ruby Value1 No",
        		                      "Scala No",
        		                      "Scheme No"
        		                    ];
        		                    $( ".conceptValue" ).addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" ).autocomplete({
        		                            delay: 0,
        		                            minLength: 0,
        		                            scroll: true,
        		                            //source: availableTags,
        		                            
        		                            source: function (request, response) {
        		                            	
        		                            	 //$.getJSON("http://localhost:8081/AuthoringEnvironment/getAllWellnessConcepts", request, function(result) {
        		                                 //$.getJSON("http://163.180.116.194:8081/AuthoringEnvironment/getAllWellnessConcepts", request, function(result) {
        		                                 $.getJSON("${pageContext.request.contextPath}/getAllWellnessConcepts", request, function(result) {
        		                            		
        		                                     response($.map(result, function(item) {                	
        		                                         return {
        		                                             // following property gets displayed in drop down
        		                                             label: item.wellnessConceptDescription,
        		                                             // following property gets entered in the textbox
        		                                             value: item.wellnessConceptDescription
        		                                             
        		                                         }
        		                                     	
        		                                     }));
        		                             	});
        		                            }
        		                            
        		                    }).focus(function() {
        		                        $(this).autocomplete('search', $(this).val())
        		                    });
        	}
        	else
        	{
        		//alert("Before : " + controlId);
        		var correspondingControl = controlId.replace("Key", "Value");
        		//alert("After : " + correspondingControl);
        		//alert(document.getElementById(correspondingControl));
        		var strClass = correspondingControl.replace(/\./g, "");
        		//alert("selectedKey : " + selectedKey);
        		document.getElementById(correspondingControl).setAttribute("class", strClass);
        		 		 var availableTags = [
        		                      "Value1",
        		                      "AppleScript Value1",
        		                      "Asp Value1",
        		                      "BASIC Value1",
        		                      "C Value1",
        		                      "C++ Value1",
        		                      "Haskell",
        		                      "Java Value1",
        		                      "JavaScript Value1",
        		                      "Lisp Value1",
        		                      "Perl Value1",
        		                      "PHP Value1",
        		                      "Python Value1",
        		                      "Ruby Value1",
        		                      "Scala Value1",
        		                      "Scheme Value1"
        		                    ];
        		                    $( "." + strClass ).addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" ).autocomplete({
        		                            delay: 100,
        		                            minLength: 0,
        		                            scroll: true,
        		                            //source: availableTags,
        		                            
        		                            source: function (request, response) {
        		                            	
        		                            		//$.getJSON("http://localhost:8081/AuthoringEnvironment/getWellnessConceptsByKey/" + selectedKey + "/" , request, function(result) {
        		                            		//$.getJSON("http://163.180.116.194:8081/AuthoringEnvironment/getWellnessConceptsByKey/" + selectedKey + "/" , request, function(result) {
        		                            		$.getJSON("${pageContext.request.contextPath}/getWellnessConceptsByKey/" + selectedKey + "/" , request, function(result) {
        		                            			
        		                                     response($.map(result, function(item) {                	
        		                                         return {
        		                                             // following property gets displayed in drop down
        		                                             label: item.wellnessConcept1Description,
        		                                             // following property gets entered in the textbox
        		                                             value: item.wellnessConcept1Description
        		                                             
        		                                         }
        		                                     	
        		                                     }));
        		                             	});
        		                            }
        		                    		
        		                            
        		                    }).focus(function() {
        		                        $(this).autocomplete('search', $(this).val())
        		                    });
        	}
    	  

    } 

    bindConcepts();
    bindConceptValues("NO");
    buildRuleAndSituation();
    
    
    function conditionRowRemove(){
        $(".deleterow").on("click", function(){
        var $killrow = $(this).parent('tr');
            $killrow.addClass("danger");
        $killrow.fadeOut(0, function(){
            $(this).remove();
        });});
    }
    conditionRowRemove();
    // Will add code of add new row for condition and conclusion
    
    function conditionRowAdd()
    {
    	
        $("#addnewrowCondition").on("click", function(){
        	var counter=0;
        	counter = $('#tblCondition tr').length - 1;
        	//alert(counter);
        $('#tblCondition tr:last').after("<tr><td><input id='rule.conditionList"+ counter +".conditionKey' name='rule.conditionList["+ counter +"].conditionKey' type='text' class='conceptKey' value='' onblur='buildRuleAndSituation()' /></td>    <td><select id='rule.conditionList"+ counter +".conditionValueOperator' name='rule.conditionList["+ counter +"].conditionValueOperator' onchange='buildRuleAndSituation()'><option value='='>=</option><option value='>'>></option> <option value='<'><</option><option value='>='>>=</option><option value='<='><=</option></select></td>    <td><input id='rule.conditionList"+ counter +".conditionValue' name='rule.conditionList["+ counter +"].conditionValue' type='text' class='conceptValue' value='' onblur='buildRuleAndSituation()' /></td><td><input id='rule.conditionList"+ counter +".isItSituation1' name='rule.conditionList["+ counter +"].isItSituation' type='checkbox' value='true' onchange='buildRuleAndSituation()'><input type='hidden' name='_rule.conditionList["+ counter +"].isItSituation' value='on'></td><td class='deleterow'><div class='icon-trash'></div><input id='rule.conditionList"+ counter +".ConditionID' name='rule.conditionList["+ counter +"].ConditionID' type='hidden' value='0' /></td></tr>");
            
            bindConcepts();
            bindConceptValues("NO");
            conditionRowRemove();
            counter++;
        });
    } 
    
    function conclusionRowAdd()
    {
        $("#addnewrowConclusion").on("click", function(){
        	var counter=0;
        	counter = $('#tblConclusion tr').length - 1;
        	//alert(counter);
        $('#tblConclusion tr:last').after("<tr><td><input id='rule.conclusionList"+ counter +".conclusionKey' name='rule.conclusionList["+ counter +"].conclusionKey' type='text' class='conceptKey' value='' onblur='buildRuleAndSituation()'/></td>    <td><select id='rule.conclusionList"+ counter +".conclusionOperator' name='rule.conclusionList["+ counter +"].conclusionOperator' onchange='buildRuleAndSituation()'><option value='='>=</option><option value='>'>></option> <option value='<'><</option><option value='>='>>=</option><option value='<='><=</option></select></td>    <td><input id='rule.conclusionList"+ counter +".conclusionValue' name='rule.conclusionList["+ counter +"].conclusionValue' type='text' class='conceptValue' value='' onblur='buildRuleAndSituation()'/></td><td class='deleterow'><div class='icon-trash'></div><input id='rule.conclusionList"+ counter +".ConclusionID' name='rule.conclusionList["+ counter +"].ConclusionID' type='hidden' value='0'/></td></tr>");
            
            bindConcepts();
            bindConceptValues("NO");
            conditionRowRemove();
            counter++;
        });
    }
    
    conditionRowAdd();
    conclusionRowAdd();
    
    // -----------------------------

   

        });
                      
    </script>  
</head>
<body ng-app="MyApp" onload="getParameterByName();">

<!--  Start of header -->
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="#"> <img src="${pageContext.request.contextPath}/resources/images/MMlogo.png" style="height:55px; float:left; margin-right:10px;" /> &nbsp;Expert-Driven Intelligent Knowledge Authoring Tool</a>
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


        <li class="active"><a href="${pageContext.request.contextPath}/rules"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/addNewRule"><i class="icon-list-alt"></i><span>Rule Editor</span> </a> </li>
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


<c:url var="addAction" value="/rules/add" ></c:url>
 
<form:form action="${addAction}" commandName="situationRuleWrapper">
<table>
	<tr>
		<td>
			<div class="boxheader" >
			<h3 class="text-center">
			    Rule Information
			</h3>
			</div>
			 <div class="box">
			 <table>
    
    <tr>
        <td>
            <form:label path="rule.RuleTitle">
                <spring:message text="Rule Title"/>
            </form:label>
            <form:hidden path="rule.RuleID" />
        </td>
        <td>
            <form:input path="rule.RuleTitle" />
        </td>
        <td align="right">
            <form:label path="user.userName">
                <spring:message text="Author's Name"/>
            </form:label>
        </td>
        <td align="center">
            <form:input path="user.userName" />
        </td> 
    </tr>
    <tr>
    	<td>
            <form:label path="rule.RuleTypeID">
                <spring:message text="Rule Type"/>
            </form:label>
        </td>
        <td>
           <form:select path="rule.RuleTypeID">
				  <form:option value="1">Weight Management</form:option>
				  <form:option value="2">Nutrition</form:option>
			</form:select>
        </td>
        <td align="right">
            <form:label path="rule.Institution">
                <spring:message text="Institution"/>
            </form:label>
        </td>
        <td align="center">
            <form:input path="rule.Institution" />
        </td>
    </tr>
    <tr>
    	<td>
            <form:label path="rule.RuleCreatedDate">
                <spring:message text="Rule Created Date"/>
            </form:label>
        </td>
        <td>
            <form:input path="rule.RuleCreatedDate" />
        </td>
        <td align="right">
            <form:label path="rule.SpecialistName">
                <spring:message text="Specialist Name"/>
            </form:label>
        </td>
        <td align="center">
            <form:input path="rule.SpecialistName" />
        </td>
        
    </tr>
    <tr>
    	<td colspan="1">
            <form:label path="rule.RuleDescription">
                <spring:message text="Explanation"/>
            </form:label>
        </td>
        <td colspan="3">
            <form:textarea path="rule.RuleDescription" class="longTextArea"   />
		</td>
    </tr>
    
    </table>
    
    </div>
    <div class="boxheader" >
		<h3 class="text-center">
		    Rule Conditions
		</h3>
	</div>
	<div class="box">
    <table>
    <tr>
    	<td colspan="4" style="width:400px">
    		<input type="button" class='btn btn-lg btn-primary addnewrow pull-right mBottom' value="Add New" id="addnewrowCondition" />
    		     <div class="container">
				    <div class="table-responsive">
				    <table id="tblCondition"  class="table table-striped table-hover  table-bordered">
				<tr>
					<td>Condition Key</td>    <td>Condition Operator</td>    <td>Condition Value</td><td>Is it Situation</td><td></td>
				</tr>
				<c:forEach items="${situationRuleWrapper.rule.conditionList}" var="condition" varStatus="countConditions">
						<tr>
						    <td><form:input type="text" class="conceptKey" path="rule.conditionList[${countConditions.index}].conditionKey" onblur="buildRuleAndSituation()" /></td>
						    <td><form:select path="rule.conditionList[${countConditions.index}].conditionValueOperator" onchange="buildRuleAndSituation()">
									  <form:option value="=">=</form:option>
									  <form:option value=">">></form:option>
									  <form:option value="<"><</form:option>
									  <form:option value=">=">>=</form:option>
									  <form:option value="<="><=</form:option>
								</form:select></td>
						    <td><form:input type="text" class="conceptValue" path="rule.conditionList[${countConditions.index}].conditionValue" onblur="buildRuleAndSituation()"  /></td>
						    <td><form:checkbox path="rule.conditionList[${countConditions.index}].isItSituation" onChange="buildRuleAndSituation()"  /></td>
						    <td class='deleterow'><div class='icon-trash'></div>
						    <form:hidden path="rule.conditionList[${countConditions.index}].ConditionID" /></td>
						</tr>
					</c:forEach>
				    </table>
				</div></div>
    	</td>
    </tr>
    <tr><td colspan="4" height="20px"></td></tr>
    </table>
    </div>
    <div class="boxheader" >
		<h3 class="text-center">
		    Rule Conclusions
		</h3>
	</div>
	<div class="box">
    <table>
    <tr>
    	<td colspan="4" style="width:400px">
    		<input type="button" class='btn btn-lg btn-primary addnewrow pull-right mBottom' value="Add New" id="addnewrowConclusion" />
    		<div class="container">
				    <div class="table-responsive">
			<table id="tblConclusion"  class="table table-striped table-hover table-bordered">
					<tr>
						<td>Conclusion Key</td>    <td>Conclusion Operator</td>    <td>Conclusion Value</td><td></td>
					</tr>
					<c:forEach items="${situationRuleWrapper.rule.conclusionList}" var="conclusion" varStatus="countConclusions">
					<tr>
					    <td><form:input type="text" class="conceptKey" path="rule.conclusionList[${countConclusions.index}].conclusionKey" onblur="buildRuleAndSituation()" /></td>
					    <td><form:select path="rule.conclusionList[${countConclusions.index}].conclusionOperator" onchange="buildRuleAndSituation()">
								  <form:option value="=">=</form:option>
								  <form:option value=">">></form:option>
								  <form:option value="<"><</form:option>
								  <form:option value=">=">>=</form:option>
								  <form:option value="<="><=</form:option>
							</form:select></td>
					    <td><form:input type='text' class="conceptValue" path="rule.conclusionList[${countConclusions.index}].conclusionValue" onblur="buildRuleAndSituation()" /></td>
					    <td class='deleterow'><div class='icon-trash'></div>
					    <form:hidden path="rule.conclusionList[${countConclusions.index}].ConclusionID" /></td>
					</tr>
					</c:forEach>
			</table>
				</div></div>
				    
    	</td>
    </tr>
    <tr>
        <td colspan="2" align="right">
            <form:label path="rule.RuleConclusion">
                <spring:message text="Recommendation"/>
            </form:label>
        </td>
        <td colspan="2">
            <form:textarea path="rule.RuleConclusion" class="middleTextArea" />
        </td>
    </tr>
    </table>
    </div>
    <div class="boxheader" >
		<h3 class="text-center">
		    Selected Situation
		</h3>
	</div>
	<div class="box">
    <table>
    <tr>
        <td colspan="4" align="center">
            <label id="lblSituationDescription">No condition is selected as situation</label>
        </td>
    </tr>
    </table>
    </div>
    <div class="boxheader" >
		<h3 class="text-center">
		    Rule View
		</h3>
	</div>
	<div class="box">
    <table>
    <tr>
        <td colspan="4" align="center">
            <label id="lblRuleConditionView">IF ( Condition... )</label>
            <label id="lblRuleConclusionView">Then ( Conclusion... )</label>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <form:hidden path="user.UserID" />
            <form:hidden path="rule.SituationID" />
            <form:hidden path="rule.RuleCreatedBy" />
        </td>
    </tr>
    <tr>
        
    </tr>
    <tr>
        <td colspan="4">
           <input type="submit" value="<spring:message text="Save Rule" />" class='btn btn-lg btn-primary addnewrow pull-right mBottom' />
           <button  onclick="this.disabled=true;post('/addNewRule')" class='btn btn-lg btn-primary addnewrow pull-right mBottom'>Delete</button>
        </td>
    </tr>
</table>
</div>
		</td>
	</tr>
</table>
<c:if test="${isSaved != null}">
<script>
    alert("Rule has been saved successfully");        
</script>
</c:if>
</form:form>


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
                        
                      <img src="${pageContext.request.contextPath}/resources/images/uclab_logo.png" />
                   
                      
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