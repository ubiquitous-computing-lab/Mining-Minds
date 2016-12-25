<?php
		session_start(); 
		
	
	/*if(!$_SESSION['user_id']){ 
		header("Location: index.php"); 
		exit; 
		
		}
		
	if (isset($_POST['addreview'])) {
	
		header("Location: index.php"); 

	} 
	*/
	include "functions.php"; 
	
	
	if(isset($_GET['userid'])){
		$uid=$_GET['userid'];
		$userJson=retrieveUserByUserID($uid);
		
	}	
	
	else
			header("Location: users.php");

//for static dates uncomment
$start =new DateTime('2015-12-01');
$end = new DateTime('2015-12-20');


//for dynsmaic dates uncomment
//$start = new DateTime('now');
//$end = new DateTime('now');


//$uid=39;
$start->setTime(8,00,00);
$end->setTime(18,59,59);

//$starttime = DateTime::CreateFromFormat("Y m d H:i:s", $start);
//$endtime = DateTime::CreateFromFormat("Y m d H:i:s", $end);
	

$movement=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);

$start = new DateTime('-6 days');
$start->setTime(00,00,00);

$movementweekly=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);

$start = new DateTime('-29 days');

$start->setTime(00,00,00);
$movementmonthly=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);

$starttime = new DateTime('2015-12-01');
//$starttime =  new DateTime('now');


?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mining Minds Expert View- User</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- favicon -->

<!-- bootstrap framework -->
<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- icon sets -->
<!-- elegant icons -->
<link href="assets/icons/elegant/style.css" rel="stylesheet" media="screen">
<!-- elusive icons -->
<link href="assets/icons/elusive/css/elusive-webfont.css" rel="stylesheet" media="screen">
<!-- flags -->
<link rel="stylesheet" href="assets/icons/flags/flags.css">
<!-- scrollbar -->
<link rel="stylesheet" href="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
<link href="assets/lib/ion.rangeSlider/css/ion.rangeSlider.css" rel="stylesheet" media="screen">
<link href="assets/lib/ion.rangeSlider/css/ion.rangeSlider.skinFlat.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="assets/lib/charts/chartist.min.css">

<!-- uplaoder -->
<!-- google webfonts -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>

<!-- main stylesheet -->
<link href="assets/css/main.min.css" rel="stylesheet" media="screen" id="mainCss">

 <!-- jBox -->
 <link href="assets/lib/jBox-0.3.0/Source/jBox.css" rel="stylesheet" media="screen">
 <link href="assets/lib/jBox-0.3.0/Source/themes/NoticeBorder.css" rel="stylesheet" media="screen">

<!-- moment.js (date library) -->
<script src="assets/js/moment-with-langs.min.js"></script>
<script>


function myFunction() {
// alert($("#wysiwg_editor").val());
var value =document.getElementById("wysiwg_editor").value;
$.ajax({
        type: "GET",
        url: 'addExpertReview.php',
        data: "review="+value+"&userID=<?php echo $uid;?>&expertID=<?php echo $uid;?>",
        success: function(response) {
		
		  
        }
    });
   // alert("I am an alert box!");
    document.getElementById("wysiwg_editor").value='';
   $( "#review" ).hide();
 //  document.getElementById("review").hide();
   
   
}

		/* $(function(){

$( ".link" ).click(function( event ) {

alert("outside");
});
});*/
</script>
</head>
<body class="side_menu_active side_menu_expanded">
<div id="page_wrapper"> 
  
  <!-- header --><!-- breadcrumbs -->
  
    <?php //include "header.php";?>
  <!-- main content -->
  <div id="main_wrapper">
    <div class="container-fluid">
    
   <?php include "uppermenu.php";?>
    
    
      <div class="row">
			<?php     if(isset($_GET['notify'])){
			
			
					
			
			
			?>
        <div class="alert alert-danger">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong> <?php echo $printalert;?></strong>
  </div>
  <?php }?>
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-5"> <img width="76" height="76" alt="" src="members/<?php if($userJson[0]['userID'])echo $userJson[0]['userID'].".jpg"; else echo "default.png";?>" class="user_profile_img"> <br />
              <h1 class="user_profile_name"><?php echo $userJson[0]['firstName']." ".$userJson[0]['lastName'];?> </h1>
              <button class="btn btn-sm btn-info" data-toggle="modal" data-target="#review">Send a message</button>
             
               <br class="clearfix" />
             
              <br class="clearfix" />
              <div class="timeline">
     
              
                <div class="timeline-row">
                 
                  <div class="timeline-icon">
                    <div class="bg-info"> <i class="el-icon-pencil"></i> </div>
                  </div>
                  
                  <div class="timeline-content">
                   
                    <div class="timeline-inner-content">
                    
                    
							<?php 	//include "dynamictimeline.php";  ?>	

                    </div>
                  </div>
                  
                </div>
                <div class="timeline-row">
                  
                  <div class="timeline-icon">
                    <div class="bg-danger"> <i class="el-icon-comment"></i> </div>
                  </div>
                  <div class="timeline-content">
                    
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-7">
             <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#activity">Activity</a></li>
                <li><a data-toggle="tab" role="tab" href="#Locations">Locations </a></li>
                 <li><a data-toggle="tab" role="tab" href="#Nutrition">Nutrition</a></li>
