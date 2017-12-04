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




class monthlyactivity{




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


	public function monthlyactivityJS() {	
	
		$uid=$_GET['userid'];



?>
<script>
AmCharts.makeChart("monthly_activities_chartdiv",
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
							"title": "Stairs",
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
						
						$starttimeobject = new DateTime('-29 days');						//$starttimeobject = new DateTime('2015-05-16');
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
						$result =$this->postJsonRest($url,$json);
						$result=json_decode($result,true); 
	
						//echo '<pre>';
						//print_r($result);
	
						//format date according to JSON for graph
						$s = $starttimeobject->format('Y-m-d');
						//echo '<pre>';
						//print_r($result);
						
						//calculate days activity 
						$days =$this->calculatemonthlygraphdata($result,$s);
						
						
						 
					
						 //display the json in graph format
						
						 for($i=0;$i<30;$i++){
						 
						 
						 
						 		
						 		
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
CaLUCLATE monthly activity
calculatemonthlygraphdata($monthlyactivityjson,startdate)
**********************************************************************************************/

public function calculatemonthlygraphdata($result,$s){

					$days ='';
					for($i=0;$i<count($result);$i++){
												//print substr($result[$i]['activityDate'],0,10).'/n';
											
												if($s==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[0])) {
																	   $days[0] = NULL;}
															
																$days[0].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 1 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[1])) {
																	   $days[1] = NULL;}
																$days[1].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												
												if(date('Y-m-d', strtotime($s. ' + 2 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[2])) {
																	   $days[2] = NULL;}
																$days[2].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												if(date('Y-m-d', strtotime($s. ' + 3 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[3])) {
																	   $days[3] = NULL;}
																$days[3].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 4 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[4])) {
																		$days[4] = NULL;}
																$days[4].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 5 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[5])) {
																	   $days[5] = NULL;}			
																$days[5].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 6 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[6])) {
																	   $days[6] = NULL;}
																$days[6].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 7 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[7])) {
																	   $days[7] = NULL;}
																$days[7].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												
												if(date('Y-m-d', strtotime($s. ' + 8 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[8])) {
																	   $days[8] = NULL;}
																$days[8].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												if(date('Y-m-d', strtotime($s. ' + 9 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[9])) {
																	   $days[9] = NULL;}
																$days[9].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 10 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[10])) {
																		$days[10] = NULL;}
																$days[10].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 11 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[11])) {
																	   $days[11] = NULL;}			
																$days[11].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 12 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[12])) {
																	   $days[12] = NULL;}
																$days[12].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
													
												if(date('Y-m-d', strtotime($s. ' + 13 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[13])) {
																		$days[13] = NULL;}
																$days[13].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 14 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[14])) {
																	   $days[14] = NULL;}			
																$days[14].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 15 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[15])) {
																	   $days[15] = NULL;}
																$days[15].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 16 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[16])) {
																		$days[16] = NULL;}
																$days[16].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 17 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[17])) {
																	   $days[17] = NULL;}			
																$days[17].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 18 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[18])) {
																	   $days[18] = NULL;}
																$days[18].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 19 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[19])) {
																		$days[19] = NULL;}
																$days[19].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 20 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[20])) {
																	   $days[20] = NULL;}			
																$days[20].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 21 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[21])) {
																	   $days[21] = NULL;}
																$days[21].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 22 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[22])) {
																	   $days[22] = NULL;}
																$days[22].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 23 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[23])) {
																	   $days[23] = NULL;}
																$days[23].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 24 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[24])) {
																	   $days[24] = NULL;}
																$days[24].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 25 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[25])) {
																	   $days[25] = NULL;}
																$days[25].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 26 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[26])) {
																	   $days[26] = NULL;}
																$days[26].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 27 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[27])) {
																	   $days[27] = NULL;}
																$days[27].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 28 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[28])) {
																	   $days[28] = NULL;}
																$days[28].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 29 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[29])) {
																	   $days[29] = NULL;}
																$days[29].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
											 }	
											 
											 
											 return $days;
						 
						 
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
    
    
    