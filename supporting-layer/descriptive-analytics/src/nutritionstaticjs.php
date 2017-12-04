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

?>
<script>
AmCharts.makeChart("nutritionmonthly",
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
							"title": "Carbohydrate",
							"valueField": "Carbohydrate"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Protein",
							"valueField": "Protein"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Fat",
							"valueField": "Fat"
						}
						
						
						
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "durations",
							"title": "Calories"
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
					
					
					
					
										
					{
					
						"date": "2016-04-12",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-13",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-14",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-15",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-16",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-17",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-18",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-19",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-20",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-21",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-22",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-23",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-24",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-25",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-26",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-27",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-28",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-29",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-30",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-01",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-02",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-03",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-04",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-05",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-06",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-07",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-08",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-09",
						
						            "Carbohydrate": 19,
									"Protein": 162,
									"Fat": 57,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-10",
						
						            "Carbohydrate": 29,
									"Protein": 118,
									"Fat": 67,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-05-11",
						
						            "Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0,
									
                                   
                                    
									
                                    
						  }										
					]
				}
			);					
					
					
AmCharts.makeChart("nutritionweekly",
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
							"title": "Carbohydrate",
							"valueField": "Carbohydrate"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Protein",
							"valueField": "Protein"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Fat",
							"valueField": "Fat"
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
					
					
					
					
										
					{
					
									"date": "2016-05-05",
									"Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-06",
									"Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-07",
									"Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-08",
									"Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-09",
									"Carbohydrate": 19,
									"Protein": 162,
									"Fat": 57
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-10",
									"Carbohydrate": 29,
									"Protein": 118,
									"Fat": 67
									
                                   
                                    
									
                                    
						  },										
										
					{
					
									"date": "2016-05-11",
									"Carbohydrate": 0,
									"Protein": 0,
									"Fat": 0
									
                                   
                                    
									
                                    
						  }										
					]
				}
			);					
					
					


var chart = AmCharts.makeChart( "breakdownweekly", {
  "type": "serial",
  "theme": "light",
  "depth3D": 20,
  "angle": 30,
  "legend": {
    "horizontalGap": 10,
    "useGraphSettings": true,
    "markerSize": 10
  },
  "dataProvider": [ 
  
  ﻿ {"Word":"Banana","Protein":1,"Fat":0,"Carbs":29},{"Word":"Beef","Protein":175,"Fat":100,"Carbs":0},{"Word":"Chicken","Protein":66,"Fat":17,"Carbs":0},{"Word":"Rice","Protein":2,"Fat":0,"Carbs":19},{"Word":"Tuna","Protein":36,"Fat":7,"Carbs":0}	  
   ],
  "valueAxes": [ {
    "stackType": "regular",
    "axisAlpha": 0,
    "gridAlpha": 0
  } ],
  "graphs": [  {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Protein",
    "type": "column",
    "color": "#000000",
    "valueField": "Protein"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Fat",
    "type": "column",
    "color": "#000000",
    "valueField": "Fat"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Carbs",
    "type": "column",
    "color": "#000000",
    "valueField": "Carbs"
  } ],
  "categoryField": "Word",
  "categoryAxis": {
    "gridPosition": "start",
    "axisAlpha": 0,
    "gridAlpha": 0,
    "position": "left"
  },
  "export": {
    "enabled": true
  }

} );

var chart = AmCharts.makeChart( "breakdownmonthly", {
  "type": "serial",
  "theme": "light",
  "depth3D": 20,
  "angle": 30,
  "legend": {
    "horizontalGap": 10,
    "useGraphSettings": true,
    "markerSize": 10
  },
  "dataProvider": [ 
  
  {"Word":"Banana","Protein":1,"Fat":0,"Carbs":29},{"Word":"Beef","Protein":175,"Fat":100,"Carbs":0},{"Word":"Chicken","Protein":66,"Fat":17,"Carbs":0},{"Word":"Rice","Protein":2,"Fat":0,"Carbs":19},{"Word":"Tuna","Protein":36,"Fat":7,"Carbs":0}  
  	  
   ],
  "valueAxes": [ {
    "stackType": "regular",
    "axisAlpha": 0,
    "gridAlpha": 0
  } ],
  "graphs": [  {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Protein",
    "type": "column",
    "color": "#000000",
    "valueField": "Protein"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Fat",
    "type": "column",
    "color": "#000000",
    "valueField": "Fat"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Carbs",
    "type": "column",
    "color": "#000000",
    "valueField": "Carbs"
  } ],
  "categoryField": "Word",
  "categoryAxis": {
    "gridPosition": "start",
    "axisAlpha": 0,
    "gridAlpha": 0,
    "position": "left"
  },
  "export": {
    "enabled": true
  }

} );

</script>