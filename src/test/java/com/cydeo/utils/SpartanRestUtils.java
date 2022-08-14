package com.cydeo.utils;

import static io.restassured.RestAssured.given;

public class SpartanRestUtils {

    static String baseUrl = ConfigurationReader.getProperty("spartan.api.url");

    public static void deleteSpartanById(int spartanID) {
        System.out.println("DELETE spartatn with id {" + spartanID + "}");
        // Send DELETE request{{baseUrl}}/api/spartans/id
        given().pathParam("id", spartanID)
                .when().delete(baseUrl + "/spartans/{id}")
                .then().log().all();


    }

}
