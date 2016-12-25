<?php
include_once("server.php");

include "functions.php";
//print_r($_GET);
$querytype=$_GET['querytype'];
$value=$_GET['options'];
$graph=$_GET['graph'];
$start=$_GET['startdate'];
$end=$_GET['enddate'];

$start=explode("/",$start);

	
$end=explode("/",$end);
$startdate=$start[2]." ".$start[0]." ".$start[1]." 00:00:00";
$enddate=$end[2]." ".$end[0]." ".$end[1]." 00:00:00";


$options=explode(",",$value);



//sit 5
//walk 6
//stand 7
//run 8


//activity





//activity graph
if($querytype=="activity"){

dynamicactivitygraph($graph,$startdate,$enddate,$options,$value);
		

}

//location graph

if($querytype=="location"){

dynamiclocationgraph($graph,$startdate,$enddate,$options,$value);
		

}

//activity graph
if($querytype=="rec"){

dynamicrecgraph($graph,$startdate,$enddate,$options,$value);
		

}


//nutrition graph
if($querytype=="nutrition"){

dynamicnutritiongraph($graph,$startdate,$enddate,$options,$value);
		

}


 
 function dynamicnutritiongraph($graph,$startdate,$enddate,$options,$value){



$Protein=0;$Carbohydrates=0;$Fat=0;


$data = array(
							
									"userId"=>0,
									"startTime"=>$startdate,
									"endTime"=>$enddate
							
							);




						global $dclserver;
						$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetFoodLogByUserIDDateRange";
						
						
								
								
						$json = json_encode($data,true);
									
						$result =postJsonRest($url,$json);		
						$result=json_decode($result,true);	
						
						for($i=0;$i<count($result);$i++){
						
							$Protein=$Protein+$result[$i]['totalProtein'];
							$Carbohydrates=$Carbohydrates+$result[$i]['totalCarbohydrate'];						
							$Fat=$Fat+$result[$i]['totalFat'];						
						}
						//echo '<pre>';
						//print_r($result);

//$Protein=5;$Carbohydrates=10;$Fat=20;

if (in_array("Carbohydrates", $options)||in_array("Fat", $options)||in_array("Protein", $options)){

?>

 <div id="nutrition_line_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>

<script>
AmCharts.makeChart("nutrition_line_chartdiv",
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
							"title": "Calories",
							"type": "column",
							"valueField": "Calories"
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
											
						<?php if (in_array("Carbohydrates", $options)) {
  
  
  
  ?>{
						
							"category": "Carbohydrates",
							"Calories": <?php echo $Carbohydrates;?>,

						},<?php
						
						}
						?>
						
						
						<?php if (in_array("Fat", $options)) {
  
  
  
  ?>
						{
							"category": "Fat",
							"Calories": <?php echo $Fat;?>,

						},<?php
						
						}
						?>
						
						<?php if (in_array("Protein", $options)) {
  
  
  
  ?>
						{
							"category": "Protein",
							"Calories": <?php echo $Protein;?>,

						}
						<?php
						
						}
						?>
					]
				}
			);
			

       <?php }
	   
	   
	   }
	   
 function dynamicrecgraph($graph,$start,$end,$options,$value){


global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/RetrieveRecommendationByDateRangeActivityIDs";

$data = array(
				"situationCategoryIDs"=>$value,
				"startDate"=>$start,
				"endDate"=>$end 
			);
$json = json_encode($data,true);
$result =postJsonRest($url,$json);

$result=json_decode($result,true); 


//echo "<pre> hello";print_r($data);
$standing=0;$LyingDown=0;$sitting=0;

for($i=0;$i<count($result);$i++){


//echo "<pre> office";print_r($options);
	if (in_array(3, $options) && $result[$i]['situationCategoryDescription']=="Sitting" ){
	
			$sitting++;
	
	}
	if (in_array(7, $options )&& $result[$i]['situationCategoryDescription']=="LyingDown" ){
	
			$LyingDown++;
	
	}
	if (in_array(8, $options) && $result[$i]['situationCategoryDescription']=="Standing" ){
	
			
			$standing++;
	
	}

		


}



if (in_array(3, $options)||in_array(7, $options)||in_array(8, $options)){

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
							"title": "Recommendation",
							"type": "column",
							"valueField": "Recommendation"
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
						
							"category": "Sitting",
							"Recommendation": <?php echo $sitting;?>

						},<?php
						
						}
						?>
						
						
						<?php if (in_array(7, $options)) {
  
  
  
  ?>
						{
							"category": "LyingDown",
							"Recommendation": <?php echo $LyingDown;?>

						},<?php
						
						}
						?>
						
						<?php if (in_array(8, $options)) {
  
  
  
  ?>
						{
							"category": "Standing",
							"Recommendation": <?php echo $standing;?>

						}
						<?php
						
						}
						?>
					]
				}
			);
			

       <?php }
	   
	   
	   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
