<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor - Home</title>
	<link href="../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function reloadform(edit)
		{
			var editId = edit;
			var rating = prompt("Please set rating");
			
			if(rating>=1 && rating<=5)
			{
				alert("Rating updated successfully.");
				window.location.href='http://localhost:9090/student/rating/'+editId+'/'+rating;
			}
			else
			if(rating=="" || rating==" " || rating==null)
			{
				return false;
			}
			else
			{
				alert("Rating value is invalid.\nValid values is (1,2,3,4,5) only.");
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
			<c:if test="${roleId == 1}">
				<jsp:include page="left-sidebar-menu3.jsp"/>
			</c:if>
			<c:if test="${roleId == 2}">
				<h4>All Training</h4>
				<p><a href="/student/training">Training</a></p>
			</c:if>
			<c:if test="${roleId == 3}">
				<jsp:include page="left-sidebar-menu.jsp"/>
			</c:if>
		</div>
		<div class="main1">
			<h3 style="text-align:right; padding-right:80px; margin-bottom:0px">${firstName}</h3>
			<h2 style="margin:0px;">All Training</h2>
			<div class="list">
				<div class="item" style="overflow:scroll; height:315px;">
					<table align="center" border="1" width="100%" cellpadding="2">
						<tr class="header-fix">
							<th>ID</th>
							<c:if test="${roleId == 1 || roleId == 2}">
							<th>MENOTR-NAME</th>
							</c:if>
							<th>STUDENT-NAME</th>
							<th>SLOT-TIME-FROM</th>
							<th>SLOT-TIME-TO</th>
							<th>TECHNOLOGY</th>
							<th>PROGRESS</th>
							<th>START-DATE</th>
							<th>END-DATE</th>
							<c:if test="${roleId == 2}">
							<th>PAID-AMOUNT</th>
							</c:if>
							<c:if test="${roleId != 2}">
							<th>AMOUNT-RECIVED</th>
							</c:if>
							<th>RATING</th>
							<th>STATUS</th>
							<c:if test="${roleId == 2}">
							<th>RATING</th>
							</c:if>
						</tr>
						<c:if test="${not empty trainingList}">
						<c:set var="i" value="0"/>
						<c:forEach var="training" items="${trainingList}">
						<tr>
							<td><c:out value="${i=i+1}"/></td>
							<c:if test="${roleId == 1 || roleId == 2}">
							<td>${training.mentorName}</td>
							</c:if>
							<td>${training.userName}</td>
							<td>${training.slotTimeFrom}</td>
							<td>${training.slotTimeTo}</td>
							<td>${training.techName}</td>
							<td>${training.progress}%</td>
							<td>${training.startDate}</td>
							<td>${training.endDate}</td>
							<c:if test="${roleId == 2}">
							<td>${training.totalFee}</td>
							</c:if>
							<c:if test="${roleId != 2}">
							<td>${training.amountReceived}</td>
							</c:if>
							<td>${training.rating}</td>
							<td>${training.request}</td>
							<c:if test="${roleId == 2}">
							<td>
							<button type="button" onclick="return reloadform(${training.id});">Rating</button>
							</td>
							</c:if>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty trainingList}">
							<tr>
								<td colspan="13" style="text-align:center;">Empty list</td>
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