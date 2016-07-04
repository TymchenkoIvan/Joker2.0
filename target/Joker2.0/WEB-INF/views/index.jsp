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
            response.sendRedirect("/sign_in");  %>

    <table border="0" width="80%" align="center">
        <tr align="center">
            <td>Hello, <%=userName%>!  <a href="../sign_out" method="post"><b>sign out</b></a></td>
            <td><img height="100" width="150" src="http://fc06.deviantart.net/fs70/i/2011/110/8/a/a_joker_smiles_by_leehi-d2pnjx2.png"></td>
            <td><h4><a href="../" method="post"><b>MAIN</b></a></h4></td>
            <td><h4><a href="../add_page" method="post"><b>ADD</b></a></h4></td>
            <td><h4><a href="../archive" method="post"><b>ARCHIVE</b></a></h4></td>
            <td><h4><a href="../faq" method="post"><b>FAQ</b></a></h4></td>
        </tr>
    </table>

    <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td></td>
            <td><center><b>Joke</b></center></td>
        </tr>
        </thead>
        <c:forEach items="${jokes}" var="joke">
            <tr>
                <jsp:useBean id="beanNow" class="java.util.Date" />
                <fmt:formatDate value="${joke.date}" var="s_now" pattern="dd.MM.YYYY" />
                <td><center><b>+${joke.likes}</b></center></td>
                <td><center>-${joke.dislikes}</center></td>
                <td>${joke.text}
                    <br/><br/>
                    <p style="font-size:14px; font-style:italic;">author:<b>${joke.user.login}</b>   added:${s_now}</p>
                </td>
                <td>
                    <a title="Cool!" href="../like?jokeId=${joke.id}&login=<%=userName%>&page=${page}">
                        <img src="http://www.clker.com/cliparts/2/5/4/b/12456961341644183975Anselmus_Green_Checkmark_and_Red_Minus.svg.thumb.png" width="30">
                    </a>
                </td>
                <td>
                    <a title="Crap" href="../dislike?jokeId=${joke.id}&login=<%=userName%>&page=${page}">
                        <img src="http://www.clker.com/cliparts/7/e/a/2/12456961561981398760Anselmus_Green_Checkmark_and_Red_Minus_1.svg.thumb.png" width="30">
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <ul class="pagination">
            <c:forEach begin="1" end="${pages}" var="i">
                <c:choose>
                    <c:when test="${page == i}">
                        <li class="active"><a href="../index?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="../index?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
