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

class emotion{




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


	public function emotionJS() {	



$emotiondata=array();$week=6;
for($i=0;$i<7;$i++){

	$starttimeobject = new DateTime('-'.$week.' days');
	//print $starttimeobject->format('Y m d');
	$emotiondata[$i]["date"]=$starttimeobject->format('Y-m-d');
	$emotiondata[$i]["Sadness"]=0;
	$emotiondata[$i]["Happiness"]=0;
	$emotiondata[$i]["Anger"]=0;
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
						$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedEmotionByUserIDDateRange";
						
						
						
								
								
						$json = json_encode($data,true);
									
						$result =postJsonRest($url,$json);		
						$result=json_decode($result,true);	
					

for($i=0;$i<count($result);$i++){

	
	$key = array_search(substr($result[$i]['startTime'],0,10), array_column($emotiondata, 'date'));
	
	if($result[$i]['emotionLabel']=="Sadness"){
	
		
		$emotiondata[$key]["Sadness"]=$emotiondata[$key]["Sadness"]+$result[$i]['duration'];
	
	}	
	
	if($result[$i]['emotionLabel']=="Happiness"){
		$emotiondata[$key]["Happiness"]=$emotiondata[$key]["Happiness"]+$result[$i]['duration'];
		
	
	}	
	
	if($result[$i]['emotionLabel']=="Anger"){
		$emotiondata[$key]["Anger"]=$emotiondata[$key]["Anger"]+$result[$i]['duration'];
	
	}	
	
}
			
			


?>
<script>
AmCharts.makeChart("emotionchartdiv",
				{
					"type": "serial",
					<!--"pathToImages": "http://cdn.amcharts.com/lib/3/images/",-->
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
							"title": "Anger",
							"valueField": "Anger"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Happiness",
							"valueField": "Happiness"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Sadness",
							"valueField": "Sadness"
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
										
					<?php for($i=0;$i<count($emotiondata);$i++){?>
					
					{
					
						"date": "<?php echo $emotiondata[$i]["date"];?>",
						
						             "Sadness": <?php echo $emotiondata[$i]["Sadness"]/60;?>,
									"Happiness": <?php echo $emotiondata[$i]["Happiness"]/60;?>,
									"Anger": <?php echo $emotiondata[$i]["Anger"]/60;?>,
									
                                   
                                    
									
                                    
						  },										
					<?php }?>
					
											
					
					
									                           
					]
				}
			);					
					
					


</script>



 
 
 
 