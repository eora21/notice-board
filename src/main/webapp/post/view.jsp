<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/08
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>post/view</title>
</head>
<body>
<c:set var="user" value="${requestScope.get('post')}"/>
<h1>${user.title}</h1>
<p>${user.content}</p>
<button onclick="location.href='/post/update.do?id=${user.id}'">수정</button>
<form method="post" action="/post/delete.do">
    <input type="hidden" name="id" value="${user.id}">
    <button type="submit">삭제</button>
</form>
<a href="/post/list.do">목록으로 돌아가기</a>
</body>
</html>
