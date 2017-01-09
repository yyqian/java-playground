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

    private DatabaseSource databaseSource;
    @NotEmpty
    private String sqlQuery;
    private List<Transformer> transformers = new ArrayList<>();

    public DataView() {
    }

    public DatabaseSource getDatabaseSource() {
        return databaseSource;
    }

    public void setDatabaseSource(DatabaseSource databaseSource) {
        this.databaseSource = databaseSource;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public List<Transformer> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<Transformer> transformers) {
        this.transformers = transformers;
    }
}
