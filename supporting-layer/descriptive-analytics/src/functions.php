<?php
include_once("server.php");
include "servicecuration.php";
include "feedbackfunctions.php";
include "addfunctions.php";
include "restcallfunctions.php";
include "activityfunctions.php";
include "timelinefunctions.php";

/*********************************************************************************************


Functions 

printWeekDays()
addExpertReview ($uid,$euid,$reviewDescription,$reviewDate)
getActivityRecommendation ($uid,$startdate,$enddate)
userAuth($email,$password)
postJsonRest($url,$json)
getJsonRest($url)
calculateActivityDuration($startTime,$endTime)
getRecommendation($uid,$startdate,$enddate)
getsituationDescription($sid)
retrieveUserList()
retrieveUserByUserID($uid)

function getRecommendationFeedbackbyID($recID)
function timelinerow($startdate,$enddate)

Get Activity Feedback by UserID
function getRecommendationFeedbackbyUserID($uid)
function getFactsFeedbackbyUserID($uid)

addDay($day)//might not work



function printactivity($activity,$array)
function addUserRecognizedActivity()


calculateweeklygraphdata($weeklyactivityjson,startdate)

calculatemonthlygraphdata($weeklyactivityjson,startdate)

**********************************************************************************************/




/*********************************************************************************************
CaLUCLATE weekly location
calculateweeklygraphdata($weeklyactivityjson,startdate)
**********************************************************************************************/


