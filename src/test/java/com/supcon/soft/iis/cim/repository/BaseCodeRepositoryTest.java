package com.supcon.soft.iis.cim.repository;

import com.devskiller.jfairy.Fairy;
import com.supcon.soft.iis.cim.entity.BaseCodeEntity;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseCodeRepositoryTest {
    private final Fairy fairy = Fairy.create(Locale.forLanguageTag("zh"));

    @Autowired
    private BaseCodeRepository repository;

    @Test
    public void insertTest() {
        BaseCodeEntity item = new BaseCodeEntity();
        item.setName(fairy.person().getFullName());
        log.info(item.toString());
        BaseCodeEntity result = repository.saveAndFlush(item);
        log.info(result.toString());
        BaseCodeEntity query = repository.findById(result.getId()).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void updateTest() {
        BaseCodeEntity result = repository.findById(1).orElse(null);
        log.info(result != null ? result.toString() : null);
        assert result != null;
        result.setName(fairy.person().getFullName());
        result.setUpdater(new Submitter());
        log.info(result.toString());
        BaseCodeEntity updated = repository.saveAndFlush(result);
        log.info(updated.toString());
        BaseCodeEntity query = repository.findById(1).orElse(null);
        log.info(query != null ? query.toString() : null);
    }

    @Test
    public void deleteTest(){
        BaseCodeEntity item = new BaseCodeEntity();
        item.setName(fairy.person().getFullName());
        item.setUsed(true);
        repository.saveAndFlush(item);
        log.info(item.toString());
        int id = item.getId();
        repository.delete(item);
        BaseCodeEntity result = repository.findById(id).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void insertGenderCode(){
        BaseCodeEntity entity = new BaseCodeEntity();
        entity.setName("人的性别代码");
        entity.setDescription("GB/T 2261.1-2003");
        log.info(repository.saveAndFlush(entity).toString());
    }

}