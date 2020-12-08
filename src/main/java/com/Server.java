package com;

import com.config.ConfigManager;
import com.config.Configuration;

import com.threads.serverThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Server {

    private static final Logger logger = LogManager.getLogger(Server.class);

    public static void main(String[] args){
        ConfigManager cm = new ConfigManager();
        Configuration conf = cm.getConfig();
        int port = conf.port;
        serverThread st = new serverThread(port);
        st.start();
        st.joinNoErr();
        logger.info("Shutting down all");


    }

}