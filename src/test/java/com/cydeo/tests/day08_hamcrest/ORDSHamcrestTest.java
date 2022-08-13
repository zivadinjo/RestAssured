package com.cydeo.tests.day08_hamcrest;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRApiTestBase {

    /**
     * given accept type is json
     * when I send get request to /countries
     * Then status code is 200
     * and content type is json
     * and count should be 25
     * and country ids should contain "AR,AU,BE,BR,CA"
     * and country names should have "Argentina,Australia,Belgium,Brazil,Canada"
     * <p>
     * items[0].country_id ==> AR
     * items[1].country_id ==> AU
     * items.country_id ==> AR, AU, .... all of them as a list of string
     */

    @DisplayName("GET /countries ->hamcrest assertion")
    @Test
    public void countriesTest() {

        String countryId = given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("count", is(25),
                        "items.country_id", hasItems("AR", "AU", "BE", "BR", "CA"),
                        "items.country_name", hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"),
                        "items[0].country_id", is(equalTo("AR")),
                        "items[1].country_id", is(equalTo("AU")))
                .and().extract().body().path("items[0].country_id");
        //.log().all();

        System.out.println("countryId = " + countryId);


        /**
         * * given accept type is json
         *      * when I send get request to /countries
         *      * Then status code is 200
         *      * and content type is json
         *      And county_id is AR ,country_name is Argentina, and region_id is 2
         */

        given().accept(ContentType.JSON)
                .and().pathParam("country_id",countryId)
                .when().get("/countries/{country_id}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("country_name",equalTo("Argentina"),
                "country_id",equalTo("AR"),
                        "region_id",equalTo(2));
        //.and().extract().jsonPath(); -> needs to be assigned in variable at the beginning of the path

    }
}
