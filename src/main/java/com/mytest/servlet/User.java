package com.mytest.servlet;

import com.mytest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class User extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        UserService userService = new UserService();
        long id = (long)Long.valueOf(request.getParameter("id"));
        System.out.println("id = " + id);
        com.mytest.domains.User user = userService.getUserInfo(id);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println("用户名：" + user.getName() + "\n" +
        "用户年龄：" + user.getAge());
    }
}
