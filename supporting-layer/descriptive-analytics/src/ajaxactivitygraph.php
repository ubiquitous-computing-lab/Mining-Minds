<?php

$querytype=$_GET['querytype'];
$value=$_GET['options'];
$graph=$_GET['graph'];


$options=explode("|",$value);




//sit 5
//walk 6
//stand 7
//run 8
if($graph=="Line"){
if (in_array(5, $options)||in_array(6, $options)||in_array(7, $options) || in_array(8, $options)){

?>
<div id="chartdiv"></div>
<script>

 AmCharts.makeChart("chartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "category",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Negative",
							"type": "column",
							"valueField": "Negative"
						},
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,
							"title": "Positive",
							"type": "column",
							"valueField": "Positive"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "rec_feedback",
							"stackType": "regular",
							"labelRotation":"3",
							"title": ""
							
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
						
						{
							
							<?php if (in_array(7, $options)) {?>
							
											
							"category": "Walking",
							"Negative": 1,
							"Positive": 6						},<?php } if (in_array(8, $options)) {?>{
							"category": "Running",
							"Negative": 4,
							"Positive": 7						},<?php } if (in_array(6, $options)) {?>{
							"category": "Standing",
							"Negative": 2,
							"Positive": 4						},<?php } if (in_array(5, $options)) {?>{
							"category": "Sitting",
							"Negative": 0,
							"Positive": 2						}<?php } ?>
							
					]
				}
			);
 
 
  
 
 
 </script>
  


<?php
}


}

//activity


if (in_array(1, $options)||in_array(2, $options)||in_array(3, $options) || in_array(4, $options)){


					$sitting=501.9;
					$standing=301.9;
					$walking= 201.1;
					$running= 165.8;
					
					
					if (in_array(1, $options)&&in_array(2, $options)){
								$ratiositstand=round($sitting/($sitting+$standing)*100,2);
					
								echo "<strong>People spend ".$ratiositstand."% more time sitting than standing.</strong></br></br></br>";
						}
						
					if (in_array(3, $options)&&in_array(4, $options)){
								$ratiorunwalk=round($walking/($running+$walking)*100,1);
					
								echo "<strong>People spend ".$ratiorunwalk."% more time walking than running.</strong></br>";
						}	
			}






if($graph=="Pie"){
if (in_array(1, $options)||in_array(2, $options)||in_array(3, $options) || in_array(4, $options)){

?><div id="chartdiv"></div>



 
<script>
var chart = AmCharts.makeChart( "chartdiv", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ 
  
  <?php if (in_array(1, $options)) {
  
  
  
  ?>{
    "activity": "Sitting",
    "Minutes": 501.9
  },<?php } 
  	 if (in_array(2, $options)) {?>
  {
    "activity": "Standing",
    "Minutes": 301.9
  }, <?php }    if (in_array(3, $options)) {?>
{
    "activity": "Walking",
    "Minutes": 201.1
  }, <?php }    if (in_array(4, $options)) {?>{
    "activity": "Running",
    "Minutes": 165.8
  } <?php } ?>],
  "valueField": "Minutes",
  "titleField": "activity",
   "balloon":{
   "fixedPosition":true
  },
  "export": {
    "enabled": true
  }
} );
	
	
</script>      

       <?php }
	   }
	   else if($graph=="Line")
	   
	   { 
	   		if (in_array(1, $options)||in_array(2, $options)||in_array(3, $options) || in_array(4, $options)){
	   
	    ?>

 <div id="activity_line_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>

 <script>
AmCharts.makeChart("activity_line_chartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "category",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Activity",
							"type": "column",
							"valueField": "Minutes"
						}
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "activities_feedback",
							"stackType": "regular",
							"title": ""
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					
					"dataProvider": [
					<?php if (in_array(3, $options)) {
  
  
  
  ?>{
						
							"category": "Walking",
							"Minutes": 201.1,

						},<?php
						
						}
						?>
						
						
						<?php if (in_array(4, $options)) {
  
  
  
  ?>
						{
							"category": "Running",
							"Minutes": 165.8,

						},<?php
						
						}
						?>
						
						<?php if (in_array(1, $options)) {
  
  
  
  ?>
						{
							"category": "Sitting",
							"Minutes": 501.9,

						},<?php } 
						 if (in_array(2, $options)) {?>
					  {
						"category": "Standing",
							"Minutes": 301.9,
					  }
											<?php
						
						}
						?>
					]
				}
			);
					</script>
 <?php
 }
 }//line graph
 ?>
 

 
 
 
 
      

 <?php
//Location
//home 13
//gym 14
//office 15
if (in_array(13, $options)||in_array(14, $options)||in_array(15, $options)){

?> <div id="feedback_location_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>



 
<script>
AmCharts.makeChart("feedback_location_chartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "category",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Location",
							"type": "column",
							"valueField": "Location"
						}
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "activities_feedback",
							"stackType": "regular",
							"title": ""
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					
					"dataProvider": [
					
					<?php if (in_array(13, $options)) {
  
  
  
  ?>{
						
							"category": "Home",
							"Location": 8,

						},<?php
						
						}
						?>
						
						
						<?php if (in_array(14, $options)) {
  
  
  
  ?>
						{
							"category": "Gym",
							"Location": 6,

						},<?php
						
						}
						?>
						
						<?php if (in_array(15, $options)) {
  
  
  
  ?>
						{
							"category": "Office",
							"Location": 2,

						}
						<?php
						
						}
						?>
					]
				}
			);
			

       <?php }?>







