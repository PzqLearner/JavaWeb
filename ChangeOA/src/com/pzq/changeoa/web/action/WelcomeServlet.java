package com.pzq.changeoa.web.action;

import com.pzq.changeoa.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username=null;
        String password=null;
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }
        /**
         * 1.取出cookie中的values
         * 2.验证密码是否匹配，匹配则转移到欢迎页面
         * 3.不匹配则转发到index.jsp
         */

        if (username!=null&&password!=null){
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            boolean flag=false;
            try {
                conn= DBUtils.getConnection();
                String sql = "select username,password from t_user where username=? and password=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                rs= ps.executeQuery();
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
            }else {
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }


    }
}
