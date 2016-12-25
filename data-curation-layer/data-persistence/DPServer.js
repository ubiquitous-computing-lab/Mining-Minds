

/**
 * @author JinHyuk Gong
 * @since 2016.11.21
 * @version 0.1
 
 * Copyright [2016] [Jinhyuk Gong]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


//DPServer.js - nodejs server for Datapersistence

/**
 * @example
 * Usage:
 * 	node DPServer.js
 */

/**
 * @description Run all of the modules.
 * @description If local file size is going to enough to store, then start sendHDFS module.
 */

//modules in node_modules.
var express		=require('express');
var bodyParser  =require('body-parser');
var requestify  =require('requestify');
var util = require('util');
var nodeProcess = require('process');
var fs = require('fs');

//modules in local.
var manu = require('./DataManufacture');
var fileio = require('./LocalFileManagement');
var store = require('./sendHDFS');
var config = require('./config.json');

//public value
var cnt;
var port = config.port;
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
//Post request.
app.post('/saveData', urlencodedParser,  function (req,res)
{
	var result = manu.dmf(req); //data sorting
    	cnt = fileio.lfm(result[0], result[1], result[2]);
	if(cnt != -1)
	{
		console.log("cnt is not -1");
		store.wc(cnt);
	}
	res.json({response:0});
});

//Use the PORT environment variable
app.listen('8083', function (){
	console.log('Server is running');
});
