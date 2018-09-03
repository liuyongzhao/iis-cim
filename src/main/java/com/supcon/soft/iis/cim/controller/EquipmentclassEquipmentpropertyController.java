package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentclassEquipmentpropertyEntity;
import com.supcon.soft.iis.cim.manager.EquipmentclassEquipmentpropertyManager;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentclassEquipmentpropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentclassEquipmentproperty")
@Slf4j
public class EquipmentclassEquipmentpropertyController extends BaseController{
    @Autowired
    private EquipmentclassEquipmentpropertyManager manager;

    @Autowired
    private EquipmentclassEquipmentpropertyRepository repository;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentclassEquipmentpropertyById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        // log.info("{}: User [{}] request {} with {}", new Date().toString(), submitter.toString(), EquipmentClass.class, id);
        return new ResponseEntity<>(manager.getEquipmentclassEquipmentpropertyById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentclassEquipmentpropertyList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentclassEquipmentpropertyList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentclassEquipmentproperty(@RequestBody EquipmentclassEquipmentpropertyEntity equipmentclassEquipmentproperty) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentclassEquipmentproperty(equipmentclassEquipmentproperty, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentclassEquipmentproperty(@RequestBody EquipmentclassEquipmentpropertyEntity equipmentclassEquipmentproperty) {
        if (null == equipmentclassEquipmentproperty.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentclassEquipmentproperty(equipmentclassEquipmentproperty, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentclassEquipmentproperty(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentclassEquipmentproperty(id, submitter), HttpStatus.OK);
    }

}

