<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Mining Minds Expert View(dashboard)</title>
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


        <!-- google webfonts -->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>

        <!-- main stylesheet -->
		<link href="assets/css/main.min.css" rel="stylesheet" media="screen" id="mainCss">

        <!-- moment.js (date library) -->
        <script src="assets/js/moment-with-langs.min.js"></script>

    </head>
    <body class="side_menu_active side_menu_expanded">
        <div id="page_wrapper">

            <!-- header -->
            <header id="main_header">
                <div class="container-fluid">
                    <div class="brand_section">
                        <a href="dashboard.html"><img src="assets/img/logo_header.png" alt="site_logo" ></a>
                    </div>
                    <ul class="header_notifications clearfix">
                        
                        <!--<li class="dropdown" id="tasks_dropdown">
                            <span class="label label-danger">14</span>
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle active"><i class="el-icon-bell"></i></a>
                      <div class="dropdown-menu">
                                <ul>
                                    <li>
                                    <a href="#" >
                                        <div class="clearfix">
                                            <div class="label label-warning pull-right">Medium</div>
                                            <small class="text-muted">Jamil Hussain (30.04.2015)</small>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet&hellip;</p>
                                       </a>
                                    </li>
                                    <li><a href="#" >
                                        <div class="clearfix">
                                        
                                            <div class="label label-danger pull-right">High</div>
                                            <small class="text-muted">Taqdir Ali(26.04.2015)</small>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet&hellip;</p>
                                        </a>
                                    </li>
                                    <li>
                                    <a href="#" >
                                        <div class="clearfix">
                                            <div class="label label-danger pull-right">High</div>
                                            <small class="text-muted">Bilal (25.04.2015)</small>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet&hellip;</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="btn btn-xs btn-primary btn-block">All tasks</a>
                                    </li>
                                </ul>
                            </div>
							
                        </li>-->
                        
                  </ul>
                    <div class="header_user_actions dropdown">
                        <div data-toggle="dropdown" class="dropdown-toggle user_dropdown">
                            <div class="user_avatar">
                                <img src="assets/img/avatars/avatar02_tn.png" alt="" title="Jamil Hussain(carrol@example.com)" width="38" height="38">
                            </div>
                            <span class="caret"></span>
                        </div>
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
              </ul>
            </nav>

            <!-- main content -->
            <div id="main_wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4 col-sm-6">
                            <div class="info_box_var_1 box_bg_a">
                                <div class="info_box_body">
                                    <span class="info_box_icon icon_group"></span>
                                    <span class="countUpMe" data-endVal="1342">20</span>
                                </div>
                                <div class="info_box_footer">
                                    New Users
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="info_box_var_1 box_bg_b">
                                <div class="info_box_body">
                                    <span class="info_box_icon el-icon-bell"></span>
                                    <span class="countUpMe" data-endVal="57">57</span>
                                </div>
                                <div class="info_box_footer">
User recommendations                                </div>
                            </div>
                        </div>
                       
                        <div class="col-lg-4 col-sm-6">
                            <div class="info_box_var_1 box_bg_d">
                                <div class="info_box_body">
                                    <span class="info_box_icon icon_mail_alt"></span>
                                    <span class="countUpMe" data-endVal="531">531</span>
                                </div>
                                <div class="info_box_footer">
                                    Total Reviews sent 
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12">
<!--                            <div class="heading_a"><span class="heading_text">Users (location)</span></div>
-->                            <div class="row">
                                <!--<div class="col-md-8">
                                    <div id="world_map_vector" style="width:100%;height:300px">
                                        <script>
                                            countries_data = {
                                                "US": 2320,
                                                "BR": 1945,
                                                "IN": 1779,
                                                "AU": 1486,
                                                "TR": 1024,
                                                "CN": 753
                                            };
                                        </script>
                                    </div>
                                </div>-->
                                <div class="col-md-4">
                                     <div class="heading_a">
                                <span class="heading_text">Users (by age)</span>
                            </div>
                            <div id="c3_users_age" style="height:220px"></div>
                                </div>
                                
                                
                                <div class="col-md-4">
                                     <div class="heading_a">
                                <span class="heading_text">Users (by Location)</span>
                            </div>
