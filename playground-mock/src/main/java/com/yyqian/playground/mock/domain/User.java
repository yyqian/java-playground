package com.yyqian.playground.mock.domain;

/**
 * Created on 2016-12-23T14:33:38+08:00.
 *
 * @author Yinyin Qian
 */
public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
