package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
     */
    @Test
    public void test(){
        spec03.pathParam("parametre1","employees");
        Response response=given().
                accept("application/json").
                spec(spec03).when().get("/{parametre1}");

        response.prettyPrint();
        JsonPath json=response.jsonPath();
        System.out.println(json.getList("data.employee_name"));
        //response daki tum employee leri bulup getirir.
        System.out.println("====================");
        System.out.println(json.getString("data.employee_name[2]"));

        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));
        String list;
      /* for (int i = 0; i < 4; i++) {
           System.out.println(list.get(i));

       } */
        System.out.println(json.getString("data.employee_name[-1]"));
        //[-1] en sondaki ismi veriri


    }





}
