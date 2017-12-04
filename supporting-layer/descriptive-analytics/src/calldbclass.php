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



	include('db.class.php'); // call db.class.php
	$bdd = new db(); // create a new object, class db()

	$Users = $bdd->getAll('SELECT * FROM `notifications` ORDER BY `logID` DESC limit 10'); // select ALL from users
	$nbrUsers = count($Users);
	
	echo $nbrUsers.' users in the database<br />';


?>sasa