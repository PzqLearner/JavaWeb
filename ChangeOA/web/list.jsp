<%@ page import="java.util.List" %>
<%@ page import="com.pzq.changeoa.bean.Temp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>list</title>
</head>
<script type="text/javascript">
    function del(dno){
        if(window.confirm("您好，删除无法恢复!!!")){
            document.location.href="<%=request.getContextPath()%>/dept/delete?deptno="+dno;
        }
    }
</script>
<body>
<h1><a>欢迎<%=session.getAttribute("username")%>!!!</a></h1>
<h3><a href="<%=request.getContextPath()%>/user/exit">[安全退出]</a></h3>
    <table border="1px" align="center" width="50%">
        <tr>
            <th>序号</th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>操作</th>
        </tr>
        <%
            List<Temp> tempList=(List<Temp>)request.getAttribute("tempList");
            int i=0;
            for (Temp temp:tempList){
        %>
        <tr>
            <td><%=++i%></td>
            <td><%=temp.getDeptno()%></td>
            <td><%=temp.getName()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=temp.getDeptno()%>)">删除</a>
                <a href="<%=request.getContextPath()%>/dept/detail?ff=t&deptno=<%=temp.getDeptno()%>">修改</a>
                <a href="<%=request.getContextPath()%>/dept/detail?ff=f&deptno=<%=temp.getDeptno()%>">详情</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <hr>
    <a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>
</html>