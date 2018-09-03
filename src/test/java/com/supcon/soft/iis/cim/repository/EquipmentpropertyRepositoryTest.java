package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentpropertyEntity;
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
public class EquipmentpropertyRepositoryTest {

    @Autowired
    private EquipmentpropertyRepository repository;

    @Test
    public void insertTest() {
        EquipmentpropertyEntity entity = new EquipmentpropertyEntity();
        entity.setBaseCodeId(1);
        entity.setDimensionId(4);
        entity.setValuetype("sss");
        entity.setDescription("6666");
        entity.setParentId(11);
        entity.setDefaultValue("555");
        entity.setUnitId(66);
        entity.setName("sss");

        log.info(entity.toString());
        EquipmentpropertyEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentpropertyEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmentpropertyEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setBaseCodeId(100);
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentpropertyEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentpropertyEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void deleteTest() {
        EquipmentpropertyEntity item = new EquipmentpropertyEntity();
        item.setBaseCodeId(10);
        item.setDimensionId(400);
        item.setValuetype("sss0");
        item.setDescription("66660");
        item.setParentId(110);
        item.setDefaultValue("5550");
        item.setUnitId(660);
        item.setName("sss0");
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentpropertyEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmentpropertyClass() {
        log.info(repository.findById(1).toString());
    }
}
