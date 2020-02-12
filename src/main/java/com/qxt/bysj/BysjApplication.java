package com.qxt.bysj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.qxt.bysj"})
@MapperScan("com.qxt.bysj.dao")
@EnableScheduling
public class BysjApplication {

    public static void main(String[] args) {
        SpringApplication.run(BysjApplication.class, args);
    }

}
