<?php 

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