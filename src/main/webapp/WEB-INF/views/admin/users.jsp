<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Jokes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <tr> <center><b>Welcome in admin control panel!</b></center></tr>

    <%  String adminName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("jokerAdmin"))
                    adminName = cookie.getValue();

        if (adminName == null)
            response.sendRedirect("/com_company/admin/admin");  %>

    <table border="0" width="80%" align="center">
        <tr align="center">
            <td>Hello, <%=adminName%>!  <a href="/com_company/admin/sign_out" method="post"><b>sign out</b></a></td>
            <td><img height="100" width="150" src="http://fc06.deviantart.net/fs70/i/2011/110/8/a/a_joker_smiles_by_leehi-d2pnjx2.png"></td>
            <td><h4><a href="/com_company/admin/jokes" method="post"><b>JOKES</b></a></h4></td>
            <td><h4><a href="/com_company/admin/users" method="post"><b>USERS</b></a></h4></td>
        </tr>
    </table>

    <tr> <center><h3>All users info:</h3></center></tr>
    <table class="table table-striped">
        <thead>
            <tr>
                <td><center><b>Login</b></center></td>
                <td><center><b>E-mail</b></center></td>
                <td><center><b>Tel.</b></center></td>
                <td><center><b>Password</b></center></td>
                <td><center><b>Mark</b></center></td>
            </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><center>${user.login}</center></td>
                <td><center>${user.mail}</center></td>
                <td><center>${user.telephone}</center></td>
                <td><center>${user.password}</center></td>
                <td><center>${user.mark}</center></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