<!--                <li><a data-toggle="tab" role="tab" href="#feedback">Feedback</a> </li>
-->                 <li><a data-toggle="tab" role="tab" href="#HLC">High Level Context</a> </li>
              </ul>
            
            <div class="tab-content">
            
            <div id="activity" class="tab-pane fade in active">
              <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#day">Day</a></li>
                <li><a data-toggle="tab" role="tab" href="#week">Week</a></li>
                <li><a data-toggle="tab" role="tab" href="#month">Month</a> </li>
              </ul>
              <div class="tab-content">
                <div id="day" class="tab-pane fade in active">
                  <ul role="tablist" class="cat_tab">
                    <li class="active">
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="<?php  $actAmount=activityCal($movement["totalAmtWalk"],$movement["totalAmtJogging"],$movement["totalAmtRunning"],1);echo $actAmount["activityPercent"];?>"> <span class="easy_chart_percent"><?php echo $actAmount["activityAcheivement"];?> min</span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Amount of Activity</h4>
                          </div>
                        </div>
                      </div>
                      </li>
                    <li >
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="<?php $metMinutesCal=metMinutesCal($movement["totalAmtWalk"],$movement["totalAmtJogging"],$movement["totalAmtRunning"],1);echo $metMinutesCal["metsMinutesPercent"];?>"> <span class="easy_chart_percent"><?php echo $metMinutesCal["metsMinutesAcheivement"];?> </span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">MET Minutes <br />
                              </h4>
                          </div>
                        </div>
                      </div>
                     </li>
                    <li>
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_c easy_chart" data-percent="<?php $calBurnedCal=calBurnedCal($uid,$movement["totalAmtWalk"],$movement["totalAmtJogging"],$movement["totalAmtRunning"],1);echo $calBurnedCal["kcalAcheivementPercent"];?>"> <span class="easy_chart_percent"><?php echo $calBurnedCal["kcalAcheivement"];?></span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Calories Burned</h4>
                          </div>
                        </div>
                      </div>
                      </li>
                  </ul>
                  <div class="tab-content"> <br class="clearfix" />
                    <div id="activity" class="tab-pane fade in active">
                      <div id="dailychartdiv" style="width: 100%; height: 500px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    
                    
                    
                  </div>
                </div>
                <div id="week" class="tab-pane fade in">
                
                  <ul role="tablist" class="cat_tab">
                    <li class="active">
                     <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="<?php  $actAmount=activityCal($movementweekly["totalAmtWalk"],$movementweekly["totalAmtJogging"],$movementweekly["totalAmtRunning"],7);echo $actAmount["activityPercent"];?>"> <span class="easy_chart_percent"><?php echo $actAmount["activityAcheivement"];?></span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Amount of Activity</h4>
                          </div>
                        </div>
                      </div>
                     </li>
                    <li >
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="<?php $metMinutesCal=metMinutesCal($movementweekly["totalAmtWalk"],$movementweekly["totalAmtJogging"],$movementweekly["totalAmtRunning"],7);echo $metMinutesCal["metsMinutesPercent"];?>"> <span class="easy_chart_percent"><?php echo $metMinutesCal["metsMinutesAcheivement"];?> </span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">MET Minutes <br />
                              </h4>
                          </div>
                        </div>
                      </div>
                     </li>
                    <li>
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_c easy_chart" data-percent="<?php $calBurnedCal=calBurnedCal($uid,$movementweekly["totalAmtWalk"],$movementweekly["totalAmtJogging"],$movementweekly["totalAmtRunning"],7);echo $calBurnedCal["kcalAcheivementPercent"];?>"> <span class="easy_chart_percent"><?php echo $calBurnedCal["kcalAcheivement"];?> </span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Calories Burned</h4>
                          </div>
                        </div>
                      </div>
                      </li>
                  </ul>
                  <div class="tab-content"> <br class="clearfix" />
                    <div id="weekactivity" class="tab-pane fade in active">
                      <div id="weeklychartdiv" style="width: 100%; height: 500px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    
                    
                    
                  </div>
                </div>
                <div id="month" class="tab-pane fade"> 
                
                <ul role="tablist" class="cat_tab">
                    <li class="active">
                 
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                         <div class="easy_chart_b easy_chart" data-percent="<?php  $actAmount=activityCal($movementmonthly["totalAmtWalk"],$movementmonthly["totalAmtJogging"],$movementmonthly["totalAmtRunning"],30);echo $actAmount["activityPercent"];?>"> <span class="easy_chart_percent"><?php echo $actAmount["activityAcheivement"];?></span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Amount of Activity</h4>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li >
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="<?php $metMinutesCal=metMinutesCal($movementmonthly["totalAmtWalk"],$movementmonthly["totalAmtJogging"],$movementmonthly["totalAmtRunning"],30);echo $metMinutesCal["metsMinutesPercent"];?>"> <span class="easy_chart_percent"><?php echo $metMinutesCal["metsMinutesAcheivement"];?> </span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">MET Minutes <br />
                              </h4>
                          </div>
                        </div>
                      </div>
                      </li>
                    <li>
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_c easy_chart" data-percent="<?php $calBurnedCal=calBurnedCal($uid,$movementmonthly["totalAmtWalk"],$movementmonthly["totalAmtJogging"],$movementmonthly["totalAmtRunning"],30);echo $calBurnedCal["kcalAcheivementPercent"];?>"> <span class="easy_chart_percent"><?php echo $calBurnedCal["kcalAcheivement"];?> </span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Calories Burned</h4>
                          </div>
                        </div>
                      </div>
                       </li>
                  </ul>
                
                
                <div class="tab-content"> <br class="clearfix" />
                    <div id="monthactivity" class="tab-pane fade in active">
                       <div id="monthly_activities_chartdiv" style="width: 100%; height: 500px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    
                     
                    
                    
                    
                  </div>
                
                
                
               
                
              
                
                </div>
                    
                
                
              </div>
               <!-- activity div end-->
              </div>
             
               
             
             
                
                

                <div id="Locations" class="tab-pane fade"> <h2> User Location Analysis</h2>
                
               
              <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#weeklylocation">Week</a></li>
                <li><a data-toggle="tab" role="tab" href="#monthlylocation">Month</a> </li>
              </ul>
                
                <div class="tab-content">
                	<div id="weeklylocation" class="tab-pane fade in active"><div id="locationchartdiv" style="width: 100%; height:500px; background-color: #FFFFFF;" class="" ></div> </div>
             	<div id="monthlylocation" class="tab-pane fade">	<div id="monthlylocationchartdiv" style="width: 100%; height:500px; background-color: #FFFFFF;" ></div></div> 
                
				</div>                
                
                </div>
           
                 <div id="Nutrition" class="tab-pane fade">  <h2> User Nutrition  Analysis</h2> 
                 
                  <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#weeklyEmotion">Week</a></li>
                <li><a data-toggle="tab" role="tab" href="#monthlyEmotion">Month</a> </li>
              </ul>
                
                <div class="tab-content">
                	<div id="weeklyEmotion" class="tab-pane fade in active"><div id="emotionchartdiv" style="width: 100%; height:500px; background-color: #FFFFFF;" class="" ></div> </div>
             	<div id="monthlyEmotion" class="tab-pane fade">	<div id="monthlyemotionchartdiv" style="width: 100%; height:500px; background-color: #FFFFFF;" ></div></div> 
                
				</div>      
                 
                 </div>
                 
                 

            
               
                
                
                 <div id="HLC" class="tab-pane fade"><h2> High Level Context</h2>
             
             
                <div id="hlcchartdiv" style="width: 100%; height: 500px; background-color: #FFFFFF;" ></div></div>
                
                
                
                
                
             <!-- tabcontent div end-->
              </div>
              
             <div id="feedback" ><h2> User Feedback Analysis</h2>
             
             
                <div id="feedback_recommendations_chartdiv" style="width: 100%; height: 500px; background-color: #FFFFFF;" ></div></div>
              
             
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <!-- main menu -->
  <?php include "leftsidebar.php";?>
