<?php
include_once("functions.php");

$locationdata=calculateweeklylocation(29,$uid);			
			
//echo "<pre> hello";
//
//	print_r($locationdata);


?>
<script>
AmCharts.makeChart("monthlylocationchartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD",
					"categoryAxis": {
						"autoRotateCount": 0,
						"parseDates": true,
						"startOnAxis": true
					},
					"chartCursor": {},
					
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
							"id": "AmGraph-11",
							"title": "Home",
							"valueField": "Home"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-12",
							"title": "Office",
							"valueField": "Office"
						},
						{
							"bullet": "round",
							"id": "AmGraph-13",
							"title": "Yard",
							"valueField": "Yard"
						},{
							"bullet": "round",
							"id": "AmGraph-14",
							"title": "Gym",
							"valueField": "Gym"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-15",
							"title": "Mall",
							"valueField": "Mall"
						},
						{
							"bullet": "round",
							"id": "AmGraph-16",
							"title": "Restaurant",
							"valueField": "Restaurant"
						},{
							"bullet": "round",
							"id": "AmGraph-17",
							"title": "Outdoors",
							"valueField": "Outdoors"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-18",
							"title": "Transport",
							"valueField": "Transport"
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
										
					<?php for($i=0;$i<count($locationdata);$i++){?>
					
					{
					


						"date": "<?php echo $locationdata[$i]["date"];?>",
						
						            "Home": <?php echo $locationdata[$i]["Home"]/60;?>,
									"Office": <?php echo $locationdata[$i]["Office"]/60;?>,
									"Yard": <?php echo $locationdata[$i]["Yard"]/60;?>,
									"Gym": <?php echo $locationdata[$i]["Gym"]/60;?>,
									"Mall": <?php echo $locationdata[$i]["Mall"]/60;?>,
									"Restaurant": <?php echo $locationdata[$i]["Restaurant"]/60;?>,
									"Outdoors": <?php echo $locationdata[$i]["Outdoors"]/60;?>,
									"Transport": <?php echo $locationdata[$i]["Transport"]/60;?>,
									
									
                                   
                                    
									
                                    
						  },										
					<?php }?>
					
											
					
					
									                           
					]
				}
			);					
					
					





</script>




 
 
 
 