package com.cydeo.tests.day04_jsonpath;

import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPathMethodTest extends SpartanTestBase {

    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @DisplayName("GET /spartans/{id} and path()")
    @Test
    public void readSpartanJsonUsingPathTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",13)
                .when().get("/spartans/{id}");

        System.out.println("id = " + response.path("id"));
        System.out.println("Name = " + response.path("name"));
        System.out.println("Gender = " + response.path("gender"));
        System.out.println("Phone = " + response.path("phone"));

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        int spartanId = response.path("id");
        assertEquals(13,spartanId);
        assertEquals("Jaimie",response.path("name"));
        assertEquals("Female",response.path("gender"));
        assertEquals(7842554879L,(long)response.path("phone"));

    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @DisplayName("GET /spartans and path()")
    @Test
    public void readSpartanJsonArrayUsingPathTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //print first spartan id and name
        System.out.println("first spartan id = " + response.path("id[0]"));
        System.out.println("first person name = " + response.path("name[0]"));
        //System.out.println("first person name = " + response.path("[0].name"));--> same result as above one

        //print last spartan id and name [-1] index point to last one
        System.out.println("Last spartan id = " + response.path("id[-1]"));
        System.out.println("Last person name = " + response.path("name[-1]"));

        //get all ids into a List
        List<Integer> allIds= response.path("id");
        System.out.println(allIds.size());
        System.out.println(allIds);

        //get all names and say hi
        List<String> names = response.path("name");
        names.forEach(name -> System.out.println("Hi "+ name));//functional programing

        for(String name : names){

            System.out.println("By " + name);
        }

    }

}
