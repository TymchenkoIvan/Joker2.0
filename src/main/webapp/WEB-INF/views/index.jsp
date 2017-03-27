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
                    <c:forEach items="${jokes}" var="joke">
                        <tr>
                            <td>
                                <div class="joke">
                                    <p class="vote">likes: ${joke.likes} | dislikes: ${joke.dislikes}</p>
                                    <util:like jokeId="${joke.id}"/>
                                    <p class="text">${joke.text}</p>
                                    <jsp:useBean id="beanNow" class="java.util.Date" />
                                    <fmt:formatDate value="${joke.date}" var="joke_date" pattern="dd.MM.YYYY" />
                                    <p class="date">${joke_date}, added by ${joke.userLogin}</p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div align="center">
                    <ul class="pagination">
                        <c:forEach begin="1" end="7" var="i">
                            <c:choose>
                                <c:when test="4">
                                    <li class="active"><a href="../paging?page=${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="../paging?page=${i}&sort=${sort}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>