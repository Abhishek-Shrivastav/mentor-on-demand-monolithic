<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Edit Technology</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function update()
		{
			alert("Technology Update!");
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
			<jsp:include page="left-sidebar-menu4.jsp"/>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Edit Technology</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/admin/update-tech" onsubmit="update()" autocomplete="off">
						<tr>
							<td colspan="2"><form:hidden  path="id" readonly="true" required="true"/></td>
						</tr>
						<tr>
							<td>Technology : </td>
							<td><form:input path="technologyName" placeholder="Technology Name" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="isActive" readonly="true" value="1" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;"><input type="submit" name="add" value="Insert"/></td>
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