<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

    <script type="text/javascript" src="script/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Cyclical', 'Non-cyclical'],
          ['4월',	${requestScope.sample[0][1]},	${requestScope.sample[1][1]}],
          ['5월',	${requestScope.sample[2][1]},	${requestScope.sample[3][1]}],
          ['6월',	${requestScope.sample[4][1]},	${requestScope.sample[5][1]}],
          ['7월',	${requestScope.sample[6][1]},	${requestScope.sample[7][1]}],
          ['8월',	${requestScope.sample[8][1]},	${requestScope.sample[9][1]}],
          ['9월',	${requestScope.sample[10][1]},	${requestScope.sample[11][1]}],
          ['10월',	${requestScope.sample[12][1]},	${requestScope.sample[13][1]}],
          ['11월',	${requestScope.sample[14][1]},	${requestScope.sample[15][1]}],
          ['12월',	${requestScope.sample[16][1]},	${requestScope.sample[17][1]}]
          
        ]);

        
        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>


  </head>
  <body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>