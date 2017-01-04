package com.yyqian.playground.mock.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created on 2016-12-23T16:15:43+08:00.
 *
 * We should not unit test the @Controller, just do a integration test!
 *
 * @author Yinyin Qian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost"; // default
        RestAssured.port = port;
    }

    @Test
    public void getPos() throws Exception {
        when().get("/api/user/23")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(23))
                .body("name", Matchers.equalTo("User23"));
    }

    @Test
    public void getNeg() throws Exception {
        String path =  "/api/user/-23";
        Response response = when().get(path)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .body("status", Matchers.equalTo(404))
                .body("error", Matchers.equalTo("Not Found"))
                .body("exception", Matchers.notNullValue())
                .body("message", Matchers.equalTo("Not Found"))
                .body("path", Matchers.equalTo(path))
                .extract()
                .response();
        System.out.println(response.asString());
    }
}
