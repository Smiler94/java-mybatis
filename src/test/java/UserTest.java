import com.mytest.domains.Group;
import com.mytest.domains.Post;
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
        long id = 1;
        User user = sqlSession.selectOne("test.findUserById", id);
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

    @Test
    public void getUserTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        long id = 1;
        User user = sqlSession.selectOne("test.getUser", id);

        System.out.println(user);
        List<Post> posts = user.getPosts();
        for(Post p : posts) {
            System.out.println("Title :" + p.getTitle());
            System.out.println("Content :" + p.getContent());
        }
        List<Group> groups = user.getGroups();
        for(Group g : groups) {
            System.out.println("Id : "+g.getId());
            System.out.println("Name : " + g.getName());
        }
        sqlSession.close();
    }

    @Test
    public void getUserListTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        User user = new User();
        user.setName("zhan");
        user.setAge(12);

        List<User> users = sqlSession.selectList("test.getUserList", user);

        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void getUserListNameNullTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        User user = new User();
        user.setName(null); // 名字为 null 时，会导致 SQL 错误
        user.setAge(12);

//        List<User> users = sqlSession.selectList("test.getUserList", user);
        // 使用 where 标签解决这个问题，可以自动略去开头的 AND 或者 OR
        List<User> users = sqlSession.selectList("test.getUserListByWhere", user);
        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void updateUserTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        long id = 1;
        User user = sqlSession.selectOne("test.findUserById", id);

        user.setSex(1);
        int res = sqlSession.update("test.updateUser", user);
        System.out.println(res);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getChooseTest() throws IOException
    {
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();

        User user = new User();
        user.setName("to");
        user.setSex(2);
        List<User> users = sqlSession.selectList("test.getUserChoose", user);

        System.out.println(users);
        sqlSession.close();
    }
}
