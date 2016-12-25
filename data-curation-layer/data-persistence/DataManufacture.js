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

var bodyParser  =require('body-parser');

/**
 * @description Sorting a data object
 * @description Extract data in object using by index 
 * @module dmf(DataManufacture) 
 * @param {JSON} req - The JSON format data recieved by Syncronization module
 * @return {object} result - This module returns object which has 3 factor{
 * obj[index[0]] => contentBody
 * obj[index[1]] => timestamp
 * obj[index[2]] => userID
 * }
 */
exports.dmf = function(req){ 


var index = []; //Array for index
var obj = new Object(); //Create a object
obj = req.body;
for(var x in obj)
{
	index.push(x);
}

index.sort(function (a, b) {    
   return a == b ? 0 : (a > b ? 1 : -1); 
}); 


var result = [];
result[0] = obj[index[2]];
result[1] = obj[index[1]];
result[2] = obj[index[0]]

return result;
};

