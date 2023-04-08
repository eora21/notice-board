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
<form method="post" action="/post/write.do">
    <label for="title">title: </label><input name="title" id="title"><br>
    <label for="content">content: </label><textarea name="content" id="content"></textarea><br>
    <button type="submit">작성</button>
</form>
</body>
</html>