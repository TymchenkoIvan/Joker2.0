<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${sessionScope.user != null}">
	<td>
		<a href="/joker.com/${jokeId}/like" >
			<img height="27" width="20" src="<c:url value="/resources/web/like.png" />">
		</a>
	</td>
	<td>
		<a href="/joker.com/${jokeId}/dislike">
			<img height="27" width="20" src="<c:url value="/resources/web/dislike.png" />">
		</a>
	</td>
</c:if>