
package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.EquipmentClassEntity;
import com.supcon.soft.iis.cim.manager.EquipmentClassManager;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/equipmentClass")
@Slf4j
public class EquipmentClassController extends BaseController {

    @Autowired
    private EquipmentClassManager manager;

    @RequestMapping(value = "/{id}", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentClassById(@PathVariable Integer id) {
        Submitter submitter = getUserInfo();
       // log.info("{}: User [{}] request {} with {}", new Date().toString(), submitter.toString(), EquipmentClass.class, id);
        return new ResponseEntity<>(manager.getEquipmentClassById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = "application/json",method = RequestMethod.GET)
    public ResponseEntity getEquipmentClassList() {
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getEquipmentClassList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipmentClass(@RequestBody EquipmentClassEntity equipmentClass) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createEquipmentClass(equipmentClass, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEquipmentClass(@RequestBody EquipmentClassEntity equipmentClass) {
        if (null == equipmentClass.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateEquipmentClass(equipmentClass, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentClass(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteEquipmentClass(id, submitter), HttpStatus.OK);
    }

}

