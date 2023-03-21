package com.zantong.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class BillingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.zantong.contract.BillingSystemApplication.class, args);
	}

}
