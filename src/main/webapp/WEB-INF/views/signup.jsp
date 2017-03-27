<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new user</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/form.css" />">
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.signup.validator.js" />"></script>
    </head>
    <body>
        <section class="container">
            <util:errorMessage errorMessage="${errorMessage}"/>
            <div class="form">
                <util:formImage/>
                <h1>Sign Up</h1>
                <form:form action="signup" commandName="signUpForm">
                    <p>
                        <form:errors path="login" cssClass="validationError"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </p>
                    <p>
                        <form:errors path="mail" cssClass="validationError"/>
                        <form:input path="mail" type="text" class="form-control" placeholder="E-mail *"/>
                    </p>
                    <p>
                        <form:errors path="telephone" cssClass="validationError"/>
                        <form:input path="telephone" type="text" class="form-control" placeholder="Telephone"/>
                    </p>
                    <p>
                        <form:errors path="password" cssClass="validationError"/>
                        <form:input path="password" type="text" class="form-control" placeholder="Password *"/>
                    </p>
                    <p>
                        <form:errors path="confirm" cssClass="validationError"/>
                        <form:input path="confirm" type="text" class="form-control" placeholder="Confirm password *"/>
                    </p>
                    <p>
                        <input type="submit" class="submit" value="Create user">
                    </p>
                    <p><input type="button" onclick="location.href='/joker.com/login';" value="Login" /></p>
                </form:form>
            </div>
        </section>
    </body>
</html>