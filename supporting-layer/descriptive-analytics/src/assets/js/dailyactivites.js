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
					"chartScrollbar": {},
					
					"graphs": [
						{
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
							"id": "Running",
							"title": "Running",
							"type": "column",
							"valueField": "Running"
						},
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Standing",
							"title": "Standing",
							"type": "column",
							"valueField": "Standing"
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
						"periodValueText": "Total: [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
						{
							"date": "2014-03-01 08",
							"Walking": 8,
							"Running": 5,
							"Standing": 2
						},
						{
							"date": "2014-03-01 09",
							"Walking": 6,
							"Running": 7,
							"Standing": 3
						},
						{
							"date": "2014-03-01 10",
							"Walking": 2,
							"Running": 3,
							"Standing": 1
						},
						{
							"date": "2014-03-01 11",
							"Walking": 1,
							"Running": 3,
							"Standing": 2
						},
						{
							"date": "2014-03-01 12",
							"Walking": 2,
							"Running": 1,
							"Standing": 1
						},
						{
							"date": "2014-03-01 13",
							"Walking": 3,
							"Running": 2,
							"Standing": 9
						},
						{
							"date": "2014-03-01 14",
							"Walking": 6,
							"Running": 8,
							"Standing": 1
						}
					]
				}
			);