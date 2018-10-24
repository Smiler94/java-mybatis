package com.mytest.service;

import com.mytest.domains.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserService {
    public SqlSessionFactory getSqlSessionFactory() throws IOException
    {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public User getUserInfo(long id) throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        return sqlSession.selectOne("test.findUserById", id);
    }
}
