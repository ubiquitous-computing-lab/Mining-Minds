				   
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
					"chartScrollbar": {},
					"trendLines": [
						{
							"finalDate": "2014-03-20",
							"finalValue": 7,
							"id": "TrendLine-1",
							"initialDate": "2014-03-01",
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
							"title": "Walking",
							"valueField": "Walking"
						},
						{
							"bullet": "round",
							"columnWidth": 0,
							"fillAlphas": 0.27,
							"id": "AmGraph-2",
							"title": "Running",
							"valueField": "Running"
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
							"title": "Sitting",
							"valueField": "Sitting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "Jogging",
							"valueField": "Jogging"
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
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					"dataProvider": [
										
					
					
					{
					
						"date": "2015-05-05",
						
						"Walking": 0,"Running": 0,"Standing": 240,"Sitting": 90.05,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-06",
						
						"Walking": 0,"Running": 0,"Standing": 0,"Sitting": 360,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-07",
						
						"Walking": 0,"Running": 0,"Standing": 0,"Sitting": 360,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-08",
						
						"Walking": 0,"Running": 0,"Standing": 0,"Sitting": 79.9,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-09",
						
						"Walking": 0,"Running": 0,"Standing": 0,"Sitting": 0.05,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-10",
						
						"Walking": 0,"Running": 0,"Standing": 0,"Sitting": 0.05,"Jogging": 0},										
					
					
					{
					
						"date": "2015-05-11",
						
						                                    "Walking": 0,
                                    "Running": 0,
                                    "Standing": 0,
                                    "Sitting": 0,
                                    "Jogging": 0
						  },					                           
					]
				}
			);					
					
					
