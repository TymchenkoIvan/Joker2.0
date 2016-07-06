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

    <%@ include file="jspf/header.jspf"%>

    <tr> <center><h3>New joke:</h3></center></tr>
    <form role="form" class="form-horizontal" action="/joker.com/add/create" method="post">
        <div class="form-group"><textarea class="form-control" rows="15" cols="50" name="text" id="comment"></textarea></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="new joke"></div>
    </form>
</div>
</body>
</html>
