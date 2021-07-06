package com.techproed.apipractice;

import org.junit.Test;

public class Get02 {
    /*
         Positive Scenario:
                 When I send a GET Request to
                     http://dummy.restapiexample.com/api/v1/employees
             And Accept type  “application/JSON” olsun
             Then
                 HTTP Status Code 200 olsun
             And Response'in format "application/JSON" olsun
             And 24 employees olsun
             And "Ashton Cox" employee'lerden biri olsun
             And 21, 61, ve 23 employee yaslarindan biri olsun
              1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
      /*
 http://dummy.restapiexample.com/api/v1/employees url'ine
 accept type'i "application/json" olan GET request'i yolladigimda
 gelen response'un
 status kodunun 200    ve content type'inin "application/json"
ve employees sayisinin 24
ve employee'lerden birinin "Ashton Cox"
ve girilen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
  */


    @Test
    public  void getRequest(){

        String endpoint=" http://dummy.restapiexample.com/api/v1/employees";
    }

}
