<?php



/*********************************************************************************************

function postJsonRest($url,$json)
function getJsonRest($url)
**********************************************************************************************/




/*********************************************************************************************

function to post json to a rest service


**********************************************************************************************/


function postJsonRest($url,$json){

		$client=curl_init($url);
		
		curl_setopt($client, CURLOPT_POSTFIELDS, $json);
		curl_setopt($client, CURLOPT_RETURNTRANSFER, true);
		
		
		
		curl_setopt($client, CURLOPT_HTTPHEADER, array(
		'Content-Type: application/json',
		'Content-Length: ' . strlen($json))
	);
	
	
		curl_setopt($client, CURLOPT_TIMEOUT, 5);
		curl_setopt($client, CURLOPT_CONNECTTIMEOUT, 5);
	
		
		$response=curl_exec($client);
		//$result=json_decode($response,true);
//		print $response;
		curl_close($client);
		return $response;
	
}


/*********************************************************************************************


function to get json from a rest service


**********************************************************************************************/

function getJsonRest($url){

		$client=curl_init($url);
		
	
		//for get
		curl_setopt($client,CURLOPT_RETURNTRANSFER,1);
		
			
		//response from url
		$response=curl_exec($client);
		$result=json_decode($response,true);
		curl_close($client);
		return $result;

}





/*****************************************************************************/
/***************************CURL function without json decode*************************************/
/*****************************************************************************/
/*****************************************************************************/


function curl_download($Url){
 
    // is cURL installed yet?
    if (!function_exists('curl_init')){
        die('Sorry cURL is not installed!');
    }
 
    // OK cool - then let's create a new cURL resource handle
    $ch = curl_init();
 
    // Now set some options (most are optional)
 
    // Set URL to download
    curl_setopt($ch, CURLOPT_URL, $Url);
 
 
    // User agent
    curl_setopt($ch, CURLOPT_USERAGENT, "MozillaXYZ/1.0");
 
    // Include header in result? (0 = yes, 1 = no)
    curl_setopt($ch, CURLOPT_HEADER, 0);
 
    // Should cURL return or print out the data? (true = return, false = print)
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
    // Timeout in seconds
    curl_setopt($ch, CURLOPT_TIMEOUT, 10);
 
    // Download the given URL, and return output
    $output = curl_exec($ch);
 
    // Close the cURL resource, and free system resources
    curl_close($ch);
 
    return $output;
}

?>