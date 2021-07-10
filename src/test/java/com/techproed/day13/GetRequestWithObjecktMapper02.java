package com.techproed.day13;

import com.techproed.testBase.TestBaseHerokuApp;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjecktMapper02 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
   "firstname": "Mark",
   "lastname": "Wilson",
   "totalprice": 284,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-08-10",
       "checkout": "2018-06-22"
   }
}
     */

    @Test
    public void test(){

        //url gunder
        spec02.pathParams("parametre1","booking","parametre2",2);

        //expected data olusturalim
        //.---String cevir
        String jsonData="{\n" +
                "   \"firstname\": \"Susan\",\n" +
                "   \"lastname\": \"Smith\",\n" +
                "   \"totalprice\": 297,\n" +
                "   \"depositpaid\": true,\n" +
                "   \"bookingdates\": {\n" +
                "       \"checkin\": \"2017-03-06\",\n" +
                "       \"checkout\": \"2021-06-12\"\n" +
                "   }\n" +
                "}";

        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData,HashMap.class);
        System.out.println(expectedDataMap);

    Response response=given().contentType(ContentType.JSON).spec(spec02).when().get("/{parametre1}/{parametre2}");
    response.prettyPrint();

    HashMap<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println(actualDataMap);

        //Assert.assertEquals(expectedDataMap,actualDataMap);
        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));

       // System.out.println(expectedDataMap.get("checkin")); bu null getiriyor


    }
}