package com.yyqian.playground.mybatis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.domain.DatabaseSource;
import com.yyqian.playground.mybatis.domain.WebserviceSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created on 2017-01-06T14:37:09+08:00.
 *
 * @author Yinyin Qian
 */
public class ExtractServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractServiceTest.class);
    private static final String HOST = "192.168.6.231";

    @Test
    public void fetchAll() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SourceService sourceService = new SourceService(new StringRedisTemplate(), objectMapper);
        ExtractService extractService = new ExtractService(new TransformService(new FilterService()), sourceService, objectMapper);
        List<Map<String, Object>> results = extractService.fetchAll(getWebserviceView());
        assertNotNull(results);
        results.forEach(kvMap -> {
            String log = kvMap.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining(", ", "{", "}"));
            LOGGER.info(log);
        });
    }

    private static DataView getOracleDataView() {
        DataView dataView = new DataView();
        DatabaseSource source = new DatabaseSource();
        source.setDbType("oracle");
        source.setHost(HOST);
        source.setPort(49161);
        source.setSpace("xe");
        source.setUsername("system");
        source.setPassword("oracle");
        dataView.setDatabaseSource(source);
        dataView.setSqlQuery("select * from HR.JOBS");
        return dataView;
    }

    private static DataView getPgSQLDataView() {
        DataView dataView = new DataView();
        DatabaseSource source = new DatabaseSource();
        source.setDbType("postgresql");
        source.setHost(HOST);
        source.setPort(5432);
        source.setSpace("postgres");
        source.setUsername("postgres");
        source.setPassword("PatSnap2017");
        dataView.setDatabaseSource(source);
        dataView.setSqlQuery("select * from \"public\".\"post\"");
        return dataView;
    }

    private static DataView getMySQLDataView() {
        DataView dataView = new DataView();
        DatabaseSource source = new DatabaseSource();
        source.setDbType("mysql");
        source.setHost(HOST);
        source.setPort(3306);
        source.setSpace("test");
        source.setUsername("root");
        source.setPassword("PatSnap2017");
        dataView.setDatabaseSource(source);
        dataView.setSqlQuery("select * from user");
        return dataView;
    }

    private static DataView getWebserviceView() {
        DataView dataView = new DataView();
        WebserviceSource source = new WebserviceSource();
        source.setUri("http://localhost:8081/api/data");
        source.setMethod("GET");
        source.setDataFormat("json");
        source.setRequestBody(null);
        dataView.setWebserviceSource(source);
        return dataView;
    }
}