package com.cydeo.tests.day16_methodsource_mocks;
import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class MockStudentAPITest {

    @BeforeAll
    public static void setUp(){
        baseURI = "https://21e1db9f-5699-40d9-8afd-88306f50e8cc.mock.pstmn.io";
    }

    @DisplayName("GET /students/1")
    @Test
    public void testStudent(){
        given().accept(ContentType.JSON)
                .when().get("/students/1")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .log().all();
    }

}
