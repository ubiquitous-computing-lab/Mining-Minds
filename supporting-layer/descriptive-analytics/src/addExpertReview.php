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
include"functions.php";
//print_r($_GET);
$uid=$_GET['userID'];
$reviewDescription=$_GET['review'];
$euid=$_GET['expertID'];
//2015 06 11 01:30:01
// 2015 05 13 15:51:34
$reviewDate=date('Y m d H\:i\:s');
//echo $reviewDate;

$response= addExpertReview ($uid,$euid,$reviewDescription,$reviewDate);

//echo $response;
if (strpos($response,'No Error') != false) {
    echo 'Review Inserted';
}
else	
	echo 'Review Not Inserted. Please Try Again';
	
	
	
	
?>