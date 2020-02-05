<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Home</title>
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
		<div class="sidebar" align="center">
			<h4>Notification Bar</h4>
			<div class="scrollit">
			<table align="center" cellpadding="2">
				<c:if test="${not empty notifyList}">
				<c:forEach var="notify" items="${notifyList}">
					<tr>
						<td align="center"><a href="/mentor/request">${notify.userName} proposed for ${notify.techName} training</a></td>
					</tr>
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
		<div class="main1">
			<h2>Mentor Panel</h2>
			<div class="list">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr>
							<th>ID</th>
							<!--<th>MENOTR-NAME</th>-->
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>PROGRESS</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>AMMOUNT-RECIVED</th>
							<th>RATING</th>
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
							<!--<td>${training.mentorName}</td>-->
							<td>${training.userName}</td>
							<td>${training.slotTimeFrom}</td>
							<td>${training.slotTimeTo}</td>
							<td>${training.techName}</td>
							<td>${training.progress}%</td>
							<td>${training.startDate}</td>
							<td>${training.endDate}</td>
							<td>${training.amountReceived}</td>
							<td>${training.rating}</td>
							<td>${training.request}</td>
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
							<!--<th>MENOTR-NAME</th>-->
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>PROGRESS</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>AMMOUNT-RECIVED</th>
							<th>RATING</th>
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