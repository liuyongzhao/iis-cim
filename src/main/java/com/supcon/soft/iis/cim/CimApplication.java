package com.supcon.soft.iis.cim;

import com.supcon.soft.iis.cim.utils.CommandUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Application EntryPoint
 * @author qiyuqi
 */
@SpringBootApplication
@EnableRedisHttpSession
public class CimApplication {
    public static void main(String[] args){
        //Process the application.properties argument start with "-Dspring.profile.active="
        CommandUtils.getAndSetSpringProfile(args);
        SpringApplication.run(CimApplication.class, args);
    }
}
