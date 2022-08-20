package com.cydeo.tests.day_12_jsonschema_authorization;

import com.cydeo.utils.SpartanSecureTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanBasicAuthTest extends SpartanSecureTestBase {

    /**
     given accept type is json
     and basic auth credentials are admin , admin
     When user sends GET request to /spartans
     Then status code is 200
     And content type is json
     And print response
     */
    @DisplayName("GET /spartans with basic auth")
    @Test
    public void getSpartansWithAuthTest() {

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().log().all();

    }

}
