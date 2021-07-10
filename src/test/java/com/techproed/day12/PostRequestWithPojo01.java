package com.techproed.day12;

import com.techproed.pojos.TodosPojo;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {
    //POJO Plain Old Java Object: Basit Eski Java Objesi //pojo class lari kullandim orda Api kullandim
    //Pojo Encapsulation yontemi ile olusturulur
    // degiskenler privat yapilarak kisitlama uygulanir buna encapsulation
    //basit manada olusturacagimiz expectedData, requestBody ve actualDatalar icin birer kaliptir.

    //Pojo classlar nasil olustururlur.
    //1-json objecten gelen tum keyleri private bir degisken olarak tanimliyorum.
    //2-Her degisken icin getter ve setter methodlar olusturuyorum.
    //3-Parametresiz Consructor olusturuyoruz
    //4-parametreli constructor olusturuyoruz
    //5-toString

/*
     https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     Status code is 201
      response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
 */


    @Test
    public void test(){
        //url enppoint olusturuldu
        spec01.pathParam("parametre1","todos");

        //requestBosy olusturuyorum

        TodosPojo todos=new TodosPojo(21,201,"Tidy your room",false);
        System.out.println(todos);
        //request
        Response response=given().contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin","password123").
                body(todos).when().post("/{parametre1}");
        response.prettyPrint();

        //De-serialization(Gson)---POJO
        TodosPojo actualData= response.as(TodosPojo.class);
        System.out.println(actualData);

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(todos.getId(),actualData.getId());
        Assert.assertEquals(todos.getUserId(),actualData.getUserId());
        Assert.assertEquals(todos.getTitle(),actualData.getTitle());
        Assert.assertEquals(todos.getCompleted(),actualData.getCompleted());

        //JsonPath ---POJO

        JsonPath json=response.jsonPath();
        Assert.assertEquals(todos.getId(),json.getInt("id"));
        Assert.assertEquals(todos.getUserId(),json.getInt("userId"));
        Assert.assertEquals(todos.getTitle(),json.getString("title"));
        Assert.assertEquals(todos.getCompleted(),json.getBoolean("completed"));

        //Machther class
        response.then().assertThat().
                body("userId", equalTo(todos.getUserId()),
                        "id",equalTo(todos.getId()),"title",
                        equalTo(todos.getTitle()),
                        "completed",equalTo(todos.getCompleted()));





    }

}
