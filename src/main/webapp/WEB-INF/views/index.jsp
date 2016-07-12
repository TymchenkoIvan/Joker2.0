<%@ include file="jspf/directive/page.jspf"%>

<html>
<head>
    <title>Jokes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%@ include file="jspf/header.jspf"%>

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
                <util:like jokeId="${joke.id}"/>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
