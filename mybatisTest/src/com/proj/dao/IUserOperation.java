package com.proj.dao;

import com.proj.entity.Article;
import com.proj.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public interface IUserOperation {
    public User selectUserByID(int id);
    public List<User> selectUsers(String userName);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public List<Article> getUserArticles(int id);

}
