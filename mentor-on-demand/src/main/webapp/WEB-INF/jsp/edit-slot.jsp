<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Edit Time Slot</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function update()
		{
			alert("Time Slot Updated!");
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
		<div class="sidebar" align="center">
			<jsp:include page="left-sidebar-menu2.jsp"/>
		</div>
		<div class="main1">
			<h2>Edit Mentor-Slot Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/mentor/update-slot" onsubmit="update()">
						<tr>
							<td colspan="2"><form:hidden  path="id" readonly="true" required="true"/></td>
						</tr>
						<tr>
							<td></td>
							<td><form:hidden  path="mentorId" readonly="true" value="" required="true"/></td>
						</tr>
						<tr>
							<td>Time From : </td>
							<td><form:input path="timeFrom" placeholder="Time From" required="true"/></td>
						</tr>
						<tr>
							<td>Time To : </td>
							<td><form:input path="timeTO" placeholder="Time To" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="edit" value="Update" /></td>
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