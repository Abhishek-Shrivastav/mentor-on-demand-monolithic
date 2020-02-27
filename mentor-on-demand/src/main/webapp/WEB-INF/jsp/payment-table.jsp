<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Payment Table</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function pay()
		{
			var result = confirm("Are you sure to pay the mentor?");
			
			if(result)
			{	
				return true;
			}
			else
			{
				return false;
			}
		}
	</script>
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<jsp:include page="admin-menu.jsp"/>
		</div>
		<div class="main">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Payment Panel</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
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
							<th>PAY</th>
						</tr>
						<c:if test="${not empty training}">
						<c:set var="i" value="0"/>
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
							<td><a href="/admin/paypal/${training.id}" onclick="return pay()">Payment</a></td>
						</tr>
						</c:if>
						<c:if test="${empty training}">
							<tr>
								<td colspan="14">Empty list</td>
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