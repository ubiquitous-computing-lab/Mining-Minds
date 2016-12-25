<div class="timeline-inner-content">
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