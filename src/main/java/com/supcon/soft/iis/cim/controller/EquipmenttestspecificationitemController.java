package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmenttestspecificationitemEntity;
import com.supcon.soft.iis.cim.manager.EquipmenttestspecificationitemManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmenttestspecificationitem")
@Slf4j
public class EquipmenttestspecificationitemController extends BaseController {
    @Autowired
    private EquipmenttestspecificationitemManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenttestspecificationitemById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenttestspecificationitemById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenttestspecificationitemList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenttestspecificationitemList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmenttestspecificationitem(@RequestBody EquipmenttestspecificationitemEntity equipmenttestspecificationitem) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmenttestspecificationitem(equipmenttestspecificationitem, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmenttestspecificationitem(@RequestBody EquipmenttestspecificationitemEntity equipmenttestspecificationitem) {
        if (null == equipmenttestspecificationitem.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmenttestspecificationitem(equipmenttestspecificationitem, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmenttestspecificationitem(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmenttestspecificationitem(id, submitter), HttpStatus.OK);
    }

}