function calculateweeklylocation($days,$uid){



	
			$locationdata=array();//$week=6;
			$week=$days;	
			for($i=0;$i<=$week;$i++){
			
				$starttimeobject = new DateTime('-'.$days.' days');
				
				//print $starttimeobject->format('Y m d');
				$locationdata[$i]["date"]=$starttimeobject->format('Y-m-d');
				$locationdata[$i]["Home"]=0;
				$locationdata[$i]["Office"]=0;
				$locationdata[$i]["Yard"]=0;
				$locationdata[$i]["Gym"]=0;
				$locationdata[$i]["Mall"]=0;
				$locationdata[$i]["Restaurant"]=0;
				$locationdata[$i]["Outdoors"]=0;
				$locationdata[$i]["Transport"]=0;
				$days--;
			
			}
			
		
			$starttimeobject = new DateTime('-'.$week.' days');	
			//$starttimeobject = new DateTime('2015-05-16');
			$starttimeobject->setTime(00,00,00);
			
			//set end time today
			$endtimeobject = new DateTime('NOW');
			$endtimeobject->setTime(23,59,59);
			
			//convert date objects into strings
			$start = $starttimeobject->format('Y m d H:i:s');
			$end = $endtimeobject->format('Y m d H:i:s');
			global $dclserver;
			
			$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserDetectedLocation";
			
			
			$data = array(
							"userId"=>$uid,
							"startTime"=>$start,
							"endTime"=>$end 
						);
						
						
				
				
						
			$json = json_encode($data,true);
			$result =postJsonRest($url,$json);
			
			$result=json_decode($result,true); 
			//print_r($data);exit;
			for($i=0;$i<count($result);$i++){
			
				$key = array_search(substr($result[$i]['startTime'],0,10), array_column($locationdata, 'date'));
				if(isset($result[$i]['locationLabel'])){
					if($result[$i]['locationLabel']=="Home"){
					
							$locationdata[$key]["Home"]=$locationdata[$key]["Home"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Office"){
					
							$locationdata[$key]["Office"]=$locationdata[$key]["Office"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Yard"){
					
							$locationdata[$key]["Yard"]=$locationdata[$key]["Yard"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Gym"){
					
							$locationdata[$key]["Gym"]=$locationdata[$key]["Gym"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Mall"){
					
							$locationdata[$key]["Mall"]=$locationdata[$key]["Mall"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Restuarant"){
					
							$locationdata[$key]["Restuarant"]=$locationdata[$key]["Restuarant"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Outdoors"){
					
							$locationdata[$key]["Outdoors"]=$locationdata[$key]["Outdoors"]+$result[$i]['duration'];
					}	
					
					
					if($result[$i]['locationLabel']=="Transport"){
					
							$locationdata[$key]["Transport"]=$locationdata[$key]["Transport"]+$result[$i]['duration'];
					}	
					
				}	
					
			}
			
		
		return $locationdata;	

}


/*********************************************************************************************
CaLUCLATE weekly activity
calculateweeklygraphdata($weeklyactivityjson,startdate)
**********************************************************************************************/

function calculateweeklygraphdata($result,$s){

					$days ='';
					for($i=0;$i<count($result);$i++){
												//print substr($result[$i]['activityDate'],0,10).'/n';
											
												if($s==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[0])) {
																	   $days[0] = NULL;}
																	   if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[0].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 1 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[1])) {
																	   $days[1] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[1].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												
												}
												
												$day1flag=0;
												if(date('Y-m-d', strtotime($s. ' + 2 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[2])) {
																	   $days[2] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[2].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												if(date('Y-m-d', strtotime($s. ' + 3 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[3])) {
																	   $days[3] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[3].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 4 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[4])) {
																		$days[4] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[4].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 5 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[5])) {
																	   $days[5] = NULL;}			
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[5].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
												}
												
												if(date('Y-m-d', strtotime($s. ' + 6 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[6])) {
																	   $days[6] = NULL;}
																if ( isset($result[$i]['activityDescription'])&& isset($result[$i]['duration'])) {
															
																			$days[6].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																		}	
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												
												
											 }	
											 
											 
											 return $days;
						 
						 
						 }











/*********************************************************************************************
CaLUCLATE monthly activity
calculatemonthlygraphdata($monthlyactivityjson,startdate)
**********************************************************************************************/

function calculatemonthlygraphdata($result,$s){

					$days ='';
					for($i=0;$i<count($result);$i++){
												//print substr($result[$i]['activityDate'],0,10).'/n';
											
												if($s==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[0])) {
																	   $days[0] = NULL;}
															
																$days[0].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 1 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[1])) {
																	   $days[1] = NULL;}
																$days[1].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												
												if(date('Y-m-d', strtotime($s. ' + 2 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[2])) {
																	   $days[2] = NULL;}
																$days[2].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												if(date('Y-m-d', strtotime($s. ' + 3 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[3])) {
																	   $days[3] = NULL;}
																$days[3].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 4 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[4])) {
																		$days[4] = NULL;}
																$days[4].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 5 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[5])) {
																	   $days[5] = NULL;}			
																$days[5].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 6 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[6])) {
																	   $days[6] = NULL;}
																$days[6].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 7 days'))==substr($result[$i]['activityDate'],0,10)){
												
																if ( ! isset($days[7])) {
																	   $days[7] = NULL;}
																$days[7].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												
												if(date('Y-m-d', strtotime($s. ' + 8 days'))==substr($result[$i]['activityDate'],0,10)){
													
																if ( ! isset($days[8])) {
																	   $days[8] = NULL;}
																$days[8].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												if(date('Y-m-d', strtotime($s. ' + 9 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[9])) {
																	   $days[9] = NULL;}
																$days[9].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 10 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[10])) {
																		$days[10] = NULL;}
																$days[10].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 11 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[11])) {
																	   $days[11] = NULL;}			
																$days[11].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 12 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[12])) {
																	   $days[12] = NULL;}
																$days[12].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
													
												if(date('Y-m-d', strtotime($s. ' + 13 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[13])) {
																		$days[13] = NULL;}
																$days[13].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 14 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[14])) {
																	   $days[14] = NULL;}			
																$days[14].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 15 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[15])) {
																	   $days[15] = NULL;}
																$days[15].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 16 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[16])) {
																		$days[16] = NULL;}
																$days[16].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 17 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[17])) {
																	   $days[17] = NULL;}			
																$days[17].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 18 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[18])) {
																	   $days[18] = NULL;}
																$days[18].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 19 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[19])) {
																		$days[19] = NULL;}
																$days[19].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 20 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[20])) {
																	   $days[20] = NULL;}			
																$days[20].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
												}
												
												if(date('Y-m-d', strtotime($s. ' + 21 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[21])) {
																	   $days[21] = NULL;}
																$days[21].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 22 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[22])) {
																	   $days[22] = NULL;}
																$days[22].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 23 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[23])) {
																	   $days[23] = NULL;}
																$days[23].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 24 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[24])) {
																	   $days[24] = NULL;}
																$days[24].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 25 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[25])) {
																	   $days[25] = NULL;}
																$days[25].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 26 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[26])) {
																	   $days[26] = NULL;}
																$days[26].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 27 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[27])) {
																	   $days[27] = NULL;}
																$days[27].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 28 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[28])) {
																	   $days[28] = NULL;}
																$days[28].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
												if(date('Y-m-d', strtotime($s. ' + 29 days'))==substr($result[$i]['activityDate'],0,10)){
																if ( ! isset($days[29])) {
																	   $days[29] = NULL;}
																$days[29].= "\"".$result[$i]['activityDescription']."\"".":". ($result[$i]['duration']/60).",";
																//echo "\"date\"".":\"". substr($result[$i]['activityDate'],0,10)."\",";
												}
											 }	
											 
											 
											 return $days;
						 
						 
						 }
















