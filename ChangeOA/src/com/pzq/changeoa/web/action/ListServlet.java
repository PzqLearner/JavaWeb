package com.pzq.changeoa.web.action;

import com.pzq.changeoa.bean.Temp;
import com.pzq.changeoa.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/dept/list","/dept/detail","/dept/edit","/dept/delete","/dept/add"})
public class ListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)){
            doList(request,response);
        }else if ("/dept/detail".equals(servletPath)){
            doDetail(request,response);
        }else if ("/dept/edit".equals(servletPath)){
            doEdit(request,response);
        }else if ("/dept/delete".equals(servletPath)){
            doDeleted(request,response);
        }else if ("/dept/add".equals(servletPath)){
            doAdd(request,response);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            con=DBUtils.getConnection();
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con,ps,null);
        }
        if (count==1) {
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doDeleted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            con=DBUtils.getConnection();
            String sql = "delete from dept where deptno=?";
            ps= con.prepareStatement(sql);
            ps.setString(1,deptno);
            count= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con,ps,null);
        }
        //重定向
        if (count==1) {
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            con=DBUtils.getConnection();
            String sql = "update dept set dname= ?,loc= ? where deptno= ?";
            ps= con.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con,ps,null);
        }
        if (count==1){
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        var temp=new Temp();
        String deptno = request.getParameter("deptno");
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=DBUtils.getConnection();
            String sql = "select dname,loc from dept where deptno=?";
            ps= con.prepareStatement(sql);
            ps.setString(1,deptno);
            rs=ps.executeQuery();
            if (rs.next()) {
                String dname= rs.getString("dname");
                String loc= rs.getString("loc");
                temp.setDeptno(deptno);
                temp.setName(dname);
                temp.setLocation(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con,ps,rs);
        }
        request.setAttribute("temp",temp);

        String ff = request.getParameter("ff");
        if ("t".equals(ff)){
            request.getRequestDispatcher("/edit.jsp").forward(request,response);
        }else if ("f".equals(ff)){
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Temp> temps=new ArrayList<>();
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con= DBUtils.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                String deptno= rs.getString("deptno");
                String name= rs.getString("dname");
                String loc=rs.getString("loc");

                Temp temp = new Temp();
                temp.setDeptno(deptno);
                temp.setName(name);
                temp.setLocation(loc);
                temps.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con,ps,rs);
        }
        request.setAttribute("tempList",temps);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
