import com.mytest.domains.Site;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SiteTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException
    {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findSiteByIdTest() throws IOException
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        com.mytest.mappers.Site siteMapper = sqlSession.getMapper(com.mytest.mappers.Site.class);
        Site site = siteMapper.getSiteById(7);

        System.out.println(site);

        sqlSession.close();
    }

    @Test
    public void insertSiteTest() throws IOException
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        com.mytest.mappers.Site siteMapper = sqlSession.getMapper(com.mytest.mappers.Site.class);
        Site site = new Site();
        site.setName("百度");
        site.setUrl("www.baidu.com");
        int id = siteMapper.insertSite(site);

        System.out.println("新增 id：" + id);
        sqlSession.close();
    }
}
