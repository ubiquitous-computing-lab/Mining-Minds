<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    

<HTML><HEAD><TITLE>Data Driven Knowledge Acquisition</TITLE>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery.tree-multiselect.min.js" ></script>
<script type="text/javascript" src="js/bootstrap.min.js" ></script> 
<link href="css/jquery.tree-multiselect.css" rel="stylesheet" type="text/css">
<link href="css/ddka-interface.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"> 

<script language="javascript">

$(document).ready(function(){
	
	document.getElementById("loadselectedfeatures_btn").disabled = true;
	document.getElementById("visualizefeaturesscorecard_btn").disabled = true;
	document.getElementById("preprocessorloader_btn").disabled = true;
	
	$("#loadschema_btn").click(function () {

	jQuery.support.cors = true;
		
	$.ajax({
	    type : "GET",
		url : "http://private-b0977-schema5.apiary-mock.com/schema",
        dataType: "json",
       
	    success : function(data) {
	        var schematitle='<b>Schema Title : <font size="4" color="#004584">'+data.schema_name+ '</font></b><br><br>(Note: Please select the features to build the feature model) ';
		   	$('#schemaname').append(schematitle);
		   	var dataindex=1;
		   	var tableindex=0;
	       	while(tableindex < data.schema_tables.length ){
	       		var columnindex=0;
	       		while(columnindex < data.schema_tables[tableindex].table_metadata.length ){
	       			var treenodehtml='<option value="' + data.schema_tables[tableindex].table_name.toUpperCase()+'___'+data.schema_tables[tableindex].table_metadata[columnindex].column_name + '" data-section="' + data.schema_name +'/'+ data.schema_tables[tableindex].table_name.toUpperCase() + '" data-index="' + dataindex + '">' + data.schema_tables[tableindex].table_metadata[columnindex].column_name + ' ' + data.schema_tables[tableindex].table_metadata[columnindex].datatype + '</option>';
	       			$('#schematree').append(treenodehtml);
	       	   		columnindex = columnindex + 1;
	    	   		dataindex = dataindex + 1;
	       			}
	       		tableindex = tableindex + 1;	       	
	       		}
	       	$("#schematree").css('visibility','visible');
	       	$("#preprocessorloader_btn").css('visibility','visible');
	    	var options = { };
	    	$("select#schematree").treeMultiselect(options);
	    },
	    error: function (jqXHR, status, err) {
	    	console.log( 'something went wrong', status, err );
            if (jqXHR.status === 0) {
	                alert('Connection problem, please verify the DCL connection.');
	    	 	}
 	    	}
		}); // success or Error Ending 
	      	
		var button = document.getElementById('loadschema_btn');
		button.setAttribute('disabled', true);
	
		if(button.disabled)
	  		{
			button.style.cursor='hand';
	  		}
	  	else
	  		{
			button.style.cursor='default';
	  		}
		
		document.getElementById("loadselectedfeatures_btn").disabled = false;
		
        });
	 	
  	
    $("#loadselectedfeatures_btn").click(function () {
    	
    	$('#TextBoxesGroupDiv').empty();
    	
    	var i = 0;
    	var featurescounter=0;
    	var e = document.getElementById("schematree");
		var listLength = e.options.length;
		while(i<listLength){
			if(e.options[i].selected == true){
	  			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + e.options[i].value); 
				newTextBoxDiv.after().html('<br/><label class="col-md-5 col-md-offset-1">'+ e.options[i].innerHTML + ' : </label>' + '<input type="text" id="textbox' + e.options[i].value + '" class="col-md-offset-1" value="">');  		  		            
		  		newTextBoxDiv.appendTo("#TextBoxesGroupDiv");
		  		featurescounter++;
				}
			i++;
			}
		
		if(featurescounter<1){
			alert("Please select Features first then click the 'Load Query Panel' button !");
			}
		else{
			document.getElementById("visualizefeaturesscorecard_btn").disabled = false;
			document.getElementById("preprocessorloader_btn").disabled = false;
			}
	    });
    
    
    $("#visualizefeaturesscorecard_btn").click(function () {
    	
		var jsonString ="";
		var j=0;
       	var queryObject = '[';
    	var absColumnName = [];
    	var tableName = '';
    	var columnName = '';
    	var tempQueryVar = '';

     	var e = document.getElementById("schematree");
 		var listLength = e.options.length;
 		
 		while(j<listLength){
 			if(e.options[j].selected == true){
 				absColumnName = (e.options[j].value).split("___");
 				tableName = absColumnName[0];
 				columnName = absColumnName[1];
 				queryObject += '{ \"table\":"'+ tableName +'", \"column\":"' + columnName + '", \"condition\":"' + $('#textbox' + e.options[j].value).val() + '"},';
 				tempQueryVar += tableName + columnName + $('#textbox' + e.options[j].value).val();
 				}
 			j++;	
 			}
		var query = queryObject.substring(0, queryObject.length-1);
		query += ']';
		
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
		   	var tempVar="";
	    	   			   	
			var columncounter = 0;
	    	jsonString += "[";
		   	while(columncounter < data.data_attributes.length){
		   		jsonString += "\""+data.data_attributes[columncounter].attribute_name +"\""+",";
		   		columncounter++;
		   		}
		   	jsonString = jsonString.substring(0, jsonString.length-1);
		   	jsonString += "],";		    	
	    	
	     	var rowcounter = 0;
		   	while(rowcounter < data.data_values.length){
		   		jsonString += "[";
		   		columncounter = 0;
		   		while(columncounter < data.data_attributes.length){
						tempVar = data.data_values[rowcounter][columncounter];
		   			if (tempVar==0){   
		   			    tempVar = "";
		   			 	jsonString += "\""+ data.data_values[rowcounter][columncounter] + "\",";
		   				}
		   			else{
		   				jsonString += "\""+ tempVar +"\",";
		   				}
		   			columncounter++;
			   		}
		   		jsonString = jsonString.substring(0, jsonString.length-1);
			   	jsonString += "],";
		   		rowcounter++;
		   		}
		   	jsonString = jsonString.substring(0, jsonString.length-1);
	    	jsonString += "]}";

	    	//console.log(jsonString);
	    	var filePath = "D:/DDKAResources/";
			var request = $.ajax({
				url: "featuresvisualizer?filepath="+filePath,
				type: "POST",
				data: jsonString,
				dataType: "json",
				success : function(response) {
	                var tabletitleHTML='<b><font size="4" color="#004584">Features Scorecard</font></b><br/><br/>';
				   	var tableHTML=''; 
					var headerrowHTML='<tr><td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#004584"><b>Feature Name</b></font></td>';
				   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#004584"><b>Feature Score</b></font></td>';
				   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#004584"><b>Relative Feature Weightage</b></font></td>';	   	
				   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#004584"><b>Feature Priority</b></font></td>';	   	
				   	headerrowHTML+='<td style="border-style: solid; border-width: 1px" vertical-align:middle" align="center"><font face="Times New Roman" size="3" color="#004584"><b>Feature Ranking</b></font></td></tr>';
				   	var datarowsHTML='';
				   	var rowcounter = 0;
				   	while(rowcounter < response.featureTitles.length){
				   		datarowsHTML+='<tr>';
				   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle; padding-left: 7px;" width="20%" align="left"><font face="Times New Roman" size="3" color="#004584">' + response.featureTitles[rowcounter] + '</font></td>';
				   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="20%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.featureScores[rowcounter] + '</font></td>';		  
				   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="20%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.featureWeights[rowcounter] + ' %</font></td>';		  
				   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="20%" align="center"><font face="Times New Roman" size="3" color="#004584">' + response.featurePriorities[rowcounter] + '</font></td>';		  
				   		datarowsHTML+='<td style="border-style: solid; border-width: 1px; vertical-align:middle" width="20%" align="center"><font face="Times New Roman" size="3" color="#004584">' + (rowcounter+1) + '</font></td>';
					   	datarowsHTML+='</tr>';
				   		rowcounter++;
				   		}				    			   	
				   	tableHTML = tabletitleHTML + '<table border="2" width="100%" style="width: 70%" bordercolor="#004584">' + headerrowHTML + datarowsHTML + '</table>';
				   	document.getElementById("AttributeImportanceDiv").innerHTML = tableHTML;
				},
				error: function(e) {
					alert("error : " + e.responseText);
				}
				
	 		});		

	    	},
	        error: function(errorThrown){
	            alert(errorThrown);
	            alert("There is an error with AJAX!");
	        }        
		});
	});

  
    $("#preprocessorloader_btn").click(function () {
    	 
    	j=0;
    	var queryObject = '[';
    	var absColumnName = [];
    	var tableName = '';
    	var columnName = '';
    	var tempQueryVar = '';

     	var e = document.getElementById("schematree");
 		var listLength = e.options.length;
 		
 		while(j<listLength){
 			if(e.options[j].selected == true){
 				absColumnName = (e.options[j].value).split("___");
 				tableName = absColumnName[0];
 				columnName = absColumnName[1];
 				queryObject += '{ \"table\":"'+ tableName +'", \"column\":"' + columnName + '", \"condition\":"' + $('#textbox' + e.options[j].value).val() + '"},';
 				tempQueryVar += tableName + columnName + $('#textbox' + e.options[j].value).val();
 				}
 			j++;	
 			}
		var query = queryObject.substring(0, queryObject.length-1);
		query += ']';
		
		$("#loadingdataQuery").val(query);
		document.forms['featuremodelForm'].submit();
		
  		});
  
	});

