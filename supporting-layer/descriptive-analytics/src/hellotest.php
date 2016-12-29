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
$value=$_GET['review'];
//activity
if($value==1){
?>
        <p>
          <input name="Walking" type="checkbox" value="1" />
Walking</p>
        <p>
          <input name="Running" type="checkbox" value="2" /> 
          Running
</p>
        <p>
          <input name="Sitting" type="checkbox" value="3" />
Sitting</p>
        <p>
          <input name="Standing" type="checkbox" value="4" />
                       Standing         </p>
<?php
//feedback
}else if($value==2){
?>
        <p>
          <input name="Postive" type="checkbox" value="1" />
Postive</p>
        <p>
          <input name="Negative" type="checkbox" value="2" /> 
          Negative
</p>
       
<?php
//recommendations
} else if($value==3){
?>
        <p>
          <input name="Walking" type="checkbox" value="1" />
Walking</p>
        <p>
          <input name="Running" type="checkbox" value="2" /> 
          Running
</p>
        <p>
          <input name="Sitting" type="checkbox" value="3" />
Sitting</p>
        <p>
          <input name="Standing" type="checkbox" value="4" />
                       Standing         </p>


<?php
//location
} else if($value==4){
?>
        <p>
          <input name="Home" type="checkbox" value="1" />
Home</p>
        <p>
          <input name="Gym" type="checkbox" value="2" /> 
          Gym
</p>
        <p>
          <input name="Office" type="checkbox" value="3" />
Office</p>
        <p>
          <input name="Standing" type="checkbox" value="4" />
                       Standing         </p>
<?php
}?>





