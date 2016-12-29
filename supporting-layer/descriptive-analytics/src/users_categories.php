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

?><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mining Minds Expert View-Edit Profile</title>
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
  <?php include "header.php";?>
  
  
  
  <!-- main content -->
  <div id="main_wrapper">
    <div class="container-fluid">
    
    <?	  include "uppermenu.php";?>
      <div class="row">
        <div class="col-md-12">
          <div class="heading_a alert alert-danger"> Bad LLM </div>
          <ul class="user_list bad">
            <li > <a href="#" data-toggle="modal" data-target="#modalLarge"> <img class="img-circle" src="assets/img/avatars/avatar08_tn.png" alt=""/>
              <h2>Iva Abshire</h2>
             
              </a> </li>
            <li > <a href="#" data-toggle="modal" data-target="#modalLarge"> <img class="img-circle" src="assets/img/avatars/avatar07_tn.png" alt=""/>
              <h2>Makenna Bartell Sr.</h2>
              </a> </li>
            <li > <a href="#" data-toggle="modal" data-target="#modalLarge"> <img class="img-circle" src="assets/img/avatars/avatar06_tn.png" alt=""/>
              <h2>Dr. Richie Schamberger</h2>
              </a> </li>
            <li > <a href="#" data-toggle="modal" data-target="#modalLarge"> <img class="img-circle" src="assets/img/avatars/avatar05_tn.png" alt=""/>
              <h2>Michele Dicki Sr.</h2>
              </a> </li>
            <li > <a href="#" data-toggle="modal" data-target="#modalLarge"> <img class="img-circle" src="assets/img/avatars/avatar12_tn.png" alt=""/>
              <h2>Keira Murray</h2>
              </a> </li>
          </ul>
          <div class="heading_a alert alert-warning"> Normal LLM </div>
          <ul class="user_list normal">
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar08_tn.png" alt=""/>
              <h2>Iva Abshire</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar07_tn.png" alt=""/>
              <h2>Makenna Bartell Sr.</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar06_tn.png" alt=""/>
              <h2>Dr. Richie Schamberger</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar05_tn.png" alt=""/>
              <h2>Michele Dicki Sr.</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar12_tn.png" alt=""/>
              <h2>Keira Murray</h2>
              </a> </li>
          </ul>
         <div class="heading_a alert alert-info"> Good LLM </div>
          <ul class="user_list good">
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar08_tn.png" alt=""/>
              <h2>Iva Abshire</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar07_tn.png" alt=""/>
              <h2>Makenna Bartell Sr.</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar06_tn.png" alt=""/>
              <h2>Dr. Richie Schamberger</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar05_tn.png" alt=""/>
              <h2>Michele Dicki Sr.</h2>
              </a> </li>
            <li > <a href="#"> <img class="img-circle" src="assets/img/avatars/avatar12_tn.png" alt=""/>
              <h2>Keira Murray</h2>
              </a> </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  
  <!-- main menu -->
  <nav id="main_menu">
    <div class="menu_wrapper">
      <ul>
        <li class="first_level"> <a href="dashboard.html"> <span class="icon_house_alt first_level_icon"></span> <span class="menu-title">Dashboard</span> </a> </li>
        <li class="first_level"> <a href="users.html"> <span class="icon_contacts_alt  first_level_icon "></span> <span class="menu-title">Users</span> </a> </li>
      </ul>
    </div>
    <div class="menu_toggle"> <span class="icon_menu_toggle"> <i class="arrow_carrot-2left toggle_left"></i> <i class="arrow_carrot-2right toggle_right" style="display:none"></i> </span> </div>
  </nav>
</div>

<div class="modal fade" id="modalLarge">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium, amet aperiam at autem blanditiis commodi culpa enim est id ipsum iusto magnam minima&hellip;</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary btn-sm">Save changes</button>
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

  <!-- jBox -->
            <script src="assets/lib/jBox-0.3.0/Source/jBox.min.js"></script>

            <script>
                $(function() {
                    // jBox
                    yukon_jBox.p_components_notifications_popups();
                })
				
				
				
            </script>
        
</body>
</html>
