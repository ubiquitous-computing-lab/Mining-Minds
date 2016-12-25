<?php
	include "functions.php"; 


$locationdata=calculateweeklylocation();			




echo "<pre> hello";

	print_r($locationdata);exit;














ini_set('default_charset', 'UTF-8');
header('Content-Type: text/html;charset=utf-8');

						$starttimeobject = new DateTime('-6 days');
						//$starttimeobject = new DateTime('2015-05-16');
						$starttimeobject->setTime(00,00,00);
						
						//set end time today
						$endtimeobject = new DateTime('NOW');
						$endtimeobject->setTime(23,59,59);
						
						//convert date objects into strings
						$start = $starttimeobject->format('Y m d H:i:s');
						$end = $endtimeobject->format('Y-m-d H:i:s');



$emotiondata=array();$week=6;
for($i=0;$i<7;$i++){

	$starttimeobject = new DateTime('-'.$week.' days');
	//print $starttimeobject->format('Y m d');
	$emotiondata[$i]["date"]=$starttimeobject->format('Y-m-d');;
	$emotiondata[$i]["Sadness"]=0;
	$emotiondata[$i]["Happiness"]=0;
	$emotiondata[$i]["Anger"]=0;
	$week--;
}



echo "<pre> hello";

	print_r($emotiondata);



global $dclserver;
$url="$dclserver/MMDataCurationRestfulService/webresources/InformationCuration/GetUserRecognizedEmotionByUserIDDateRange";
$data = array(
		
					"userId"=>35,
					"startTime"=>"2015 12 03 02:29:01",
					"endTime"=>"2015 12 09 02:45:49"
		
		);
		


		
		
$json = json_encode($data,true);
			
$result =postJsonRest($url,$json);		
$result=json_decode($result,true);		
			

for($i=0;$i<count($result);$i++){

	//$datearray=explode("-",$result[$i]['startTime']);

	//$date=$datearray[0]." ".$datearray[1]." ".substr($datearray[2],0,2);
	//echo $date, PHP_EOL;
	$key = array_search(substr($result[$i]['startTime'],0,10), array_column($emotiondata, 'date'));
	
	if($result[$i]['emotionLabel']=="Sadness"){
	
		
		$emotiondata[$key]["Sadness"]=$emotiondata[$key]["Sadness"]+$result[$i]['duration'];
	
	}	
	
	if($result[$i]['emotionLabel']=="Happiness"){
		$emotiondata[$key]["Sadness"]=$emotiondata[$key]["Sadness"]+$result[$i]['duration'];
	
	}	
	
	if($result[$i]['emotionLabel']=="Anger"){
		$emotiondata[$key]["Anger"]=$emotiondata[$key]["Anger"]+$result[$i]['duration'];
	
	}	
	
}
			
			
//$result=json_decode($result,true);

	print_r($emotiondata);


exit;

?>

<?php




function foodSNS(){

			
			
			$dict='{
				"word":["홍차","케이크","아이스티","딸기","알이"],
				
				"meaning":["black tea","Cake","Iced Tea","Strawberry","Eggs"]
				
			}';
			$dict=json_decode($dict,true);
			$starttimeobject = new DateTime('-2 days');
			$endtimeobject = new DateTime('-1 days');
			$start = $starttimeobject->format('Y-m-d');
			$end = $endtimeobject->format('Y-m-d');
			
			$response='{
				"word":["껍질","홍차","케이크","자연","초파리","전공","알이","아이스티","충격","동기","딸기"],"freq":[634,627,623,603,601,596,593,592,590,588,800]}';
			
			$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=topic-list&media=twitter&search=%22%EA%B3%BC%EC%9D%BC%22&exclude=%22%EB%B3%80%EB%B0%B1%ED%98%84%22%3Cor%3E%22%EC%82%AC%ED%83%95%EC%8B%A0%22%3Cor%3E%22%EC%B9%98%ED%82%A8%22&from=".$start."&to=".$end."&offset=0&size=5";
			$result=getJsonRest($url);
			
			
			//$result=json_decode($response,true);
			
			
			$data='';
			$k=0;
			for($i=0;$i<count($result['word']);$i++){
			
						echo $result['word'][$i];
						$url="http://social.trendup.co.kr/api/%20expert.htm?ak=00438530-538b-496a-8ed5-dbff22782885&action=volume-trend&media=twitter&search=".$result['word'][$i]."&exclude=%22%EB%B3%80%EB%B0%B1%ED%98%84%22%3Cor%3E%22%EC%82%AC%ED%83%95%EC%8B%A0%22%3Cor%3E%22%EC%B9%98%ED%82%A8%22&from=".$start."&to=".$end;
						echo $result['word'][$i];
						$volume=getJsonRest($url);
						$data['word'][$k]=$result['word'][$i];
						$data['pos'][$k]=0;
						$data['neg'][$k]=0;
						$data['neutral'][$k]=0;
						for($j=0;$j<count($volume['freq']);$j++){
			
								
																
				
								$data['pos'][$k]=$volume['pos'][$j]+$data['pos'][$k];
								$data['neg'][$k]=$volume['neg'][$j]+$data['neg'][$k];
								$data['neutral'][$k]=$volume['freq'][$j]+$data['neutral'][$k];
				
								
						}							
						
						$data['neutral'][$k]=$data['neutral'][$k]-$data['neg'][$k]-$data['pos'][$k];
						$k++;
								
			
			
			}
			
			return $data;

}





?>


<script>

/*$(document).ready(function(){
    $("#datepicker").datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
          $("#datepicker2").datepicker("option","minDate", selected)
        }
    });
    $("#datepicker2").datepicker({ 
        numberOfMonths: 2,
        onSelect: function(selected) {
           $("#datepicker").datepicker("option","maxDate", selected)
        }
    });  
	
	
	
	
	
	
	
});
*/

</script>


<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
<style type="text/css">
body
{
    font-size:8pt;
    font-family:Verdana;
    padding: 5px;
}
</style>
<script>
  $(function() {
    $( "#startdate" ).datepicker({
	 numberOfMonths: 2,
        onSelect: function(selected) {
          $("#enddate").datepicker("option","minDate", selected)
        }
	
	});
	 $( "#enddate" ).datepicker({
	 numberOfMonths: 2,
        onSelect: function(selected) {
         $("#startdate").datepicker("option","maxDate", selected)
        }
	
	});
  });
  </script>
  <a href="#" data-toggle="modal" data-target="#modalLarge">
<img class="img-circle" src="members/35.jpg" alt="" width="75" height="75"/>
<h2>Do Hyung Kim</h2>
</a>
  <div class="modal fade" id="modalLarge">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">User Violations</h4>
      </div>
      <div class="modal-body">
        <p>The User violations </p>
        <ul>
          <li>Sitting one Hour on date 26/11/2015</a></li>
          <li>Sitting four Hour on date 28/11/2015</a></li>
          <li>Standing two Hour on date 28/11/2015 </a></li>
          <li>Sitting one Hour on date 31/11/2015</a></li>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary btn-sm">Save changes</button>-->
      </div>
    </div>
  </div>
</div>
</body>
</html>