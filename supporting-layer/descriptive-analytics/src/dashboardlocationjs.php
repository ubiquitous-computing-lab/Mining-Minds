<?php
include_once("functions.php");
$url="http://163.180.116.206:8080/analytics/location?userid=0";
$locationresult =getJsonRest($url);
//$locationresultjson ='{"data_values":{"Home":"166488","Office":"347339","Yard":"56316","Mall":"53304","Restaurant":"29088","Outdoors":"169081","Transport":"42888"}}';
//$locationresult =json_decode($locationresultjson,true);

//echo '<pre>';
//print_r($locationresult);

//print_r($locationresult['data_values']['Office']);
?>
<script>
AmCharts.makeChart("location_chartdiv",
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
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Location",
							"type": "column",
							"valueField": "Location"
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
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
					<?php
						
					?>
					
					
						{
							"category": "Home",
							"Location": <?php if(isset($locationresult['data_values']['Home'])) echo $locationresult['data_values']['Home']; else echo '0';?>,

						},
						

						{
							"category": "Office",
							"Location": <?php if(isset($locationresult['data_values']['Office'])) echo $locationresult['data_values']['Office']; else echo '0'; ?>,

						},
						{
							"category": "Yard",
							"Location": <?php if(isset($locationresult['data_values']['Yard'])) echo $locationresult['data_values']['Yard']; else echo '0'; ?>,

						},
						{
							"category": "Mall",
							"Location": <?php if(isset($locationresult['data_values']['Mall'])) echo $locationresult['data_values']['Mall']; else echo '0'; ?>,

						},{
							"category": "Restaurant",
							"Location": <?php if(isset($locationresult['data_values']['Restaurant'])) echo $locationresult['data_values']['Restaurant']; else echo '0'; ?>,

						},{
							"category": "Outdoors",
							"Location": <?php if(isset($locationresult['data_values']['Outdoors'])) echo $locationresult['data_values']['Outdoors']; else echo '0'; ?>,

						},{
							"category": "Transport",
							"Location": <?php if(isset($locationresult['data_values']['Transport'])) echo $locationresult['data_values']['Transport']; else echo '0'; ?>,

						}
						
						
						
					]
				}
			);
			
            </script>