<div id="activities" class="tab-pane fade in active">
                  <div id="feedback_location_chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                </div>                                </div>
                
                <div class="col-md-4">
                                     <div class="heading_a">
                                <span class="heading_text">Users (by Actvity)</span>
                            </div>

<div id="chartdiv"></div>


                                </div>
                
                            </div>
                        </div>
                    </div>
                   
                   
                   
                   
                   <div class="row">
                   <div class="col-md-12">
                   
                   
                   
                   <div class="col-md-4">
                                     <div class="heading_a" align="center">
                                <span class="heading_text">Topic Trend By SNS</span>
                            </div>
                            
                            <div class="bubble"></div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>


                                </div>
                   
                     <div class="col-md-4">
                                     <div class="heading_a">SNS Food Trend</div>
                          <div id="snschartdiv" style="width: 500px; height: 500px; background-color: #FFFFFF;" ></div>
                                </div>
                   
                   </div>
                   <div >
                   
                   
                   </div>
                   
                    
            </div>
            
            <!-- main menu -->
            <nav id="main_menu">
                <div class="menu_wrapper">
                   <ul>
						<li class="first_level section_active"> <a href="dashboard.html"> <span class="icon_house_alt first_level_icon"></span> <span class="menu-title">Dashboard</span> </a> </li>
						<li class="first_level "> <a href="#"> <span class="icon_contacts_alt  first_level_icon "></span> <span class="menu-title">Users</span> </a>
						  <ul>
							<li><a href="users.html">User list</a></li>
							<li><a href="users2.html">Query Panel </a></li>
						  </ul>
						</li>
					</ul>
                </div>
<div class="menu_toggle">
                    <span class="icon_menu_toggle">
                        <i class="arrow_carrot-2left toggle_left"></i>
                        <i class="arrow_carrot-2right toggle_right" style="display:none"></i>
                    </span>
                </div>
            </nav>

        </div>



<style type="text/css">
#chartdiv {
	width		: 100%;
	height		: 300px;
	font-size	: 11px;
}					
</style>
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


<!-- amCharts javascript sources --> 
<script src="assets/js/amcharts.js" type="text/javascript"></script> 
<script src="assets/js/serial.js" type="text/javascript"></script> 

<script src="http://www.amcharts.com/lib/3/pie.js"></script>
<script src="http://www.amcharts.com/lib/3/themes/light.js"></script>
        <!-- Yukon Admin functions -->
        <script src="assets/js/yukon_all.min.js"></script>

	    <!-- page specific plugins -->

            <!-- c3 charts -->
            <script src="assets/lib/d3/d3.min.js"></script>
            <script src="assets/lib/c3/c3.min.js"></script>
            <!-- vector maps -->
            <script src="assets/lib/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
            <script src="assets/lib/jvectormap/maps/jquery-jvectormap-world-mill-en.js"></script>
            <!-- countUp animation -->
            <script src="assets/js/countUp.min.js"></script>
            <!-- easePie chart -->
            <script src="assets/lib/easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

            <script>
                $(function() {
                    // c3 charts
                    yukon_charts.p_dashboard();
                    // countMeUp
                    yukon_count_up.init();
                    // easy pie chart
                    yukon_easyPie_chart.p_dashboard();
                    // vector maps
                    yukon_vector_maps.p_dashboard();
                    // match height
                    yukon_matchHeight.p_dashboard();
                })
            </script>
		
        
  <script src="assets/js/bubble.js"></script>    
   
    <script src="assets/js/div.js"></script>        
    <?php  include "divdata.php";?>
    </body>
</html>
