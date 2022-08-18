package com.cydeo.utils;

import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

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


    /**
     * This method creates object of spartan POJO clas and
     * assigns random data using Faker class
     * @return
     */
    public static Spartan getNewSpartan(){
        Faker random = new Faker(); // Create Faker object to help us generate random firstname
        Spartan spartan = new Spartan();
        spartan.setName(random.name().firstName());
        //set random gender, 1-Female otherwise Male
        int num = random.number().numberBetween(1,3);
        if (num == 1) {
            spartan.setGender("Female");
        }else{
            spartan.setGender("Male");
        }
        spartan.setPhone(random.number().numberBetween(1000000000L,9999999999L));

        return spartan;
    }

    //Method accept spartanId and sends GET request,
    //and return is Map object containing json data
    public static Map<String, Object> getSpartan(int spartanId) {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .when().get(baseUrl + "/spartans/{id}");

        Map<String, Object> spartanMap = response.as(Map.class);
        return spartanMap;

    }
}
