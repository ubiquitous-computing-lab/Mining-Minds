<?php 




class dailyactivity{




		var $name;
		public function __construct($name) {		
			$this->name = $name;		
		}		
 
		public function set_name($new_name) {
		 	 $this->name = $new_name;
		}	
 
		public function get_name() {		
		 	 return $this->name;		
		 }		


	public function dailyactivityJS() {	
	
		$uid=$_GET['userid'];

?>
<script>
AmCharts.makeChart("dailychartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD HH",
					"categoryAxis": {
						"minPeriod": "hh",
						"parseDates": true
					},
					"chartCursor": {
						"categoryBalloonDateFormat": "JJ:NN"
					},
					<!--"chartScrollbar": {},-->
					
					"graphs": [
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "LyingDown",
							"title": "LyingDown",
							"type": "column",
							"valueField": "LyingDown"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Sitting",
							"title": "Sitting",
							"type": "column",
							"valueField": "Sitting"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Standing",
							"title": "Standing",
							"type": "column",
							"valueField": "Standing"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Walking",
							"negativeBase": -5,
							"periodSpan": -5,
							"precision": -1,
							"title": "Walking",
							"type": "column",
							"valueField": "Walking"
						},
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Jogging",
							"title": "Jogging",
							"type": "column",
							"valueField": "Jogging"
						},
						
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Running",
							"title": "Running",
							"type": "column",
							"valueField": "Running"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Stretching",
							"title": "Stretching",
							"type": "column",
							"valueField": "Stretching"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Dancing",
							"title": "Dancing",
							"type": "column",
							"valueField": "Dancing"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Sweeping",
							"title": "Sweeping",
							"type": "column",
							"valueField": "Sweeping"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Eating",
							"title": "Eating",
							"type": "column",
							"valueField": "Eating"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "ClimbingStairs",
							"title": "ClimbingStairs",
							"type": "column",
							"valueField": "ClimbingStairs"
						}
						
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "durations",
							"title": "Minutes"
						}
					],
					"allLabels": [],
					"balloon": { 
						"periodValueText": "([[value.sum]] min)",
						
					},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					
					"dataProvider": [
						
 

 



<?php

//include "functions.php";


//$starttime="2015 05 05 00:00:00";
//$endtime="2015 05 05 00:59:59";


//for static dates uncomment
$starttime =new DateTime('2016-05-13');
$endtime = new DateTime('2016-05-13');


//$starttime = new DateTime('now');
//$endtime = new DateTime('now');

//$starttime = new DateTime('now');
$starttime->setTime(9,00,00);
//$endtime = new DateTime('now');
$endtime->setTime(9,59,00);




$start = $starttime;
//$months = $start->format('h'); // '07'

$end = $endtime;
//$yCalories=0;


for($i=0;$i<13;$i++){
				
					
				$newDateStart = $starttime->format('Y m d H:i:s');
				$newDateEnd = $endtime->format('Y m d H:i:s');
				$result=json_decode($this->dailyUserRecognizedActivity($uid,$newDateStart,$newDateEnd),true);
				//print_r($result);
				$hourlyCalories[$i]=$this->calMovementHourly($result);
				
				?>
				
				
										{
											"date": "<?php echo $starttime->format('Y-m-d')." ".  $starttime->format('H'); ?>",
											<?php
											
											
											if(count($result)==0){
											?>
													"Walking": 0,
													"Running": 0,
													"Standing": 0,
													"Sitting": 0,
													"LyingDown": 0,
													"Jogging": 0,
													"Stretching": 0,
													"Dancing": 0,
													"Sweeping": 0,
													"Eating": 0,
													"ClimbingStairs": 0
											<?php
												
												
												
													
				
											
											} else {
													
													 $this->printactivity($result);
										
											}
											echo '}';
											if($i<22)
														echo ',';
											
											?>
											
											
										  
				<?php
				
				
				$start->add(new DateInterval('PT1H'));
				
				$end->add(new DateInterval('PT1H'));
				
				
								
}







//$newDateString = $dt->format('Y m d H:i:s');
//print_r($newDateString);
//
//$result=json_decode(dailyUserRecognizedActivity($uid,$starttime,$endtime),true);
//echo '<pre>';
//print_r($result);
echo "                           
					]
				}
			);";
			
