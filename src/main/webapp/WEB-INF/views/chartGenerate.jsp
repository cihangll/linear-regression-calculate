<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<form:form action="chart" method="post" commandName="testForm">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Lütfen Nokta Sayısını
							Giriniz.</h2></td>
				</tr>
				<tr>
					<td>Oluşturulacak Nokta Sayısı:</td>
					<td><form:input path="pointValue" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Oluştur" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>