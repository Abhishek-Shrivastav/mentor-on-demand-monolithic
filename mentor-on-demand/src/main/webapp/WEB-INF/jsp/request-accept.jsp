<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Request Accept</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function update()
		{
			var result = confirm("Want to accept request?\nIf 'yes', you can't declined request!");
			
			if(result)
			{
				alert("Request Accepted!");
				
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
			<jsp:include page="left-sidebar-menu.jsp"/>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Date Allotment</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="/mentor/request-accept" onsubmit="return update()" autocomplete="off">
						<tr>
							<td colspan="2"><form:hidden  path="id" readonly="true"/></td>
						</tr>
						<tr>
							<td>Start Date : </td>
							<td><form:input path="startDate" placeholder="Start Date"/></td>
						</tr>
						<tr>
							<td>End Date : </td>
							<td><form:input path="endDate" placeholder="End Date"/></td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="request" readonly="true" value="2" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="accept" value="Accept"/></td>
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

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript">
$(function() {
    $('#startDate').datepicker({'dateFormat':'yy-mm-dd'});
});

$(function() {
    $('#endDate').datepicker({'dateFormat':'yy-mm-dd'});
});
</script>
</html>