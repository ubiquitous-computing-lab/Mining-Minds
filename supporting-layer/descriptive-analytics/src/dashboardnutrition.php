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
                                     <div class="heading_a" align="center"><span class="heading_a style4">SNS Fruit Trend</span></div>
                            
                            <div id="fooddashboardGraph"></div>
							

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>


                                </div>
                                
                                <div class="col-md-1">
                
                
                
                            </div>
                            
                            
                            <div class="col-md-6">
                                     <div class="heading_a style4"><span class="heading_a">Most Food Consumed by Users</span></div>
                              <div id="foodbreakdownchart" style="width: 500px; height: 500px; background-color: #FFFFFF;" ></div>   
                         
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

#fooddashboardGraph {
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




class dashboardNutrition {


   		private $data = array();
	
	
		public function __set($variable, $value){
        $this->data[$variable] = $value;
    }

    	public function __get($variable){
			if(isset($this->data[$variable])){
				return $this->data[$variable];
			}else{
				die('Unknown variable.');
			}
    }
		
		
/*********************************************************************************************

Food breakdown with respect to fats carbs and proteins
**********************************************************************************************/


				public function foodbreakdown() {
	
		
		
		

/*********************************************************************************************

Call the service with last 90 days
**********************************************************************************************/


						$starttimeobject = new DateTime('-89 days');
						//$starttimeobject = new DateTime('2015-05-16');
						$starttimeobject->setTime(00,00,00);
						
						//set end time today
						$endtimeobject = new DateTime('NOW');
						$endtimeobject->setTime(23,59,59);
						
						//convert date objects into strings
						$start = $starttimeobject->format('Y m d H:i:s');
						$end = $endtimeobject->format('Y m d H:i:s');
						
						
						
						//assign userid
						//$uid=35;
						
						//create object and get data from DCL
						$data = array(
							
									"userId"=>0,
									"startTime"=>$start,
									"endTime"=>$end
							
							);




						global $dclserver;
						$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetFoodLogByUserIDDateRange";
						
						
						
								
								
						$json = json_encode($data,true);
									
						$result =postJsonRest($url,$json);		
						$result=json_decode($result,true);	
						
						$foodName=array();
						
						for($i=0;$i<count($result);$i++){
						
						
								//get food name 
								$foodName[$i]=$result[$i]['foodName'];
								
								
						}		
						
	 					//create unique array of fooditems eaten 
						
						$foodName = array_unique($foodName);
						$foodName = array_values($foodName);
						
						
						
						//create empty array of food and its total breakdown of nutrients in a month
						$breakdown=array();
						for($i=0;$i<count($foodName);$i++){
										
										
									$breakdown[$i]['foodName']=$foodName[$i];
									$breakdown[$i]['totalFat']=0;
									$breakdown[$i]['totalCarbohydrate']=0;
									$breakdown[$i]['totalProtein']=0;
						
							}
						
						for($i=0;$i<count($result);$i++){
						
						
								//find index of food present in monthly and add all the nutrients of that food in the month
						
								$key = array_search($result[$i]['foodName'], array_column($breakdown, 'foodName'));
								$breakdown[$key]['totalProtein']=$breakdown[$key]['totalProtein']+$result[$i]['totalProtein'];
								$breakdown[$key]['totalCarbohydrate']=$breakdown[$key]['totalCarbohydrate']+$result[$i]['totalCarbohydrate'];
								$breakdown[$key]['totalFat']=$breakdown[$key]['totalFat']+$result[$i]['totalFat'];
								
								
						}			
						
						
						


?>


<script>
var chart = AmCharts.makeChart( "foodbreakdownchart", {
  "type": "serial",
  "theme": "light",
  "depth3D": 20,
  "angle": 30,
  "legend": {
    "horizontalGap": 10,
    "useGraphSettings": true,
    "markerSize": 10
  },
  "dataProvider": [ 
  
   <?php
  
  		for($i=0;$i<count($breakdown)-5;$i++){
		
		echo '{"Word":"'.$breakdown[$i]['foodName'].'","Protein":'.$breakdown[$i]['totalProtein'].',"Fat":'.$breakdown[$i]['totalFat'].',"Carbohydrates":'.$breakdown[$i]['totalCarbohydrate'].'},';
			
		
		}
  
  
  ?>
	  
   ],
  "valueAxes": [ {
    "stackType": "regular",
    "axisAlpha": 0,
    "gridAlpha": 0
  } ],
  "graphs": [  {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Protein",
    "type": "column",
    "color": "#000000",
    "valueField": "Protein"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Fat",
    "type": "column",
    "color": "#000000",
    "valueField": "Fat"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Carbohydrates",
    "type": "column",
    "color": "#000000",
    "valueField": "Carbohydrates"
  } ],
  "categoryField": "Word",
  "categoryAxis": {
    "gridPosition": "start",
    "axisAlpha": 0,
    "gridAlpha": 0,
    "position": "left"
  },
  "export": {
    "enabled": true
  }

} );
</script>

<?php
						
	 
	 
		
		
		}
		