</div>

<div class="modal fade" id="review">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title"> <span title="" class="el-icon-comment bs_ttip" data-original-title=".el-icon-comment"></span>New Message</h4>
      </div>
                  

      <div class="modal-body">
          <form id="add_review" method="post" action="index.php">

        <p>Please write your message below:</p>
        <textarea name="wysiwg_editor" id="wysiwg_editor" cols="100" rows="10" class="form-control"></textarea>
        </form>
      </div>
      <div class="modal-footer">
      
        <button onClick="myFunction()" type="button" class="btn btn-default btn-sm" id="sendmessage"  >Send</button>
        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Close</button>

      </div>
    </div>
  </div>
</div>

<!-- jQuery --> 
<script src="assets/js/jquery.min.js"></script> 
<!-- jQuery Cookie --> 
<script src="assets/js/jqueryCookie.min.js"></script> 
<!-- Bootstrap Framework --> 
<script src="assets/bootstrap/js/bootstrap.min.js"></script> 
<!-- retina images --> 
<script src="assets/js/retina.min.js"></script> 
<!-- switchery --> 
<script src="assets/lib/switchery/dist/switchery.min.js"></script> 
<!-- typeahead --> 
<script src="assets/lib/typeahead/typeahead.bundle.min.js"></script> 
<!-- fastclick --> 
<script src="assets/js/fastclick.min.js"></script> 
<!-- match height --> 
<script src="assets/lib/jquery-match-height/jquery.matchHeight-min.js"></script> 
<!-- scrollbar --> 
<script src="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script> 
<script src="assets/lib/charts/chartist.min.js"></script> 
<!-- Yukon Admin functions --> 
<script src="assets/js/yukon_all.min.js"></script> 
<!-- easePie chart -->
<script src="assets/lib/easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

