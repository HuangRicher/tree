package com.tongzhu.friend;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tongzhu.friend.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@EnableDistributedTransaction
@EnableScheduling
public class FriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendApplication.class, args);
	}
}
