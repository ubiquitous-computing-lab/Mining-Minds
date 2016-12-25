<!DOCTYPE html>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<title>Mining Minds Expert View(dashboard)</title>
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- favicon -->
       

        <!-- bootstrap framework -->
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		
        <!-- icon sets -->
            <!-- elegant icons -->
                <link href="assets/icons/elegant/style.css" rel="stylesheet" media="screen">
            <!-- elusive icons -->
                <link href="assets/icons/elusive/css/elusive-webfont.css" rel="stylesheet" media="screen">
            <!-- flags -->
                <link rel="stylesheet" href="assets/icons/flags/flags.css">
            <!-- scrollbar -->
                <link rel="stylesheet" href="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">


        <!-- google webfonts -->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>

        <!-- main stylesheet -->
		<link href="assets/css/main.min.css" rel="stylesheet" media="screen" id="mainCss">

        <!-- moment.js (date library) -->
        <script src="assets/js/moment-with-langs.min.js"></script>

        <style type="text/css">
<!--
.style1 {font-size: 36px}
-->
        </style>
</head>
    <body class="side_menu_active side_menu_expanded">
        <div id="page_wrapper">

            <!-- header -->
            <?php include "header.php";?>

            <!-- breadcrumbs -->
      

            <!-- main content -->
            <div id="main_wrapper">
                <div class="container-fluid"> <?php include "uppermenu.php";?>
                  <div class="row">
                   <div class="col-md-12">
                   
                   
                   
                   <div class="col-md-2 col-sep-ml">
                   
                   
                  
                <h4 class="heading_text">Filters</h4>
                <hr>

<?php 

class activity {

   var $duration;
   var $color;

   function activity($edible, $color="green")
   {
       $this->edible = $edible;
       $this->color = $color;
   }

   function is_duration()
   {
       return $this->duration;
   }

 

} // end of class Vegetable
                                     
?>                            
<form action="" method="get" >
  <select name="parentattribute"  class="target form-control input-sm"  >
    <option value="0">Please Select</option>
    <option value="1">Activity</option>
    <option value="2">Recommendations</option>
    <option value="3">Location</option>
    <option value="4">SNS Trends</option>
    <option value="5">Nutritions</option>

  </select>
  <p></p> <p></p> <p></p>
<div class="options">
</div>

<p>Start Date: <input type="text" id="startdate">
<p>End Date: <input type="text" id="enddate">
<p>
  <button  type="button"  id="sendquery"  class="btn btn-primary" >Send</button>
</form>

                     </div>
                   <div class="col-md-10">
                                     
                            
<div id="graphload"></div>

                     </div>
                   </div>
                   <div >
                   
                   
                   </div>
                   
                    
            </div>
            
            <!-- main menu -->
            <nav id="main_menu">
                
  <div class="menu_toggle">
                    <span class="icon_menu_toggle">
                        <i class="arrow_carrot-2left toggle_left"></i>
                        <i class="arrow_carrot-2right toggle_right" style="display:none"></i>
                    </span>
              </div>
            </nav>

    </div>



<style type="text/css">
#chartdiv {
	width		: 100%;
	height		: 300px;
	font-size	: 11px;
}					
</style>
        <!-- jQuery -->
        <script src="assets/js/jquery.min.js"></script>
        <!-- jQuery Cookie -->
        <script src="assets/js/jqueryCookie.min.js"></script>
        <!-- Bootstrap Framework -->
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- retina images -->
        <script src="assets/js/retina.min.js"></script>
        <!-- switchery -->
        <script src="assets/lib/switchery/dist/switchery.min.js"></script>
        <!-- typeahead -->
        <script src="assets/lib/typeahead/typeahead.bundle.min.js"></script>
        <!-- fastclick -->
        <script src="assets/js/fastclick.min.js"></script>
        <!-- match height -->
        <script src="assets/lib/jquery-match-height/jquery.matchHeight-min.js"></script>
        <!-- scrollbar -->
        <script src="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>


