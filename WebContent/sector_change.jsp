<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Sign-Up/Login Form</title>
<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


<link rel="stylesheet" href="css/log.css">
<style type="text/css">
body
{
background:url('img/demo-1-bg.jpg')no-repeat center center fixed;

        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;

}
</style>

</head>

<body>


	<div class="form">
		
			<div id="signup">
				<h1>Change Sector for Free</h1>

				<form action="dashCont" method="post">
				
					<div class="field-wrap">
               <label>Basic Materials</label><input type="checkbox" name="sector" value="s01"><br>
               <label>Capital Goods</label><input type="checkbox" name="sector" value="s02"><br>
               <label>Conglomerates</label><input type="checkbox" name="sector" value="s03"><br>
               <label>Consumer Cyclical</label><input type="checkbox" name="sector" value="s04"><br>
               <label>Consumer/   Non-Cyclical</label><input type="checkbox" name="sector" value="s05"><br>
               <label>Energy</label><input type="checkbox" name="sector" value="s06"><br>
               <label>Financial</label><input type="checkbox" name="sector" value="s07"><br>
               <label>Healthcare</label><input type="checkbox" name="sector" value="s08"><br>
               <label>Services</label><input type="checkbox" name="sector" value="s09"><br>
               <label>Technology</label><input type="checkbox" name="sector" value="s10"><br>
               <label>Transportation</label><input type="checkbox" name="sector" value="s11"><br>
               <label>Utilities</label><input type="checkbox" name="sector" value="s12"><br>
               </div>
               		<!-- <span class="submit"><input type="submit" value="Get Started" ></span> -->
					<button type="submit" class="button button-block" name="command" value="change_sector">
					Get Changed
					</button>

				</form>

			</div>
		
		<!-- tab-content -->

	</div>

	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>



	<script src="js/log.js"></script>




</body>

</html>
