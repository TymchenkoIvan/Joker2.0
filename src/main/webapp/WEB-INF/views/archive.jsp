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

    <%@ include file="jspf/header.jspf"%>

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
                <td><center><a href="./archive/delete?jokeId=${joke.id}&login=<%=userName%>"><b>delete</b></a></center></td>
                <td><center><a href="./archive/recover?jokeId=${joke.id}&login=<%=userName%>"><b>recover</b></a></center></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

