package com.cydeo.tests.day13_acces_token_specs;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.path("location"), hasItems("VA", "IL"));


    }

}
