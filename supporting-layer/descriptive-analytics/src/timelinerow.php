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

?><div class="timeline-inner-content">
<?php
include "functions.php"; 

					

			$divFlag=11;
			$j=0;
			$start = new DateTime('');
			$end = new DateTime('+3 days');
			$interval = new DateInterval('P1D');
			$period = new DatePeriod($start, $interval, $end);
			
				foreach ($period as $dt) {
				if ($dt->format("N") === 7) {
				
					
					$end->add(new DateInterval('P1D'));
				}
				else  {
					
				//	if(isset($_POST['userID']))
						$result=json_decode(getRecommendation ('','',''),true);
			
			?>   
                
                
                
                
					       
                				
                      <h2><?php echo $dt->format("l Y-m-d");?></h2>
                      <div  class="panel-group">
                      
                        <?php for($i=0;$i<count($result);$i++){?>
                      
                      <div class="panel panel-default">
                          <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="<?php echo $divFlag;?>"> <strong>Recommendation</strong>:  <?php if(issset($result[$i]['recommendationDescription'])) echo $result[$i]['recommendationDescription']; else echo 'No recommendation';?> </a> </h4>
                          </div>
                          <div id="<?php echo $divFlag; $divFlag++; ?>" class="panel-collapse collapse">
                            <div class="panel-body"><strong>Reason</strong>:  <?php  echo getsituationDescription($result[$i]['situationID'])?></div>
                          </div>
                      
                      <?php
                
					}//for loop
					
					
					
				
					}//else loop
					
			}//foreach loop
			
				
				?>
				</div>