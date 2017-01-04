package com.yyqian.playground.mock.service.impl;

import com.yyqian.playground.mock.domain.User;
import com.yyqian.playground.mock.service.PermissionService;
import com.yyqian.playground.mock.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2016-12-23T14:35:06+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PermissionService permissionService;

    @Autowired
    public UserServiceImpl(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public User findUserById(int id) {
        LOGGER.info("Find user with id: {}", id);
        if (id < 0 || !permissionService.hasPermission(id)) {
            return null;
        }
        return new User(id, "User" + id);
    }
}
