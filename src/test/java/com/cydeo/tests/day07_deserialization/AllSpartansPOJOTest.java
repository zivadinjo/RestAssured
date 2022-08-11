package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansPOJOTest extends SpartanTestBase {

    /**
     * Given accept type is json
     * when I send GET request to /spartans
     * Then status code is 200
     * And content type is json
     * And I can convert json to list of spartan pojos
     */

    @Test
    public void allSpartansToPojoTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //convert to jsonpath
        JsonPath jsonPath = response.jsonPath();

        //using jsonPath to extract List of spartans  to  do deserialization
        List<Spartan> allSpartans = jsonPath.getList("",Spartan.class);
        System.out.println("count = " + allSpartans.size());

        //first spartan
        System.out.println("first spartan = " + allSpartans.get(0));
        
        //using streams: filter and store female spartans in to different list
        List<Spartan> femaleSpartans= allSpartans.stream()
                .filter(spartan -> spartan.getGender()
                .equals("Female")).collect(Collectors.toList());
        System.out.println("femaleSpartans.size() = " + femaleSpartans.size());

        //using streams: filter and store male spartans in to different list
        long count = allSpartans.stream().filter(sp-> sp.getGender().equals("Male")).count();
        List<Spartan> maleSpartan = allSpartans.stream().filter(sp->sp.getGender().equals("Male"))
                .collect(Collectors.toList());
        System.out.println("maleSpartan = " + maleSpartan + count);

    }

}
