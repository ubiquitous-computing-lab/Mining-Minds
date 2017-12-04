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

include_once("server.php");

/*********************************************************************************************


Functions 

function timelinerow($startdate,$enddate)
function getRecommendation($uid,$startdate,$enddate)

function getsituationDescription($sid)

**********************************************************************************************/


/*********************************************************************************************


Add timeline row fixed user 39
**********************************************************************************************/


function timelinerow($uid,$startdate,$enddate){



//print_r(getRecommendation(39,'2015 05 04 00:30:01','2015 05 04 23:30:01'));
//$result=json_decode(getRecommendation(39,'2015 05 04 00:30:01','2015 05 04 23:30:01'),true);
$result=json_decode(getRecommendation($uid,$startdate,$enddate),true);
//echo '<pre>';print_r($result);
//[recommendationDate] => 2015-05-20 13:50:59.0


$desc=count($result)-1;
if(count($result))
{
	 for($i=$desc;$i>-1;$i--){
		//for($i=0;$i<count($result);$i++){


	?>         
                                        <div class="panel panel-default">
                                                                  <div class="panel-heading">
                                                              <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#plus_<?php echo $result[$i]['recommendationID'];?>"><strong>Time:</strong> <?php  if(isset($result[$i]['recommendationDate']))echo substr($result[$i]['recommendationDate'],10,6);?> </br><strong>Recommendation</strong>:  <?php  if(isset($result[$i]['recommendationDescription'])) echo $result[$i]['recommendationDescription'];?></a> </h4>
                                                                  </div>
                                                                  <div id="plus_<?php echo $result[$i]['recommendationID'];?>" class="panel-collapse collapse">
                                                                    <div class="panel-body"><strong>Reason</strong>:  <?php  echo getsituationDescription($result[$i]['situationID']);?></div>
                                                                     <div class="panel-body"><strong>Feedback</strong>:  <?php  echo getRecommendationFeedbackbyID($result[$i]['recommendationID']);?></div>
                                                                  </div>
                                                                </div>
                        
                        <?php
                        	}//for loop
							}//if cond
							
							else{
							
							                           
										?>
										
										<div class="panel panel-default">
									  <div class="panel-heading">
										<h4 class="panel-title"> <a  data-parent="#accordion" href="#plus_"> <strong>No Recommendations</strong>:  </a> </h4>
									  </div>
									 
									</div>
										
                            <?php
                            
							}

}



 
/*********************************************************************************************

Get Recommendation by user id, start time and end time

**********************************************************************************************/

function getRecommendation($uid,$startdate,$enddate){

global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/RetrieveRecommendationByUserIDDate";
	


	$data = array("userID"=>$uid,
	"startDate"=>$startdate,
	"endDate"=>$enddate,
		
		);
		
	//print_r($data);
	$json = json_encode($data,true);
	
	$result =postJsonRest($url,$json);
	
	return $result;


}


/*********************************************************************************************

get situation Description by Situation id
**********************************************************************************************/

function getsituationDescription($sid){

global $dclserver;
	$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/SituationBySituationID/$sid";
	$situationJson=getJsonRest($url);
	
	//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
	//rayemahmood@oslab.khu.ac.kr,12345
		
	//print_r($situationJson[0]['situationDescription']);
	
	if(isset($situationJson[0]['situationDescription']))
		$situationDescription=$situationJson[0]['situationDescription'];
	if(isset($situationJson[0]['situationCategoryDescription']))
		$situationCategoryDescription=$situationJson[0]['situationCategoryDescription'];	
		
		
	return $situationDescription. " ".$situationCategoryDescription;


}




?>