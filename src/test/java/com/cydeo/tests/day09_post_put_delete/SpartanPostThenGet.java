package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostThenGet extends SpartanTestBase {

    Spartan newSpartan = SpartanRestUtils.getNewSpartan();

    @DisplayName("POST /spartan -> GET with id and compare")
    @Test
    public void postNewSpartanThenGetTest() {

        System.out.println("newSpartan = " + newSpartan);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");
        response.prettyPrint();
        assertThat(response.statusCode(), is(201));

        // get id value using jsonPath()
        int newSpartanID = response.jsonPath().getInt("data.id");
        System.out.println("newSpartanID = " + newSpartanID);

        //Send GET request with newSpartanID and compare
        Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("id", newSpartanID)
                .when().get("/spartans/{id}");
        System.out.println("GET request body");
        response1.prettyPrint();
        //Convert GET request JSON body to another SPARTAN Object -> Deserialization
        Spartan spartanFromGetRequest = response1.as(Spartan.class);

        //compare posted Spartan with GET request Spartan
        assertThat(spartanFromGetRequest.getName(),equalTo(newSpartan.getName()));
        assertThat(spartanFromGetRequest.getGender(),equalTo(newSpartan.getGender()));
        assertThat(spartanFromGetRequest.getPhone(),equalTo(newSpartan.getPhone()));

    }
}
