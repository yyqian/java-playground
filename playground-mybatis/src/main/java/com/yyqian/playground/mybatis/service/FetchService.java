package com.yyqian.playground.mybatis.service;

import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.domain.DatabaseSource;
import com.yyqian.playground.mybatis.mapper.MapperBuilder;
import com.yyqian.playground.mybatis.mapper.ViewMapper;
import com.yyqian.playground.mybatis.util.DataSourceUtil;

import javassist.CannotCompileException;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final TransformService transformService;
    private final SourceService sourceService;

    @Autowired
    public FetchService(TransformService transformService, SourceService sourceService) {
        this.transformService = transformService;
        this.sourceService = sourceService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchService.class);

    public List<Map<String, Object>> fetchAll(DataView dataView) {
        Class<?> dynamicMapperClass = new MapperBuilder()
                .setSqlQuery(dataView.getSqlQuery())
                .build();
        DatabaseSource databaseSource = dataView.getDatabaseSource() == null ?
                sourceService.getDatebaseSource() : dataView.getDatabaseSource();
        DataSource dataSource = DataSourceUtil.getDataSource(databaseSource);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("runtimeDataSource", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(dynamicMapperClass);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        List<Map<String, Object>> results = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ViewMapper mapper = (ViewMapper) session.getMapper(dynamicMapperClass);
            results = mapper.fetch();
        }
        if (results == null) {
            results = new ArrayList<>();
        }
        transformService.transform(results, dataView.getTransformers());
        return results;
    }
}
