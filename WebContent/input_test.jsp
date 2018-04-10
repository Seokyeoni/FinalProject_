<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">

		var json = ${sessionScope.json}
		var obj = JSON.parse(json)
		console.log(obj)
		
		
			var data1 = {
			  labels : [
				  "'2017-08",
				  "'2017-09",
				  "'2017-10",
				  "2017-11",
				  "2017-12",
				  "2018-01",
				  "2018-02",
				  "2018-03"
				  ]
		}
		
		
		
		
	</script>

</body>
</html>