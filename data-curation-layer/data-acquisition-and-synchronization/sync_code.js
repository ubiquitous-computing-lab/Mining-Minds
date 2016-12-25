/*Copyright [2016] [Dinh-Mao Bui]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

/* ***********************************************************************************************
Author: Dinh-Mao Bui
Affiliation: UC Lab
Project: Mining Mind
Created date: 2016-03-23
Revised date: 2016-10-27
Last revised date: 2016-11-10
Reason: add audio/image support
Functionality: do the synchronization for unlimited data source.
*********************************************************************************************** */

/*Required libraries***********************************************************************************************/
var express		=require('express');
var loki        =require('lokijs');
var bodyParser  =require('body-parser');
var requestify  =require('requestify');
var fs = require('fs');
var util = require('util');
var nodeProcess = require('process');
var config = require('./config.json');
var apiHelper=require('./api.js');
var multer  =   require('multer');
/*Required libraries***********************************************************************************************/

/*Load configuration***********************************************************************************************/
var nDataSource=config.nDataSource;	// number of data source. 
									//Theoretically, this source code can
									//handle unlimited number of data source.
var deadline =config.deadline;//deadline to issue the forced (bad) data sync
var destUrl=config.destUrl;
var serverPort=config.serverPort;	// number of data source. 
/*Load configuration***********************************************************************************************/

/*Global variables***********************************************************************************************/
//var tempDate=new Date("2000/01/01");
//var summaryDuration=tempDate.getTime();
//var nSync=0;
var storage =   multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, './uploads');
  },
  filename: function (req, file, callback) {
    callback(null, file.fieldname + '-' + Date.now());
  }
});
var upload = multer({ storage : storage}).single('userFile');//name of upload MUST equals to the name of FILE FIELD in uploaded form// ATTENTIONNNN
/*Global variables***********************************************************************************************/

/*Server initialization***********************************************************************************************/
var app	=express();
	app.use(bodyParser.json());
var urlencodedParser = bodyParser.urlencoded({ extended: true })
var db =new loki('sync_temp');
var buffer =db.addCollection('buffer');//main collection for data sync
var historyData =db.addCollection('history');//most previous data for covering the missing value
/*Server initialization***********************************************************************************************/

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

//following is the processing code for the HTTP POST restful, the route /newData is the entry point for all of data source
app.post('/newData', urlencodedParser,  function (req,res){	
		console.log(req.body);
		var revDataType= req.body.id;//id/type of the data source. For example: android source is 1, k'nect is 2, accelerometer is 3, GPS is 4.
        var revUserId = req.body.revUserId;//id of user
        var revTimeStamp = req.body.timestamp;//time stamp of incoming data.
        var revContent = req.body.data;//content of the data				
		var timeDataCome=Date.now();		
		var timeObjData=new Date(timeDataCome);
		
		/*console.log("xxxxxxxxxxxxxxxxxxxxxxxx");
		console.log("Data comes at: "+timeObjData.toUTCString());
		console.log("Data type: "+revDataType);
		console.log("User ID: "+revUserId);
		console.log("Time stamp: "+revTimeStamp);
		console.log("Data content: "+revContent);
		console.log("End xxxxxxxxxxxxxxxxxxxxxxxx");*/
		
		/*We depend on the existence of the user to determine that the data is existed or not. The logic is simple.
		There are three cases: 
		1. data of type X is existed, and the incoming data is also X--> update the data of type X with the new one and check for the sync completion.
		2. data of type X is not existed, but the data of the other types--> it means that the sync has already 
			started. Just create new record for X and check for the sync completion.
		3. No data of any type is existed--> Create the record of incoming type and start the sync.
			Note: only the first data can start the sync process. The later data are only and check for the sync completion.
			If the quantity is exceeded, then send the data collection, save one copy to history and clear the sent data from buffer.*/
        var record = buffer.find({userID: revUserId});
        if(record.length){//the first and second case.
			var dataNotExist=1;//flag for data existence.
			for(var i = 0; i < record.length; i++){//iterate through the data to check for existence.
				if(record[i].dataType == revDataType){//data type is matched--> update new data and break the iteration.
					record[i].timeStamp=revTimeStamp;
					record[i].contentBody=revContent;
					buffer.update(record[i]);
					dataNotExist=0;//data existed, no need to add new data.
					break;
				}
			}
			if(dataNotExist){//gone through the data checking but no matched--> create new record for the data.
				addNewData(buffer,revDataType,revTimeStamp,revContent,revUserId);
            }          
        }else{//no data existence of any type of source. Create new record and start the sync process. The sync handle is also keep into the record.
			var badSyncClock=setTimeout(function(){ badSync(buffer,revUserId,historyData) },deadline);
			//console.log("++++++++");
			//var time2Sync=Date.now();
			//summaryDuration=summaryDuration-time2Sync;
			//var timeObj=new Date(time2Sync);
			//console.log("Time to start sync:"+ timeObj);
			//console.log("++++++++");
			//this called the bad sync because it relies on the deadline. When deadline comes but some data are still missing, then cover them with the most previous historical data.
            addNewData(buffer,revDataType,revTimeStamp,revContent,revUserId,badSyncClock,1);			
        }
		var xSync=apiHelper.coreSync(buffer,revUserId,historyData,nDataSource,config);		
		res.json(xSync);
});

