package com.config;

public class ConfigManager {
    Configuration config = null;
    public Configuration getConfig(){
        if(config == null){
            config = new Configuration();
        }
        return config;
    }
}
