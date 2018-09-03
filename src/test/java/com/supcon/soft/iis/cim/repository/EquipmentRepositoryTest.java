package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentEntity;
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
public class EquipmentRepositoryTest {
    @Autowired
    private EquipmentRepository repository;

    @Test
    public void insertTest() {
        EquipmentEntity entity = new EquipmentEntity();
        entity.setParentId(1);
        entity.setCode("9");
        entity.setName("泵1");
        log.info(entity.toString());
        EquipmentEntity result = repository.saveAndFlush(entity);
        log.info( result.toString());
        EquipmentEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmentEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setName("离心泵");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void deleteTest() {
        EquipmentEntity item = new EquipmentEntity();
        item.setName("离心泵1");
        item.setCode("9");
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void insertEquipmentCode() {
        EquipmentEntity entity = new EquipmentEntity();
        entity.setName("泵的种类");
        entity.setCode("9");
        entity.setDescription("GB/T 2261.1-2003");
        log.info(repository.saveAndFlush(entity).toString());
    }

    @Test
    public void queryEquipment() {
        log.info(repository.findById(1).toString());
    }
}
