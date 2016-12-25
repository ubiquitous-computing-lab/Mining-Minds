AmCharts.makeChart("weeklychartdiv",
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
							"id": "AmGraph-2",
							"title": "Running",
							"valueField": "Running"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Standing",
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
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					"dataProvider": [
						{
							"date": "2014-03-01",
							"Walking": 8,
							"Running": 5,
							"Standing": 4
						},
						{
							"date": "2014-03-02",
							"Walking": 6,
							"Running": 7,
							"Standing": 5
						},
						{
							"date": "2014-03-03",
							"Walking": 2,
							"Running": 3,
							"Standing": 1
						},
						{
							"date": "2014-03-04",
							"Walking": 1,
							"Running": 3,
							"Standing": 3
						},
						{
							"date": "2014-03-05",
							"Walking": 2,
							"Running": 1,
							"Standing": 10
						},
						{
							"date": "2014-03-06",
							"Walking": 3,
							"Running": 2,
							"Standing": 1
						},
						{
							"date": "2014-03-07",
							"Walking": 6,
							"Running": 8,
							"Standing": 6
						}
					]
				}
			);