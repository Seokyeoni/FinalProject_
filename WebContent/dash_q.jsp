<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div class="title">Dashboard</div>
			<div class="interval">
				<ul>
					<a href="http://localhost/Triple_Core/dashCont?command=dash_q"><li>Monthly</li></a>
					<li class="active">Quarterly</li>
				</ul>
			</div>
		</header>
		<section>
			<div class="chart">
				<canvas id="c1" width="900" height="200"></canvas>
			</div>
		</section>
		<section>
			<header>Avg Return by Sector</header>
			<div class="inlineChart">
				<canvas id="c2" width="100" height="100"></canvas>
				<div class="info">
					<div class="value"><%=request.getAttribute("sector1_ror")%></div>
					<div class="title"><%=request.getAttribute("sector1_name")%></div>
				</div>
			</div>
			<div class="inlineChart">
				<canvas id="c3" width="100" height="100"></canvas>
				<div class="info">
					<div class="value"><%=request.getAttribute("sector2_ror")%></div>
					<div class="title"><%=request.getAttribute("sector2_name")%></div>
				</div>
			</div>
			<div class="inlineChart">
				<canvas id="c4" width="100" height="100"></canvas>
				<div class="info">
					<div class="value"><%=request.getAttribute("sector3_ror")%></div>
					<div class="title"><%=request.getAttribute("sector3_name")%></div>
				</div>
			</div>
		</section>
		<section>
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Exchange</th>
						<th>Sector</th>
						<th>Industry</th>
						<th>AvgRtn</th>
						<th>AvgVol</th>
					</tr>
				</thead>
			<c:forEach items="${requestScope.top_company}" var="v">
				<tbody>
				

 					<tr>
						<td>${v.name}</td>
						<td>${v.exchange}</td>
						<td>${v.sector}</td>
						<td>${v.industry}</td>
						<td>${v.avgRtn}</td>
						<td><fmt:formatNumber value="${v.avgVol}" pattern="#,###" /></td>
					</tr>

				</tbody>
				
			</c:forEach>
			</table>
		</section>
	</article>
	<script
		src='js/jquery.min.js'></script>
	<script
		src='js/Chart.min.js'></script>


	<script type="text/javascript">
	
	var data1 = {
		labels : [				  <%=request.getAttribute("sector1_ym")%>				  ],
		datasets : [
			{
				fillColor : "rgba(56,175,91,.1)",
				strokeColor : "rgba(56,175,91,1)",
				pointColor : "rgba(56,175,91,1)",
				pointStrokeColor : "rgba(0,0,0,0.6)",
				data : [			    	  <%=request.getAttribute("sector1_rtn")%>			    	  ]
			},
		
			{
				fillColor : "rgba(234,142,57,.1)",
				strokeColor : "rgba(234,142,57,1)",
				pointColor : "rgba(234,142,57,1)",
				pointStrokeColor : "rgba(0,0,0,0.6)",
				data : [			    	  <%=request.getAttribute("sector2_rtn")%>			    	  ]
			},
			
			{
				fillColor : "rgba(236,72,127,.1)",
				strokeColor : "rgba(236,72,127,1)",
				pointColor : "rgba(236,72,127,1)",
				pointStrokeColor : "rgba(0,0,0,0.6)",
				data : [			    	  <%=request.getAttribute("sector3_rtn")%>			    	  ]
			}
			]
		}
		
		
	var options1 = {
		scaleFontColor : "rgba(255,255,255,0.7)",
		scaleLineColor : "rgba(0,0,0,0)",
		scaleGridLineColor : "rgba(255,255,255,0.1)",
		scaleFontFamily: "Open Sans",
		scaleFontSize: 14,
		bezierCurve : true,
		scaleShowLabels: true,
		pointDotRadius: 5,
		animation: true,
		scaleShowGridLines: true,
		datasetFill: true,
		responsive: true
	}
	
	new Chart(c1.getContext("2d")).Line(data1,options1);
	
	var x = document.getElementsByClassName("value")[0].textContent;
	var y = x.replace("%", "")
	var z = Number(y)
	
	
	
	var data2 = [{
		value: 50,
		color: "rgba(236,72,127,1)",
		label: ""
		},
		{
		value: 50,
		color: "#3c4449",
		label: ""
		}];
	             
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c2").get(0).getContext("2d")).Doughnut(data2,options2);
	
	var data2 = [{
		value: 64,
		color: "rgba(234,142,57,1)",
		label: ""
		},
		{
		value: 36,
		color: "#3c4449",
		label: ""
		}];
		             
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c3").get(0).getContext("2d")).Doughnut(data2,options2);
	
	var data2 = [{
		value: 34,
		color: "rgba(56,175,91,1)",
		label: ""
		},
		{
		value: 66,
		color: "#3c4449",
		label: ""
		}];
	           
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c4").get(0).getContext("2d")).Doughnut(data2,options2);
	
	var data2 = [{
		value: 80,
		color: "rgba(236,72,127,1)",
		label: ""
		},
		{
		value: 20,
		color: "#3c4449",
		label: ""
		}];
		             
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c2").get(0).getContext("2d")).Doughnut(data2,options2);
	
	var data2 = [{
		value: 64,
		color: "rgba(234,142,57,1)",
		label: ""
		},
		{
		value: 36,
		color: "#3c4449",
		label: ""
		}];
	             
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c3").get(0).getContext("2d")).Doughnut(data2,options2);
	
	var data2 = [{
		value: 34,
		color: "rgba(56,175,91,1)",
		label: ""
		},
		{
		value: 66,
		color: "#3c4449",
		label: ""
		}];
			             
	
	var options2 = {
		animation: false,
		responsive: true,
		segmentShowStroke: false,
		percentageInnerCutout: 90
		}
	
	new Chart($("#c4").get(0).getContext("2d")).Doughnut(data2,options2);
	

	
	</script>




</body>

</html>
