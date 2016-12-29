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
<?php

/*$querytype=$_GET['querytype'];
$value=$_GET['options'];
$graph=$_GET['graph'];


$options=explode("|",$value);*/

$start=$_GET['startdate'];
$end=$_GET['enddate'];
$start=explode("/",$start);

	
$end=explode("/",$end);
$startdate=$start[2].$start[0].$start[1];
$enddate=$end[2].$end[0].$end[1];


include_once "functions.php";
include_once "curltest.php";




//$search="필라테스";
//$exclude="전현무";

//search="헬스장"<or>"헬스클럽"<or>"피트니스"<or>"휘트니스"
$searchword="";
$search=explode(",",$_GET['searchsns']);


for($i=0;$i<count($search)-1;$i++){
	
	$searchword.='"'.$search[$i].'"';
	if($i!=count($search)-2)
		//$searchword .= htmlspecialchars("<or>");
		$searchword .= "<or>";
	

}


$excludeword="";
$exclude=explode(",",$_GET['excludesns']);
for($i=0;$i<count($exclude)-1;$i++){
	
	$excludeword.='"'.$exclude[$i].'"';
	
	if($i!=count($exclude)-2){
		//$excludeword .= htmlspecialchars("<or>");
				$excludeword .= "<or>";

		
		}

}


/*for($i=0;$i<count($exclude)-1;$i++){
	
$excludeword='';

}
*/
/*$exclude=explode(",",$_GET['exclude']);
$search=$_GET['searchsns'];
$exclude=$_GET['excludesns'];
*/
//print_r($searchword);
$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=topic-list&media=twitter&search=".$searchword."&exclude=".$excludeword."&from=".$startdate."&to=".$enddate."&offset=0&size=5";
$result=getJsonRest($url);

//print_r($result);

echo $url;



$colorhex= array("B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333");
	$json="";
for($i=0;$i<count($result['word']);$i++){


	$json.='
			
							{
					"Word": "'.$result['word'][$i].'",
					"Occurrence": '.$result['freq'][$i].',
					"color": "#'.$colorhex[$i].'"
					
				},';


}
?>

   <div id="snschartdiv" style="width: 500px; height: 500px; background-color: #FFFFFF;" ></div>
<script>

var chart = AmCharts.makeChart("snschartdiv", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [
			
					
					<?php echo $json;?>
							
	

	
	
	],
    "valueAxes": [{
        "position": "left",
        "title": "Frequency"
    }],
    "graphs": [{
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillColorsField": "color",
        "fillAlphas": 1,
        "lineAlpha": 0.1,
        "type": "column",
        "valueField": "Occurrence"
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
        "labelRotation": 0
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

