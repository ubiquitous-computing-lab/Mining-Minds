<?php


/*********************************************************************************************


Functions 

dailyUserRecognizedActivity($uid,$starttime,$endtime)
function getActivityRecommendation ($uid,$startdate,$enddate)
function  userRecognizedActivityAccumulate($uid,$starttime,$endtime)
function printactivity($array)

**********************************************************************************************/



include_once("server.php");





/*********************************************************************************************


Get Activity Recommendation by user id and date rang
**********************************************************************************************/


function getActivityRecommendation ($uid,$startdate,$enddate){

	global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/RetrieveActivityRecommendationByUserAndDate";
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345

	$data = array("userID"=>$uid,
	"endTimestamp"=>"2014 01 01 01:30:01",
	"startTimestamp"=>"2015 06 11 01:30:01",
		
		);
		
	//	print_r($data);
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	return $result;


}
	
/*********************************************************************************************

Get User Recognized Activity Accumulate By User And Date Range (Accumulate)
**********************************************************************************************/

 //userRecognizedActivityAccumulate(39,'','');
function  userRecognizedActivityAccumulate($uid,$starttime,$endtime){


global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulateByUserAndDateRange";
	



	$data = array("userId"=>$uid,
	"startTime"=>$starttime,
	"endTime"=>$endtime,
		
		);
		
		
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	//print_r($result);
	return $result;

}


/*********************************************************************************************

dailychatdiv json generation

**********************************************************************************************/




function printactivity($array){
 //echo '<pre>';
 
$LyingDown=0;
$Sitting=0;
$Standing=0;
$Walking=0;
$Jogging=0;
$Running=0;
$Stretching=0;
$Dancing=0;
$Sweeping=0;
$Eating=0;
$ClimbingStairs=0;

for($c=0;$c<count($array);$c++){
 
				 if($array[$c]["activityDescription"]=="Walking"){
									
						$Walking=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Running"){
									
						$Running=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Standing"){
									
						$Standing=$array[$c]["duration"]/60;
									
								
				}
				
				 if($array[$c]["activityDescription"]=="Sitting"){
									
						$Sitting=$array[$c]["duration"]/60;
									
								
				} 
				
				 if($array[$c]["activityDescription"]=="Jogging"){
									
						$Jogging=$array[$c]["duration"]/60;
									
								
				}
						
						
			if($array[$c]["activityDescription"]=="LyingDown"){
									
						$LyingDown=$array[$c]["duration"]/60;
									
								
				}	
			
			if($array[$c]["activityDescription"]=="Stretching"){
									
						$Stretching=$array[$c]["duration"]/60;
									
								
				}	
			if($array[$c]["activityDescription"]=="Dancing"){
									
						$Dancing=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="Sweeping"){
							
						$Sweeping=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="Eating"){
									
						$Eating=$array[$c]["duration"]/60;
									
								
				}		
			if($array[$c]["activityDescription"]=="ClimbingStairs"){
									
						$ClimbingStairs=$array[$c]["duration"]/60;
									
								
				}										
								
								
 }
 
 
 
 
echo "\"LyingDown\": ". abs($LyingDown).',';
echo "\"Sitting\": ". abs($Sitting).',';
echo "\"Standing\": ". abs($Standing).',';
echo "\"Walking\": ". abs($Walking).',';
echo "\"Jogging\": ". abs($Jogging).',';
echo "\"Running\": ". abs($Running).',';
echo "\"Stretching\": ". abs($Stretching).',';
echo "\"Dancing\": ". abs($Dancing).',';
echo "\"Sweeping\": ". abs($Sweeping).',';
echo "\"Eating\": ". abs($Eating).',';
echo "\"ClimbingStairs\": ". abs($ClimbingStairs);

 
 $Walking=0;
 $Running=0;
 $Standing=0;
 $Sitting=0;
 $Jogging=0;
 $LyingDown=0;
$Stretching=0;
$Dancing=0;
$Sweeping=0;
$Eating=0;
$ClimbingStairs=0;
 }


/*********************************************************************************************

Get User daily accumulated Recognized Activity by user id, start time and end time 

**********************************************************************************************/
function dailyUserRecognizedActivity($uid,$starttime,$endtime){


global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivity";
	
//$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulate
//$starttime="2015 05 04 01:00:04.000";
//$endtime="2015 05 04 01:59:59.000";


	$data = array("userId"=>$uid,
	"startTime"=>$starttime,
	"endTime"=>$endtime,
		
		);
		
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	return $result;

}


/*********************************************************************************************


getActivityLabel static
**********************************************************************************************/


function getActivityLabel($activityID){




switch ($activityID) {
    case 1:
        return "Running";
        break;
    case 2:
        return "Walking";
        break;
	case 3:
        return "Sitting";
        break;
	case 4:
        return "Subway";
        break;
	case 5:
        return "Bus";
        break;
	case 6:
        return "Cycling";
        break;
	case 7:
        return "LyingDown";
        break;					
	case 8:
        return "Standing";
        break;
	case 9:
        return "Jogging";
        break;
	case 10:
        return "Hiking";
        break;
	case 11:
        return "Climbing";
        break;
	case 12:
        return "Descending";
        break;	
	case 13:
        return "Stretching";
        break;	
	case 14:
        return "Dancing";
        break;	
	case 15:
        return "Sweeping";
        break;	
	case 16:
        return "Eating";
        break;	
	case 17:
        return "Escalator";
        break;
	case 18:
        return "Elevator";
        break;	
		
	case 19:
        return "Resting";
        break;
	
			
    default:
        echo "No label";
}


}

?>