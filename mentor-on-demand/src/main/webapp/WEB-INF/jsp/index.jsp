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
						<option value="1">C</option>
						<option value="2">JAVA</option>
						<option value="3">SPRING</option>
					</select>
					<input type="submit" name="submit" />
				</form>
			</div>
			<c:if test="${roleId != 2}">
			<jsp:include page="menu.jsp"/>
			</c:if>
			<c:if test="${roleId == 2}">
			<jsp:include page="left-sidebar-menu7.jsp"/>
			</c:if>
		</div>
		<div class="sidebar" align="center">
			<c:if test="${roleId == 2}">
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
			</c:if>
		</div>
		<div class="main1">
			<h2>Search Mentor</h2>
			<div class="list">
					<table align="center" border="2" width="100%" cellpadding="2">
						<tr>
							<th>SNO</th>
							<th>MENTOR-NAME</th>
							<th>TECH-NAME</th>
							<th>LINKED-IN</th>
							<th>YEAR-EXP</th>
							<th>RATING</th>
							<th>TOC</th>
							<th>PREREQUISTES</th>
							<th>FEE</th>
							<th>TIME-FROM</th>
							<th>TIME-TO</th>
							<c:if test="${roleId == 2}">
							<th>PROPOSE</th>
							</c:if>
						</tr>
					</table>
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="2" width="100%" cellpadding="2">
						<c:set var="i" value="0"/>
						<c:forEach var="list" items="${search}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${list.mentorFirstName} ${list.mentorLastName}</td>
							<td>${list.techName}</td>
							<td>${list.linkedInUrl}</td>
							<td>${list.yearExp}</td>
							<td>${list.avgRating}</td>
							<td>${list.toc}</td>
							<td>${list.prerequisites}</td>
							<td>${list.fee}</td>
							<td>${list.timeFrom}</td>
							<td>${list.timeTo}</td>
							<c:if test="${roleId == 2}">
							<td><a href="/student/send-proposal/${list.mentorId}/${userId}/${list.slotId}/${list.techId}" onclick="return proposed();">Propose</a></td>
							</c:if>
						</tr>
						</c:forEach>
						<tr>
							<th>SNO</th>
							<th>MENTOR-NAME</th>
							<th>TECH-NAME</th>
							<th>LINKED-IN</th>
							<th>YEAR-EXP</th>
							<th>RATING</th>
							<th>TOC</th>
							<th>PREREQUISTES</th>
							<th>FEE</th>
							<th>TIME-FROM</th>
							<th>TIME-TO</th>
							<c:if test="${roleId == 2}">
							<th>PROPOSE</th>
							</c:if>
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