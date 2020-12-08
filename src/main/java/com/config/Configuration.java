package com.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Configuration {
    String value1;
    String o1v1;
    String o1v2;

    public Configuration(){
        System.out.println("Configuration ran");
        JSONParser parser = new JSONParser();
        try{
            Object jsonIn = parser.parse(new FileReader("src/main/java/com/config/config.json"));
            JSONObject json = (JSONObject) jsonIn;
            value1 = json.get("Prop1").toString();
            JSONObject prop2 = (JSONObject) json.get("Prop2");
            o1v1 = prop2.get("Prop2V1").toString();
            o1v2 = prop2.get("Prop2V2").toString();
        }
        catch (Exception e){
            System.out.println("Error in reading json");
        }
    }
}
