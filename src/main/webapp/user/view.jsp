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
    <title>user/view</title>
</head>
<body>
<c:set var="user" value="${requestScope.get('user')}"/>
<h1>${user.name}</h1>
<img src="${user.profileFileName}" alt="프로필"/>
<button onclick="location.href='/user/profile.jsp?id=${user.id}'">프로필 업로드</button>
<button onclick="location.href='/user/update.do?id=${user.id}'">수정</button>
<form method="post" action="/user/delete.do">
    <input type="hidden" name="id" value="${user.id}">
    <button type="submit">삭제</button>
</form>
<a href="/user/list.do">목록으로 돌아가기</a>
</body>
</html>
