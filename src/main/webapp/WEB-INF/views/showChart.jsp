<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		console.log("test");
		
		$.ajax({
			  url: 'https://www.google.com/jsapi?callback',
			  cache: true,
			  dataType: 'script',
			  success: function(){
			    google.load('visualization', '1', {packages:['corechart'], 'callback' : function()
			      {
			    	$.ajax({
						url : "${home}/ChartGenerator/getPoints",
						dataType : "JSON",
						method : "POST",
						success : function(gelendeger) {
							
							var gelenJson = JSON.stringify(gelendeger);
							var parcaliJson = JSON.parse(gelenJson);
							//console.log(parcaliJson);
							
							var data = new google.visualization.DataTable();
							data.addColumn('number');
							data.addColumn('number');
							
							var data2 = google.visualization.arrayToDataTable([
						          
						    	]);
							data2.addColumn('number');
							data2.addColumn('number');
							
							for ( var i in parcaliJson) {
								//console.log(parcaliJson[i].x);
								var valueX = parcaliJson[i].x;
								var valueY = parcaliJson[i].y;
								
								data2.addRow([ valueX, valueY ]);
							}
							
							var options2 = {
							          hAxis: {minValue: 0, maxValue: 100},
							          vAxis: {minValue: 0, maxValue: 100},
							          chartArea: {width:'50%'},
							          trendlines: {
							            0: {
							              type: 'linear',
							              showR2: true,
							              visibleInLegend: true
							            }
							          }
							        };
							
							
							for ( var i in parcaliJson) {
								//console.log(parcaliJson[i].x);
								var valueX = parcaliJson[i].x;
								var valueY = parcaliJson[i].y;
								
								//Simple Test
								console.log(valueX + " , " + valueY);
								data.addRow([ valueX, valueY ]);
							}
							
							var options = {
								legend : 'none',
								crosshair : {
									trigger : 'both'
								}
							};
							
							var chart = new google.visualization.ScatterChart(document
									.getElementById('chart_div'));
							chart.draw(data, options);	
							
							 var chartLinear = new google.visualization.ScatterChart(document.getElementById('chartLinear'));
						        chartLinear.draw(data2, options2);

						        options2.trendlines[0].type = 'exponential';
						        options2.colors = ['#6F9654'];

						        var chartExponential = new google.visualization.ScatterChart(document.getElementById('chartExponential'));
						        chartExponential.draw(data2, options2);
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
	
	<div align="center">
		<table border="0">
			<tr>
				<td colspan="2"><h2>Chart Oluşturuldu.</h2></td>
			</tr>
			<tr>
				<td>Nokta Sayısı:</td>
				<td>${testForm.pointValue}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<!-- Chart Ekranda Göster. -->
					<div id="chart_div" style="width: 900px; height: 500px;"></div>
				<td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<div id="chartLinear" style="height: 350px; width: 800px"></div>
   					<div id="chartExponential" style="height: 350px; width: 800px"></div>
				<td>
			</tr>
		</table>
	</div>

	<div align="center">
		<form action="linearEquation">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Hesapla" /></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>