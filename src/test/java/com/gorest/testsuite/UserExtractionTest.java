package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

    @Test
    public void test001() {
        //1. Extract the All Ids
        List<Integer> id = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  the All Ids : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        //2. Extract the all Names
        List<String> name = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Names : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003() {
        //3. Extract the name of 5th object
        String theName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object: " + theName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test004() {
        //4. Extract the names of all object whose status = inactive
        List<String> status = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status  inactive are: " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test005() {
        //5. Extract the gender of all the object whose status = active
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status  inactive are: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test006() {
        //6. Print the names of the object whose gender = female
        List<String> female = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status  inactive are: " + female);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test007() {
        //7. Get all the emails of the object where status = inactive
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status  inactive are: " + allEmails);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test008() {
        //8. Get the ids of the object where gender = male
        List<Integer> idsOfObject = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status  inactive are: " + idsOfObject);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test009() {
        //9. Get all the status
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Names : " + status);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test010() {
        //10. Get email of the object where name = Karthik Dubashi IV
        String getEmail = response.extract().path("findAll{it.email == 'namboothiri_chidaakaash_dr@schamberger.test'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email is: " + getEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test011() {
        //11. Get gender of id = 5471
        int id = response.extract().path("find");
        System.out.println("------------------StartingTest---------------------------");
       // System.out.println("The email is: " + );
        System.out.println("------------------End of Test---------------------------");


    }


}









