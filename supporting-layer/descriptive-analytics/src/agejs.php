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
