<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    

<HTML><HEAD><TITLE>Data Driven Knowledge Acquisition</TITLE>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="css/ddka-interface.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js" ></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js" ></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script language="javascript">

$(document).ready(function(){
		
	$("#start_btn").click(function () {
    	document.forms['indexForm'].submit() ;
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
		     	<font size="5" color="#004584"><b>Data Driven Knowledge Acquisition Tool </b></font><br><br>&nbsp;
		     	
		     	<div class="row">
		     	<div class="col-md-8 col-md-offset-1" align="left" ><b>Data Driven Knowledge Acquisition Tool</b> 
						(DDKAT) is a web-based application that curates the knowledge using data-driven approach. This tool is designed for the Mining Minds (MM) platform and covers the data-driven part for the Knowledge Curation Layer (KCL).			
						<br><br>
						DDKAT acquires the data, preprocesses it, selects an appropriate decision trees algorithm, generates the classification model, and finally, produces the production rules. The main features include <b>Data Selection</b>, <b>Basic Preprocessing</b>, <b>Appropriate Algorithm Selection</b>, <b>Generation of Classification Model</b>, and <b>Creation of Shareable Production Rules</b>.</div>
				
				<div class="col-md-1" align="center" >
				<form method="POST" name='indexForm' action="dataselectionview">
					<button id="start_btn" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-play"></span> Click to Start</button> 
   				</form>
				</div>
				</div>
				<br><br>
				<div class="row">		
				<div class="col-md-1 col-md-offset-1" align="center" ><img border="0" src="images/DD1.png" height="150"></div>
				<div class="col-md-1 col-md-offset-1" align="center" ><img border="0" src="images/FS.png" height="150"></div>
				<div class="col-md-1" align="center" ><img border="0" src="images/arrow.png" height="150"></div>
				<div class="col-md-2" align="center" ><img border="0" src="images/DT.png" height="150"></div>
				<div class="col-md-1" align="center" ><img border="0" src="images/arrow.png" height="150"></div>
				<div class="col-md-1" align="center" ><img border="0" src="images/DD.png" height="150"></div>
				</div>
				
				<br><br>
				   </div>
                    </TD></TR></TBODY></TABLE>
    <table align="center" style="width: 100%">
           <tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">
        <script language="JavaScript">
            copyright = new Date();
            currentYear = copyright.getFullYear();
            document.write("" + currentYear + " Ubiquitous Computing Lab., Dept. of Computer Engg.");
            </script></font></td>
	</tr>
	<tr>
		<td style="width: 100%; text-align:center">
		<font size="1" color="#39597B">Kyung Hee University, Korea</font></td>
	</tr>
	      
                </table>
</BODY>

</HTML>