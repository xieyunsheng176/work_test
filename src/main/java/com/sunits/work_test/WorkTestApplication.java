package com.sunits.work_test;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAdminServer
@MapperScan("com.sunits.work_test.mapper")
public class WorkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkTestApplication.class, args);
    }

}
