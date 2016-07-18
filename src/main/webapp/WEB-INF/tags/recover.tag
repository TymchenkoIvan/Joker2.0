<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${sessionScope.user.status == 'admin'}">
	<td>
		<a href="/joker.com/archive/${jokeId}/delete">delete</a>
	</td>
	<td>
		<a href="/joker.com/archive/${jokeId}/recover">recover</a>
	</td>
</c:if>
