package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.Map;
import java.util.List;

public class BookItApiMethodSourceExcelTest extends BookItAPITestBase {

    @DisplayName("GET /sign ->data from BookItQa3.xlsx")
    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void bookItAuthDDTTest(Map<String, String> userInfo) {
        System.out.println("userInfo = " + userInfo);// this can be used as QueryParams in API call
        //API call/request to bookIt/sign and verify status and access code is there
        given().accept(ContentType.JSON)
                .and().queryParams(userInfo)
                .when().get("/sign")
                .then().spec(responseSpec) //status code, contentType assertion by spec() method
                .and().body("accessToken", not(blankOrNullString())); //hamcrest matcher

    }

    public static List<Map<String, String>> getUserCredentials() {
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelReader = new ExcelUtil(filePath, "QA3");
        List<Map<String, String>> data = excelReader.getDataList();

        return data;
    }

    @DisplayName("GET /sign")
    @Test
    public void loginTestUsingLoop() {
        List<Map<String, String>> allCredentials = getUserCredentials();

        for (Map<String, String> userInfo : allCredentials) {
            System.out.println("userInfo = " + userInfo);
            try {
                given().accept(ContentType.JSON)
                        .and().queryParams(userInfo)
                        .when().get("/sign")
                        .then().spec(responseSpec) //status code, contentType assertion by spec() method
                        .and().body("accessToken", not(blankOrNullString()));
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
