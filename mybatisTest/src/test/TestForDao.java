package test;

import java.io.Reader;
import java.util.List;

import com.proj.entity.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.proj.dao.IUserOperation;
import com.proj.entity.User;
/**
 * Created by Administrator on 2016/3/31.
 */
public class TestForDao {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader    = Resources.getResourceAsReader("config/config_mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getUserList(String userName){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            List<User> users = userOperation.selectUsers(userName);
            for(User user:users){
                System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAddress());
            }

        } finally {
            session.close();
        }
    }

    /**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
    public void addUser(){
        User user=new User();
        user.setUserAddress("ani.sh");
        user.setUserName("user2");
        user.setUserAge("80");
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("当前增加的用户 id为:"+user.getId());
        } finally {
            session.close();
        }
    }

    public void updateUser(){
        //先得到用户,然后修改，提交。
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(3);
            user.setUserAddress("new address");
            userOperation.updateUser(user);
            session.commit();

        } finally {
            session.close();
        }
    }

    /**
     * 删除数据，删除一定要 commit.
     * @param id
     */
    public void deleteUser(int id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            userOperation.deleteUser(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void getUserArticles(int userid){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            List<Article> articles = userOperation.getUserArticles(userid);
            for(Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                        ":作者是:"+article.getUser().getUserName()+":地址:"+
                        article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }

    public static void main(String[] arg) {

        TestForDao testForDao=new TestForDao();
        testForDao.getUserList("%");
        //testForDao.addUser();
        //testForDao.updateUser();
        //System.out.println("更新后的数据：");
        //System.out.println("删除一条数据后的数据表：");
        //testForDao.deleteUser(2);
        //testForDao.getUserList("%");
        testForDao.getUserArticles(1);
    }
}
