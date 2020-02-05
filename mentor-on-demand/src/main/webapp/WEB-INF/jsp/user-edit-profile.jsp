<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - Edit Profile</title>
	<link href="../css/style.css" rel="stylesheet">
	<script type="text/javascript">
		function update()
		{
			alert("Form Updated!");
		}
	</script>
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<jsp:include page="left-sidebar-menu7.jsp"/>
		</div>
		<div class="main">
			<h2>Edit Profile Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="update-profile" onsubmit="update()">
						<tr>
							<td></td>
							<td><form:hidden  path="id" readonly="true" required="true"/></td>
						</tr>
						<tr>
							<td>Firstname : </td>
							<td><form:input path="firstName" placeholder="Firstname" required="true"/></td>
						</tr>
						<tr>
							<td>Lastname : </td>
							<td><form:input path="lastName" placeholder="Lsatname" required="true"/></td>
						</tr>
						<tr>
							<td>Contect : </td>
							<td><form:input path="contact" placeholder="Contact" required="true"/></td>
						</tr>
						<tr>
							<td>Linkedin Id : </td>
							<td><form:input path="linkedInUrl" placeholder="Linkedin Id" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><form:hidden  path="yearOfExp" readonly="true" value="0" required="true"/></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="edit" value="Update"/></td>
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