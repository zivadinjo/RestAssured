package com.cydeo.tests.day04_jsonpath;

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

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiGetTest {

    @BeforeEach
    public void setUp(){

        //RestAssured.baseURI= ConfigurationReader.getProperty("hr.api.url");
        baseURI= ConfigurationReader.getProperty("hr.api.url");//this option works also because there is static import for RestAssured

    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */


    @Test
    public void getRegionsTest(){

        Response response=given().accept(ContentType.JSON).
                when().get("/regions");
        response.prettyPrint();

        assertEquals(200,response.statusCode(),"Verification of status code failed");
        assertEquals("application/json",response.contentType(),"Verification of content failed");
        assertTrue(response.body().asString().contains("Europe"),"Verification of body failed");

    }

    /**
     * Given accept type is json
     And Path param "region_id" value is 1
     When user send get request to /ords/hr/regions/{region_id}
     Status code should be 200
     Content type should be "application/json"
     And body should contain "Europe"
     */
    @Test
    public void getSinglePathParamName(){

        Response response=given().log().all().accept(ContentType.JSON).
                and().pathParam("region_id",1).when().get("/regions/{region_id}");
        response.prettyPrint();

        assertEquals(200,response.statusCode(),"Verification of status code failed");
        //assertEquals(HttpStatus.SC_OK,response.statusCode(),"Verification of status code failed");
        assertEquals("application/json",response.contentType(),"Verification of content failed");
        //assertEquals(ContentType.JSON.toString(),response.contentType(),"Verification of content failed");
        assertTrue(response.body().asString().contains("Europe"),"Verification of body failed");

    }

    /**
     * Given accept type is json
     * And query param q={"region_name": "Americas"}
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */

    @Test
    public void getRegionWithQueryParamTest(){

        Response response=given().log().all().accept(ContentType.JSON).
                and().queryParam("q","{\"region_name\": \"Americas\"}")
                .when().get("/regions");

        response.prettyPrint();

        assertEquals(200,response.statusCode(),"Verification of status code failed");
        //assertEquals(HttpStatus.SC_OK,response.statusCode(),"Verification of status code failed");
        assertEquals("application/json",response.contentType(),"Verification of content failed");
        //assertEquals(ContentType.JSON.toString(),response.contentType(),"Verification of content failed");
        assertTrue(response.body().asString().contains("Americas"),"Verification of body failed");
        assertTrue(response.body().asString().contains("\"region_id\": 2"), "Verification of region id failed");

    }


}
