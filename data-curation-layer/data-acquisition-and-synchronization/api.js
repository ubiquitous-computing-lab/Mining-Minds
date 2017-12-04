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
var nodeProcess = require('process');
var jsonFile = require('jsonfile');
var util = require('util');
var request = require("request");
function isJson(item) {
    item = typeof item !== "string"
        ? JSON.stringify(item)
        : item;

    try {
        item = JSON.parse(item);
    } catch (e) {
        return false;
    }

    if (typeof item === "object" && item !== null) {
        return true;
    }

    return false;
}

function isURL(str) {
  var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
  '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
  '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
  '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
  '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
  '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
  return pattern.test(str);
}

function syncStart (mergeDataBody, jsonConfig)
{	
	var jsonUrl=jsonConfig.destUrl;		
	var requestData=JSON.stringify(mergeDataBody);
	console.log("send data-------------------: " + requestData)	
	for(var i=0;i<jsonUrl.length;i++)
	{
		requestUrl=jsonUrl[i]["url"]+'/saveData';
		console.log("Sending data to URL: " + requestUrl);
		request({
		url: requestUrl,
		method: "POST",
		json: true,
		headers: {
			"content-type": "application/json",
		},
		json: mergeDataBody //no need to stringify, we can send json directly!
		},function (error, response, body) {
			if (!error && response.statusCode === 200) {
				console.log(body)
			}
			else {

				console.log("error: " + error)
				//console.log("response.statusCode: " + response.statusCode)
				//console.log("response.statusText: " + response.statusText)
			}
		});
	}
}

var sensorQtyAdjust = function(sensorNumber, jsonConfig) {
	try {
		if(isNaN(sensorNumber)) throw "the input should be a number. Adjustment is cancelled.";
		if(jsonConfig.nDataSource!=sensorNumber)
		{
			jsonConfig.nDataSource=sensorNumber;		
			jsonFile.writeFileSync("./config.json",jsonConfig);
			console.log("Notice: adjustment completed.");
			nodeProcess.exit(1);			
		}else{
			console.log("Notice: The input value is the same as the last one. Adjustment rejected.");
		}
		
	}catch(err)
	{
		console.log("Error: "+err);
	}
};

var serverPortAdjust = function(port, jsonConfig) {
	try {
		if(isNaN(sensorNumber)) throw "the input should be a number. Adjustment is cancelled.";
		if(jsonConfig.serverPort!=port)
		{
			jsonConfig.serverPort=port;		
			jsonFile.writeFileSync("./config.json",jsonConfig);
			console.log("Notice: adjustment completed.");
			nodeProcess.exit(1);			
		}else{
			console.log("Notice: The input value is the same as the last one. Adjustment rejected.");
		}
		
	}catch(err)
	{
		console.log("Error: "+err);
	}
};


var deadlineAdjust = function(dueDate, jsonConfig) {
	try {
		if(isNaN(dueDate)) throw "the input should be milliseconds. Adjustment is cancelled.";
		if(jsonConfig.deadline!=dueDate)
		{
			jsonConfig.deadline=dueDate;	
			jsonFile.writeFileSync("./config.json",jsonConfig);
			console.log("Notice: adjustment completed.");
			nodeProcess.exit(1);
		}else{
			console.log("Notice: The input value is the same as the last one. Adjustment rejected.");
		}
	}catch(err)
	{
		console.log("Error: "+err);
	}
};
var destAdjust= function(jsonUrl, jsonConfig) {
	try {
		if(!isJson(jsonUrl)) throw "the input should be in JSON format. Adjustment is cancelled.";
		for(var i=0;i<jsonUrl.length;i++)
		{			
			if(!isURL(jsonUrl[i]["url"])) throw "the input should be URL. Adjustment is cancelled.";
		}		
		if(JSON.stringify(jsonConfig.destUrl)!=JSON.stringify(jsonUrl))
		{
			jsonConfig.destUrl=jsonUrl;	
			jsonFile.writeFileSync("./config.json",jsonConfig);
			console.log("Notice: adjustment completed.");
			nodeProcess.exit(1);
		}else{
			console.log("Notice: The input value is the same as the last one. Adjustment rejected.");
		}
	}catch(err)
	{
		console.log("Error: "+err);
	}
};

