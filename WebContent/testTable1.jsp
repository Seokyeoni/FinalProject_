<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tableList.jsp</title>
</head>
<body>
	<style>
table, th, td {
	border: 3px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}
</style>
</head>
<body>
	<center>
		<h1>Google Finance</h1>
		<h3>2010 ~ 2018</h3>

		<table style="width: 70%">
				<tr>
					<td><b>거래소</b></td>
					<td><b>심볼</b></td>
					<td><b>회사명</b></td>
					<td><b>날짜</b></td>
					<td><b>화폐단위</b></td>
					<td><b>시작가</b></td>
					<td><b>고가</b></td>
					<td><b>저가</b></td>
					<td><b>종가</b></td>
					<td><b>거래량</b></td>
					
				</tr>

			<c:forEach items="${requestScope.stock}" var="v">
				<tr>
					<td>${v[0]}</td>
					<td>${v[1]}</td>
					<td>${v[2]}</td>
					<td>${v[3]}</td>
					<td>${v[4]}</td>
					<td>${v[5]}</td>
					<td>${v[6]}</td>
					<td>${v[7]}</td>
					<td>${v[8]}</td>
					<td>${v[9]}</td>

				</tr>
			</c:forEach>
		</table>

	</center>


</body>
</html>