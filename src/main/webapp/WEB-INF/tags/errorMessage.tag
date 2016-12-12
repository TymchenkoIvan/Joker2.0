<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="errorMessage" required="true"%>

<div class="error">
	<p>${errorMessage}</p>
</div>