<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />






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


require_once("functions.php");
require_once("sqlconn.php");
require_once("db.class.php");
require_once("dbfunctions.php");
$page= $_SERVER["PHP_SELF"];

$dbObject = new db(); // create a new object, class db()
$dbObject2 = new db(); // create a new object, class db()

 if(isset($_GET['notify'])){
 
					updatereadrow($_GET['notify']);
					
				}
			

			

$maxid = $dbObject->getAll('SELECT MAX(logID) FROM notifications'); // 1 line selection, return 1 line



if(empty($maxid))
	$maxid[0]['MAX(logID)']=0;

$inserturl="$llmserver/testWeb/webresources/testweb/SLViolations/".$maxid[0]['MAX(logID)'];
$newrowsresult=getJsonRest($inserturl);

$userlist= retrieveUserList();
$newrows=array();
for($i=0;$i<count($newrowsresult);$i++){

	$key = array_search($newrowsresult[$i]['userID'], array_column($userlist, 'userID'));
	$Name=$userlist[$key]["firstName"]. " ".$userlist[$key]["lastName"];
	
	
	
	$sql = "INSERT INTO notifications (logID, time, activityTargetDuration,userid,name,activityID)VALUES (".$newrowsresult{$i}['logID'].", '".$newrowsresult{$i}['startTime']."', '".$newrowsresult{$i}['activityTargetDuration']."', ".$newrowsresult{$i}['userID'].",'".$Name."',".$newrowsresult{$i}['activityID'].")";
	
	$result = $dbObject->execute($sql); 

	
}


// select last 10 notifications to show in push notifications header

$notificationData = $dbObject->getAll('SELECT * FROM `notifications` ORDER BY `logID` DESC limit 10'); 
$newnotify=0;
if (count($notificationData) > 0) {

	foreach($notificationData as $notification) {
	
		
					$notifydata[$i]['id']=$notification["id"];
					$notifydata[$i]['name']=$notification["name"];
					$notifydata[$i]['activityTargetDuration']=$notification["activityTargetDuration"];
					$notifydata[$i]['time']=$notification["time"];
					$notifydata[$i]['view']=$notification["view"];
					$notifydata[$i]['activityID']=$notification["activityID"];
					$notifydata[$i]['userid']=$notification["userid"];
					$notifydata[$i]['read']=$notification["read"];
					$i++;
					if($notification["view"]==0)
						$newnotify++;
	
	}

	
}

//print_r($notifydata);
//exit;

			
if(isset($_GET['notify'])){
 
 			$printalert='';
			for($j=0;$j<count($notifydata);$j++){
				if($notifydata[$j]["id"]==$_GET['notify']){
				
					if($notifydata[$j]["activityID"]==3 || 	$notifydata[$j]["activityID"]==2)
						$printalert=($notifydata[$j]["activityTargetDuration"]/60) ." hour for ".getActivityLabel($notifydata[$j]["activityID"])."  on the date ".substr($notifydata[$j]['time'],0,16);
					if($notifydata[$j]["activityID"]==16)
						$printalert="Normal Fat Eating limit crossed. ".($notifydata[$j]["activityTargetDuration"]) ." grams of fat for ".getActivityLabel($notifydata[$j]["activityID"])."  on the date ".substr($notifydata[$j]['time'],0,16);
					
				}
			
			}
	
	}				

				
?>

<header id="main_header">
                <div class="container-fluid">
                    <div class="brand_section">
                        <a href="dashboardgeneral.php"><img src="assets/img/logo.png"  height="53" alt="site_logo" ></a>
                    </div>
                    
                    <ul class="header_notifications clearfix">
        <li class="dropdown" id="tasks_dropdown"> <span class="label label-danger" id="newnotify" ><?php if($newnotify>0) echo $newnotify;?></span> <a data-toggle="dropdown" id="pushnotify" class="dropdown-toggle active"><i class="el-icon-bell"></i></a>
          <div class="dropdown-menu">
            <ul>
              <?php 
			  
			  for($j=0;$j<count($notifydata);$j++){
			  		if($notifydata[$j]["activityID"]==3 || 	$notifydata[$j]["activityID"]==8 || 	$notifydata[$j]["activityID"]==16) {
			  	
			  ?>
              <li <?php if($notifydata[$j]['read']==0) echo 'style="background:#CCCCCC";' ?>><a href="user.php?userid=<?php echo $notifydata[$j]['userid']?>&notify=<?php echo $notifydata[$j]['id']?>" >
                <div class="clearfix">
                  <div class="label label-danger pull-right"><?php if($notifydata[$j]['read']==0) echo 'unread';?></div>
                  <small class="text-muted"><?php echo $notifydata[$j]['name']?> (<?php echo substr($notifydata[$j]['time'],0,16);?>)</small> </div>
                <p> <?php 	if($notifydata[$j]["activityID"]==3 || 	$notifydata[$j]["activityID"]==8) 
				
									echo  ($notifydata[$j]['activityTargetDuration']/60)." hour for ".getActivityLabel($notifydata[$j]["activityID"]);
							if($notifydata[$j]["activityID"]==16) 
				
								echo  "Normal Fat Eating limit crossed. ".($notifydata[$j]['activityTargetDuration'])." Grams for ".getActivityLabel($notifydata[$j]["activityID"]);
							
							
							
							?>
                            
                     </p>
                </a> </li>
                
                
                <?php }}?>

         
            </ul>
          </div>
        </li>
      </ul>
                    <div class="header_user_actions dropdown">
                        <div data-toggle="dropdown" class="dropdown-toggle user_dropdown">
                            <div class="user_avatar">
                                <img src="assets/img/avatars/avatar02_tn.png" alt="" title="Jamil Hussain(carrol@example.com)" width="38" height="38">
                            </div>
                            <span class="caret"></span>
                        </div>
                        <ul class="dropdown-menu dropdown-menu-right">   
                            <li><a href="profile.html">User Profile</a></li>
                            <li><a href="index.html">Log Out</a></li>
                        </ul>
                    </div>
                </div>
            </header>
            