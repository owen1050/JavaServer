package com.threads;

import com.httpGenerator.httpResponseGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.OutputStream;
import java.io.InputStream;

public class socketThread extends Thread{

    private Socket socket;
    private static final Logger logger = LogManager.getLogger(socketThread.class);

    public  socketThread(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try {
            InputStream is = this.socket.getInputStream();

            String request = isToStr(is);
            logger.info("Request:" + request);

            OutputStream os = this.socket.getOutputStream();

            Path htmlPath = Paths.get("src/main/java/com/html/example.html");
            String message = new String(Files.readAllBytes(htmlPath));

            httpResponseGenerator respGen = new httpResponseGenerator();
            respGen.setMessage(message);
            String messageWithHeader = respGen.genResponse();

            os.write(messageWithHeader.getBytes(StandardCharsets.UTF_8));

            is.close();
            os.close();
            this.socket.close();

        } catch (Exception e) {
            logger.error("Error in socket thread" + e);
        }
    }

    private String isToStr(InputStream is){
        String ret = "\n";
        try{
            int c = is.read();
            ret = ret.concat(Character.toString((char)c));
            while(c > -1) {
                c = is.read();
                ret = ret.concat(Character.toString((char)c));
                if(ret.length() > 4 && ret.substring(ret.length()-4).compareTo("\r\n\r\n") == 0){
                    return ret;
                }
            }
        }
        catch(Exception e){
            logger.error("Could not convert request to string");
        }
        return ret;
    }
}
