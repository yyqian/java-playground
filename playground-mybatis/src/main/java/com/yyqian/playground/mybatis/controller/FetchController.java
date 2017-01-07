package com.yyqian.playground.mybatis.controller;

import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.service.FetchService;

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
public class FetchController {

    private final FetchService fetchService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchController.class);

    @Autowired
    public FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @RequestMapping(value = "/api/dataview", method = RequestMethod.POST)
    public List<Map<String, Object>> getData(@Valid @RequestBody DataView dataView) {
        //LOGGER.info(dataView.getTransformers());
        return fetchService.fetchAll(dataView);
    }
}
