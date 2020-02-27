<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Save Time Slot</title>
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
	
	<link href="../../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function save()
		{
			var timeFrom = document.getElementById('timeFrom').value;
			var timeTo = document.getElementById('timeTo').value;
			
			if(timeFrom != "" || timeTo != "")
			{
				var timeFromArr = timeFrom.split(":");
				var timeToArr = timeTo.split(":");
				
				if(timeFromArr.length!=3 && timeToArr.length!=3)
				{
					document.getElementById('timeFrom').value = "";
					document.getElementById('timeTo').value = "";
					
					alert("Time format is invalid!");
			        return false;
			    }
				else
				{
					if(isNaN(timeFromArr[0]) || isNaN(timeFromArr[1]) || isNaN(timeFromArr[2]))
					{
						document.getElementById('timeFrom').value = "";
						document.getElementById('timeTo').value = "";
						
						alert("Time format is invalid!");
						return false;
					}
					if(isNaN(timeToArr[0]) || isNaN(timeToArr[1]) || isNaN(timeToArr[2]))
					{
						document.getElementById('timeFrom').value = "";
						document.getElementById('timeTo').value = "";
						
						alert("Time format is invalid!");
						return false;
					}
					
					if(timeFromArr[0]<24 && timeFromArr[1]<60 && timeFromArr[2]<60)
					{
						if(timeToArr[0]<24 && timeToArr[1]<60 && timeToArr[2]<60)
						{
							alert("Time Slot Saved!");
							return true;
						}
					}
					else
					{
						document.getElementById('timeFrom').value = "";
						document.getElementById('timeTo').value = "";
						
						alert("Time format is invalid!");
						return false;
					}
				}
			}
			else
			{
				document.getElementById('timeFrom').value = "";
				document.getElementById('timeTo').value = "";
				
				alert("Empty");
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
			<jsp:include page="left-sidebar-menu2.jsp"/>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">Add Time Slot</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="save-slot" modelAttribute="slot" onsubmit="return save();" autocomplete="off">
							<tr>
								<td colspan="2"><form:hidden  path="mentorId" readonly="true" value="${mentorId}"  required="true"/></td>
							</tr>
							<tr>
								<td>Time From : </td>
								<td><form:input path="timeFrom" placeholder="Time From"/></td>
							</tr>
							<tr>
								<td>Time To : </td>
								<td><form:input path="timeTo" placeholder="Time To"/></td>
							</tr>
							<tr>
								<td colspan="2"><form:hidden  path="active" readonly="true" value="0"  required="true"/></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align:center;"><input type="submit" name="add" value="Insert" /></td>
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

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() 
	{
		$('#timeFrom').timepicker({ 'timeFormat': 'HH:mm:ss' });
		$('#timeTo').timepicker({ 'timeFormat': 'HH:mm:ss' });
	});
</script>
</html>