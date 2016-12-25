<!--
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

-->	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html >
<head>  
    <title>Add Row dynamically</title>  
    
    <script src="https://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/ui/1.9.2/jquery-ui.min.js" type="text/javascript"></script>
    
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
    
  	<style>
  	table{width:100%}

.deleterow{cursor:pointer}
  	</style>
	
	
	
 
    <script type="text/javascript">  
    $(document).ready(function (){
    	
    function test(){
	    var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];
    $( ".abc" ).addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" ).autocomplete({
            delay: 0,
            minLength: 0,
      source: availableTags
    });

} 

test();

function testRemove(){
    $(".deleterow").on("click", function(){
    var $killrow = $(this).parent('tr');
        $killrow.addClass("danger");
    $killrow.fadeOut(0, function(){
        $(this).remove();
    });});
}
testRemove();

var counter=0;
$(".addnewrow").on("click", function(){
$('table tr:last').after("<tr><td><input class='abc' type='text' id='one"+ counter +"'/></td>    <td><input type='text' class='abc' id=two"+ counter +"'/></td>    <td><input type='text' class='abc' id=three"+ counter +"'/></td><td class='deleterow'><div class='glyphicon glyphicon-remove'></div></td></tr>");
    
     test();
    testRemove();
    counter++;
});


    });
                      
    </script>  
    <body >  
  
     <div class="container">
    <h2>Table Management</h2>
    <div class="table-responsive">
    <table border="1" class="table table-striped table-hover  table-bordered">
<tr>
    <td><input type="text" class="abc" id="one" /></td>    <td><input type="text" class="abc" id="two" /></td>    <td><input type="text" class="abc" id="three" /></td><td class='deleterow'><div class='glyphicon glyphicon-remove'></div></td>
    </tr>
    </table>
</div></div>

<hr>
    <button class='btn btn-lg btn-primary addnewrow pull-right'>Add New <span class="glyphicon glyphicon-plus"></span></button>
   	</body>
</html>
