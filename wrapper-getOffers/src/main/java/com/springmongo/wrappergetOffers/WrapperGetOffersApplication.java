package com.springmongo.wrappergetOffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableFeignClients
@EnableAsync
@ComponentScan("com.tatadigital")
@SpringBootApplication
public class WrapperGetOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(WrapperGetOffersApplication.class, args);
	}

}
