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
.style4 {font-size: 24px}
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
                <div class="container-fluid">
                     <?php include "uppermenu.php";?>
                    
                    <div class="row">
                        <div class="col-md-12">
<!--                            <div class="heading_a"><span class="heading_text">Users (location)</span></div>
-->                            <div class="row">
                                <!--<div class="col-md-8">
                                    <div id="world_map_vector" style="width:100%;height:300px">
                                        <script>
                                            countries_data = {
                                                "US": 2320,
                                                "BR": 1945,
                                                "IN": 1779,
                                                "AU": 1486,
                                                "TR": 1024,
                                                "CN": 753
                                            };
                                        </script>
                                    </div>
                                </div>-->
                                <div class="col-md-5">
                                     <div class="heading_a" align="center">SNS Trends of Meat in last week</div>
                            
                            <div id="Fishsnschartdiv"></div>
							

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>


                                </div>
                                
                                <div class="col-md-1">
                
                
                
                            </div>
                            
                            
                            <div class="col-md-6">
                                     <div class="heading_a style4"><span class="heading_a">SNS Trends of Vegetables in last week</span></div>
                              <div id="Vegetablessnschartdiv" style="width: 500px; height: 500px; background-color: #FFFFFF;" ></div>   
                         
                                </div>
                        </div>
                    </div>
                   
                   
                   
                   
                   <div class="row">
                   <div class="col-md-12">
                   
                   
                   
                   
                   
                     
                   
                   </div>
                   <div >
                   
                   
                   </div>
                   
                    
            </div>
            
            <!-- main menu -->
              <?php	  include "leftmenudashboard.php";?>

        </div>



<style type="text/css">
#chartdiv {
	width		: 100%;
	height		: 300px;
	font-size	: 11px;
}

#Fishsnschartdiv {
	width		: 100%;
	height		: 435px;
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
                $(function() {
                    // c3 charts
                    yukon_charts.p_dashboard();
                    // countMeUp
                    yukon_count_up.init();
                    // easy pie chart
                    yukon_easyPie_chart.p_dashboard();
                    // vector maps
                    yukon_vector_maps.p_dashboard();
                    // match height
                    yukon_matchHeight.p_dashboard();
                })
            </script>
		
        
  <script src="assets/js/bubble.js"></script>    
   
    <script src="assets/js/div.js"></script>        

<?php include_once("snsfunctions.php");


foodSNSGraph("Fish","","");
foodSNSGraph("Vegetables","","");?>
 
    <?php // include "divdata.php";?>
    <?php  include "footer.php";?>  
    </body>
</html>
