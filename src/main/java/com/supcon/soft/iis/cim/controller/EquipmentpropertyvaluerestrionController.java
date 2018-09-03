package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyvaluerestrionEntity;
import com.supcon.soft.iis.cim.manager.EquipmentpropertyvaluerestrionManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentpropertyvaluerestrion")
@Slf4j
public class EquipmentpropertyvaluerestrionController extends BaseController {
    @Autowired
    private EquipmentpropertyvaluerestrionManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentpropertyvaluerestrionById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentpropertyvaluerestrionById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentpropertyvaluerestrionList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentpropertyvaluerestrionList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentpropertyvaluerestrion(@RequestBody EquipmentpropertyvaluerestrionEntity equipmentpropertyvaluerestrion) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentpropertyvaluerestrion(equipmentpropertyvaluerestrion, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentpropertyvaluerestrion(@RequestBody EquipmentpropertyvaluerestrionEntity equipmentpropertyvaluerestrion) {
        if (null == equipmentpropertyvaluerestrion.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentpropertyvaluerestrion(equipmentpropertyvaluerestrion, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentpropertyvaluerestrion(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentpropertyvaluerestrion(id, submitter), HttpStatus.OK);
    }

}

