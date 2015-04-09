<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FAQ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("jokerUser"))
                    userName = cookie.getValue();

        if (userName == null)
            response.sendRedirect("/com_company/");%>

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

    <table class="table table-striped">
        <thead>
        <tr> <center><h3>F.A.Q.</h3></center></tr>
        </thead>
        <tr><td>1. You can add new jokes.</td></tr>
        <tr><td>2. You can once rate every joke. You need to be authorized.</td></tr>
        <tr><td>3. You can create new user with unique login and e-mail address.</td></tr>
        <tr><td>4. Every bad joke with more than 10 votes will be move to archive.</td></tr>
        <tr><td>5. You can delete or recover joke from archive.</td></tr>
        <tr><td>6. After recovering all votes will be deleted from joke.</td></tr>
        <tr><td>7. Used technologies: Java, SpringMVC, Maven, Hibernate, JPA, MySQL, JSTL, Tomcat, Bootstrap, HTML&CSS.</td></tr>
        <tr><td>8. <a href="https://github.com/TymchenkoIvan/Joker2.0">You can find it on GitHub</a>.</td></tr>
    </table>
</div>
</body>
</html>
