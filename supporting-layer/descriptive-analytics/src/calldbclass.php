<?php



	include('db.class.php'); // call db.class.php
	$bdd = new db(); // create a new object, class db()

	$Users = $bdd->getAll('SELECT * FROM `notifications` ORDER BY `logID` DESC limit 10'); // select ALL from users
	$nbrUsers = count($Users);
	
	echo $nbrUsers.' users in the database<br />';


?>sasa