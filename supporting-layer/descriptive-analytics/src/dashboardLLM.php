<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mining Minds Expert View-Edit Profile</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- favicon -->
<!-- bootstrap framework -->
<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- icon sets -->
<!-- elegant icons -->
<link href="assets/icons/elegant/style.css" rel="stylesheet" media="screen">
<!-- elusive icons -->
<link href="assets/icons/elusive/css/elusive-webfont.css" rel="stylesheet" media="screen">
<!-- flags -->
<link rel="stylesheet" href="assets/icons/flags/flags.css">
<!-- scrollbar -->
<link rel="stylesheet" href="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
<!-- google webfonts -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<!-- main stylesheet -->
<link href="assets/css/main.min.css" rel="stylesheet" media="screen" id="mainCss">
<!-- moment.js (date library) -->
<script src="assets/js/moment-with-langs.min.js"></script>
</head>
<body class="side_menu_active side_menu_expanded">
<div id="page_wrapper">
  <!-- header -->
  <?php
  
   require_once "functions.php";	
   include "header.php";?>
  <!-- main content -->
  <div id="main_wrapper">
    <div class="container-fluid">
      <?php include "uppermenu.php";
	  
	  
	  $userlist= array();
	  $url="$llmserver/testWeb/webresources/testweb/TotalViolations";
	  $result=getJsonRest($url);
	  
	  
	  $userlist= retrieveUserList();
	  //echo "<pre> hello";
	//print_r($result);
	 
	 class dashboardLLM {


   		private $data = array();
	
	
		public function __set($variable, $value){
        $this->data[$variable] = $value;
    }

    	public function __get($variable){
			if(isset($this->data[$variable])){
				return $this->data[$variable];
			}else{
				die('Unknown variable.');
			}
    }
		
		
		
		
		}
	 
	 
	 
	 
	 
	$goodroutine=0;$improperroutine=0;$badroutine=0;$goodlist=array();$badlist=array();$improperlist=array();$j=0;$k=0;$l=0;$userviolationsjson=array();
	for($i=0;$i<count($result);$i++){
	
		if($result[$i]['totalViolations']<30){
			$goodroutine++;
			$key = array_search($result[$i]['userID'], array_column($userlist, 'userID'));
			$goodlist{$j}['Name']=$userlist[$key]["firstName"]. " ".$userlist[$key]["lastName"];
			$goodlist{$j}['userID']=$result[$i]['userID'];
			$j++;		
			
		}	
		if($result[$i]['totalViolations']<154 && $result[$i]['totalViolations']>31 ){
			$improperroutine++;
			$key = array_search($result[$i]['userID'], array_column($userlist, 'userID'));
			$improperlist{$k}['Name']=$userlist[$key]["firstName"]. " ".$userlist[$key]["lastName"];
			$improperlist{$k}['userID']=$result[$i]['userID'];
			$k++;
			
			}
		if($result[$i]['totalViolations']>155){
			$badroutine++;
			//echo $result[$i]['userID'].PHP_EOL;
			$key = array_search($result[$i]['userID'], array_column($userlist, 'userID'));
			
			$badlist{$l}['Name']=$userlist[$key]["firstName"]. " ".$userlist[$key]["lastName"];
			 //echo "LLM userid". $result[$i]['userID']." Index of userlist ".$key."  ".$badlist{$l}['Name'].PHP_EOL;
			
			$badlist{$l}['userID']=$result[$i]['userID'];
			$l++;
	
		}		
		
		
		$userviolationsurl="$llmserver/testWeb/webresources/testweb/UserViolations/".$result[$i]['userID'];
		
		$userviolationslist=getJsonRest($userviolationsurl);	
		
		$userviolationsjson[$i]['userID']=$result[$i]['userID'];
		$userviolationsjson[$i]['list']=$userviolationslist;
	}

	  ?>
      <div class="row">
        <div class="col-md-12">
        
          <?php if(count($badlist)>0){?>
          <div class="heading_a alert alert-danger"> Unhealthy Habituation </div>
          <ul class="user_list bad">
          
          <?php
         	for($i=0;$i<count($badlist);$i++){
		  
		  		echo '<li > <a href="#" data-toggle="modal" data-target="#modalLarge'.$badlist[$i]['userID'].'"> <img class="img-circle" src="members/'.$badlist[$i]['userID'].'.jpg" alt="" width="75" height="75"/>
              <h2>'.$badlist[$i]['Name'].'</h2>
              </a> </li>';
		  }
		  ?>
          
          </ul>
          <?php }?>
            <?php if(count($improperlist)>0){?>
          <div class="heading_a alert alert-warning">Improper Routine </div>
          <ul class="user_list normal">
          <?php
         	for($i=0;$i<count($improperlist);$i++){
		  
		  		echo '<li > <a href="#" data-toggle="modal" data-target="#modalLarge'.$improperlist[$i]['userID'].'"> <img class="img-circle" src="members/'.$improperlist[$i]['userID'].'.jpg" alt="" width="75" height="75"/>
              <h2>'.$improperlist[$i]['Name'].'</h2>
              </a> </li>';
		  }
		  ?>
          </ul>
          <?php }?>
         
         
		  
		  
		 <?php if(count($goodlist)>0){?>
          <div class="heading_a alert alert-info"> Good Routine </div>
          <ul class="user_list good">
               <?php
         	for($i=0;$i<count($goodlist);$i++){
		  
		  		echo '<li > <a href="#" data-toggle="modal" data-target="#modalLarge'.$goodlist[$i]['userID'].'"> <img class="img-circle" src="members/'.$goodlist[$i]['userID'].'.jpg" alt="" width="75" height="75"/>
              <h2>'.$goodlist[$i]['Name'].'</h2>
              </a> </li>';
		  }
		  ?>
          </ul>
          
          <?php }?>
         
         
        </div>
      </div>
    </div>
  </div>
  <!-- main menu -->
  <?php include "leftmenudashboard.php";?>
