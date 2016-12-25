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
							"id": "activities_feedback",
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
							"category": "Facts",
							"Negative": 10,
							"Positive": 20,
						}
					]
				}
			);