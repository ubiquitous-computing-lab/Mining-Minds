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
$value=$_GET['options'];
//activity
if($value==1){
?><table width="200" border="0">
  <tr>
    <td><p>
    
    <input id="querytype" name="querytype" type="hidden" value="activity" />
          <input name="Walking" type="checkbox" value="2" />
Walking</p>
        <p>
          <input name="Running" type="checkbox" value="1" /> 
          Running
</p>
        <p>
          <input name="Sitting" type="checkbox" value="3" />
Sitting</p>
        <p>
          <input name="Standing" type="checkbox" value="8" />
                         Standing       </p> </td>
    <td><p> <input type="radio" name="graph" value="Pie" checked="checked">Pie</p> <p>
  <input type="radio" name="graph" value="Line">Line</p>
        </td>
  </tr>
</table>

       

<?php
//recommendations
} else if($value==2){
?>

 <table width="200" border="0">
  <tr>
    <td>
        <p>
        <input id="querytype" name="querytype" type="hidden" value="rec" />
          <input name="Standing" type="checkbox" value="8" />
Standing</p>
        <p>
          <input name="LyingDown" type="checkbox" value="7" /> 
          Lying Down
</p>
        <p>
          <input name="Sitting" type="checkbox" value="3" />
Sitting</p>
        <p>
       
                       
        </td>
    <td> <p><input type="radio" name="graph" value="Line" checked="checked">Line</p>
        </td>
  </tr>
</table>                


<?php
//location
} else if($value==3){
?>
      <table width="200" border="0">
  <tr>
    <td>  <p>
         <input id="querytype" name="querytype" type="hidden" value="location" />
          <input name="Home" type="checkbox" value="Home" />
Home</p>
        <p>
          <input name="Gym" type="checkbox" value="Gym" /> 
          Gym
</p>
        <p>
          <input name="Office" type="checkbox" value="Office" />
Office</p>

 </td>
    <td><p> <p><input type="radio" name="graph" value="Line" checked="checked">Line</p>
        </td>
  </tr>
</table>
   <?php
//Nutrition
} else if($value==5){
?>
      <table width="200" border="0">
  <tr>
    <td>  <p>
         <input id="querytype" name="querytype" type="hidden" value="nutrition" />
          <input name="Fat" type="checkbox" value="Fat" />
Fat</p>
        <p>
          <input name="Carbohydrates" type="checkbox" value="Carbohydrates" /> 
          Carbohydrates
</p>
        <p>
          <input name="Protein" type="checkbox" value="Protein" />
Protein</p>

 </td>
    <td><p> <p><input type="radio" name="graph" value="Line" checked="checked">Line</p>
        </td>
  </tr>
</table> 
<?php
//SNS
} else if($value==4){?>


<table width="200" border="0">
  <tr>
    <td>  <p>
         <input id="querytype" name="querytype" type="hidden" value="SNS" />
          <input name="Food" type="radio" value="Fruits" checked="checked"/>
Fruits</p>
        <p>
          <input name="Food" type="radio" value="Vegetables" /> 
          Vegetables
</p>
        <p>
          <input name="Food" type="radio" value="Meat" />
Meat</p>

 </td>
    <td><p> <p><input type="radio" name="graph" value="Line" checked="checked">Line</p>
        </td>
  </tr>
</table> 

<?php
}?>

