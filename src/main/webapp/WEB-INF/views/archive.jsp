<%@ include file="jspf/directive/page.jspf"%>

<html>
    <util:head title="Archive jokes"/>
    <body>
        <div class="container">
            <%@ include file="jspf/leftPanel.jspf"%>
            <div class="main">
                <util:errorMessage errorMessage="${errorMessage}"/>
                <h2>Archive</h2>
                <table>
                    <tbody>
                        <c:forEach items="${archiveJokes}" var="joke">
                            <tr>
                                <td>
                                    <div class="joke">
                                        <p class="vote">likes: ${joke.likes} | dislikes: ${joke.dislikes}</p>
                                        <util:recover jokeId="${joke.id}"/>
                                        <p class="text">${joke.text}</p>
                                        <jsp:useBean id="beanNow" class="java.util.Date" />
                                        <fmt:formatDate value="${joke.date}" var="joke_date" pattern="dd.MM.YYYY" />
                                        <p class="date">${joke_date}, added by ${joke.userLogin}</p>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
