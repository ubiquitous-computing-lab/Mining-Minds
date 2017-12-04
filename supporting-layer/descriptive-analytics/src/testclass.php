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