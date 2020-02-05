<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - User</title>
	<link href="../css/style.css" rel="stylesheet">
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
			<h2>User Table</h2>
			<div class="list">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr>
							<th>ID</th>
							<th>FIRST-NAME</th>
							<th>LAST-NAME</th>
							<th>CONTACT</th>
							<th>ROLE</th>
							<th>LINKED-IN</th>
							<th>EXPIRENCE</th>
							<th>ACTIVE</th>
						</tr>
					</table>
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<c:if test="${not empty userList}">
						<c:set var="i" value="0"/>
						<c:forEach var="user" items="${userList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.contact}</td>
							<c:if test="${user.roleId == 1}">
							<td>Admin</td>
							</c:if>
							<c:if test="${user.roleId == 2}">
							<td>Student</td>
							</c:if>
							<c:if test="${user.roleId == 3}">
							<td>Mentor</td>
							</c:if>
							<td>${user.linkedInUrl}</td>
							<td>${user.yearOfExp}</td>
							<c:if test="${user.isActive == 1}">
							<td><a href="/admin/activate/${user.id}/0">Active</a></td>
							</c:if>
							<c:if test="${user.isActive == 0}">
							<td><a href="/admin/activate/${user.id}/1">InActive</a></td>
							</c:if>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty userList}">
							<tr>
								<td colspan="8">Empty list</td>
							</tr>
						</c:if>
						<tr>
							<th>ID</th>
							<th>FIRST-NAME</th>
							<th>LAST-NAME</th>
							<th>CONTACT</th>
							<th>ROLE</th>
							<th>LINKED-IN</th>
							<th>EXPIRENCE</th>
							<th>ACTIVE</th>
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