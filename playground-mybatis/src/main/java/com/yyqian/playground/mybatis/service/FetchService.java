package com.yyqian.playground.mybatis.service;

import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.mapper.MapperBuilder;
import com.yyqian.playground.mybatis.mapper.WildMapper;
import com.yyqian.playground.mybatis.util.DataSourceUtil;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Created on 2017-01-06T14:06:49+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class FetchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchService.class);

    public List<Map<String, Object>> fetchAll(DataView dataView) {
        DataSource dataSource = DataSourceUtil.getDataSource(dataView);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("runtimeDataSource", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        MapperBuilder mapperBuilder = null;
        try {
            mapperBuilder = new MapperBuilder();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        mapperBuilder.addStatement(new MapperBuilder.Statement(dataView.getSqlQuery(), "selectAll"));
        Class<?> dynamicMapperClass = null;
        try {
            dynamicMapperClass = mapperBuilder.build();
        } catch (CannotCompileException e) {
            LOGGER.error(e.toString());
            return new ArrayList<>();
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.toString());
            return new ArrayList<>();
        }

        configuration.addMapper(dynamicMapperClass);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        List<Map<String, Object>> results = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            WildMapper mapper = (WildMapper) session.getMapper(dynamicMapperClass);
            results = mapper.selectAll();
        }
        return results == null ? new ArrayList<>() : results;
    }
}
