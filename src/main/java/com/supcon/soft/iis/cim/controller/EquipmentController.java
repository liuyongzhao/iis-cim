package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentEntity;
import com.supcon.soft.iis.cim.manager.EquipmentManager;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/equipment")
@Slf4j
public class EquipmentController extends BaseController {

    @Autowired
    private EquipmentManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
        // log.info("{}: User [{}] request {} with {}", new Date().toString(), submitter.toString(), Equipment.class, id);
        return new ResponseEntity<>(manager.getEquipmentById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipment(@RequestBody EquipmentEntity equipment) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipment(equipment, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipment(@RequestBody EquipmentEntity equipment) {
        if (null == equipment.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipment(equipment, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipment(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipment(id, submitter), HttpStatus.OK);
    }

}