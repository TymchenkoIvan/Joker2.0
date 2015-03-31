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

    <!-- в связи с тем, что в проекте нет Spring Security роходится обходиться данным котылем
        Начало костыля:-->
    <c:if test="${isLogin eq 'new'}">
        <c:set var="login" scope="application" value="${userLogin}"/>
    </c:if>
    <!-- Конец костыля:-->

    <table border="0" width="100%">
        <tr>
            <td valign="top" align="center">
                <form class="form-inline" role="form" action="/com_company/index" method="post">
                    <input type="submit" class="btn btn-primary" value="Main">
                </form>
                <form class="form-inline" role="form" action="/com_company/add_page" method="post">
                    <input type="submit" class="btn btn-default" value="Add joke">
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

    <p align="center" style="color:rgba(134, 3, 1, 0.73); font-size:15px">${error}</p>

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
                <td><a href="/com_company/like?jokeId=${joke.id}&login=${login}"><b>Like!</b></a></td>
                <td><a href="/com_company/dislike?jokeId=${joke.id}&login=${login}"><b>Dislike</b></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
