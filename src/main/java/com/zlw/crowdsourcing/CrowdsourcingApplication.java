package com.zlw.crowdsourcing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zlw.crowdsourcing.mapper")
public class CrowdsourcingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrowdsourcingApplication.class, args);
    }

}
