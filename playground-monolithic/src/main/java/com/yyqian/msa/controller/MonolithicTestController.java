package com.yyqian.msa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created on 2017-01-08T20:59:05+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class MonolithicTestController {
    @RequestMapping(value = "/api/uuid", method = RequestMethod.GET)
    public String test() {
        return UUID.randomUUID().toString();
    }
}
