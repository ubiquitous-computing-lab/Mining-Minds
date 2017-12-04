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
include "functions.php"; 

/*if(!$_SESSION['user_id']){ 
		header("Location: index.php"); 
		exit; 
		
		}*/

?><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mining Minds Expert View- User Lists</title>
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
  	<?php 	include "header.php"; ?>	
  
  <!-- breadcrumbs -->
  <nav id="breadcrumbs">
    
  </nav>
  
  <!-- main content -->
  <div id="main_wrapper">
    <div class="container-fluid">
    <?php include "uppermenu.php";	?>
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-sm-4 col-md-3">
              <select name="contactList_sort" id="contactList_sort" class="form-control input-sm">
                <option value="" selected>-- Sort by --</option>
                <option value="name">Name</option>
                <option value="name_desc">Name (desc)</option>
              </select>
            </div>
            <div class="col-sm-4 col-md-3">
              <input type="text" name="contactList_search" id="contactList_search" class="form-control input-sm" placeholder="Find user...">
            </div>
          </div>
          <hr/>
          <ul class="contact_list">
            <?php  include "userlist.php"; ?> 
           
           
          </ul>
        </div>
      </div>
    </div>
  </div>
   <?php  include "leftsidebar.php"; ?> 
  <!-- main menu -->
  
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

<!-- typeahead --> 
<script src="assets/lib/typeahead/typeahead.bundle.min.js"></script> 
<!-- fastclick --> 
<script src="assets/js/fastclick.min.js"></script> 
<!-- match height --> 
<script src="assets/lib/jquery-match-height/jquery.matchHeight-min.js"></script> 
<!-- scrollbar --> 
<script src="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script> 

<!-- Yukon Admin functions --> 
<script src="assets/js/yukon_all.min.js"></script> 

<!-- page specific plugins --> 

<!-- shuffle.js --> 
<script src="assets/lib/shuffle/jquery.shuffle.modernizr.min.js"></script> 
<script>
                $(function() {

                    // contact list
                    yukon_contact_list.init();
                })
            </script> 

<!-- style switcher -->
<?php  include "footer.php";?>  
</body>
</html>
