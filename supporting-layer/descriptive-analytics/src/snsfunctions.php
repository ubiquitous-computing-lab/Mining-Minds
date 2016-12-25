<?php

include_once("functions.php");
//Activity Trend word ????

















/*****************************************************************************/
/***************************FoodTrend SNS*************************************/
/*****************************************************************************/
/*****************************************************************************/

function foodSNS($type,$start,$end){
	
	if($type=='Cereals') $id=331316;
	else if($type=='Fish') $id=331317;
	else if($type=='Vegetables') $id=331318;
	else if($type=='Fruits') $id=331319;
	else if($type=='Fat') $id=331320;
	else if($type=='Milk') $id=331321;
	else if($type=='Meat') $id=331317;
	
	else $id=331321;

	
	if($start==""){
		$starttimeobject = new DateTime('-7 days');
		$start = $starttimeobject->format('Ymd');
	}
	
	if($end==""){
		$endtimeobject = new DateTime('-1');
		$end = $endtimeobject->format('Ymd');

	}	
	$url='http://social.trendup.co.kr/api/brand.htm?action=volume-trend&ak=00438530-538b-496a-8ed5-dbff22782885&category=65611&group='.$id.'&media=twitter&from='.$start.'&to='.$end.'&format=json';
	$stringjson=curl_download($url);
	$jsonData=json_decode($stringjson,true);
	
	
	/*****************************************************************************/
/***************************Translate Data*************************************/
/*****************************************************************************/
/*****************************************************************************/

	for($i=0;$i<count($jsonData['name']);$i++){
	
			//$create.='"'.dictionary($jsonData['name'][$i]).'",';
			//print  dictionary($jsonData['name'][$i]);
			//print  $jsonData['total'][$i].'<br>';;
			$stringjson=str_replace($jsonData['name'][$i],dictionary($jsonData['name'][$i]),$stringjson);
	
			
	
	}

		$jsonData=json_decode($stringjson,true);
		
		return $jsonData;
		//print_r($jsonData);
	
}



/*****************************************************************************/
/***************************dictionary function*************************************/
/*****************************************************************************/
/*****************************************************************************/

function dictionary($name){


	$dict=array(
	
				//Fruits
				"키위"=>"Kiwi","복숭아"=>"Peach","사과"=>"Apple","살구"=>"Apricot","메론"=>"Melon","바나나"=>"Banana","자몽"=>"grapefruit","아보카도"=>"avocado","레몬"=>"Lemon","귤"=>"orange",
	
				//Fat
				"호두"=>"Walnut",
				//Meat
				"가자미"=>"flounder","꽁치"=>"Pacific saury","닭고기"=>"chicken","대구"=>"Daegu","돼지고기"=>"Pork","새우"=>"Shrimp","쇠고기"=>"beef","연어"=>"salmon","조기"=>"Chugi",
				"참치"=>"tuna","삼치"=>"Mackerel","계란"=>"eggs","고등어"=>"mackerel","두부"=>"Head","콩"=>"Tufu",
				//Cereals
				"귀리"=>"Oats ","보리"=>"barley ","퀴노아"=>"quinoa ","현미"=>"brown ","호밀"=>"rye",
				
				//vegetables
				"가지"=>"Branch","감자"=>"potato","고구마"=>"sweet potato","당근"=>"Carrot ","버섯"=>"mushroom ","오이"=>"cucumber ","브로콜리"=>"broccoli ","양배추"=>"cabbage ","토마토"=>"tomato ","케일"=>"Kale ",
				"샐러리"=>"celery  ","새싹채소"=>"Sprouts ","치커리"=>"chicory ","양상추"=>"lettuce ","호박"=>"pumpkin",
				//milk
				"요구르트"=>"Yogurt","저지방치즈"=>"low fat cheese","저지방우유"=>"low fat milk"
	);
	
	if (array_key_exists($name, $dict))
		return $dict[$name];
}






