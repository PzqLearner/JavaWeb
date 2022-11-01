package com.pzq.changeoa.web.action;

import com.pzq.changeoa.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login","/user/exit"})
public class UesrServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request,response);
        }else if ("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if (session!=null) {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag=false;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DBUtils.getConnection();
            String sql = "select * from t_user where username=? and password=?";
            ps= conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if (rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
        }
        if (flag){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
