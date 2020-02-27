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
			<div class="search">
				<form action="search" method="get">
					<select name="techId" required="required">
						<option value="" disabled="disabled" selected="selected">-- Select --</option>
						<c:forEach var="list" items="${techList}">
							<option value="${list.id}">${list.technologyName}</option>
						</c:forEach>
					</select>
					<input type="submit" name="submit" />
				</form>
			</div>
			<c:if test="${roleId != 2}">
			<jsp:include page="menu.jsp"/>
			</c:if>
			<c:if test="${roleId == 2}">
			<jsp:include page="student-menu.jsp"/>
			</c:if>
		</div>
		<c:if test="${roleId == 2}">
		<div class="sidebar" align="center">
			
			<h4>Notification Bar</h4>
			<div class="scrollit">
			<table align="center" cellpadding="2">
				<c:if test="${not empty notifyList}">
				<c:forEach var="notify" items="${notifyList}">
					<c:if test="${notify.request == '2'}">
					<tr>
						<td align="center"><a href="/student/pay/${notify.id}">${notify.userName} accept your ${notify.techName} training proposal. Now pay fee for start training.</a></td>
					</tr>
					</c:if>
					<c:if test="${notify.request == '1'}">
					<tr>
						<td align="center"><a href="#">${notify.userName} reject your ${notify.techName} training proposal. Now try any one more.</a></td>
					</tr>
					</c:if>
				</c:forEach>
				</c:if>
				<c:if test="${empty notifyList}">
				<tr>
					<td align="center" style="color:red;">You have no notification</td>
				</tr>
				</c:if>
			</table>
			</div>
			
		</div>
		</c:if>
		<c:if test="${roleId == 2}">
		<div class="main1">
		</c:if>
		<c:if test="${roleId != 2}">
		<div class="main">
		</c:if>
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Search Mentor</h2>
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
							<c:if test="${roleId == 2}">
							<th>CALENDAR</th>
							</c:if>
						</tr>
						</thead>
						<tbody>
						<c:if test="${not empty search}">
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
							<td><a href="/student/view-calendar/${list.mentorId}/${userId}/${list.techId}">View</a></td>
							</c:if>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty search}">
							<tr>
								<td colspan="10" style="text-align:center;">Search result is empty.</td>
							</tr>
						</c:if>
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