/*********************************************************************************************

Retrieve User from user ID
**********************************************************************************************/

function retrieveUserByUserID($uid){

	global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/DataCuration/User/$uid";
	$userJson=getJsonRest($url);
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345

	//print_r($situationJson[0]['situationDescription']);
	return $userJson;


}













/*********************************************************************************************

Retrieve User List
**********************************************************************************************/

function retrieveUserList(){
	
	
	global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/DataCuration/UserListByExpertID/1";
	
	$retrieveUserListJson=getJsonRest($url);
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345

	//print_r($situationJson[0]['situationDescription']);
	return $retrieveUserListJson;


}


function ageCalculator($dob){
	if(!empty($dob)){
		$birthdate = new DateTime($dob);
		$today   = new DateTime('today');
		$age = $birthdate->diff($today)->y;
		return $age;
	}else{
		return 0;
	}
}


function print_var_name($var) {
    foreach($GLOBALS as $var_name => $value) {
        if ($value === $var) {
            return $var_name;
        }
    }

    return false;
}







/*********************************************************************************************

printWeekDays
http://stackoverflow.com/questions/16624171/how-to-iterate-day-in-php

**********************************************************************************************/


function printWeekDays(){

			$days='3';
			$start = new DateTime('');
			$end = new DateTime('+3 days');
			$interval = new DateInterval('P1D');
			$period = new DatePeriod($start, $interval, $end);
			
			foreach ($period as $dt) {
				if ($dt->format("N") === 7) {
				
					echo 'if'.'<br>';
					$end->add(new DateInterval('P1D'));
				}
				else  {
					echo $dt->format("l Y-m-d") . PHP_EOL;
					
							echo 'else'.'<br>';
				}
			}
}
	
	
	

	
	

/*********************************************************************************************


User Authentication Function

**********************************************************************************************/


function userAuth($email,$password){
	global $dclserver;

	$url="$dclserver/MMDataCurationRestfulService/webresources/DataCuration/ValidateUser";
	
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345

			$data = array(
		
					"emailAddress"=>"$email",
					"password"=>"$password"
		
		);
		
	//	print_r($data);
			$json = json_encode($data,true);
			
			$result =postJsonRest($url,$json);
			return $result;


}
	
	
	
	
	
	
	
	




/*********************************************************************************************


calculate time between two dates in minutes

**********************************************************************************************/

function calculateActivityDuration($startTime,$endTime){

		$start = new DateTime($startTime);
		$end = new DateTime($endTime);
		
		$start = (int)$start->format('U');
		$end = (int)$end->format('U');
		
		$duration =($end - $start)/60;
		return $duration;



			}




/*********************************************************************************************

duplicate function
returm actvty label from activity id

**********************************************************************************************/

function getActivityLabel1($activityID){


if($activityID==1)
	return "Running";
	
if($activityID==2)
	return "Walking";	
	
if($activityID==3)
	return "Sitting";

if($activityID==7)
	return "LyingDown";

if($activityID==8)
	return "Standing";
	
	
if($activityID==9)
	return "Jogging";

if($activityID==13)
	return "Stretching";

if($activityID==14)
	return "Dancing";

if($activityID==15)
	return "Sweeping";

if($activityID==16)
	return "Eating";

if($activityID==21)
	return "ClimbingStairs";

}

/*********************************************************************************************


Add 24 hours

**********************************************************************************************/


function addDay($day){
	
	/*//$startdate='2015 5 4 00:30:01';
	$startdatearray= explode(" ", $day);
	$newday=$startdatearray[2]+1;
	//echo '<pre>';
	//print $startdatearray[2]. " ".$newday;
	$newStartDate="$startdatearray[0] $newday $startdatearray[2] $startdatearray[3]";
	return $newStartDate;*/
	
	}


?>