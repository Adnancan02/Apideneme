package com.techproed.day09;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    @Test
    public void test() {
        spec03.pathParam("parametre1", "employees");
        TestDataDummy testData = new TestDataDummy();
        HashMap<String, Integer> expectedDataMap = testData.setUpTestData2();
        System.out.println(expectedDataMap);
        Response response = given().

                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        response.prettyPrint();

        //De-Serialization islemi

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataMap.get("statusCod"),(Integer) response.getStatusCode());
        //once maaslardan olusan bir list olusturalim

        List<Integer> actualMaasList=new ArrayList<>();

        //actualDataMap ten donen data nin uzunlugunu(size) bulmaliyim
        int dataSize=((List)actualDataMap.get("data")).size();
        for (int i=0; i<dataSize;i++){
            actualMaasList.add((Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(actualMaasList);

      Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),actualMaasList.get(actualMaasList.size()-1));
      Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"),actualMaasList.get(actualMaasList.size()-2));

      //
        List<Integer> actualYasList=new ArrayList<Integer>();
        for (int i=0;i<dataSize;i++){
           actualYasList.add( (Integer)((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age"));
        }

        Collections.sort(actualYasList);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),actualYasList.get(0));

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("statusCod"),(Integer) response.getStatusCode());

        List<Integer> jsonYasListesi=json.getList("data.employee_age");
        Collections.sort(jsonYasListesi);

        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),jsonYasListesi.get(0));
        List<Integer> jsonMaasListesi=json.getList("data.employee_salary");
        Collections.sort(jsonMaasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),jsonMaasListesi.get(jsonMaasListesi.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"),jsonMaasListesi.get(jsonMaasListesi.size()-2));
    }
}
