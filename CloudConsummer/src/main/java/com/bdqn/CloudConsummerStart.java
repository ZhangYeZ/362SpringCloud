package com.bdqn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.bdqn")
@SpringBootApplication
@EnableEurekaClient

public class CloudConsummerStart {
    public static void main(String[] args) {

        SpringApplication.run(CloudConsummerStart.class, args);
    }
}
