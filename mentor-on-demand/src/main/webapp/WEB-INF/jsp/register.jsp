<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
						<form:form method="post" modelAttribute="userForm" onsubmit="return equalPass();">
						<spring:bind path="userName">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Username : </td>
							<td><form:input path="userName" placeholder="Username"/></td>
							<td><form:errors path="userName" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="password">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Password : </td>
							<td><form:password path="password" placeholder="Password" onkeyup="check();"/></td>
							<td><form:errors path="password" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="confirmPassword">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Confirm-Password : </td>
							<td><form:password path="confirmPassword" placeholder="Confirm-Password" onkeyup="check();"/></td>
							<td><form:errors path="confirmPassword" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="firstName">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Firstname : </td>
							<td><form:input path="firstName" placeholder="Firstname"/></td>
							<td><form:errors path="firstName" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="lastName">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Lastname : </td>
							<td><form:input path="lastName" placeholder="Lsatname"/></td>
							<td><form:errors path="lastName" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="contact">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Contect : </td>
							<td><form:input path="contact" placeholder="Contact"/></td>
							<td><form:errors path="contact" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="roles">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Role : </td>
							<td>
								<form:radiobutton path="roles" value="2" checked="checked" />:Student
								<form:radiobutton path="roles" value="3" />:Mentor
							</td>
							<td><form:errors path="roles" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<spring:bind path="linkedInUrl">
						<tr class="${status.error ? 'has-error' : ''}">
							<td>Linkedin Id : </td>
							<td><form:input path="linkedInUrl" placeholder="Linkedin Id"/></td>
							<td><form:errors path="linkedInUrl" cssClass="form-error"></form:errors></td>
						</tr>
						</spring:bind>
						<tr>
							<td>Experince Year : </td>
							<td>
								<form:select path="yearOfExp">
									<form:option value="0">-- Select --</form:option>
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
									<form:option value="11">11</form:option>
									<form:option value="12">12</form:option>
									<form:option value="13">13</form:option>
									<form:option value="14">14</form:option>
									<form:option value="15">15</form:option>
								</form:select>
							</td>
							<td><form:errors path="yearOfExp"></form:errors></td>
						</tr>
						<form:hidden path="isActive" value="1"/>
						<tr>
							<td colspan="2" style="text-align:center;"><input type="submit" name="register" value="Register" /></td>
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