<!-- page specific plugins --> 

<!-- wysiwg editor --> 
<script src="assets/lib/ckeditor/ckeditor.js"></script> 
<script src="assets/lib/ckeditor/adapters/jquery.js"></script> 

<!-- icheck --> 
<script src="assets/lib/iCheck/icheck.min.js"></script>

<!-- amCharts javascript sources -->
<script src="http://www.amcharts.com/lib/3/amcharts.js" type="text/javascript"></script>
<script src="http://www.amcharts.com/lib/3/serial.js" type="text/javascript"></script>
        
<!-- Daily Activites Chart -->
<!--<script src="assets/js/dailyactivites.js" type="text/javascript"></script>
--> <!--  weekly Activites Chart -->
<!--<script src="assets/js/weeklyactivites.js" type="text/javascript"></script>
--> <!--  monthly Activites Chart -->
<!--<script src="assets/js/monthlyactivites.js" type="text/javascript"></script>
--> 
 
<!-- Feedback on actvities -->
<!--<script src="assets/js/cal_burned.js" type="text/javascript"></script>-->

<!-- Feedback on recommendations -->
<!-- Feedback on facts -->
<!--<script src="assets/js/feedback_facts.js" type="text/javascript"></script>
-->



 <script src="assets/lib/jBox-0.3.0/Source/jBox.min.js"></script>
<script>
 $(function() {
                  
// easy pie chart
	yukon_easyPie_chart.p_dashboard();
                  
})


 new jBox('Modal', {
                width: 500,
                height: 300,
				
                attach: $('#sendmessage'),
                draggable: 'title',
                closeButton: 'title',
                title: '',
                content: '<h3 style="text-align:center;">  <span class="icon_check_alt bs_ttip"></span> Your message has been succesfully delivered.</h3>'
            });
     
 </script>
 
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
							"Negative": 0,
							"Positive": 0						},{
							"category": "Sitting",
							"Negative": 10,
							"Positive": 4						},{
							"category": "Standing",
							"Negative": 0,
							"Positive": 1						},{
							"category": "Walking",
							"Negative": 0,
							"Positive": 0						},{
							"category": "Jogging",
							"Negative": 0,
							"Positive": 0						},{
							"category": "Running",
							"Negative": 0,
							"Positive": 0						}
					]
				}
			);
 
 
  
 
 
 </script>
 <script>
