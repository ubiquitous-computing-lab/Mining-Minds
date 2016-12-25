AmCharts.makeChart("monthcal_burned_chartdiv",
				{
					"type": "serial",
					"path": "http://www.amcharts.com/lib/3/images/",
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
							"bulletBorderThickness": 0,
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Cal Burned",
							"title": "Cal Burned",
							"type": "column",
							"valueField": "column-1"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "calburned",
							"title": "cal burned"
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
							"date": "2014-03-01 "
						},
						{
							"column-1": 6,
							"date": "2014-03-02"
						},
						{
							"column-1": 2,
							"date": "2014-03-03"
						},
						{
							"column-1": 1,
							"date": "2014-03-04"
						},
						{
							"column-1": 2,
							"date": "2014-03-05"
						},
						{
							"column-1": 3,
							"date": "2014-03-06"
						},
						{
							"column-1": 6,
							"date": "2014-03-07"
						}
					]
				}
			);









AmCharts.makeChart("monthmet_minutes_chartdiv",
				{
					"type": "serial",
					"path": "http://www.amcharts.com/lib/3/images/",
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
							"bulletBorderThickness": 0,
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Cal Burned",
							"title": "Cal Burned",
							"type": "column",
							"valueField": "column-1"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "calburned",
							"title": "cal burned"
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