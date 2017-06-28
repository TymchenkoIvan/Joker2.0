<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New joke</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css" />">
        <script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
        <script>tinymce.init({ selector:'textarea' });</script>
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.joke.validator.js" />"></script>
    </head>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <util:errorMessage errorMessage="${errorMessage}"/>
                <h2>New joke</h2>
                <form:form id="jokeForm" role="form" class="form-horizontal" action="jokeForm" commandName="jokeForm">
                    <div class="form-group">
                        <form:errors path="text" cssClass="validationError"/>
                        <form:textarea id="text" path="text" class="form-control" rows="15" cols="50" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="submit" value="Submit">
                    </div>
                </form:form>
            </div>
        </div>
    </body>
</html>
