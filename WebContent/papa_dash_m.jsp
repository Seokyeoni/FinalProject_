<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


	<nav>
		<header>Stock Report</header>
		<section>
			<header>Main</header>
			<ul>
				<li class="active"><a href="dash_q.html">Dashboard</a></li>
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
			<div class="title">Dashboard</div>
			<div class="user"></div>
			<div class="interval">
				<ul>
					<a href="dash_q.html"><li>Quarterly</li></a>
					<li class="active">Monthly</li>
				</ul>
			</div>
		</header>
		<section>
			<div class="chart">
				<canvas id="c1" width="900" height="200"></canvas>
			</div>
		</section>
		<section>
			<header>Rate of Return</header>
			<div class="inlineChart">
				<canvas id="c2" width="100" height="100"></canvas>
				<div class="info">
					<div class="value"><%=request.getAttribute("sec1_ror") %></div>
					<div class="title"><%=request.getAttribute("sec1_name") %></div>
				</div>
			</div>
			<div class="inlineChart">
				<canvas id="c3" width="100" height="100"></canvas>
				<div class="info">
					<div class="value">$24,734</div>
					<div class="title">Channel Sales</div>
				</div>
			</div>
			<div class="inlineChart">
				<canvas id="c4" width="100" height="100"></canvas>
				<div class="info">
					<div class="value">$15,650</div>
					<div class="title">Direct Sales</div>
				</div>
			</div>
		</section>
		<section>
			<table>
				<thead>
					<tr>
						<th>November Sales</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Dallas Oak Dining Chair</td>
						<td>20</td>
						<td>$1,342</td>
					</tr>
					<tr>
						<td>Benmore Glass Coffee Table</td>
						<td>18</td>
						<td>$1,550</td>
					</tr>
					<tr>
						<td>Aoraki Leather Sofa</td>
						<td>15</td>
						<td>$4,170</td>
					</tr>
					<tr>
						<td>Bali Outdoor Wicker Chair</td>
						<td>25</td>
						<td>$2,974</td>
					</tr>
				</tbody>
			</table>
		</section>
	</article>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/Chart.js/0.2.0/Chart.min.js'></script>
	
	<script type="text/javascript">
	
	var data1 = {
			  labels : [
				  <%=request.getAttribute("ym1")%>
				  ],

			  datasets : [
			    {
			      fillColor : "rgba(56,175,91,.1)",
			      strokeColor : "rgba(56,175,91,1)",
			      pointColor : "rgba(56,175,91,1)",
			      pointStrokeColor : "rgba(0,0,0,0.6)",
			      data : [
			    	  <%=request.getAttribute("rtn1")%>
			    	  ]
			    },
			    
			    {
			      fillColor : "rgba(234,142,57,.1)",
			      strokeColor : "rgba(234,142,57,1)",
			      pointColor : "rgba(234,142,57,1)",
			      pointStrokeColor : "rgba(0,0,0,0.6)",
			      data : [
			    	  <%=request.getAttribute("rtn1")%>
			    	  ]
			    },
			    {
			      fillColor : "rgba(236,72,127,.1)",
			      strokeColor : "rgba(236,72,127,1)",
			      pointColor : "rgba(236,72,127,1)",
			      pointStrokeColor : "rgba(0,0,0,0.6)",
			      data : [
			    	  <%=request.getAttribute("rtn1")%>
			    	  ]
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
			  pointDotRadius: 6,
			  animation: true,
			  scaleShowGridLines: true,
			  datasetFill: true,
			  responsive: true
			}

			new Chart(c1.getContext("2d")).Line(data1,options1);


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
