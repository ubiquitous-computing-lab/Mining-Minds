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
AmCharts.makeChart("nutrition",
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
							"title": "Fat",
							"valueField": "Fat"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Cholesterol",
							"valueField": "Cholesterol"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Sugar",
							"valueField": "Sugar"
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
					
						"date": "2016-03-26",
						
						             "Fat": 10,
									"Cholesterol": 15,
									"Sugar": 5,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-27",
						
						             "Fat": 0,
									"Cholesterol": 20,
									"Sugar": 4,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-28",
						
						             "Fat": 4,
									"Cholesterol": 7,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-29",
						
						             "Fat": 7,
									"Cholesterol": 3,
									"Sugar":7,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-30",
						
						             "Fat": 8,
									"Cholesterol": 15,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-31",
						
						             "Fat": 0,
									"Cholesterol": 0,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-01",
						
						             "Fat": 0,
									"Cholesterol": 0,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
											
					
					
									                           
					]
				}
			);					
					
					


var chart = AmCharts.makeChart( "chartSNS", {
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
  
  ﻿{"Word":"Shrimp","Protein":3758,"Fat":2315,"Carbs":19},	
	{"Word":"Beef","Protein":1581,"Fat":1831,"Carbs":22},	
	{"Word":"Chicken","Protein":11680,"Fat":2460,"Carbs":266},	
	{"Word":"Pork","Protein":181,"Fat":63,"Carbs":0},	
	{"Word":"Tuna","Protein":1591,"Fat":306,"Carbs":118}	
	  
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
