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

?><script>
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
				$result=json_decode(dailyUserRecognizedActivity($uid,$newDateStart,$newDateEnd),true);
				//print_r($result);
				$hourlyCalories[$i]=calMovementHourly($result);
				
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
													
													 printactivity($result);
										
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

 
 
 
 