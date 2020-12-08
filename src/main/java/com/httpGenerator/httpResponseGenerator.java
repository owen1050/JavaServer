package com.httpGenerator;

public class httpResponseGenerator {
    private boolean includeHTTPHeader = true;
    private boolean ok = true;
    private String message = "";
    private boolean includeContentLength = true;

    public void setMessage(String s){
        this.message = s;
    }
    public String genResponse(){
        final String CRLF = "\n\r";
        String ret = "";
        if(includeHTTPHeader){
            if(ok){
                ret = ret + "HTTP/1.1 200 OK" + CRLF;
            }
            else{
                ret = ret + "HTTP/1.1 500 Internal Server Error";
                return ret;
            }
        }
        if(includeContentLength){
            ret = ret + "Content-Length: " + this.message.getBytes().length + CRLF;
        }
        ret = ret + "\n" + this.message + CRLF + CRLF;
        return ret;
    }
}