?>

 </script>

 
 <?php
 
 
 
 }
 
 
 /*********************************************************************************************

Get User daily accumulated Recognized Activity by user id, start time and end time 

**********************************************************************************************/
public function dailyUserRecognizedActivity($uid,$starttime,$endtime){


global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivity";
	
//$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulate
//$starttime="2015 05 04 01:00:04.000";
//$endtime="2015 05 04 01:59:59.000";


	$data = array("userId"=>$uid,
	"startTime"=>$starttime,
	"endTime"=>$endtime,
		
		);
		
	$json = json_encode($data,true);
	
	$result =$this->postJsonRest($url,$json);
	return $result;

}
 /*********************************************************************************************

amount of activity Calculation Hourly for bar graph

**********************************************************************************************/


 
public function calMovementHourly($result){

		
			$movement=array(
								"totalAmtWalk" => 0,
								"totalAmtJogging" => 0,
								"totalAmtRunning"=> 0
								
							);	
		
			
			
			for($i=0;$i<count($result);$i++){
			
				if($result[$i]["activityDescription"]=="Running")
					$movement["totalAmtRunning"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Jogging")
					$movement["totalAmtJogging"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Walking")
					$movement["totalAmtWalk"]=$result[$i]["duration"]/60;		
			}
			
	return $movement;	

}
 
 
 /*********************************************************************************************

dailychatdiv json generation

**********************************************************************************************/

 
 function printactivity($array){
 //echo '<pre>';
 
$LyingDown=0;
$Sitting=0;
$Standing=0;
$Walking=0;
$Jogging=0;
$Running=0;
$Stretching=0;
$Dancing=0;
$Sweeping=0;
$Eating=0;
$ClimbingStairs=0;

for($c=0;$c<count($array);$c++){
 
				 if($array[$c]["activityDescription"]=="Walking"){
									
						$Walking=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Running"){
									
						$Running=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Standing"){
									
						$Standing=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Sitting"){
									
						$Sitting=$array[$c]["duration"]/60;
									
								
				} 
				
				 if($array[$c]["activityDescription"]=="Jogging"){
									
						$Jogging=$array[$c]["duration"]/60;
									
								
				}
						
						
			if($array[$c]["activityDescription"]=="LyingDown"){
									
						$LyingDown=$array[$c]["duration"]/60;
									
								
				}	
			
			if($array[$c]["activityDescription"]=="Stretching"){
									
						$Stretching=$array[$c]["duration"]/60;
									
								
				}	
			if($array[$c]["activityDescription"]=="Dancing"){
									
						$Dancing=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="Sweeping"){
							
						$Sweeping=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="Eating"){
									
						$Eating=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="ClimbingStairs"){
									
						$ClimbingStairs=$array[$c]["duration"]/60;
									
								
				}										
								
								
 }
 
 
 
 
echo "\"LyingDown\": ". abs($LyingDown).',';
echo "\"Sitting\": ". abs($Sitting).',';
echo "\"Standing\": ". abs($Standing).',';
echo "\"Walking\": ". abs($Walking).',';
echo "\"Jogging\": ". abs($Jogging).',';
echo "\"Running\": ". abs($Running).',';
echo "\"Stretching\": ". abs($Stretching).',';
echo "\"Dancing\": ". abs($Dancing).',';
echo "\"Sweeping\": ". abs($Sweeping).',';
echo "\"Eating\": ". abs($Eating).',';
echo "\"ClimbingStairs\": ". abs($ClimbingStairs);

 
 $Walking=0;
 $Running=0;
 $Standing=0;
 $Sitting=0;
 $Jogging=0;
 $LyingDown=0;
$Stretching=0;
$Dancing=0;
$Sweeping=0;
$Eating=0;
$ClimbingStairs=0;
 }

 
 
 	/*********************************************************************************************
							
							function to post json to a rest service
							
	**********************************************************************************************/
							
							
							public function postJsonRest($url,$json){
							
									$client=curl_init($url);
									
									curl_setopt($client, CURLOPT_POSTFIELDS, $json);
									curl_setopt($client, CURLOPT_RETURNTRANSFER, true);
									
									
									
									curl_setopt($client, CURLOPT_HTTPHEADER, array(
									'Content-Type: application/json',
									'Content-Length: ' . strlen($json))
								);
								
								
									curl_setopt($client, CURLOPT_TIMEOUT, 5);
									curl_setopt($client, CURLOPT_CONNECTTIMEOUT, 5);
								
									
									$response=curl_exec($client);
									//$result=json_decode($response,true);
							//		print $response;
									curl_close($client);
									return $response;
								
							}
							
							
							/*********************************************************************************************
							
							
							function to get json from a rest service
							
							
							**********************************************************************************************/
							
							public function getJsonRest($url){
							
									$client=curl_init($url);
									
								
									//for get
									curl_setopt($client,CURLOPT_RETURNTRANSFER,1);
									
										
									//response from url
									$response=curl_exec($client);
									$result=json_decode($response,true);
									curl_close($client);
									return $result;
							
							}

 
 }
 ?>
 
 