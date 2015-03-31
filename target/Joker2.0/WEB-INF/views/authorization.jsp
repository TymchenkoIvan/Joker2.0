<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <p><img height="200" width="200" src="http://fs199.www.ex.ua/show/46462669/46462669.jpg?1600"></p>

        <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p>

            <table border="0" width="100%">
                <tr>
                    <td align="center">
                        <table>
                            <form role="form" class="form-horizontal" action="/com_company/adduser" method="post">
                                <div class="form-group"><h3>User authorization</h3></div>
                                <div class="form-group"><input type="text" class="form-control" name="login" placeholder="Login *"></div>
                                <div class="form-group"><input type="text" class="form-control" name="mail" placeholder="E-mail *"></div>
                                <div class="form-group"><input type="text" class="form-control" name="telephone" placeholder="Telephone"></div>
                                <div class="form-group"><input type="text" class="form-control" name="password" placeholder="Password *"></div>
                                <div class="form-group"><input type="text" class="form-control" name="repeat_password" placeholder="Repeat password *"></div>

                                <div class="form-group"><input type="submit" class="btn btn-primary" value="Authorize">
                                    <a href="/com_company/">or you can Sign in</a>
                                </div>
                            </form>
                        </table>
                    </td>
                </tr>
            </table>
    </div>
</body>
</html>