</div>
<?php
      	for($i=0;$i<count($userviolationsjson);$i++){?>
		
		<div class="modal fade" id="modalLarge<?php echo $userviolationsjson[$i]['userID'];?>"> 
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">User Violations</h4>
      </div>
      <div class="modal-body">
        <p>The User violations </p>
        <ul>
        <?php for($j=0;$j<count($userviolationsjson[$i]['list']);$j++){
		
		
				if($userviolationsjson[$i]['list'][$j]["activityID"]==3) 

						echo ' <li>Sitting '.($userviolationsjson[$i]['list'][$j]['activityTargetDuration']).' minutes on date '.$userviolationsjson[$i]['list'][$j]['startTime'].'</a></li>';
						
				if($userviolationsjson[$i]['list'][$j]["activityID"]==8) 

						echo ' <li>Standing '.($userviolationsjson[$i]['list'][$j]['activityTargetDuration']).' minutes on date '.$userviolationsjson[$i]['list'][$j]['startTime'].'</a></li>';		
						
						
				if($userviolationsjson[$i]['list'][$j]["activityID"]==16) 
						echo ' <li>Normal Fat Eating limit crossed. Eating '.$userviolationsjson[$i]['list'][$j]['activityTargetDuration'].' grams on date '.$userviolationsjson[$i]['list'][$j]['startTime'].'</a></li>';
			
				if($j>10)
					break;
		
		}
		?>
        
        
        
          
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary btn-sm">Save changes</button>-->
      </div>
    </div>
  </div>
</div>

<?php }?>
<!-- jQuery -->
<script src="assets/js/jquery.min.js"></script>
<!-- jQuery Cookie -->
<script src="assets/js/jqueryCookie.min.js"></script>
<!-- Bootstrap Framework -->
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<!-- retina images -->
<script src="assets/js/retina.min.js"></script>
<!-- switchery -->
<!-- typeahead -->
<script src="assets/lib/typeahead/typeahead.bundle.min.js"></script>
<!-- fastclick -->
<script src="assets/js/fastclick.min.js"></script>
<!-- match height -->
<script src="assets/lib/jquery-match-height/jquery.matchHeight-min.js"></script>
<!-- scrollbar -->
<script src="assets/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- Yukon Admin functions -->
<script src="assets/js/yukon_all.min.js"></script>
<!-- jBox -->
<script src="assets/lib/jBox-0.3.0/Source/jBox.min.js"></script>
<script>
                $(function() {
                    // jBox
                    yukon_jBox.p_components_notifications_popups();
                })
            </script>
<?php  include "footer.php";?>
</body>
</html>
