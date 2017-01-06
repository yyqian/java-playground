package com.yyqian.playground.mybatis.service;

import com.yyqian.playground.mybatis.domain.DataView;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created on 2017-01-06T14:37:09+08:00.
 *
 * @author Yinyin Qian
 */
public class FetchServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchServiceTest.class);

    @Test
    public void fetchAll() throws Exception {
        FetchService fetchService = new FetchService();
        List<Map<String, Object>> results = fetchService.fetchAll(getDataView());
        assertNotNull(results);
        results.forEach(kvMap -> {
            String str = kvMap.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining(", ", "{", "}"));
            LOGGER.info(str);
        });
    }

    private static DataView getDataView() {
        DataView dataView = new DataView();
        dataView.setDbType("oracle");
        dataView.setHost("192.168.6.231");
        dataView.setPort(49161);
        dataView.setSpace("xe");
        dataView.setUsername("system");
        dataView.setPassword("oracle");
        dataView.setSqlQuery("select * from HR.JOBS");
        return dataView;
    }
}