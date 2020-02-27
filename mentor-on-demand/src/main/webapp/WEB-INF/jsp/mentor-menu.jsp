<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<div class="log">
	<a href="/mentor/home">Home</a> |
	<a href="/mentor/edit-profile">Edit Profile</a> |
	<a href="/mentor/view-skill">Mentor Skill</a> |
	<a href="/mentor/view-slot">Time Calendar</a> |
	<a href="/mentor/training">Training</a> |
	<a href="/mentor/request">Request</a> |
	<a href="/mentor/payment">Payment Status</a> |
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<!--<a href="/mentor/logout">Logout</a>-->
</div>