<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${not empty pageContext.request.userPrincipal}">
	<img onclick="sendVote('${jokeId}', 'like')" height="20" width="20" src="<c:url value="/resources/web/like.png" />">
	<img onclick="sendVote('${jokeId}', 'dislike')"height="20" width="20" src="<c:url value="/resources/web/dislike.png" />">
</c:if>