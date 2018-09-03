package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestresultitemEntity;
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
public class EquipmenttestresultitemRepositoryTest {
    @Autowired
    private EquipmenttestresultitemRepository repository;

    @Test
    public void insertTest() {
        EquipmenttestresultitemEntity entity = new EquipmenttestresultitemEntity();
        entity.setEquipmentId(11);
        entity.setEquipmentPropertyId(12);
        entity.setValue("888");
        entity.setResultId(13);
        log.info(entity.toString());
        EquipmenttestresultitemEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmenttestresultitemEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmenttestresultitemEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setValue("999");
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmenttestresultitemEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmenttestresultitemEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
        System.out.println("输出查询结果" + query);
    }

    @Test
    public void deleteTest() {
        EquipmenttestresultitemEntity item = new EquipmenttestresultitemEntity();
        item.setEquipmentId(111);
        item.setEquipmentPropertyId(112);
        item.setValue("8888");
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmenttestresultitemEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmenttestresultitemClass() {
        log.info(repository.findById(1).toString());
    }
}
