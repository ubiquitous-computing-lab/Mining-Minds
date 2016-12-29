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
//$start = new DateTime('2015-05-18');
//$end = new DateTime('2015-05-18');



$start = new DateTime('now');
$end = new DateTime('now');


//$uid=39;
$start->setTime(00,00,00);
$end->setTime(23,59,59);

//$starttime = DateTime::CreateFromFormat("Y m d H:i:s", $start);
//$endtime = DateTime::CreateFromFormat("Y m d H:i:s", $end);
	



$movement=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);

$starttime =  new DateTime('now');
$start->setTime(00,00,00);

$movementweekly=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);


$starttime =  new DateTime('now');
$start->setTime(00,00,00);
$movementmonthly=calMovement($start->format("Y m d H:i:s"),$end->format("Y m d H:i:s"),$uid);


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
  
    <?php include "header.php";?>
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
              <div class="timeline">
     
              
                <div class="timeline-row">
                 
                  <div class="timeline-icon">
                    <div class="bg-info"> <i class="el-icon-pencil"></i> </div>
                  </div>
                  
                  <div class="timeline-content">
                   
                    <div class="timeline-inner-content">
                    
                    
							<?php 	include "dynamictimeline.php";  ?>	

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
                      <div id="dailychartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="cal" class="tab-pane fade in">
                    <div id="amount_met_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="met" class="tab-pane fade in">
                       <div id="cal_burned_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
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
                      <div id="weeklychartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="weekcal" class="tab-pane fade in">
                        <div id="weeklycal_burned_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="weekmet" class="tab-pane fade in">
                      <div id="weeklymet_minutes_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
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
                       <div id="monthly_activities_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="monthmet" class="tab-pane fade in">
                     <div id="monthlymet_minutes_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                      
                    </div>
                     <div id="monthcal" class="tab-pane fade in">
                        <div id="monthlycal_burned_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    
                    
                  </div>
                
                
                
                 <h2> User Location Analysis</h2> 
                
              
                
                </div>
                    <h2> User Location and Emotion Analysis</h2>
              <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#emotion">Emotion</a></li>
                <li><a data-toggle="tab" role="tab" href="#location">Location</a></li>
              </ul>
              <div class="tab-content">
                <div id="emotion" class="tab-pane fade in active">   <div id="emotionchartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div> </div>
                <div id="location" class="tab-pane fade"> <div id="locationchartdiv" style="width: 100%; height:300px; background-color: #FFFFFF;" ></div> </div>
              </div>
                
                
              </div>
              <h2> User Feedback Analysis</h2>
             
             
                <div id="feedback_recommendations_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
             
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
        <textarea name="wysiwg_editor" id="wysiwg_editor" cols="30" rows="4" class="form-control"></textarea>
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
<script src="assets/js/amcharts.js" type="text/javascript"></script>
<script src="assets/js/serial.js" type="text/javascript"></script>
        
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
                height: 150,
				
                attach: $('#sendmessage'),
                draggable: 'title',
                closeButton: 'title',
                title: '',
                content: '<h3 style="text-align:center;">  <span class="icon_check_alt bs_ttip"></span> Your message has been succesfully delivered.</h3>'
            });
     
 </script>
<?php  include "recfeedback.php";?>
<?php  include "factfeedback.php"; ?>
<?php  include "dailychartdivx.php";?>
<?php  include "weeklyactivites.php";?>
<?php include "monthlyactivites.php";?>
<?php include "emotionchartdiv.php";?>
<?php include "locationchartdiv.php";?>
<?php  include "footer.php";?>  


 <script>



</script>



</body>
</html>
