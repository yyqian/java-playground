package com.yyqian.playground.mock.service.impl;

import com.yyqian.playground.mock.service.PermissionService;

import org.springframework.stereotype.Service;

/**
 * Created on 2016-12-23T18:16:59+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public boolean hasPermission(int userId) {
        return true;
    }
}
