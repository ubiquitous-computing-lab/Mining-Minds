<?php // include "functions.php";


include_once("server.php");


global $dclserver;

$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulateByUserAndDateRange";



//set start time 365 days ago
$starttimeobject = new DateTime('-364 days');
$starttimeobject->setTime(00,00,00);

//set end time today
$endtimeobject = new DateTime('NOW');
$endtimeobject->setTime(23,59,59);

//convert date objects into strings
$start = $starttimeobject->format('Y m d H:i:s');
$end = $endtimeobject->format('Y m d H:i:s');	

$data = array(
		
					"userId"=>0,
				"startTime"=>$start,
				"endTime"=>$end 
		
		);
$json = json_encode($data,true);
$result =postJsonRest($url,$json);
//echo "<pre> hello";
$result=json_decode($result,true); 
//print_r($result);
$sittingtime=0;$standingtime=0;$walkingtime=0;$runningtime=0;
for($i=0;$i<count($result);$i++){
	
	//sitting
	if($result[$i]['activityId']==3){//sitting
	
		$sittingtime=$sittingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==8){//standing
		$standingtime=$standingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==2){//Walking
		$walkingtime=$walkingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==1){//running
		$runningtime=$runningtime+$result[$i]['duration'];
	
	} 



}

//print $sittingtime." ".$standingtime." ".$walkingtime."  ".$runningtime;

?>
<script>
var chart = AmCharts.makeChart( "chartdiv2", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {
    "activity": "Sitting",
    "Seconds": <?php echo $sittingtime;?>
  }, {
    "activity": "Standing",
    "Seconds": <?php echo $standingtime;?>
  }, {
    "activity": "Walking",
    "Seconds": <?php echo $walkingtime;?>
  }, {
    "activity": "Running",
    "Seconds": <?php echo $runningtime;?>
  } ],
  "valueField": "Seconds",
  "titleField": "activity",
   "balloon":{
   "fixedPosition":true
  },
  "export": {
    "enabled": true
  }
} );




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
							"balloonText": "[[title]]  [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Activity in Minutes",
							"type": "column",
							"valueField": "Activity"
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
						<!--"periodValueText": "Total : [[value.sum]]",-->
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
						{
							"category": "Sitting",
							"Activity": <?php echo $sittingtime/60;?>,

						},
						{
							"category": "Walking",
							"Activity": <?php echo $walkingtime/60;?>,

						},
						{
							"category": "Running",
							"Activity": <?php echo $runningtime/60;?>,

						},{
							"category": "Standing",
							"Activity": <?php echo $standingtime/60;?>,

						}
					]
				}
			);
			

</script>