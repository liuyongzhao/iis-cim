package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentEquipmentpropertyEntity;
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
public class EquipmentEquipmentpropertyRepositoryTest {
    @Autowired
    private EquipmentEquipmentpropertyRepository repository;

    @Test
    public void insertTest() {
        EquipmentEquipmentpropertyEntity entity = new EquipmentEquipmentpropertyEntity();
        entity.setEquipmentId(1);
        entity.setEquipmentPropertyId(9);
        entity.setUnitId(8);
        entity.setEquipmentPropertyTagId(9);
        log.info(entity.toString());
        EquipmentEquipmentpropertyEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentEquipmentpropertyEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }
    @Test
    public void updateTest() {
        EquipmentEquipmentpropertyEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setUnitId(100);
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentEquipmentpropertyEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentEquipmentpropertyEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
        System.out.println("输出查询结果"+query);
    }

    @Test
    public void deleteTest(){
        EquipmentEquipmentpropertyEntity item = new EquipmentEquipmentpropertyEntity();
        item.setEquipmentId(100);
        item.setEquipmentPropertyId(100);
        item.setUnitId(100);
        item.setEquipmentPropertyTagId(100);
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentEquipmentpropertyEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }
    @Test
    public void queryEquipmentEquipmentpropertyClass(){
        log.info(repository.findById(1).toString());
    }
}