app.post('/newFile',function(req,res){
    upload(req,res,function(err) {
		console.log(util.inspect(req));
        if(err) {
            return res.end("Error uploading file.");
        }
		
		/*console.log(util.inspect(req.file));				
		console.log(req.body.revUserId);
		console.log(req.body.timestamp);
		console.log(req.file.filename);	*/		
        res.end("File is uploaded");
		var exttype = req.file.mimetype.split("/");
		var ext=exttype[1];
		var type=exttype[0];
		var revDataType="";//id/type of the data source. For example: audio is 5, video/image is 6.
		if(type=="audio")	
			revDataType=5;
		else
			revDataType=6;
        var revUserId = req.body.revUserId;//id of user
        var revTimeStamp = req.body.timestamp;//time stamp of incoming data.
        var revContent = type+"_"+revUserId+'_'+revTimeStamp+'.'+ext;//content of the data
		fs.rename('./uploads/'+req.file.filename, './uploads/'+revContent, function(err) {
		if ( err ) console.log('ERROR: ' + err);
		});
		var record = buffer.find({userID: revUserId});
        if(record.length){//the first and second case.
			var dataNotExist=1;//flag for data existence.
			for(var i = 0; i < record.length; i++){//iterate through the data to check for existence.
				if(record[i].dataType == revDataType){//data type is matched--> update new data and break the iteration.
					record[i].timeStamp=revTimeStamp;
					record[i].contentBody=revContent;
					buffer.update(record[i]);
					dataNotExist=0;//data existed, no need to add new data.
					break;
				}
			}
			if(dataNotExist){//gone through the data checking but no matched--> create new record for the data.
				addNewData(buffer,revDataType,revTimeStamp,revContent,revUserId);
            }          
        }else{//no data existence of any type of source. Create new record and start the sync process. The sync handle is also keep into the record.
			var badSyncClock=setTimeout(function(){ badSync(buffer,revUserId,historyData) },deadline);
			//console.log("++++++++");
			//var time2Sync=Date.now();
			//summaryDuration=summaryDuration-time2Sync;
			//var timeObj=new Date(time2Sync);
			//console.log("Time to start sync:"+ timeObj);
			//console.log("++++++++");
			//this called the bad sync because it relies on the deadline. When deadline comes but some data are still missing, then cover them with the most previous historical data.
            addNewData(buffer,revDataType,revTimeStamp,revContent,revUserId,badSyncClock,1);			
        }
		var xSync=apiHelper.coreSync(buffer,revUserId,historyData,nDataSource,config);	
		
		
    });
});

