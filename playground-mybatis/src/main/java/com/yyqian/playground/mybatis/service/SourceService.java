package com.yyqian.playground.mybatis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyqian.playground.mybatis.domain.DatabaseSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created on 2017-01-09T14:15:58+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class SourceService {

    private final StringRedisTemplate template;
    private final ObjectMapper objectMapper;
    private static final String SOURCE_KEY = "transformer:datasource";

    @Autowired
    public SourceService(StringRedisTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    public void saveDatabaseSource(DatabaseSource source) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        template.opsForValue().set(SOURCE_KEY, str);
    }

    public DatabaseSource getDatebaseSource() {
        String str = template.opsForValue().get(SOURCE_KEY);
        DatabaseSource databaseSource = null;
        try {
            if (str != null) {
                databaseSource = objectMapper.readValue(str, DatabaseSource.class);
            } else {
                databaseSource = new DatabaseSource();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return databaseSource;
    }
}
