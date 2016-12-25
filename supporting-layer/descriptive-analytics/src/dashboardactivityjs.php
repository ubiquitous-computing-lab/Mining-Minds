<?php // include "functions.php";


include_once("server.php");
include_once("functions.php");






//set start time 60 days ago
$starttimeobject = new DateTime('-60 days');
$starttimeobject->setTime(00,00,00);

//set end time today
$endtimeobject = new DateTime('NOW');
$endtimeobject->setTime(23,59,59);

//convert date objects into strings
$start = $starttimeobject->format('Y m d H:i:s');
$end = $endtimeobject->format('Y m d H:i:s');	


//Dashboard Activity Class represents the ration of all the activities.
// The analytics represents all the users in Mining Mind
//setter and getter functions
class dashboardActivity {


   		private $data = array();
	
	
		public function __set($variable, $value){
        $this->data[$variable] = $value;
    }

    	public function __get($variable){
			if(isset($this->data[$variable])){
				return $this->data[$variable];
			}else{
				die('Unknown variable.');
			}
    }
			
		
		}


$dashboardData = new dashboardActivity;
$dashboardData->userId = 0;
$dashboardData->startTime = $start;
$dashboardData->endTime = $end;


$data = array(
		
				"userId"=>$dashboardData->userId,
				"startTime"=>$dashboardData->startTime,
				"endTime"=>$dashboardData->endTime 
		
		);






global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedActivityAccumulateByUserAndDateRange";
$json = json_encode($data,true);
$result =postJsonRest($url,$json);
//echo "<pre> hello";
$result=json_decode($result,true); 
//print_r($result);
$sittingtime=0;$standingtime=0;$walkingtime=0;$runningtime=0;
for($i=0;$i<count($result);$i++){
	
	//sitting
	if($result[$i]['activityId']==3){//sitting
	
		$sittingtime=$sittingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==8){//standing
		$standingtime=$standingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==2){//Walking
		$walkingtime=$walkingtime+$result[$i]['duration'];
	
	}else if($result[$i]['activityId']==1){//running
		$runningtime=$runningtime+$result[$i]['duration'];
	
	} 



}

//print $sittingtime." ".$standingtime." ".$walkingtime."  ".$runningtime;

?>
<script>
var chart = AmCharts.makeChart( "chartdiv", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {
    "activity": "Sitting",
    "Seconds": <?php echo $sittingtime;?>
  }, {
    "activity": "Standing",
    "Seconds": <?php echo $standingtime/600;?>
  }, {
    "activity": "Walking",
    "Seconds": <?php echo $walkingtime;?>
  }, {
    "activity": "Running",
    "Seconds": <?php echo $runningtime;?>
  } ],
  "valueField": "Seconds",
  "titleField": "activity",
   "balloon":{
   "fixedPosition":true
  },
  "export": {
    "enabled": true
  }
} );

</script>