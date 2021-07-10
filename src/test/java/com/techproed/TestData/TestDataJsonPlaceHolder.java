package com.techproed.TestData;
import org.json.JSONObject;

import java.util.HashMap;
public class TestDataJsonPlaceHolder {
    public int statusCode=201;
    public HashMap<String, Object> setUpTestData() {
        HashMap<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("completed", false);
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");
        return expectedDataMap;
    }
    public JSONObject setUpPost03(){
    JSONObject requestBody = new JSONObject();
        requestBody.put("userId",55);
        requestBody.put("title","Tidy your room");
        requestBody.put("completed",false);



        return requestBody;
}
public JSONObject  SetUpPut01(){
        /*
         }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
         */

    JSONObject requestBody=new JSONObject();
    requestBody.put("userId",21);
    requestBody.put("title","Tidy your room");
    requestBody.put("completed",false);
    return requestBody;
}
/*
"userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
 */
public JSONObject SetUpPatch01(){
        JSONObject requestBody=new JSONObject();
        requestBody.put("title","API calismaliyim");
        return requestBody;
}
public JSONObject SetUpexpectedBody(){
    JSONObject expectedBody=new JSONObject();
    expectedBody.put("userId",10);
    expectedBody.put("title","API calismaliyim");
    expectedBody.put("completed",true);
    return expectedBody;
}
    }
