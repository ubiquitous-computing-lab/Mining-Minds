<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
<HTML><HEAD><TITLE>Data Driven Knowledge Acquisition</TITLE>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://code.jquery.com/jquery-1.12.0.js"></script>
<link href="css/ddka-interface.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js" ></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"> 

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

<script language="javascript">

$(document).ready(function(){
	
	document.getElementById("dataconverter_btn").disabled = true;
	document.getElementById("missingvaluehandler_btn").disabled = true;
	document.getElementById("outlierhandler_btn").disabled = true;
	document.getElementById("discretizer_btn").disabled = true;
	document.getElementById("featureselector_btn").disabled = true;
	document.getElementById("modellearner_btn").disabled = true;
	
	var jsonString ="";
		
	$("#loaddata_btn").click(function () {
				
		var query = $("#loadingdataQueryId").val();
		
		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
			});
		
		// Send the data using post
		$.ajax({ 
			url: "http://private-673d5-data11.apiary-mock.com/data?kcldataquery="+query,
		    type: 'POST',
		    success: function(data) {

		    	var emptyTemp;
		    	
		    	jsonString = "{\"unprocessed_data\": [";
	    	
		    	console.log(data);
		    
		    	var tabletitleHTML='<b>Data Title : <font size="4" color="#004584">'+data.data_name+ '</font></b><br/><br/>';
			   	var tempVar="";
			  	var hrow = document.getElementById("myHeaderRow");
		    	   			   	
				var columncounter = 0;
		    	jsonString += "[";
			   	while(columncounter < data.data_attributes.length){
			   		var headerCell = document.createElement("TH");
			   		headerCell.innerHTML = data.data_attributes[columncounter].attribute_name.toUpperCase();
			   		hrow.appendChild(headerCell);
			   		jsonString += "\""+data.data_attributes[columncounter].attribute_name +"\""+",";
			   		columncounter++;
			   		}
			   	jsonString = jsonString.substring(0, jsonString.length-1);
			   	jsonString += "],";		    	
		    	
		    	var dataSet = "[";
		     	var rowcounter = 0;
			   	while(rowcounter < data.data_values.length){
			   		jsonString += "[";
			   		dataSet += "[";
			   		columncounter = 0;
			   		while(columncounter < data.data_attributes.length){
   						tempVar = data.data_values[rowcounter][columncounter];
			   			if (tempVar==0){   
			   			    tempVar = "";
			   			 	jsonString += "\""+ data.data_values[rowcounter][columncounter] + "\",";
			   			 	dataSet += "\""+ data.data_values[rowcounter][columncounter] + "\",";
			   				}
			   			else{
			   				jsonString += "\""+ tempVar +"\",";
			   				dataSet += "\""+ tempVar +"\",";
			   				}
			   			columncounter++;
				   		}
			   		jsonString = jsonString.substring(0, jsonString.length-1);
			   		dataSet = dataSet.substring(0, dataSet.length-1);
				   	jsonString += "],";
				   	dataSet += "],";
			   		rowcounter++;
			   		}
			   	jsonString = jsonString.substring(0, jsonString.length-1);
			   	dataSet = dataSet.substring(0, dataSet.length-1);
		    	jsonString += "]}";
		    	dataSet += "]";
 			   				   	
		 	   	$("#myTable").DataTable( {
		 	   	"columnDefs": [
		 	   	           { className: "my_class", "targets": [ 0 ] }
		 	   	         ],
		 		 	   		
			        data: JSON.parse(dataSet)
			    } );
		
		    	},
		        error: function(errorThrown){
		            alert(errorThrown);
		            alert("There is an error with AJAX!");
		        }        
		    
			});
		
		document.getElementById("dataconverter_btn").disabled = false;
		});
	
	
	$("#dataconverter_btn").click(function () {
		
		var jsonData = jsonString;
		var request = $.ajax({
			url: "dataconverter",
			type: "POST",
			data: jsonData,
			dataType: "text",
			success : function(response) {
				console.log(response);
				var messageHTML='<font size="3" color="#004584"><b>' + response + ' </b></font>';
				document.getElementById("StorageMessageDiv").innerHTML = messageHTML; 
  		    	},
  		        error: function(errorThrown){
  		            alert("There is an error with Input Data File!");
  		        }     
			});	
		document.getElementById("missingvaluehandler_btn").disabled = false;
		document.getElementById("outlierhandler_btn").disabled = false;
		document.getElementById("discretizer_btn").disabled = false;
		document.getElementById("featureselector_btn").disabled = false;
	    });
	
	$("#missingvaluehandler_btn").click(function () {
		var request = $.ajax({
			url: "missingvaluehandler",
			type: "POST",
			dataType: "json",
			success : function(response) {
				console.log(response);
	            var processConfirmation = response.confirmationMessage[0];
	            console.log(processConfirmation);
	            var messageHTML='<font size="3" color="#004584"><b>' + processConfirmation + '</b></font>';
				document.getElementById("MessageDiv").innerHTML = messageHTML; 
  		    	},
  		        error: function(errorThrown){
  		            alert("There is an error with Input Data File!");
  		        }     
        	});
		document.getElementById("modellearner_btn").disabled = false;
	    });
	
	$("#outlierhandler_btn").click(function () {
		var request = $.ajax({
			url: "outlierhandler",
			type: "POST",
			dataType: "text",
			success : function(response) {
				console.log(response);
	            var messageHTML='<font size="3" color="#004584"><b>' + response + ' </b></font>';
				document.getElementById("MessageDiv").innerHTML = messageHTML; 
  		    	},
  		        error: function(errorThrown){
  		            alert("There is an error with Input Data File!");
  		        }     
			});	
		document.getElementById("modellearner_btn").disabled = false;
	    });
		
	$("#discretizer_btn").click(function () {
		var request = $.ajax({
			url: "datadiscretizer",
			type: "POST",
			dataType: "json",
			success : function(response) {
				console.log(response);
	            var processConfirmation = response.confirmationMessage[0];
	            console.log(processConfirmation);
	            var messageHTML='<font size="3" color="#004584"><b>' + processConfirmation + '</b></font>';
	            document.getElementById("MessageDiv").innerHTML = messageHTML; 
  		    	},
  		        error: function(errorThrown){
  		            alert("There is an error with Input Data File!");
  		        }     
        	});
		document.getElementById("modellearner_btn").disabled = false;
	    });
	
	
	$("#featureselector_btn").click(function () {
		var request = $.ajax({
			url: "featuresselector",
			type: "POST",
			dataType: "json",
			success : function(response) {
				console.log(response);
	            var processConfirmation = response.confirmationMessage[0];
	            console.log(processConfirmation);
	            var messageHTML='<font size="3" color="#004584"><b>' + processConfirmation + '</b></font>';
	            document.getElementById("MessageDiv").innerHTML = messageHTML; 
  		    	},
  		        error: function(errorThrown){
  		            alert("There is an error with Input Data File!");
  		        }     
        	});
		document.getElementById("modellearner_btn").disabled = false;
	    });

		
	$("#modellearner_btn").click(function () {
		$("#communicationVariable").val("Communication Test");
		document.forms['modellearnerForm'].submit() ;
	});	
});

