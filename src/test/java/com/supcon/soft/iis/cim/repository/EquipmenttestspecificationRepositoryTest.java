package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestspecificationEntity;
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
public class EquipmenttestspecificationRepositoryTest {
    @Autowired
    private EquipmenttestspecificationRepository repository;

    @Test
    public void insertTest() {
        EquipmenttestspecificationEntity entity = new EquipmenttestspecificationEntity();
        entity.setEquipmentClassId(11);
        entity.setName("规范1");
        entity.setDescription("测试规范1");
        log.info(entity.toString());
        EquipmenttestspecificationEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmenttestspecificationEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmenttestspecificationEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setName("规范100");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmenttestspecificationEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmenttestspecificationEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);

    }

    @Test
    public void deleteTest() {
        EquipmenttestspecificationEntity item = new EquipmenttestspecificationEntity();
        item.setEquipmentClassId(11);
        item.setName("规范1");
        item.setDescription("测试规范1");
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmenttestspecificationEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmenttestspecificationClass() {
        log.info(repository.findById(1).toString());
    }
}
