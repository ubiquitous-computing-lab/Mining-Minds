<script>
	$("#pushnotify").click(function () {
	
   	$.ajax({
        type: "GET",
        url: 'updateview.php',
        data: "options=dummy",
        success: function(response) {
		
		 $("#newnotify").html("");

        }
    });
       

});
</script>