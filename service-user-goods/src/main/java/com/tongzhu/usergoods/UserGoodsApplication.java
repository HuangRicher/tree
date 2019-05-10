package com.tongzhu.usergoods;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tongzhu.usergoods.mapper")
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableDistributedTransaction
@EnableTransactionManagement
@EnableScheduling
public class UserGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserGoodsApplication.class, args);
    }
}
