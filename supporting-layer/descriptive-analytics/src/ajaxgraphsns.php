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

?>

<?php


include_once("snsfunctions.php");




/*$querytype=$_GET['querytype'];
$value=$_GET['options'];
$graph=$_GET['graph'];


$options=explode("|",$value);*/

$start=$_GET['startdate'];
$end=$_GET['enddate'];
$start=explode("/",$start);

	
$end=explode("/",$end);
$startdate=$start[2].$start[0].$start[1];
$enddate=$end[2].$end[0].$end[1];
$snstype=$_GET['snstype'];


?>

<div id="<?php echo $snstype;?>snschartdiv" style="width: 1000px; height: 500px; background-color: #FFFFFF;" >
<?php

foodSNSGraph($snstype,$startdate,$enddate);



?>