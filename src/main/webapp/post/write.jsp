<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/08
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>write</title>
</head>
<body>
<c:set var="user" value="${requestScope.get('post')}"/>
<c:choose>
    <c:when test="${empty user}">
        <c:set var="to" value="/post/write.do"/>
        <c:set var="submit" value="작성"/>
    </c:when>
    <c:otherwise>
        <c:set var="to" value="/post/update.do?id=${param.get('id')}"/>
        <c:set var="submit" value="수정"/>
    </c:otherwise>
</c:choose>
<form method="post" action="${to}">
    <label for="title">title: </label><input name="title" id="title" value="${user.title}"><br>
    <label for="content">content: </label><textarea name="content" id="content">${user.content}</textarea><br>
    <button type="submit">${submit}</button>
</form>
</body>
</html>