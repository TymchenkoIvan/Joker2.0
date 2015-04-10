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

    <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p>

    <tr> <center><h3>Archive:</h3></center></tr>
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
                <td><center><a href="/com_company/archive/delete?jokeId=${joke.id}&login=<%=userName%>"><b>delete</b></a></center></td>
                <td><center><a href="/com_company/archive/recover?jokeId=${joke.id}&login=<%=userName%>"><b>recover</b></a></center></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

