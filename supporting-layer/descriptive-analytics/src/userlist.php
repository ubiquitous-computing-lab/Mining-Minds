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

?><?php 
$result=array();
$result= retrieveUserList();
 //echo "<pre> hello";
	// print_r($result);
$male=0;$female=0;
for($i=0;$i<count($result);$i++){

?>
              <li class="contact_item"  data-name="<?php echo $result[$i]['firstName']." ". $result[$i]['lastName'];?>"> <a href="user.php?userid=<?php echo $result[$i]['userID'];?>"> <img class="img-circle" src="members/<?php if($result[$i]['userID'] && $result[$i]['userID']<42) echo $result[$i]['userID'].".jpg"; else echo "default.png";?>" alt="" width="76" height="76"/> <span class="contact_content">
              <h2><?php echo $result[$i]['firstName']." ". $result[$i]['lastName'];?></h2>
              <span class="text-muted"><?php echo $result[$i]['occupationDescription'];?></span> <small class="text-muted"><?php echo $result[$i]['emailAddress'];?></small> </span> </a> </li>
                
                
                

<?php }?>
             