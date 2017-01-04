package com.yyqian.playground.mock.service.impl;

import com.yyqian.playground.mock.service.PermissionService;
import com.yyqian.playground.mock.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created on 2016-12-23T17:38:00+08:00.
 *
 * @author Yinyin Qian
 */
public class UserServiceImplTest {

    @Mock
    private PermissionService permissionService;

    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(permissionService);
    }

    @Test
    public void findUserByIdPos() throws Exception {
        BDDMockito.given(permissionService.hasPermission(110)).willReturn(true);
        BDDMockito.given(permissionService.hasPermission(9527)).willReturn(true);
        assertNotNull(userService.findUserById(110));
        assertNotNull(userService.findUserById(9527));
    }

    @Test
    public void findUserByIdNeg() throws Exception {
        BDDMockito.given(permissionService.hasPermission(220)).willReturn(false);
        assertNull(userService.findUserById(220));
        assertNull(userService.findUserById(1290));
        assertNull(userService.findUserById(-1));
    }
}