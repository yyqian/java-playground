package com.yyqian.playground.mock.controller;

import com.yyqian.playground.mock.domain.User;
import com.yyqian.playground.mock.exception.NotFoundException;
import com.yyqian.playground.mock.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2016-12-23T14:36:01+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/{id}",
            method = RequestMethod.GET)
    public User get(@PathVariable int id) {
        LOGGER.info("ID=" + id);
        User user = userService.findUserById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }
}
