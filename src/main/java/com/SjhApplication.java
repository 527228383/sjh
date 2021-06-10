package com;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.sjh.login.dao"})
@ComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括启动类，servlet 等
@ServletComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括 servlet、 filter等
@EnableAutoConfiguration //自动载入应用程序所需的所有Bean，当使用Exclude这个属性时，是禁止自动配置某个类
@SpringBootApplication
@EnableCaching
@EnableHasor
@EnableHasorWeb
public class SjhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjhApplication.class, args);
	}
}
