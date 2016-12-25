<?php				












class Recommendation {


							private $data = array();
							private $rec;
						
						
						
						public function __construct($rec="")
						   {
							   $this->rec = $rec;
						   }
						
												
						
						
						
						
						
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
			
		
							public function timeline(){
								
								$todayStart =new DateTime('2016-05-14');
								$todayEnd =new DateTime('2016-05-14');
				
								
								//$todayStart = new DateTime('NOW');
								//$todayEnd = new DateTime('NOW');
																
																//for static dates uncomment
								$start =new DateTime('2016-05-11');
								$end = new DateTime('2016-05-11');
								
								
								//for dynamic dates uncomment
								//$start = new DateTime('now');
								//$end = new DateTime('now');
								
								
								//$uid=39;
								$uid=$_GET['userid'];
								$start->setTime(8,00,00);
								$end->setTime(18,59,59);
								
								
								
								
								
								$todayStart->setTime(00, 00,00);
								$todayEnd->setTime(23, 59,59);
								
								$reference=$todayStart;
								for($i=0;$i<6;$i++){
								
								
									$previousStart=$reference;
									
									$previousStart->setTime(00,00,00);
									$start=$previousStart->format('Y m d H:i:s');
									$previousStart->setTime(23, 59,59);
									$end=$previousStart->format('Y m d H:i:s');
									 
									 echo "<h2>". $previousStart->format('Y-m-d')." </h2>";
									 
									 									
									?>
									
									 <div  class="panel-group">
											<?php 
											
									 $this->timelinerow($uid,$start,$end);
											
											
										?>
										
										
									  </div>
									
									<?php
									//echo $previousStart->format('Y-m-d H:i:s') . " <br>";
									$previousStart->sub(new DateInterval('P1D'));
					
					}
					
											

					
					
				



						
								
				}		
				
					
					public function timelinerow($uid,$startdate,$enddate){


					
					//print_r(getRecommendation(39,'2015 05 04 00:30:01','2015 05 04 23:30:01'));
					//$result=json_decode(getRecommendation(39,'2015 05 04 00:30:01','2015 05 04 23:30:01'),true);
					$result=json_decode( $this->getRecommendation($uid,$startdate,$enddate),true);
					//echo '<pre>';print_r($result);
					//[recommendationDate] => 2015-05-20 13:50:59.0
					
					
					$desc=count($result)-1;
					if(count($result))
					{
						 for($i=$desc;$i>-1;$i--){
							//for($i=0;$i<count($result);$i++){
					
					
						?>         
															<div class="panel panel-default">
																					  <div class="panel-heading">
																				  <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#plus_<?php echo $result[$i]['recommendationID'];?>"><strong>Time:</strong> <?php  if(isset($result[$i]['recommendationDate']))echo substr($result[$i]['recommendationDate'],10,6);?> </br><strong>Recommendation</strong>:  <?php  if(isset($result[$i]['recommendationDescription'])) echo $result[$i]['recommendationDescription'];?></a> </h4>
																					  </div>
																					  <div id="plus_<?php echo $result[$i]['recommendationID'];?>" class="panel-collapse collapse">
																						<div class="panel-body"><strong>Reason</strong>:  <?php  echo  $this->getsituationDescription($result[$i]['situationID']);?></div>
																						 <div class="panel-body"><strong>Feedback</strong>:  <?php  echo  $this->getRecommendationFeedbackbyID($result[$i]['recommendationID']);?></div>
																					  </div>
																					</div>
											
											<?php
												}//for loop
												}//if cond
												
												else{
												
																		   
															?>
															
															<div class="panel panel-default">
														  <div class="panel-heading">
															<h4 class="panel-title"> <a  data-parent="#accordion" href="#plus_"> <strong>No Recommendations</strong>:  </a> </h4>
														  </div>
														 
														</div>
															
												<?php
												
												}
					
					}
					
					
					
					 
					/*********************************************************************************************
					
					Get Recommendation by user id, start time and end time
					
					**********************************************************************************************/
					
									public function getRecommendation($uid,$startdate,$enddate){
													
													global $dclserver;
														$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/RetrieveRecommendationByUserIDDate";
														
													
													
														$data = array("userID"=>$uid,
														"startDate"=>$startdate,
														"endDate"=>$enddate,
															
															);
															
														//print_r($data);
														$json = json_encode($data,true);
														
														$result =$this->postJsonRest($url,$json);
														
														return $result;
													
													
													}
					
					
					/*********************************************************************************************
					
					get situation Description by Situation id
					**********************************************************************************************/
														
														public function getsituationDescription($sid){
														
															global $dclserver;
															$url="$dclserver/MMDataCurationRestfulService/webresources/ServiceCuration/SituationBySituationID/$sid";
															$situationJson=$this->getJsonRest($url);
															
															//$data = array("userId" => 2, "startTime" => "2014 05 02 02:30:01", "endTime" => "2015 05 02 02:45:49");
															//rayemahmood@oslab.khu.ac.kr,12345
																
															//print_r($situationJson[0]['situationDescription']);
															
															if(isset($situationJson[0]['situationDescription']))
																$situationDescription=$situationJson[0]['situationDescription'];
															if(isset($situationJson[0]['situationCategoryDescription']))
																$situationCategoryDescription=$situationJson[0]['situationCategoryDescription'];	
																
																
															return $situationDescription. " ".$situationCategoryDescription;
														
														
														}
					
					
					

					
					
/*********************************************************************************************

Get Recommendation Feedback by RecommendationID

**********************************************************************************************/
						
						//getRecommendationFeedbackbyID(4);
						public function getRecommendationFeedbackbyID($recID){
						
							global $dclserver;
						
							//print "Rec ID is ".$recID;
							$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/RecommendationFeedback/$recID";
							$recommendationFeedbackJson=$this->getJsonRest($url);
							
							if(count($recommendationFeedbackJson)>0)
							
									if($recommendationFeedbackJson[0]['rate']==1)
										print "I liked the Recommendation. ". $recommendationFeedbackJson[0]['reason'];
									else if($recommendationFeedbackJson[0]['rate']==0)
										print "I did not like the Recommendation.". $recommendationFeedbackJson[0]['reason'];
							
						
						
						}
						












							/*********************************************************************************************
							
							function to post json to a rest service
							
							
							**********************************************************************************************/
							
							
							public function postJsonRest($url,$json){
							
									$client=curl_init($url);
									
									curl_setopt($client, CURLOPT_POSTFIELDS, $json);
									curl_setopt($client, CURLOPT_RETURNTRANSFER, true);
									
									
									
									curl_setopt($client, CURLOPT_HTTPHEADER, array(
									'Content-Type: application/json',
									'Content-Length: ' . strlen($json))
								);
								
								
									curl_setopt($client, CURLOPT_TIMEOUT, 5);
									curl_setopt($client, CURLOPT_CONNECTTIMEOUT, 5);
								
									
									$response=curl_exec($client);
									//$result=json_decode($response,true);
							//		print $response;
									curl_close($client);
									return $response;
								
							}
							
							
							/*********************************************************************************************
							
							
							function to get json from a rest service
							
							
							**********************************************************************************************/
							
							public function getJsonRest($url){
							
									$client=curl_init($url);
									
								
									//for get
									curl_setopt($client,CURLOPT_RETURNTRANSFER,1);
									
										
									//response from url
									$response=curl_exec($client);
									$result=json_decode($response,true);
									curl_close($client);
									return $result;
							
							}

				
				}
							
					
					
                    
                    
                    
                     
                 











