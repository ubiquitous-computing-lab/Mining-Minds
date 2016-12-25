<?php 

class Feedback{


		var $name;
		public function __construct($name) {		
			$this->name = $name;		
		}		
 
		public function set_name($new_name) {
		 	 $this->name = $new_name;
		}	
 
		public function get_name() {		
		 	 return $this->name;		
		 }		
		 
		 
		 
		public function feedbackJS(){
		 
		 		$uid=$_GET['userid'];
				$sentiment=$this->getRecommendationFeedbackbyUID($uid);		 
		 
		 
		 ?>
		 
         <script>
 
 AmCharts.makeChart("feedback_recommendations_chartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "category",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,						
							"title": "Negative",
							"type": "column",
							"valueField": "Negative"
						},
						{
							"balloonText": "[[title]] of [[category]]:[[value]]",
							"fillAlphas": 1,
							"title": "Positive",
							"type": "column",
							"valueField": "Positive"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "rec_feedback",
							"stackType": "regular",
							"labelRotation":"3",
							"title": ""
							
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						"periodValueText": "Total : [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
						
						{
							"category": "LyingDown",
							"Negative": <?php echo 	$sentiment["LyingDownnegative"];?>,
							"Positive": <?php echo 	$sentiment["LyingDownpositive"];?>
						},{
							"category": "Sitting",
							"Negative": <?php echo 	$sentiment["sittingnegative"];?>,
							"Positive": <?php echo 	$sentiment["sittingpositive"];?>
						},{
							"category": "Standing",
							"Negative": <?php echo 	$sentiment["standingnegative"];?>,
							"Positive": <?php echo $sentiment["standingpositive"];	?>
						},{
							"category": "Walking",
							"Negative": <?php echo 	$sentiment["walkingnegative"];?>,
							"Positive": <?php echo 	$sentiment["walkingpositive"];?>
						},{
							"category": "Jogging",
							"Negative": <?php echo 	$sentiment["joggingnegative"];?>,
							"Positive": <?php echo 	$sentiment["joggingpositive"];?>
						},{
							"category": "Running",
							"Negative": <?php echo 	$sentiment["runningnegative"];?>,
							"Positive": <?php echo 	$sentiment["runningpositive"];?>
						}
					]
				}
			);
 
 
  
 
 
 </script>
         
         
		 <?php
		 
		 
		 }
		 
		 
		 
 /*********************************************************************************************

Get Recommendation Feedback by User ID
**********************************************************************************************/
//getRecommendationFeedbackbyUserID(39);
				public function getRecommendationFeedbackbyUID($uid){
				
				
						$sentiment = array(
									"sittingpositive" => 0,
									"sittingnegative" => 0,
									"standingpositive"=> 0,
									"standingnegative"=> 0,
									"walkingpositive" => 0,
									"walkingnegative" => 0,
									"runningpositive" => 0,
									"runningnegative" => 0,
									"LyingDownpositive" => 0,
									"LyingDownnegative" => 0,
									"joggingpositive" => 0,
									"joggingnegative" => 0
								);		
				
						global $dclserver;
						$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/RecommendationFeedbackByUserID/$uid";
						$getRecommendationFeedbackbyUserID=$this->getJsonRest($url);
				
						$positive=0;
						$negative=0;
						for($i=0;$i<count($getRecommendationFeedbackbyUserID);$i++){
				
							
							
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
								$sentiment["sittingpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
								$sentiment["sittingnegative"]++;
								
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
								$sentiment["standingpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
								$sentiment["standingnegative"]++;
								
								
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
								$sentiment["walkingpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
								$sentiment["walkingnegative"]++;		
				
				
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
								$sentiment["runningpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
								$sentiment["runningnegative"]++;	
							
							
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
								$sentiment["LyingDownpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
								$sentiment["LyingDownnegative"]++;	
								
								
								
							if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
								$sentiment["joggingpositive"]++;
							else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
								$sentiment["joggingnegative"]++;					
				}
					
						
						
					return $sentiment;
					
				
				
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






?>



 