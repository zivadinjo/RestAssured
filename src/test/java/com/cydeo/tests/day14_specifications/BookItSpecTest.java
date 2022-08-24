package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookItSpecTest extends BookItAPITestBase {

    @Test
    public void teacherInfoTest() {

        /**
         * Given accept type is json
         * And header Authorization is access_token of teacher
         * When I send GET request to /api/me
         * Then status code is 200
         * And content type is json
         * And campus location info is presented in payload
         */

        Map<String, ?> teacherMap = given().spec(teacherReqSpec)
                .when().get("/api/teachers/me")
                .then().spec(responseSpec)
                .and().extract().body().as(Map.class);

        Assertions.assertEquals(1816, teacherMap.get("id") );
        Assertions.assertEquals("Barbabas", teacherMap.get("firstName") );
        Assertions.assertEquals("Lyst", teacherMap.get("lastName") );
        Assertions.assertEquals("teacher", teacherMap.get("role") );

        //by hamcrest Matchers
        given().spec(teacherReqSpec)
                .when().get("api/teachers/me")
                .then().spec(responseSpec)
                .and().body("id", is(1816),
                      "firstname", is("Barbabas"),
                              "lastName", is("Lyst"),
                              "teacher", is("role"));

    }
}
