<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${sessionScope.user != null}">
	<td>
		<a href="/joker.com/like?jokeId=${jokeId}">Like!</a>
	</td>
	<td>
		<a href="/joker.com/dislike?jokeId=${jokeId}">Dislike</a>
	</td>
</c:if>