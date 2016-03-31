package com.proj.entity;

/**
 * Created by Administrator on 2016/3/31.
 * Article实体对应article数据表，里面保存的每个记录与一条user数据表里的记录形成多对一的关系，article表里的userid对应user数据表里的id
 *
 * aid	userid	title	      content
 *  1	1	    test_title	  test_content
 *  2	1	    test_title_2  test_content_2
 *  3	1	    test_title_3  test_content_3
 *  4	1	    test_title_4  test_content_4
 */
public class Article {
    private int aid;
    private User user;//Article实体的user是直接定义的一个User对象，而不是int类型。但article数据表里这里对应的是userid，一个int型字段
    private String title;
    private String content;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
