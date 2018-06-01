package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
//@EnableDiscoveryClient
@MapperScan(basePackages = {"com.sjh.login.dao"})
@ComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括启动类，servlet 等
@ServletComponentScan(basePackages = {"com"}) // 扫描包下的所有组件，包括 servlet、 filter等
@EnableAutoConfiguration //自动载入应用程序所需的所有Bean，当使用Exclude这个属性时，是禁止自动配置某个类
@SpringBootApplication
public class SjhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjhApplication.class, args);
	}
}
