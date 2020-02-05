<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor On Demand - Home</title>
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<jsp:include page="logmenu.jsp"/>
		</div>
		<div class="main">
			<h2>Student Panel</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<tr>
							<th>Hello Student</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<h5>Mentor On demand</h5>
		</div>
	</div>
</body>
</html>