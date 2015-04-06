<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <table border="0" align="center">
            <td align="center">
                <table>
                    <form role="form" class="form-horizontal" action="/com_company/adduser" method="post">
                        <div><img height="200" width="200" src="http://fs199.www.ex.ua/show/46462669/46462669.jpg?1600"></div>
                        <div><h3>New user</h3></div>
                        <div><p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p></div>
                        <div class="form-group"><input type="text" class="form-control" name="login" placeholder="Login *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="mail" placeholder="E-mail *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="telephone" placeholder="Telephone"></div>
                        <div class="form-group"><input type="text" class="form-control" name="password" placeholder="Password *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="repeat_password" placeholder="Repeat password *"></div>

                        <div class="form-group"><input type="submit" class="btn btn-primary" value="Create user">
                            <a href="/com_company/">or you can Sign in</a>
                        </div>
                    </form>
                </table>
            </td>
        </table>
    </div>
</body>
</html>