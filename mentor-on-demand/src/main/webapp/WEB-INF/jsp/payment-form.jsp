<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Payment Form</title>
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function pay()
		{
			var result = confirm("Are you sure to pay this amount?\nIf 'yes' click on 'OK' or 'no' click on 'Cancel'!!!");
			
			if(result)
			{
				alert("Payment has been done successfully!");
				
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
			<h2>Payment Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/admin/pay" onsubmit="return pay()">
						<tr>
							<td>Training Id : </td>
							<td><form:input  path="trainingId" readonly="true" value="${training.id}" required="true"/></td>
						</tr>
						<tr>
							<td>Teacher Code : </td>
							<td><input type="text" name="mentorCode" value="${training.userId}" readonly="readonly" required="true"/></td>
						</tr>
						<tr>
							<td>Teacher Name : </td>
							<td><input type="text" name="mentorName" value="${mentorName}" readonly="readonly" required="true"/></td>
						</tr>
						<tr>
							<td>Total Fee/Payment : </td>
							<td><form:input  path="totalFees" readonly="true" value="${training.totalFee}" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="recivedAmount" readonly="true" value="${training.amountReceived + (training.totalFee / 5)}" /></td>
						</tr>
						<tr>
							<td>Remaining Fee/Payment : </td>
							<td><form:input  path="remainingPayment" readonly="true" value="${training.totalFee - training.amountReceived}" required="true"/></td>
						</tr>
						<tr>
							<td>Installment Amount : </td>
							<td><form:input path="payment" readonly="true" value="${training.totalFee / 5}" required="true"/></td>
						</tr>
						<tr>
							<td>Installment No. : </td>
							<td><form:input path="installmentStatus" readonly="true" value="${training.installmentStatus + 1}" required="true"/></td>
						</tr>
						<tr>
							<td>Payment Date : </td>
							<td><form:input path="dateTime" value="${dateTime}" readonly="true" required="true"/></td>
						</tr>
						<tr>
							<td>Confirm : </td>
							<td><form:checkbox path="paymentStatus" value="1" required="true"/> : All payment inputs are valid.</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="add" value="Insert" /></td>
						</tr>
						</form:form>
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