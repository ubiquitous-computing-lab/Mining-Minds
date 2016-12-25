AmCharts.makeChart("amount_met_chartdiv",
				{
					"type": "serial",
					"path": "http://www.amcharts.com/lib/3/",
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
					"trendLines": [],
					"graphs": [
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Amount of MET",
							"title": "Amount of MET",
							"type": "column",
							"valueField": "column-1"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "Amount of MET",
							"title": "Amount of MET"
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"useGraphSettings": true
					},
					"titles": [],
					"dataProvider": [
						{
							"column-1": 8,
							"date": "2014-03-01 08"
						},
						{
							"column-1": 6,
							"date": "2014-03-01 09"
						},
						{
							"column-1": 2,
							"date": "2014-03-01 10"
						},
						{
							"column-1": 1,
							"date": "2014-03-01 11"
						},
						{
							"column-1": 2,
							"date": "2014-03-01 12"
						},
						{
							"column-1": 3,
							"date": "2014-03-01 13"
						},
						{
							"column-1": 6,
							"date": "2014-03-01 14"
						}
					]
				}
			);