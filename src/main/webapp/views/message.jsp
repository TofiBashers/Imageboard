<%--
  Created by IntelliJ IDEA.
  User: TofixXx
  Date: 28.08.2014
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <table>
        <tr>
            <td>
                <c:out value="${requestScope.mess.text}"/>
            </td>
        </tr>
    </table>
    <div class="messageId">
        <c:out value="${requestScope.mess.id}" />
    </div>
</div>

