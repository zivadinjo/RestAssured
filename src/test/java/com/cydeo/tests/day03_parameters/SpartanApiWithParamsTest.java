package com.cydeo.tests.day03_parameters;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        given().accept(ContentType.JSON).when().get(url+"/5");

        Response response= given().accept(ContentType.JSON).and().pathParams("id",5).when().get(url+"/{id}");

        response.prettyPrint();

        System.out.println("Status code = " + response.statusCode());
        assertEquals(200,response.statusCode());



    }

}
