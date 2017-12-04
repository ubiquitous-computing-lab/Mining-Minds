<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML><HEAD><TITLE>Data Driven Knowledge Acquisition</TITLE>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="css/ddka-interface.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js" ></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"> 

<script language="javascript">

$(document).ready(function(){
	
	document.getElementById("learnmodel_btn").disabled = true;
	document.getElementById("translatemodel_btn").disabled = true;
	
	$('#algolist li').on('click', function(){
	    $('#algotxtbox').val($(this).text());
	    var algo = $("#algotxtbox").val();
	    var selectedAlgoHTML='<br><br><font size="3" color="#004584"><b>Algorithm Selected for Model Learning : </b></font><font size="3" color="red"><b>'+algo+'</b></font><br><br>';
	    document.getElementById("ManualSelectedAlgorithmDiv").innerHTML = selectedAlgoHTML;
	    document.getElementById("learnmodel_btn").disabled = false;
	});
	
	
	$("#autoalgorithmselector_btn").click(function () {
		
		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
			});
		
		var request = $.ajax({
			url: "autoalgoselection",
			type: "POST",
			dataType: "json",
			success : function(response) {
				var tabletitleHTML='<b><font size="4" color="#004584">Top (k=3) Recommended Algorithms</font></b><br/><br/>';
			   	var tableHTML=''; 
				var headerrowHTML='<tr><td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Rank</b></font></td>';
				headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Case ID</b></font></td>';
				headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Algorithm</b></font></td>';
				headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Similarity Score</b></font></td></tr>';
			   	var datarowsHTML='';
			   	var rowcounter = 0;
			   	while(rowcounter < response.autoSelectedAlgos.length){
			   		datarowsHTML+='<tr>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="5%" align="center"><font face="Times New Roman" size="3" color="#004584">' + (rowcounter+1) + '</font></td>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="15%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.caseIDs[rowcounter] + '</font></td>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="15%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.autoSelectedAlgos[rowcounter] + '</font></td>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="15%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.similarityScores[rowcounter] + '</font></td>';
					datarowsHTML+='</tr>';
			   		rowcounter++;
			   		}		    		    			   	
			   	tableHTML = tabletitleHTML + '<table border="2" width="100%" style="width: 40%" bordercolor="#004584">' + headerrowHTML + datarowsHTML + '</table>';
			   	document.getElementById("SimilarCasesDiv").innerHTML = tableHTML;
		
			 	var autoselectedalgotableHTML='<font size="5" color="#004584"><b>Best Recommended Algorithm : </b></font><font size="5" color="red"><b>' + response.autoSelectedAlgos[0] + '</b></font>';
				document.getElementById("MachineSelectedAlgorithmDiv").innerHTML = autoselectedalgotableHTML; 						
				},
			error: function(errorThrown) {
				alert("error : " + errorThrown.responseText);
				}
				
			});
		});	
	
	
	$("#learnmodel_btn").click(function () {
		
		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
			});
		
		var algo = $("#algotxtbox").val();
		$("#algoTxtAreaVal").val(algo);
		var request = $.ajax({
		url: "learnmodel",
		type: "POST",
		data: algo,
		dataType: "json",
		success : function(response) {
			var accuracytableHTML='<br><br><b><font size="5" color="#004584">Accuracy of Classification Model</font></b><br/><br/>';
			accuracytableHTML=accuracytableHTML+'<font size="3" color="#004584"><b>Original Data Accuracy : </b></font><font size="3" color="red"><b>' + response.modelAccuracies[0] + '</b></font><br><br>';
			accuracytableHTML=accuracytableHTML+'<font size="3" color="#004584"><b>Processed Data Accuracy : </b></font><font size="3" color="red"><b>' + response.modelAccuracies[1] + '</b></font><br><br>';
			document.getElementById("ModelAccuraciesDiv").innerHTML = accuracytableHTML;
			
			$("#decisionattributeTxtAreaVal").val(response.decisionAttribute);
			$("#modelStringTxtAreaVal").val(response.modelString);
						
			},
		error: function(errorThrown) {
			alert("error : " + errorThrown.responseText);
			}
			
			});		

		document.getElementById("translatemodel_btn").disabled = false;
		
	});	
	
     
    $("#translatemodel_btn").click(function () {
    	   	
		var algo = $("#algoTxtAreaVal").val();
		$("#algoVar").val(algo);
		var decisionattribute = $("#decisionattributeTxtAreaVal").val();
		$("#decisionattributeVar").val(decisionattribute);
		var modelstring = $("#modelStringTxtAreaVal").val();
		$("#modelstringVar").val(modelstring);
		   		
		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
			});
		    	
 			var request = $.ajax({
			url: "modeltranslator",
			type: "POST",
			data: algo+"#####"+decisionattribute+"#####"+modelstring,
			dataType: "json",
			success : function(response) {
				var tabletitleHTML='<b><font size="5" color="#004584">Rules extracted from Classification Model</font></b><br/><br/>';
			   	var tableHTML=''; 
				var headerrowHTML='<tr><td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Rule ID</b></font></td>';
			   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Conditions</b></font></td>';
			   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#FF9900"><b>Conclusion</b></font></td></tr>';
			   	var datarowsHTML='';
			   	var rowcounter = 0;
			   	while(rowcounter < response.ruleConditions.length){
			   		datarowsHTML+='<tr>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="10%" align="center"><font face="Times New Roman" size="3" color="#004584">' + (rowcounter+1) + '</font></td>';
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="55%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.ruleConditions[rowcounter] + '</font></td>';		  
			   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="20%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.ruleConclusions[rowcounter] + '</font></td>';
				   	datarowsHTML+='</tr>';
			   		rowcounter++;
			   		}				    			   	
			   	tableHTML = tabletitleHTML + '<table border="2" width="100%" style="width: 60%" bordercolor="#004584">' + headerrowHTML + datarowsHTML + '</table>';
			   	document.getElementById("ExtractedRulesDiv").innerHTML = tableHTML;
				},
			error: function(errorThrown) {
				alert("error : " + e.responseText);
				}
 			});
 			
		});

});
</script>
</HEAD>
<BODY>

