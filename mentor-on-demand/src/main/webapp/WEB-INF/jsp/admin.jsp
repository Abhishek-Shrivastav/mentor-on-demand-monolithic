<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Home</title>
	<link href="../css/style.css" rel="stylesheet">
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<jsp:include page="admin-menu.jsp"/>
		</div>
		<div class="sidebar" align="center">
			<h4>Notification Bar</h4>
			<div class="scrollit">
			<table align="center" cellpadding="2">
				<c:if test="${not empty trainingList}">
				<c:forEach var="notify" items="${trainingList}">
					<c:if test="${notify.progress==0}">
					<tr>
						<td align="center"><a href="/admin/payment/${notify.id}">
							${notify.userName}(student) has paid. Now pay first installment to the ${notify.mentorName}(teacher).</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${notify.progress==25}">
					<tr>
						<td align="center"><a href="/admin/payment/${notify.id}">
							${notify.mentorName}(teacher) has completed 25% of syllabus. Now pay second installment to the ${notify.mentorName}.</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${notify.progress==50}">
					<tr>
						<td align="center"><a href="/admin/payment/${notify.id}">
							${notify.mentorName}(teacher) has completed 50% of syllabus. Now pay third installment to the ${notify.mentorName}.</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${notify.progress==75}">
					<tr>
						<td align="center"><a href="/admin/payment/${notify.id}">
							${notify.mentorName}(teacher) has completed 75% of syllabus. Now pay fourth installment to the ${notify.mentorName}.</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${notify.progress==100}">
					<tr>
						<td align="center"><a href="/admin/payment/${notify.id}">
							${notify.mentorName}(teacher) has completed 100% of syllabus. Now pay final installment to the ${notify.mentorName}.</a>
						</td>
					</tr>
					</c:if>
				</c:forEach>
				</c:if>
				<c:if test="${empty trainingList}">
				<tr>
					<td align="center" style="color:red;">You have no notification</td>
				</tr>
				</c:if>
			</table>
			</div>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Admin Panel</h2>
			<div class="list">
				<div class="item" style="overflow:scroll;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr class="header-fix">
							<th>ID</th>
							<th>MENTOR-NAME</th>
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>PROGRESS</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<th>AMMOUNT-RECIVED</th>
							<th>INSTALLMENT-STATUS</th>
							<th>RATING</th>
							<th>STATUS</th>
							<th>PAY-NOW</th>
						</tr>
						<c:if test="${not empty trainingList}">
						<c:set var="i" value="0"/>
						<c:forEach var="training" items="${trainingList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${training.mentorName}</td>
							<td>${training.userName}</td>
							<td>${training.slotTimeFrom}</td>
							<td>${training.slotTimeTo}</td>
							<td>${training.techName}</td>
							<td>${training.progress}%</td>
							<td>${training.startDate}</td>
							<td>${training.endDate}</td>
							<td>${training.amountReceived}</td>
							<td>${training.installmentStatus}</td>
							<td>${training.rating}</td>
							<td>${training.request}</td>
							<td><a href="/admin/payment/${training.id}">Pay</a></td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty trainingList}">
							<tr>
								<td colspan="14" style="text-align:center;">Empty list</td>
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