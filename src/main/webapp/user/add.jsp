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
    <title>add</title>
</head>
<body>
<form method="post" action="/user/add.do">
    <label for="id">id: </label><input name="id" id="id"><br>
    <label for="pw">pw: </label><input type="password" name="pw" id="pw"><br>
    <label for="name">name: </label><input name="name" id="name"><br>
    <button type="submit">추가</button>
</form>
</body>
</html>
