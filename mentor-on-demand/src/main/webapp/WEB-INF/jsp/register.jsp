<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Mentor On Demand - Home</title>
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="outer">
		<div class="header">
			<h1>Mentor On Demand</h1>
		</div>
		<div class="menu">
			<jsp:include page="logmenu.jsp"/>
		</div>
		<div class="main">
			<h2>Register Form</h2>
			<div class="list">
				<div class="item">
					<table align="center" width="40%" cellpadding="2">
						<form:form method="post" action="register" modelAttribute="user">
						<tr>
							<td>Username : </td>
							<td><form:input path="userName" placeholder="Username" required="true"/></td>
						</tr>
						<tr>
							<td>Password : </td>
							<td><form:input path="password" placeholder="Password" required="true"/></td>
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
							<td>Role : </td>
							<td>
								<form:radiobutton path="roleId" value="2" checked="checked" />:Student
								<form:radiobutton path="roleId" value="3" />:Mentor
							</td>
						</tr>
						<tr>
							<td>Linkedin Id : </td>
							<td><form:input path="linkedInUrl" placeholder="Linkedin Id" required="true"/></td>
						</tr>
						<tr>
							<td>Experince Year : </td>
							<td>
								<form:select path="yearOfExp" required="true">
									<form:option value="">-- Select --</form:option>
									<form:option value="1">1</form:option>
									<form:option value="2">2</form:option>
									<form:option value="3">3</form:option>
									<form:option value="4">4</form:option>
									<form:option value="5">5</form:option>
									<form:option value="6">6</form:option>
									<form:option value="7">7</form:option>
									<form:option value="8">8</form:option>
									<form:option value="9">9</form:option>
									<form:option value="10">10</form:option>
								</form:select>
							</td>
						</tr>
						<form:hidden path="isActive" value="1"/>
						<tr>
							<td colspan="2"><input type="submit" name="register" value="Register" /></td>
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