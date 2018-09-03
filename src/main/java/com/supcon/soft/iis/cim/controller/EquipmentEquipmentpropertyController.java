package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentEquipmentpropertyEntity;
import com.supcon.soft.iis.cim.manager.EquipmentEquipmentpropertyManager;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentEquipmentpropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentEquipmentproperty")
@Slf4j
public class EquipmentEquipmentpropertyController extends BaseController {
    @Autowired
    private EquipmentEquipmentpropertyManager manager;

    @Autowired
    private EquipmentEquipmentpropertyRepository repository;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentEquipmentpropertyById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentEquipmentpropertyById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentEquipmentpropertyList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentEquipmentpropertyList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentEquipmentproperty(@RequestBody EquipmentEquipmentpropertyEntity equipmentEquipmentproperty) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentEquipmentproperty(equipmentEquipmentproperty, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentEquipmentproperty(@RequestBody EquipmentEquipmentpropertyEntity equipmentEquipmentproperty) {
        if (null == equipmentEquipmentproperty.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentEquipmentproperty(equipmentEquipmentproperty, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentEquipmentproperty(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentEquipmentproperty(id, submitter), HttpStatus.OK);
    }

}

