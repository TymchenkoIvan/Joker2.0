<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/form.css" />">
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
                        <form:errors path="login" cssClass="errorSpringMVC"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </p>
                    <p class="form-group">
                        <form:errors path="password" cssClass="errorSpringMVC"/>
                        <form:input path="password" name="password" placeholder="Password *" type="password" class="form-control"/>
                    </p>
                    <p>
                        <input type="submit" value="Login">
                    </p>
                    <p><input type="button" onclick="location.href='/joker.com/signup';" value="Create user" /></p>
                </form:form>
            </div>
        </section>
    </body>
</html>
<!--
<div class="container">
    <table border="0" align="center">
        <td align="center">
            <table>
                <form:form action="login" commandName="logInForm">
                    <div>
                        <img height="200" width="200" src="<c:url value="/resources/web/main_clown.jpg" />"></div>
                    <div>
                    <h3>Log in</h3></div>
                    <div>
                        <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${errorMessage}</p>
                    </div>
                    <div class="form-group">
                        <form:errors path="login" cssClass="errorSpringMVC"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="password" cssClass="errorSpringMVC"/>
                        <form:input name="password" path="password" type="password" class="form-control" placeholder="Password *"/>
                    </div>

                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Log In">
                        <a href="signup">you can create new user</a>
                    </div>
                </form:form>
            </table>
        </td>
    </table>
</div>
</body> -->