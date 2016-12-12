<%@ include file="jspf/directive/page.jspf"%>

<html>
    <util:head title="Jokes"/>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <util:errorMessage errorMessage="${errorMessage}"/>
                <h2>Jokes</h2>
                <table>
                    <tbody>
                    <c:forEach items="${jokes}" var="joke">
                        <tr>
                            <td>
                                <div class="joke">
                                    <p class="text">${joke.text}</p>
                                    <p class="vote">likes: ${joke.likes} | dislikes: ${joke.dislikes}</p>
                                    <util:like jokeId="${joke.id}"/>
                                    <jsp:useBean id="beanNow" class="java.util.Date" />
                                    <fmt:formatDate value="${joke.date}" var="joke_date" pattern="dd.MM.YYYY" />
                                    <p class="date">${joke_date}</p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>