import com.mytest.domains.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void findUserByIdTest() throws IOException {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAge());
        sqlSession.close();
    }
    @Test
    public void insertUserTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        User user = new User();
        user.setName("zhangsan");
        user.setAge(15);
        int i = sqlSession.insert("test.insertUser", user);
        sqlSession.commit();
        System.out.println(i);
    }

    @Test
    public void getAllUserTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        List<User> users = sqlSession.selectList("test.getAllUsers");

        sqlSession.close();
        System.out.println(users);
    }
}
