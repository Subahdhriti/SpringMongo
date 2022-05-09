package com.springmongo.funcgetOffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableFeignClients
@EnableAsync
@ComponentScan("com.springmongo")
@SpringBootApplication
public class FuncGetOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncGetOffersApplication.class, args);
	}

}
