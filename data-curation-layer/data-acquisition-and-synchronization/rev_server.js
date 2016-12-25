/************************************************************************************************
Author: Dinh-Mao Bui
Affiliation: UC Lab
Project: Mining Mind
Created date: 2016-03-23
Revised date: 2016-10-27
Functionality: receiving server 1.
************************************************************************************************/

/*Required libraries***********************************************************************************************/
var express		=require('express');
var bodyParser  =require('body-parser');
var requestify  =require('requestify');
var util = require('util');
var nodeProcess = require('process');

/*Server initialization***********************************************************************************************/
var app	=express();
	app.use(bodyParser.json());
var urlencodedParser = bodyParser.urlencoded({ extended: true })
/*Server initialization***********************************************************************************************/

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

//following is the processing code for the HTTP POST restful, the route /newData is the entry point for all of data source
app.post('/saveData', urlencodedParser,  function (req,res){	
		console.log("userID:"+req.body.userID);		
		console.log("============");
		res.json({response:0});
});

app.listen('8082', function (){
	console.log('Receiving server is listening at port 8082.');
});