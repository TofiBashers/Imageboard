<%--
  Created by IntelliJ IDEA.
  User: TofixXx
  Date: 27.08.2014
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="<c:url value="/appResources/stylesheets/index.css" />" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="boardsDiv">
    <table class="boardsTable">
        <c:forEach var="board" items="${boards}">
            <tr>
                <td>
                    <a href="/Imageboard/${board.name}">/${board.name}</a><br/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
