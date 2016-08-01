<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <style>
        .error {
            color: red; font-weight: bold;
        }
    </style>
</head>

<body>

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
                        <form:errors path="login" cssClass="error"/>
                        <form:input name="login" path="login" type="text" class="form-control" placeholder="Login *"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="password" cssClass="error"/>
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
</body>
</html>