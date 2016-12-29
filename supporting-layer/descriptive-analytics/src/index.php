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

/*** begin our session ***/


include "functions.php"; 
$message ="";
if (isset($_POST['submitbutton'])) {
			/*** check if the users is already logged in ***/
			if(isset( $_SESSION['user_id'] ))
			{
				$message = 'Users is already logged in';
			}
			/*** check that both the username, password have been submitted ***/
			if(!isset( $_POST['email'], $_POST['password']))
			{
				$message = 'Please enter a valid username and password';
			}
			/*** check the username is the correct length ***/
			elseif (strlen( $_POST['email']) < 4 )
			{
				$message = 'Incorrect Length for Username. Must be more than 4';
			}
			/*** check the password is the correct length ***/
			elseif (strlen($_POST['password']) < 4)
			{
				$message = 'Incorrect Length for Password. Must be more than 4';
			}
			
			
			else
			{
			
			/*** if we are here the data is valid and we can Authenticate ***/
				$email = filter_var($_POST['email'], FILTER_SANITIZE_STRING);
				$password = filter_var($_POST['password'], FILTER_SANITIZE_STRING);
				
				$message=userAuth($email,$password);
				$result=json_decode($message,true);
				if(count($result)>0){
							session_start();
							$_SESSION['user_id'] = $result[0]["userID"];
							$_SESSION['expertname'] = $result[0]['firstName']." ".$result[0]['lastName'];
							header("Location: users.php");
				}
				else
							$message = 'Incorrect Username or Password';
					
			}
			
			
}			
?><!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Mining Minds Expert View</title>
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- bootstrap framework -->
        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <!-- google webfonts -->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <!-- main stylesheet -->
		<link href="assets/css/main.min.css" rel="stylesheet" media="screen">

        <style type="text/css">
<!--
.style1 {color: #FF0000}
-->
        </style>
</head>
    <body class="login_page2">
      <div class="logo" >
      	<img src="assets/img/logo.png"  class="center-block" />
      
       </div>
        <div class="login_container">
        <?php
		
					if(!isset( $_POST['email'], $_POST['password']))
							echo $message;
		?>
            <form id="login_form" class="form-horizontal" method="post" action="">
                <h2 class="heading_a"><span class="heading_text">Log in</span></h2>
                <div class="form-group">
                    <label for="login_username" class="col-sm-3 control-label">Username</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"  id="login_username" name="email" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="login_password" class="col-sm-3 control-label">Password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="login_password"  name="password" autocomplete="off">
                    </div>
                </div>
                <div class="form-group sepH_c">
                    <div class="col-sm-9 col-sm-offset-3">
                      <input name="submitbutton" type="submit" class="btn btn-sm btn-primary btn-block" value="Log in">
                    <!--<a href="dashboard.html" class="btn btn-sm btn-primary btn-block">Log in</a> -->          
                    <span class="style1"><?php echo $message;?>
                    
                           </span></div>
              </div>
             
               
            </form>
        </div>

    </body>
</html>