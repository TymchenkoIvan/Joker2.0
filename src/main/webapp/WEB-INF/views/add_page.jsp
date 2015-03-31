<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New joke</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table border="0" width="100%">
        <tr>
            <td valign="top" align="center">
                <form class="form-inline" role="form" action="/com_company/index" method="post">
                    <input type="submit" class="btn btn-default" value="Main">
                </form>
                <form class="form-inline" role="form" action="/com_company/add_page" method="post">
                    <input type="submit" class="btn btn-primary" value="Add joke">
                </form>
                <form class="form-inline" role="form" action="/com_company/archive" method="post">
                    <button type="submit" class="btn btn-default">Archive</button>
                </form>
                <form class="form-inline" role="form" action="/com_company/faq" method="post">
                    <button type="submit" class="btn btn-default">FAQ</button>
                </form>
            </td>
            <td align="center"><p><img height="200" width="200" src="http://fs199.www.ex.ua/show/46462669/46462669.jpg?1600"></p></td>
            <td>
                <right>
                    <b>Hello, ${login}</b>
                    <form class="form-inline" role="form" action="/com_company/sign_out" method="post">
                        <button type="submit" class="btn btn-default">Sign out</button>
                    </form>
                </right>
            </td>
        </tr>
    </table>

    <form role="form" class="form-horizontal" action="/com_company/add" method="post">
        <div class="form-group"><input type="text" class="form-control" name="text" placeholder="Joke"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add joke"></div>
    </form>
</div>
</body>
</html>
