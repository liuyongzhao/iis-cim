package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.manager.BaseCodeManager;
import com.supcon.soft.iis.cim.model.BaseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/basecode")
@Slf4j
public class BaseCodeController {
    @Autowired
    private BaseCodeManager manager;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBaseCodeById(@PathVariable Integer id){
        try {
            return new ResponseEntity(manager.getBaseCodeById(id),HttpStatus.OK);
        }catch (InfoNotFoundException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
