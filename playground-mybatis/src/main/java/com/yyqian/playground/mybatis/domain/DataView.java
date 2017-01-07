package com.yyqian.playground.mybatis.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017-01-05T16:03:16+08:00.
 *
 * @author Yinyin Qian
 */
public class DataView {

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
    @NotEmpty
    private String sqlQuery;
    private List<String> keyKeyMaps = new ArrayList<>();
    private List<Transformer> transformers = new ArrayList<>();

    public DataView() {
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

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public List<String> getKeyKeyMaps() {
        return keyKeyMaps;
    }

    public void setKeyKeyMaps(List<String> keyKeyMaps) {
        this.keyKeyMaps = keyKeyMaps;
    }

    public List<Transformer> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<Transformer> transformers) {
        this.transformers = transformers;
    }
}
