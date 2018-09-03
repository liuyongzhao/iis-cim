package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestresultEntity;
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
public class EquipmenttestresultRepositoryTest {
    @Autowired
    private EquipmenttestresultRepository repository;

    @Test
    public void insertTest() {
        EquipmenttestresultEntity entity = new EquipmenttestresultEntity();
        entity.setSpecificationId(11);
        log.info(entity.toString());
        EquipmenttestresultEntity result = repository.saveAndFlush(entity);
        log.info(result.toString());
        EquipmenttestresultEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        EquipmenttestresultEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setSpecificationId(110);
        result.setUpdater(new Submitter());
        log.info(result.toString());
        EquipmenttestresultEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        EquipmenttestresultEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);

    }

    @Test
    public void deleteTest() {
        EquipmenttestresultEntity item = new EquipmenttestresultEntity();
        item.setSpecificationId(223);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        EquipmenttestresultEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void queryEquipmenttestresultClass() {
        log.info(repository.findById(1).toString());
    }
}
