<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Technology</title>
	<link href="../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function del()
		{
			var status = confirm("Want to delete?");
			
			if(status)
				return true;
			else
				return false;
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
			<h2>Technology</h2>
			<div class="list">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr>
							<th>ID</th>
							<th>TECHNOLOGY</th>
							<th>ACTIVE</th>
							<th>EDIT</th>
							<th>DELETE</th>
						</tr>
					</table>
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<c:if test="${not empty techList}">
						<c:set var="i" value="0"/>
						<c:forEach var="tech" items="${techList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${tech.technologyName}</td>
							<c:if test="${tech.isActive == 1}">
								<td>Active</td>
							</c:if>
							<c:if test="${tech.isActive == 0}">
								<td>InActive</td>
							</c:if>
							<td><a href="/admin/edit-tech/${tech.id}"">Edit</a></td>
							<td><a href="/admin/delete-tech/${tech.id}" onclick="return del()">Delete</a></td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty techList}">
							<tr>
								<td colspan="5">Empty list</td>
							</tr>
						</c:if>
						<tr>
							<th>ID</th>
							<th>TECHNOLOGY</th>
							<th>ACTIVE</th>
							<th>EDIT</th>
							<th>DELETE</th>
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