package com.cydeo.tests.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanGetJsonSchemaValidationTest extends SpartanTestBase {

    /**
     * given accept type is json
     * and path param id is 15
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches SingleSpartanSchema.json
     */

    @Test
    public void singleSpartanSchema(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SingleSpartanSchema.json")))
                .and().log().all();

    }

    /**
     * given accept type is json
     * and path param id is 15
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches AllSpartanSchema.json
     */

    @DisplayName("GET /spartan{id} and json schema validation")
    @Test
    public void allSpartansJsonSchemaValidationTest(){

        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/AllSpartansSchema.json")))
                .log().all();

    }

    /**
     * given accept type is json
     * and query param : nameContains "e" and gender "Female"
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches SearchSpartanSchema.json
     */

    @Test
    public void searchSpartansJasonSchemaValidationTest(){

        given().accept(ContentType.JSON)
                .when().queryParam("nameContains","e")
                .and().queryParam("gender","Female")
                .when().get("/spartans/search")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SearchSpartansSchema.json")))
                .log().all();


    }

}
