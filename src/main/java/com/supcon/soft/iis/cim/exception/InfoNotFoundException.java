package com.supcon.soft.iis.cim.exception;

public class InfoNotFoundException extends Exception {
    private final int errorCode = 404;
    private String resource;
    private String request;

    private String messageTemplate = "ERROR 404: The data[{0}] requested with [{1}] is not existed.";

    public InfoNotFoundException(String classname, String request){
        this.resource = classname;
        this.request = request;
    }

    public String getMessage(){
        return String.format(messageTemplate,resource,request);
    }
}
