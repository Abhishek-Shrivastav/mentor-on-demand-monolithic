<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Complete Payment</title>
	<link href="../../css/style.css" rel="stylesheet">
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
			<c:if test="${roleId == 3}">
				<jsp:include page="mentor-menu.jsp"/>
			</c:if>
		</div>
		<div class="sidebar" align="center">
			<jsp:include page="left-sidebar-menu5.jsp"/>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Complete Payment Panel</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr class="header-fix">
							<th>ID</th>
							<th>TRAINING-ID</th>
							<th>TOTAL-PAID-AMOUNT</th>
							<th>INSTALLMENT-STATUS</th>
							<th>PAYMENT-DATE</th>
							<th>STATUS</th>
						</tr>
						<c:if test="${not empty payList}">
						<c:set var="i" value="0"/>
						<c:forEach var="pay" items="${payList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<td>${pay.trainingId}</td>
							<td>${pay.recivedAmount}</td>
							<td>${pay.installmentStatus}</td>
							<td>${pay.dateTime}</td>
							<c:if test="${pay.paymentStatus == 1}">
							<td>Payment Completed</td>
							</c:if>
							<c:if test="${pay.paymentStatus == 0}">
							<td>Fail</td>
							</c:if>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty payList}">
							<tr>
								<td colspan="8" style="text-align:center;">Empty list</td>
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