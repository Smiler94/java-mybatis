import com.mytest.domains.Site;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        siteMapper.insertSite(site);
        sqlSession.close();
    }

    @Test
    public void getAllSitesTest() throws IOException
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        com.mytest.mappers.Site siteMapper = sqlSession.getMapper(com.mytest.mappers.Site.class);
        List<Site> sites = siteMapper.getAllSites();

        System.out.println(sites);
        sqlSession.close();
    }
}
