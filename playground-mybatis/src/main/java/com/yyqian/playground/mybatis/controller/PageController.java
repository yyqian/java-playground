package com.yyqian.playground.mybatis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 2017-01-09T14:18:11+08:00.
 *
 * @author Yinyin Qian
 */
@Controller
public class PageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        LOGGER.info("Fetch index page.");
        return "index";
    }
}
