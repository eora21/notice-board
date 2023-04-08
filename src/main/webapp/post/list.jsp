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
    <title>post</title>
</head>
<body>
<h1>게시물 목록</h1>
<c:forEach var="post" items="${requestScope.get('posts')}">
    <h3>제목: <a href="/post/view.do?id=${post.id}">${post.title}</a></h3>
    <p>작성자: ${post.writerUserId}</p>
    <p>작성 시각: ${post.writeTime}</p>
    <p>조회수: ${post.viewCount}</p>
</c:forEach>
<button onclick="location.href='/post/write.jsp'">글쓰기</button>
</body>
</html>
