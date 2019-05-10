package com.tongzhu.fishing;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tongzhu.fishing.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@EnableDistributedTransaction
public class ServiceFishingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFishingApplication.class, args);
    }
}
