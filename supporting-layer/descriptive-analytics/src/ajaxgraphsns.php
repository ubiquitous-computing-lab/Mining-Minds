

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