function foodSNSGraph($type,$start,$end){

			$snsjson=foodSNS($type,$start,$end);
			$json="";
			
						$colorhex= array("B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333","B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333","B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333","B0DE09", "04D215", "0D8ECF","0D52D1","2A0CD0","8A0CCF","CD0D74","754DEB","DDDDDD","999999","333333");

			
				for($i=0;$i<count($snsjson['name']);$i++){
				
					
					
					$json.='
									
													{
											"Word": "'.$snsjson['name'][$i].'",
											"Occurrence": '.$snsjson['total'][$i].',
											"color": "#'.$colorhex[$i].'"
											
										},';
						
				
				}


		
			?>

  
<script>

var chart = AmCharts.makeChart("<?php echo $type?>snschartdiv", {
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


<?php
}






function locationSNS(){



			$starttimeobject = new DateTime('-1 days');
			$endtimeobject = new DateTime('NOW');
			$start = $starttimeobject->format('Y-m-d');
			$end = $endtimeobject->format('Y-m-d');
			$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=topic-list&media=twitter&search=%22%ED%97%AC%EC%8A%A4%EC%9E%A5%22%3Cor%3E%22%ED%97%AC%EC%8A%A4%ED%81%B4%EB%9F%BD%22%3Cor%3E%22%ED%94%BC%ED%8A%B8%EB%8B%88%EC%8A%A4%22%3Cor%3E%22%ED%9C%98%ED%8A%B8%EB%8B%88%EC%8A%A4%22&exclude=%22%EB%8C%80%ED%86%B5%EB%A0%B9%22%3Cor%3E%22%EC%B2%AD%EC%99%80%EB%8C%80%22%3Cor%3E%22%EC%B6%94%EB%AF%B8%EC%95%A0%22&from=".$start."&to=".$end."&offset=0&size=7";
			$result=getJsonRest($url);

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

  
<script>

var chart = AmCharts.makeChart("locationsnschartdiv", {
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


<?php
}


function foodsentimentSNS(){

			
			
		
			$starttimeobject = new DateTime('-1 days');
			$endtimeobject = new DateTime('NOW');
			$start = $starttimeobject->format('Y-m-d');
			$end = $endtimeobject->format('Y-m-d');
			
			
			$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=topic-list&media=twitter&search=%22%EA%B3%BC%EC%9D%BC%22&exclude=%22%EB%B3%80%EB%B0%B1%ED%98%84%22%3Cor%3E%22%EC%82%AC%ED%83%95%EC%8B%A0%22%3Cor%3E%22%EC%B9%98%ED%82%A8%22&from=".$start."&to=".$end."&offset=0&size=5";
			$result=getJsonRest($url);
			
			
			//$result=json_decode($response,true);
			
			
			$data='';
			$k=0;
			for($i=0;$i<count($result['word']);$i++){
			
						//echo $result['word'][$i];
						$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=volume-trend&media=twitter&search=".$result['word'][$i]."&exclude=%22%EB%B3%80%EB%B0%B1%ED%98%84%22%3Cor%3E%22%EC%82%AC%ED%83%95%EC%8B%A0%22%3Cor%3E%22%EC%B9%98%ED%82%A8%22&from=".$start."&to=".$end;
							
						$volume=getJsonRest($url);
						$data['word'][$k]=$result['word'][$i];
						$data['pos'][$k]=0;
						$data['neg'][$k]=0;
						$data['neutral'][$k]=0;
						for($j=0;$j<count($volume['freq']);$j++){
			
								
																
				
								$data['pos'][$k]=$volume['pos'][$j]+$data['pos'][$k];
								$data['neg'][$k]=$volume['neg'][$j]+$data['neg'][$k];
								$data['neutral'][$k]=$volume['freq'][$j]+$data['neutral'][$k];
				
								
						}							
						
						$data['neutral'][$k]=$data['neutral'][$k]-$data['neg'][$k]-$data['pos'][$k];
						$k++;
								
			
			
			}
			
			return $data;
			
			

}





function foodsentimentSNSGraph(){


?>
 <script>
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
  
  <?php

	
  	$data=foodsentimentSNS();
  	for($j=0;$j<count($data['word']);$j++){
	
	echo "{\"Word\":\"".$data['word'][$j]."\",";
	echo "\"neutral\":".$data['neutral'][$j].",";
	echo "\"postive\":".$data['pos'][$j].",";
	echo "\"negative\":".$data['neg'][$j]."}";
	if($j!=count($data['word'])-1)
		echo ",";
	
	?>
	
	<?php }
  
  ?>
  
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
    "title": "neutral",
    "type": "column",
    "color": "#000000",
    "valueField": "neutral"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "postive",
    "type": "column",
    "color": "#000000",
    "valueField": "postive"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "negative",
    "type": "column",
    "color": "#000000",
    "valueField": "negative"
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


<?php

}

function fooddashboardGraph(){
?>

<script>

var chart = AmCharts.makeChart("fooddashboardGraph", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [
			
					
					
									
													{
											"Word": "Kiwi",
											"Occurrence": 1581,
											"color": "#B0DE09"
											
										},
									
													{
											"Word": "Peach",
											"Occurrence": 15663,
											"color": "#04D215"
											
										},
									
													{
											"Word": "Apple",
											"Occurrence": 58164,
											"color": "#0D8ECF"
											
										},
									
													{
											"Word": "Apricot",
											"Occurrence": 2145,
											"color": "#0D52D1"
											
										},
									
													{
											"Word": "Melon",
											"Occurrence": 46055,
											"color": "#2A0CD0"
											
										},
									
													{
											"Word": "Banana",
											"Occurrence": 20297,
											"color": "#8A0CCF"
											
										},
									
													{
											"Word": "grapefruit",
											"Occurrence": 10030,
											"color": "#CD0D74"
											
										},
									
													{
											"Word": "avocado",
											"Occurrence": 2593,
											"color": "#754DEB"
											
										},
									
													{
											"Word": "Lemon",
											"Occurrence": 9675,
											"color": "#DDDDDD"
											
										},
									
													{
											"Word": "orange",
											"Occurrence": 4231,
											"color": "#999999"
											
										},							
	

	
	
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


 
        <script>
	$("#pushnotify").click(function () {
	
   	$.ajax({
        type: "GET",
        url: 'updateview.php',
        data: "options=dummy",
        success: function(response) {
		
		 $("#newnotify").html("");

        }
    });
       

});
</script>  
<?php

}

function  staticfoodbreakdown()
{

?>
<script>
var chart = AmCharts.makeChart( "foodbreakdownchart", {
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
<?php 

}



?>