<TABLE id=header cellPadding=0 style="width: 100%">
  <TBODY>
  <TR>
    <TD align="left" class="style1">
	<img border="0" src="images/banner.jpg" height="106"></TD>
    <TD align="right" style="width: 30%">
	<img border="0" src="images/MM_logo.png" height="106"></TD>
    </TR></TBODY></TABLE>
<TABLE cellPadding=0>
  <TBODY>
  <TR>
    <TD id=tran1></TD></TR>
  <TR>
    <TD id=tran2 height="171"></TD></TR></TBODY></TABLE>
<TABLE id=menubar cellPadding=0>
  <TBODY>
  <TR>
    <TD>
     <TABLE style="WIDTH: 100%" cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD align=right>
          <a href="index.jsp">
		  <img border="0" src="images/main.gif" height="21"></a>
          </TD>
		  </TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
		  
		  
<TABLE id=main cellPadding=0 width="777" style="width: 100%">
  <TBODY>
  <TR>
    <TD id=leftMenu width="654" rowspan="2">
                 &nbsp;</TD>
    </TR></TBODY></TABLE>
    
    <TABLE align=center border=2 borderColor=#39597d width="82%">
              <TBODY>
              <TR>
                <TD>
        <br><br>
		<div align="center">
		    <font size="5" color="#004584"><b>Model Learner</b></font><br><br><br>
		    <p class="bg-info"><b>For Automatic Algorithm Selection, click <font color="#004584"><i>'Automatic Algorithm Selector'</i></font> button.</b></p>
		    <br>
			<button id="autoalgorithmselector_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-asterisk"></span> Automatic Algorithm Selector</button>
		    <br><br><div id="SimilarCasesDiv" align="center" style="border: 0px #d3d3d3 solid;"></div>
			<br><br><div id="MachineSelectedAlgorithmDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div><br/>
			<br><br>
		    <p class="bg-info"><b>For creating Classification Model, choose the decision tree algorithm from list and then click  <font color="#004584"><i>'Learn Model'</i></font> button.</b></p>
		    <br>
			
			<div class = "btn-group">
			   <button id="algobtn" type = "button" class = "btn btn-primary dropdown-toggle" data-toggle = "dropdown"><span class="glyphicon glyphicon-check"></span> 
			      Choose Decision Tree Algorithm 
			      <span class = "caret"></span>
			   </button>
			   <ul id="algolist" class = "dropdown-menu" role = "menu">
			      <li><a>BFTree</a></li>
			      <li><a>FT</a></li>
			      <li><a>J48</a></li>
			      <li><a>J48graft</a></li>
			      <li><a>RandomTree</a></li>
			      <li><a>REPTree</a></li>
			      <li><a>SimpleCart</a></li>
			   </ul>
			</div>
			<div id="ManualSelectedAlgorithmDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div><br/>
			<br>
		    <button id="learnmodel_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-cog"></span> Learn Model</button>
		    <div id="ModelAccuraciesDiv" align="center" style="border: 0px #d3d3d3 solid;"></div>
			<br><br>
  			
		    <p class="bg-info"><b>For extracting rules from Classification Model and sharing with expert, click  <font color="#004584"><i>'Translate Model'</i></font> button.</b></p>
		    <br>
			<form method="POST" name='modelTranslationForm' action="modeltranslation">
				<button id="translatemodel_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-cog"></span> Translate Model</button>
				<input type="hidden" id="algoVar" name="algoVar" />
				<input type="hidden" id="decisionattributeVar" name="decisionattributeVar" />
				<input type="hidden" id="modelstringVar" name="modelstringVar" />
  			</form>
		</div>
		<br/><br/>
		<div class="row">
		<div id="ExtractedRulesDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div>
		</div>
	     <input type="TextBox" ID="algotxtbox" Class="form-control" style="visibility: hidden"></input>
	     <textarea id="algoTxtAreaVal" style="visibility: hidden"></textarea>
	    <textarea id="decisionattributeTxtAreaVal" style="visibility: hidden"></textarea>
	    <textarea id="modelStringTxtAreaVal" style="visibility: hidden"></textarea>
	    </TD></TR></TBODY></TABLE>

    <table align="center" style="width: 100%">
           <tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">
        <script language="JavaScript">
            copyright = new Date();
            currentYear = copyright.getFullYear();
            document.write("�" + currentYear + "Ubiquitous Computing Lab., Dept. of Computer Engg.");
            </script>
            </font></td>
	</tr>
	<tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">Kyung Hee University, Korea</font></td>
	</tr>
	      
                </table>
                </BODY>

</HTML>