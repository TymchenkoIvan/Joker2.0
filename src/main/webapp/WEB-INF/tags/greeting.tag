<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>

<div class="login">
	<c:choose>
		<c:when test="${not empty pageContext.request.userPrincipal}">
			<p>Hi, ${pageContext.request.userPrincipal.name}</p>
			<p><a href="/joker.com/logout">Log out</a></p>
		</c:when>
		<c:otherwise>
			<a href="/joker.com/login">Log In</a> | <a href="/joker.com/signup">Sign Up</a>
		</c:otherwise>
	</c:choose>
</div>