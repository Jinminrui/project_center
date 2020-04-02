package com.jmr.projectcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.jmr")
@SpringBootApplication
@EnableFeignClients
public class ProjectCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectCenterApplication.class, args);
    }

}
