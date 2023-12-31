package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggieTakeOutApplication {

    public static void main(String[] args) {
        log.info("Project running...");
        SpringApplication.run(ReggieTakeOutApplication.class, args);
    }

}
