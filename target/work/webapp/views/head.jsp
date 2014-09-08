<%--
  Created by IntelliJ IDEA.
  User: TofixXx
  Date: 04.09.2014
  Time: 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="<c:url value="/appResources/stylesheets/thread.css" />" rel="stylesheet">
    <title></title>
</head>
<body>
<div>
    <a href="/Imageboard/"><p align="left"><u>Главная</u></p></a>
</div>
<br/>
<div>
    <p align="center"><h1>/${param.head}</h1></p>
</div>
<br/>
</body>
</html>
