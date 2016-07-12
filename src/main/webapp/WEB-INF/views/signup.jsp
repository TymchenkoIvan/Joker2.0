<%@ include file="jspf/directive/page.jspf"%>

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
                    <form role="form" class="form-horizontal" action="/joker.com/signup" method="post">
                        <div><img height="200" width="200" src="<c:url value="/resources/web/main_clown.jpg" />"></div>
                        <div><h3>Sign Up</h3></div>
                        <div><p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${errorMessage}</p></div>
                        <div class="form-group"><input type="text" class="form-control" name="login" placeholder="Login *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="mail" placeholder="E-mail *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="telephone" placeholder="Telephone"></div>
                        <div class="form-group"><input type="text" class="form-control" name="password" placeholder="Password *"></div>
                        <div class="form-group"><input type="text" class="form-control" name="confirm" placeholder="Confirm password *"></div>

                        <div class="form-group"><input type="submit" class="btn btn-primary" value="Create user">
                            <a href="/joker.com/login">or you can login</a>
                        </div>
                    </form>
                </table>
            </td>
        </table>
    </div>
</body>
</html>