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

    <%  String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("jokerUser"))
                    userName = cookie.getValue();

        if (userName == null)
            response.sendRedirect("/com_company/");  %>

    <table border="0" width="80%" align="center">
        <tr align="center">
            <td>Hello, <%=userName%>!  <a href="/com_company/sign_out" method="post"><b>sign out</b></a></td>
            <td><img height="100" width="150" src="http://fc06.deviantart.net/fs70/i/2011/110/8/a/a_joker_smiles_by_leehi-d2pnjx2.png"></td>
            <td><h4><a href="/com_company/index" method="post"><b>MAIN</b></a></h4></td>
            <td><h4><a href="/com_company/add_page" method="post"><b>ADD</b></a></h4></td>
            <td><h4><a href="/com_company/archive" method="post"><b>ARCHIVE</b></a></h4></td>
            <td><h4><a href="/com_company/faq" method="post"><b>FAQ</b></a></h4></td>
        </tr>
    </table>

    <tr> <center><h3>New joke:</h3></center></tr>
    <form role="form" class="form-horizontal" action="/com_company/add" method="post">
       <!-- <div class="form-group"><input type="text" class="form-control" name="text" placeholder="Joke"></div>-->
        <div class="form-group"><textarea class="form-control" rows="15" cols="50" name="text" id="comment"></textarea></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="new joke"></div>
    </form>
</div>
</body>
</html>
