package com.yyqian.playground.rest.controller;

import com.yyqian.playground.rest.entity.Post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created on 2017-01-09T21:31:09+08:00.
 *
 * @author Yinyin Qian
 */
@RestController
public class ApiController {

    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            posts.add(getRndPost());
        }
        return posts;
    }

    private Post getRndPost() {
        Random r = new Random();
        int num = r.nextInt(100);
        return new Post(num, "some title" + num, "some content" + num, Timestamp.valueOf(LocalDateTime.now()));
    }
}
