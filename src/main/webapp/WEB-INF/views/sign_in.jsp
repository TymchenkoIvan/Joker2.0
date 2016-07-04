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

    <%  String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("jokerUser"))
                    userName = cookie.getValue();

        if (userName != null)
            response.sendRedirect("/joker.com/index");  %>

    <table border="0" align="center">
        <td align="center">
            <table>
                <form action="/com_company/sign_in/sign_in" method="post">
                    <div><img height="200" width="200" src="http://fs199.www.ex.ua/show/46462669/46462669.jpg?1600"></div>
                    <div><h3>Sign in</h3></div>
                    <div><p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p></div>
                    <div class="form-group"><input type="text" class="form-control" name="login" placeholder="Login *"></div>
                    <div class="form-group"><input  type=password class="form-control" name="password" placeholder="Password *"></div>

                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Sign in">
                        <a href="./authorization">you can create new user</a>
                    </div>
                </form>
            </table>
        </td>
    </table>
</div>
</body>
</html>