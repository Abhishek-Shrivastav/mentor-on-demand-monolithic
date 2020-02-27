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
			<c:if test="${roleId == 1}">
				<jsp:include page="left-sidebar-menu3.jsp"/>
			</c:if>
			<c:if test="${roleId == 3}">
				<jsp:include page="left-sidebar-menu.jsp"/>
			</c:if>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Running Training</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr class="header-fix">
							<th>ID</th>
							<c:if test="${roleId == 1}">
							<th>MENOTR-NAME</th>
							</c:if>
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>PROGRESS</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>AMMOUNT-RECIVED</th>
							<th>RATING</th>
							<th>STATUS</th>
							<c:if test="${roleId == 3}">
							<th>ACTION</th>
							</c:if>
						</tr>
						<c:if test="${not empty trainingList}">
						<c:set var="i" value="0"/>
						<c:forEach var="training" items="${trainingList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<c:if test="${roleId == 1}">
							<td>${training.mentorName}</td>
							</c:if>
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
							<c:if test="${roleId == 3}">
							
							
								<c:if test="${((training.progress == 0) && (training.installmentStatus == 1) || (training.progress == 25) && (training.installmentStatus == 2) || (training.progress == 50) && (training.installmentStatus == 3) || (training.progress == 75) && (training.installmentStatus == 4))}">
									<td><a href="/mentor/progress/${training.id}">Progress</a></td>
								</c:if>
								<c:if test="${((training.progress == 0) && (training.installmentStatus == 0) || (training.progress == 25) && (training.installmentStatus == 1) || (training.progress == 50) && (training.installmentStatus == 2) || (training.progress == 75) && (training.installmentStatus == 3))}">
									<td>Pay Soon</td>
								</c:if>
								
								<c:if test="${((training.progress == 100) && (training.installmentStatus == 5))}">
									<td><a href="/mentor/complete/${training.id}">Complete</a></td>
								</c:if>
								<c:if test="${((training.progress == 100) && (training.installmentStatus == 4))}">
									<td>Pay Soon</td>
								</c:if>
							</c:if>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty trainingList}">
							<tr>
								<td colspan="12" style="text-align:center;">Empty list</td>
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