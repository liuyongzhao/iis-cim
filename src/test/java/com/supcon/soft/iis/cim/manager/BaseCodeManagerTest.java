package com.supcon.soft.iis.cim.manager;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseCodeManagerTest {
    @Autowired
    private BaseCodeManager baseCodeManager;

    @Test
    public void getBaseCodeByIdTest(){
        log.info(baseCodeManager.getBaseCodeById(1).toString());
    }

}