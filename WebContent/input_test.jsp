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
		var data = {2003:{"Cyclical":${requestScope.sample[0][1]},"Non-cyclical":${requestScope.sample[5][1]}},
						2004:{"Cyclical":${requestScope.sample[10][1]},"Non-cyclical":${requestScope.sample[15][1]}},
						2005:{"Cyclical":${requestScope.sample[20][1]},"Non-cyclical":${requestScope.sample[25][1]}},
						2006:{"Cyclical":${requestScope.sample[30][1]},"Non-cyclical":${requestScope.sample[35][1]}},
						2007:{"Cyclical":${requestScope.sample[40][1]},"Non-cyclical":${requestScope.sample[45][1]}},
						2008:{"Cyclical":${requestScope.sample[50][1]},"Non-cyclical":${requestScope.sample[55][1]}},
						2009:{"Cyclical":${requestScope.sample[60][1]},"Non-cyclical":${requestScope.sample[65][1]}},
						2010:{"Cyclical":${requestScope.sample[70][1]},"Non-cyclical":${requestScope.sample[75][1]}},
						2011:{"Cyclical":${requestScope.sample[80][1]},"Non-cyclical":${requestScope.sample[85][1]}},
						2012:{"Cyclical":${requestScope.sample[90][1]},"Non-cyclical":${requestScope.sample[95][1]}}
						}
		console.log(data["2003"])
	</script>

</body>
</html>