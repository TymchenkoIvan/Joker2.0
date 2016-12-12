<%@ include file="jspf/directive/page.jspf"%>

<html>
    <util:head title="FAQ"/>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <util:errorMessage errorMessage="${errorMessage}"/>
                <h2>F.A.Q</h2>
                <table>
                    <tbody>
                    <tr><td>1. You can add new jokes.</td></tr>
                    <tr><td>2. You can once rate every joke. You need to be authorized.</td></tr>
                    <tr><td>3. You can create new user with unique login and e-mail address.</td></tr>
                    <tr><td>4. Every bad joke with more than 10 votes will be move to archive.</td></tr>
                    <tr><td>5. Admin can delete or recover joke from archive.</td></tr>
                    <tr><td>6. After recovering all votes will be deleted from joke.</td></tr>
                    <tr><td>7. Used technologies: Java, SpringMVC, Maven, Hibernate, JPA, MySQL, JSTL, Tomcat, Bootstrap, HTML&amp;CSS.</td></tr>
                    <tr><td>8. <a href="https://github.com/TymchenkoIvan/Joker2.0">You can find it on GitHub</a>.</td></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>