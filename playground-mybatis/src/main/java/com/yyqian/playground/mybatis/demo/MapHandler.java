package com.yyqian.playground.mybatis.demo;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created on 2017-01-04T16:09:03+08:00.
 *
 * @author Yinyin Qian
 */
public class MapHandler implements ResultHandler<Map<String, Object>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapHandler.class);

    @Override
    public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
        Map<String, Object> row = resultContext.getResultObject();
        LOGGER.info("username={}", row.get("username"));
    }
}
