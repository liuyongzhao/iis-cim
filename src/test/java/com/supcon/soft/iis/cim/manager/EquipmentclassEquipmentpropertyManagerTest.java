package com.supcon.soft.iis.cim.manager;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EquipmentclassEquipmentpropertyManagerTest {

    @Autowired
    private EquipmentclassEquipmentpropertyManager equipmentclassEquipmentpropertyManager;

    @Test
    public void getEquipmentclassEquipmentpropertyByIdTest(){
        log.info(equipmentclassEquipmentpropertyManager.getEquipmentclassEquipmentpropertyById(1).toString());
    }

}