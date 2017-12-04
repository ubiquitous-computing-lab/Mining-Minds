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
/*********************************************************************************************

function addUserRecognizedActivity()
function addExpertReview ($uid,$euid,$reviewDescription,$reviewDate)
**********************************************************************************************/








/*********************************************************************************************

Add User Recognized Activity
**********************************************************************************************/
//print addUserRecognizedActivity();
function addUserRecognizedActivity(){

		global $dclserver;
		$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/AddUserRecognizedActivity";
		
		$data = array("userRecognizedActivityId"=>NULL,
		"userId"=>39,
		"activityId"=>2,
		"startTime"=>"2015 05 05 16:58:56",
		"endTime"=>"2015 05 05 16:58:59",
		"duration"=>3
		
		);
		
	
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	
	return $result;

		
}

/*********************************************************************************************


Add Expert Review
$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/AddExpertReview
{
"expertReviewID":null,
"userID":35,
"userExpertID":2,
"reviewDescription":"testing review desc",
"reviewDate":"2015 05 02 02:30:01"
}

**********************************************************************************************/


function addExpertReview ($uid,$euid,$reviewDescription,$reviewDate){

	global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/AddExpertReview";
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345

	$data = array("expertReviewID"=>null,
				"userID"=>$uid,
				"userExpertID"=>$euid,
				"reviewDescription"=>$reviewDescription,
				"reviewDate"=>$reviewDate,
				"reviewStatusID"=>1
		
		);
		
		


		
		
	//print_r($data);
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	
	return $result;


}	


/*********************************************************************************************


Add Expert Review
$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/AddExpertReview
{
"expertReviewID":null,
"userID":35,
"userExpertID":2,
"reviewDescription":"testing review desc",
"reviewDate":"2015 05 02 02:30:01"
}

**********************************************************************************************/


function addExpertRecommendation ($uid,$euid,$reviewDescription,$reviewDate){

	global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/AddRecommendation";
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345
	


	$data = array("recommendationID"=>null,
				  "recommendationIdentifier"=>"A0001",
				  "situationID"=>1,
				 "recommendationDescription"=>$reviewDescription,
				 "recommendationTypeID"=>1,
				 "conditionValue"=>"condition Value",
				 "recommendationLevelID"=>1,
				 "recommendationStatusID"=>1,
				 "recommendationDate"=>$reviewDate

				
		
		);
		
		
	//print_r($data);
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	
	return $result;


}	




?>