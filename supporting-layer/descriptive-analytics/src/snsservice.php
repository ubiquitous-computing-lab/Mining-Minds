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

?><?php
include_once("functions.php");

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/
/*********************Get Category Type**************************************/
/*****************************************************************************/
/*****************************************************************************/
$type="";

if(isset($_GET['type']))
		$type=$_GET['type'];
else{
	 print "Invalid Arguments";		
	 exit;
	 }
if($type=='Cereals') $id=331316;
else if($type=='Fish') $id=331317;
else if($type=='Vegetables') $id=331318;
else if($type=='Fruits') $id=331319;
else if($type=='Fat') $id=331320;
else if($type=='Milk') $id=331321;
else{
	 print "Invalid Category";
	 exit;
	 }


/*****************************************************************************/
/***************************Set Url*************************************/
/*****************************************************************************/
/*****************************************************************************/

$starttimeobject = new DateTime('-7 days');
$endtimeobject = new DateTime('-1');
$start = $starttimeobject->format('Ymd');
$end = $endtimeobject->format('Ymd');

$url='http://social.trendup.co.kr/api/brand.htm?action=volume-trend&ak=00438530-538b-496a-8ed5-dbff22782885&category=65611&group='.$id.'&media=twitter&from='.$start.'&to='.$end.'&format=json';

$stringjson=curl_download($url);
$jsonData=json_decode($stringjson,true);


/*****************************************************************************/
/***************************Translate Data*************************************/
/*****************************************************************************/
/*****************************************************************************/

for($i=0;$i<count($jsonData['name']);$i++){

		//$create.='"'.dictionary($jsonData['name'][$i]).'",';
		//print  dictionary($jsonData['name'][$i]). PHP_EOL;;
		//print  $jsonData['total'][$i].'<br>';;
		$stringjson=str_replace($jsonData['name'][$i],dictionary($jsonData['name'][$i]),$stringjson);

		

}
$stringjson="[".$stringjson."]";

$jsonData=json_decode($stringjson,true);

header('Content-type: application/json');
echo json_encode($jsonData);



/*****************************************************************************/
/***************************dictionary function*************************************/
/*****************************************************************************/
/*****************************************************************************/

function dictionary($name){


	$dict=array(
	
				//Fruits
				"키위"=>"kiwi","복숭아"=>"peach","사과"=>"apple","살구"=>"apricot","메론"=>"melon","바나나"=>"banana","자몽"=>"grapefruit","아보카도"=>"avocado","레몬"=>"lemon","귤"=>"orange",
	
				//Fat
				"호두"=>"walnut",
				//Meat
				"가자미"=>"flounder","꽁치"=>"pacific saury","닭고기"=>"chicken","대구"=>"daegu","돼지고기"=>"pork","새우"=>"shrimp","쇠고기"=>"beef","연어"=>"salmon","조기"=>"chugi",
				"참치"=>"tuna","삼치"=>"japanese mackerel","계란"=>"eggs","고등어"=>"mackerel","두부"=>"tofu","콩"=>"beans",
				//Cereals
				"귀리"=>"oats ","보리"=>"barley ","퀴노아"=>"quinoa ","현미"=>"brown ","호밀"=>"rye",
				
				//vegetables
				"가지"=>"branch","감자"=>"potato","고구마"=>"sweet potato","당근"=>"carrot ","버섯"=>"mushroom ","오이"=>"cucumber ","브로콜리"=>"broccoli ","양배추"=>"cabbage ","토마토"=>"tomato ","케일"=>"kale ",
				"샐러리"=>"celery  ","새싹채소"=>"sprouts ","치커리"=>"chicory ","양상추"=>"lettuce ","호박"=>"pumpkin",
				//milk
				"요구르트"=>"yogurt","저지방치즈"=>"low fat cheese","저지방우유"=>"low fat milk"
	);
	
	if (array_key_exists($name, $dict))
		return $dict[$name];
	else
		return $name;	
}



/*****************************************************************************/
/***************************CURL function*************************************/
/*****************************************************************************/
/*****************************************************************************/




?>