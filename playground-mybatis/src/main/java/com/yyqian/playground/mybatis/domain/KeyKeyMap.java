package com.yyqian.playground.mybatis.domain;

/**
 * Created on 2017-01-06T15:39:31+08:00.
 *
 * @author Yinyin Qian
 */
public class KeyKeyMap {
    private final String from;
    private final String to;

    public KeyKeyMap(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
