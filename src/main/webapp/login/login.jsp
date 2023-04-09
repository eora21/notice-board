<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/07
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form method="post" action="/login.do">
        <label for="id">ID:</label><input name="id" id="id" value="admin"><br>
        <label for="pw">PW:</label><input name="pw" id="pw" value="12345"><br>
        <button type="submit">로그인</button>
    </form>
</body>
</html>
