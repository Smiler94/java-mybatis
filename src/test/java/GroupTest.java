import com.mytest.domains.Group;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class GroupTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void getGroupTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        long id = 1;

        Group group = sqlSession.selectOne("test.getGroup", id);

        System.out.println(group);

        sqlSession.close();
    }
}
