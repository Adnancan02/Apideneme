package com.techproed.apipractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get01 {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Wilson"
     And depositpaid true
     And checkin date "2016-06-19"
     And checkout date "2019-08-26"
     1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
        */

    @Test
    public void getRequest() {
        // 1.Adim Url'i olustur
        String endPoint = "https://restful-booker.herokuapp.com/booking/10";

        //Response response=given().accept("application/json").when().get(endPoint);
        //response.prettyPrint();
        //3.Adim Request gönder
        Response response = RestAssured.given().accept("application/json").when().get(endPoint);
        response.prettyPrint();

        response.then().assertThat().contentType("application/json").statusCode(200);

        //4.Adim Validation yap
     /*   response.then().assertThat().body("firstname",equalTo("Eric"),
                "lastname",equalTo("Brown"),
                "depositpaid",equalTo(false),
                "bookingdates.checkin",equalTo("2015-09-12"),"bookingdates.checkout",
                equalTo("2019-04-26"));
 */
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals("Mark",jsonPath.getString("firstname"));
        Assert.assertEquals("Jackson",jsonPath.getString("lastname"));
        Assert.assertEquals(970,jsonPath.getInt("totalprice"));
        Assert.assertEquals("2015-02-11",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2016-06-20",jsonPath.getString("bookingdates.checkout"));


    }
/*
{
    "firstname": "Mark",
    "lastname": "Jackson",
    "totalprice": 970,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2015-02-11",
        "checkout": "2016-06-20"
    },
    "additionalneeds": "Breakfast"
}
 */
}
