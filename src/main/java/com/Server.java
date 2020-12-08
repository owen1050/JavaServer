package com;

import com.config.ConfigManager;
import com.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

    private static final Logger logger = LogManager.getLogger(Server.class);

    public static void main(String[] args){
        ConfigManager cm = new ConfigManager();
        Configuration conf = cm.getConfig();
        logger.info("Starting server on port " + conf.port);
    }

}