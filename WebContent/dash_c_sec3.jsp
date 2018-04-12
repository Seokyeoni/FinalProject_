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
<link rel="stylesheet" href="css/dash.css">


</head>

<body>

<!-- data loading test -->
	<nav>
		<header>Stock Report</header>
		<section>
			<header>Main</header>
			<ul>
				<li class="active"><a href="http://localhost/Triple_Core/dashCont?command=dash_m">Dashboard</a></li>
				<li>Summary</li>
			</ul>
		</section>
		
		<section>
			<header>Categories</header>
			<ul>
				<li class="red" ><a href = "http://localhost/Triple_Core/dashCont?command=dash_c_sec1">[Google Map] Sector1</a></li>
				<li class="yellow"><a href = "http://localhost/Triple_Core/dashCont?command=dash_c_sec2"'>[Google Map] Sector2</a></li>
				<li class="green"><a href = "http://localhost/Triple_Core/dashCont?command=dash_c_sec3">[Google Map] Sector3</a></li>
				<li class="new"><a href="http://localhost/Triple_Core/sector_change.jsp"><i class="fa fa-plus-circle"></i> Change Sectors</a></li>
			</ul>
		</section>
		<section>
			<header>Account</header>
			<ul>
				<li><a href="main.html">logout</a></li>
<!-- 			<li>Messages</li>
				<li>Downloads</li>
 -->
			</ul>
		</section>
	</nav>
	<article>
		<header>
			<div class="title">Google Map</div>
			<div class="user"></div>
			<div class="interval">
				<ul>
					<a href="http://localhost/Triple_Core/dashCont?command=dash_q"><li>Quarterly</li></a>
					<li class="active">Monthly</li>
				</ul>
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
          ['China', ${requestScope.CNY_rtn}],
          ['Hong Kong', ${requestScope.HKD_rtn}],
          ['Japan', ${requestScope.JPY_rtn}],
          ['Korea, Republic of', ${requestScope.KRW_rtn}],
          ['Mexico', ${requestScope.MXN_rtn}],
          ['Taiwan', ${requestScope.TWD_rtn}],
          ['United States', ${requestScope.USD_rtn}]
          
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







</body>

</html>
