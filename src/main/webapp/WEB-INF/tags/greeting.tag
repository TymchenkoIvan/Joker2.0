<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${sessionScope.user != null}">
		Hi, ${sessionScope.user.login} | <a href="/joker.com/logout">Log out</a>
	</c:when>
	<c:otherwise>
		<a href="/joker.com/login">Log In</a> | <a href="/joker.com/signup">Sign Up</a>
	</c:otherwise>
</c:choose>