package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyvalueEntity;
import com.supcon.soft.iis.cim.manager.EquipmentpropertyvalueManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipmentpropertyvalue")
@Slf4j
public class EquipmentpropertyvalueController extends BaseController {
        @Autowired
        private EquipmentpropertyvalueManager manager;

        @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
        public ResponseEntity getEquipmentpropertyvalueById(@PathVariable Integer id) {
            Submitter submitter = getUserInfo();
            return new ResponseEntity<>(manager.getEquipmentpropertyvalueById(id), HttpStatus.OK);
        }

        @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
        public ResponseEntity getEquipmentpropertyvalueList() {
            Submitter submitter = getUserInfo();
            return new ResponseEntity<>(manager.getEquipmentpropertyvalueList(), HttpStatus.OK);
        }

        @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity createEquipmentpropertyvalue(@RequestBody EquipmentpropertyvalueEntity equipmentpropertyvalue) {
            Submitter submitter = getUserInfo();
            //TODO: get Current_User information from OSS
            return new ResponseEntity<>(manager.createEquipmentpropertyvalue(equipmentpropertyvalue, submitter), HttpStatus.OK);
        }

        @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
        public ResponseEntity updateEquipmentpropertyvalue(@RequestBody EquipmentpropertyvalueEntity equipmentpropertyvalue) {
            if (null == equipmentpropertyvalue.getId()) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            Submitter submitter = getUserInfo();
            return new ResponseEntity<>(manager.updateEquipmentpropertyvalue(equipmentpropertyvalue, submitter), HttpStatus.ACCEPTED);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        public ResponseEntity deleteEquipmentpropertyvalue(@PathVariable Integer id) {
            Submitter submitter = new Submitter();
            return new ResponseEntity<>(manager.deleteEquipmentpropertyvalue(id, submitter), HttpStatus.OK);
        }

    }


