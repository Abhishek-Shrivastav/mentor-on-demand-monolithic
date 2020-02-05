<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Request Training</title>
	<link href="../css/style.css" rel="stylesheet">
	
	<script type="text/javascript">
		function accept()
		{
			var result = confirm("Want to accept request?");
			
			if(result)
				return true;
			else
				return false;
		}
		
		function decline()
		{
			var result = confirm("Want to decline request?");
			
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
			<h2>Request Training</h2>
			<div class="list">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr>
							<th>ID</th>
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</table>
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<c:if test="${not empty trainingList}">
						<c:set var="i" value="0"/>
						<c:forEach var="training" items="${trainingList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${training.userName}</td>
							<td>${training.slotTimeFrom}</td>
							<td>${training.slotTimeTo}</td>
							<td>${training.techName}</td>
							<td>${training.startDate}</td>
							<td>${training.endDate}</td>
							<td>${training.request}</td>
							<td><a href="/mentor/accept/${training.id}" onclick="return accept()">Accept</a> | <a href="/mentor/request-decline/${training.id}" onclick="return decline()">Decline</a></td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty trainingList}">
							<tr>
								<td colspan="11">Empty list</td>
							</tr>
						</c:if>
						<tr>
							<th>ID</th>
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>STATUS</th>
							<th>ACTION</th>
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