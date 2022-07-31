package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.rmi.transport.Endpoint;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * When User sends GET Request to
 * https://reqres.in/api/users
 * <p>
 * Then Response status code should be 200
 * And Response body should contain "George"
 * And Header Content type should be json
 */

public class RecapRestApiTest {

    String url = "https://reqres.in/api/users";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {

        //WHEN USER SENDS GET REQUEST
        //Response response = RestAssured.get(url); --> without static import
        Response response = when().get(url); // with already imported restAssured, and when() is making it more readable (gherkin syntax)

        //Then response status should be 200
        System.out.println("Status code =" + response.statusCode());
        assertEquals(200, response.statusCode());

        //BDD syntax
        response.then().statusCode(200);//assertion we can add assertThat() after than()

        //print response body in a formatted way
        response.prettyPrint();

        //And response body should contain "George"
        System.out.println("Response json body = " + response.asString());
        assertTrue(response.asString().contains("George"), "Verification failed");

        //And Header type should be json
        System.out.println("Content type header value = " + response.contentType());
        assertTrue(response.contentType().contains("application/json"), "Verification failed");


    }

    /**
     * When User Sends get request to API Endpoint:
     * "https://reqres.in/api/users/5"
     * Then Response status code should be 200
     * And Response body should contain user info "Charles"
     */


    @Test
    public void getSingleUserApiTest() {

        Response response = when().get(url + "/5");

        //Then response status should be 200
        System.out.println("Status code =" + response.statusCode());
        assertEquals(200, response.statusCode());


        //print response body in a formatted way
        response.prettyPrint();

        //And response body should contain "George"
        System.out.println("Response json body = " + response.asString());
        assertTrue(response.asString().contains("Charles"), "Verification failed");

        //And Header type should be json
        System.out.println("Content type header value = " + response.contentType());
        assertTrue(response.contentType().contains("application/json"), "Verification failed");


    }

    /**
     * When Send get request to API Endpoint:
     * "https://reqres.in/api/users/50"
     * Then Response status code should be 404
     * And Response body should contain "{}"
     */
    @DisplayName("GET request to non existing user")
    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = when().get(url + "/50");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(404, response.statusCode());

        System.out.println("Json body = " + response.asString());
        assertEquals("{}", response.asString());

    }


}
