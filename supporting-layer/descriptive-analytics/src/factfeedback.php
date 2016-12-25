<?php 
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
 