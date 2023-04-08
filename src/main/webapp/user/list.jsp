<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/08
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
<h1>유저 목록</h1>
<c:forEach var="user" items="${requestScope.get('users')}">
    <p><a href="/user/view.do?id=${user.id}">${user.name}</a></p>
</c:forEach>
<button onclick="location.href='/user/add.jsp'">유저 추가</button>
</body>
</html>
