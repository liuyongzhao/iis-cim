package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyEntity;
import com.supcon.soft.iis.cim.manager.EquipmentpropertyManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentproperty")
@Slf4j
public class EquipmentpropertyController extends BaseController {
    @Autowired
    private EquipmentpropertyManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentpropertyById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentpropertyById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentpropertyList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentpropertyList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentproperty(@RequestBody EquipmentpropertyEntity equipmentproperty) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentproperty(equipmentproperty, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentproperty(@RequestBody EquipmentpropertyEntity equipmentproperty) {
        if (null == equipmentproperty.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentproperty(equipmentproperty, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentproperty(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentproperty(id, submitter), HttpStatus.OK);
    }

}

