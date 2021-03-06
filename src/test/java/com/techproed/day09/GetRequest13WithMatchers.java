package com.techproed.day09;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.hamcrest.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13WithMatchers extends TestBaseDummy {
    /*
        http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
      {
     "id":"11"
     "employee_name": "Jena Gaines",
    "employee_salary": "90560",
    "employee_age": "30",
    "profile_image": "" }
    } gibi olduğunu test edin.
         */
    @Test
    public void test() {
        spec03.pathParam("parametre1", "employees");
        TestDataDummy testData = new TestDataDummy();
        HashMap<String, Object> expectedDataMap = testData.setUpTestData();
        System.out.println(expectedDataMap);
        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                body("data[4].employee_name", equalTo(expectedDataMap.get("besinciCalisan")),
                        "data.id", hasSize((Integer) expectedDataMap.get("calisanSayisi")),
                        "data[-2].employee_salary", equalTo(expectedDataMap.get("istenenMaas")),
                        "data.employee_age", hasItems(
                                ((List) expectedDataMap.get("yasListesi")).get(0),
                                ((List) expectedDataMap.get("yasListesi")).get(1),
                                ((List) expectedDataMap.get("yasListesi")).get(2)),
                        "data[10].id", equalTo(((Map) expectedDataMap.get("employe11")).get("id")),
                        "data[10].employee_name", equalTo(((Map) expectedDataMap.get("employe11")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedDataMap.get("employe11")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedDataMap.get("employe11")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map) expectedDataMap.get("employe11")).get("profile_image")));


    }
}
