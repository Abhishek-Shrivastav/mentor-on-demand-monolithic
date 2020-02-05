<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Edit Profile</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function update()
		{
			alert("Skill Updated!");
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
			<jsp:include page="left-sidebar-menu1.jsp"/>
		</div>
		<div class="main1">
			<h2>Edit Mentor-Skill Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/mentor/update-skill" onsubmit="update()">
						<tr>
							<td colspan="2"><form:hidden  path="id" readonly="true" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="mentorId" readonly="true" value="${mentorId}" required="true"/></td>
						</tr>
						<tr>
							<td>Technology : </td>
							<td>
								<form:select path="technologyId" required="true">
									<form:option value="${techId}" selected="true">${techName}</form:option>
									<form:option value="" disabled="true">-- Select --</form:option>
								<c:forEach var="techList" items="${techList}">
									<form:option value="${techList.id}">${techList.technologyName}</form:option>
								</c:forEach>
								</form:select>
							</td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="avgRating" readonly="true" value="5" required="true"/></td>
						</tr>
						<tr>
							<td>TOC : </td>
							<td><form:input path="toc" placeholder="TOC" required="true"/></td>
						</tr>
						<tr>
							<td>Prerequisite : </td>
							<td><form:input path="prerequisites" placeholder="Prerequisite" required="true"/></td>
						</tr>
						<tr>
							<td>Fee : </td>
							<td><form:input path="fee" placeholder="Fee" required="true"/></td>
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