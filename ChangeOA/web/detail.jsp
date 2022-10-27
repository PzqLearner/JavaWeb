<%@ page import="com.pzq.changeoa.bean.Temp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>details</title>
</head>
<body>
    <h1>部门详情</h1>
    <hr>
    <%
        Temp temp = (Temp) request.getAttribute("temp");
    %>
    部门编号: <%=temp.getDeptno()%> <br>
    部门名称: <%=temp.getName()%><br>
    部门位置: <%=temp.getLocation()%><br>

    <input type="button" value="后退" onclick="window.history.back()">
</body>
</html>