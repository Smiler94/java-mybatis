package com.mytest.servlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Site extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        com.mytest.mappers.Site siteMapper = sqlSession.getMapper(com.mytest.mappers.Site.class);
        com.mytest.domains.Site site = new com.mytest.domains.Site();
        site.setName(request.getParameter("name"));
        site.setUrl(request.getParameter("url"));

        int id = siteMapper.insertSite(site);

        PrintWriter out = response.getWriter();
        out.println("新增 id：" + id);
    }
}
