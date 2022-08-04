package com.cydeo.tests.day03_parameters;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithParamsTest {

    String url = "http://54.211.225.58:8000/api/spartans";

    /**
     * Given accept type is Json
     * And Id parameter value is 5
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Blythe" should be in response payload(body)
     */

    @DisplayName("GET/api/spartans/{id}")
    @Test
    public void getSingleSpartan() {

        int id = 5;
        given().accept(ContentType.JSON)
                .when().get(url + "/" + id);// int id declared and concatenated with url

        given().accept(ContentType.JSON).when().get(url + "/5"); // this one is id direct concatenated to url

        Response response = given().accept(ContentType.JSON).and().pathParams("id", 5).when().get(url + "/{id}"); // pathParameters method giving id value of 5 in this case

        response.prettyPrint();

        System.out.println("Status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        assertEquals(HttpStatus.SC_OK, response.statusCode()); // HttpStatus.SC_OK replaces 200 status code should be 200

        System.out.println("content type = " + response.contentType());
        System.out.println("content type = " + response.getHeader("content-type"));

        assertEquals("application/json", response.contentType()); // response content-type: application/json

        assertTrue(response.asString().contains("Blythe"));// Getting name Blythe assertion

    }

    /**Given accept type is Json
     And Id parameter value is 500
     When user sends GET request to /api/spartans/{id}
     Then response status code should be 404
     And response content-type: application/json
     And "Not Found" message should be in response payload
     */

    @Test
    public void getSingleSpartanNotFound(){


        Response response = given().accept(ContentType.JSON).and().pathParam("id",500)
                        .when().get(url + "/{id}"); /*And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}*/

        response.prettyPrint();

        System.out.println("Status code = " + response.statusCode());

        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.asString().contains("Not Found"));




    }


}
