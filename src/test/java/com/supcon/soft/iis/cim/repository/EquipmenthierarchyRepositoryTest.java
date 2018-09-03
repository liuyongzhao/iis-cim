package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenthierarchyEntity;
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
public class EquipmenthierarchyRepositoryTest {

    @Autowired
    private EquipmenthierarchyRepository repository;

    @Test
    public void insertTest() {
        EquipmenthierarchyEntity entity = new EquipmenthierarchyEntity();
        entity.setCode("1");
        entity.setName("12312");
        entity.setEquipmentType("运输");
        entity.setDescription("设备用于运输");
        log.info(entity.toString());
        EquipmenthierarchyEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmenthierarchyEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }
    @Test
    public void updateTest() {
        EquipmenthierarchyEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setCode("100");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmenthierarchyEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmenthierarchyEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
        System.out.println("输出查询结果"+query);
    }

    @Test
    public void deleteTest(){
        EquipmenthierarchyEntity item = new EquipmenthierarchyEntity();
        item.setCode("1");
        item.setName("12312");
        item.setEquipmentType("运输");
        item.setDescription("设备用于运输");
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmenthierarchyEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }
    @Test
    public void queryEquipmenthierarchyClass(){
        log.info(repository.findById(1).toString());
    }
}
