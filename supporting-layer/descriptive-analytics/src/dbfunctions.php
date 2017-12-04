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
require_once("sqlconn.php");

function updaterows($userid){

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mmv2";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) 
    die("Connection failed: " . $conn->connect_error);


$sql = "UPDATE notifications SET `view`='1'";
$result = $conn->query($sql);

}


function updatereadrow($id){

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mmv2";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) 
    die("Connection failed: " . $conn->connect_error);


$sql = "UPDATE notifications SET `read`='1' where id=".$id ;
$result = $conn->query($sql);

}






?>
