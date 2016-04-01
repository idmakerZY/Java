package test;

import com.proj.dao.IUserOperation;
import com.proj.entity.Article;
import com.proj.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SpringMybatisTest {

    private static ApplicationContext applicationContext;

    static{
        applicationContext=new ClassPathXmlApplicationContext("config/applicationContext.xml");
    }

    public static void main(String[] args){

        IUserOperation operation=(IUserOperation)applicationContext.getBean("userMapper");

        User user=operation.selectUserByID(1);
        System.out.println(user.getUserName()+","+user.getUserAge()+","+user.getUserAddress());
        System.out.println("得到id为1的作者的文章列表：");
        List<Article> articles=operation.getUserArticles(1);
        for(Article article:articles)
        {
            System.out.println(article.getTitle()+":"+article.getContent());
        }
    }
}
