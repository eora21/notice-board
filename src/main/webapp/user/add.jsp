<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/08
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="user" value="${requestScope.get('user')}"/>
<c:choose>
    <c:when test="${empty user}">
        <c:set var="to" value="/user/add.do"/>
        <c:set var="submit" value="추가"/>
        <c:set var="readonly" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="to" value="/user/update.do"/>
        <c:set var="submit" value="수정"/>
        <c:set var="readonly" value="readonly"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <title>add</title>
</head>
<body>
<form method="post" action="${to}">
    <label for="id">id: </label><input name="id" id="id" value="${user.id}" ${readonly} required><br>
    <label for="pw">pw: </label><input type="password" name="pw" id="pw" value="${user.password}" required><br>
    <label for="name">name: </label><input name="name" id="name" value="${user.name}" required><br>
    <button type="submit">${submit}</button>
</form>
</body>
</html>
