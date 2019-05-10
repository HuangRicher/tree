package com.tongzhu.treehouse;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tongzhu.treehouse.mapper")
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableDistributedTransaction
@EnableTransactionManagement
public class TreeHouseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeHouseServiceApplication.class, args);
	}
}
