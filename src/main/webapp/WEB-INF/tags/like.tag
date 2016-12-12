<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${not empty pageContext.request.userPrincipal}">
	<a href="/joker.com/${jokeId}/like">
		<img height="20" width="20" src="<c:url value="/resources/web/like.png" />">
	</a>
	<a href="/joker.com/${jokeId}/dislike">
		<img height="20" width="20" src="<c:url value="/resources/web/dislike.png" />">
	</a>
</c:if>