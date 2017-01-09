package com.yyqian.playground.mybatis.controller;

import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.domain.DatabaseSource;
import com.yyqian.playground.mybatis.service.FetchService;
import com.yyqian.playground.mybatis.service.SourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * Created on 2017-01-06T14:13:57+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class ApiController {

    private final FetchService fetchService;
    private final SourceService sourceService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    public ApiController(FetchService fetchService, SourceService sourceService) {
        this.fetchService = fetchService;
        this.sourceService = sourceService;
    }

    @RequestMapping(value = "/api/dataview", method = RequestMethod.POST)
    public List<Map<String, Object>> getData(@Valid @RequestBody DataView dataView) {
        LOGGER.info(dataView.toString());
        dataView.getTransformers().forEach(transformer -> LOGGER.info(transformer.toString()));
        return fetchService.fetchAll(dataView);
    }

    @RequestMapping(value = "/api/datasource", method = RequestMethod.POST)
    public void setDatabaseSource(@Valid @RequestBody DatabaseSource databaseSource) {
        LOGGER.info(databaseSource.toString());
        sourceService.saveDatabaseSource(databaseSource);
    }

    @RequestMapping(value = "/api/datasource", method = RequestMethod.GET)
    public DatabaseSource getDatabaseSource() {
        LOGGER.info("getDatabaseSource");
        return sourceService.getDatebaseSource();
    }

}
