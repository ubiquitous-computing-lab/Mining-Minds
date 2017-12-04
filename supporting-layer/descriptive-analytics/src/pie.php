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

?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<script src="assets/js/amcharts.js"></script>
<script src="assets/js/pie.js"></script>
<script src="assets/js/light.js"></script>
<script>

var chart = AmCharts.makeChart( "agediv", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {"title": "24 and below",
    "value": 4  }, {
    "title": "25-32",
    "value": 2  },{
    "title": "33-40",
    "value": 9  } ,{
    "title": "41 and above",
    "value": 3} ],
  "titleField": "title",
  "valueField": "value",
  "labelRadius": 5,

  "radius": "42%",
  "innerRadius": "60%",
  "labelText": "[[title]]",
  "export": {
    "enabled": true
  }
} );</script>


<style type="text/css">
#agediv {
	width		: 100%;
	height		: 500px;
	font-size	: 11px;
}					</style>
<body>

<div id="agediv"></div>					
</body>
</html>
