package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentpropertyvalueEntity;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EquipmentpropertyvalueRepositoryTest{
    @Autowired
    private EquipmentpropertyvalueRepository repository;

    @Test
    public void insertTest() {
        EquipmentpropertyvalueEntity entity = new EquipmentpropertyvalueEntity();
        entity.setEquipmentId(1);
        entity.setEquipmentPropertyId(10);
        entity.setEquipmentPropertyTagId(11);
        entity.setValue("1234");
        entity.setTimestamp(entity.getUpdated());
        log.info(entity.toString());
        EquipmentpropertyvalueEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentpropertyvalueEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmentpropertyvalueEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setValue("4100");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentpropertyvalueEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentpropertyvalueEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void deleteTest() {
        EquipmentpropertyvalueEntity item = new EquipmentpropertyvalueEntity();
        item.setEquipmentId(1);
        item.setEquipmentPropertyId(10);
        item.setEquipmentPropertyTagId(11);
        item.setValue("1234");
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentpropertyvalueEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmentpropertyvalueClass() {
        log.info(repository.findById(1).toString());
    }
}
