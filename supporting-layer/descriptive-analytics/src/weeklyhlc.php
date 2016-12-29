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

?><?php
include_once("functions.php");

class weeklyhlc{




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


	public function weeklyhlcJS() {	
	
	
	
					
				$uid=$_GET['userid'];
				$emotiondata=array();$week=6;
				for($i=0;$i<7;$i++){
				
					$starttimeobject = new DateTime('-'.$week.' days');
					//print $starttimeobject->format('Y m d');
					$hlcdata[$i]["date"]=$starttimeobject->format('Y-m-d');
					$hlcdata[$i]["Amusement"]=0;
					$hlcdata[$i]["Commuting"]=0;
					$hlcdata[$i]["Exercising"]=0;
					$hlcdata[$i]["Gardening"]=0;
					$hlcdata[$i]["HavingMeal"]=0;
					$hlcdata[$i]["HouseWork"]=0;
					$hlcdata[$i]["Inactivity"]=0;
					$hlcdata[$i]["OfficeWork"]=0;
					$hlcdata[$i]["Sleeping"]=0;
					$week--;
				}
				
				
				
				

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
						//$uid=35;
						
						//create object and get data from DCL
						$data = array(
							
									"userId"=>$uid,
									"startTime"=>$start,
									"endTime"=>$end
							
							);
							global $dclserver;

						$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedHLCByUserIDDateRange";
						$json = json_encode($data,true);
						$result =$this->postJsonRest($url,$json);		
						$result=json_decode($result,true);	
							

						for($i=0;$i<count($result);$i++){
						
							
							$key = array_search(substr($result[$i]['startTime'],0,10), array_column($hlcdata, 'date'));
							
							if($result[$i]['hLCLabel']=="Amusement"){
							
								
								$hlcdata[$key]["Amusement"]=$hlcdata[$key]["Amusement"]+$result[$i]['duration'];
							
							}	
							
							if($result[$i]['hLCLabel']=="Exercising"){
								$hlcdata[$key]["Exercising"]=$hlcdata[$key]["Exercising"]+$result[$i]['duration'];
							
							}	
							if($result[$i]['hLCLabel']=="Commuting"){
								$hlcdata[$key]["Commuting"]=$hlcdata[$key]["Commuting"]+$result[$i]['duration'];
							
							}	
							if($result[$i]['hLCLabel']=="Gardening"){
								$hlcdata[$key]["Gardening"]=$hlcdata[$key]["Gardening"]+$result[$i]['duration'];
							
							}	
							
							if($result[$i]['hLCLabel']=="HavingMeal"){
							
								
								$hlcdata[$key]["HavingMeal"]=$hlcdata[$key]["HavingMeal"]+$result[$i]['duration'];
							
							}	
							
							if($result[$i]['hLCLabel']=="HouseWork"){
								$hlcdata[$key]["HouseWork"]=$hlcdata[$key]["HouseWork"]+$result[$i]['duration'];
							
							}	
							
							if($result[$i]['hLCLabel']=="Inactivity"){
								$hlcdata[$key]["Inactivity"]=$hlcdata[$key]["Inactivity"]+$result[$i]['duration'];
							
							}	
							if($result[$i]['hLCLabel']=="OfficeWork"){
							
								
								$hlcdata[$key]["OfficeWork"]=$hlcdata[$key]["OfficeWork"]+$result[$i]['duration'];
							
							}	
							
							if($result[$i]['hLCLabel']=="Sleeping"){
								$hlcdata[$key]["Sleeping"]=$hlcdata[$key]["Sleeping"]+$result[$i]['duration'];
							
							}	
							
							
							}	

?>
<script>
AmCharts.makeChart("hlcchartdiv",
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
							"title": "Amusement",
							"valueField": "Amusement"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Commuting",
							"valueField": "Commuting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Exercising",
							"valueField": "Exercising"
						},
						
						{
							"bullet": "round",
							"id": "AmGraph-4",
							"title": "Gardening",
							"valueField": "Gardening"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "HavingMeal",
							"valueField": "HavingMeal"
						},
						{
							"bullet": "round",
							"id": "AmGraph-6",
							"title": "HouseWork",
							"valueField": "HouseWork"
						},
						/*{
							"bullet": "round",
							"id": "AmGraph-7",
							"title": "Inactivity",
							"valueField": "Inactivity"
						},	*/
						
						{
							"bullet": "round",
							"id": "AmGraph-8",
							"title": "OfficeWork",
							"valueField": "OfficeWork"
						},
						{
							"bullet": "round",
							"id": "AmGraph-9",
							"title": "Sleeping",
							"valueField": "Sleeping"
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
										
					<?php for($i=0;$i<count($hlcdata);$i++){?>
					
					{
					
						"date": "<?php echo $hlcdata[$i]["date"];?>",
						
						             "Amusement": <?php echo $hlcdata[$i]["Amusement"]/60;?>,
									"Commuting": <?php echo $hlcdata[$i]["Commuting"]/60;?>,
									"Exercising": <?php echo $hlcdata[$i]["Exercising"]/60;?>,
									 "Gardening": <?php echo $hlcdata[$i]["Gardening"]/60;?>,
									"HavingMeal": <?php echo $hlcdata[$i]["HavingMeal"]/60;?>,
									"HouseWork": <?php echo $hlcdata[$i]["HouseWork"]/60;?>,
									 <!--"Inactivity": <?php //echo $hlcdata[$i]["Inactivity"]/60;?>,-->
									"OfficeWork": <?php echo $hlcdata[$i]["OfficeWork"]/60;?>,
									"Sleeping": <?php echo $hlcdata[$i]["Sleeping"]/60;?>
                     
                                    
									
                                    
						  },										
					<?php }?>
					
											
					
					
									                           
					]
				}
			);					
					
					


</script>


<?php


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