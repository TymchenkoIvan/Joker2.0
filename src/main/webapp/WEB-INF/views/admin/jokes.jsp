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

    <%  String adminName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("jokerAdmin"))
                    adminName = cookie.getValue();

        if (adminName == null)
            response.sendRedirect("/com_company/admin/admin");  %>

    <tr> <center><b>Welcome in admin control panel!</b></center></tr>

    <table border="0" width="80%" align="center">
        <tr align="center">
            <td>Hello, <%=adminName%>!  <a href="/com_company/admin/sign_out" method="post"><b>sign out</b></a></td>
            <td><img height="100" width="150" src="http://fc06.deviantart.net/fs70/i/2011/110/8/a/a_joker_smiles_by_leehi-d2pnjx2.png"></td>
            <td><h4><a href="/com_company/admin/jokes" method="post"><b>JOKES</b></a></h4></td>
            <td><h4><a href="/com_company/admin/users" method="post"><b>USERS</b></a></h4></td>
        </tr>
    </table>

    <tr> <center><b><h3>New jokes:</h3></b></center></tr>
    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><center><b>Likes</b></center></td>
            <td><center><b>Dislikes</b></center></td>
            <td><center><b>Joke</b></center></td>
        </tr>
        </thead>
        <c:forEach items="${jokes}" var="joke">
            <tr>
                <jsp:useBean id="beanNow" class="java.util.Date" />
                <fmt:formatDate value="${joke.date}" var="s_now" pattern="dd.MM.YYYY" />
                <td>${s_now}</td>
                <td><center><b>${joke.likes}</b></center></td>
                <td><center>${joke.dislikes}</center></td>
                <td>${joke.text}</td>
                <td><a href="/com_company/admin/jokes/toArchive?jokeId=${joke.id}&login=<%=adminName%>"><b>to archive</b></a></td>
            </tr>
        </c:forEach>
    </table>

    <tr> <center><b><h3>Archivated jokes:</h3></b></center></tr>
    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><center><b>Likes</b></center></td>
            <td><center><b>Dislikes</b></center></td>
            <td><center><b>Joke</b></center></td>
        </tr>
        </thead>
        <c:forEach items="${jokesArchive}" var="jokeArchiv">
            <tr>
                <jsp:useBean id="beanAfter" class="java.util.Date" />
                <fmt:formatDate value="${jokeArchiv.date}" var="date" pattern="dd.MM.YYYY" />
                <td>${date}</td>
                <td><center><b>${jokeArchiv.likes}</b></center></td>
                <td><center>${jokeArchiv.dislikes}</center></td>
                <td><center>${jokeArchiv.text}</center></td>
                <td><a href="/com_company/admin/jokes/recover?jokeId=${jokeArchiv.id}&login=<%=adminName%>"><b>recover</b></a></td>
                <td><a href="/com_company/admin/jokes/delete?jokeId=${jokeArchiv.id}&login=<%=adminName%>"><b>delete</b></a></td>
            </tr>
        </c:forEach>
    </table>

    <tr> <center><b><h3>Deleted jokes:</h3></b></center></tr>
    <table class="table table-striped">
        <thead>
        <tr>
            <td><center><b>Likes</b></center></td>
            <td><center><b>Dislikes</b></center></td>
            <td><center><b>Joke</b></center></td>
        </tr>
        </thead>
        <c:forEach items="${deleted}" var="delete">
            <tr>
                <td><center><b>${delete.likes}</b></center></td>
                <td><center>${delete.dislikes}</center></td>
                <td><center>${delete.text}</center></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
