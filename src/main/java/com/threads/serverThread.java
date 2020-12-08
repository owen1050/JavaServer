package com.threads;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class serverThread extends Thread{

    private static final Logger logger = LogManager.getLogger(serverThread.class);
    int port;

    public serverThread(int port){
        this.port = port;
    }

    public void run(){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(this.port);
            logger.info("Server started on port " + this.port);

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                socketThread st = new socketThread(socket);
                st.start();
            }

            serverSocket.close();
            logger.info("Server closed from port " + this.port);
        } catch (IOException e) {
            logger.error("Server could not be started on port " + this.port + " because of error:" + e);
        }
    }

    public void joinNoErr() {
        try {
            super.join();
        } catch (InterruptedException e) {
            logger.error("Could not join server thread " + e);
        }
    }

}
