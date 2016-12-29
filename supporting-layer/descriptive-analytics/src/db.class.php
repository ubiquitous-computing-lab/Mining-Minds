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


//DB class for LLM notifications for the expert
//DB is used  for keeping track of viewed and unread notifications

class db {
	private $conn;
	private $host;
	private $user;
	private $password;
	private $baseName;
	private $port;
	private $Debug;
	
	
	
//contructor 

	
	
	
	function __construct($params=array()) {
		$this->conn = false;
		$this->host = 'localhost'; //hostname
		$this->user = 'root'; //username
		$this->password = ''; //password
		$this->baseName = 'mmv2'; //name of your database
		$this->port = '3306';
		$this->debug = true;
		$this->connect();
	}
 
 
 //closing the DB connection
	function __destruct() {
		$this->disconnect();
	}
	
	
//	connection to the DB
	
	function connect() {
		if (!$this->conn) {
			$this->conn =mysqli_connect($this->host, $this->user, $this->password,$this->baseName);	
			//mysqli_select_db($this->baseName, $this->conn); 
			mysqli_set_charset($this->conn,'utf8');
			
			if (!$this->conn) {
				$this->status_fatal = true;
				echo 'Connection BDD failed';
				die();
			} 
			else {
				$this->status_fatal = false;
			}
		}
 
		return $this->conn;
	}
 
	function disconnect() {
		if ($this->conn) {
			@pg_close($this->conn);
		}
	}
	
	
	
	// getOne function: when you need to select only 1 line in the database
	
	function getOne($query) { 
		$cnx = $this->conn;
		if (!$cnx || $this->status_fatal) {
			echo 'GetOne -> Connection BDD failed';
			die();
		}
 
		$cur = @mysqli_query($query, $cnx);
 
		if ($cur == FALSE) {		
			$errorMessage = @pg_last_error($cnx);
			$this->handleError($query, $errorMessage);
		} 
		else {
			$this->Error=FALSE;
			$this->BadQuery="";
			$tmp = mysqli_fetch_array($cur, MYSQL_ASSOC);
			
			$return = $tmp;
		}
 
		@mysqli_free_result($cur);
		return $return;
	}
	
	
	// getAll function: when you need to select more than 1 line in the database
	
	function getAll($query) { 
		$cnx = $this->conn;
		if (!$cnx || $this->status_fatal) {
			echo 'GetAll -> Connection BDD failed';
			die();
		}
		
		mysqli_query( $this->conn,"SET NAMES 'utf8'");
		$cur = mysqli_query( $this->conn,$query);
		$return = array();
		
		while($data = mysqli_fetch_assoc($cur)) { 
			array_push($return, $data);
		} 
 
		return $return;
	}
	
	
	 // execute function: to use INSERT or UPDATE
	 
	 
	function execute($query,$use_slave=false) {
		$cnx = $this->conn;
		if (!$cnx||$this->status_fatal) {
			return null;
		}
 
		$cur = @mysqli_query($query, $cnx);
 
		if ($cur == FALSE) {
			$ErrorMessage = @mysqli_last_error($cnx);
			$this->handleError($query, $ErrorMessage);
		}
		else {
			$this->Error=FALSE;
			$this->BadQuery="";
			$this->NumRows = mysqli_affected_rows();
			return;
		}
		@mysqli_free_result($cur);
	}
	
	
	 // Handle errors and exception for DB connection Fail
	
	
	function handleError($query, $str_erreur) {
		$this->Error = TRUE;
		$this->BadQuery = $query;
		if ($this->Debug) {
			echo "Query : ".$query."<br>";
			echo "Error : ".$str_erreur."<br>";
		}
	}
}
?>