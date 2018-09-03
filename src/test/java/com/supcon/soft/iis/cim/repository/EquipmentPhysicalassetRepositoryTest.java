package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentPhysicalassetEntity;
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
public class EquipmentPhysicalassetRepositoryTest {

    @Autowired
    private EquipmentPhysicalassetRepository repository;

    @Test
    public void insertTest() {
        EquipmentPhysicalassetEntity entity = new EquipmentPhysicalassetEntity();
        entity.setEquipmentId(1);
        entity.setPhysicalAssetId(4);
        log.info(entity.toString());
        EquipmentPhysicalassetEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmentPhysicalassetEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }
    @Test
    public void updateTest() {
        EquipmentPhysicalassetEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setPhysicalAssetId(100);
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmentPhysicalassetEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmentPhysicalassetEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
        System.out.println("输出查询结果"+query);
    }

    @Test
    public void deleteTest(){
        EquipmentPhysicalassetEntity item = new EquipmentPhysicalassetEntity();
        item.setEquipmentId(12);
        item.setPhysicalAssetId(42);
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmentPhysicalassetEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }
    @Test
    public void queryEquipmentPhysicalassetClass(){
        log.info(repository.findById(1).toString());
    }
}