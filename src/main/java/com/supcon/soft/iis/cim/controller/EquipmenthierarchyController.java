package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmenthierarchyEntity;
import com.supcon.soft.iis.cim.manager.EquipmenthierarchyManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmenthierarchy")
@Slf4j
public class EquipmenthierarchyController extends BaseController {
    @Autowired
    private EquipmenthierarchyManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenthierarchyById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenthierarchyById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmenthierarchyList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmenthierarchyList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmenthierarchy(@RequestBody EquipmenthierarchyEntity equipmenthierarchy) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmenthierarchy(equipmenthierarchy, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmenthierarchy(@RequestBody EquipmenthierarchyEntity equipmenthierarchy) {
        if (null == equipmenthierarchy.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmenthierarchy(equipmenthierarchy, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmenthierarchy(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmenthierarchy(id, submitter), HttpStatus.OK);
    }

}

