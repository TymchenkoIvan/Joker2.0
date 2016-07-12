<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${sessionScope.user.mark == 'admin'}">
	<td>
		<a href="/joker.com/archive/delete?jokeId=${jokeId}">delete</a>
	</td>
	<td>
		<a href="/joker.com/archive/recover?jokeId=${jokeId}">recover</a>
	</td>
</c:if>
