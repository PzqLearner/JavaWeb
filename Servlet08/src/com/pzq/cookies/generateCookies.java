package com.pzq.cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/generate")
public class generateCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("pzqFirst","123456");
        Cookie cookie2 = new Cookie("pzqSecond","654321");

        cookie.setMaxAge(-1);
        cookie2.setMaxAge(-2);

        /**
         * cookie默认路径为项目名+下一级路径名
         */
        cookie.setPath(request.getContextPath());
        cookie2.setPath(request.getContextPath());

        response.addCookie(cookie);
        response.addCookie(cookie2);
    }
}
