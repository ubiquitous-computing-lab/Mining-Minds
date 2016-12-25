<?php 
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