// Include webhdfs module
var WebHDFS = require('webhdfs');

// Create a new
var hdfs = WebHDFS.createClient({
  user: 'hadoop', // Hadoop user
  host: 'hadoop-VirtualBox', // Namenode host
  port: 50070 // Namenode port
});

module.exports = hdfs;
