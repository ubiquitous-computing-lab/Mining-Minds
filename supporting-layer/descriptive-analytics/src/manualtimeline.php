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
					
					
						$startdate='2015 05 04 00:30:01';
						$enddate='2015 05 04 23:30:01';
						$startdate1='2015 05 05 00:30:01';
						$enddate1='2015 05 05 23:30:01';
						$startdate2='2015 05 06 00:30:01';
						$enddate2='2015 05 06 23:30:01';

								
								
					
					?>
                    
                    
                    
                     
                      <h2><?php echo substr($startdate,0,10);?> </h2>
                      <div  class="panel-group">
							<?php 
							
							timelinerow($startdate,$enddate);
							
							
                        ?>
                        
                        
                      </div>
                                            <h2><?php echo substr($startdate1,0,10);?> </h2>

                     <div  class="panel-group">
							<?php 
							
							timelinerow($startdate1,$enddate1);
						
							
                        ?>
                        
                        
                      </div>
                                            <h2><?php echo substr($startdate2,0,10);?> </h2>

                        <div  class="panel-group">
							<?php 
							
							timelinerow($startdate2,$enddate2);
							
                        ?>
                        
                        
                      </div>
