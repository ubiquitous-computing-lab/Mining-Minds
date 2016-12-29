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



/*********************************************************************************************

function calMovementDaily($starttime,$endtime,$uid)
function calMovementHourly($result)
function activityCal($totalAmtWalk,$totalAmtJogging,$totalAmtRunning)
function metMinutesCal($totalAmtWalk,$totalAmtJogging,$totalAmtRunning)
function retrieveUserPhysiologicalFactors($uid)
function calBurnedCal($uid,$totalAmtWalk,$totalAmtJogging,$totalAmtRunning)
**********************************************************************************************/

include_once("server.php");





/*********************************************************************************************

amount of activity Calculation Daily

**********************************************************************************************/


function calMovement($starttime,$endtime,$uid){

			
			$starttime='2016 05 11 08:00:00';
			$endtime='2016 05 11 18:00:00';
			$result=json_decode(dailyUserRecognizedActivity($uid,$starttime,$endtime),true);
			
				
			$movement=array(
								"totalAmtWalk" => 0,
								"totalAmtJogging" => 0,
								"totalAmtRunning"=> 0
								
							);		
			
			for($i=0;$i<count($result);$i++){
			
				if($result[$i]["activityDescription"]=="Running")
					$movement["totalAmtRunning"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Jogging")
					$movement["totalAmtJogging"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Walking")
					$movement["totalAmtWalk"]=$result[$i]["duration"]/60;		
			}
			
			
			//print_r($movement);
			
			return $movement;


}

/*********************************************************************************************

amount of activity Calculation Hourly for bar graph

**********************************************************************************************/


function calMovementHourly($result){

		
			$movement=array(
								"totalAmtWalk" => 0,
								"totalAmtJogging" => 0,
								"totalAmtRunning"=> 0
								
							);	
		
			
			
			for($i=0;$i<count($result);$i++){
			
				if($result[$i]["activityDescription"]=="Running")
					$movement["totalAmtRunning"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Jogging")
					$movement["totalAmtJogging"]=$result[$i]["duration"]/60;
				if($result[$i]["activityDescription"]=="Walking")
					$movement["totalAmtWalk"]=$result[$i]["duration"]/60;		
			}
			
	return $movement;	

}
/*********************************************************************************************

activity Calculation
**********************************************************************************************/

function activityCal($totalAmtWalk,$totalAmtJogging,$totalAmtRunning,$days){



		$goal=30*$days;


		$activityCal=array(
								"activityAcheivement" => 0,
								"activityPercent" => 0,
								
								
							);

		$activityCal["activityAcheivement"]=$totalAmtWalk+1.5*$totalAmtJogging+2*$totalAmtRunning;
		
			
		
		$activityCal["activityPercent"]=round($activityCal["activityAcheivement"]/$goal*100,PHP_ROUND_HALF_UP);
		
		
	
		if($activityCal["activityAcheivement"]>$goal) {
			$activityCal["activityPercent"]=100;
			return $activityCal;
			
			}
		else
			return $activityCal;


}


/*********************************************************************************************

Met Minutes
**********************************************************************************************/

function metMinutesCal($totalAmtWalk,$totalAmtJogging,$totalAmtRunning,$days){

		$metMinutesCal=array(
								"metsMinutesAcheivement" => 0,
								"metsMinutesPercent" => 0,
								
								
							);

		$goal=500*$days;	
		$metsMinutesWalk=$totalAmtWalk*5.4;
		$metsMinutesJogging=$totalAmtJogging*6.3*1.5;
		$metsMinutesRunning=$totalAmtRunning*11.1*2;
		$metMinutesCal["metsMinutesAcheivement"]=$metsMinutesWalk+$metsMinutesJogging+$metsMinutesRunning;
		
		//multiply
		$metMinutesCal["metsMinutesAcheivement"]=$metMinutesCal["metsMinutesAcheivement"]*2;
		
		
		$metMinutesCal["metsMinutesPercent"]=round($metMinutesCal["metsMinutesAcheivement"]/$goal*100,PHP_ROUND_HALF_UP);
		
		
		if($metMinutesCal["metsMinutesAcheivement"]>$goal) {
		
			$metMinutesCal["metsMinutesPercent"]=100;
			
			return $metMinutesCal;
			
			}
		else
			return $metMinutesCal;



}
/*********************************************************************************************

Retrieve User Physiological Factors by User ID

**********************************************************************************************/

function retrieveUserPhysiologicalFactors($uid){

		global $dclserver;
		$url="$dclserver/MMDataCurationRestfulService/webresources/DataCuration/PhysiologicalFactors/$uid";
		$userPhysiologicalFactors=getJsonRest($url);
		return $userPhysiologicalFactors;

}





/*********************************************************************************************

calories Burned Calculation
**********************************************************************************************/




function calBurnedCal($uid,$totalAmtWalk,$totalAmtJogging,$totalAmtRunning,$days){

			$calBurnedCal=array(
								"kcalAcheivement" => 0,
								"kcalAcheivementPercent" => 0,
								
								
							);




			$userFactors=retrieveUserPhysiologicalFactors($uid);
			if(count($userFactors)>0){
				$weight=$userFactors[0]["weight"];
			
			
			}
			else 
				$weight=0;
			
			$goal=1000*$days;
			$kcalPerMinWalk=5.4*$weight/60;
			$kcalPerMinJogging=6.3*$weight/60;
			$kcalPerMinRunning=11.1*$weight/60;
			$kcalPerDayWalk=$totalAmtWalk*$kcalPerMinWalk;
			$kcalPerDayJogging=$totalAmtJogging*$kcalPerMinJogging;
			$kcalPerDayRunning=$totalAmtRunning*$kcalPerMinRunning;
			$calBurnedCal['kcalAcheivement']=$kcalPerDayWalk+$kcalPerDayJogging+$kcalPerDayRunning;
			
			
			//multiply
			$calBurnedCal["kcalAcheivement"]=$calBurnedCal["kcalAcheivement"]*3;
			
			$calBurnedCal['kcalAcheivementPercent']=round($calBurnedCal['kcalAcheivement']/$goal*100,PHP_ROUND_HALF_UP);
			
			if($calBurnedCal['kcalAcheivement']>$goal) {
				$calBurnedCal['kcalAcheivementPercent']=100;
				return 	$calBurnedCal;
				
			}	
			else
				return 	$calBurnedCal;
}




?>