function dynamiclocationgraph($graph,$start,$end,$options,$value){
//Location
//home 13
//gym 14
//office 15
global $dclserver;

$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserDetectedLocation";

$data = array(
				"userId"=>0,
				"startTime"=>$start,
				"endTime"=>$end 
			);
$json = json_encode($data,true);
$result =postJsonRest($url,$json);

$result=json_decode($result,true); 


//echo "<pre> hello";print_r($result);
$office=0;$home=0;$gym=0;

for($i=0;$i<count($result);$i++){


//echo "<pre> office";print_r($options);
	if(isset( $result[$i]['locationLabel'])){
				if (in_array("Office", $options) && $result[$i]['locationLabel']=="Office" ){
				
						$office++;
				
				}
				if (in_array("Home", $options )&& $result[$i]['locationLabel']=="Home" ){
				
						$home++;
				
				}
				if (in_array("Gym", $options) && $result[$i]['locationLabel']=="Gym" ){
				
						
						$gym++;
				
				}

		
		}

}



if (in_array("Gym", $options)||in_array("Home", $options)||in_array("Office", $options)){

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
					
					<?php if (in_array("Home", $options)) {
  
  
  
  ?>{
						
							"category": "Home",
							"Location": <?php echo $home;?>,

						},<?php
						
						}
						?>
						
						
						<?php if (in_array("Gym", $options)) {
  
  
  
  ?>
						{
							"category": "Gym",
							"Location": <?php echo $gym;?>,

						},<?php
						
						}
						?>
						
						<?php if (in_array("Office", $options)) {
  
  
  
  ?>
						{
							"category": "Office",
							"Location": <?php echo $office;?>,

						}
						<?php
						
						}
						?>
					]
				}
			);
			

       <?php }
	   
	   
	   }
	   ?>





<?php




//$graph is graph type (Line,Pie)
//$start,$end, is start date and end date
//$options,$value is number of activities in an array and also comma based



function dynamicactivitygraph($graph,$start,$end,$options,$value){


//sit 3
//walk 2
//stand 8
//run 1

//activity graph
//$start="2014 05 09 00:00:00";
//$end="2015 07 29 00:00:00";
global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulateByActivitiesAndDateRange";

$data = array(
				"activityIDs"=>$value,
				"startTime"=>$start,
				"endTime"=>$end 
			);
$json = json_encode($data,true);
$result =postJsonRest($url,$json);

$result=json_decode($result,true); 


//echo "<pre> hello";print_r($result);
$runningminutes=0;$walkingminutes=0;$sittingminutes=0;$standingminutes=0;

for($i=0;$i<count($result);$i++){



	if (in_array(1, $options) && $result[$i]['activityId']==1 ){
	
			$runningminutes=$runningminutes+$result[$i]['duration'];
	
	}
	if (in_array(2, $options )&& $result[$i]['activityId']==2 ){
	
			$walkingminutes=$walkingminutes+$result[$i]['duration'];
	
	}
	if (in_array(3, $options) && $result[$i]['activityId']==3 ){
	
			
			$sittingminutes=$sittingminutes+$result[$i]['duration'];
	
	}

	if (in_array(8, $options) && $result[$i]['activityId']==8 ){
	
			$standingminutes=$standingminutes+$result[$i]['duration'];
	
	}
	


}

$runningminutes=$runningminutes/60;$walkingminutes=$walkingminutes/60;$sittingminutes=$sittingminutes/60;$standingminutes=$standingminutes/60;





if (in_array(3, $options)||in_array(8, $options)||in_array(2, $options) || in_array(1, $options)){


					$sitting=501.9;
					$standing=301.9;
					$walking= 201.1;
					$running= 165.8;
					
					
					if (in_array(3, $options)&&in_array(8, $options) && $sittingminutes>0 && $standingminutes>0){
								$ratiositstand=round($sittingminutes/($sittingminutes+$standingminutes)*100,2);
					
								echo "<strong>People spend ".$ratiositstand."% more time sitting than standing.</strong></br></br></br>";
						}
						
					if (in_array(2, $options)&&in_array(1, $options)&& $runningminutes>0 && $walkingminutes>0 ){
								$ratiorunwalk=round($walkingminutes/($runningminutes+$walkingminutes)*100,1);
					
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
  
  <?php if (in_array(3, $options)) {
  
  
  
  ?>{
    "activity": "Sitting",
    "Minutes": <?php echo $sittingminutes;?>
  },<?php } 
  	 if (in_array(8, $options)) {?>
  {
    "activity": "Standing",
    "Minutes": <?php echo $standingminutes;?>
  }, <?php }    if (in_array(2, $options)) {?>
{
    "activity": "Walking",
    "Minutes": <?php echo $walkingminutes;?>
  }, <?php }    if (in_array(1, $options)) {?>{
    "activity": "Running",
    "Minutes":<?php echo $runningminutes;?>
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
					<?php if (in_array(2, $options)) {
  
  
  
  ?>{
						
							"category": "Walking",
							"Minutes": <?php echo $walkingminutes;?>,

						},<?php
						
						}
						?>
						
						
						<?php if (in_array(1, $options)) {
  
  
  
  ?>
						{
							"category": "Running",
							"Minutes": <?php echo $runningminutes;?>,

						},<?php
						
						}
						?>
						
						<?php if (in_array(3, $options)) {
  
  
  
  ?>
						{
							"category": "Sitting",
							"Minutes": <?php echo $sittingminutes;?>,

						},<?php } 
						 if (in_array(8, $options)) {?>
					  {
						"category": "Standing",
							"Minutes": <?php echo $standingminutes;?>,
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
 
 




}


?>