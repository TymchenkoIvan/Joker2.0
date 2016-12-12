<%@ include file="jspf/directive/page.jspf"%>

<html>
    <util:head title="Error page"/>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <div id="wrapper" style="width:100%; text-align:center">
                    <img src="<c:url value="/resources/web/error.jpg" />">
                </div>
            </div>
        </div>
    </body>
</html>