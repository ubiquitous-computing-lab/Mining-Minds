
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


/**
 * @description Storing data in local(temp.txt).
 * @description Storing metadata in local(metadata.txt).
 * @module lfm(LocalFileManagement)
 * @param {string} parsed sequential JSON data.
 * @return {number} cnt
 */

var config = require('./config.json');
var fs = require('fs');
var unitSize = config.fileSizeUnit;

exports.lfm = function(userID, timestamp, contentbody){	

	var cnt;
	var filedata; 

	filedata = fs.readFileSync('temp.txt', 'utf-8');
	fs.writeFileSync('temp.txt', filedata + '\r\n' + userID + '  ' + timestamp + '  ' + contentbody);

	var stats = fs.statSync("temp.txt");
	var fileSizeInBytes = stats["size"];
	console.log("temp.txt file size is : " + fileSizeInBytes);

	if(fileSizeInBytes < unitSize)
	{
		console.log("File is not full");
		return -1;
	}
	else
	{
		cnt = fs.readFileSync('metadata.txt', 'utf-8');
		fs.writeFileSync('metadata.txt', parseInt(cnt)+1);
		console.log("File is Full : is seding to HDFS Server!");
		return cnt;
	}

	return -1;
};
