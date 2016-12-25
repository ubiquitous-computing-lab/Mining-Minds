<?php
include_once("functions.php");
//$uid=35;



class Nutrition{




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
		 


		public function nutritionJS() {	





						$uid=$_GET['userid'];
						$nutritiondata=array();$week=29;
						$nutritiondataweekly=array();
						$weekcounter=0;
						
						
						/*********************************************************************************************
						
						Create Empty nutrition monthly and weekly arrays with dates
						**********************************************************************************************/
						
						
						for($i=0;$i<30;$i++){
						
							$starttimeobject = new DateTime('-'.$week.' days');
							//print $starttimeobject->format('Y m d');
							$nutritiondata[$i]["date"]=$starttimeobject->format('Y-m-d');
							$nutritiondata[$i]["totalCarbohydrate"]=0;
							$nutritiondata[$i]["totalProtein"]=0;
							$nutritiondata[$i]["totalFat"]=0;
							//$nutritiondata[$i]["foodName"]='';
							
							if($week<7){
								$nutritiondataweekly[$weekcounter]["date"]=$starttimeobject->format('Y-m-d');
								$nutritiondataweekly[$weekcounter]["totalCarbohydrate"]=0;
								$nutritiondataweekly[$weekcounter]["totalFat"]=0;
								$nutritiondataweekly[$weekcounter]["totalProtein"]=0;
								//$nutritiondataweekly[$i]["foodName"]='';
						
								$weekcounter++;
							
							}
							
							$week--;
							
							
							
							
						}
						
						/*********************************************************************************************
						
						Call the service with last 30 days
						**********************************************************************************************/
						
						
												$starttimeobject = new DateTime('-29 days');
												//$starttimeobject = new DateTime('2015-05-16');
												$starttimeobject->setTime(00,00,00);
												
												//set end time today
												$endtimeobject = new DateTime('NOW');
												$endtimeobject->setTime(23,59,59);
												
												//convert date objects into strings
												$start = $starttimeobject->format('Y m d H:i:s');
												$end = $endtimeobject->format('Y m d H:i:s');
												
												
												
												//assign userid
												//$uid=35;
												
												//create object and get data from DCL
												$data = array(
													
															"userId"=>$uid,
															"startTime"=>$start,
															"endTime"=>$end
													
													);
						
						
						
						
												global $dclserver;
												$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetFoodLogByUserIDDateRange";
												
												
												
														
														
												$json = json_encode($data,true);
															
												$result =$this->postJsonRest($url,$json);		
												$result=json_decode($result,true);	
												//echo '<pre>';
												//print_r($result);

				/*********************************************************************************************
				
				Resultset with 30 days data
				**********************************************************************************************/
				
				
										
										$foodNameMonthly=array();
										$foodNameWeekly=array();
										
										
										for($i=0;$i<count($result);$i++){
										
										
												//get food name for monthly
												$foodNameMonthly[$i]=$result[$i]['foodName'];
												
												
												//get food name for weekly
				
												if( strtotime($result[$i]['eatingTime']) > strtotime('-6 day')) {
														$foodNameWeekly[$i]=$result[$i]['foodName'];
														//echo $result[$i]['foodName'];
														
														
														}
												
												//get index of the result set which date/time user has eaten food for monthly array
												$key = array_search(substr($result[$i]['eatingTime'],0,10), array_column($nutritiondata, 'date'));
				
				
												//populate that index with protein carb for month
				
												$nutritiondata[$key]["totalProtein"]=$nutritiondata[$key]["totalProtein"]+$result[$i]['totalProtein'];
												$nutritiondata[$key]["totalFat"]=$nutritiondata[$key]["totalFat"]+$result[$i]['totalFat'];
												$nutritiondata[$key]["totalCarbohydrate"]=$nutritiondata[$key]["totalCarbohydrate"]+$result[$i]['totalCarbohydrate'];
												
												//get index of the result set which date/time user has eaten food for week array
				
												
												$keyweekly = array_search(substr($result[$i]['eatingTime'],0,10), array_column($nutritiondataweekly, 'date'));
												
												//populate that index with protein carb for month
												$nutritiondataweekly[$keyweekly]["totalProtein"]=$nutritiondataweekly[$keyweekly]["totalProtein"]+$result[$i]['totalProtein'];
												$nutritiondataweekly[$keyweekly]["totalFat"]=$nutritiondataweekly[$keyweekly]["totalFat"]+$result[$i]['totalFat'];
												$nutritiondataweekly[$keyweekly]["totalCarbohydrate"]=$nutritiondataweekly[$keyweekly]["totalCarbohydrate"]+$result[$i]['totalCarbohydrate'];
												
												
										}
										
										
										
										
										//create unique array of fooditems eaten in the month
										$foodNameMonthly = array_unique($foodNameMonthly);
										$foodNameMonthly = array_values($foodNameMonthly);
										
										//create unique array of fooditems eaten in the week
										$foodNameWeekly = array_unique($foodNameWeekly);
										$foodNameWeekly = array_values($foodNameWeekly);
										
										
										
										
										
										//create empty array of food and its total breakdown of nutrients in a month
										
										$breakdownmonthly=array();
										$breakdownweekly=array();
										for($i=0;$i<count($foodNameMonthly);$i++){
														
														
													$breakdownmonthly[$i]['foodName']=$foodNameMonthly[$i];
													$breakdownmonthly[$i]['totalFat']=0;
													$breakdownmonthly[$i]['totalCarbohydrate']=0;
													$breakdownmonthly[$i]['totalProtein']=0;
													
													
																					
													
											
											}
										
										
											//create empty array of food and its total breakdown of nutrients in a week
										for($i=0;$i<count($foodNameWeekly);$i++){
														
													$breakdownweekly[$i]['foodName']=$foodNameWeekly[$i];
													$breakdownweekly[$i]['totalFat']=0;
													$breakdownweekly[$i]['totalCarbohydrate']=0;
													$breakdownweekly[$i]['totalProtein']=0;
														
													
											
											}
											
														
										
										for($i=0;$i<count($result);$i++){
										
										
												//find index of food present in monthly and add all the nutrients of that food in the month
										
												$key = array_search($result[$i]['foodName'], array_column($breakdownmonthly, 'foodName'));
												$breakdownmonthly[$key]['totalProtein']=$breakdownmonthly[$key]['totalProtein']+$result[$i]['totalProtein'];
												$breakdownmonthly[$key]['totalCarbohydrate']=$breakdownmonthly[$key]['totalCarbohydrate']+$result[$i]['totalCarbohydrate'];
												$breakdownmonthly[$key]['totalFat']=$breakdownmonthly[$key]['totalFat']+$result[$i]['totalFat'];
												
												
												
												//find index of food present in week and add all the nutrients of that food in the week
												$keyweekly = array_search($result[$i]['foodName'], array_column($breakdownweekly, 'foodName'));
												if( strtotime($result[$i]['eatingTime']) > strtotime('-6 day') ) {
												
												
														$breakdownweekly[$keyweekly]['totalProtein']=$breakdownweekly[$keyweekly]['totalProtein']+$result[$i]['totalProtein'];
														$breakdownweekly[$keyweekly]['totalCarbohydrate']=$breakdownweekly[$keyweekly]['totalCarbohydrate']+$result[$i]['totalCarbohydrate'];
														$breakdownweekly[$keyweekly]['totalFat']=$breakdownweekly[$keyweekly]['totalFat']+$result[$i]['totalFat'];
												
												}
												
												
										}
						
				


?>

<script>
AmCharts.makeChart("nutritionmonthly",
				{
					"type": "serial",
					<!--"pathToImages": "http://cdn.amcharts.com/lib/3/images/",-->
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD",
					"categoryAxis": {
						"autoRotateCount": 0,
						"parseDates": true,
						"startOnAxis": true
					},
					"chartCursor": {},
					<!--"chartScrollbar": {},-->
					"trendLines": [
						{
							"finalValue": 7,
							"id": "TrendLine-1",
							"initialValue": 7,
							"lineAlpha": 0.43,
							"lineColor": "#CC0000",
							"lineThickness": 3
						}
					],
					"graphs": [
					
						{
							"bullet": "round",
							"id": "AmGraph-1",
							"title": "Carbohydrate",
							"valueField": "Carbohydrate"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Protein",
							"valueField": "Protein"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Fat",
							"valueField": "Fat"
						}
						
						
						
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "durations",
							"title": "Calories"
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					"dataProvider": [
					
					
					
					
					<?php for($i=0;$i<count($nutritiondata);$i++){?>
					
					{
					
						"date": "<?php echo $nutritiondata[$i]["date"];?>",
						
						            "Carbohydrate": <?php echo $nutritiondata[$i]["totalCarbohydrate"];?>,
									"Protein": <?php echo $nutritiondata[$i]["totalProtein"];?>,
									"Fat": <?php echo $nutritiondata[$i]["totalFat"];?>
									
                                   
                                    
									
                                    
						  },										
					<?php }?>]
				}
			);					
					
					
AmCharts.makeChart("nutritionweekly",
				{
					"type": "serial",
					<!--"pathToImages": "http://cdn.amcharts.com/lib/3/images/",-->
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD",
					"categoryAxis": {
						"autoRotateCount": 0,
						"parseDates": true,
						"startOnAxis": true
					},
					"chartCursor": {},
					<!--"chartScrollbar": {},-->
					"trendLines": [
						{
							"finalValue": 7,
							"id": "TrendLine-1",
							"initialValue": 7,
							"lineAlpha": 0.43,
							"lineColor": "#CC0000",
							"lineThickness": 3
						}
					],
					"graphs": [
					
						{
							"bullet": "round",
							"id": "AmGraph-1",
							"title": "Carbohydrate",
							"valueField": "Carbohydrate"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Protein",
							"valueField": "Protein"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Fat",
							"valueField": "Fat"
						}
						
						
						
						
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "durations",
							"title": "Minutes"
						}
					],
					"allLabels": [],
					"balloon": {},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					"dataProvider": [
					
					
					
					
					<?php for($i=0;$i<count($nutritiondataweekly);$i++){?>
					
					{
					
									"date": "<?php echo $nutritiondataweekly[$i]["date"];?>",
									"Carbohydrate": <?php echo $nutritiondataweekly[$i]["totalCarbohydrate"];?>,
									"Protein": <?php echo $nutritiondataweekly[$i]["totalProtein"];?>,
									"Fat": <?php echo $nutritiondataweekly[$i]["totalFat"];?>
									
                                   
                                    
									
                                    
						  },										
					<?php }?>]
				}
			);					
					
					


var chart = AmCharts.makeChart( "breakdownweekly", {
  "type": "serial",
  "theme": "light",
  "depth3D": 20,
  "angle": 30,
  "legend": {
    "horizontalGap": 10,
    "useGraphSettings": true,
    "markerSize": 10
  },
  "dataProvider": [ 
  
  ﻿ <?php
  
  		for($i=0;$i<count($breakdownweekly);$i++){
		
		echo '{"Word":"'.$breakdownweekly[$i]['foodName'].'","Protein":'.$breakdownweekly[$i]['totalProtein'].',"Fat":'.$breakdownweekly[$i]['totalFat'].',"Carbohydrate":'.$breakdownweekly[$i]['totalCarbohydrate'].'},';
			
		
		}
  
  
  ?>
	  
   ],
  "valueAxes": [ {
    "stackType": "regular",
    "axisAlpha": 0,
    "gridAlpha": 0
  } ],
  "graphs": [  {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Protein",
    "type": "column",
    "color": "#000000",
    "valueField": "Protein"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Fat",
    "type": "column",
    "color": "#000000",
    "valueField": "Fat"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Carbohydrate",
    "type": "column",
    "color": "#000000",
    "valueField": "Carbohydrate"
  } ],
  "categoryField": "Word",
  "categoryAxis": {
    "gridPosition": "start",
    "axisAlpha": 0,
    "gridAlpha": 0,
    "position": "left"
  },
  "export": {
    "enabled": true
  }

} );

var chart = AmCharts.makeChart( "breakdownmonthly", {
  "type": "serial",
  "theme": "light",
  "depth3D": 20,
  "angle": 30,
  "legend": {
    "horizontalGap": 10,
    "useGraphSettings": true,
    "markerSize": 10
  },
  "dataProvider": [ 
  
  <?php
  
  		for($i=0;$i<count($breakdownmonthly);$i++){
		
		echo '{"Word":"'.$breakdownmonthly[$i]['foodName'].'","Protein":'.$breakdownmonthly[$i]['totalProtein'].',"Fat":'.$breakdownmonthly[$i]['totalFat'].',"Carbohydrate":'.$breakdownmonthly[$i]['totalCarbohydrate'].'},';
			
		
		}
  
  
  ?>
  
  	  
   ],
  "valueAxes": [ {
    "stackType": "regular",
    "axisAlpha": 0,
    "gridAlpha": 0
  } ],
  "graphs": [  {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Protein",
    "type": "column",
    "color": "#000000",
    "valueField": "Protein"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Fat",
    "type": "column",
    "color": "#000000",
    "valueField": "Fat"
  }, {
    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
    "fillAlphas": 0.8,
    "labelText": "[[value]]",
    "lineAlpha": 0.3,
    "title": "Carbohydrate",
    "type": "column",
    "color": "#000000",
    "valueField": "Carbohydrate"
  } ],
  "categoryField": "Word",
  "categoryAxis": {
    "gridPosition": "start",
    "axisAlpha": 0,
    "gridAlpha": 0,
    "position": "left"
  },
  "export": {
    "enabled": true
  }

} );

</script>
<?php
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