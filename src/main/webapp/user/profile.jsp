<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/09
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
<form method="post" action="/user/profile.do?id=${param.get('id')}" enctype="multipart/form-data">
    <label for="profile">name: </label><input type="file" name="profile" id="profile"
                                              accept="image/png, image/jpeg" required><br>
    <button type="submit">업로드</button>
</form>
</body>
</html>
