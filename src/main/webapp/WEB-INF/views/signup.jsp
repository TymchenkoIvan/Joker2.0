<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Create new user</title>
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
                <form:form action="signup" commandName="signUpForm">
                    <div>
                        <img height="200" width="200" src="<c:url value="/resources/web/main_clown.jpg" />">
                    </div>
                    <div><h3>Sign Up</h3></div>
                    <div>
                        <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${errorMessage}</p>
                    </div>
                    <div class="form-group">
                        <form:errors path="login" cssClass="error"/>
                        <form:input path="login" type="text" class="form-control" placeholder="Login *"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="mail" cssClass="error"/>
                        <form:input path="mail" type="text" class="form-control" placeholder="E-mail *"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="telephone" cssClass="error"/>
                        <form:input path="telephone" type="text" class="form-control" placeholder="Telephone"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="password" cssClass="error"/>
                        <form:input path="password" type="text" class="form-control" placeholder="Password *"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="confirm" cssClass="error"/>
                        <form:input path="confirm" type="text" class="form-control" placeholder="Confirm password *"/>
                    </div>

                    <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Create user">
                        <a href="login">or you can login</a>
                    </div>
                </form:form>
            </table>
        </td>
    </table>
</div>
</body>
</html>