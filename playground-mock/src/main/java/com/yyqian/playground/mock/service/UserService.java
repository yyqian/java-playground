package com.yyqian.playground.mock.service;

import com.yyqian.playground.mock.domain.User;

/**
 * Created on 2016-12-23T14:34:34+08:00.
 *
 * @author Yinyin Qian
 */
public interface UserService {
    User findUserById(int id);
}
