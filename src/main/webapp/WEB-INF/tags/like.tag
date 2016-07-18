<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${sessionScope.user != null}">
	<td>
		<a href="/joker.com/${jokeId}/like">Like!</a>
	</td>
	<td>
		<a href="/joker.com/${jokeId}/dislike">Dislike</a>
	</td>
</c:if>