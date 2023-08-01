package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
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
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size", equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        response.body("[0].id", hasItem("Bhasvan Kapoor"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Bhima Chaturvedi"));
    }

    //4. Check the multiple ‘Names’
    @Test
    public void test004() {
        response.body("name", hasItems("Saraswati Dhawan", "Bhima Chaturvedi", "Tushar Ahluwalia", "Dhanesh Arora PhD"));

    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("email", hasItem("bhasvan_kapoor@adams.test"));
    }
    //6. Verify the status is “Active” of  name is “Shanti Bhat V”
    @Test
    public void test006() {
    response.body("findAll{it.name == 'Bhasvan Kapoor'}.status",hasItem(equalTo("active")));
    }
    //7. Verify the Gender = male of username is “Niro Prajapat”
    @Test
    public void test007() {
    response.body("findAll{it.name =='Gagan Roa'}.gender",hasItem(equalTo("male")));


    }






}
