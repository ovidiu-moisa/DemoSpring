package com.hackerrank.stocktrades;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.request;
import static io.restassured.RestAssured.*;


@SpringBootTest
public class DemoApplicationRestTests {


    @Test
    public void testPostCallRest() {
        String requestBody = "{\"type\":\"sell\",\"userId\": 23,\"symbol\": \"AAC\",\"shares\": 12, \"price\" : 133, \"timestamp\":1521522701000l}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/trades")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        Response response = RestAssured.given()
                   .when()
                   .get("/trades/1")
                   .then()
                   .statusCode(HttpStatus.OK.value())
                   .contentType(ContentType.JSON)
                   .extract()
                   .response();

        //StockTrade foundTradeobj = response.getBody().as(StockTrade.class);
    //using contains since I could not test this because of some kind of groovy/restassured conflict in the dependencies
        String foundTrade = response.getBody().asString();
        assert (foundTrade.contains("sell"));
        assert (foundTrade.contains("\"userId\": 23"));

    }


}