/*****************************************************
Function: coreSync
Content: every time a data comes, check for enough data or not, then do the good sync as we expected.
Input parameters: coreSync(objLoki,mUserId,history,badMerge,historyRecord)
1. objLoki: the reference to the buffer collection.
2. mUserId: the id of the user.
3. history: the reference to the history collection.
4. badMerge: optional, this flag indicates that the coreSync needs to check for syncronization for one last time, then do the forced sync.
5. historyRecord: the record that is used to cover the missing data.
Output:
1. return 1 if the sync is not performed.
2. return the synced json if enough data is collected by good or bad sync.
*****************************************************/
var coreSync= function(objLoki,mUserId,history,nDataSource,jsonConfig,badMerge,historyRecord,syncType) {
	
	var mergeDataBody=1;
	//var recordSyncYet = objLoki.find({'$and': [{syncYet:1},{userID: mUserId}]});
	//if(recordSyncYet.length){return mergeDataBody;}//has already synced!
	objLoki.find({userID: mUserId});
	var record = objLoki.find({userID: mUserId});
	//console.log("record.length"+record.length);
	if(record.length==nDataSource)//enough quantity. start merging the data and send.
	{
		mergeDataBody={};//all the records are merged into this variable.
		mergeDataBody["userID"]=mUserId;	
		for(var i = 0; i < record.length; i++){//merge data process
			/*if(record[i].syncYet == 0){
				record[i].syncYet=1;
				record[i].syncEndTime=Date.now();
				objLoki.update(record[i]);
				clearTimeout(record[i].syncClockRef);
				console.log("clear bad sync for "+record[0].userID+"---"+record[i].syncClockRef);
			}*/
			mergeDataBody["timeStamp_"+record[i].dataType]=record[i].timeStamp;
			mergeDataBody["contentBody_"+record[i].dataType]=record[i].contentBody;
			clearTimeout(record[i].syncClockRef);//clear the bad sync handle.
			objLoki.remove(record[i]);//remove the merged record.
		}
		var recordHistory = history.find({userID: mUserId});//find the latest historical data of this user
		if(recordHistory.length){history.remove(recordHistory);}//delete the latest one.
		history.insert(mergeDataBody);//insert with the new one
		//console.log(JSON.stringify(history));
	}else{// not enough data.
		if(typeof badMerge == 'undefined')//but the deadline is not exceed, no need for bad sync.
		{
			return mergeDataBody;//return 1 to indicate that the data is not synced yet.
		}else{//deadline is exceeded, let's do the bad thing.
			if(typeof historyRecord != 'undefined')//the historical data is ok to cover the missing data.
			{	
				mergeDataBody=historyRecord;//fill the data collection with the historical data.
				for(var i = 0; i < record.length; i++){//this moment, I add the new data to data collection.
					/*if(record[i].syncYet == 0){
							record[i].syncYet=1;
							record[i].syncEndTime=Date.now();
							objLoki.update(record[i]);
							console.log("do the bad sync for "+record[0].userID+"---"+record[i].syncClockRef);
						}*/
					mergeDataBody["timeStamp_"+record[i].dataType]=record[i].timeStamp;
					mergeDataBody["contentBody_"+record[i].dataType]=record[i].contentBody;
					objLoki.remove(record[i]);//remove the merged record.
				}
				console.log("?????????????????????Data synced by badSync????????????????");
			}else{//there is no historical data, reset the bad sync to wait for enough data. There is no other option.
				/*console.log("The history data is not available to cover the missing data.");
				var badSyncClockAgain=setTimeout(function(){ badSync(objLoki,mUserId,history)},deadline);//reset the bad sync.
				//update the first record to keep the bad sync handle. the syncYet key keeps the role of an anchor to define the first record.
				var reSync = objLoki.find({'$and': [{syncYet:0},{userID: mUserId}]});
				reSync[0].syncClockRef=badSyncClockAgain;
				objLoki.update(reSync[0]);
				*/
				console.log("?????????????????????Data synced by forcedSync????????????????");
				mergeDataBody={};//all the records are merged into this variable.
				mergeDataBody["userID"]=mUserId;	
				for(var i = 0; i < record.length; i++){//merge data process					
					mergeDataBody["timeStamp_"+record[i].dataType]=record[i].timeStamp;
					mergeDataBody["contentBody_"+record[i].dataType]=record[i].contentBody;
					clearTimeout(record[i].syncClockRef);//clear the bad sync handle.
					objLoki.remove(record[i]);//remove the merged record.
				}				
			}
		}
	}
	console.log("********");
	console.log("-------");
	console.log("Synced data:");
	console.log("-------");
	//console.log(JSON.stringify(mergeDataBody));
	console.log(util.inspect(mergeDataBody));
	console.log("++++++++");
	//var time2Sync=Date.now();
	//var timeObj=new Date(time2Sync);
	//summaryDuration=summaryDuration+time2Sync;	
	//nSync=nSync+1;
	//console.log("Time to end sync:"+ timeObj.toUTCString());
	//console.log("Average sync duration:"+ ((summaryDuration-tempDate.getTime())/nSync));
	console.log("********");
	
	syncStart(mergeDataBody,jsonConfig);
	return mergeDataBody;
};
exports.sensorQtyAdjust = sensorQtyAdjust;
exports.deadlineAdjust = deadlineAdjust;
exports.destAdjust = destAdjust;
exports.coreSync = coreSync;
exports.serverPortAdjust = serverPortAdjust;