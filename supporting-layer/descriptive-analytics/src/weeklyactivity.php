<?php

/*
Copyright [2016] [Shujaat Hussain]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

?><?php include_once("server.php");

include_once("functions.php");



class weeklyactivity{




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


	public function weeklyactivityJS() {	
	
		$uid=$_GET['userid'];



?>
<script>
AmCharts.makeChart("weeklychartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD",
					"categoryAxis": {
						"autoRotateCount": 0,
						"parseDates": true,
						"startOnAxis": true
					},
					"chartCursor": {},
					<!--"chartScrollbar": {},-->
					"trendLines": [
						{
							"finalValue": 7,
							"id": "TrendLine-1",
							"initialValue": 7,
							"lineAlpha": 0.43,
							"lineColor": "#CC0000",
							"lineThickness": 3
						}
					],
					"graphs": [
					
						{
							"bullet": "round",
							"id": "AmGraph-1",
							"title": "LyingDown",
							"valueField": "LyingDown"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Sitting",
							"valueField": "Sitting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Standing",
							"valueField": "Standing"
						},
						{
							"bullet": "round",
							"id": "AmGraph-4",
							"title": "Walking",
							"valueField": "Walking"
						},
						
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "Jogging",
							"valueField": "Jogging"
						},
						{
							"bullet": "round",
							"id": "AmGraph-6",
							"title": "Running",
							"valueField": "Running"
						},{
							"bullet": "round",
							"id": "AmGraph-7",
							"title": "Stretching",
							"valueField": "Stretching"
						},{
							"bullet": "round",
							"id": "AmGraph-8",
							"title": "Dancing",
							"valueField": "Dancing"
						},{
							"bullet": "round",
							"id": "AmGraph-9",
							"title": "Sweeping",
							"valueField": "Sweeping"
						},{
							"bullet": "round",
							"id": "AmGraph-10",
							"title": "Eating",
							"valueField": "Eating"
						},{
							"bullet": "round",
							"id": "AmGraph-11",
							"title": "ClimbingStairs",
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
					"balloon": {},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					"dataProvider": [
					<?php
						//include "functions.php";
						
						

						

						
						//set start time 6 days ago
						
						$starttimeobject = new DateTime('-6 days');
						//$starttimeobject = new DateTime('2015-05-16');
						$starttimeobject->setTime(00,00,00);
						
						//set end time today
						$endtimeobject = new DateTime('NOW');
						$endtimeobject->setTime(23,59,59);
						
						//convert date objects into strings
						$start = $starttimeobject->format('Y m d H:i:s');
						$end = $endtimeobject->format('Y m d H:i:s');
						
						
						
						//assign userid
						//$uid=39;
						
						//create object and get data from DCL
						$data = array(
							
									"userId"=>$uid,
									"startTime"=>$start,
									"endTime"=>$end
							
							);
						
					global $dclserver;
						$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulateByUserAndDateRange";
						$json = json_encode($data,true);
						$result =postJsonRest($url,$json);
						$result=json_decode($result,true); 
	
						//echo '<pre>';
						//print_r($result);
	
						//format date according to JSON for graph
						$s = $starttimeobject->format('Y-m-d');
						//echo '<pre>';
						//print_r($result);
						
						//calculate days activity 
						$days =$this->calculateweeklygraphdata($result,$s);
						
						
						 
					
						 //display the json in graph format
						
						 for($i=0;$i<7;$i++){
						 
						 
						 
						 		
						 		
								echo "{\"date\"".":\"".date('Y-m-d', strtotime($s. ' + '.$i.' days'))."\",";
								
								
							
							
							if(!isset($days[$i])){
							?>
                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  <?php
						   }else if(isset($days[$i]))
									echo substr($days[$i], 0, -1);
									
									
							echo '}';
							if($i<6)
										echo ',';		
						 
						 }
						 
						 echo "	]});";
						
						
?>
					
	</script>
    <?php
    
	
	
	
	}
	
	
	
/*********************************************************************************************
CaLUCLATE weekly activity
calculateweeklygraphdata($weeklyactivityjson,startdate)
**********************************************************************************************/

public function calculateweeklygraphdata($result,$s){

					$days ='';
					for($i=0;$i<count($result);$i++){
												//print substr($result[$i]['activityDate'],0,10).'/n';
											
												if($s==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[0])) {
																	   $days[0] = NULL;}
																	   if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[0].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 1 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[1])) {
																	   $days[1] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[1].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												
												}
												
												$day1flag=0;
												if(date('Y-m-d', strtotime($s. ' + 2 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[2])) {
																	   $days[2] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[2].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												if(date('Y-m-d', strtotime($s. ' + 3 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[3])) {
																	   $days[3] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[3].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 4 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[4])) {
																		$days[4] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[4].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 5 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[5])) {
																	   $days[5] = NULL;}			
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[5].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 6 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[6])) {
																	   $days[6] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[6].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												
												
											 }	
											 
											 
											 return $days;
						 
						 
						 }


	
	
 
	
	
	}
	
	
	?>
    
    
    