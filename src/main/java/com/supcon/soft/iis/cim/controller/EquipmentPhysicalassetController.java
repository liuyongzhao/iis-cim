package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentPhysicalassetEntity;
import com.supcon.soft.iis.cim.manager.EquipmentPhysicalassetManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentPhysicalasset")
@Slf4j
public class EquipmentPhysicalassetController  extends BaseController {
    @Autowired
    private EquipmentPhysicalassetManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentPhysicalassetById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentPhysicalassetById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentPhysicalassetList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentPhysicalassetList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentPhysicalasset(@RequestBody EquipmentPhysicalassetEntity equipmentPhysicalasset) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentPhysicalasset(equipmentPhysicalasset, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentPhysicalasset(@RequestBody EquipmentPhysicalassetEntity equipmentPhysicalasset) {
        if (null == equipmentPhysicalasset.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentPhysicalasset(equipmentPhysicalasset, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentPhysicalasset(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentPhysicalasset(id, submitter), HttpStatus.OK);
    }

}

