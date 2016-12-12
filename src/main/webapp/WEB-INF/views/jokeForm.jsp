<%@ include file="jspf/directive/page.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <util:head title="New joke"/>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <util:errorMessage errorMessage="${errorMessage}"/>
                <h2>New joke</h2>
                <form:form id="jokeForm" role="form" class="form-horizontal" action="jokeForm" commandName="jokeForm">
                    <div class="form-group">
                        <form:errors path="text" cssClass="errorSpringMVC"/>
                        <form:textarea id="text" path="text" class="form-control" rows="15" cols="50" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Submit">
                    </div>
                </form:form>
            </div>
        </div>
    </body>
</html>
