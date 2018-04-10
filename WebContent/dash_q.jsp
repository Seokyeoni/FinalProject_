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
				<li class="red" ><a href = 'dash_c1.html'>카테고리1</a></li>
				<li class="yellow"><a href = 'dash_c1.html'>카테고리2</a></li>
				<li class="green"><a href = 'dash_c1.html'>카테고리3</a></li>
				<li class="new"><a href="http://localhost/Triple_Core/sector_change.html"><i class="fa fa-plus-circle"></i> Add Category</a></li>
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
					<li class="active">Quarterly</li>
					<li><a href="dash_m.jsp">Monthly</a></li>
				</ul>
			</div>
		</header>
		<section>
			<div class="chart">
				<canvas id="c1" width="900" height="200"></canvas>
			</div>
		</section>
		<section>
			<header>Total Sales</header>
			<div class="inlineChart">
				<canvas id="c2" width="100" height="100"></canvas>
				<div class="info">
					<div class="value">$5,000</div>
					<div class="title">Credit sales</div>
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



	<script src="js/dash_q.js"></script>




</body>

</html>