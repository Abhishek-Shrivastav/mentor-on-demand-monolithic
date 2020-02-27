<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor On Demand - Home</title>
	<link href="/css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function proposed()
		{
			var result = confirm("Want to proposed a Mentor?");
			
			if(result)
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
			<jsp:include page="student-menu.jsp"/>
		</div>
		<div class="main">
			<h3><span style="float:right; padding-right:80px;">${firstName}</span></h3>
			<h2>Propose Mentor</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="2" width="100%" cellpadding="2">
						<thead>
						<tr class="header-fix">
							<th>SNO</th>
							<th>MENTOR-NAME</th>
							<th>TECH-NAME</th>
							<th>LINKED-IN</th>
							<th>YEAR-EXP</th>
							<th>RATING</th>
							<th>TOC</th>
							<th>PREREQUISTES</th>
							<th>FEE</th>
						</tr>
						</thead>
						<tbody>
						<c:set var="i" value="0"/>
						<c:forEach var="list" items="${search}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${list.mentorFirstName} ${list.mentorLastName}</td>
							<td>${list.techName}</td>
							<td>${list.linkedInUrl}</td>
							<td width="30">${list.yearExp}</td>
							<td width="30">${list.avgRating}</td>
							<td>${list.toc}</td>
							<td width="30">${list.prerequisites}</td>
							<td>${list.fee}</td>
							<c:if test="${roleId == 2}">
							<!--<td><a href="/student/send-proposal/${list.mentorId}/${userId}/${list.techId}" onclick="return proposed();">Propose</a></td>-->
							</c:if>
						</tr>
						</c:forEach>
						</tbody>
					</table>
					<table align="center" border="2" width="100%" cellpadding="2">
						<thead>
						<tr class="header-fix">
							<th>SNO</th>
							<th>TIME-FROM</th>
							<th>TIME-TO</th>
							<th>PROPOSE</th>
						</tr>
						</thead>
						<tbody>
						<c:set var="i" value="0"/>
						<c:forEach var="list" items="${mentorSlotList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${list.timeFrom}</td>
							<td>${list.timeTo}</td>
							<td><a href="/student/send-proposal/${list.mentorId}/${userId}/${list.id}/${techId}" onclick="return proposed();">Propose</a></td>
						</tr>
						</c:forEach>
						</tbody>
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