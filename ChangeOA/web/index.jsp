<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2022/10/26
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>欢迎使用登录界面</title>
</head>
<body>
<h1>欢迎登录</h1>
<form action="<%=request.getContextPath()%>/user/login" method="post">
  username:<input type="text" name="username"><br>
  password:<input type="text" name="password"><br>
  <input type="submit" value="login">
</form>
</body>
</html>
