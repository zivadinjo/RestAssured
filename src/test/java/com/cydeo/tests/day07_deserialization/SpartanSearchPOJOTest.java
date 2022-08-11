package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPOJOTest extends SpartanTestBase {

    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And json response data is as expected
     */

    @Test
    public void spartanSearchToPOJOTest(){

        Map<String,String> queryMap =  new LinkedHashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response= given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //deserialize json to SpartanSearch pojo class
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);

        //total elements
        System.out.println("Total element = "+ spartanSearch.getTotalElement());

        //all spartans
        System.out.println("All spartans = "+ spartanSearch.getContent());

        //first spartan info
        System.out.println("First spartan = "+ spartanSearch.getContent().get(0));

        //second spartan info,we put it in variable to get only info for second spartan
        Spartan secondSpartan = spartanSearch.getContent().get(1);
        System.out.println("secondSpartan = " + secondSpartan);
        //get specific value
        System.out.println("second spartan name = " + secondSpartan.getName());
        System.out.println("second spartan id = " + secondSpartan.getId());

        List<Spartan> spartanData = spartanSearch.getContent();
        //String seconndSpartan = String.valueOf(spartanData.get(1));
        //System.out.println(seconndSpartan);

        //read all names to list
        List<String> names = new ArrayList<>();
        for (Spartan sp : spartanData){
            names.add(sp.getName());
        }
        System.out.println("names = " +names);

        //using functional programing
        List<String> allNames = spartanData.stream().map(sp->sp.getName()).collect(Collectors.toList());
        System.out.println("allNames = " + allNames);


    }

}
