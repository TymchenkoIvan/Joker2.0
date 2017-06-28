<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/form.css" />">
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.login.validator.js" />"></script>
        <title>Sign in</title>
    </head>
    <body>
        <section class="container">
            <util:errorMessage errorMessage="${errorMessage}"/>
            <div class="form">
                <util:formImage/>
                <h1>Log in</h1>
                <form:form commandName="logInForm" action="login">
                    <p>
                        <form:errors path="login" cssClass="validationError"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </p>
                    <p class="form-group">
                        <form:errors path="password" cssClass="validationError"/>
                        <form:input path="password" name="password" placeholder="Password *" type="password" class="form-control"/>
                    </p>
                    <p>
                        <input type="submit" class="submit" value="Login">
                    </p>
                    <p><input type="button" onclick="location.href='/joker.com/signup';" value="Create user" /></p>
                </form:form>
            </div>
        </section>
    </body>
</html>