package com.supcon.soft.iis.cim.repository;

import com.devskiller.jfairy.Fairy;
import com.supcon.soft.iis.cim.entity.BaseCode;
import lombok.extern.slf4j.Slf4j;
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
        BaseCode item = new BaseCode();
        item.setName(fairy.person().getFullName());
        log.info(item.toString());
        BaseCode result = repository.saveAndFlush(item);
        log.info(result.toString());
    }

    @Test
    public void updateTest() {
        BaseCode result = repository.findById(3).orElse(null);
        log.info(result != null ? result.toString() : null);
    }

}