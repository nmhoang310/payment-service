package com.tpssoft.paymentservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "paypal")
public class PaypalConfigure {
	private String baseUrl;
	private String signature;
	private String user;
	private String pwd;
}
