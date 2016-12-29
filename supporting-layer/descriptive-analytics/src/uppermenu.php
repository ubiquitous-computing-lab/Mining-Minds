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

?><style type="text/css">
.style1 {
	font-size: 36px
}
</style>
<?php



?>
<div class="row">
  <div class="col-lg-4 col-sm-6">
    <div class="col-md-12 main-btn btn btn-primary <?php if (strpos($page,'dashboard') == false)  echo "btn-outline";?>"> <span class="style1"> <a href="dashboardgeneral.php">Dashboard</a></span> </div>
  </div>
  <div class="col-lg-4 col-sm-6">
    <div class="col-md-12 main-btn btn btn-primary <?php if (strpos($page,'user') == false)  echo "btn-outline";?>"> <span class="info_box_icon "></span> <span> <span class="style1"><a href="users.php">Users</a></span></span> </div>
  </div>
  <div class="col-lg-4 col-sm-6">
    <div class="col-md-12 main-btn btn btn-primary <?php if (strpos($page,'query') == false)  echo "btn-outline";?>  style1"><a href="query.php"> Query Panel </a></div>
  </div>
</div>
