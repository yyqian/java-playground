package com.yyqian.playground.mybatis.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 2017-01-06T13:25:20+08:00.
 *
 * @author Yinyin Qian
 */
public class DataSourceUtilTest {
    @Test
    public void getDriver() throws Exception {
        assertEquals("com.mysql.jdbc.Driver", DataSourceUtil.getDriver("mysql"));
        assertEquals("org.postgresql.Driver", DataSourceUtil.getDriver("postgresql"));
        assertEquals("com.microsoft.sqlserver.jdbc.SQLServerDriver", DataSourceUtil.getDriver("sqlserver"));
        assertEquals("oracle.jdbc.OracleDriver", DataSourceUtil.getDriver("oracle"));
    }

}