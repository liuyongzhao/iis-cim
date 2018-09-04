package com.supcon.soft.iis.cim.repository;

import com.devskiller.jfairy.Fairy;
import com.supcon.soft.iis.cim.entity.BaseCodeItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseCodeItemRepositoryTest {
    private final Fairy fairy = Fairy.create(Locale.CHINA);

    @Autowired
    private BaseCodeItemRepository repository;

    @Test
    public void insertTest(){
        BaseCodeItemEntity entity = new BaseCodeItemEntity();
        entity.setBaseCodeId(1);
        entity.setCode("9");
        entity.setValue("未说明的性别");
        repository.saveAndFlush(entity);
        log.info(entity.toString());
        log.info(repository.findById(entity.getId()).toString());
    }

    @Test
    public void getSingleRecord(){
        log.info(repository.findById(1).toString());
    }

    @Test
    public void getInUseListByBaseCodeId() {
        List<BaseCodeItemEntity> list = repository.getInUseListByBaseCodeId(1);

        list.forEach(item -> log.info(item.toString()));

    }

    @Test
    public void countTest(){
        log.info(String.valueOf(repository.count()));
    }

    @Test
    public void queryCountTest(){
        log.info(String.valueOf(repository.getInUseListByBaseCodeId(1).size()));
    }


}

