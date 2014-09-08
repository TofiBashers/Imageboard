<%--
  Created by IntelliJ IDEA.
  User: TofixXx
  Date: 27.08.2014
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="<c:url value="/appResources/stylesheets/board.css" />" rel="stylesheet">
    <title>
        <jsp:include page="head.jsp">
            <jsp:param name="head" value="${boardId}" />
        </jsp:include>
    </title>
</head>
<body>
<c:forEach var="thread" items="${threadsAndMessages}">
    <%-- пока просто проход по списку сообщений каждого треда --%>
    <br/>
    <%--сделать отдельный класс для шапки треда!--%>
    <div class="threadHead">
        <img src="<s:url value="/appResources/images/${thread[0].id}"/>">
        <c:out value="${thread[0].text}" />
    </div>
    <c:forEach varStatus="i" begin="1" items="${thread}">
        <div class = messageBlock>
            <img src="<s:url value="/appResources/images/${thread[i.index].id}"/>">
            <c:out value="${thread[i.index].text}" />
        </div>
    </c:forEach>
    <hr>
</c:forEach>
<div id="form">
<sf:form method="POST" enctype="multipart/form-data">
    <table>
        <tr>
            <th>Введите текст сообщения:</th>
            <td>
                <input name="text" type="text" />
            </td>
        </tr>

        <tr>
            <th>Вставьте каритнку:</th>
            <td>
                <input name="image" type="file" />
            </td>
        </tr>
        <tr>
            <td>
                <input name="submit" type="submit"/>
            </td>
        </tr>
    </table>
</sf:form>
</div>
</body>
</html>
