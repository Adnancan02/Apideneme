package com.techproed.day03;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest01 {
   /*
    GetRequest01:
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
ØHTTP status kodunun 200
ØContent Type’in Json
ØVe Status Line’in HTTP/1.1 200 OK
Oldugunu test edin.
    */

    @Test
    public void test01() {
        //1- Url belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";
        //2- expected result olustur...(bu test icin gerek yok)
//burada bizden body de gelen reposu assert etmemiz beklenmemistir.
// bu sebeple bu adima gerek yok

        //3-request gönder
        Response response = given().
                accept("application/json").
                when().get(url);
        response.prettyPrint();//responsa dan donen yazdirir.
        //4-respose al(actual result)
        //body testi yapmadigim icin actual result almiyorum

        //5-assertion islemi yapalim
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        System.out.println("satatus code :"+response.getStatusCode());
        System.out.println("satatus line :"+response.getStatusLine());
        System.out.println("Headers :"+response.getHeaders());
        System.out.println("ContentType:"+response.getContentType());
    }

}