<!-- amCharts javascript sources --> 
<script src="http://www.amcharts.com/lib/3/amcharts.js" type="text/javascript"></script> 
<script src="http://www.amcharts.com/lib/3/serial.js" type="text/javascript"></script> 

<script src="http://www.amcharts.com/lib/3/pie.js"></script>
<script src="http://www.amcharts.com/lib/3/themes/light.js"></script>
        <!-- Yukon Admin functions -->
        <script src="assets/js/yukon_all.min.js"></script>

	    <!-- page specific plugins -->

            <!-- c3 charts -->
            <script src="assets/lib/d3/d3.min.js"></script>
            <script src="assets/lib/c3/c3.min.js"></script>
            <!-- vector maps -->
            <script src="assets/lib/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
            <script src="assets/lib/jvectormap/maps/jquery-jvectormap-world-mill-en.js"></script>
            <!-- countUp animation -->
            <script src="assets/js/countUp.min.js"></script>
            <!-- easePie chart -->
            <script src="assets/lib/easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

            <script>
          $(document).ready(function () {


    $(".target").change(function () {
        var str = "";
        if ($(this).val() != "0") {
           // alert($(this).val());
            str = $(this).val() ;
            $(".options").text(str);
			myFunction();
        }
    });
	

});


	$("#sendquery").click(function () {
	
	
	
	


	
    var checkbox_value = "";
	var radio=$( "input:radio[name=graph]:checked" ).val();
	var startdate=$( "#startdate" ).val();
	var enddate=$( "#enddate" ).val();
	var querytype=$( "#querytype" ).val();
	
	if(startdate==""){
	
		alert("Please select start date.");
        return false;
	
	}
	
	if(enddate==""){
	
		alert("Please select end date.");
        return false;
	
	}
	
	
	
	
	
	
	
	if(querytype=="SNS"){
	
	
			var snstype=$( "input:radio[name=Food]:checked" ).val();
			
			$.ajax({
        type: "GET",
        url: 'ajaxgraphsns.php',
        data: "snstype="+snstype+"&graph="+radio+"&startdate="+startdate+"&enddate="+enddate,
        success: function(response) {
		
			$("#graphload" ).empty();
		   $("#graphload").html(response);

        }
    });

	
	
	
	}
	
	
	else {
	
    $(":checkbox").each(function () {
        var ischecked = $(this).is(":checked");
        if (ischecked) {
            checkbox_value += $(this).val() + ",";
        }
    });
	if(checkbox_value==""){
	
		alert("Please select an option.");
        return false;
	
	}
	
	
	
	
	
	checkbox_value = checkbox_value.substring(0, checkbox_value.length - 1);

	
	$.ajax({
        type: "GET",
        url: 'ajaxgraph.php',
        data: "options="+checkbox_value+"&graph="+radio+"&startdate="+startdate+"&enddate="+enddate+"&querytype="+querytype,
        success: function(response) {
		
			$("#graphload" ).empty();
		   $("#graphload").html(response);

        }
    });
       
}
});


function myFunction() {
// alert($("#wysiwg_editor").val());
var value = $(".target").val();
$.ajax({
        type: "GET",
        url: 'queryoptions.php',
        data: "options="+value,
        success: function(response) {
		
		   $(".options").html(response);

        }
    });
	}
	
	
	
	
</script>      

  <script>
  $(function() {
    $( "#startdate" ).datepicker({
	 numberOfMonths: 2,
        onSelect: function(selected) {
          $("#enddate").datepicker("option","minDate", selected)
        }
	
	});
	 $( "#enddate" ).datepicker({
	 numberOfMonths: 2,
        onSelect: function(selected) {
         $("#startdate").datepicker("option","maxDate", selected)
        }
	
	});
  });
  </script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  

  <link rel="stylesheet" href="/resources/demos/style.css">
    </body>
</html>
