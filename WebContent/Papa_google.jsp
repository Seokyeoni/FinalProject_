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
          ['1',	${requestScope.sample[0][1]},	${requestScope.sample[5][1]}],
          ['2',	${requestScope.sample[10][1]},	${requestScope.sample[15][1]}],
          ['3',	${requestScope.sample[20][1]},	${requestScope.sample[25][1]}],
          ['4',	${requestScope.sample[30][1]},	${requestScope.sample[35][1]}],
          ['5',	${requestScope.sample[40][1]},	${requestScope.sample[45][1]}],
          ['6',	${requestScope.sample[50][1]},	${requestScope.sample[55][1]}],
          ['7',	${requestScope.sample[60][1]},	${requestScope.sample[65][1]}],
          ['8',	${requestScope.sample[70][1]},	${requestScope.sample[75][1]}],
          ['9',	${requestScope.sample[80][1]},	${requestScope.sample[85][1]}],
          ['10',	${requestScope.sample[90][1]},	${requestScope.sample[95][1]}],
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