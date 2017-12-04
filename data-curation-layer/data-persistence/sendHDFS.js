
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
 * @description Send the data to HDFS using by HTTP.
 * @description "webhdfs-client.js" file has information about HDFS(host, user, port...).
 * @module sendHDFS
 * @param {number} - metadata for HDFS file name.
 */

var config = require('./config.json');
var localFilePath = config.filePath_local;
var HDFSFilePath = config.filePath_HDFS;

exports.wc = function(cnt){

var hdfs = require('../webhdfs-client');
var fs = require('fs');

var filepath =  + cnt;
var localFileStream = fs.createReadStream('./temp.txt');
var remoteFileStream = hdfs.createWriteStream('/kjh/'+cnt);
console.log("File write in " + filepath);

// Pipe data to HDFS
localFileStream.pipe(remoteFileStream);

// Handle errors
remoteFileStream.on('error', function onError (err) {
});
console.log("data is stored in HDFS");

};