AmCharts.makeChart("dailychartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD HH",
					"categoryAxis": {
						"minPeriod": "hh",
						"parseDates": true
					},
					"chartCursor": {
						"categoryBalloonDateFormat": "JJ:NN"
					},
					<!--"chartScrollbar": {},-->
					
					"graphs": [
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "LyingDown",
							"title": "LyingDown",
							"type": "column",
							"valueField": "LyingDown"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Sitting",
							"title": "Sitting",
							"type": "column",
							"valueField": "Sitting"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Standing",
							"title": "Standing",
							"type": "column",
							"valueField": "Standing"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Walking",
							"negativeBase": -5,
							"periodSpan": -5,
							"precision": -1,
							"title": "Walking",
							"type": "column",
							"valueField": "Walking"
						},
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Jogging",
							"title": "Jogging",
							"type": "column",
							"valueField": "Jogging"
						},
						
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Running",
							"title": "Running",
							"type": "column",
							"valueField": "Running"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Stretching",
							"title": "Stretching",
							"type": "column",
							"valueField": "Stretching"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Dancing",
							"title": "Dancing",
							"type": "column",
							"valueField": "Dancing"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Sweeping",
							"title": "Sweeping",
							"type": "column",
							"valueField": "Sweeping"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Eating",
							"title": "Eating",
							"type": "column",
							"valueField": "Eating"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "ClimbingStairs",
							"title": "ClimbingStairs",
							"type": "column",
							"valueField": "ClimbingStairs"
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
					"balloon": { 
						"periodValueText": "([[value.sum]] min)",
						
					},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					
					"dataProvider": [
						
 

 



				
				
										{
											"date": "2015-12-01 09",
											"LyingDown": 15,"Sitting": 20,"Standing":1,"Walking": 2,"Jogging": 0,"Running": 5,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 10",
											"LyingDown": 1,"Sitting": 20,"Standing":0,"Walking": 2,"Jogging": 0,"Running": 0,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 11",
											"LyingDown": 0,"Sitting": 20,"Standing":1,"Walking": 2,"Jogging": 0,"Running": 0,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 12",
											"LyingDown": 0,"Sitting": 10,"Standing":5,"Walking": 5,"Jogging": 0,"Running": 0,"Stretching": 3,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 13",
											"LyingDown": 0,"Sitting": 0,"Standing":0,"Walking": 0,"Jogging": 0,"Running": 0,"Stretching": 3,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 14",
											"LyingDown": 0,"Sitting": 5,"Standing":0,"Walking": 0,"Jogging": 0,"Running": 5,"Stretching":0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 15",
											"LyingDown": 0,"Sitting": 20,"Standing":1,"Walking": 0,"Jogging": 0,"Running": 5,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 16",
											"LyingDown": 0,"Sitting": 7,"Standing":10,"Walking": 0,"Jogging": 0,"Running": 5,"Stretching": 3,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 17",
											"LyingDown": 0,"Sitting": 2,"Standing":11,"Walking": 0,"Jogging": 0,"Running": 5,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 18",
											"LyingDown": 0,"Sitting": 20,"Standing":12,"Walking": 4,"Jogging": 0,"Running": 5,"Stretching":5,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 19",
											"LyingDown": 0,"Sitting": 14,"Standing":1,"Walking": 2,"Jogging": 0,"Running": 5,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 20",
											"LyingDown": 5,"Sitting": 1,"Standing":1,"Walking": 2,"Jogging": 0,"Running": 5,"Stretching": 0,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
								
				
										{
											"date": "2015-12-01 21",
											"LyingDown": 5,"Sitting": 2,"Standing":0,"Walking": 11,"Jogging": 0,"Running": 5,"Stretching": 1,"Dancing": 0,"Sweeping": 0,"Eating": 0.2,"ClimbingStairs": 0.1},											
											
										  
				                           
					]
				}
			);
 </script>

 
 
 
 <script>
