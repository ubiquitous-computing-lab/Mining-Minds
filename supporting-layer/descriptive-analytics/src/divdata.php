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

?><script>
<?php

//print_r(foodSNS());
$data=foodSNS();
	//print_r($data);
?>


var chart = AmCharts.makeChart("snschartdiv", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [<?php
	
			for($i=0;$i<count($data[0]['meaning']);$i++){


					echo'
			
							{
					"Word": "'.$data[0]['meaning'][$i].'",
					"visits": '.$data[0]['freq'][$i].',
					"color": "#'.$data[1][$i].'"
					
				},';
				
				
	
	
	}
	?>
	
	

	
	
	],
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
    "categoryField": "Word",
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


<?php


function foodSNS(){

			
			
			$dict='{
				"word":["홍차","케이크","아이스티","딸기","알이"],
				
				"meaning":["black tea","Cake","Iced Tea","Strawberry","Eggs"]
				
			}';
			$dict=json_decode($dict,true);
			
			
			$response='{
				"word":["껍질","홍차","케이크","자연","초파리","전공","알이","아이스티","충격","동기","딸기"],
				
				"freq":[634,627,623,603,601,596,593,592,590,588,800]
				
			}';
			
			$result=json_decode($response,true);
			$k=0;
			for($i=0;$i<count($dict['word']);$i++){
				for($j=0;$j<count($result['word']);$j++){
				
					if (strcmp ($result['word'][$j], $dict['word'][$i]) == 0){
						
						
						
						$data['word'][$k]=$dict['word'][$i];
						$data['meaning'][$k]=$dict['meaning'][$i];
						$data['freq'][$k]=$result['freq'][$j];
						$k++;
						
					
					}
			}}
		
			
			$colorhex= array("B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333");
			
		
				
			 return array($data,$colorhex);

}






?>


</script>