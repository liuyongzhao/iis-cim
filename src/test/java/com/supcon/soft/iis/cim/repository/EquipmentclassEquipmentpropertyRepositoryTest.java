package com.supcon.soft.iis.cim.repository;
import com.supcon.soft.iis.cim.entity.EquipmentclassEquipmentpropertyEntity;
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
public class EquipmentclassEquipmentpropertyRepositoryTest {
    @Autowired
    private EquipmentclassEquipmentpropertyRepository repository;

    @Test
    public void insertTest() {
        EquipmentclassEquipmentpropertyEntity entity = new EquipmentclassEquipmentpropertyEntity();
        entity.setEquipmentClassId(1);
        entity.setEquipmentPropertyId(9);
        entity.setUnitId(8);
        entity.setEquipmentPropertyTagId(9);
        log.info(entity.toString());
        EquipmentclassEquipmentpropertyEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentclassEquipmentpropertyEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }
    @Test
    public void updateTest() {
        EquipmentclassEquipmentpropertyEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setUnitId(100);
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentclassEquipmentpropertyEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentclassEquipmentpropertyEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void deleteTest(){
        EquipmentclassEquipmentpropertyEntity item = new EquipmentclassEquipmentpropertyEntity();
        item.setEquipmentClassId(100);
        item.setEquipmentPropertyId(100);
        item.setUnitId(100);
        item.setEquipmentPropertyTagId(100);
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentclassEquipmentpropertyEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmentclassEquipmentproperty(){
        log.info(repository.findById(1).toString());
    }
}
