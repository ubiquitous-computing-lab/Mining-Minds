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
else if($type=='<e>Fruits</e>') $id=331319;
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
		//print  dictionary($jsonData['name'][$i]);
		//print  $jsonData['total'][$i].'<br>';;
		$stringjson=str_replace($jsonData['name'][$i],dictionary($jsonData['name'][$i]),$stringjson);

		

}




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
				"키위"=>"Kiwi","복숭아"=>"Peach","사과"=>"Apple","살구"=>"Apricot","메론"=>"Melon","바나나"=>"Banana","자몽"=>"grapefruit","아보카도"=>"avocado","레몬"=>"Lemon","귤"=>"orange",
	
				//Fat
				"호두"=>"Walnut",
				//Mear
				"가자미"=>"flounder","꽁치"=>"Pacific saury","닭고기"=>"chicken","대구"=>"Daegu","돼지고기"=>"Pork","새우"=>"Shrimp","쇠고기"=>"beef","연어"=>"salmon","조기"=>"early",
				"참치"=>"tuna","삼치"=>"tuna","계란"=>"eggs","고등어"=>"mackerel","두부"=>"Head","콩"=>"beans",
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


/*****************************************************************************/
/***************************CURL function*************************************/
/*****************************************************************************/
/*****************************************************************************/


function curl_download($Url){
 
    // is cURL installed yet?
    if (!function_exists('curl_init')){
        die('Sorry cURL is not installed!');
    }
 
    // OK cool - then let's create a new cURL resource handle
    $ch = curl_init();
 
    // Now set some options (most are optional)
 
    // Set URL to download
    curl_setopt($ch, CURLOPT_URL, $Url);
 
 
    // User agent
    curl_setopt($ch, CURLOPT_USERAGENT, "MozillaXYZ/1.0");
 
    // Include header in result? (0 = yes, 1 = no)
    curl_setopt($ch, CURLOPT_HEADER, 0);
 
    // Should cURL return or print out the data? (true = return, false = print)
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
    // Timeout in seconds
    curl_setopt($ch, CURLOPT_TIMEOUT, 10);
 
    // Download the given URL, and return output
    $output = curl_exec($ch);
 
    // Close the cURL resource, and free system resources
    curl_close($ch);
 
    return $output;
}

/*$stringjson='{
	"name":[
	"키위","복숭아","사과","살구","메론","바나나","자몽","아보카도","레몬","귤"],
	"total":[
	"3891","30710","128319","6187","41644","44829","16285","1455","29677","14297"]
}';

$jsonData=json_decode($stringjson,true);
*/


//print_r($stringjson);
?>