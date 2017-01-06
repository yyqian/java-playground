package com.yyqian.playground.mybatis.util;

import com.yyqian.playground.mybatis.domain.DataView;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Created on 2017-01-06T13:17:11+08:00.
 *
 * @author Yinyin Qian
 */
public class DataSourceUtil {

    private static final Map<String, String> driverList = new HashMap<>();
    private static final Map<String, String> urlFormatList = new HashMap<>();

    static {
        driverList.put("mysql", "com.mysql.jdbc.Driver");
        driverList.put("postgresql", "org.postgresql.Driver");
        driverList.put("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        driverList.put("oracle", "oracle.jdbc.OracleDriver");
        urlFormatList.put("mysql", "jdbc:mysql://%s:%d/%s");
        urlFormatList.put("postgresql", "jdbc:postgresql://%s:%d/%s");
        urlFormatList.put("sqlserver", "jdbc:sqlserver://%s:%d;DatabaseName=%s");
        urlFormatList.put("oracle", "jdbc:oracle:thin:@%s:%d:%s");
    }

    public static String getDriver(String dbType) {
        String driverName = driverList.get(dbType);
        if (driverName == null) {
            throw new IllegalArgumentException("Not supported Database");
        }
        return driverName;
    }

    public static String getUrl(String dbType, String hostname, int port, String space) {
        String urlFormat = urlFormatList.get(dbType);
        if (urlFormat == null) {
            throw new IllegalArgumentException("Not supported Database");
        }
        return String.format(urlFormat, hostname, port, space);
    }

    // for oracle, space would be SID, otherwise it would be databaseName
    public static DataSource getDataSource(String dbType, String hostname, int port, String space, String username, String password) {
        String driver = getDriver(dbType);
        String url = getUrl(dbType, hostname, port, space);
        return new UnpooledDataSource(driver, url, username, password);
    }

    public static DataSource getDataSource(DataView dataView) {
        return getDataSource(dataView.getDbType(), dataView.getHost(), dataView.getPort(), dataView.getSpace(), dataView.getUsername(), dataView.getPassword());
    }
}
