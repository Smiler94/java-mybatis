import com.mytest.domains.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
}
