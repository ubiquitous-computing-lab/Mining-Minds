                    
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
