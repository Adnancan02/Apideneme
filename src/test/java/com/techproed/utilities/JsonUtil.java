package com.techproed.utilities;



import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    //object mapper kullanarak de_serialization yapma yöntemi
    private  static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    //Methodumuzu oluşturacağız. Stringe çevrilmiş json datasını java objesine dönüştürecek.
    //T--> genelde developerlarin kullandigi generic bir ifadedir.
    //tum data type lari kapsar,burada bize gelen ifadesi tariyor ve ne yapmasi gerektigini kendisi karar veriyor.
    //
    public static <T> T convertJsonToJava(String json,Class<T> cls){
        T javaResult= null;
        try {
            javaResult = mapper.readValue(json, cls);
            //method a gelen parametreleri okuyor ve bunu mapper tipinde java reult a aktariyor.
            // try catch de kullanilir uresulitet methodlarda kullanilir.
        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi"+e.getMessage());
            //err sayfaya renkli yazdirir
        }
        return javaResult;
    }

}
