<?php 
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
             