</script>	

</HEAD>
<BODY>
<TABLE id=header cellPadding=0 style="width: 100%">
  <TBODY>
  <TR>
    <TD align="left" style="width: 100%" >
	<img border="0" src="images/banner.jpg" height="106"></TD>
    <TD align="right" style="width: 30%">
	<img border="0" src="images/MM_logo.png" height="106" ></TD>
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
		  
		  
<TABLE id=main cellPadding=0 style="width: 100%">
  <TBODY>
  <TR>
    <TD id=leftMenu width="100%" rowspan="2">
                 &nbsp;</TD>
    </TR></TBODY></TABLE>
    
    <TABLE align=center border=2 borderColor=#39597d width="100%">
              <TBODY>
              <TR>
                <TD>
        <br><br>
        <div align="center">    
		     	<font size="5" color="#004584"><b>Data Preprocessor </b></font><br><br><br>
		 <p class="bg-info"><b>For loading of the data from an external source, click <font color="#004584"><i>'Load Data'</i></font> button.</b></p>
				<br>  
		     	<button id="loaddata_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-cloud-download"></span> Load Data</button>
                </div>                          
                <textarea id="loadingdataQueryId" style="visibility: hidden">${loadingdataQueryId}</textarea>
        <table id="myTable" class="display" width="80%">
  		<thead align="center">
            <tr id="myHeaderRow" >
            </tr>
        </thead>
        <tbody align="center"></tbody>
  		</table>
 		<br>
		<br>
		<div align="center">
		<p class="bg-info"><b>To start data preprocessing tasks, click <font color="#004584"><i>'Save External Data'</i></font> button to store the data into your local machine.</b></p>
			<br>  
			<button id="dataconverter_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-floppy-disk"></span> Save External Data</button>
  		<br><br>
  		<div id="StorageMessageDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div><br/>
		<br>
  		<p class="bg-info"><b>Preprocessor Panel</b></p>
			<br>  
  		    <button id="missingvaluehandler_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-star-empty"></span> Fill Missing Values</button>
  		    <button id="outlierhandler_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-trash"></span> Remove Outliers</button>
  		    <button id="discretizer_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-retweet"></span> Invoke Data Transformation</button>
  		    <button id="featureselector_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-filter"></span> Invoke Features Selection</button>
	<br><br><br>
	<div id="MessageDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div><br/>
  
		<p class="bg-info"><b>For creating Classification Model, click <font color="#004584"><i>'Model Learner'</i></font> button.</b></p>
			<br> 
		<form method="POST" name='modellearnerForm' action="modellearner">
			<button id="modellearner_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-cog"></span> Model Learner </button>
			<input type="hidden" id="communicationVariable" name="communicationVariable" />
  		</form>
  		  </div>
	<br><br>

               </TD></TR></TBODY></TABLE>
    <table align="center" style="width: 100%">
           <tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">
        <script language="JavaScript">
            copyright = new Date();
            currentYear = copyright.getFullYear();
            document.write("�" + currentYear + "Ubiquitous Computing Lab., Dept. of Computer Engg.");
            </script></font></td>
	</tr>
	<tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">Kyung Hee University, Korea</font></td>
	</tr>
	      
                </table>
                </BODY>

</HTML>