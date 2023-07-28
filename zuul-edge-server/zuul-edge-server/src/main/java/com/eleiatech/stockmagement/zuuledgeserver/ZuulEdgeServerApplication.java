package com.eleiatech.stockmagement.zuuledgeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import org.springframework.web.bind.annotation.CrossOrigin;

@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@CrossOrigin(origins = "*", maxAge = 3600)
public class ZuulEdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeServerApplication.class, args);
	}

}
