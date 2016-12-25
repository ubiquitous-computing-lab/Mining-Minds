<?php
		session_start(); 
		
	
	/*if(!$_SESSION['user_id']){ 
		header("Location: index.html"); 
		exit; 
		
		}*/
		
	if (isset($_POST['addreview'])) {
	
		header("Location: index.html"); 

	} 
	
	include "functions.php"; 
	
	
	if(isset($_GET['userid'])){
		$uid=$_GET['userid'];
		$userJson=retrieveUserByUserID($uid);
	}	
	
	else
			header("Location: users.php");
	
		

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

<!-- moment.js (date library) -->
<script src="assets/js/moment-with-langs.min.js"></script>
<script>


function myFunction() {
// alert($("#wysiwg_editor").val());
var value =document.getElementById("wysiwg_editor").value;
$.ajax({
        type: "GET",
        url: 'http://localhost/expertview/addExpertReview.php',
        data: "review="+value+"&userID=<?php echo $uid;?>&expertID=<?php echo $uid;?>",
        success: function(response) {
         alert(response);
        }
    });
   // alert("I am an alert box!");
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
  
  <!-- header -->
  <header id="main_header">
    <div class="container-fluid">
      <div class="brand_section"> <a href="dashboard.html"><img src="assets/img/logo_header.png" alt="site_logo" ></a> </div>
      <ul class="header_notifications clearfix">
        <li class="dropdown" id="tasks_dropdown"> <span class="label label-danger">3</span> <a data-toggle="dropdown" href="#" class="dropdown-toggle active"><i class="el-icon-bell"></i></a>
          <div class="dropdown-menu">
            <ul>
              <li> <a href="#" >
                <div class="clearfix">
                  <div class="label label-warning pull-right">Medium</div>
                  <small class="text-muted">Jamil Hussain (30.04.2015)</small> </div>
                <p>User completed 50% goals for last week</p>
                </a> </li>
              <li><a href="#" >
                <div class="clearfix">
                  <div class="label label-danger pull-right">High</div>
                  <small class="text-muted">Taqdir Ali(26.04.2015)</small> </div>
                <p>User completed 80% goals for last week</p>
                </a> </li>
              <li> <a href="#" >
                <div class="clearfix">
                  <div class="label label-danger pull-right">High</div>
                  <small class="text-muted">Bilal (25.04.2015)</small> </div>
                <p>User completed 87% goals for last week</p>
                </a> </li>
              <li> <a href="#" class="btn btn-xs btn-primary btn-block">All tasks</a> </li>
            </ul>
          </div>
        </li>
      </ul>
      <div class="header_user_actions dropdown">
        <div data-toggle="dropdown" class="dropdown-toggle user_dropdown">
          <div class="user_avatar"> <img src="assets/img/avatars/avatar02_tn.png" alt="" title="Jamil Hussain" width="38" height="38"> </div>
          <span class="caret"></span> </div>
        <ul class="dropdown-menu dropdown-menu-right">
          <li><a href="profile.html">User Profile</a></li>
          <li><a href="index.html">Log Out</a></li>
        </ul>
      </div>
    </div>
  </header>
  
  <!-- breadcrumbs -->
  <nav id="breadcrumbs">
    <ul>
      <li><a href="dashboard.html">Home</a></li>
      <li><span>User</span></li>
    </ul>
  </nav>
  
  <!-- main content -->
  <div id="main_wrapper">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-5"> <img width="76" height="76" alt="" src="members/<?php if($userJson[0]['userID'])echo $userJson[0]['userID'].".jpg"; else echo "default.png";?>" class="user_profile_img"> <br />
              <h1 class="user_profile_name"> <?php echo $userJson[0]['firstName']." ".$userJson[0]['lastName']?> </h1>
              <button class="btn btn-sm btn-info" data-toggle="modal" data-target="#review">Write Your Review</button>
              <br class="clearfix" />
              <div class="timeline">
     
              
                <div class="timeline-row">
                 
                  <div class="timeline-icon">
                    <div class="bg-info"> <i class="el-icon-pencil"></i> </div>
                  </div>
                  
                  <div class="timeline-content">
                   
                    <div class="timeline-inner-content">
                    
                    
                    
                    <?php 
					
					
						$divFlag=11;
						$start = new DateTime('');
						$end = new DateTime('+3 days');
						$interval = new DateInterval('P1D');
						$period = new DatePeriod($start, $interval, $end);
						$startdate='2015 05 05 00:30:01';
						$enddate='2015 05 05 00:30:01';
					
						foreach ($period as $dt) {
								if ($dt->format("N") === 7) {
									
										
										$end->add(new DateInterval('P1D'));
									}
									else  {
								
					
					?>
                    
                    
                    
                     
                      <h2><?php echo $dt->format("l Y-m-d");?> </h2>
                      <div  class="panel-group">
							<?php 
							
							timelinerow($startdate,$enddate);
							 //include "timeline.php?startdate=$startdate&enddate=$enddate"; ?> 
                        
                        
                        
                      </div>
                      
                      <?php }}?>

                    </div>
                  </div>
                  
                </div>
                <div class="timeline-row">
                  
                  <div class="timeline-icon">
                    <div class="bg-danger"> <i class="el-icon-comment"></i> </div>
                  </div>
                  <div class="timeline-content">
                    <div class="timeline-inner-content">
                      <h2>Comment</h2>
                      <p><i>Please Reduce Biking durations for the future;</i></p>
                      <p class="sepH_c"><small class="text-muted">User:</small> Jamil Hussain</p>
                      <p>
                        <button class="btn btn-outline btn-sm btn-primary">View Comment</button>
                      </p>
                    </div>
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
                    <li class="active"><a data-toggle="tab" href="#activity">
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_a easy_chart" data-percent="80"> <span class="easy_chart_percent">80%</span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Amount of Activity</h4>
                          </div>
                        </div>
                      </div>
                      </a></li>
                    <li ><a data-toggle="tab" href="#cal">
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_b easy_chart" data-percent="30"> <span class="easy_chart_percent">30%</span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Cal <br />
                              Burned</h4>
                          </div>
                        </div>
                      </div>
                      </a></li>
                    <li><a data-toggle="tab" href="#met">
                      <div class="col-md-4">
                        <div class="easy_chart_wrapper mHeight-item">
                          <div class="easy_chart_c easy_chart" data-percent="50"> <span class="easy_chart_percent">50%</span> </div>
                          <div class="easy_chart_info">
                            <h4 class="easy_chart_heading">Amount of MET</h4>
                          </div>
                        </div>
                      </div>
                      </a> </li>
                  </ul>
                  <div class="tab-content"> <br class="clearfix" />
                    <div id="activity" class="tab-pane fade in active">
                      <div id="dailychartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                    </div>
                    
                    <div id="cal" class="tab-pane fade in">
                     Cal Burned Graphs
                    </div>
                    
                    <div id="met" class="tab-pane fade in">
                       MET  Graphs
                    </div>
                  </div>
                </div>
                <div id="week" class="tab-pane fade"> <div id="weeklychartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div> </div>
                <div id="month" class="tab-pane fade"> <div id="monthly_activities_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div> </div>
              </div>
              <h2> User feedback Analysis</h2>
              <ul role="tablist" class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#activities">Activities</a></li>
                <li><a data-toggle="tab" role="tab" href="#recommendations">Recommendations</a></li>
                <li><a data-toggle="tab" role="tab" href="#facts">Facts</a> </li>
              </ul>
              <div class="tab-content">
                <div id="activities" class="tab-pane fade in active"> <div id="feedback_activites_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div> </div>
                <div id="recommendations" class="tab-pane fade"> <div id="feedback_recommendations_chartdiv" style="width: 100%; height:300px; background-color: #FFFFFF;" ></div> </div>
                <div id="facts" class="tab-pane fade"> <div id="feedback_facts_chartdiv" style="width: 100%; height:300px; background-color: #FFFFFF;" ></div> </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <!-- main menu -->
  <nav id="main_menu">
    <div class="menu_wrapper">
      <ul>
        <li class="first_level"> <a href="dashboard.html"> <span class="icon_house_alt first_level_icon"></span> <span class="menu-title">Dashboard</span> </a> </li>
        <li class="first_level section_active"> <a href="users.php"> <span class="icon_contacts_alt  first_level_icon "></span> <span class="menu-title">Users</span> </a>        </li>
      </ul>
    </div>
    <form id="add_review" method="post" action="index.php">
    <div class="menu_toggle"> <span class="icon_menu_toggle"> <i class="arrow_carrot-2left toggle_left"></i> <i class="arrow_carrot-2right toggle_right" style="display:none"></i> </span> </div>
  </nav>
</div>

<div class="modal fade" id="review">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title"> <span title="" class="el-icon-comment bs_ttip" data-original-title=".el-icon-comment"></span> Write Review</h4>
      </div>
                  

      <div class="modal-body">
        <p>Start by writing a brief description:</p>
        <textarea name="wysiwg_editor" id="wysiwg_editor" cols="30" rows="4" class="form-control"></textarea>
        <div class="row">
          <div class="col-sm-12">
            <h5>User Status</h5>
            <hr />
            
            
            
          </div>
        </div>
      </div>
      <div class="modal-footer">
      
        <button onClick="myFunction()" type="button" class="btn btn-default btn-sm" >Send</button>
        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Close</button>

        <input name="addreview" type="submit" class="btn btn-primary btn-sm" data-dismiss="modal">
      </div>
    </div>
  </div>
</div>
</form>
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
<script src="assets/js/weeklyactivites.js" type="text/javascript"></script>
 <!--  monthly Activites Chart -->
<script src="assets/js/monthlyactivites.js" type="text/javascript"></script>
 
 
<!-- Feedback on actvities -->
<script src="assets/js/feedback_activities.js" type="text/javascript"></script>
<!-- Feedback on recommendations -->
<script src="assets/js/feedback_recommendations.js" type="text/javascript"></script>
<!-- Feedback on recommendations -->
<script src="assets/js/feedback_facts.js" type="text/javascript"></script>

<script>
 $(function() {
                  
// easy pie chart
	yukon_easyPie_chart.p_dashboard();
                  
})
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
					"chartScrollbar": {},
					
					"graphs": [
						{
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
							"id": "Running",
							"title": "Running",
							"type": "column",
							"valueField": "Running"
						},
						{
							"fillAlphas": 1,
							"fixedColumnWidth": 20,
							"fontSize": -1,
							"id": "Standing",
							"title": "Standing",
							"type": "column",
							"valueField": "Standing"
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
						"periodValueText": "Total: [[value.sum]]",
						"useGraphSettings": true,
						"valueAlign": "left"
					},
					
					"dataProvider": [
						{
							"date": "2014-03-01 08",
							"Walking": 8,
							"Running": 5,
							"Standing": 2
						},
						{
							"date": "2014-03-01 09",
							"Walking": 6,
							"Running": 7,
							"Standing": 3
						},
						{
							"date": "2014-03-01 10",
							"Walking": 2,
							"Running": 3,
							"Standing": 1
						},
						{
							"date": "2014-03-01 11",
							"Walking": 1,
							"Running": 3,
							"Standing": 2
						},
						{
							"date": "2014-03-01 12",
							"Walking": 2,
							"Running": 1,
							"Standing": 1
						},
						{
							"date": "2014-03-01 13",
							"Walking": 3,
							"Running": 2,
							"Standing": 9
						},
						{
							"date": "2014-03-01 14",
							"Walking": 6,
							"Running": 8,
							"Standing": 1
						}
					]
				}
			);
 </script>
 
</body>
</html>
