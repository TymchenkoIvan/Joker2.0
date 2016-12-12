<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/form.css" />">
        <title>Create new user</title>
    </head>
    <body>
        <section class="container">
            <util:errorMessage errorMessage="${errorMessage}"/>
            <div class="form">
                <util:formImage/>
                <h1>Sign Up</h1>
                <form:form action="signup" commandName="signUpForm">
                    <p>
                        <form:errors path="login" cssClass="errorSpringMVC"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </p>
                    <p>
                        <form:errors path="mail" cssClass="errorSpringMVC"/>
                        <form:input path="mail" type="text" class="form-control" placeholder="E-mail *"/>
                    </p>
                    <p>
                        <form:errors path="telephone" cssClass="errorSpringMVC"/>
                        <form:input path="telephone" type="text" class="form-control" placeholder="Telephone"/>
                    </p>
                    <p>
                        <form:errors path="password" cssClass="errorSpringMVC"/>
                        <form:input path="password" type="text" class="form-control" placeholder="Password *"/>
                    </p>
                    <p>
                        <form:errors path="confirm" cssClass="errorSpringMVC"/>
                        <form:input path="confirm" type="text" class="form-control" placeholder="Confirm password *"/>
                    </p>
                    <p>
                        <input type="submit" value="Create user">
                    </p>
                    <p><input type="button" onclick="location.href='/joker.com/login';" value="Create" /></p>
                </form:form>
            </div>
        </section>
    </body>
</html>