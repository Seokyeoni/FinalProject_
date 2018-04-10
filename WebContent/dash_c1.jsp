<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Triple core</title>
<style type="text/css">
 a:link { color: #738491 ; text-decoration: none;}
 a:visited { color: #738491; text-decoration: none;}
 a:hover { color: #2278AE; text-decoration: none;}
</style>


<link rel="stylesheet" href="css/dash_c.css"><!-- ???? -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {
        'packages':['geochart'],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        'mapsApiKey': 'AIzaSyB7-IUfkBdxlz1Rba8yQrcdvj-GmvuMtNk'
      });
      google.charts.setOnLoadCallback(drawRegionsMap);

      
      function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
          ['Country', 'Rtn'],
          ['Korea, Republic of', -2.3],
          ['United States', 5.2],
          ['Brazil', 3.3],
          ['Canada', 0.2],
          ['France', -3.4],
          ['RU', 8.5]
        ]);
        var options = {
        		
                colorAxis: {colors: ['#ffffff', '#2278AE']},
                backgroundColor: '#202B33',
                datalessRegionColor: ''
              };
       

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);
      }
    </script>
</head>

<body>


	<nav>
		<header>Stock Report</header>
		<section>
			<header>Main</header>
			<ul>
				<li>Dashboard</li>
				<li>Summary</li>
			</ul>
		</section>
		
		<section>
			<header>Categories</header>
			<ul>
				<li class="red" >카테고리1</li>
				<li class="yellow">카테고리2</li>
				<li class="green">카테고리3</li>
				<li class="new"><i class="fa fa-plus-circle"> </i> Add Category</li>
			</ul>
		</section>
		<section>
			<header>Account</header>
			<ul>
				<li><a href="main.html">logout</a></li>
				<li>Messages</li>
				<li>Downloads</li>
			</ul>
		</section>
	</nav>
	<article>
		<header>
			<div class="title" >카테고리1</div>
			<div class="user"></div>
			<div class="interval">
			</div>
		</header>
		<section>
			
			<div id="regions_div" style="width: 1200px; height: 666px; color: aqua;"></div>
			
		</section>
		
		
	</article>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/Chart.js/0.2.0/Chart.min.js'></script>



	<script src="js/dash_c.js"></script>




</body>

</html>
