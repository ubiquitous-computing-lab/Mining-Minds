<?php 

//set start time 60 days ago
$starttimeobject = new DateTime('-60 days');
$starttimeobject->setTime(00,00,00);

//set end time today
$endtimeobject = new DateTime('NOW');
$endtimeobject->setTime(23,59,59);

//convert date objects into strings
$start = $starttimeobject->format('Y m d H:i:s');
$end = $endtimeobject->format('Y m d H:i:s');	


$data = array(
		
					"userId"=>0,
				"startTime"=>$start,
				"endTime"=>$end 
		
		);




//print_r($data);
class Demo {
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

$d = new Demo;
// Set a non-existent variable
$d->userId = 0;
$d->startTime = $start;
$d->endTime = $end;

print_r($d);








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
// Set a non-existent variable
$dashboardData->userId = 0;
$dashboardData->startTime = $start;
$dashboardData->endTime = $end;

print_r($dashboardData);

exit;

// Get what we just stored
//echo $d->startTime;
// Get a non-existant variable
//echo $d->testFail;
?>