package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;

public class PostsAssertionTest  {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "20");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size", equalTo(20));
    }
    //2. Verify the if the title of id = 2730 is equal to Ad ipsa coruscus ipsam eos demitto centum.
    @Test
    public void test002() {
      response.body("[1].id",hasItem(hasEntry("title","Rerum omnis sursum damno terror.")));
    }
    //3. Check the single user_id in the Array list 4040713
    @Test
    public void test003() {
        response.body("user_id",hasItem(4123666));
    }
    //Check the multiple ids in the ArrayList (56978,56973,56969)
    @Test
    public void test004() {
       response.body("id",hasItems( 57453,57452,57451));
    }



}
