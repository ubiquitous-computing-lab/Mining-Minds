<?php


/*********************************************************************************************


Functions 

function getRecommendationFeedbackbyUserID($uid)
function getFactFeedbackbyUserID($uid)

function getActivityFeedbackbyUserID($uid)

**********************************************************************************************/



include_once("server.php");





/*********************************************************************************************

Get Recommendation Feedback by User ID
**********************************************************************************************/
//getRecommendationFeedbackbyUserID(39);
function getRecommendationFeedbackbyUserID($uid){


		$sentiment = array(
					"sittingpositive" => 0,
					"sittingnegative" => 0,
					"standingpositive"=> 0,
					"standingnegative"=> 0,
					"walkingpositive" => 0,
					"walkingnegative" => 0,
					"runningpositive" => 0,
					"runningnegative" => 0,
					"LyingDownpositive" => 0,
					"LyingDownnegative" => 0,
					"joggingpositive" => 0,
					"joggingnegative" => 0
				);		

		global $dclserver;
		$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/RecommendationFeedbackByUserID/$uid";
		$getRecommendationFeedbackbyUserID=getJsonRest($url);

		$positive=0;
		$negative=0;
		for($i=0;$i<count($getRecommendationFeedbackbyUserID);$i++){

			
			
			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
				$sentiment["sittingpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
				$sentiment["sittingnegative"]++;
				
			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
				$sentiment["standingpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
				$sentiment["standingnegative"]++;
				
				
			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
				$sentiment["walkingpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
				$sentiment["walkingnegative"]++;		


			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
				$sentiment["runningpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
				$sentiment["runningnegative"]++;	
			
			
			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
				$sentiment["LyingDownpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
				$sentiment["LyingDownnegative"]++;	
				
				
				
			if($getRecommendationFeedbackbyUserID[$i]['rate']==1 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
				$sentiment["joggingpositive"]++;
			else if($getRecommendationFeedbackbyUserID[$i]['rate']==0 && $getRecommendationFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
				$sentiment["joggingnegative"]++;					
}
	
		
		
	return $sentiment;
	


}




/*********************************************************************************************

Get fact Feedback by User ID
**********************************************************************************************/
function getFactFeedbackbyUserID($uid){


		$sentiment = array(
					"sittingpositive" => 0,
					"sittingnegative" => 0,
					"standingpositive"=> 0,
					"standingnegative"=> 0,
					"walkingpositive" => 0,
					"walkingnegative" => 0,
					"runningpositive" => 0,
					"runningnegative" => 0,
					"LyingDownpositive" => 0,
					"LyingDownnegative" => 0,
					"joggingpositive" => 0,
					"joggingnegative" => 0
				);		

		global $dclserver;
		$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/FactsFeedbackByUserID/$uid";
		$getFactFeedbackbyUserID=getJsonRest($url);
		
			
		//	print_r($getRecommendationFeedbackbyUserID);

		$positive=0;
		$negative=0;
		for($i=0;$i<count($getFactFeedbackbyUserID);$i++){
		
		
		

			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
				$sentiment["sittingpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Sitting")
				$sentiment["sittingnegative"]++;
				
			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
				$sentiment["standingpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Standing")
				$sentiment["standingnegative"]++;
				
				
			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
				$sentiment["walkingpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Walking")
				$sentiment["walkingnegative"]++;		


			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
				$sentiment["runningpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Running")
				$sentiment["runningnegative"]++;	
				
			
			
			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
				$sentiment["LyingDownpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="LyingDown")
				$sentiment["LyingDownnegative"]++;	
				
				
			if($getFactFeedbackbyUserID[$i]['rate']==1 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
				$sentiment["joggingpositive"]++;
			else if($getFactFeedbackbyUserID[$i]['rate']==0 && $getFactFeedbackbyUserID[$i]['situationCategoryDescription']=="Jogging")
				$sentiment["joggingnegative"]++;					
}
	
		
		
	return $sentiment;
	


}




/*********************************************************************************************

Get Activity Feedback by UserID (not used)
**********************************************************************************************/
function getActivityFeedbackbyUserID($uid){

		global $dclserver;
		$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/ActivityFeedbackByUserID/$uid";
		$getActivityFeedbackbyUserID=getJsonRest($url);
		

		$positive=0;
		$negative=0;
		for($i=0;$i<count($getActivityFeedbackbyUserID);$i++){

			if($getActivityFeedbackbyUserID[$i]['rate']==1)
				//$positive=$positive+$getFactsFeedbackbyUserID[$i]['rate'];
				$positive++;
			else if($getActivityFeedbackbyUserID[$i]['rate']==0)
				$negative++;
				
				

}
	
		$sentiment = array(
			"positive" => $positive,
			"negative" => $negative,
		);
		
	//print_r($getRecommendationFeedbackbyUserID);

	return $sentiment;

}




/*********************************************************************************************

Get Recommendation Feedback by RecommendationID

**********************************************************************************************/

//getRecommendationFeedbackbyID(4);
function getRecommendationFeedbackbyID($recID){

	global $dclserver;

	//print "Rec ID is ".$recID;
	$url="$dclserver/MMDataCurationRestfulService/webresources/SupportingLayer/RecommendationFeedback/$recID";
	$recommendationFeedbackJson=getJsonRest($url);
	
	if(count($recommendationFeedbackJson)>0)
	
			if($recommendationFeedbackJson[0]['rate']==1)
				print "I liked the Recommendation. ". $recommendationFeedbackJson[0]['reason'];
			else if($recommendationFeedbackJson[0]['rate']==0)
				print "I did not like the Recommendation.". $recommendationFeedbackJson[0]['reason'];
	


}

?>