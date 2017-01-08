package com.yyqian.msa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * Created on 2017-01-08T20:26:22+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class MsaTestController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/api/uuid", method = RequestMethod.GET)
    public String test2() {
        return restTemplate.getForObject("http://localhost:8080/api/uuid", String.class);
    }

}
