package com.yyqian.playground.mybatis.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created on 2017-01-09T13:51:25+08:00.
 *
 * @author Yinyin Qian
 */
public class DatabaseSource {
    @NotEmpty
    private String dbType;
    @NotEmpty
    private String host;
    private int port;
    @NotEmpty
    private String space;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public DatabaseSource() {
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DatabaseSource{" +
                "dbType='" + dbType + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", space='" + space + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
