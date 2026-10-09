package com.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.lab.module", "com.lab.common.audit.repository"})
@EnableScheduling
public class LabApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, (String[])args);
    }
}

