package com.tongzhu.welfare;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tongzhu.welfare.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableKafka
@EnableTransactionManagement
@EnableDistributedTransaction
public class ServiceWelfareApplication {

    public static void main(String[] args) {
    	SpringApplication.run(ServiceWelfareApplication.class, args);
    }
}
