package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentpropertytagEntity;
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
public class EquipmentpropertytagRepositoryTest {
        @Autowired
        private EquipmentpropertytagRepository repository;

        @Test
        public void insertTest() {
            EquipmentpropertytagEntity entity = new EquipmentpropertytagEntity();
            entity.setName("标识1");
            entity.setDescription("标识类");
            log.info(entity.toString());
            EquipmentpropertytagEntity result = repository.saveAndFlush(entity);
            log.info(result.toString());
            EquipmentpropertytagEntity query = repository.findById(result.getId()).orElse(null);
            log.info(query != null ? query.toString() : null);
        }

        @Test
        public void updateTest() {
            EquipmentpropertytagEntity result = repository.findById(1).orElse(null);
            log.info(result != null ? result.toString() : null);
            assert result != null;
            result.setName("标识100");
            result.setUpdater(new Submitter());
            log.info(result.toString());
            EquipmentpropertytagEntity updated = repository.saveAndFlush(result);
            log.info(updated.toString());
            EquipmentpropertytagEntity query = repository.findById(1).orElse(null);
            log.info(query != null ? query.toString() : null);
            System.out.println("输出查询结果" + query);
        }

        @Test
        public void deleteTest() {
            EquipmentpropertytagEntity item = new EquipmentpropertytagEntity();
            item.setName("标识100");
            item.setDescription("标识类100");
            item.setUsed(true);
            repository.saveAndFlush(item);
            log.info(item.toString());
            int id = item.getId();
            repository.delete(item);
            EquipmentpropertytagEntity result = repository.findById(id).orElse(null);
            Assert.assertNull(result);
        }

        @Test
        public void queryEquipmentpropertytagClass() {
            log.info(repository.findById(1).toString());
        }
}
