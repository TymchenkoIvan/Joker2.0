<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">

    <!-- в связи с тем, что в проекте нет Spring Security роходится обходиться данным котылем
    Начало костыля:-->
    <c:if test="${isLogin eq 'remove'}">
        <c:remove var="login" />
    </c:if>
    <!-- Конец костыля:-->

    <p><img height="200" width="200" src="http://fs199.www.ex.ua/show/46462669/46462669.jpg?1600"></p>

    <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p>

    <form action="/com_company/sign_in/sign_in" method="post">
        <table border="0" width="100%">
            <tr>
                <td align="center">
                    <table>
                        <tr>
                            <td align="right"><b>Login: </b></td>
                            <td align="center">
                                <input type="text" name="login" size="24" maxlength="256" />
                            </td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td align="right"><b>Password: </b></td>
                            <td align="center"><input type=password name="password" size="24" maxlength="256"/></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td colspan="2" align="right"><input type="submit" class="btn btn-primary" value="Sign in"></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td colspan="2" align="right"><a href="/com_company/authorization">you can create new user</a></td>
                    </table>
                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>