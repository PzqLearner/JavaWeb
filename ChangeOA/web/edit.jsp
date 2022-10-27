<%@ page import="com.pzq.changeoa.bean.Temp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>edit</title>
</head>
<body>
    <h1>修改部门</h1>
    <hr>
    <%
        Temp temp = (Temp) request.getAttribute("temp");
    %>

    <form action="<%=request.getContextPath()%>/dept/edit" method="get">
        部门编号<input type="text" name="deptno" value="<%=temp.getDeptno()%>" readonly><br>
        部门名称<input type="text" name="dname" value="<%=temp.getName()%>"><br>
        部门位置<input type="text" name="loc" value="<%=temp.getLocation()%>"><br>
        <input type="submit" value="修改"> <br>
    </form>
</body>
</html>