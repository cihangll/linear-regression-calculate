<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	
<script src="<c:url value="/resources/jquery-3.2.0.min.js"/>" />

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">


$(document).ready(function() {
	
	$.ajax({
		  url: 'https://www.google.com/jsapi?callback',
		  cache: true,
		  dataType: 'script',
		  success: function(){
		    google.load('visualization', '1', {packages:['corechart'], 'callback' : function()
		      {
		    	$.ajax({
					url : "${home}/ChartGenerator/getEquationValues",
					dataType : "JSON",
					method : "POST",
					success : function(gelendeger) {
						
						var gelenJson = JSON.stringify(gelendeger);
						var parcaliJson = JSON.parse(gelenJson);
						console.log(parcaliJson);
						
						var data = new google.visualization.DataTable();
						data.addColumn('number');
						data.addColumn('number');		
						
						for ( var i in parcaliJson) {
							var valueX = parcaliJson[i].x;
							var valueY = parcaliJson[i].y;
							data.addRow([ valueX, valueY ]);
						}
						
						var options = {
							hAxis: {minValue: 0, maxValue: 100},
							vAxis: {minValue: 0, maxValue: 100},
							title: 'Linear Equation',
						    legend: { position: 'none' }
						};

						var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
					    chart.draw(data, options); 
					}
				});

		      }
		    });
		    return true;
		  }
		});
	
});
</script>

<body>
	<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>


</html>