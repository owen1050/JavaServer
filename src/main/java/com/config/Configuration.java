package com.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Configuration {
    public int port;

    public Configuration(){
        JSONParser parser = new JSONParser();
        try{
            Object jsonIn = parser.parse(new FileReader("src/main/java/com/config/config.json"));
            JSONObject json = (JSONObject) jsonIn;
            port = Integer.parseInt(json.get("Port").toString());
        }
        catch (Exception e){
            System.out.println("Error in reading json");
        }
    }
}
