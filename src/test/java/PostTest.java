import com.mytest.domains.Post;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PostTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void getPostTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        long id = 1;
        Post post = sqlSession.selectOne("test.getPost", id);

        System.out.println(post);
        System.out.println(post.getUser());

        sqlSession.close();
    }
}
