package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentClassEntity;
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
public class EquipmentClassRepositoryTest {
    @Autowired
    private EquipmentClassRepository repository;

    @Test
    public void insertTest() {
        EquipmentClassEntity entity = new EquipmentClassEntity();
        entity.setParentId(1);
        entity.setCode("9");
        entity.setName("泵1");
        log.info(entity.toString());
        EquipmentClassEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentClassEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }
    @Test
    public void updateTest() {
        EquipmentClassEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setName("离心泵");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentClassEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentClassEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
        System.out.println("输出查询结果"+query);
    }

    @Test
    public void deleteTest(){
        EquipmentClassEntity item = new EquipmentClassEntity();
        item.setName("离心泵1");
        item.setCode("9");
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentClassEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void insertEquipmentCode(){
        EquipmentClassEntity entity = new EquipmentClassEntity();
        entity.setName("泵的种类");
        entity.setCode("9");
        entity.setDescription("GB/T 2261.1-2003");
        log.info(repository.saveAndFlush(entity).toString());
    }
    @Test
    public void queryEquipmentClass(){
        log.info(repository.findById(1).toString());
    }
}
