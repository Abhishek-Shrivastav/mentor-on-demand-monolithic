<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
			
<div class="log">
	<a href="/admin/home">Home</a> |
	<a href="/admin/edit-profile">Edit Profile</a> |
	<a href="/admin/view-tech">Technology</a> |
	<a href="/admin/training">Training</a> |
	<a href="/admin/user">User</a> |
	<a href="/admin/running-payment">Payment</a> |
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<!--<a href="/admin/logout">Logout</a>-->
</div>