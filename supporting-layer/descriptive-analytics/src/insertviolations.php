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
require_once("sqlconn.php");
require_once("dbfunctions.php");
require_once("functions.php");



$url="$llmserver/testWeb/webresources/testweb/Violations";
$result=getJsonRest($url);

$userlist= retrieveUserList();

$k=0;$improperlist=array();
for($i=0;$i<count($result);$i++){


		$key = array_search($result[$i]['userID'], array_column($userlist, 'userID'));
		$improperlist{$i}['Name']=$userlist[$key]["firstName"]. " ".$userlist[$key]["lastName"];
		
		$improperlist{$i}['userID']=$result[$i]['userID'];
		$improperlist{$i}['startTime']=$result[$i]['startTime'];
		$improperlist{$i}['logID']=$result[$i]['logID'];
		$improperlist{$i}['activityTargetDuration']=$result[$i]['activityTargetDuration'];
		$improperlist{$i}['activityID']=$result[$i]['activityID'];
		
		
}

for($i=0;$i<count($improperlist);$i++){

$sql = "INSERT INTO notifications (logID, time, activityTargetDuration,userid,name)VALUES (".$improperlist{$i}['logID'].", '".$improperlist{$i}['startTime']."', '".$improperlist{$i}['activityTargetDuration']."', ".$improperlist{$i}['userID'].",'".$improperlist{$i}['Name']."')";
echo $sql;
$result = $conn->query($sql);	


}