/*****************************************************
Function: badSync
Content: bad guys do the bad thing if the coreSync does not work well because of the shortage of data.
Input parameters: badSync(objLoki,mUserId,history)
1. objLoki: the reference to the buffer collection.
2. mUserId: the id of the user.
3. history: the reference to the history collection.
Output: no.
*****************************************************/
function badSync(objLoki,mUserId,history)
{	
	//Query the most previous historical data collection for data covering.
	var record = history.find({userID: mUserId});	
	//Force the data synchronization no matter the data is enough or not.
	//Remember that before doing the bad sync, I also check for the chance of good sync for one last time. If we are lucky, so we will not miss the latest data.
	var xSync= apiHelper.coreSync(objLoki,mUserId,history,nDataSource,config,1,record[0]);	
}

/*****************************************************
Function: addNewData
Content: as its name, add new data to the buffer.
Input parameters: coreSync(objLoki,dType,dTstamp,dContent,dUserid,syncClock,first_touch)
1. objLoki: the reference to the buffer collection.
2. dType,dTstamp,dContent,dUserid: the content and meta of the incoming data.
3. syncClock: optional, the reference to the bad sync clock. We need to keep this ref to cancel it later if the sync completed before the deadline.
4. first_touch: optional, this flag indicates that the sync needs to start after this first record is created.

Output: no
*****************************************************/
function addNewData(objLoki,dType,dTstamp,dContent,dUserid,syncClock,first_touch)
{
	var dChunk={};
	dChunk.dataType = dType;
	dChunk.timeStamp = dTstamp;
	dChunk.contentBody=dContent;
	dChunk.userID=dUserid;
	if(typeof first_touch != 'undefined')
			{
				dChunk.syncStartTime=dTstamp;
				dChunk.syncYet=0;//the handle to determine that this is the first record.
				dChunk.syncClockRef=syncClock;
			}
	objLoki.insert(dChunk);
	//console.log(util.inspect(objLoki)) 
}
//check whether any instances of node js are existed -->
var nodePID = fs.readFileSync('pid','utf8');
var checkInstance=require('is-running')(nodePID);
//<--check whether any instances of node js are existed
if (!checkInstance)//no instance
{
//start the syncronization server on port 8081
app.listen(serverPort, function (){
	console.log('Synchronization server is listening at port '+serverPort+'.');
	if (nodeProcess.pid) {
		fs.writeFile("pid", nodeProcess.pid, function(err) {
		if(err) {
			return console.log(err);
		}
		//console.log("The file was saved!"+destUrl[0]["url"]);
		apiHelper.sensorQtyAdjust(5,config);
		//apiHelper.destAdjust([{"url":"http://xyz2.com"},{"url":"http://xyz3.com"}],config);
		}); 
	
	}
});
}
else{
console.log("Cannot start Synchronization server because of the existence of other instance.");
}







/*****************************************************
Apendix:
data form history:
[
	{
	  "userID": "12232",
	  
	  "timeStamp_1232": "2016",
	  "contentBody_1232": "con meo",
	  
	  "timeStamp_123": "2016",
	  "contentBody_123": "con meo",
	  
	  "meta": {
		"revision": 0,
		"created": 1458793197366,
		"version": 0
	  },
	  "$loki": 1
	}
]
data from buffer:
[
  {
    "user_122324": {
      "data_122": {
        "timeStamp": "20160312",
        "content": "zhgbckdjfkld"
      }
    },
    "meta": {
      "revision": 0,
      "created": 1458752201592,
      "version": 0
    },
    "$loki": 1
  },
  {
    "user_122324": {
      "data_122": {
        "timeStamp": "20160312",
        "content": "zhgbckdjfkld"
      }
    },
    "meta": {
      "revision": 0,
      "created": 1458752201592,
      "version": 0
    },
    "$loki": 1
  }
]
*****************************************************/