AmCharts.makeChart("weeklychartdiv",
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
							"title": "LyingDown",
							"valueField": "LyingDown"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Sitting",
							"valueField": "Sitting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Standing",
							"valueField": "Standing"
						},
						{
							"bullet": "round",
							"id": "AmGraph-4",
							"title": "Walking",
							"valueField": "Walking"
						},
						
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "Jogging",
							"valueField": "Jogging"
						},
						{
							"bullet": "round",
							"id": "AmGraph-6",
							"title": "Running",
							"valueField": "Running"
						},{
							"bullet": "round",
							"id": "AmGraph-7",
							"title": "Stretching",
							"valueField": "Stretching"
						},{
							"bullet": "round",
							"id": "AmGraph-8",
							"title": "Dancing",
							"valueField": "Dancing"
						},{
							"bullet": "round",
							"id": "AmGraph-9",
							"title": "Sweeping",
							"valueField": "Sweeping"
						},{
							"bullet": "round",
							"id": "AmGraph-10",
							"title": "Eating",
							"valueField": "Eating"
						},{
							"bullet": "round",
							"id": "AmGraph-11",
							"title": "ClimbingStairs",
							"valueField": "ClimbingStairs"
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
					{"date":"2016-03-26",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-03-27",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-03-28",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-03-29",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-03-30",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-03-31",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  },{"date":"2016-04-01",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Jogging": 0,
                                    "Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimbingStairs": 0

                                   
                                    
									
                                    
						  }	]});					
	</script><script>
AmCharts.makeChart("monthly_activities_chartdiv",
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
							"title": "LyingDown",
							"valueField": "LyingDown"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Sitting",
							"valueField": "Sitting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Standing",
							"valueField": "Standing"
						},
						{
							"bullet": "round",
							"id": "AmGraph-4",
							"title": "Walking",
							"valueField": "Walking"
						},
						
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "Jogging",
							"valueField": "Jogging"
						},
						{
							"bullet": "round",
							"id": "AmGraph-6",
							"title": "Running",
							"valueField": "Running"
						},{
							"bullet": "round",
							"id": "AmGraph-7",
							"title": "Stretching",
							"valueField": "Stretching"
						},{
							"bullet": "round",
							"id": "AmGraph-8",
							"title": "Dancing",
							"valueField": "Dancing"
						},{
							"bullet": "round",
							"id": "AmGraph-9",
							"title": "Sweeping",
							"valueField": "Sweeping"
						},{
							"bullet": "round",
							"id": "AmGraph-10",
							"title": "Eating",
							"valueField": "Eating"
						},{
							"bullet": "round",
							"id": "AmGraph-11",
							"title": "Stairs",
							"valueField": "ClimbingStairs"
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
					{"date":"2016-03-03",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-04",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-05",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-06",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-07",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-08",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-09",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-10",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-11",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-12",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-13",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-14",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-15",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-16",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-17",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-18",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-19",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-20",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-21",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-22",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-23",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-24",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-25",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-26",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-27",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-28",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-29",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-30",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-03-31",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  },{"date":"2016-04-01",                                    "LyingDown": 0,
									"Sitting": 0,
									"Standing": 0,
									"Walking": 0,
									"Resting": 0,
									"Jogging": 0,
									"Running": 0,
									"Stretching": 0,
									"Dancing": 0,
									"Sweeping": 0,
									"Eating": 0,
									"ClimibingStairs": 0,
                                    
									
                                   
                                    
									
                                    
						  }	]});					
	</script><script>
AmCharts.makeChart("emotionchartdiv",
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
							"title": "Fat",
							"valueField": "Fat"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Cholesterol",
							"valueField": "Cholesterol"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Sugar",
							"valueField": "Sugar"
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
										
										
					{
					
						"date": "2016-03-26",
						
						             "Fat": 10,
									"Cholesterol": 15,
									"Sugar": 5,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-27",
						
						             "Fat": 0,
									"Cholesterol": 20,
									"Sugar": 4,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-28",
						
						             "Fat": 4,
									"Cholesterol": 7,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-29",
						
						             "Fat": 7,
									"Cholesterol": 3,
									"Sugar":7,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-30",
						
						             "Fat": 8,
									"Cholesterol": 15,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-31",
						
						             "Fat": 0,
									"Cholesterol": 0,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-01",
						
						             "Fat": 0,
									"Cholesterol": 0,
									"Sugar": 0,
									
                                   
                                    
									
                                    
						  },										
										
											
					
					
									                           
					]
				}
			);					
					
					


</script>



 
 
 
 <script>
