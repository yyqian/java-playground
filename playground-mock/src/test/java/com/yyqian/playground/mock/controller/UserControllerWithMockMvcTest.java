package com.yyqian.playground.mock.controller;

import com.yyqian.playground.mock.domain.User;
import com.yyqian.playground.mock.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 2016-12-23T14:42:54+08:00.
 *
 * WARNING: DON'T DO CONTROLLER UNIT TEST IN THIS WAY!!!
 *
 * Please Read this:
 *
 * https://github.com/spring-projects/spring-boot/issues/7321
 *
 * Spring Boot's error handling is based on Servlet container error mappings that result in an ERROR
 * dispatch to an ErrorController. MockMvc however is container-less testing so with no Servlet
 * container the exception simply bubbles up with nothing to stop it.
 *
 * So MockMvc tests simply aren't enough to test error responses generated through Spring Boot.
 * I would argue that you shouldn't be testing Spring Boot's error handling. If you're customizing it
 * in any way you can write Spring Boot integration tests (with an actual container) to verify error
 * responses. And then for MockMvc tests focus on fully testing the web layer while expecting
 * exceptions to bubble up.
 *
 * This is a typical unit vs integration tests trade off. You do unit tests even if they don't test
 * everything because they give you more control and run faster.
 *
 * @author Yinyin Qian
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerWithMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getPos() throws Exception {
        User mockUser = new User(9527, "User9527");
        BDDMockito.given(userService.findUserById(mockUser.getId())).willReturn(mockUser);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + mockUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(mockUser.getId()))
                .andExpect(jsonPath("$.name").value(mockUser.getName()));
    }

    @Test
    public void getNeg() throws Exception {
        // MockMVC cannot test the error Response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/-23"))
                .andExpect(status().isNotFound());
    }

}