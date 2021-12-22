package com.sunits.work_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sunits.work_test.mapper")
public class WorkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkTestApplication.class, args);
    }

}
