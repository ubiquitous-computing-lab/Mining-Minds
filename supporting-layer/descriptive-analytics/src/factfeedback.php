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
//include "functions.php";
$sentiment=getFactFeedbackbyUserID($uid);

?>


<script>
 
 AmCharts.makeChart("feedback_facts_chartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "category",
					"rotate": true,
					"startDuration": 1,
					"addClassNames": true,
					"classNamePrefix": "feedbackfact",
					"categoryAxis": {
						"gridPosition": "start"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,
							"id": "AmGraph-1",
							"title": "Negative",
							"type": "column",
							"valueField": "Negative"
						},
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,
							"id": "AmGraph-2",
							"title": "Positive",
							"type": "column",
							"valueField": "Positive"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "facts_feedback",
							"stackType": "regular",
							"title": ""
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"useGraphSettings": true
					},
					
					"dataProvider": [
						{
							"category": "LyingDown",
							"Negative": <?php echo 	$sentiment["LyingDownnegative"];?>,
							"Positive": <?php echo 	$sentiment["LyingDownpositive"];?>
						},{
							"category": "Sitting",
							"Negative": <?php echo 	$sentiment["sittingnegative"];?>,
							"Positive": <?php echo 	$sentiment["sittingpositive"];?>
						},
						{
							"category": "Standing",
							"Negative": <?php echo 	$sentiment["standingnegative"];?>,
							"Positive": <?php echo $sentiment["standingpositive"];	?>
						},
						{
							"category": "Walking",
							"Negative": <?php echo 	$sentiment["walkingnegative"];?>,
							"Positive": <?php echo 	$sentiment["walkingpositive"];?>
						},
						{
							"category": "Jogging",
							"Negative": <?php echo 	$sentiment["joggingnegative"];?>,
							"Positive": <?php echo 	$sentiment["joggingpositive"];?>
						},
						{
							"category": "Running",
							"Negative": <?php echo 	$sentiment["runningnegative"];?>,
							"Positive": <?php echo 	$sentiment["runningpositive"];?>
						}
						
						
					]
				}
			);
 
 
  
 
 
 </script>
 