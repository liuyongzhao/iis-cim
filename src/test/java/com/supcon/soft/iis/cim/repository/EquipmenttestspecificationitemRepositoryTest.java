package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestspecificationitemEntity;
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
public class EquipmenttestspecificationitemRepositoryTest{
    @Autowired
    private EquipmenttestspecificationitemRepository repository;

    @Test
    public void insertTest() {
        EquipmenttestspecificationitemEntity entity = new EquipmenttestspecificationitemEntity();
        entity.setPropertyId(11);
        entity.setPropertyTagId(22);
        entity.setSpecificationId(33);
        log.info(entity.toString());
        EquipmenttestspecificationitemEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmenttestspecificationitemEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmenttestspecificationitemEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setPropertyId(1100);
        log.info(result.toString());
        EquipmenttestspecificationitemEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmenttestspecificationitemEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);

    }

    @Test
    public void deleteTest() {
        EquipmenttestspecificationitemEntity item = new EquipmenttestspecificationitemEntity();
        item.setPropertyId(110);
        item.setPropertyTagId(220);
        item.setSpecificationId(330);
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmenttestspecificationitemEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmenttestspecificationitemClass() {
        log.info(repository.findById(1).toString());
    }
}