AmCharts.makeChart("monthlyemotionchartdiv",
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
							"title": "Anger",
							"valueField": "Anger"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Happiness",
							"valueField": "Happiness"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Sadness",
							"valueField": "Sadness"
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
										
										
					{
					
						"date": "2016-03-03",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-04",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-05",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-06",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-07",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-08",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-09",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-10",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-11",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-12",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-13",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-14",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-15",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-16",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-17",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-18",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-19",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-20",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-21",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-22",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-23",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-24",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-25",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-26",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-27",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-28",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-29",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-30",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-31",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-04-01",
						
						             "Sadness": 0,
									"Happiness": 0,
									"Anger": 0,
									
                                   
                                    
									
                                    
						  },										
										
											
					
					
									                           
					]
				}
			);					
					
					


</script>



 
 
 
 <script>
AmCharts.makeChart("hlcchartdiv",
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
							"id": "AmGraph-1",
							"title": "Amusement",
							"valueField": "Amusement"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-2",
							"title": "Commuting",
							"valueField": "Commuting"
						},
						{
							"bullet": "round",
							"id": "AmGraph-3",
							"title": "Exercising",
							"valueField": "Exercising"
						},
						
						{
							"bullet": "round",
							"id": "AmGraph-4",
							"title": "Gardening",
							"valueField": "Gardening"
						},	
						
						{
							"bullet": "round",
							"id": "AmGraph-5",
							"title": "HavingMeal",
							"valueField": "HavingMeal"
						},
						{
							"bullet": "round",
							"id": "AmGraph-6",
							"title": "HouseWork",
							"valueField": "HouseWork"
						},
						/*{
							"bullet": "round",
							"id": "AmGraph-7",
							"title": "Inactivity",
							"valueField": "Inactivity"
						},	*/
						
						{
							"bullet": "round",
							"id": "AmGraph-8",
							"title": "OfficeWork",
							"valueField": "OfficeWork"
						},
						{
							"bullet": "round",
							"id": "AmGraph-9",
							"title": "Sleeping",
							"valueField": "Sleeping"
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
										
										
					{
					
						"date": "2016-03-26",
						
						             "Amusement": 10,
									"Commuting":2,
									"Exercising": 2,
									 "Gardening": 0,
									"HavingMeal": 10,
									"HouseWork": 14,
									 <!--"Inactivity": ,-->
									"OfficeWork": 60,
									"Sleeping":60                     
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-27",
						
						             "Amusement": 10,
									"Commuting": 20,
									"Exercising": 10,
									 "Gardening": 20,
									"HavingMeal": 30,
									"HouseWork": 40,
									 <!--"Inactivity": ,-->
									"OfficeWork": 50,
									"Sleeping": 60                     
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-28",
						
						             "Amusement": 0,
									"Commuting": 20,
									"Exercising": 20,
									 "Gardening": 20,
									"HavingMeal": 10,
									"HouseWork": 10,
									 <!--"Inactivity": ,-->
									"OfficeWork": 110,
									"Sleeping": 200                     
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-29",
						
						             "Amusement":10,
									"Commuting": 20,
									"Exercising": 20,
									 "Gardening": 30,
									"HavingMeal": 30,
									"HouseWork": 20,
									 <!--"Inactivity": ,-->
									"OfficeWork": 80,
									"Sleeping": 90                     
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-30",
						
						             "Amusement": 0,
									"Commuting": 0,
									"Exercising": 0,
									 "Gardening": 0,
									"HavingMeal": 0,
									"HouseWork": 0,
									 <!--"Inactivity": ,-->
									"OfficeWork": 0,
									"Sleeping": 0                     
                                    
									
                                    
						  },										
										
					{
					
						"date": "2016-03-31",
						
						             "Amusement": 0,
									"Commuting": 0,
									"Exercising": 0,
									 "Gardening": 0,
									"HavingMeal": 0,
									"HouseWork": 0,
									 <!--"Inactivity": ,-->
									"OfficeWork": 0,
									"Sleeping": 0                     
                                    
									
                                    
						  },										
										
							
											
					
					
									                           
					]
				}
			);					
					
					


