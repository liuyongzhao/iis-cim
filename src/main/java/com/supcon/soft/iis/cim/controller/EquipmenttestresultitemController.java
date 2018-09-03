package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmenttestresultitemEntity;
import com.supcon.soft.iis.cim.manager.EquipmenttestresultitemManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmenttestresultitem")
@Slf4j
public class EquipmenttestresultitemController extends BaseController {
    @Autowired
    private EquipmenttestresultitemManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenttestresultitemById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenttestresultitemById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenttestresultitemList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenttestresultitemList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmenttestresultitem(@RequestBody EquipmenttestresultitemEntity equipmenttestresultitem) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmenttestresultitem(equipmenttestresultitem, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmenttestresultitem(@RequestBody EquipmenttestresultitemEntity equipmenttestresultitem) {
        if (null == equipmenttestresultitem.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmenttestresultitem(equipmenttestresultitem, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmenttestresultitem(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmenttestresultitem(id, submitter), HttpStatus.OK);
    }

}

