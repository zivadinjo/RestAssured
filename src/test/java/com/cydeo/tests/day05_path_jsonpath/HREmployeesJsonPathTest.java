package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.HRApiTestBase;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HRApiTestBase {

    /**
     Given accept type is Json
     And query param limit is 200
     when I send GET request to /employees
     Then I can use jsonPath to query and read values from json body
     */

    @DisplayName("GET /employees?limit=200 => jsonPath filters")
    @Test
    public void jsonPathEmployeesTest() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit",200)
                .when().get("/employees");

        assertEquals(200,response.statusCode());

        JsonPath jsonPath= response.jsonPath();

        System.out.println("first emp firstname = "+ jsonPath.getString("items[0].first_name"));
        System.out.println("first emp job tittle = "+ jsonPath.getString("items[0].job_id"));

        //Working with JsonPath lists:
        //get all the emails into list and print out
        List<String> emails = jsonPath.getList("items.email");
        System.out.println(emails);

        //assert TGATES is among emails
        assertTrue(emails.contains("TGATES"));

        //get all employee names who work at departament 90
        List<String> namesDept90 = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println(namesDept90);

        //get all employee first names who work as IT_PROG
        //"items.findAll{it.job_id=='IT_PROG'}.first_name");
        // from list, findAll method from groovy to find, {in parentises you put it.what are you looking or from where are you looking}, . first_name
        //giving you only first name,it act as filter if you don't put anything it returns all result
        List<String> itProg = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println(itProg);

        //get emp first name who has max salary
        String firstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(firstName);

        //get emp first name who has min salary
        String minSalary= jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println(minSalary);

    }

}
