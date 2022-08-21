package com.cydeo.tests.day13_acces_token_specs;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.cydeo.utils.BookItAPITestBase;
import static io.restassured.RestAssured.*;

public class BoolItApiTest extends BookItAPITestBase {

    /**
     * Given accept type is json
     * And header Authorization is access_token of teacher
     * When I send GET request to /api/campuses
     * Then status code is 200
     * And content type is json
     * And campus location info is presented in payload
     */
    @Test
    public void getAllCampusesTest() {

        String accessToken = getAccessToken(ConfigurationReader.getProperty("teacher_email"),
                ConfigurationReader.getProperty("teacher_password"));

        System.out.println("accessToken = " + accessToken);

        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .when().get("/api/campuses");

        response.prettyPrint();

    }

}