		 public function fooddashboardGraph(){
		
		
?>

<script>

var chart = AmCharts.makeChart("fooddashboardGraph", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [
			
					
					
									
													{
											"Word": "Kiwi",
											"Occurrence": 1581,
											"color": "#B0DE09"
											
										},
									
													{
											"Word": "Peach",
											"Occurrence": 15663,
											"color": "#04D215"
											
										},
									
													{
											"Word": "Apple",
											"Occurrence": 58164,
											"color": "#0D8ECF"
											
										},
									
													{
											"Word": "Apricot",
											"Occurrence": 2145,
											"color": "#0D52D1"
											
										},
									
													{
											"Word": "Melon",
											"Occurrence": 46055,
											"color": "#2A0CD0"
											
										},
									
													{
											"Word": "Banana",
											"Occurrence": 20297,
											"color": "#8A0CCF"
											
										},
									
													{
											"Word": "grapefruit",
											"Occurrence": 10030,
											"color": "#CD0D74"
											
										},
									
													{
											"Word": "avocado",
											"Occurrence": 2593,
											"color": "#754DEB"
											
										},
									
													{
											"Word": "Lemon",
											"Occurrence": 9675,
											"color": "#DDDDDD"
											
										},
									
													{
											"Word": "orange",
											"Occurrence": 4231,
											"color": "#999999"
											
										},							
	

	
	
	],
    "valueAxes": [{
        "position": "left",
        "title": "Frequency"
    }],
    "graphs": [{
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillColorsField": "color",
        "fillAlphas": 1,
        "lineAlpha": 0.1,
        "type": "column",
        "valueField": "Occurrence"
    }],
    "depth3D": 20,
	"angle": 30,
    "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
    },    
    "categoryField": "Word",
    "categoryAxis": {
        "gridPosition": "start",
        "labelRotation": 0
    },
    "export": {
    	"enabled": true
     }

});
jQuery('.chart-input').off().on('input change',function() {
	var property	= jQuery(this).data('property');
	var target		= chart;
	chart.startDuration = 0;

	if ( property == 'topRadius') {
		target = chart.graphs[0];
      	if ( this.value == 0 ) {
          this.value = undefined;
      	}
	}

	target[property] = this.value;
	chart.validateNow();
});

</script>


 
        <script>
	$("#pushnotify").click(function () {
	
   	$.ajax({
        type: "GET",
        url: 'updateview.php',
        data: "options=dummy",
        success: function(response) {
		
		 $("#newnotify").html("");

        }
    });
       

});
</script>  
<?php


		
		}
		}



		$dashboardData = new dashboardNutrition;

		$dashboardData->foodbreakdown();
		$dashboardData->fooddashboardGraph();
	
	//foodbreakdown();
	 
	 
	 
	// foodbreakdown();
	 
	 
	 
	 
	 
	 ?>
 
    <?php // include "divdata.php";?>
    <?php  include "footer.php";?>  
    </body>
</html>
