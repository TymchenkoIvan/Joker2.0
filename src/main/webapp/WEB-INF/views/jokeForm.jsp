<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>New joke</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <style>
        .error {
            color: red; font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">

    <%@ include file="jspf/header.jspf"%>

    <tr> <center><h3>New joke:</h3></center></tr>
    <form:form role="form" class="form-horizontal" action="jokeForm" commandName="jokeForm">
            <div class="form-group">
            <form:errors path="text" cssClass="error"/>
            <form:textarea path="text" class="form-control" rows="15" cols="50"/>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="new joke">
        </div>
    </form:form>
</div>
</body>
</html>
