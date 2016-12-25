<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>





AmCharts.makeChart("feedback_location_chartdiv",
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
						{
							"category": "Home",
							"Location": 8,

						},
						{
							"category": "Work",
							"Location": 6,

						},
						{
							"category": "Gym",
							"Location": 2,

						}
					]
				}
			);
			
			
var chart = AmCharts.makeChart( "chartdiv", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {
    "activity": "Sitting",
    "Minutes": 501.9
  }, {
    "activity": "Standing",
    "Minutes": 301.9
  }, {
    "activity": "Walking",
    "Minutes": 201.1
  }, {
    "activity": "Running",
    "Minutes": 165.8
  } ],
  "valueField": "Minutes",
  "titleField": "activity",
   "balloon":{
   "fixedPosition":true
  },
  "export": {
    "enabled": true
  }
} );



<?php
ini_set('default_charset', 'UTF-8');
header('Content-Type: text/html;charset=utf-8');
$response='{
	"word":["??","??","???","??","???","??","??","????","??","??"],
	
	"freq":[634,627,623,603,601,596,593,592,590,588]
	
}';
//echo "<pre>";
$result=json_decode($response,true);
print_r($result);
?>


var chart = AmCharts.makeChart("snschartdiv", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [{
        "country": "USA",
        "visits": 4025,
        "color": "#FF0F00"
    }, {
        "country": "China",
        "visits": 1882,
        "color": "#FF6600"
    }, {
        "country": "Japan",
        "visits": 1809,
        "color": "#FF9E01"
    }, {
        "country": "Germany",
        "visits": 1322,
        "color": "#FCD202"
    }, {
        "country": "UK",
        "visits": 1122,
        "color": "#F8FF01"
    }, {
        "country": "France",
        "visits": 1114,
        "color": "#B0DE09"
    }, {
        "country": "India",
        "visits": 984,
        "color": "#04D215"
    }, {
        "country": "Spain",
        "visits": 711,
        "color": "#0D8ECF"
    }, {
        "country": "Netherlands",
        "visits": 665,
        "color": "#0D52D1"
    }, {
        "country": "Russia",
        "visits": 580,
        "color": "#2A0CD0"
    }, {
        "country": "South Korea",
        "visits": 443,
        "color": "#8A0CCF"
    }, {
        "country": "Canada",
        "visits": 441,
        "color": "#CD0D74"
    }, {
        "country": "Brazil",
        "visits": 395,
        "color": "#754DEB"
    }, {
        "country": "Italy",
        "visits": 386,
        "color": "#DDDDDD"
    }, {
        "country": "Australia",
        "visits": 384,
        "color": "#999999"
    }, {
        "country": "Taiwan",
        "visits": 338,
        "color": "#333333"
    }, {
        "country": "Poland",
        "visits": 328,
        "color": "#000000"
    }],
    "valueAxes": [{
        "position": "left",
        "title": "Visitors"
    }],
    "graphs": [{
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillColorsField": "color",
        "fillAlphas": 1,
        "lineAlpha": 0.1,
        "type": "column",
        "valueField": "visits"
    }],
    "depth3D": 20,
	"angle": 30,
    "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
    },    
    "categoryField": "country",
    "categoryAxis": {
        "gridPosition": "start",
        "labelRotation": 90
    },
    "export": {
    	"enabled": true
     }

});
jQuery('.chart-input').off().on('input change',function() {
	var property	= jQuery(this).data('property');
	var target		= chart;
	chart.startDuration = 0;

	if ( property == 'topRadius') {
		target = chart.graphs[0];
      	if ( this.value == 0 ) {
          this.value = undefined;
      	}
	}

	target[property] = this.value;
	chart.validateNow();
});
</script>