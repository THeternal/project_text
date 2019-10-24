package com.kemean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 代研
 * 
 * @Date 2018年6月1日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = { "com.kemean.mapper" })
public class DaikenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaikenApplication.class, args);
	}
}
