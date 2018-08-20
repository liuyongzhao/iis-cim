package com.supcon.soft.iis.cim.core;

import com.supcon.soft.iis.cim.model.Submitter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    private static final String TOKEN = "token";
    protected HttpServletRequest requst;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void initalCommonVariables(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        this.requst = request;
        this.response = response;
        this.session = session;
    }

    protected Submitter getUserInfo(){
        if(null == requst.getHeader(TOKEN)) {
            return null;
        }else{
            return new Submitter();
        }
    }
}