</script><script>
AmCharts.makeChart("locationchartdiv",
				{
					"type": "serial",
					"pathToImages": "http://cdn.amcharts.com/lib/3/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD HH",
					"categoryAxis": {
						"minPeriod": "hh",
						"parseDates": true
					},
					"chartCursor": {
						"categoryBalloonDateFormat": "JJ:NN"
					},
					<!--"chartScrollbar": {},-->
					
					"graphs": [
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Fat",
							"title": "Fat",
							"type": "column",
							"valueField": "Fat"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Cholesterol",
							"title": "Cholesterol",
							"type": "column",
							"valueField": "Cholesterol"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Carbohydrates",
							"title": "Carbohydrates",
							"type": "column",
							"valueField": "Carbohydrates"
						},{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"id": "Walking",
							"negativeBase": -5,
							"periodSpan": -5,
							"precision": -1,
							"title": "Proteins",
							"type": "column",
							"valueField": "Proteins"
						},
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Fiber",
							"title": "Fiber",
							"type": "column",
							"valueField": "Fiber"
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
					"balloon": { 
						"periodValueText": "([[value.sum]] min)",
						
					},
					"legend": {
						<!--"periodValueText": "([[value.sum]] min)",-->
						"useGraphSettings": true,
						"valueAlign": "left",
						"valueText":"([[value]] min)"
					},
					
					"dataProvider": [
						
 

 



				
				
										{
											"date": "2015-12-01 09",
											"Fat": 150,"Cholesterol": 200,"Carbohydrates":10,"Proteins": 20,"Fiber": 80},											
											
										  
								
				
										{
											"date": "2015-12-01 10",
											"Fat": 10,"Cholesterol": 15,"Carbohydrates":0,"Proteins": 2,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 11",
											"Fat": 0,"Cholesterol": 50,"Carbohydrates":1,"Proteins": 2,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 12",
											"Fat": 0,"Cholesterol": 600,"Carbohydrates":5,"Proteins": 5,"Fiber": 100},											
											
										  
								
				
										{
											"date": "2015-12-01 13",
											"Fat": 0,"Cholesterol": 20,"Carbohydrates":0,"Proteins": 0,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 14",
											"Fat": 0,"Cholesterol": 5,"Carbohydrates":0,"Proteins": 0,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 15",
											"Fat": 0,"Cholesterol": 20,"Carbohydrates":1,"Proteins": 0,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 16",
											"Fat": 0,"Cholesterol": 7,"Carbohydrates":10,"Proteins": 0,"Fiber": 0},											
											
										  
								
				
										{
											"date": "2015-12-01 17",
											"Fat": 0,"Cholesterol":10 ,"Carbohydrates":11,"Proteins": 0,"Fiber": 10},											
											
										  
								
				
										{
											"date": "2015-12-01 18",
											"Fat": 110,"Cholesterol": 203,"Carbohydrates":12,"Proteins": 4,"Fiber": 50},											
											
										  
								
				
																				
											
										  
				                           
					]
				}
			);

</script>




 
 
 
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
										
										
					{
					


						"date": "2016-03-03",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-04",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-05",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-06",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-07",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-08",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-09",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-10",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-11",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-12",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-13",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-14",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-15",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-16",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-17",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-18",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-19",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-20",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-21",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-22",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-23",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-24",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-25",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-26",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-27",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-28",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-29",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-30",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-03-31",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
					{
					


						"date": "2016-04-01",
						
						            "Home": 0,
									"Office": 0,
									"Yard": 0,
									"Gym": 0,
									"Mall": 0,
									"Restaurant": 0,
									"Outdoors": 0,
									"Transport": 0,
									
									
                                   
                                    
									
                                    
						  },										
										
											
					
					
									                           
					]
				}
			);					
					
					





</script>




 
 
 
 
<script>
	$("#pushnotify").click(function () {
	
   	$.ajax({
        type: "GET",
        url: 'updateview.php',
        data: "options=dummy",
        success: function(response) {
		
		 $("#newnotify").html("");

        }
    });
       

});
</script>  

<?php  //include "recfeedback.php";?>
<?php  //include "dailychartdiv.php";?>
<?php  //include "weeklyactivites.php";?>
<?php //include "monthlyactivites.php";?>
<?php //include "emotionchartdiv.php";?>
<?php //include "monthlyemotionchartdiv.php";?>
<?php //include "hlcchartdiv.php";?>
<?php //include "locationchartdiv.php";?>
<?php //include "monthlylocationchartdiv.php";?>

<?php  include "footer.php";?>  


 <script>



</script>



</body>
</html>
