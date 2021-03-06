<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Payment Form</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function pay()
		{
			var result = confirm("Are you sure to pay this amount?\nIf 'yes' click on 'OK' or 'no' click on 'Cancel'!!!");
			
			if(result)
			{
				alert("Payment has been done successfully!\nYour training will be start on that date.");
				
				return true;
			}
			else
			{
				return false;
			}
		}
	</script>
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<c:if test="${roleId == 1}">
				<jsp:include page="admin-menu.jsp"/>
			</c:if>
			<c:if test="${roleId == 2}">
				<jsp:include page="student-menu.jsp"/>
			</c:if>
			<c:if test="${roleId == 3}">
				<jsp:include page="mentor-menu.jsp"/>
			</c:if>
		</div>
		<div class="main">
			<h3><span style="float:right; padding-right:80px;">${firstName}</span></h3>
			<h2>Payment Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/student/paypal" onsubmit="return pay()" modelAttribute="training">
						<tr>
							<td>Training Id : </td>
							<td><form:input  path="id" readonly="true" value="${training.id}" required="true"/></td>
						</tr>
						<tr>
							<td>Teacher Name : </td>
							<td><input type="text" name="mentorName" value="${mantor}" readonly="readonly" required="true"/></td>
						</tr>
						<tr>
							<td>Total Fee/Payment : </td>
							<td><form:input  path="totalFee" readonly="true" value="${trainingFee}" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;"><input type="submit" name="add" value="Pay" /></td>
						</tr>
						</form:form>
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