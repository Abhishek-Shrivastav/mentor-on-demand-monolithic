<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			<h2>Login Form</h2>
			<div class="list">
				<div class="item">
					<form method="POST" action="${contextPath}/signin">
					<table align="center" width="40%" cellpadding="2" class="${error != null ? 'has-error' : ''}">
						<tr>
							<td>Username : </td>
							<td><input name="username" type="text" placeholder="Username"/></td>
						</tr>
						<tr>
							<td>Password : </td>
							<td><input name="password" type="password" placeholder="Password"/></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<input type="submit" name="login" value="Login" />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;">
								<span class="form-error">${error}</span>
								<span class="form-error">${message}</span>
							</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
		<div class="footer">
			<h5>Mentor On demand</h5>
		</div>
	</div>
</body>
</html>