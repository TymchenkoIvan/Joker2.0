<%@ include file="jspf/directive/page.jspf"%>

<html>
<head>
    <title>Error page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%@ include file="jspf/header.jspf"%>

    <div id="wrapper" style="width:100%; text-align:center">
        <img src="<c:url value="/resources/web/error.jpg" />">
    </div>

</div>
</body>
</html>