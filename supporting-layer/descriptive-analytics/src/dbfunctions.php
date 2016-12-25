<?php 
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
