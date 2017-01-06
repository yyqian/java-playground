package com.yyqian.playground.mybatis.controller;

import com.yyqian.playground.mybatis.domain.DataView;
import com.yyqian.playground.mybatis.service.FetchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017-01-06T14:13:57+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class FetchController {

    private final FetchService fetchService;

    @Autowired
    public FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @RequestMapping(value = "/api/dataview", method = RequestMethod.POST)
    public List<Map<String, Object>> getData(@RequestBody DataView dataView) {
        return fetchService.fetchAll(dataView);
    }
}
