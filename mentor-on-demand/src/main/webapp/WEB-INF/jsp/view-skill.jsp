<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Home</title>
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
			<jsp:include page="left-sidebar-menu1.jsp"/>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Mentor Skill List</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr class="header-fix">
							<th>ID</th>
							<th>TECHNOLOGY</th>
							<th>Rating</th>
							<th>TOC</th>
							<th>PREREQUISITE</th>
							<th>FEE</th>
							<th>EDIT</th>
							<th>DELETE</th>
							
						</tr>
						<c:if test="${not empty skillList}">
						<c:set var="i" value="0"/>
						<c:forEach var="skill" items="${skillList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${skill.technologyName}</td>
							<td>${skill.avgRating}</td>
							<td>${skill.toc}</td>
							<td>${skill.prerequisites}</td>
							<td>${skill.fee}</td>
							<td><a href="/mentor/edit-skill/${skill.id}">Edit</a></td>
							<td><a href="/mentor/delete-skill/${skill.id}" onclick="return del()">Delete</a></td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty skillList}">
							<tr>
								<td colspan="6" style="text-align:center;">Empty list</td>
							</tr>
						</c:if>
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