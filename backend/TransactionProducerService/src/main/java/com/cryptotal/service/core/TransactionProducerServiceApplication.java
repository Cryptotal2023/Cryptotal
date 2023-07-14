package com.cryptotal.service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class TransactionProducerServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(TransactionProducerServiceApplication.class, args);
    }
}
