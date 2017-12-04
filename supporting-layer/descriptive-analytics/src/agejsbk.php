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

?>

<script>

var chart = AmCharts.makeChart( "agediv", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {"title": "24 and below",
    "value": <?php echo $agebelow24;?>  }, {
    "title": "25-32",
    "value": <?php echo $age25to32;?>  },{
    "title": "33-40",
    "value": <?php echo $age33to40;?>  } ,{
    "title": "41 and above",
    "value": <?php echo $ageabove41;?>} ],
  "titleField": "title",
  "valueField": "value",
  "labelRadius": 5,

  "radius": "42%",
  "innerRadius": "60%",
  "labelText": "[[title]]",
  "export": {
    "enabled": true
  }
} );

</script>
