<style type="text/css">
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
