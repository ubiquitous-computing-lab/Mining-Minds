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



class weeklylocation{




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


	public function weeklylocationJS() {	
	
	
	
					
				$uid=$_GET['userid'];
				$locationdata=$this->calculateweeklylocation(6,$uid);			

			
/*echo "<pre> hello";
//
	print_r($locationdata);*/


?>
<script>
AmCharts.makeChart("locationchartdiv",
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
							"id": "AmGraph-11",
							"title": "Home",
							"valueField": "Home"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-12",
							"title": "Office",
							"valueField": "Office"
						},
						{
							"bullet": "round",
							"id": "AmGraph-13",
							"title": "Yard",
							"valueField": "Yard"
						},{
							"bullet": "round",
							"id": "AmGraph-14",
							"title": "Gym",
							"valueField": "Gym"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-15",
							"title": "Mall",
							"valueField": "Mall"
						},
						{
							"bullet": "round",
							"id": "AmGraph-16",
							"title": "Restaurant",
							"valueField": "Restaurant"
						},{
							"bullet": "round",
							"id": "AmGraph-17",
							"title": "Outdoors",
							"valueField": "Outdoors"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-18",
							"title": "Transport",
							"valueField": "Transport"
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
										
					<?php for($i=0;$i<count($locationdata);$i++){?>
					
					{
					


						"date": "<?php echo $locationdata[$i]["date"];?>",
						
						            "Home": <?php echo $locationdata[$i]["Home"]/60;?>,
									"Office": <?php echo $locationdata[$i]["Office"]/60;?>,
									"Yard": <?php echo $locationdata[$i]["Yard"]/60;?>,
									"Gym": <?php echo $locationdata[$i]["Gym"]/60;?>,
									"Mall": <?php echo $locationdata[$i]["Mall"]/60;?>,
									"Restaurant": <?php echo $locationdata[$i]["Restaurant"]/60;?>,
									"Outdoors": <?php echo $locationdata[$i]["Outdoors"]/60;?>,
									"Transport": <?php echo $locationdata[$i]["Transport"]/60;?>,
									
									
                                   
                                    
									
                                    
						  },										
					<?php }?>
					
											
					
					
									                           
					]
				}
			);					
					
					





</script>




 <?php
 
 
 }
 
 
/*********************************************************************************************
CaLUCLATE weekly location
calculateweeklygraphdata($weeklyactivityjson,startdate)
**********************************************************************************************/


public function calculateweeklylocation($days,$uid){



	
			$locationdata=array();//$week=6;
			$week=$days;	
			for($i=0;$i<=$week;$i++){
			
				$starttimeobject = new DateTime('-'.$days.' days');
				
				//print $starttimeobject->format('Y m d');
				$locationdata[$i]["date"]=$starttimeobject->format('Y-m-d');
				$locationdata[$i]["Home"]=0;
				$locationdata[$i]["Office"]=0;
				$locationdata[$i]["Yard"]=0;
				$locationdata[$i]["Gym"]=0;
				$locationdata[$i]["Mall"]=0;
				$locationdata[$i]["Restaurant"]=0;
				$locationdata[$i]["Outdoors"]=0;
				$locationdata[$i]["Transport"]=0;
				$days--;
			
			}
			
		
			$starttimeobject = new DateTime('-'.$week.' days');	
			//$starttimeobject = new DateTime('2015-05-16');
			$starttimeobject->setTime(00,00,00);
			
			//set end time today
			$endtimeobject = new DateTime('NOW');
			$endtimeobject->setTime(23,59,59);
			
			//convert date objects into strings
			$start = $starttimeobject->format('Y m d H:i:s');
			$end = $endtimeobject->format('Y m d H:i:s');
			global $dclserver;
			
			$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserDetectedLocation";
			
			
			$data = array(
							"userId"=>$uid,
							"startTime"=>$start,
							"endTime"=>$end 
						);
						
						
				
				
						
			$json = json_encode($data,true);
			$result =$this->postJsonRest($url,$json);
			
			$result=json_decode($result,true); 
			//print_r($data);exit;
			for($i=0;$i<count($result);$i++){
			
				$key = array_search(substr($result[$i]['startTime'],0,10), array_column($locationdata, 'date'));
				if(isset($result[$i]['locationLabel'])){
					if($result[$i]['locationLabel']=="Home"){
					
							$locationdata[$key]["Home"]=$locationdata[$key]["Home"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Office"){
					
							$locationdata[$key]["Office"]=$locationdata[$key]["Office"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Yard"){
					
							$locationdata[$key]["Yard"]=$locationdata[$key]["Yard"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Gym"){
					
							$locationdata[$key]["Gym"]=$locationdata[$key]["Gym"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Mall"){
					
							$locationdata[$key]["Mall"]=$locationdata[$key]["Mall"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Restuarant"){
					
							$locationdata[$key]["Restuarant"]=$locationdata[$key]["Restuarant"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Outdoors"){
					
							$locationdata[$key]["Outdoors"]=$locationdata[$key]["Outdoors"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Transport"){
					
							$locationdata[$key]["Transport"]=$locationdata[$key]["Transport"]+$result[$i]['duration'];
					}	
					
				}	
					
			}
			
		
		return $locationdata;	

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
 
 
 