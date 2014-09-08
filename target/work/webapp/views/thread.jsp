<%--
  Created by IntelliJ IDEA.
  User: TofixXx
  Date: 27.08.2014
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link href="<c:url value="/appResources/stylesheets/thread.css" />" rel="stylesheet">
    <title>
        <jsp:include page="head.jsp">
            <jsp:param name="head" value="${boardId}" />
        </jsp:include>
    </title>
</head>
<body>
<table cellspacing="15px">
<c:forEach var="message" items="${messageList}">
    <tr>
        <td>
            <div class="messageBlock">
                <img src="<s:url value="/appResources/images/${message.id}"/>">
                <c:out value="${message.text}"/>
                <div class="id">
                    <c:out value="${message.id}" />
                </div>
            </div>
        </td>

    </tr>
</c:forEach>
</table>
<br/>
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
</body>
</html>