</script>	

</HEAD>
<BODY>

<TABLE id=header cellPadding=0 style="width: 100%">
  <TBODY>
  <TR>
    <TD align="left" class="style1">
   	<img border="0" src="images/banner.jpg" height="106" /></TD>
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
    <TABLE align=center border=2 borderColor=#39597d cellPadding=5 cellSpacing=5 width="82%">
              <TBODY>
              <TR>
                <TD>
        <br><br>
		<div align="center">
		     	<font size="5" color="#004584"><b>Data Selector</b></font><br><br><br>
		     	<p class="bg-info"><b>For loading of dataset schema, click <font color="#004584"><i>'Load Schema'</i></font> button.</b></p>
				<br>
				
				<form id = "form" action = "#" name = "form">
	  		  	<button id="loadschema_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-cloud-download"></span> Load Schema</button>
	  		  	</form>
	  		  	
                </div>
                    <br/>
                    <div id="schemaname" align="center"></div>
                    <br/>
                    <select id="schematree" multiple="multiple"></select>
<br/><br/>
<div align="center">
<p class="bg-info"><b>Query Configuration Panel</b></p><br>
	<div class="row">
		<div class="col-md-2 col-md-offset-1" align="left"> <button id="loadselectedfeatures_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-download-alt"></span> Load Query Panel</button></div>
		<div class="col-md-7" align="left">
			<fieldset>
				<div id="TextBoxesGroupDiv" style="border: 1px #d3d3d3 solid;"><br/></div><br/><br/>	
			</fieldset>
		</div>
	</div>
</div>
<div>
</div> 
<div class="row">
		<div id="AttributeImportanceDiv" align="center" style="border: 0px #d3d3d3 solid;"><br/></div><br/>
</div>
<br/>
	<div align="center">
		<form method="POST" name='featuremodelForm' action="preprocessor">
			<button id="visualizefeaturesscorecard_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-search"></span> Visualize Features Scorecard</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="preprocessorloader_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-forward"></span> Data Preprocessor </button> 
  			<input type="hidden" id="loadingdataQuery" name="loadingdataQuery" />
  		</form>
	</div>
  <br/>
  <br/>
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
                </TD>
    </TR></TBODY></TABLE>
 </BODY></HTML>