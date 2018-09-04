
package com.supcon.soft.iis.cim.controller;

import com.supcon.soft.iis.cim.core.BaseController;
import com.supcon.soft.iis.cim.entity.BaseCodeEntity;
import com.supcon.soft.iis.cim.manager.BaseCodeManager;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * BaseCode REST API
 *
 * @author qiyuqi
 */

@RestController
@RequestMapping(value = "/basecode")
@Slf4j
public class BaseCodeController extends BaseController {

    @Autowired
    private BaseCodeManager manager;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity getBaseCodeById(@PathVariable Integer id) {
        //Submitter submitter = getUserInfo();
       // log.info("{}: User [{}] request {} with {}", new Date().toString(), submitter.toString(), BaseCode.class, id);
        return new ResponseEntity<>(manager.getBaseCodeById(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBaseCodeList() {
       // Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.getBaseCodeList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createBaseCode(@RequestBody BaseCodeEntity baseCode) {
        Submitter submitter = getUserInfo();
        //TODO: get Current_User information from OSS
        return new ResponseEntity<>(manager.createBaseCode(baseCode, submitter), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateBaseCode(@RequestBody BaseCodeEntity baseCode) {
        if (null == baseCode.getId()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Submitter submitter = getUserInfo();
        return new ResponseEntity<>(manager.updateBaseCode(baseCode, submitter), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBaseCode(@PathVariable Integer id) {
        Submitter submitter = new Submitter();
        return new ResponseEntity<>(manager.deleteBaseCode(id, submitter), HttpStatus.OK);
    }

}


