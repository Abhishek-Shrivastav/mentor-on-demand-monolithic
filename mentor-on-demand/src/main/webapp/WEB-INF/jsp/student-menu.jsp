<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<div class="log">
	<a href="/student/home">Home</a> |
	<a href="/student/edit-profile">Edit Profile</a> |
	<a href="/student/training">Training</a> |
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<!--<a href="/student/logout">Logout</a>-->
</div>