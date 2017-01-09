package com.yyqian.playground.rest.entity;

import java.sql.Timestamp;

/**
 * Created on 2017-01-09T21:32:46+08:00.
 *
 * @author Yinyin Qian
 */
public class Post {
    private int id;
    private String title;
    private String content;
    private Timestamp createdAt;

    public Post() {
    }

    public Post(int id, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
