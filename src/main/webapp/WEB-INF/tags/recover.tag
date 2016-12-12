<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="jokeId" required="true"%>

<c:if test="${pageContext.request.isUserInRole('admin')}">
	<a href="/joker.com/archive/${jokeId}/delete">delete</a> |
	<a href="/joker.com/archive/${jokeId}/recover">recover</a>
</c:if>
